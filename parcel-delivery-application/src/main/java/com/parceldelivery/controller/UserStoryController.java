package com.parceldelivery.controller;

import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.service.UserStoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("user-story")
@Api("USER STORY")
public class UserStoryController {


    private final UserStoryService userStoryService;

    @ApiOperation(value = "USER can log in")
    @PostMapping("/signin")
    public String loginAdmin(@RequestBody LoginRequest loginRequest) {

        return userStoryService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "USER can create an user account")
    @PostMapping("/signup-user")
    public Long registerUser(@RequestBody SignUpRequest signUpRequest) {
        return userStoryService.registerUser(signUpRequest);
    }


    @ApiOperation(value = "USER can create a parcel delivery order")
    @PostMapping("order")
    public ResponseEntity prepareOrder(@RequestHeader("Authorization") String apikey, @RequestBody DeliveryOrderCommand deliveryOrderCommand) {

        return userStoryService.prepareOrder(deliveryOrderCommand, apikey);
    }

    @ApiOperation(value = "USER can change the destination of a parcel delivery order")
    @PutMapping("{requestId}/{destination}")
    public ResponseEntity updateDestination(@RequestHeader("Authorization") String apikey, @PathVariable Long requestId, @PathVariable String destination) {

        return userStoryService.updateDestination(requestId, destination, apikey);

    }

    @ApiOperation(value = "USER can cancel a parcel delivery order;")
    @DeleteMapping("{requestId}")
    public ResponseEntity cancelOrder(@RequestHeader("Authorization") String apikey, @PathVariable Long requestId) {

        return userStoryService.cancelOrder(requestId, requestId, apikey);
    }

    /**
     * return own DeliveryOrder
     *
     * @param apiKey
     * @return
     */
    @ApiOperation(value = "USER can see all parcel delivery orders that he/she created")
    @GetMapping("deliveryorder")
    public List<DeliveryOrderView> getOwnDeliveryList(@RequestHeader("Authorization") String apiKey) {

        return userStoryService.getOwnDeliveryList(apiKey);
    }

    @ApiOperation(value = "USER can see the details of a delivery")
    @GetMapping("deliveryorder/{requestId}")
    public DeliveryOrderView deliveryDetails(@PathVariable Long requestId, @RequestHeader("Authorization") String apiKey) {

        return userStoryService.getDeliveryDetails(requestId, apiKey);
    }

}