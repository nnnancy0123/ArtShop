package com.artshop.jin.admin.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *商品管理情報エンティティ
 *@author Nancy
 *@since 2025-03-27
 *@version 2.0
 */
@Entity
@Table(name = "product_management")
public class AdminProductManagementEntity {

	// 商品ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	// 商品名
	@JsonProperty("product_name")
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
	private boolean stockStatus;

	// 商品写真
	private String productPhoto;

	// 削除フラグ
	private boolean delFlag;

	// 作成時間
	@CreationTimestamp
	private LocalDateTime createdAt;

	// 更新時間
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	/**
	 * @return productId
	 */
	@JsonProperty("id")
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
	public boolean isStockStatus() {
		return stockStatus;
	}

	/**
	 * @param stockStatus セットする stockStatus
	 */
	public void setStockStatus(boolean stockStatus) {
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
	public boolean isDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag セットする delFlag
	 */
	public void setDelFlag(boolean delFlag) {
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
