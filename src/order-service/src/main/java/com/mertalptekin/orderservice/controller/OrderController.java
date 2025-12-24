package com.mertalptekin.orderservice.controller;

import com.mertalptekin.orderservice.client.ProductClient;
import com.mertalptekin.orderservice.request.OrderedProductDetailRequest;
import com.mertalptekin.orderservice.response.OrderedProductDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {
    private final ProductClient productClient;
    private final StreamBridge streamBridge;

    public OrderController(ProductClient productClient, StreamBridge streamBridge){
        this.productClient = productClient;
        this.streamBridge = streamBridge;
    }
    // api/v1/orders/123/orderDetails
    @GetMapping("{orderCode}/orderDetails")
    public ResponseEntity<OrderedProductDetailResponse> getOrderedProductDetails(String orderCode){
        String[] productIds = new String[2];
        productIds[0] = UUID.randomUUID().toString();
        productIds[1] = UUID.randomUUID().toString(); // veri tabanında çekilmiş gibi simüle ettik
        log.info("order-service-request");
        return productClient.getOrderedProducts(new OrderedProductDetailRequest(productIds));
    }


    @PostMapping("sendKafka")
    public ResponseEntity<String> sendMessageToKafka(){

        Message<String> message = MessageBuilder.withPayload("Mesaj Gönderildi").build();
        boolean isSend = streamBridge.send("submitOrder-out-0",message);

        return ResponseEntity.ok(isSend ? "Mesaj teslim edildi":"Mesaj iletilemedi");
    }

    // find Ordered Product In DB
    // var response = orderRepository.findByOrderCode(orderCode)
    // response.productIds -> sipariş edilen ürünlerimiz


}
