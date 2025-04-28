package com.artshop.jin.admin.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artshop.jin.admin.dto.ProductInfoDto;
import com.artshop.jin.admin.service.ProductManagementService;

/**
 * 商品情報管理コントローラ
 * @author Nancy
 * @since 2025-03-28
 * @version 2.2
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductManagementController {

    @Autowired
    private ProductManagementService adminProductManagementService;

    /**
     * 商品登録
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProductInfo(
            @RequestParam("productName") String productName,
            @RequestParam("productDescription") String productDescription,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stockQuantity") int stockQuantity,
            @RequestParam("stockStatus") String stockStatus,
            @RequestParam("delFlag") String delFlag,
            @RequestParam(value = "productImg", required = false) MultipartFile productImg) {

        ProductInfoDto adminProductManagementDto = new ProductInfoDto();
        adminProductManagementDto.setProductName(productName);
        adminProductManagementDto.setProductDescription(productDescription);
        adminProductManagementDto.setCategoryName(categoryName);
        adminProductManagementDto.setPrice(price);
        adminProductManagementDto.setStockQuantity(stockQuantity);
        adminProductManagementDto.setStockStatus(stockStatus);
        adminProductManagementDto.setDelFlag(delFlag);

        try {
            ProductInfoDto result = adminProductManagementService.createProductInfo(adminProductManagementDto, productImg);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("アップロード失敗: " + e.getMessage());
        }
    }

    /**
     * 全ての商品情報を検索する
     */
    @GetMapping
    public ResponseEntity<List<ProductInfoDto>> getProductInfo() {
        List<ProductInfoDto> allProductInfo = adminProductManagementService.findAllProductInfo();
        return ResponseEntity.ok(allProductInfo);
    }

    /**
     * 商品IDによって商品情報を削除する
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductInfo(@PathVariable Long id) {
        adminProductManagementService.deleteProductById(id);
        return ResponseEntity.ok("削除成功しました");
    }

    /**
     * 編集した商品情報を更新
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductInfoDto> updateProductInfo(
            @PathVariable Long id,
            @ModelAttribute ProductInfoDto productInfo,
            @RequestParam(value = "productImg", required = false) MultipartFile productImg) {

        productInfo.setProductId(id);

        ProductInfoDto updatedProductInfo = adminProductManagementService.updateProductInfo(productInfo, productImg);
        return ResponseEntity.ok(updatedProductInfo);
    }
}
