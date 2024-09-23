package com.artshop.jin.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artshop.jin.admin.entity.AdminUserListEntity;
import com.artshop.jin.admin.object.AdminUserListObject;
import com.artshop.jin.admin.repository.AdminUserListRepository;

@Service
public class AdminUserListService {

	@Autowired
	private AdminUserListRepository adminUserListRepository;

	//ユーザ情報リストを取得する
	@Transactional
	public List<AdminUserListObject> getUserInfoList() {
		List<AdminUserListEntity> userListEntityList = adminUserListRepository.findAll();

		return userListEntityList.stream()
				.map(entity -> new AdminUserListObject(
						entity.getUsersId(),
						entity.getUsersName(),
						entity.getUsersMail(),
						entity.getUsersRoles(),
						entity.getUsersStatus(),
						entity.getCreatedAtTime(),
						entity.getUpdatedAtTime(),
						entity.getPostCode(),
						entity.getUsersAddress1(),
						entity.getUsersAddress2(),
						entity.getUsersAddress3(),
						entity.getUsersPoints()))
				.collect(Collectors.toList());

	}

	//ユーザIDを元に、情報を取得する
	public AdminUserListObject getUserInfoById(Long usersId) {
		//NullPointerExceptionを防ぐするため(orElse)
		AdminUserListEntity userInfo = adminUserListRepository.findById(usersId).orElse(null);
		;
		if (userInfo != null) {
			return new AdminUserListObject(
					userInfo.getUsersId(),
					userInfo.getUsersName(),
					userInfo.getUsersMail(),
					userInfo.getUsersRoles(),
					userInfo.getUsersStatus(),
					userInfo.getCreatedAtTime(),
					userInfo.getUpdatedAtTime(),
					userInfo.getPostCode(),
					userInfo.getUsersAddress1(),
					userInfo.getUsersAddress2(),
					userInfo.getUsersAddress3(),
					userInfo.getUsersPoints());
		} else {
			return null;
		}

	}

}
