package com.example.shoppe.controller;

import com.example.shoppe.dto.request.CustomerDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.service.implement.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAll() {
        ApiResponse<List<CustomerDTO>> apiResponse = customerService.getAll();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getById(@PathVariable("id") UUID id) {
        ApiResponse<CustomerDTO> apiResponse = customerService.getById(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> save(CustomerDTO customerDTO) {
        ApiResponse<CustomerDTO> apiResponse = customerService.save(customerDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> update(CustomerDTO customerDTO, UUID id) {
        ApiResponse<CustomerDTO> apiResponse = customerService.update(customerDTO, id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(UUID id) {
        ApiResponse<Boolean> apiResponse = customerService.delete(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
