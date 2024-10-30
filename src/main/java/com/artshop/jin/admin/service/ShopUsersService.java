package com.artshop.jin.admin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artshop.jin.admin.entity.ShopUsersEntity;
import com.artshop.jin.admin.object.ShopUsersObject;
import com.artshop.jin.admin.repository.ShopUsersRepository;

/**
 * ユーザー情報管理サービス
 * @author Nancy
 * @since 2024-09-20
 * @version 1.0
 */
@Service
public class ShopUsersService {

	@Autowired
	private ShopUsersRepository shopUsersRepository;
	/**
	 * ユーザ情報リストを取得する
	 * @return userListEntityList
	 */
	@Transactional
	public List<ShopUsersObject> getUserInfoList() {
		// 全てのユーザー情報を取得する
		List<ShopUsersEntity> userListEntityList = shopUsersRepository.findAll();

		return userListEntityList.stream()
				.map(entity -> new ShopUsersObject(
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

	/**
	 * 新規ユーザ情報をDBに登録する
	 * @param newUserInfo
	 * @return shopUsersRepository
	 */
	public ShopUsersObject creatUserInfo(ShopUsersObject newUserInfo) {
		//登録ユーザ名とメールを使用していることを判断する
		if (shopUsersRepository.findByUsersNameAndUsersMail(newUserInfo.getUsersName(), newUserInfo.getUsersMail())
				.isPresent()) {
			throw new IllegalArgumentException("このユーザ名とメールは既に使用されています。");
		}
		return shopUsersRepository.save(newUserInfo);
	}

	/**
	 * ユーザーIDでユーザー情報を取得する
	 * @param usersId
	 */
	public ShopUsersObject getUserInfoById(Long usersId) {
		//NullPointerExceptionを防ぐするため(orElse)
		ShopUsersEntity userInfo = shopUsersRepository.findByUsersIdAndDelFlag(usersId, "0").orElse(null);
		;
		if (userInfo != null) {
			return new ShopUsersObject(
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

	/**
	 * ユーザ編集モーダル情報を更新して保存する
	 * @param usersId
	 * @param name
	 * @param email
	 * @param role
	 * @param points
	 * @param postCode
	 * @param address1
	 * @param address2
	 * @param address3
	 * @param deFlag
	 * @return ShopUsersObject
	 */
	public ShopUsersObject saveUserinfoById(Long usersId, String name, String email, int role, int points,
			String postCode, String address1, String address2, String address3, String deFlag) {

		//既存のユーザIDから該当のユーザ情報を取得
		ShopUsersEntity existingEntity = shopUsersRepository.findByUsersIdAndDelFlag(usersId, "0").orElse(null);

		boolean res = shopUsersRepository.existsById(usersId);

		if (!res) {
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
			ShopUsersEntity saveUserInfoById = shopUsersRepository.save(existingEntity);

			//保存したユーザ情報がオブジェクトに返却する
			return new ShopUsersObject(
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

	/**
	 * ユーザ情報を削除する
	 * @param usersId
	 * @return
	 */
	@Transactional
	public ShopUsersObject delUserInfo(Long usersId) {

		//ユーザの情報を見つける
		Optional<ShopUsersEntity> optionalUser = shopUsersRepository.findById(usersId);

		//ユーザ存在することを判断する
		if (optionalUser.isPresent()) {
			ShopUsersEntity userDel = optionalUser.get();
			//削除フラグを１にセットする
			userDel.setDelFlag("1");
			// 更新日時を設定
			userDel.setUpdatedAtTime(LocalDateTime.now());
			//更新した情報を返す
			shopUsersRepository.save(userDel);
			return new ShopUsersObject(
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
