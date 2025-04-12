package com.artshop.jin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.ProductInfoEntity;

@Repository
public interface AdminProductManagementRepository extends JpaRepository<ProductInfoEntity, Long> {
}
