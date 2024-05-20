package dev.subrotokumar.accounts.service.impl;

import org.springframework.stereotype.Service;

import dev.subrotokumar.accounts.dto.AccountDto;
import dev.subrotokumar.accounts.exception.AccountNotFoundException;
import dev.subrotokumar.accounts.mapper.AccountMapper;
import dev.subrotokumar.accounts.repository.AccountRepository;
import dev.subrotokumar.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;

    @Override
    public AccountDto getAccountInfo(int userId) {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var user = accountRepository.findById(userId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return AccountMapper.entityToDto(user);
    }

    @Override
    public void deleteAccount(int userId){
        accountRepository.deleteById(userId);
    }

}
