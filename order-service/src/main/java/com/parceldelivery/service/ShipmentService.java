package com.parceldelivery.service;

import com.parceldelivery.model.DeliveryOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${service.shipment}:8083", name = "shipment")
public interface ShipmentService {

    @RequestMapping(method = RequestMethod.GET, value = "/shipment/deliveryOrder/{requestId}")
    DeliveryOrder getParcelDeliveryOrder(@RequestHeader("Authorization") String authorization, @PathVariable("requestId") Long requestId);


}
