package com.parceldelivery.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "deliveryOrder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    private DeliveryOrderStatusEnum deliveryOrderStatusEnum;

    private String destination;

    private Long requestId;


    private String courierUserName;
}