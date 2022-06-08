package com.parceldelivery.service;


import com.parceldelivery.command.DeliveryOrderCommand;
import com.parceldelivery.model.DeliveryOrder;
import com.parceldelivery.model.DeliveryOrderStatusEnum;
import com.parceldelivery.repository.DeliveryOrderRepository;
import com.parceldelivery.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DeliveryOrderService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RabbitTemplate rabbitTemplate;

    private final ShipmentService shipmentService;

    private final DeliveryOrderRepository deliveryOrderRepository;

    public Boolean prepareOrder(DeliveryOrderCommand deliveryOrderCommand, String apikey) {


        if (deliveryOrderRepository.findByRequestId(deliveryOrderCommand.getRequestId()) != null) {

            return false;
        }


        String userIdFromJWT = jwtTokenProvider.getUserIdFromJWT(apikey);

        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .deliveryOrderStatusEnum(DeliveryOrderStatusEnum.ORDER)
                .destination(deliveryOrderCommand.getDestination())
                .product(deliveryOrderCommand.getProduct())
                .userName(userIdFromJWT)
                .requestId(deliveryOrderCommand.getRequestId())
                .build();

        //crate order
        deliveryOrderRepository.save(deliveryOrder);

        //notify view service
        deliveryOrderCommand.setDeliveryOrderStatusEnum(DeliveryOrderStatusEnum.ORDER);
        deliveryOrderCommand.setUserName(userIdFromJWT);
        rabbitTemplate.convertAndSend("deliveryOrder", deliveryOrderCommand);
        rabbitTemplate.convertAndSend("deliveryOrder-query", deliveryOrderCommand);
        return true;
    }

    public boolean updateDestination(String destination, Long requestId, String apikey) {

        DeliveryOrder deliveryOrder = deliveryOrderRepository.findByRequestId(requestId);
        if (deliveryOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DeliveryOrder deliveryOrderShipment = shipmentService.getParcelDeliveryOrder(apikey, deliveryOrder.getRequestId());

        if (DeliveryOrderStatusEnum.ORDER.equals(deliveryOrderShipment.getDeliveryOrderStatusEnum())) {

            deliveryOrder.setDestination(destination);
            deliveryOrderRepository.save(deliveryOrder);


            DeliveryOrderCommand deliveryOrderCommand = getDeliveryOrderCommand(deliveryOrder);
            rabbitTemplate.convertAndSend("deliveryOrder", deliveryOrderCommand);
            rabbitTemplate.convertAndSend("deliveryOrder-query", deliveryOrderCommand);

            return true;
        } else {
            return false;
        }
    }

    private DeliveryOrderCommand getDeliveryOrderCommand(DeliveryOrder deliveryOrder) {
        return DeliveryOrderCommand.builder()
                .destination(deliveryOrder.getDestination())
                .requestId(deliveryOrder.getRequestId())
                .product(deliveryOrder.getProduct())
                .deliveryOrderStatusEnum(deliveryOrder.getDeliveryOrderStatusEnum())
                .userName(deliveryOrder.getUserName())
                .build();
    }

    public boolean deleteOrder(Long id, String apikey) {

        DeliveryOrder deliveryOrder = deliveryOrderRepository.findByRequestId(id);

        if (deliveryOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DeliveryOrder deliveryOrderShipment = shipmentService.getParcelDeliveryOrder(apikey, deliveryOrder.getRequestId());

        if (DeliveryOrderStatusEnum.ORDER.equals(deliveryOrderShipment.getDeliveryOrderStatusEnum())) {

            deliveryOrderRepository.delete(deliveryOrder);

            DeliveryOrderCommand deliveryOrderCommand = getDeliveryOrderCommand(deliveryOrder);
            rabbitTemplate.convertAndSend("deliveryOrder-delete", deliveryOrderCommand);
            rabbitTemplate.convertAndSend("deliveryOrder-query-delete", deliveryOrderCommand);
            return true;
        } else {
            return false;
        }
    }
}
