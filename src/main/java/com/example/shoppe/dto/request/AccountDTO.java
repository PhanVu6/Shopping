package com.example.shoppe.dto.request;

import com.example.shoppe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String accountType;
    private Double balance;
    private Customer customer;
}
