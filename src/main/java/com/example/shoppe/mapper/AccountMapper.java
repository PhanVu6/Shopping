package com.example.shoppe.mapper;

import com.example.shoppe.dto.request.AccountDTO;
import com.example.shoppe.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountDTO accountDTO);

    AccountDTO toDTO(Account account);

    Set<Account> toSetEntity(Set<AccountDTO> accountDTOS);

    Set<AccountDTO> toSetDTO(Set<Account> accounts);

    void updateEntityFromDTO(AccountDTO accountDTO, @MappingTarget Account account);
}
