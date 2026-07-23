package com.detalles.ecommerce.product_service.service;

import java.util.List;

import com.detalles.ecommerce.product_service.dto.ProductRequestDTO;
import com.detalles.ecommerce.product_service.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
	List<ProductResponseDTO> getAllProducts();
	ProductResponseDTO getProductById(String id);
	ProductResponseDTO updateProduct(String id, ProductRequestDTO requestDTO);
	void deleteProduct(String id);
}
