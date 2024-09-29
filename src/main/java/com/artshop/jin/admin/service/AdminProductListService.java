package com.artshop.jin.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.jin.admin.entity.AdminProductListEntity;
import com.artshop.jin.admin.object.AdminProductListObject;
import com.artshop.jin.admin.repository.AdminProductListRepository;

@Service
public class AdminProductListService {

	@Autowired
	private AdminProductListRepository adminProductListRepository;

	//商品情報リストを取得する
	public List<AdminProductListObject> getProductInfoList() {

		List<AdminProductListEntity> adminProductList = adminProductListRepository.findProductByDelFlag(0);

		return adminProductList.stream()
				.map(entity -> new AdminProductListObject(
						entity.getProductId(), entity.getProductName(),
						entity.getProductDescription(), entity.getPrice(),
						entity.getStockQuantity(),entity.getStockStatus(), entity.getCategoryName(), entity.getProductPhoto(),
						entity.getCreatedAtTime(), entity.getUpdatedAtTime(), entity.getDelFlag()))
				.collect(Collectors.toList());
	}
}
