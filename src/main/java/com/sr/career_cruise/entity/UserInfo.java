package com.sr.career_cruise.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー情報テーブルEntity
 * 
 * @author shimokawa
 */
@Entity
@Table(name="user_info")
@Data
@AllArgsConstructor
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
  private  LocalDateTime accountLockedTime;
  
  /** 利用可不可 */
  @Column(name = "is_disabled")
  private boolean isDisabled;
  
  // TODO 外部テーブルの作成
  /** ユーザー権限 */
  @Column
  private String authority;
  
  public UserInfo(){
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo incrementFailurCount(){
    return new UserInfo(name, mailAddress, password, ++loginFailurCount, accountLockedTime, isDisabled, authority);
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo resetLoginFailurInfo(){
    return new UserInfo(name, mailAddress, password, 0, null, isDisabled, authority);
  }

  /**
	 * アカウントロック状態に更新する
	 * 
	 * @return ログイン失敗回数、アカウントロック日時が更新されたUserInfo
	 */
  public UserInfo updateAccountLocked(){
    return new UserInfo(name, mailAddress, password, 0, LocalDateTime.now(), isDisabled, authority);
  }
}
