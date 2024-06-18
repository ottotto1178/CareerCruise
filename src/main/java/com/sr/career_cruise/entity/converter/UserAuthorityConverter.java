package com.sr.career_cruise.entity.converter;

import com.sr.career_cruise.constant.AuthorityKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserAuthorityConverter implements AttributeConverter<AuthorityKind, String> {
  /**
   * 引数で受け取ったユーザー権限enumをコード値に変換する
   * 
   * @param ユーザー権限enum
   * @return 引数で受け取ったユーザー権限に保管されているコード値
   */
  @Override
  public String convertToDatabaseColumn(AuthorityKind authorityKind) {
    return authorityKind.getCode();
  }

  /**
   * 引数で受け取ったユーザー権限コード値をenumに変換する
   * 
   * @param ユーザー権限コード値
   * @return 引数で受け取ったユーザー権限コード値に対応するユーザー権限enum
   */
  @Override
  public AuthorityKind convertToEntityAttribute(String value) {
    return AuthorityKind.from(value);
  }
}
