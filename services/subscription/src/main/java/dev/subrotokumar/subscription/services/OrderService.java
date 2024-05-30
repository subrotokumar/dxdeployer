package dev.subrotokumar.subscription.services;

import dev.subrotokumar.subscription.model.record.OrderRequest;

public interface OrderService {
    public Integer createOrder(int userId, String authorization, OrderRequest request);
}
