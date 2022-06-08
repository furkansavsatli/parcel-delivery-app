package com.parceldelivery.service;


import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.model.DeliveryOrderView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourierStoryService {

    private final AuthService authService;

    private final ShipmentService shipmentService;

    private final QueryService queryService;

    public String authenticateUser(LoginRequest loginRequest) {
        return authService.signIn(loginRequest);
    }

    public ResponseEntity changeOrderDeliveryStatus(int requestId, DeliveryOrderStatusEnum deliveryOrderStatusEnum, String apikey) {
        return shipmentService.changeOrderDeliveryStatus(requestId, deliveryOrderStatusEnum, apikey);
    }

    public DeliveryOrderView getDeliveryDetails(Long requestId, String apiKey) {
        return queryService.deliveryDetails(requestId, apiKey);
    }

    public List<DeliveryOrderView> getCourierOwnOrderList(String apiKey) {
        return queryService.getCourierOwnOrderList(apiKey);
    }
}
