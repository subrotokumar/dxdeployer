package dev.subrotokumar.accounts.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyMagiclinkDto {
    private String token;
}
