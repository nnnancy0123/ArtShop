package com.artshop.jin.admin.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品情報DTO
 * フロントエンドとのデータ受け渡し用クラス
 * @author Nancy
 * @since 2025-03-28
 * @version 2.0
 */
public class ProductInfoDto {
	// 商品ID
	private Long productId;
	// 商品名
	private String productName;
	// 商品紹介
	private String productDescription;
	 // カテゴリ
	private String categoryName;
	 // 商品価格
	private BigDecimal price;
	 // 在庫数
	private int stockQuantity;
	 // 販売フラグ
	private String stockStatus;
	 // 商品写真
	private String productPhoto;
	 // 削除フラグ
	private String delFlag;
	 // 作成時間(自動設定、フロント送信不要)
	private LocalDateTime createdAt;
	 // 更新時間(自動更新、read-only)
	private LocalDateTime updatedAt;

	public ProductInfoDto() {
	}

	public ProductInfoDto(Long productId, String productName, String productDescription, String categoryName,
			BigDecimal price, int stockQuantity, String stockStatus, String  productPhoto) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.categoryName = categoryName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.stockStatus = stockStatus;
		this.productPhoto = productPhoto;
	}

	/**
	 * @return productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId セットする productId
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName セットする productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription セットする productDescription
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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
	 * @return price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return stockQuantity
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity セットする stockQuantity
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return stockStatus
	 */
	public String getStockStatus() {
		return stockStatus;
	}

	/**
	 * @param stockStatus セットする stockStatus
	 */
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	/**
	 * @return productPhoto
	 */
	public String getProductPhoto() {
		return productPhoto;
	}

	/**
	 * @param productPhoto セットする productPhoto
	 */
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
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

}
