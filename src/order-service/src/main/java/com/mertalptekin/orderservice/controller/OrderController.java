package com.mertalptekin.orderservice.controller;

import com.mertalptekin.orderservice.client.ProductClient;
import com.mertalptekin.orderservice.request.OrderedProductDetailRequest;
import com.mertalptekin.orderservice.response.OrderedProductDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final ProductClient productClient;
    public OrderController(ProductClient productClient){
        this.productClient = productClient;
    }
    // api/v1/orders/123/orderDetails
    @GetMapping("{orderCode}/orderDetails")
    public ResponseEntity<OrderedProductDetailResponse> getOrderedProductDetails(String orderCode){
        String[] productIds = new String[2];
        productIds[0] = UUID.randomUUID().toString();
        productIds[1] = UUID.randomUUID().toString(); // veri tabanında çekilmiş gibi simüle ettik
        return productClient.getOrderedProducts(new OrderedProductDetailRequest(productIds));
    }

    // find Ordered Product In DB
    // var response = orderRepository.findByOrderCode(orderCode)
    // response.productIds -> sipariş edilen ürünlerimiz


}
