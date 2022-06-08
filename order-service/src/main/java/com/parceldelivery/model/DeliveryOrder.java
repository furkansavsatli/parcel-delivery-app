package com.parceldelivery.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "deliveryOrder", uniqueConstraints = {

})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String product;

    private DeliveryOrderStatusEnum deliveryOrderStatusEnum;

    private String destination;

    private Long requestId;

    private String userName;
}