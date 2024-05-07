package com.sr.career_cruise.authentication;

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

  /**
   * ユーザー情報生成
   * 
   * @param username メールアドレス
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    var userInfo = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    
    var accountLockedTime = userInfo,getAccountLockedTime();
    var is AccountLocked = accountLockedTime != null && accountLockedTime.plusHours(LOCKING_TIME).isAfter(LocalDateTime.now());
    
    return User.withUsername(userInfo.getMailAddress())
      .password(userInfo.getPassword())
      .roles("USER")
      .disabled(userInfo.isDisabled())
      .accountLocked(isAccountLocked  )
      .build();
  }
  
  /**
   * 認証失敗時に失敗回数を加算、ロック日時を更新
   * 
   * @param event イベント情報
   */
  @EventListener
  public void handle(AuthenticationFailurBadCredentialEvent event){
    var mailAddress = event.getAuthentication().getName();
    repository.findById(mailAddress).ifPresent(userInfo -> {
      repository.save(userInfo.incrementLoginFailurCount());
      
      var isReachFailurCount = userInfo.getLoginFailurCount() == LOCKING_BORDER_COUNT;
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
