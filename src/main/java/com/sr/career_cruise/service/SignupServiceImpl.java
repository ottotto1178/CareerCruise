package com.sr.career_cruise.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.form.SignupForm;
import com.sr.career_cruise.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Service実装クラス
 * 
 * @author shimokawa
 */
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<UserInfo> registerUserInfo(SignupForm form){
    var userInfoExistedOpt = repository.findById(form.getMailAddress());
    if(userInfoExistedOpt.isPresent()){
      return Optional.empty();
    }
    var userInfo = mapper.map(form, UserInfo.class);
    var encodedPassword = passwordEncoder.encode(form.getPassword());
    userInfo.setPassword(encodedPassword);
    userInfo.setAuthority(AuthorityKind.COMPANY_MANAGER.getAuthorityKind());
    return Optional.of(repository.save(userInfo));
  }
}
