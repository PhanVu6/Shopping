package com.example.shoppe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_number", nullable = false)
    private Long account_number;

    @Column(name = "account_type", length = 100)
    private String accountType;

    @Column(name = "balance")
    private Double balance;

    @OneToOne
    @JsonBackReference
    private Customer customer;
}
