package com.sr.career_cruise.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupMessage {

  SUCCEED(MessageConst.SIGNUP_REGISTER_SUCCEED, false),
  EXISTED_MAIL_ADDRESS(MessageConst.SIGNUP_EXISTED_MAIL_ADDRESS, true);
  
  private String message;
  private boolean isError;
}
