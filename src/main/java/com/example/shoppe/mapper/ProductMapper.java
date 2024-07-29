package com.example.shoppe.mapper;

import com.example.shoppe.dto.request.ProductDTO;
import com.example.shoppe.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

    Set<Product> toSetEntity(Set<ProductDTO> productDTOS);

    Set<ProductDTO> toSetDTO(Set<Product> products);

    void updateEntityFromDTO(ProductDTO productDTO, @MappingTarget Product product);
}
