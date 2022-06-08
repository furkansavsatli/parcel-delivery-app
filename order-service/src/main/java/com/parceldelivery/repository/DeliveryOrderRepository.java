package com.parceldelivery.repository;

import com.parceldelivery.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    DeliveryOrder findByRequestId(Long requestId);
}
