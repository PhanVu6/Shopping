package com.example.shoppe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", nullable = false)
    private UUID product_id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "products")
    private Set<Customer> customers;
}
