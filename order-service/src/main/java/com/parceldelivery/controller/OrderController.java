package com.parceldelivery.controller;

import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.service.DeliveryOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final DeliveryOrderService deliveryOrderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity prepareOrder(@RequestHeader("Authorization") String apikey, @RequestBody DeliveryOrderCommand deliveryOrderCommand) {

        if (deliveryOrderService.prepareOrder(deliveryOrderCommand, apikey)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.badRequest()
                    .body("You cannot orders with the same request id. RequestId: " + deliveryOrderCommand.getRequestId());
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("{requestId}/{destination}")
    public ResponseEntity updateDestination(@RequestHeader("Authorization") String apikey, @PathVariable Long requestId, @PathVariable String destination) {

        if (deliveryOrderService.updateDestination(destination, requestId, apikey)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.badRequest()
                    .body("You cannot make changes because your cargo is on its way.");
        }

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("{requestId}")
    public ResponseEntity cancelOrder(@RequestHeader("Authorization") String apikey, @PathVariable Long requestId) {

        if (deliveryOrderService.deleteOrder(requestId, apikey)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.badRequest()
                    .body("You cannot cancel because your cargo is on its way.");
        }
    }

}