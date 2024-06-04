package com.sr.career_cruise.service;

import java.util.Optional;

import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.form.SignupForm;

/**
 * ユーザー登録画面Serviceインターフェース
 * 
 * @author shimokawa
 */
public interface SignupService {

  /**
   * 画面の入力情報を元にユーザー情報テーブルの新規登録を行う
   * 
   * <p>但し、以下のテーブル項目はこの限りではない</p>
   * <ul>
   * <li>パスワード:画面で入力したものがハッシュ化され登録される</li>
   * <li>権限:常に「進捗状況の確認が可能」のコード血が登録される</li>
   * </ul>
   * 
   * @param form 入力情報
   * @return 登録情報(ユーザー情報Entity)、既に同じメールアドレスで登録されている場合はEmpty
   */
  public Optional<UserInfo> registerUserInfo(SignupForm form);
}
