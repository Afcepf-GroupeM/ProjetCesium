package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;

public interface OrderDaoApi {
	
	boolean createOrder(Order order);
	Order getOrderById(int orderId);
	Order updateOrderById(int orderId);
	boolean destroyOrderById(int orderId);
	
	List<Order> getOrderByUserId(int orderId);
	
	


}
