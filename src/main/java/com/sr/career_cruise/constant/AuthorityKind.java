package com.sr.career_cruise.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー権限種別Enumクラス
 * 
 * @author shimokawa
 */
 @Getter
 @AllArgsConstructor
 public enum AuthorityKind{
   /** 企業ごとの進捗登録・更新が可能 */
   COMPANY_MANAGER("1"),
   
   /** 自ユーザーについてプロフィールの閲覧・編集が可能 */
   USER_PROFILE_MANAGER("2"),
   
   /** 全ユーザーについてプロフィールの閲覧・編集が可能 */
   ALL_USER_PROFILE_MANAGER("3");
   
   private String AuthorityKind;
 }