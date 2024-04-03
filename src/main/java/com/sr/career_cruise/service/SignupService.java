package com.sr.career_cruise.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.form.SignupForm;
import com.sr.career_cruise.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Service
 * 
 * @author shimokawa
 */
@Service
@RequiredArgsConstructor
public class SignupService {
  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;

  /**
   * ユーザー情報テーブル新規登録
   * 
   * @param form 入力情報
   * @return 登録情報(ユーザー情報Entity)
   */
  public UserInfo registerUserInfo(SignupForm form){
    var userInfo = mapper.map(form, UserInfo.class);
    var encodedPassword = passwordEncoder.encode(form.getPassword());
    userInfo.setPassword(encodedPassword);
    return repository.save(userInfo);
  }
}
