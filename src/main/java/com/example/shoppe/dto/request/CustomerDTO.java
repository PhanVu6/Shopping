package com.example.shoppe.dto.request;

import com.example.shoppe.model.Account;
import com.example.shoppe.model.Product;
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
public class CustomerDTO {
    private UUID customer_id;

    private String username;

    private String password;

    private String name;

    private String address;

    private String email;

    private String contactNo;

    private Set<Product> products;

    private Account accounts;
}
