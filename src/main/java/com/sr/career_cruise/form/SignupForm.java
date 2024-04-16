package com.sr.career_cruise.form;

import lombok.Data;

/**
 * ユーザー登録画面form
 * 
 * @author shimokawa
 */
@Data
public class SignupForm {
  
  private String name;
  private String mailAdress;
  private String password;
}
