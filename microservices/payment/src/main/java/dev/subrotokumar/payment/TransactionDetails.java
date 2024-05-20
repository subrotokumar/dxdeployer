package dev.subrotokumar.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetails {
    private String orderId;
    private String currency;
    private Integer amount;
}
