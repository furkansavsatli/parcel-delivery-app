package com.parceldelivery.command;


import com.parceldelivery.model.DeliveryOrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeliveryOrderCommand implements Serializable {

    private String destination;
    private String product;
    private DeliveryOrderStatusEnum deliveryOrderStatusEnum;
    private Long requestId;
}
