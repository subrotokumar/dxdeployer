package dev.subrotokumar.payment;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class PaymentIntegrationController {

    @GetMapping("/txn")
    public TransactionDetails generateNewInvoice() throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_7rQ2mR9FdkK0ER", "hiV21iuzKgsyI1WXn7jGWmuo");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", 500);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt#1");
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        orderRequest.put("notes", notes);
        Order order = razorpay.orders.create(orderRequest);
        System.out.println(order);

        return prepareTransaction(order);
    }

    private TransactionDetails prepareTransaction(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");
        TransactionDetails tx = TransactionDetails.builder().orderId(orderId).currency(currency).amount(amount).build();
        return tx;
    }

}