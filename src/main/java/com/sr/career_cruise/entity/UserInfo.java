package com.sr.career_cruise.entity;

import java.time.LocalDateTime;

import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.constant.UserStatuskind;
import com.sr.career_cruise.entity.converter.UserAuthorityConverter;
import com.sr.career_cruise.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
  @Convert(converter = UserStatusConverter.class)
  private UserStatuskind status;
  
  /** ユーザー権限 */
  @Convert(converter = UserAuthorityConverter.class)
  private AuthorityKind authority;

  /** 登録日時 */
  @Column(name = "create_time")
  private LocalDateTime createTime;

  /** 最終更新日時 */
  @Column(name = "update_time")
  private LocalDateTime updateTime;
  
  public UserInfo(){
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo incrementFailurCount(){
    return new UserInfo(name, mailAddress, password, ++loginFailurCount, accountLockedTime, status, authority, createTime, updateTime);
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo resetLoginFailurInfo(){
    return new UserInfo(name, mailAddress, password, 0, null, status, authority, createTime, updateTime);
  }

  /**
	 * アカウントロック状態に更新する
	 * 
	 * @return ログイン失敗回数、アカウントロック日時が更新されたUserInfo
	 */
  public UserInfo updateAccountLocked(){
    return new UserInfo(name, mailAddress, password, 0, LocalDateTime.now(), status, authority, createTime, updateTime);
  }
}
