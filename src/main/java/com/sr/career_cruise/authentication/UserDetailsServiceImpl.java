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
    return User.withUsername(userInfo.getMailAddress())
      .password(userInfo.getPassword())
      .roles("USER")
      .build();
  }
}
