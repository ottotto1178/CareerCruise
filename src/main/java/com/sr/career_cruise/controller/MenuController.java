package com.sr.career_cruise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
  @GetMapping("/menu")
  public String view(){
    return "menu";
  }
}
