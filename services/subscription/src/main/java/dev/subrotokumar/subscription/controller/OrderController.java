package dev.subrotokumar.subscription.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.subscription.services.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscription/order")
@Validated
public class OrderController {

    final private OrderService orderService;

    // @PostMapping()
    // public ResponseEntity<Integer> createOrder(
    //     @RequestBody @Valid OrderRequest entity,
    //     HttpServletRequest request,
    //     @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    // ) {

    //     int userId = Integer.parseInt(request.getAttribute("X-USER-ID").toString());
    //     return ResponseEntity.ok(orderService.createOrder());
    // }
    
}
