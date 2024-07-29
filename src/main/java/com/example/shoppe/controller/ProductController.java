package com.example.shoppe.controller;

import com.example.shoppe.dto.request.ProductDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAll() {
        ApiResponse<List<ProductDTO>> apiResponse = productService.getAll();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getById(@PathVariable("id") UUID id) {
        ApiResponse<ProductDTO> apiResponse = productService.getById(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> save(@RequestBody ProductDTO productDTO) {
        ApiResponse<ProductDTO> apiResponse = productService.save(productDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@RequestBody ProductDTO productDTO, @PathVariable("id") UUID id) {
        ApiResponse<ProductDTO> apiResponse = productService.update(productDTO, id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable("id") UUID id) {
        ApiResponse<Boolean> apiResponse = productService.delete(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
