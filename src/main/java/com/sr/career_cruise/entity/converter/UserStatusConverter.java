package com.sr.career_cruise.entity.converter;

import com.sr.career_cruise.constant.UserStatuskind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatuskind, Boolean> {
  /**
   * 引数で受け取ったユーザー状態enumをbooleanに変換する
   * 
   * @param ユーザー状態enum
   * @return ユーザー状態に保管されているboolean
   */
  @Override
  public Boolean convertToDatabaseColumn(UserStatuskind userStatus) {
    return userStatus.isEnabled();
  }

  /**
   * 引数で受け取ったユーザー権限をenumに変換する
   * 
   * @param 利用可不可(利用可能ならtrue)
   * @return 引数で受け取った利用可不可に対応するユーザー状態enum
   */
  @Override
  public UserStatuskind convertToEntityAttribute(Boolean isEnabled) {
    return isEnabled ? UserStatuskind.ENABLED : UserStatuskind.DISABLED;
  }
}
