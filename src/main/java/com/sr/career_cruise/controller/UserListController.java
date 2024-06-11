package com.sr.career_cruise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sr.career_cruise.constant.AuthorityKind;
import com.sr.career_cruise.constant.UrlConst;
import com.sr.career_cruise.constant.UserStatuskind;
import com.sr.career_cruise.form.UserListForm;

@Controller
public class UserListController {
  /** モデルキー: ユーザー情報リスト */
  private static final String KEY_USER_STATUS_KIND = "userStatusKinds";
  private static final String KEY_AUTHORITY_KIND = "authorityKinds";
  
  /**
   * 画面の初期表示を行う
   * 
   * <p>その際、viewでの選択項目「アカウント状態」、「所有権限」を生成する
   * 
   * @param model モデル
   * @return 表示画面
   */
  @GetMapping(UrlConst.USER_LIST)
  public String view(Model model, UserListForm form) {
    model.addAttribute(KEY_USER_STATUS_KIND, UserStatuskind.values());
    model.addAttribute(KEY_AUTHORITY_KIND, AuthorityKind.values());

    return "userList";
  }
}
