package com.artshop.jin.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artshop.jin.admin.dto.ProductInfoDto;
import com.artshop.jin.admin.entity.ProductInfoEntity;
import com.artshop.jin.admin.repository.ProductManagementRepository;

/**
 * 商品情報管理サービス
 * @author Nancy
 * @since 2025-03-28
 * @version 2.0
 */
@Service
public class ProductManagementService {
	@Autowired
	private ProductManagementRepository productManagementRepository;
	@Value("${app.upload.path}")
	private String uploadDir;

	/**
	 * 画面から登録された情報をEntityに変換して保存し、保存結果もDTOで返す
	 * @param adminProductInfo
	 * @return　商品情報結果
	 */
	public ProductInfoDto createProductInfo(ProductInfoDto adminProductInfo, MultipartFile productPhoto)
			throws IOException {

		ProductInfoEntity adminProductManagementEntity = new ProductInfoEntity();
		adminProductManagementEntity.setProductId(adminProductInfo.getProductId());
		adminProductManagementEntity.setProductName(adminProductInfo.getProductName());
		adminProductManagementEntity.setProductDescription(adminProductInfo.getProductDescription());
		adminProductManagementEntity.setCategoryName(adminProductInfo.getCategoryName());
		adminProductManagementEntity.setPrice(adminProductInfo.getPrice());
		adminProductManagementEntity.setStockQuantity(adminProductInfo.getStockQuantity());
		adminProductManagementEntity.setStockStatus(adminProductInfo.getStockStatus());
		adminProductManagementEntity.setDelFlag(adminProductInfo.getDelFlag());

		File uploadDirFile = new File(uploadDir);

		//ディレクトリが存在しない場合、新しく作成する
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}

		// ファイルを存在することを確認、ある場合、アップロード
		if (productPhoto != null && !productPhoto.isEmpty()) {

			// アップロードされた元のファイル名を取得
			String fileName = productPhoto.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."));
			// ファイル名をかぶられないようにUUIDを使う
			String uniqueFileName = System.currentTimeMillis() + "_" + UUID.randomUUID() + extension;
			// フルパスを作成（uploads/フォルダに保存）
			Path savePath = Paths.get(uploadDir, uniqueFileName);
			// ファイルをローカルに保存
			Files.copy(productPhoto.getInputStream(), savePath);
			// ファイルをDBに保存する
			adminProductManagementEntity.setProductPhoto(uniqueFileName);
		}

		//画面の商品情報をEntityにマッピングする
		ProductInfoEntity savedProductInfo = productManagementRepository
				.save(adminProductManagementEntity);
		//取得された情報が画面DTOにセットする
		ProductInfoDto productInfoResult = new ProductInfoDto();
		productInfoResult.setProductId(savedProductInfo.getProductId());
		productInfoResult.setProductName(savedProductInfo.getProductName());
		productInfoResult.setProductDescription(savedProductInfo.getProductDescription());
		productInfoResult.setCategoryName(savedProductInfo.getCategoryName());
		productInfoResult.setPrice(savedProductInfo.getPrice());
		productInfoResult.setStockQuantity(savedProductInfo.getStockQuantity());
		productInfoResult.setStockStatus(savedProductInfo.getStockStatus());
		productInfoResult.setDelFlag(savedProductInfo.getDelFlag());
		productInfoResult.setProductPhoto(savedProductInfo.getProductPhoto());
		productInfoResult.setCreatedAt(savedProductInfo.getCreatedAt());
		productInfoResult.setUpdatedAt(savedProductInfo.getUpdatedAt());

		return productInfoResult;
	}

	/**
	 * 全ての商品が取得して、画面の商品一覧に映す
	 * @return　商品一覧リスト
	 */
	public List<ProductInfoDto> findAllProductInfo() {
		List<ProductInfoEntity> allProductInfo = productManagementRepository.findAll();

		List<ProductInfoDto> adminProductDtoList = new ArrayList<>();

		for (ProductInfoEntity adminProductManagementEntity : allProductInfo) {
			ProductInfoDto productManagementDto = new ProductInfoDto();
			productManagementDto.setProductId(adminProductManagementEntity.getProductId());
			productManagementDto.setProductName(adminProductManagementEntity.getProductName());
			productManagementDto.setProductDescription(adminProductManagementEntity.getProductDescription());
			productManagementDto.setCategoryName(adminProductManagementEntity.getCategoryName());
			productManagementDto.setPrice(adminProductManagementEntity.getPrice());
			productManagementDto.setStockQuantity(adminProductManagementEntity.getStockQuantity());
			productManagementDto.setStockStatus(adminProductManagementEntity.getStockStatus());
			productManagementDto.setDelFlag(adminProductManagementEntity.getDelFlag());
			productManagementDto.setProductPhoto(adminProductManagementEntity.getProductPhoto());
			productManagementDto.setCreatedAt(adminProductManagementEntity.getCreatedAt());
			productManagementDto.setUpdatedAt(adminProductManagementEntity.getUpdatedAt());
			adminProductDtoList.add(productManagementDto);
		}
		// 商品一覧リストに返却する
		return adminProductDtoList;
	}

	/**
	 * 商品IDによって該当商品を削除する
	 * @param productId
	 */
	public void deleteProductById(Long productId) {
		//商品IDの存在を確認する
		if (!productManagementRepository.existsById(productId)) {
			//商品ID存在しない場合，例外をスローする
			throw new RuntimeException("該当商品が見つかりません。ID: " + productId);
		}
		//存在する場合、該当商品を削除する
		productManagementRepository.deleteById(productId);
	}

	/**
	 * 選択した商品情報を編集する
	 * @param productInfo
	 * @return 
	 */
	public ProductInfoDto updateProductInfo(ProductInfoDto productInfo) {

		//商品IDで既存のデータを選択する
		ProductInfoEntity existingProduct = productManagementRepository
				.findById(productInfo.getProductId())
				.orElseThrow(() -> new RuntimeException("対象商品が見つかりません。ID: " + productInfo.getProductId()));

		//該当商品の情報をセットする
		existingProduct.setProductId(productInfo.getProductId());
		existingProduct.setProductName(productInfo.getProductName());
		existingProduct.setProductDescription(productInfo.getProductDescription());
		existingProduct.setCategoryName(productInfo.getCategoryName());
		existingProduct.setPrice(productInfo.getPrice());
		existingProduct.setStockQuantity(productInfo.getStockQuantity());
		existingProduct.setStockStatus(productInfo.getStockStatus());
		existingProduct.setDelFlag(productInfo.getDelFlag());
		existingProduct.setProductPhoto(productInfo.getProductPhoto());

		//商品情報を更新する
		ProductInfoEntity updatedProduct = productManagementRepository
				.save(existingProduct);

		//商品情報DTOに変換して返す
		ProductInfoDto adminProductInfoById = new ProductInfoDto();
		adminProductInfoById.setProductId(updatedProduct.getProductId());
		adminProductInfoById.setProductName(updatedProduct.getProductName());
		adminProductInfoById.setProductDescription(updatedProduct.getProductDescription());
		adminProductInfoById.setCategoryName(updatedProduct.getCategoryName());
		adminProductInfoById.setPrice(updatedProduct.getPrice());
		adminProductInfoById.setStockQuantity(updatedProduct.getStockQuantity());
		adminProductInfoById.setStockStatus(updatedProduct.getStockStatus());
		adminProductInfoById.setDelFlag(updatedProduct.getDelFlag());
		adminProductInfoById.setProductPhoto(updatedProduct.getProductPhoto());
		adminProductInfoById.setCreatedAt(updatedProduct.getCreatedAt());
		adminProductInfoById.setUpdatedAt(updatedProduct.getUpdatedAt());

		return adminProductInfoById;
	}
}
