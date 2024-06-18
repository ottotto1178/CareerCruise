package com.sr.career_cruise.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー権限種別Enumクラス
 * 
 * @author shimokawa
 */
 @Getter
 @AllArgsConstructor
 public enum AuthorityKind{
  /** 登録内容が不正 */
  UNKNOWN("", "登録内容が不正"),
  /** 企業ごとの進捗登録・更新が可能 */
  COMPANY_MANAGER("1", "企業ごとの進捗登録・更新が可能"),
  
  /** 自ユーザーについてプロフィールの閲覧・編集が可能 */
  USER_PROFILE_MANAGER("2", "自ユーザーについてプロフィールの閲覧・編集が可能"),
  
  /** 全ユーザーについてプロフィールの閲覧・編集が可能 */
  ALL_USER_PROFILE_MANAGER("3", "全ユーザーについてプロフィールの閲覧・編集が可能"),;
  
  /** 権限種別 */
  private String code;
  /** viewで表示する説明文 */
  private String displayValue;

  /**
   * 引数で受け取ったユーザー権限に対応するAuthorityKindを返す
   * 
   * @param code 権限種別
   * @return ユーザー権限コード値に対応するenum, 存在しない場合はUNKNOWN
   */
  public static AuthorityKind from(String code) {
    return Arrays.stream(AuthorityKind.values())
                  .filter(authorityKind -> authorityKind.getCode().equals(code))
                  .findFirst()
                  .orElse(UNKNOWN);
  }
 }