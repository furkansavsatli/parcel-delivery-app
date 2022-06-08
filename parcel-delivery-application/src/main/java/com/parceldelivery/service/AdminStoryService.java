package com.parceldelivery.service;


import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminStoryService {

    private final AuthService authService;

    private final ShipmentService shipmentService;

    private final QueryService queryService;

    public String authenticateUser(LoginRequest loginRequest) {
        return authService.signIn(loginRequest);
    }

    public Long registerCourier(SignUpRequest signUpRequest) {
        return authService.signupUser(signUpRequest);
    }


    public ResponseEntity assignToCourier(int requestId, String courierName, String apikey) {
        return shipmentService.assignToCourier(requestId, courierName, apikey);
    }

    public ResponseEntity changeOrderDeliveryStatus(int requestId, DeliveryOrderStatusEnum deliveryOrderStatusEnum, String apikey) {
        return shipmentService.changeOrderDeliveryStatus(requestId, deliveryOrderStatusEnum, apikey);
    }

    public List<DeliveryOrderView> getAllDeliveryList(String apikey) {
        return queryService.getAllDelivery(apikey);
    }

    public List<User> getCourierList(String apiKey) {
        return authService.getCourierList(apiKey);
    }

    public DeliveryOrderView getDeliveryDetails(Long requestId, String apiKey) {
        return queryService.deliveryDetails(requestId, apiKey);
    }
}
