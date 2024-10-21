package com.artshop.jin.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.jin.admin.entity.ShopProductsEntity;
import com.artshop.jin.admin.object.ShopProductsObject;
import com.artshop.jin.admin.repository.ShopProductsRepository;

@Service
public class ShopProductsService {

	@Autowired
	private ShopProductsRepository adminProductListRepository;

	//商品情報リストを取得する
	public List<ShopProductsObject> getProductInfoList() {

		List<ShopProductsEntity> adminProductList = adminProductListRepository.findProductByDelFlag(0);

		return adminProductList.stream()
				.map(entity -> new ShopProductsObject(
						entity.getProductId(), entity.getProductName(),
						entity.getProductDescription(), entity.getPrice(),
						entity.getStockQuantity(),entity.getStockStatus(), entity.getCategoryName(), entity.getProductPhoto(),
						entity.getCreatedAtTime(), entity.getUpdatedAtTime(), entity.getDelFlag()))
				.collect(Collectors.toList());
	}
}
