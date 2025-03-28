package com.artshop.jin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artshop.jin.admin.entity.ApiTestUserEntity;

@Repository
public interface ApiTestUserRepository  extends JpaRepository<ApiTestUserEntity,Integer>{

}
