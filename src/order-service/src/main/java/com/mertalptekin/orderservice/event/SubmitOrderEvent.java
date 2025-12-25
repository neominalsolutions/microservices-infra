package com.mertalptekin.orderservice.event;

import com.mertalptekin.orderservice.dto.OrderedProduct;

import java.util.List;

// orderCode -> üzerinden itemları sipariş ettik.
public record SubmitOrderEvent(String orderCode, List<OrderedProduct> items) {
}
