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
  private String name;

  /** メールアドレス */
  @Id
  @Column(name = "mail_address")
  private String mailAdress;

  /** パスワード */
  private String password;
}
