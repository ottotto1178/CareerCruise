package com.sr.career_cruise.authentication;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sr.career_cruise.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 * 
 * @author shimokawa
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
  /** ユーザー情報テーブルRepository */
  private final UserInfoRepository repository;

  /** ログイン失敗回数制限 */
  @Valie("${security.locking-border-count}")
  private int lockingBorderCount;

  /** ロック時間 */
  @Value("${security.locking-time}")
  private final int lockingTime;

  /**
   * ユーザー情報生成
   * 
   * @param username メールアドレス
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    var userInfo = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    
    var accountLockedTime = userInfo.getAccountLockedTime();
    var isAccountLocked = accountLockedTime != null && accountLockedTime.plusHours(lockingTime).isAfter(LocalDateTime.now());
    
    return User.withUsername(userInfo.getMailAddress())
      .password(userInfo.getPassword())
      .authorities(userInfo.getAuthority())
      .disabled(userInfo.isDisabled())
      .accountLocked(isAccountLocked)
      .build();
  }
  
  /**
   * 認証失敗時に失敗回数を加算、ロック日時を更新
   * 
   * @param event イベント情報
   */
  @EventListener
  public void handle(AuthenticationFailureBadCredentialsEvent event){
    var mailAddress = event.getAuthentication().getName();
    repository.findById(mailAddress).ifPresent(userInfo -> {
      repository.save(userInfo.incrementFailurCount());
      
      var isReachFailurCount = userInfo.getLoginFailurCount() == lockingBorderCount;
      if(isReachFailurCount){
        repository.save(userInfo.updateAccountLocked());
      }
    });
  }
  
  /**
   * 認証成功時に失敗回数をリセット
   * 
   * @param event イベント情報
   */
  @EventListener
  public void handle(AuthenticationSuccessEvent event){
    var mailAddress = event.getAuthentication().getName();
    repository.findById(mailAddress).ifPresent(userInfo -> {
      repository.save(userInfo.resetLoginFailurInfo());
    });
  }
}
