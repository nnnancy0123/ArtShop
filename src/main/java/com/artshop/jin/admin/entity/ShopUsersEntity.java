package com.artshop.jin.admin.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *ユーザー情報エンティティ
 * @author Nancy
 * @since 2024-10-06
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "shop_users")
public class ShopUsersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//ユーザD
	private Long usersId;
	//ユーザ名
	private String usersName;
	// ユーザメールアドレス
	private String usersMail;
	// ユーザ権限
	private int usersRoles;
	// ユーザステータス
	private short usersStatus;
	// 作成時間
	private Timestamp createdAtTime;
	// 更新時間
	private LocalDateTime updatedAtTime;
	// 郵便番号
	private String postCode;
	//支払いカード1
	private String payCard1;
	//支払いカード2
	private String payCard2;
	//支払いカード3
	private String payCard3;
	//住所１
	private String usersAddress1;
	//住所2
	private String usersAddress2;
	//住所3
	private String usersAddress3;
	// ポイント
	private int usersPoints;
	//削除フラグ
	private String delFlag;

}
