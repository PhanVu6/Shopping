package com.example.shoppe.mapper;

import com.example.shoppe.dto.request.CustomerDTO;
import com.example.shoppe.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDTO(Customer customer);

    Set<Customer> toSetEntity(Set<CustomerDTO> customerDTOS);

    Set<CustomerDTO> toSetDTO(Set<Customer> customers);

    void updateEntityFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);
}
