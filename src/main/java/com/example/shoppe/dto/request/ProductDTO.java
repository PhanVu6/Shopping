package com.example.shoppe.dto.request;

import com.example.shoppe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID product_id;
    
    private String name;

    private Long price;

    private String description;

    private Set<Customer> customers;
}
