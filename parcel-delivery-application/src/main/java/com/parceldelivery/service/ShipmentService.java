package com.parceldelivery.service;

import com.parceldelivery.model.DeliveryOrderStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${service.gateway}:9991", name = "shipment")
public interface ShipmentService {

    @RequestMapping(method = RequestMethod.PUT, value = "shipment/assign-courier/{requestId}/{courierName}")
    ResponseEntity assignToCourier(@PathVariable int requestId, @PathVariable String courierName, @RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.PUT, value = "shipment/status/{requestId}/{deliveryOrderStatus}")
    ResponseEntity changeOrderDeliveryStatus(@PathVariable("requestId") int requestId, @PathVariable("deliveryOrderStatus") DeliveryOrderStatusEnum deliveryOrderStatusEnum, @RequestHeader("Authorization") String authorization);

}
