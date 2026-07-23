package com.detalles.ecommerce.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.detalles.ecommerce.product_service.dto.ProductRequestDTO;
import com.detalles.ecommerce.product_service.dto.ProductResponseDTO;
import com.detalles.ecommerce.product_service.exception.ResourceNotFoundException;
import com.detalles.ecommerce.product_service.mapper.ProductMapper;
import com.detalles.ecommerce.product_service.model.Product;
import com.detalles.ecommerce.product_service.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final ProductMapper mapper;

	@Override
	public ProductResponseDTO createProduct(@RequestBody  ProductRequestDTO requestDTO) {

		Product product = mapper.toProduct(requestDTO);
		Product productSaved = repository.save(product);

		return mapper.toProductResponseDTO(productSaved);
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {

		List<Product> all = repository.findAll();
		return all.stream().map(mapper::toProductResponseDTO).toList();

	}

	@Override
	public ProductResponseDTO getProductById(String id) {

		Product product = findProductById(id);

		return mapper.toProductResponseDTO(product);
	}

	private Product findProductById(String id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		return product;
	}

	@Override
	public ProductResponseDTO updateProduct(String id, @RequestBody @Valid ProductRequestDTO  requestDTO) {

		Product product = findProductById(id);

		mapper.updateProductFromRequest(requestDTO, product);

		Product productSaved = repository.save(product);

		return mapper.toProductResponseDTO(productSaved);
	}

	@Override
	public void deleteProduct(String id) {

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Product", "id", id);

		}

		repository.deleteById(id);

	}

}
