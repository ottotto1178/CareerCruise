package com.sr.career_cruise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sr.career_cruise.constant.UrlConst;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
  /** ユーザー名のname属性 */
  private final String USERNAME_PARAMETER = "mailAddress";

  /**
   * Spring Securityの設定
   * 
   * @param http カスタマイズパラメーター
   * @return カスタマイズ結果
   * @throws Exception 例外
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
      .authorizeHttpRequests(
        authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
                              .anyRequest().authenticated()
      )
      .formLogin(
        login -> login.loginPage(UrlConst.LOGIN) // LoginControllerのviewメソッドを呼び出す
              .usernameParameter(USERNAME_PARAMETER) // ログイン画面のメールアドレスを取得
              .defaultSuccessUrl(UrlConst.MENU) // ログイン成功時のリダイレクト先
      );
    
    return http.build();
  }
}
