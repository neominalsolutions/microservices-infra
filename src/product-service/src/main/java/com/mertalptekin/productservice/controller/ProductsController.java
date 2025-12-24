package com.mertalptekin.productservice.controller;


import com.mertalptekin.productservice.dto.OrderedProduct;
import com.mertalptekin.productservice.request.OrderedProductDetailRequest;
import com.mertalptekin.productservice.response.OrderedProductDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {


    // api/v1/products/details
    @PostMapping("details")
    public ResponseEntity<OrderedProductDetailResponse> productDetailRequest(@RequestBody OrderedProductDetailRequest request){

        // kendi veri tabanında requestProductIds göre sorgulanıp çekildi
        List<OrderedProduct> orderedProducts = Arrays.asList(new OrderedProduct("P-1", BigDecimal.valueOf(100.2),10),new OrderedProduct("P-2", BigDecimal.valueOf(100.2),10));

        // response olarak iletildi.
        return ResponseEntity.ok(new OrderedProductDetailResponse(orderedProducts));
    }


}
