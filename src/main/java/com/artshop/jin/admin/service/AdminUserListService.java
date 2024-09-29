package com.artshop.jin.admin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artshop.jin.admin.entity.AdminUserListEntity;
import com.artshop.jin.admin.object.AdminUserListObject;
import com.artshop.jin.admin.repository.AdminUserListRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminUserListService {

	@Autowired
	private AdminUserListRepository adminUserListRepository;

	//ユーザ情報リストを取得する
	@Transactional
	public List<AdminUserListObject> getUserInfoList() {
		// delFlagが0のユーザーのみを取得
		List<AdminUserListEntity> userListEntityList = adminUserListRepository.findByDelFlag(0);

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
						entity.getUsersPoints(),
						entity.getDelFlag()))
				.collect(Collectors.toList());
	}

	//ユーザIDを元に、情報を取得する
	public AdminUserListObject getUserInfoById(Long usersId) {
		//NullPointerExceptionを防ぐするため(orElse)
		AdminUserListEntity userInfo = adminUserListRepository.findByUsersIdAndDelFlag(usersId, 0).orElse(null);
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
					userInfo.getUsersPoints(),
					userInfo.getDelFlag());
		} else {
			return null;
		}

	}

	//ユーザ編集モーダル情報を更新して保存する
	public AdminUserListObject saveUserinfoById(Long usersId, String name, String email, int role, int points,
			String postCode, String address1, String address2, String address3, int deFlag) {

		//既存のユーザIDから該当のユーザ情報を取得
		AdminUserListEntity existingEntity = adminUserListRepository.findByUsersIdAndDelFlag(usersId, 0).orElse(null);

		if (existingEntity != null) {
			existingEntity.setUsersName(name);
			existingEntity.setUsersMail(email);
			existingEntity.setUsersRoles(role);
			existingEntity.setUsersPoints(points);
			existingEntity.setPostCode(postCode);
			existingEntity.setUsersAddress1(address1);
			existingEntity.setUsersAddress2(address2);
			existingEntity.setUsersAddress3(address3);
			existingEntity.setUpdatedAtTime(LocalDateTime.now());

			//取得した該当のユーザ情報をエンティティに保存する
			AdminUserListEntity saveUserInfoById = adminUserListRepository.save(existingEntity);

			//保存したユーザ情報がオブジェクトに返却する
			return new AdminUserListObject(
					saveUserInfoById.getUsersId(),
					saveUserInfoById.getUsersName(),
					saveUserInfoById.getUsersMail(),
					saveUserInfoById.getUsersRoles(),
					saveUserInfoById.getUsersStatus(),
					saveUserInfoById.getCreatedAtTime(),
					saveUserInfoById.getUpdatedAtTime(),
					saveUserInfoById.getPostCode(),
					saveUserInfoById.getUsersAddress1(),
					saveUserInfoById.getUsersAddress2(),
					saveUserInfoById.getUsersAddress3(),
					saveUserInfoById.getUsersPoints(),
					saveUserInfoById.getDelFlag());
		} else {
			return null;

		}
	}

	//ユーザ情報を削除する
	@Transactional
	public AdminUserListObject delUserInfo(Long usersId) {

		//ユーザの情報を見つける
		Optional<AdminUserListEntity> optionalUser = adminUserListRepository.findById(usersId);

		//ユーザ存在することを判断する
		if (optionalUser.isPresent()) {
			AdminUserListEntity userDel = optionalUser.get();
			//削除フラグを１にセットする
			userDel.setDelFlag(1);
			// 更新日時を設定
			userDel.setUpdatedAtTime(LocalDateTime.now());
			//更新した情報を返す
			adminUserListRepository.save(userDel);
			return new AdminUserListObject(
					userDel.getUsersId(),
					userDel.getUsersName(),
					userDel.getUsersMail(),
					userDel.getUsersRoles(),
					userDel.getUsersStatus(),
					userDel.getCreatedAtTime(),
					userDel.getUpdatedAtTime(),
					userDel.getPostCode(),
					userDel.getUsersAddress1(),
					userDel.getUsersAddress2(),
					userDel.getUsersAddress3(),
					userDel.getUsersPoints(),
					userDel.getDelFlag());
		} else {
			// ユーザ存在しない場合
			throw new EntityNotFoundException("こちらのユーザーが見つかりません。");
		}

	}

}
