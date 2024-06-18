package com.sr.career_cruise.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー情報一覧画面DTOクラス
 * 
 * @author shimokawa
 */
@Data
public class UserListInfo {
  /** メールアドレス */
  private String mailAddress;

  /** ログイン失敗回数 */
  private int loginFailurCount;

  /** アカウントロック日時 */
  private  LocalDateTime accountLockedTime;

  /** 利用可不可 */
  private String status;

  /** ユーザー権限 */
  private String authority;

  /** 登録日時 */
  private LocalDateTime createTime;

  /** 最終更新日時 */
  private LocalDateTime updateTime;
}
