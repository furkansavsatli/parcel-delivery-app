package com.parceldelivery.service;


import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.DeliveryOrderView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserStoryService {

    private final AuthService authService;

    private final OrderService orderService;

    private final QueryService queryService;

    public String authenticateUser(LoginRequest loginRequest) {
        return authService.signIn(loginRequest);
    }

    public Long registerUser(SignUpRequest signUpRequest) {
        return authService.signupUser(signUpRequest);
    }

    public Long registerCourier(SignUpRequest signUpRequest) {
        return authService.signupUser(signUpRequest);
    }

    public ResponseEntity prepareOrder(DeliveryOrderCommand deliveryOrderCommand, String apikey) {
        return orderService.prepareOrder(deliveryOrderCommand, apikey);
    }

    public ResponseEntity updateDestination(Long requestId, String destination, String apikey) {
        return orderService.updateDestination(requestId, destination, apikey);
    }

    public ResponseEntity cancelOrder(Long requestId, Long requestId1, String apikey) {
        return orderService.cancelOrder(requestId, apikey);
    }

    public List<DeliveryOrderView> getOwnDeliveryList(String apiKey) {
        return queryService.getOwnDeliveryList(apiKey);
    }

    public DeliveryOrderView getDeliveryDetails(Long requestId, String apiKey) {
        return queryService.deliveryDetails(requestId, apiKey);
    }


}
