package com.artshop.jin.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.AdminUserListEntity;

@Repository
public interface AdminUserListRepository extends JpaRepository<AdminUserListEntity, Long> {
	// delFlagが0のユーザーを取得
	List<AdminUserListEntity> findByDelFlag(int delFlag);

	// ユーザーIDとdelFlagでユーザ情報を取得
	Optional<AdminUserListEntity> findByUsersIdAndDelFlag(Long usersId, int delFlag);

}