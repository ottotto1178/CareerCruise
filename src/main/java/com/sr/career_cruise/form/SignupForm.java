package com.sr.career_cruise.form;

import lombok.Data;

/**
 * ユーザー登録画面form
 * 
 * @author shimokawa
 */
@Data
public class SignupForm {
  
  @NotBlank
  private String name;
  
  @NotBlank
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\.)+[a-zA-Z]{2,}$", message = "{signup.invalidMailAddress}")
  private String mailAddress;
  
  @Length(min = 8, max = 20)
  private String password;
}
