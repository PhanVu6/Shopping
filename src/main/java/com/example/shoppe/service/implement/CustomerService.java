package com.example.shoppe.service.implement;

import com.example.shoppe.dto.request.CustomerDTO;
import com.example.shoppe.dto.response.ApiResponse;
import com.example.shoppe.mapper.CustomerMapper;
import com.example.shoppe.model.Customer;
import com.example.shoppe.repository.CustomerRepository;
import com.example.shoppe.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Override
    public ApiResponse<List<CustomerDTO>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream().map(customerMapper::toDTO).collect(Collectors.toList());

        ApiResponse<List<CustomerDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(customerDTOS);
        apiResponse.setMessage(!customerDTOS.isEmpty() ?
                "Lấy danh sách khách hàng thành công"
                : "Lấy danh sách khách hàng thất bại");
        return apiResponse;

    }

    @Override
    public ApiResponse<CustomerDTO> getById(UUID id) {
        Customer customer = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(customerDTO);
        apiResponse.setMessage(customer != null ?
                String.format("Lấy thành công thông tin khách hàng %s", customer.getName())
                : "Lấy khách hàng thất bại");
        return apiResponse;
    }

    @Override
    public ApiResponse<CustomerDTO> save(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            Customer customer = customerMapper.toEntity(customerDTO);
            customer = customerRepository.save(customer);
            CustomerDTO result = customerMapper.toDTO(customer);

            ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Tạo khách hàng thành công" :
                    "Tạo khách hàng thất bại");
            return apiResponse;
        }

        return null;
    }

    @Override
    public ApiResponse<CustomerDTO> update(CustomerDTO customerDTO, UUID id) {
        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        if (!customerRepository.existsById(id)) {
            apiResponse.setMessage("Cập nhập khách hàng thất bại");
            throw new NoSuchElementException();
        }

        if (customerDTO != null) {
            Customer customer = customerRepository.findById(id).get();
            customerMapper.updateEntityFromDTO(customerDTO, customer);
            customer = customerRepository.save(customer);
            CustomerDTO result = customerMapper.toDTO(customer);


            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ?
                    "Cập nhập thành công thông tin khách hàng" :
                    "Trong quá trình cập nhập thông tin khách hàng gặp lỗi, kiểm tra lại thông tin");
        }

        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        if (!customerRepository.existsById(id)) {
            apiResponse.setResult(false);
            apiResponse.setMessage("Xóa khách hàng thất bại");
            return apiResponse;
        }

        customerRepository.deleteById(id);
        apiResponse.setResult(true);
        apiResponse.setMessage("Xóa khách hàng thành công");
        return apiResponse;
    }
}
