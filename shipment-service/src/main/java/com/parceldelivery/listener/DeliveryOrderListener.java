package com.parceldelivery.listener;

import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.model.DeliveryOrder;
import com.parceldelivery.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderListener {

    private static final Logger log = LogManager.getLogger(DeliveryOrderListener.class);
    private final DeliveryOrderRepository deliveryOrderRepository;

    @RabbitListener(queues = "deliveryOrder")
    public void newDelivery(DeliveryOrderCommand command) {


        DeliveryOrder order = deliveryOrderRepository.findDeliveryOrderByRequestId(command.getRequestId());

        DeliveryOrder deliveryOrder = DeliveryOrder.builder().product(command.getProduct()).destination(command.getDestination()).deliveryOrderStatusEnum(command.getDeliveryOrderStatusEnum()).requestId(command.getRequestId()).build();

        if (order != null) {
            deliveryOrder.setId(order.getId());
        }

        deliveryOrderRepository.save(deliveryOrder);

        log.info("Delivery order is coming: " + command.getProduct());

    }

    @RabbitListener(queues = "deliveryOrder-delete")
    public void deleteDelivery(DeliveryOrderCommand command) {

        DeliveryOrder order = deliveryOrderRepository.findDeliveryOrderByRequestId(command.getRequestId());

        deliveryOrderRepository.delete(order);
        log.info("Delivery order has been deleted: " + command.getProduct());

    }
}