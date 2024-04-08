package com.sr.career_cruise.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.career_cruise.constant.MessageConst;
import com.sr.career_cruise.form.LoginForm;
import com.sr.career_cruise.service.LoginService;
import com.sr.career_cruise.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面Controller
 * 
 * @author shimokawa
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

  /** ログイン画面Service */
  private final LoginService service;

  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;

  /** MessageSource */
  private final MessageSource messageSource;
  
  /**
   * 初期表示
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @GetMapping("/login")
  public String view(Model model, LoginForm form) {
    return "login";
  }

  /**
   * ログイン
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @PostMapping("/login")
  public String login(Model model, LoginForm form){
    var userInfo = service.searchUserByMailAdress(form.getMailAdress());
    var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
    if (isCorrectUserAuth){
      return "redirect:/menu";
    }else{
      var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
      model.addAttribute("errorMsg", errorMsg);
      return "login";
    }
  }
}