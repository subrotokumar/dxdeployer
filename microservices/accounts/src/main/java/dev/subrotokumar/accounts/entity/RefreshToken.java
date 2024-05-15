package dev.subrotokumar.accounts.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(unique = true, nullable = false)
    private UUID tokenId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @Column(updatable = false)
    private Date expiry;
}
