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
   * @param bdResult バリデーションチェック結果
   * @return 表示画面
   */
  @PostMapping("/signup")
  public void signup(Model model, @Validated SignupForm form, BindingResult bdResult){
    if(bdResult.hasErrors()){
      editGuideMessage(model, MessageConst.FORM_ERROR, true);
      return;
    }
    var userInfoOpt = service.registerUserInfo(form);
    var signupMessage = judgeMessageKey(userInfoOpt);
    editGuideMessage(model, signupMessage.getMessageId(), signupMessage.isError());
  }
  
  /**
   * 表示するガイドメッセージを設定する
   * 
   * @param model モデル
   * @param messageId メッセージID
   * @param isError エラー有無
   */
  private void editGuideMessage(Model model, String messageId, boolean isError){
    var message = AppUtil.getMessage(messageSource, messageId);
    model.addAttribute("message", message);
    model.addAttribute("isError", isError);
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