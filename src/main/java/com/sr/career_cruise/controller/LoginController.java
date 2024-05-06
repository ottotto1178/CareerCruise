package com.sr.career_cruise.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.career_cruise.constant.MessageConst;
import com.sr.career_cruise.constant.UrlConst;
import com.sr.career_cruise.form.LoginForm;
import com.sr.career_cruise.service.LoginService;
import com.sr.career_cruise.util.AppUtil;

import jakarta.servlet.http.HttpSession;
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

  /** セッション情報 */
  private final HttpSession session;
  
  /**
   * 初期表示
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @GetMapping(UrlConst.LOGIN)
  public String view(Model model, LoginForm form) {
    return "login";
  }

  /**
   * 初期表示(エラー)
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @GetMapping(value = UrlConst.LOGIN, params = "error")
  public String viewWithError(Model model, LoginForm form) {
    var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    model.addAttribute("errorMsg", errorInfo.getMessage());
    return "login";
  }

  /**
   * ログイン
   * 
   * @param model モデル
   * @param form 入力情報 
   * @return 表示画面
   */
  @PostMapping(UrlConst.LOGIN)
  public String login(Model model, LoginForm form){
    var userInfo = service.searchUserBymailAddress(form.getMailAddress());
    var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
    if (isCorrectUserAuth){
      return "redirect:/menu";
    }else{
      var errorMsg = AppUtil.getMessage(messageSource, MessageConst.FORM_ERROR);
      model.addAttribute("errorMsg", errorMsg);
      return "login";
    }
  }
}