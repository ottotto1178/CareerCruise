package com.sr.career_cruise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.career_cruise.form.LoginForm;

@Controller
public class LoginController {

  private static final String MAIL_ADRESS = "user";
  private static final String PASSWORD = "pwd";

  @GetMapping("/login")
  public String view(Model model, LoginForm loginForm) {
    return "login";
  }

  @PostMapping("/login")
  public String login(Model model, LoginForm form){
    var isCorrectUserAuth = form.getMailAdress().equals(MAIL_ADRESS) && form.getPassword().equals(PASSWORD);
    if (isCorrectUserAuth){
      return "redirect:/menu";
    }else{
      model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが正しくありません。");
      return "login";
    }
  }
}
