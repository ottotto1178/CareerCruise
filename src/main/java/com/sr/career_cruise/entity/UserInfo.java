package com.sr.career_cruise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報テーブルEntity
 * 
 * @author shimokawa
 */
@Entity
@Table(name="user_info")
@Data
public class UserInfo {
  /** 氏名 */
  private String name;

  /** メールアドレス */
  @Id
  @Column(name = "mail_address")
  private String mailAddress;

  /** パスワード */
  private String password;
  
  /** ログイン失敗回数 */
  @Column(name = "login_failur_count")
  private int loginFailurCount = 0;
  
  /** アカウントロック日時 */
  @Column(name = "account_locked_time")
  private LocalDateTime accountLockedTime;
  
  /** 利用可不可 */
  @Column(name = "is_disabled")
  private boolean isDisabled;
  
  public UserInfo(){
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo incrementFailurCount(){
    return new UserInfo(name, mailaddress, password, ++loginFailurCount, accountLockedTime, isDisabled);
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo resetLoginFailurInfo(){
    return new UserInfo(name, mailaddress, password, 0, null, isDisabled);
  }
}
