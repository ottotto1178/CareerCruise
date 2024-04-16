package com.sr.career_cruise.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.career_cruise.constant.SignupMessage;
import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.form.SignupForm;
import com.sr.career_cruise.service.SignupService;
import com.sr.career_cruise.util.AppUtil;


import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Controller
 * 
 * @author shimokawa
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

  /** ユーザー登録画面Service */
  private final SignupService service;

  /** MessageSource */
  private final MessageSource messageSource;

  /**
   * 初期表示
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @GetMapping("/signup")
  public String view(Model model, SignupForm form) {
    return "signup";
  }

  /**
   * ユーザー登録
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @PostMapping("/signup")
  public void signup(Model model, SignupForm form){
    var userInfoOpt = service.registerUserInfo(form);
    var signupMessage = judgeMessageKey(userInfoOpt);
    var messageId = AppUtil.getMessage(messageSource, signupMessage.getMessage());
    model.addAttribute("message", messageId);
    model.addAttribute("isError", signupMessage.isError());
  }
  
  /**
   * ユーザー情報登録時のメッセージキー判定
   * 
   * @param userinfoOpt ユーザー情報登録結果(既に登録されている場合はEmpty)
   * @return メッセージキー
   */
  private SignupMessage judgeMessageKey(Optional<UserInfo> userinfoOpt){
    if(userinfoOpt.isEmpty()){
      return SignupMessage.EXISTED_MAIL_ADDRESS;
    }else{
      return SignupMessage.SUCCEED;
    }
  }
}