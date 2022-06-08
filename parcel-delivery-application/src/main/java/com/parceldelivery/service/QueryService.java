package com.parceldelivery.service;

import com.parceldelivery.model.DeliveryOrderView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "${service.gateway}:9991", name = "query")
public interface QueryService {

    @RequestMapping(method = RequestMethod.GET, value = "query/deliveryorder")
    List<DeliveryOrderView> getOwnDeliveryList(@RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.GET, value = "query/deliveryorder/{requestId}")
    DeliveryOrderView deliveryDetails(@PathVariable Long requestId, @RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.GET, value = "query/deliveryorder-list")
    List<DeliveryOrderView> getAllDelivery(@RequestHeader("Authorization") String authorization);

    @RequestMapping(method = RequestMethod.GET, value = "query/deliveryorder-courier")
    List<DeliveryOrderView> getCourierOwnOrderList(@RequestHeader("Authorization") String authorization);
}
