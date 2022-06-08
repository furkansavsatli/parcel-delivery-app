package com.parceldelivery.service;


import com.parceldelivery.command.ShipmentCommand;
import com.parceldelivery.model.DeliveryOrder;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.repository.DeliveryOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipmentService {

    private final RabbitTemplate rabbitTemplate;
    private final DeliveryOrderRepository deliveryOrderRepository;


    public DeliveryOrder getDeliveryDetails(int delivery) {

        return new DeliveryOrder();
    }

//    public ArrayList<DeliveryOrder> getOwnDeliveryList() {
//        return new ArrayList<DeliveryOrder>();
//    }

    public List<DeliveryOrder> getAllDeliveryList() {
        return deliveryOrderRepository.findAll();
    }


    public DeliveryOrder getParcelDeliveryOrder(int deliveryNumber) {

        return deliveryOrderRepository.findDeliveryOrderByRequestId(deliveryNumber);

    }

    public void assignToCourier(int requestId, String courierUserName) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findDeliveryOrderByRequestId(requestId);

        deliveryOrder.setCourierUserName(courierUserName);
        deliveryOrder.setDeliveryOrderStatusEnum(DeliveryOrderStatusEnum.ASSIGNED);

        deliveryOrderRepository.save(deliveryOrder);

        ShipmentCommand shipmentCommand = getDeliveryOrderCommand(deliveryOrder);
        shipmentCommand.setCourierName(courierUserName);
        rabbitTemplate.convertAndSend("shipment-query", shipmentCommand);
    }

    private ShipmentCommand getDeliveryOrderCommand(DeliveryOrder deliveryOrder) {
        return ShipmentCommand.builder().destination(deliveryOrder.getDestination()).requestId(deliveryOrder.getRequestId()).product(deliveryOrder.getProduct()).deliveryOrderStatusEnum(deliveryOrder.getDeliveryOrderStatusEnum()).courierName(deliveryOrder.getCourierUserName()).build();
    }

    public void changeOrderDeliveryStatus(int requestId, DeliveryOrderStatusEnum deliveryOrderStatusEnum) {

        DeliveryOrder deliveryOrder = deliveryOrderRepository.findDeliveryOrderByRequestId(requestId);

        deliveryOrder.setDeliveryOrderStatusEnum(deliveryOrderStatusEnum);

        deliveryOrderRepository.save(deliveryOrder);

        ShipmentCommand shipmentCommand = getDeliveryOrderCommand(deliveryOrder);
        rabbitTemplate.convertAndSend("shipment-query", shipmentCommand);
    }
}
