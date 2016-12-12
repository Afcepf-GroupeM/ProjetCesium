package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;

public interface OrderDaoApi {
	
	Order createOrder(Order order);
	Order getOrderById(int orderId);
	Order updateOrder(Order order);
	boolean destroyOrder(Order order);
	
	List<Order> getOrderByUserId(int userId);
	
	


}
