package com.sr.career_cruise.form;

import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.constant.UserStatuskind;

import lombok.Data;

@Data
public class UserListForm {
  /** メールアドレス */
  private String mailAddress;
  /** アカウント状態 */
  private UserStatuskind userStatusKind;
  /** ユーザー権限 */
  private AuthorityKind authorityKind;
}
