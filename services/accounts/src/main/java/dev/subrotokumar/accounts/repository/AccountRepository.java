package dev.subrotokumar.accounts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.subrotokumar.accounts.entity.Account;

/**
 * Repository interface for accessing account data from the database. Extends
 * JpaRepository to leverage Spring Data JPA functionalities for standard CRUD
 * operations.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Finds an account by its email.
     *
     * @param email the email of the account to find
     * @return an Optional containing the account if found, or an empty Optional
     * if not found
     */
    Optional<Account> findByEmail(String email);

    /**
     * Finds an account by its username.
     *
     * @param username the username of the account to find
     * @return an Optional containing the account if found, or an empty Optional
     * if not found
     */
    Optional<Account> findByUsername(String username);

    /**
     * Finds accounts by either username or email. This method can be useful for
     * authentication processes where either attribute might be used for login.
     *
     * @param username the username to search for
     * @param email the email to search for
     * @return a list of accounts matching the username or email
     */
    List<Account> findByUsernameOrEmail(String username, String email);
}
