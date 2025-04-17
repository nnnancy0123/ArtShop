package com.artshop.jin.admin.dto;

import java.time.LocalDateTime;

/**
 * 商品カテゴリDTO
 * フロントエンドとのデータ受け渡し用クラス
 * @author Nancy
 * @since 2025-04-17
 * @version 2.0
 */
public class ProductCategoryDto {

	private Long categoryId;

	//	カテゴリ名
	private String categoryName;

	// 作成時間
	private LocalDateTime createdAt;

	// 更新時間
	private LocalDateTime updatedAt;

	// 削除フラグ
	private String delFlag;

	public ProductCategoryDto() {

	}

	public ProductCategoryDto(Long categoryId, String categoryName, LocalDateTime createdAt, LocalDateTime updatedAt,
			String delFlag) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.delFlag = delFlag;
	}

	/**
	 * @return categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId セットする categoryId
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName セットする categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt セットする createdAt
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt セットする updatedAt
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag セットする delFlag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
