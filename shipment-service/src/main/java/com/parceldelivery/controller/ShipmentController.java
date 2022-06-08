package com.parceldelivery.controller;

import com.parceldelivery.model.DeliveryOrder;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("shipment")
public class ShipmentController {


    private final ShipmentService shipmentService;

    // user
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("deliveryOrder/{requestId}")
    public DeliveryOrder getParcelDeliveryOrder(@PathVariable int requestId) {

        return shipmentService.getParcelDeliveryOrder(requestId);
    }

    // admin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("assign-courier/{requestId}/{courierName}")
    public ResponseEntity assignToCourier(@PathVariable int requestId, @PathVariable String courierName) {
        shipmentService.assignToCourier(requestId, courierName);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //todo silincek
    @GetMapping("delivery-all")
    public List<DeliveryOrder> getAllDelivery() {
        return shipmentService.getAllDeliveryList();
    }


    @PreAuthorize("hasAnyRole('ROLE_COURIER', 'ROLE_ADMIN')")
    @PutMapping("status/{requestId}/{deliveryOrderStatus}")
    public ResponseEntity changeOrderDeliveryStatus(@PathVariable("requestId") int requestId, @PathVariable("deliveryOrderStatus") DeliveryOrderStatusEnum deliveryOrderStatusEnum) {
        shipmentService.changeOrderDeliveryStatus(requestId, deliveryOrderStatusEnum);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}