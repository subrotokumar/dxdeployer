package dev.subrotokumar.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.subrotokumar.accounts.entity.Account;
import java.util.Optional;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String username);
    List<Account> findByUsernameOrEmail(String username, String email);
}
