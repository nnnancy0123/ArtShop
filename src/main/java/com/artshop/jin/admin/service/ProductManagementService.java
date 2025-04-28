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
 * @version 2.1
 */
@Service
public class ProductManagementService {

    @Autowired
    private ProductManagementRepository productManagementRepository;

    @Value("${app.upload.path}")
    private String uploadDir;

    /**
     * 商品登録
     */
    public ProductInfoDto createProductInfo(ProductInfoDto adminProductInfo, MultipartFile productImg) throws IOException {

        ProductInfoEntity adminProductManagementEntity = new ProductInfoEntity();
        adminProductManagementEntity.setProductId(adminProductInfo.getProductId());
        adminProductManagementEntity.setProductName(adminProductInfo.getProductName());
        adminProductManagementEntity.setProductDescription(adminProductInfo.getProductDescription());
        adminProductManagementEntity.setCategoryName(adminProductInfo.getCategoryName());
        adminProductManagementEntity.setPrice(adminProductInfo.getPrice());
        adminProductManagementEntity.setStockQuantity(adminProductInfo.getStockQuantity());
        adminProductManagementEntity.setStockStatus(adminProductInfo.getStockStatus());
        adminProductManagementEntity.setDelFlag(adminProductInfo.getDelFlag());

        if (productImg != null && !productImg.isEmpty()) {
            String savedPath = saveUploadedFile(productImg);
            adminProductManagementEntity.setProductImgUrl(savedPath);
        }

        ProductInfoEntity savedProductInfo = productManagementRepository.save(adminProductManagementEntity);

        ProductInfoDto productInfoResult = new ProductInfoDto();
        productInfoResult.setProductId(savedProductInfo.getProductId());
        productInfoResult.setProductName(savedProductInfo.getProductName());
        productInfoResult.setProductDescription(savedProductInfo.getProductDescription());
        productInfoResult.setCategoryName(savedProductInfo.getCategoryName());
        productInfoResult.setPrice(savedProductInfo.getPrice());
        productInfoResult.setStockQuantity(savedProductInfo.getStockQuantity());
        productInfoResult.setStockStatus(savedProductInfo.getStockStatus());
        productInfoResult.setDelFlag(savedProductInfo.getDelFlag());
        productInfoResult.setProductImgUrl(savedProductInfo.getProductImgUrl());
        productInfoResult.setCreatedAt(savedProductInfo.getCreatedAt());
        productInfoResult.setUpdatedAt(savedProductInfo.getUpdatedAt());

        return productInfoResult;
    }

    /**
     * 商品一覧取得
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
            productManagementDto.setProductImgUrl(adminProductManagementEntity.getProductImgUrl());
            productManagementDto.setCreatedAt(adminProductManagementEntity.getCreatedAt());
            productManagementDto.setUpdatedAt(adminProductManagementEntity.getUpdatedAt());
            adminProductDtoList.add(productManagementDto);
        }
        return adminProductDtoList;
    }

    /**
     * 商品削除
     */
    public void deleteProductById(Long productId) {
        if (!productManagementRepository.existsById(productId)) {
            throw new RuntimeException("該当商品が見つかりません。ID: " + productId);
        }
        productManagementRepository.deleteById(productId);
    }

    /**
     * 商品更新
     */
    public ProductInfoDto updateProductInfo(ProductInfoDto productInfo, MultipartFile productImg) {
        ProductInfoEntity existingProduct = productManagementRepository.findById(productInfo.getProductId())
                .orElseThrow(() -> new RuntimeException("対象商品が見つかりません。ID: " + productInfo.getProductId()));

        if (productImg != null && !productImg.isEmpty()) {
            if (existingProduct.getProductImgUrl() != null) {
                try {
                    Path oldFilePath = Paths.get(uploadDir,
                            Paths.get(existingProduct.getProductImgUrl()).getFileName().toString());
                    Files.deleteIfExists(oldFilePath);
                } catch (IOException e) {
                    System.err.println("旧画像ファイルの削除に失敗しました：" + e.getMessage());
                }
            }
            String savedPath = saveUploadedFile(productImg);
            productInfo.setProductImgUrl(savedPath);
        }

        existingProduct.setProductId(productInfo.getProductId());
        existingProduct.setProductName(productInfo.getProductName());
        existingProduct.setProductDescription(productInfo.getProductDescription());
        existingProduct.setCategoryName(productInfo.getCategoryName());
        existingProduct.setPrice(productInfo.getPrice());
        existingProduct.setStockQuantity(productInfo.getStockQuantity());
        existingProduct.setStockStatus(productInfo.getStockStatus());
        existingProduct.setDelFlag(productInfo.getDelFlag());
        existingProduct.setProductImgUrl(productInfo.getProductImgUrl());

        ProductInfoEntity updatedProduct = productManagementRepository.save(existingProduct);

        ProductInfoDto adminProductInfoById = new ProductInfoDto();
        adminProductInfoById.setProductId(updatedProduct.getProductId());
        adminProductInfoById.setProductName(updatedProduct.getProductName());
        adminProductInfoById.setProductDescription(updatedProduct.getProductDescription());
        adminProductInfoById.setCategoryName(updatedProduct.getCategoryName());
        adminProductInfoById.setPrice(updatedProduct.getPrice());
        adminProductInfoById.setStockQuantity(updatedProduct.getStockQuantity());
        adminProductInfoById.setStockStatus(updatedProduct.getStockStatus());
        adminProductInfoById.setDelFlag(updatedProduct.getDelFlag());
        adminProductInfoById.setProductImgUrl(updatedProduct.getProductImgUrl());
        adminProductInfoById.setCreatedAt(updatedProduct.getCreatedAt());
        adminProductInfoById.setUpdatedAt(updatedProduct.getUpdatedAt());

        return adminProductInfoById;
    }

    /**
     * ファイル保存
     */
    private String saveUploadedFile(MultipartFile file) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String uniqueFileName = System.currentTimeMillis() + "_" + UUID.randomUUID() + extension;
            Path savePath = Paths.get(uploadDir, uniqueFileName);
            Files.copy(file.getInputStream(), savePath);
            return "/uploads/files/" + uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("ファイル保存失敗: " + e.getMessage());
        }
    }
}
