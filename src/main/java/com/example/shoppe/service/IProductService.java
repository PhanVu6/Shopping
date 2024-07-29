package com.example.shoppe.service;

import com.example.shoppe.dto.request.ProductDTO;
import com.example.shoppe.dto.response.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ApiResponse<List<ProductDTO>> getAll();

    ApiResponse<ProductDTO> getById(UUID id);

    ApiResponse<ProductDTO> save(ProductDTO productDTO);

    ApiResponse<ProductDTO> update(ProductDTO productDTO, UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
