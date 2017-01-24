package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.OrderLine;

public interface OrderLineDaoApi {

	OrderLine createOrderLine(OrderLine orderLine);
	List<OrderLine> getOrderLinesByOrderId(int orderId);
	List<Integer> getOrderIdsInOrderLinesByItemId(int itemId);
	OrderLine getOrderLineById(int orderLineId);
	OrderLine updateOrderLine(OrderLine orderLine);
	boolean deleteOrderLineById(int orderLineId);
}
