package com.artshop.jin.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.jin.admin.dto.AdminProductManagementDto;
import com.artshop.jin.admin.entity.AdminProductManagementEntity;
import com.artshop.jin.admin.repository.AdminProductManagementRepository;

/**
 * 商品情報管理サービス
 * @author Nancy
 * @since 2025-03-28
 * @version 2.0
 */
@Service
public class AdminProductManagementService {
	@Autowired
	private AdminProductManagementRepository adminProductManagementRepository;

	//画面から登録された情報をEntityに変換して保存し、保存結果もDTOで返す
	public AdminProductManagementDto saveProductInfo(AdminProductManagementDto adminProductInfo) {
		AdminProductManagementEntity adminProductManagementEntity = new AdminProductManagementEntity();

		adminProductManagementEntity.setProductId(adminProductInfo.getProductId());
		adminProductManagementEntity.setProductName(adminProductInfo.getCategoryName());
		adminProductManagementEntity.setProductDescription(adminProductInfo.getProductDescription());
		adminProductManagementEntity.setCategoryName(adminProductInfo.getCategoryName());
		adminProductManagementEntity.setPrice(adminProductInfo.getPrice());
		adminProductManagementEntity.setStockQuantity(adminProductInfo.getStockQuantity());
		adminProductManagementEntity.setStockStatus(adminProductInfo.isStockStatus());
		adminProductManagementEntity.setProductPhoto(adminProductInfo.getProductPhoto());
		adminProductManagementEntity.setDelFlag(adminProductInfo.isDelFlag());

		//画面の商品情報をEntityにマッピングする
		AdminProductManagementEntity savedProductManagementInfo = adminProductManagementRepository
				.save(adminProductManagementEntity);

		AdminProductManagementDto ProductManagementResult = new AdminProductManagementDto();
		ProductManagementResult.setProductId(savedProductManagementInfo.getProductId());
		ProductManagementResult.setProductName(savedProductManagementInfo.getProductName());
		ProductManagementResult.setProductDescription(savedProductManagementInfo.getProductDescription());
		ProductManagementResult.setCategoryName(savedProductManagementInfo.getCategoryName());
		ProductManagementResult.setPrice(savedProductManagementInfo.getPrice());
		ProductManagementResult.setStockQuantity(savedProductManagementInfo.getStockQuantity());
		ProductManagementResult.setStockStatus(savedProductManagementInfo.isStockStatus());
		ProductManagementResult.setProductPhoto(savedProductManagementInfo.getProductPhoto());
		ProductManagementResult.setDelFlag(savedProductManagementInfo.isDelFlag());
		ProductManagementResult.setCreatedAt(savedProductManagementInfo.getCreatedAt());
		ProductManagementResult.setUpdatedAt(savedProductManagementInfo.getUpdatedAt());

		return ProductManagementResult;
	}

}
