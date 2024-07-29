package com.example.shoppe.service.implement;


import com.example.shoppe.dto.request.ProductDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.mapper.ProductMapper;
import com.example.shoppe.model.Product;
import com.example.shoppe.repository.ProductRepository;
import com.example.shoppe.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    public ProductRepository productRepository;

    public final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public ApiResponse<List<ProductDTO>> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(productMapper::toDTO).collect(Collectors.toList());

        ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productDTOS);
        apiResponse.setMessage(!productDTOS.isEmpty() ?
                "Lấy danh sách sản phẩm thành công"
                : "Lấy danh sách sản phẩm thất bại");
        return apiResponse;
    }

    @Override
    public ApiResponse<ProductDTO> getById(UUID id) {
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
        if (productRepository.count() == 0) {
            apiResponse.setMessage("Danh sách sản phẩm rỗng");
            return null;
        }

        if (!productRepository.existsById(id)) {
            apiResponse.setMessage("Gọi sản phẩm thất bại");
            throw new NoSuchElementException();
        }

        Product product = productRepository.findById(id).get();
        ProductDTO productDTO = productMapper.toDTO(product);


        apiResponse.setResult(productDTO);
        apiResponse.setMessage(productDTO != null ? String.format("Lấy thành công sản phẩm %s", productDTO.getName())
                : "Lấy sản phẩm thất bại");
        return apiResponse;
    }

    @Override
    public ApiResponse<ProductDTO> save(ProductDTO productDTO) {
        if (productDTO != null) {
            Product product = productMapper.toEntity(productDTO);
            product = productRepository.save(product);
            ProductDTO result = productMapper.toDTO(product);

            ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Tạo sản phẩm thành công" :
                    "Tạo sản phẩm thất bại");
            return apiResponse;
        }

        return null;
    }

    @Override
    public ApiResponse<ProductDTO> update(ProductDTO productDTO, UUID id) {
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
        if (!productRepository.existsById(id)) {
            apiResponse.setMessage("Cập nhập sản phẩm thất bại");
            throw new NoSuchElementException();
        }

        if (productDTO != null) {
            Product product = productRepository.findById(id).get();
            productMapper.updateEntityFromDTO(productDTO, product);
            product = productRepository.save(product);
            ProductDTO result = productMapper.toDTO(product);


            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Cập nhập thành công thông tin sản phẩm" :
                    "Trong quá trình cập nhập thông tin sản phẩm gặp lỗi, kiểm tra lại thông tin");
        }

        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        if (!productRepository.existsById(id)) {
            apiResponse.setResult(false);
            apiResponse.setMessage("Xóa sản phẩm thất bại");
            return apiResponse;
        }

        productRepository.deleteById(id);
        apiResponse.setResult(true);
        apiResponse.setMessage("Xóa sản phẩm thành công");
        return apiResponse;
    }
}
