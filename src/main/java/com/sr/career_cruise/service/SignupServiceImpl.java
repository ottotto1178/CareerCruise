package com.sr.career_cruise.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.constant.UserStatuskind;
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
    userInfo.setAuthority(AuthorityKind.USER_PROFILE_MANAGER);
    userInfo.setStatus(UserStatuskind.ENABLED);
    userInfo.setCreateTime(LocalDateTime.now());
    userInfo.setUpdateTime(LocalDateTime.now());

    return Optional.of(repository.save(userInfo));
  }
}
