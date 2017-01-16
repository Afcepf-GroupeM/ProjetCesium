package fr.afcepf.al29.groupem.dao.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderState;

public interface OrderDaoApi {
	
	Order createOrder(Order order);
	Order getOrderById(int orderId);
	Order updateOrder(Order order);
	boolean destroyOrder(Order order);
	
	List<Order> getOrderByUserId(int userId);
	
	List<Order> getOrdersByState(OrderState state);
	
	List<Order> getOrdersSince(Date date);
	
	Order getOrderByTrackingNumber(String trackingNumber);

	List<Order> getOrderByState(String state);

}
