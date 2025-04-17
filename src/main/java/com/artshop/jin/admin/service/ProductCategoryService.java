package com.artshop.jin.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.jin.admin.dto.ProductCategoryDto;
import com.artshop.jin.admin.entity.ProductCategoryEntity;
import com.artshop.jin.admin.repository.ProductCategoryRepository;

/**
 * 商品カテゴリサービス
 * @author Nancy
 * @since 2025-04-17
 * @version 2.0
 */
@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	/**
	 * 画面から登録されたカテゴリ情報をDBに保存し、保存結果もカテゴリDTOで返す
	 * @param productCategoryInfo 画面からのカテゴリ情報DTO
	 * @return 登録されたカテゴリ情報DTO
	 */
	public ProductCategoryDto createproductCategory(ProductCategoryDto productCategoryInfo) {
		
		ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
		productCategoryEntity.setCategoryId(productCategoryInfo.getCategoryId());
		productCategoryEntity.setCategoryName(productCategoryInfo.getCategoryName());
		productCategoryEntity.setCreatedAt(productCategoryInfo.getCreatedAt());
		productCategoryEntity.setUpdatedAt(productCategoryInfo.getUpdatedAt());
		productCategoryEntity.setDelFlag(productCategoryInfo.getDelFlag());

		//画面で登録したカテゴリ情報をEntityにマッピングする
		ProductCategoryEntity savedProductCategory = productCategoryRepository.save(productCategoryEntity);

		//取得された情報が画面DTOにセットする
		ProductCategoryDto productCategoryResult = new ProductCategoryDto();
		productCategoryResult.setCategoryId(savedProductCategory.getCategoryId());
		productCategoryResult.setCategoryName(savedProductCategory.getCategoryName());
		productCategoryResult.setCreatedAt(savedProductCategory.getCreatedAt());
		productCategoryResult.setUpdatedAt(savedProductCategory.getUpdatedAt());
		productCategoryResult.setDelFlag(savedProductCategory.getDelFlag());

		return productCategoryResult;
	}
}
