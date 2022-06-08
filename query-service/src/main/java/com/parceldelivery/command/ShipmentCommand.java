package com.parceldelivery.command;


import com.parceldelivery.model.DeliveryOrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentCommand implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private String destination;
    private String product;
    private DeliveryOrderStatusEnum deliveryOrderStatusEnum;
    private Long requestId;
    private String courierName;
}
