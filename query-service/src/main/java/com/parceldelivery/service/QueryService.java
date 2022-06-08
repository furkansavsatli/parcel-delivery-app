package com.parceldelivery.service;


import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.repository.DeliveryOrderViewRepository;
import com.parceldelivery.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final DeliveryOrderViewRepository deliveryOrderViewRepository;


    public DeliveryOrderView getDeliveryDetails(int requestId) {

        return deliveryOrderViewRepository.findDeliveryOrderViewByRequestId(requestId);
    }

    public List<DeliveryOrderView> getOwnDeliveryList(String apikey) {

        return deliveryOrderViewRepository.findAllByUserName(jwtTokenProvider.getUserIdFromJWT(apikey));

    }

    public List<DeliveryOrderView> getAllDeliveryList() {
        return deliveryOrderViewRepository.findAll();
    }

    public List<DeliveryOrderView> getCourierOwnOrderList(String apiKey) {

        return deliveryOrderViewRepository.findAllByCourier(jwtTokenProvider.getUserIdFromJWT(apiKey));
    }
}
