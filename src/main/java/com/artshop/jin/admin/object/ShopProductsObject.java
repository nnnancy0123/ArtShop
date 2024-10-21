package com.artshop.jin.admin.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ShopProductsObject {

	// 商品ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	// 商品名
	@Column(name = "product_name")
	private String productName;

	// 商品紹介
	@Column(name = "product_description")
	private String productDescription;

	// 商品価格
	@Column(name = "price")
	private BigDecimal price;

	// 在庫数
	@Column(name = "stock_quantity")
	private int stockQuantity;

	// 在庫状態
	@Column(name = "stock_status")
	private int stockStatus;

	// カテゴリ
	@Column(name = "category_name")
	private String categoryName;

	// 商品写真
	@Column(name = "product_photo")
	private String productPhoto;

	// 作成時間
	@CreationTimestamp
	@Column(name = "created_at_time", updatable = false)
	private LocalDateTime createdAtTime;

	// 更新時間
	@UpdateTimestamp
	@Column(name = "updated_at_time")
	private LocalDateTime updatedAtTime;

	// 削除フラグ
	@Column(name = "del_flag")
	private int delFlag;

	public ShopProductsObject(Long productId, String productName, String productDescription, BigDecimal price,
			int stockQuantity, int stockStatus, String categoryName, String productPhoto,
			LocalDateTime createdAtTime, LocalDateTime updatedAtTime,int delFlag) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.stockStatus = stockStatus;
		this.categoryName = categoryName;
		this.productPhoto = productPhoto;
		this.createdAtTime = createdAtTime;
		this.updatedAtTime = updatedAtTime;
		this.delFlag = delFlag;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	public LocalDateTime getCreatedAtTime() {
		return createdAtTime;
	}

	public void setCreatedAtTime(LocalDateTime createdAtTime) {
		this.createdAtTime = createdAtTime;
	}

	public LocalDateTime getUpdatedAtTime() {
		return updatedAtTime;
	}

	public void setUpdatedAtTime(LocalDateTime updatedAtTime) {
		this.updatedAtTime = updatedAtTime;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
}
