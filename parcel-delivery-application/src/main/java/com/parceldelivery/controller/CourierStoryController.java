package com.parceldelivery.controller;

import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.service.CourierStoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("courier-story")
@Api("COURIER STORY")
public class CourierStoryController {


    private final CourierStoryService courierStoryService;

    @ApiOperation(value = "COURIER can log in")
    @PostMapping("/signin")
    public String loginAdmin(@RequestBody LoginRequest loginRequest) {

        return courierStoryService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "COURIER can change the status of a parcel delivery order")
    @PutMapping("shipment/status/{requestId}/{deliveryOrderStatus}")
    public ResponseEntity changeOrderDeliveryStatus(@PathVariable("requestId") int requestId, @PathVariable("deliveryOrderStatus") DeliveryOrderStatusEnum deliveryOrderStatusEnum, @RequestHeader("Authorization") String apiKey) {
        return courierStoryService.changeOrderDeliveryStatus(requestId, deliveryOrderStatusEnum, apiKey);

    }

    @ApiOperation(value = "COURIER can see the details of a delivery")
    @GetMapping("deliveryorder/{requestId}")
    public DeliveryOrderView deliveryDetails(@PathVariable Long requestId, @RequestHeader("Authorization") String apiKey) {

        return courierStoryService.getDeliveryDetails(requestId, apiKey);
    }

    /**
     * return own Order list
     *
     * @param apiKey
     * @return
     */
    @ApiOperation(value = "COURIER Can view all parcel delivery orders that assigned to him;")
    @GetMapping("deliveryorder-courier")
    public List<DeliveryOrderView> getCourierOwnOrderList(@RequestHeader("Authorization") String apiKey) {

        return courierStoryService.getCourierOwnOrderList(apiKey);
    }

}