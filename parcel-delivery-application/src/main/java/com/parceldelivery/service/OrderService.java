package com.parceldelivery.service;

import com.parceldelivery.command.DeliveryOrderCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${service.gateway}:9991", name = "order")
public interface OrderService {

    @RequestMapping(method = RequestMethod.POST, value = "order")
    ResponseEntity prepareOrder(@RequestBody DeliveryOrderCommand deliveryOrderCommand, @RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.PUT, value = "order/{requestId}/{destination}")
    ResponseEntity updateDestination(@PathVariable Long requestId, @PathVariable String destination, @RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.DELETE, value = "order/{requestId}")
    ResponseEntity cancelOrder(@PathVariable Long requestId, @RequestHeader("Authorization") String authorization);

}
