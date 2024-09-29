package com.artshop.jin.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.AdminProductListEntity;

@Repository
public interface AdminProductListRepository extends JpaRepository<AdminProductListEntity, Long> {
       
	List<AdminProductListEntity> findProductByDelFlag(int delFlag);
}
