package com.example.shoppe.service;

import com.example.shoppe.dto.request.AccountDTO;
import com.example.shoppe.dto.response.ApiResponse;

import java.util.UUID;

public interface IAccountService {

    ApiResponse<AccountDTO> getById(UUID id);

    ApiResponse<AccountDTO> save(AccountDTO customerDTO);

    ApiResponse<AccountDTO> update(AccountDTO customerDTO, UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
