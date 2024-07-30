package com.example.shoppe.service.implement;

import com.example.shoppe.dto.request.AccountDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.mapper.AccountMapper;
import com.example.shoppe.model.Account;
import com.example.shoppe.repository.AccountRepository;
import com.example.shoppe.service.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Override
    public ApiResponse<List<AccountDTO>> getAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream().map(accountMapper::toDTO).collect(Collectors.toList());

        ApiResponse<List<AccountDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(accountDTOS);
        apiResponse.setMessage(!accountDTOS.isEmpty() ?
                "Lấy danh sách khách hàng thành công"
                : "Lấy danh sách khách hàng thất bại");
        return apiResponse;

    }

    @Override
    public ApiResponse<AccountDTO> getById(UUID id) {
        ApiResponse<AccountDTO> apiResponse = new ApiResponse<>();
        if (accountRepository.count() == 0) {
            apiResponse.setMessage("Danh sách tài khoản rỗng");
            return apiResponse;
        }

        if (!accountRepository.existsById(id)) {
            apiResponse.setMessage("Gọi tài khoản thất bại");
            return apiResponse;
        }
        Account account = accountRepository.findById(id).get();
        AccountDTO accountDTO = accountMapper.toDTO(account);


        apiResponse.setResult(accountDTO);
        apiResponse.setMessage(account != null ?
                String.format("Lấy thành công thông tin tài khoản %s", account.getAccountType())
                : "Lấy tài khoản thất bại");
        return apiResponse;
    }

    @Override
    public ApiResponse<AccountDTO> save(AccountDTO accountDTO) {
        if (accountDTO != null) {
            Account account = accountMapper.toEntity(accountDTO);
            account = accountRepository.save(account);
            AccountDTO result = accountMapper.toDTO(account);

            ApiResponse<AccountDTO> apiResponse = new ApiResponse<>();
            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Tạo tài khoản thành công" :
                    "Tạo tài khoản thất bại");
            return apiResponse;
        }

        return null;
    }

    @Override
    public ApiResponse<AccountDTO> update(AccountDTO accountDTO, UUID id) {
        ApiResponse<AccountDTO> apiResponse = new ApiResponse<>();
        if (!accountRepository.existsById(id)) {
            apiResponse.setMessage("Cập nhập tài khoản thất bại");
            return apiResponse;
        }

        if (accountDTO != null) {
            Account account = accountRepository.findById(id).get();
            accountMapper.updateEntityFromDTO(accountDTO, account);
            account = accountRepository.save(account);
            AccountDTO result = accountMapper.toDTO(account);


            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Cập nhập thành công thông tin tài khoản" :
                    "Trong quá trình cập nhập thông tin tài khoản gặp lỗi, kiểm tra lại thông tin");
        }

        return apiResponse;
    }

    @Override
    @Transactional
    public ApiResponse<Boolean> delete(UUID id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        if (!accountRepository.existsById(id)) {
            apiResponse.setResult(false);
            apiResponse.setMessage("Xóa tài khoản thất bại");
            return apiResponse;
        }

        accountRepository.deleteById(id);

        apiResponse.setResult(true);
        apiResponse.setMessage("Xóa tài khoản thành công");

        return apiResponse;
    }
}
