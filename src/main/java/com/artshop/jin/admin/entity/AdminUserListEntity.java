package com.artshop.jin.admin.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AdminUserListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動増分主キー
    //ユーザD
    private Long usersId;
    //ユーザ名
    private String usersName;
    private String usersMail;     // 邮件地址
    private int usersRoles;       // 用户权限
    private short usersStatus;     // 用户状态
    private Timestamp createdAtTime; // 创建时间
    private Timestamp updatedAtTime; // 更新时间
    private String postCode;      // 邮政编码
    private String payCard1;      // 支付卡1
    private String payCard2;      // 支付卡2
    private String payCard3;      // 支付卡3
    private String usersAddress1;  // 地址1
    private String usersAddress2;  // 地址2
    private String usersAddress3;  // 地址3
    private int usersPoints;       // 积分

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersMail() {
        return usersMail;
    }

    public void setUsersMail(String usersMail) {
        this.usersMail = usersMail;
    }

    public int getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(int usersRoles) {
        this.usersRoles = usersRoles;
    }

    public short getUsersStatus() {
        return usersStatus;
    }

    public void setUsersStatus(short usersStatus) {
        this.usersStatus = usersStatus;
    }

    public Timestamp getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(Timestamp createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    public Timestamp getUpdatedAtTime() {
        return updatedAtTime;
    }

    public void setUpdatedAtTime(Timestamp updatedAtTime) {
        this.updatedAtTime = updatedAtTime;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPayCard1() {
        return payCard1;
    }

    public void setPayCard1(String payCard1) {
        this.payCard1 = payCard1;
    }

    public String getPayCard2() {
        return payCard2;
    }

    public void setPayCard2(String payCard2) {
        this.payCard2 = payCard2;
    }

    public String getPayCard3() {
        return payCard3;
    }

    public void setPayCard3(String payCard3) {
        this.payCard3 = payCard3;
    }

    public String getUsersAddress1() {
        return usersAddress1;
    }

    public void setUsersAddress1(String usersAddress1) {
        this.usersAddress1 = usersAddress1;
    }

    public String getUsersAddress2() {
        return usersAddress2;
    }

    public void setUsersAddress2(String usersAddress2) {
        this.usersAddress2 = usersAddress2;
    }

    public String getUsersAddress3() {
        return usersAddress3;
    }

    public void setUsersAddress3(String usersAddress3) {
        this.usersAddress3 = usersAddress3;
    }

    public int getUsersPoints() {
        return usersPoints;
    }

    public void setUsersPoints(int usersPoints) {
        this.usersPoints = usersPoints;
    }
}
