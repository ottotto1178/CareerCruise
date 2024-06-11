package com.sr.career_cruise.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * ユーザー状態種別Enumクラス
 * 
 * <p>利用可能か、利用不可かを表す
 * 
 * @author shimokawa
 */
@Getter
@AllArgsConstructor
public enum UserStatuskind {
  /** 利用可能 */
  ENABLED(true, "利用可能"),
  /** 利用不可 */
  DISABLED(false, "利用不可");

  /** 利用可能か */
  private boolean isEnabled;

  /** viewで表示する説明文 */
  private String displayValue;
}
