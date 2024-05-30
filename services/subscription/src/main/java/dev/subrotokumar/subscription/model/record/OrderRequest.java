package dev.subrotokumar.subscription.model.record;

import java.math.BigDecimal;

import dev.subrotokumar.subscription.model.enums.PaymentMethod;
import dev.subrotokumar.subscription.model.enums.Plan;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
    Integer id,
    String reference,

    @Positive(message="Order amount should be positive")
    BigDecimal amount,

    @NotNull(message="payment method should be precised")
    PaymentMethod paymentMethod,

    @NotNull(message="userId should not be null")
    @Positive(message="userId should be positive")
    Integer userId,

    @NotNull(message="plan should be precised")
    Plan plan
) {
}
