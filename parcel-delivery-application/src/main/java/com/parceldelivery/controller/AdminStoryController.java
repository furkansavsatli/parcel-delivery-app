package com.parceldelivery.controller;

import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.model.User;
import com.parceldelivery.service.AdminStoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("admin-story")
@Api("ADMIN STORY")
public class AdminStoryController {

    private final AdminStoryService adminStoryService;

    @ApiOperation(value = "ADMIN can admin log in")
    @PostMapping("auth/signin")
    public String loginAdmin(@RequestBody @ApiParam(defaultValue = "foo") LoginRequest loginRequest) {

        return adminStoryService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "ADMIN can create an courier account;")
    @PostMapping("auth/register-courier")
    public Long registerUser(@RequestBody SignUpRequest signUpRequest) {
        return adminStoryService.registerCourier(signUpRequest);
    }

    @ApiOperation(value = "ADMIN can see the details of a delivery")
    @GetMapping("deliveryorder/{requestId}")
    public DeliveryOrderView deliveryDetails(@PathVariable Long requestId, @RequestHeader("Authorization") String apiKey) {

        return adminStoryService.getDeliveryDetails(requestId, apiKey);
    }

    @ApiOperation(value = "ADMIN can assign parcel delivery order to courier")
    @PutMapping("shipment/assign-courier/{requestId}/{courierName}")
    public ResponseEntity assignToCourier(@PathVariable int requestId, @PathVariable String courierName, @RequestHeader("Authorization") String apiKey) {
        return adminStoryService.assignToCourier(requestId, courierName, apiKey);

    }

    @ApiOperation(value = "ADMIN can change the status of a parcel delivery order")
    @PutMapping("shipment/status/{requestId}/{deliveryOrderStatus}")
    public ResponseEntity changeOrderDeliveryStatus(@PathVariable("requestId") int requestId, @PathVariable("deliveryOrderStatus") DeliveryOrderStatusEnum deliveryOrderStatusEnum, @RequestHeader("Authorization") String apiKey) {
        return adminStoryService.changeOrderDeliveryStatus(requestId, deliveryOrderStatusEnum, apiKey);

    }

    @ApiOperation(value = "ADMIN view all parcel delivery orders")
    @GetMapping("query/deliveryorder-list")
    public List<DeliveryOrderView> getAllDelivery(@RequestHeader("Authorization") String apiKey) {
        return adminStoryService.getAllDeliveryList(apiKey);
    }

    @ApiOperation(value = "ADMIN can see list of couriers with their statuses;")
    @GetMapping("auth/courier-list")
    public List<User> getCourierList(@RequestHeader("Authorization") String apiKey) {
        return adminStoryService.getCourierList(apiKey);
    }

}