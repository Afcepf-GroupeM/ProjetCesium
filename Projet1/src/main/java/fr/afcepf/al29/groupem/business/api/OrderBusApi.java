package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;

public interface OrderBusApi {

	List<Order> getOrderByUserId(int idUser);

	OrderLine createOrderLine(OrderLine orderLine);

	Order createOrder(Order order);

}
