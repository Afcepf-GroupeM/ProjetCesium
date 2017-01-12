package fr.afcepf.al29.groupem.business.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;

public interface OrderBusApi {

	List<Order> getOrderByUserId(int idUser);

	OrderLine createOrderLine(OrderLine orderLine);

	Order createOrder(Order order);
	
	List<Order> getOrdersByState(OrderState state);
	
	double getTotalPrice(Order order);
	double getTotalPriceForAll(List<Order> orders);
	
	int getNumberOfOrdersSince(Date date);
	List<Order> getOrdersSince(Date date);

}
