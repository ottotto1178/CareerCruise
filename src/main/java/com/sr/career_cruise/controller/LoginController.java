package com.sr.career_cruise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.career_cruise.form.LoginForm;
import com.sr.career_cruise.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

  private final LoginService service;
  
  @GetMapping("/login")
  public String view(Model model, LoginForm loginForm) {
    return "login";
  }

  @PostMapping("/login")
  public String login(Model model, LoginForm form){
    var userInfo = service.searchUserByMailAdress(form.getMailAdress());
    var isCorrectUserAuth = userInfo.isPresent() && form.getPassword().equals(userInfo.get().getPassword());
    if (isCorrectUserAuth){
      return "redirect:/menu";
    }else{
      model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが正しくありません。");
      return "login";
    }
  }
}
