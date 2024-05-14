package com.sr.career_cruise.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sr.career_cruise.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;
  
  /** UserDetailsService */
  private final UserDetailsService userDetailsService;
  
  /** MessageSource */
  private final MessageSource messageSource;
  
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
  
  /**
   * Provider定義
   * 
   * @return カスタマイズProvider情報
   */
  @Bean
  AuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    provider.setMessageSource(messageSource);
    
    return provider;
  }
}
