package com.example.shoppe.service;

import com.example.shoppe.dto.request.CustomerDTO;
import com.example.shoppe.dto.response.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    ApiResponse<List<CustomerDTO>> getAll();

    ApiResponse<CustomerDTO> getById(UUID id);

    ApiResponse<CustomerDTO> save(CustomerDTO customerDTO);

    ApiResponse<CustomerDTO> update(CustomerDTO customerDTO, UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
