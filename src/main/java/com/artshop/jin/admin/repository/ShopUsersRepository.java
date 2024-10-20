package com.artshop.jin.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.ShopUsersEntity;

/**
 * ユーザー情報管理リポジトリ
 * @author Nancy
 * @since 2024-09-20
 * @version 1.0
 */
@Repository
public interface ShopUsersRepository extends JpaRepository<ShopUsersEntity, Long> {
	
	// ユーザーID と delFlag でユーザ情報を取得
	Optional<ShopUsersEntity> findByUsersIdAndDelFlag(Long usersId, String delFlag);
	
}
