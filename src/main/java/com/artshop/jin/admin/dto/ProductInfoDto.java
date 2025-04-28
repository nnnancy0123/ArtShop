package com.artshop.jin.admin.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品情報DTO
 * フロントエンドとのデータ受け渡し用クラス
 * @author Nancy
 * @since 2025-03-28
 * @version 2.1
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
    // 商品画像パス（uploads/files/xxx.jpg）
    private String productImgUrl;
    // 削除フラグ
    private String delFlag;
    // 作成時間
    private LocalDateTime createdAt;
    // 更新時間
    private LocalDateTime updatedAt;

    public ProductInfoDto() {
    }

    public ProductInfoDto(Long productId, String productName, String productDescription, String categoryName,
            BigDecimal price, int stockQuantity, String stockStatus, String productImgUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryName = categoryName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.stockStatus = stockStatus;
        this.productImgUrl = productImgUrl;
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

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getStockStatus() {
        return stockStatus;
    }
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }
    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
