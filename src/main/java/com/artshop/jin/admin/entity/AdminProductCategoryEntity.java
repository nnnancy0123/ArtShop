package com.artshop.jin.admin.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_category")
public class AdminProductCategoryEntity {

	//カテゴリID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	
	//カテゴリ名
	@Column(name = "category_name")
	private String categoryName;
	
	// 作成時間
	@CreationTimestamp
	@Column(name = "created_at_time", updatable = false)
	private Timestamp createdAtTime;
	
	// 更新時間
	@UpdateTimestamp
	@Column(name = "updated_at_time")
	private LocalDateTime updatedAtTime;

	// 削除フラグ
	@Column(name = "del_flag")
	private int delFlag;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getCreatedAtTime() {
		return createdAtTime;
	}

	public void setCreatedAtTime(Timestamp createdAtTime) {
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
