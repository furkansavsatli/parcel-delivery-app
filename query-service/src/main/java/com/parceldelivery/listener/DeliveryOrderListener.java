package com.parceldelivery.listener;

import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.command.ShipmentCommand;
import com.parceldelivery.model.DeliveryOrderView;
import com.parceldelivery.repository.DeliveryOrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderListener {

    private static final Logger log = LogManager.getLogger(DeliveryOrderListener.class);
    private final DeliveryOrderViewRepository deliveryOrderViewRepository;

    @RabbitListener(queues = "deliveryOrder-query")
    public void processConfigDto(DeliveryOrderCommand command) {

        DeliveryOrderView order = deliveryOrderViewRepository.findDeliveryOrderViewByRequestId(command.getRequestId());

        DeliveryOrderView deliveryOrderView = DeliveryOrderView.builder()
                .product(command.getProduct())
                .userName(command.getUserName())
                .destination(command.getDestination())
                .deliveryOrderStatusEnum(command.getDeliveryOrderStatusEnum().name())
                .requestId(command.getRequestId())
                .build();
        if (order != null) {
            deliveryOrderView.setId(order.getId());
        }

        deliveryOrderViewRepository.save(deliveryOrderView);

        log.info("Delivery order is coming: " + command.getProduct());
    }

    @RabbitListener(queues = "deliveryOrder-query-delete")
    public void deleteDelivery(DeliveryOrderCommand command) {

        DeliveryOrderView order = deliveryOrderViewRepository.findDeliveryOrderViewByRequestId(command.getRequestId());

        try {
            deliveryOrderViewRepository.delete(order);
        } catch (Exception e) {
            log.info("Error " + command.getProduct());
        }

        log.info("Delivery order has been deleted: " + command.getProduct());

    }

    @RabbitListener(queues = "shipment-query")
    public void shiptment(ShipmentCommand command) {

        DeliveryOrderView order = deliveryOrderViewRepository.findDeliveryOrderViewByRequestId(command.getRequestId());

        DeliveryOrderView deliveryOrderView = DeliveryOrderView.builder().courier(command.getCourierName()).product(command.getProduct()).destination(command.getDestination()).deliveryOrderStatusEnum(command.getDeliveryOrderStatusEnum().name()).requestId(command.getRequestId()).build();
        if (order != null) {
            deliveryOrderView.setId(order.getId());
            deliveryOrderView.setUserName(order.getUserName());
        }

        deliveryOrderViewRepository.save(deliveryOrderView);

        log.info("Delivery order is coming: " + command.getProduct());

    }
}