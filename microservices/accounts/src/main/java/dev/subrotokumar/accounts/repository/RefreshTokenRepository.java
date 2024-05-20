package dev.subrotokumar.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import dev.subrotokumar.accounts.entity.RefreshToken;
import jakarta.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{
    List<RefreshToken> findByTokenId(UUID tokenId);

    @Transactional
    void deleteByExpiryBefore(Date expiry);
}
