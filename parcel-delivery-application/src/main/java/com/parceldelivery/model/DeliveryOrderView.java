package com.parceldelivery.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderView implements Serializable {


    private Long id;

    @NotBlank
    @Size(max = 40)
    private String product;

    private String deliveryOrderStatusEnum;

    private String destination;

    private Long requestId;

    private String userName;

    private String courier;
}