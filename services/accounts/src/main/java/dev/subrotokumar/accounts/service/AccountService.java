package dev.subrotokumar.accounts.service;

import dev.subrotokumar.accounts.dto.AccountDto;

/**
 * Service interface for managing user accounts.
 */
public interface AccountService {

    /**
     * Retrieves the account information for a given user ID.
     *
     * @param userId the ID of the user whose account information is to be
     * retrieved
     * @return AccountDto containing the account details
     */
    public AccountDto getAccountInfo(int userId);

    /**
     * Deletes the account for a given user ID.
     *
     * @param userId the ID of the user whose account is to be deleted
     */
    public void deleteAccount(int userId);
    
}
