package com.example.shoppe.dto.request;

import com.example.shoppe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDTO {
    private UUID account_id;
    private Long account_number;
    private String accountType;
    private Double balance;
    private Customer customer;
}
