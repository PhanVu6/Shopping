package com.example.shoppe.controller;

import com.example.shoppe.dto.request.AccountDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.service.implement.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDTO>>> getAll() {
        ApiResponse<List<AccountDTO>> apiResponse = accountService.getAll();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getById(@PathVariable("id") UUID id) {
        ApiResponse<AccountDTO> apiResponse = accountService.getById(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDTO>> save(@RequestBody AccountDTO accountDTO) {
        ApiResponse<AccountDTO> apiResponse = accountService.save(accountDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> update(@RequestBody AccountDTO accountDTO, @PathVariable("id") UUID id) {
        ApiResponse<AccountDTO> apiResponse = accountService.update(accountDTO, id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable("id") UUID id) {
        ApiResponse<Boolean> apiResponse = accountService.delete(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
