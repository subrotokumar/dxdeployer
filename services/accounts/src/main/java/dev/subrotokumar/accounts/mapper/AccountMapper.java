package dev.subrotokumar.accounts.mapper;

import dev.subrotokumar.accounts.dto.AccountDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import dev.subrotokumar.accounts.entity.Account;
import dev.subrotokumar.accounts.entity.Role;

public class AccountMapper {

    public static Account registerAccountDtoToEntity(RegisterAccountRequestDto dto) {
        return Account.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.USER)
                .build();
    }

    public static AccountDto entityToDto(Account entity) {
        return AccountDto.builder()
                .email(entity.getEmail())
                .username(entity.getUsername())
                .role(entity.getRole())
                .emailVerified(entity.isEmailVerified())
                .createdAt(entity.getCreatedAt())
                .lastModifiedAt(entity.getLastModifiedAt())
                .build();
    }
}
