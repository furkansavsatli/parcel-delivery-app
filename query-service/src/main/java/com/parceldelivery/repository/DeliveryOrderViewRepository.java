package com.parceldelivery.repository;

import com.parceldelivery.model.DeliveryOrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOrderViewRepository extends JpaRepository<DeliveryOrderView, Long> {

    DeliveryOrderView findDeliveryOrderViewByRequestId(long requestId);

    List<DeliveryOrderView> findAllByUserName(String userName);

    List<DeliveryOrderView> findAllByCourier(String courierUserName);
}
