package com.artshop.jin.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.ShopProductsEntity;

@Repository
public interface ShopProductsRepository extends JpaRepository<ShopProductsEntity, Long> {
       
	List<ShopProductsEntity> findProductByDelFlag(int delFlag);
}
