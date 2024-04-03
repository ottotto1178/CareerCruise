package com.sr.career_cruise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sr.career_cruise.entity.UserInfo;

/**
 * ユーザー情報テーブルDAO
 * 
 * @author shimokawa
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
  
}
