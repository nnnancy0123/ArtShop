package com.artshop.jin.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.ShopUsersEntity;

@Repository
public interface ShopUsersRepository extends JpaRepository<ShopUsersEntity, Long> {

    // 全てのユーザー情報を取得する
    List<ShopUsersEntity> findAll();

    // ユーザーID と delFlag でユーザ情報を取得
    Optional<ShopUsersEntity> findByUsersIdAndDelFlag(Long usersId, String delFlag);
}
