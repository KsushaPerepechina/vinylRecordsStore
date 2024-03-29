package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.OrderDTO;
import com.vironit.vinylRecordsStore.entity.Order;
import com.vironit.vinylRecordsStore.rest.OrdersRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class OrderDtoAssembler extends ResourceAssemblerSupport<Order, OrderDTO> {

    public OrderDtoAssembler() {
        super(OrdersRestController.class, OrderDTO.class);
    }

    @Override
    public OrderDTO toResource(Order order) {
        OrderDTO dto = createResourceWithId(order.getId(), order);
        dto.setOrderId(order.getId());
        dto.setUser(order.getUserAccount().getEmail());
        dto.setBillNumber(order.getBill().getNumber());
        dto.setProductsCost(order.getProductsCost());
        dto.setDateCreated(order.getDateCreated());
        dto.setDeliveryCost(order.getDeliveryCost());
        dto.setTotalCost(order.isDeliveryIncluded() ? (order.getProductsCost() + order.getDeliveryCost()) : order.getProductsCost());
        dto.setDeliveryIncluded(order.isDeliveryIncluded());
        dto.setPayed(order.getBill().isPayed());
        dto.setExecuted(order.isExecuted());
        return dto;
    }
}
