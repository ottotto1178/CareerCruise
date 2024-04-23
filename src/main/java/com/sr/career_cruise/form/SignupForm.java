package com.sr.career_cruise.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ユーザー登録画面form
 * 
 * @author shimokawa
 */
@Data
public class SignupForm {
  
  @NotBlank(message = "{signup.invalidName}")
  private String name;
  
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\\.)+[a-zA-Z]{2,}$", message = "{signup.invalidMailAddress}")
  private String mailAddress;
  
  @Length(min = 8, max = 16)
  private String password;
}
