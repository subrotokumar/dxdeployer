package dev.subrotokumar.accounts.service;

import dev.subrotokumar.accounts.dto.AccountDto;

public interface AccountService {

    public AccountDto getAccountInfo(int userId);

    public void deleteAccount(int userId);
}
