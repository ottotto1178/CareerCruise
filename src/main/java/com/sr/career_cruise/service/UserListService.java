package com.sr.career_cruise.service;

import java.util.List;

import com.sr.career_cruise.dto.UserListInfo;

/**
 * ユーザー一覧画面Serviceインターフェース
 * 
 * <p>ユーザー一覧画面に必要な情報を取得する
 * 
 * @author shimokawa
 */
public interface UserListService {
  /**
   * ユーザー情報テーブルを全権検索し、ユーザー一覧画面に必要な情報を取得する
   * 
   * @return ユーザー一覧画面に必要な情報
   */
  public List<UserListInfo> editUserList();
}
