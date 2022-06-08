package com.parceldelivery.controller;

import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.service.QueryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("query")
public class QueryController {

    private final QueryService queryService;

    /**
     * return own DeliveryOrder
     *
     * @param apiKey
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("deliveryorder")
    public List<DeliveryOrderView> getOwnDeliveryList(@RequestHeader("Authorization") String apiKey) {

        return queryService.getOwnDeliveryList(apiKey);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN','ROLE_COURIER')")
    @GetMapping("deliveryorder/{requestId}")
    public DeliveryOrderView deliveryDetails(@PathVariable int requestId) {

        return queryService.getDeliveryDetails(requestId);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("deliveryorder-list")
    public List<DeliveryOrderView> getAllDelivery() {
        return queryService.getAllDeliveryList();
    }

    // courier

    /**
     * return own Order list
     *
     * @param apiKey
     * @return
     */
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @GetMapping("deliveryorder-courier")
    public List<DeliveryOrderView> getCourierOwnOrderList(@RequestHeader("Authorization") String apiKey) {

        return queryService.getCourierOwnOrderList(apiKey);
    }
}