package com.example.shoppe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"account_number"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false)
    private UUID account_id;

    @Column(name = "account_number", nullable = false)
    private Long account_number;

    @Column(name = "account_type", length = 100)
    private String accountType;

    @Column(name = "balance")
    private Double balance;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
