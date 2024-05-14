package com.sr.career_cruise.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.constant.UrlConst;


/**
 * メニュー画面Controller
 * 
 * @author shimokawa
 * 
 */

@Controller
public class MenuController {
  /**
   * 初期表示
   * 
   * @return 表示画面
   */
  @GetMapping(UrlConst.MENU)
  public String view(@AuthenticationPrincipal User user, Model model){
    var hasUserManageAuth = user.getAuthorities().stream()
      .allMatch(authority -> authority.getAuthority().equals(AuthorityKind.COMPANY_MANAGER.getAuthorityKind()));
    
    model.addAttribute("hasUserManageAuth", hasUserManageAuth);
    
    return "menu";
  }
}