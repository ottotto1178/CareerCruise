package com.sr.career_cruise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.formLogin(
      login -> login.loginPage("/login") // LoginControllerのviewメソッドを呼び出す
            .usernameParameter("mailAddress") // ログイン画面のメールアドレスを取得
            .defaultSuccessUrl("/menu") // ログイン成功時のリダイレクト先
    );
    
    return http.build();
  }
}
