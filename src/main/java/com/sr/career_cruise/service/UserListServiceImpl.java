package com.sr.career_cruise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.sr.career_cruise.dto.UserListInfo;
import com.sr.career_cruise.entity.UserInfo;
import com.sr.career_cruise.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author shimokawa
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {
  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserListInfo> editUserList() {
    return toUserListInfos(repository.findAll());
  }

  /**
   * ユーザー情報Entityリストをユーザー情報一覧画面DTOリストに変換する
   * 
   * @param userInfos ユーザー情報Entityリスト
   * @return ユーザー情報一覧画面DTOリスト
   */
  private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
    var userListInfos = new ArrayList<UserListInfo>();
    for (UserInfo userInfo : userInfos) {
      var userListInfo = mapper.map(userInfo, UserListInfo.class);
      userListInfo.setStatus(userInfo.getStatus().getDisplayValue());
      userListInfo.setAuthority(userInfo.getAuthority().getDisplayValue());
      userListInfos.add(userListInfo);
    }

    return userListInfos;
  }
}
