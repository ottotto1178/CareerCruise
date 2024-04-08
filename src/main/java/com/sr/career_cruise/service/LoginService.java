package com.sr.career_cruise.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面Service
 * 
 * @author shimokawa
 */
@Service
@RequiredArgsConstructor
public class LoginService {
  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /**
   * ユーザー情報テーブルキー検索
   * @param mailAdress メールアドレス
   * @return 検索結果
   */
  public Optional<UserInfo> searchUserByMailAdress(String mailAdress){
    return repository.findById(mailAdress);
  }
}
