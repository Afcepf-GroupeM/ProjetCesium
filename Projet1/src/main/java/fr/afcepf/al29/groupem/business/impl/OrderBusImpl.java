package fr.afcepf.al29.groupem.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.dao.api.OrderDaoApi;
import fr.afcepf.al29.groupem.dao.api.OrderLineDaoApi;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component
public class OrderBusImpl implements OrderBusApi{
	
	@Autowired
	private OrderDaoApi orderDao;
	
	@Autowired
	private OrderLineDaoApi orderLineDao;

	@Override
	public List<Order> getOrderByUserId(int idUser) {
		return orderDao.getOrderByUserId(idUser);		
	}
	
	@Override
	public OrderLine createOrderLine(OrderLine orderLine){
		
		return orderLineDao.createOrderLine(orderLine);
	}
	
	@Override
	public Order createOrder(Order order){
		
		return orderDao.createOrder(order);
	}

	@Override
	public List<Order> getOrdersByState(OrderState state) {
	
		return orderDao.getOrdersByState(state);
	}

	@Override
	public double getTotalPrice(Order order) {
		double amount = 0.0;
		List<OrderLine> orderLines = orderLineDao.getOrderLinesByOrderId(order.getId());
		for (OrderLine orderLine : orderLines) {
			amount += orderLine.getQuantity()*orderLine.getUnitPrice();
		}
		return amount;
	}

	@Override
	public double getTotalPriceForAll(List<Order> orders) {
		double amount = 0.0;
		for (Order order : orders) {
			amount += getTotalPrice(order);
		}
		return amount;
	}

	@Override
	public int getNumberOfOrdersSince(Date date) {
		List<Order> orders = getOrdersSince(date);
		int numberOfOrders = 0;
		for (Order order : orders) {
			numberOfOrders++;
		}
		return numberOfOrders;
	}

	@Override
	public List<Order> getOrdersSince(Date date) {
		return orderDao.getOrdersSince(date);
	}

	//search By trackingNumber
	@Override
	public List<Order> searchOrders(String input, String searchType) {
		
		List<Order> listOrders = new ArrayList<>();	    
        Order order = orderDao.getOrderByTrackingNumber(input);
        if(order != null){
                listOrders.add(order);
            }
           
	    return listOrders;
	}

	@Override
	public boolean hasOrderedItem(int itemId, int userId) {
		boolean hasOrderedItem = false;
		List<Integer> orderIdsByUser;
		List<Integer> orderIdsInOrderLinesByItemId;
		
		orderIdsByUser = orderDao.getOrderIdsByUserId(userId);		
		orderIdsInOrderLinesByItemId = orderLineDao.getOrderIdsInOrderLinesByItemId(itemId);
		
		for (Integer orderId : orderIdsInOrderLinesByItemId){
			hasOrderedItem = orderIdsByUser.contains(orderId);
			
			if (hasOrderedItem){
				break;
			}
		}
		
		return hasOrderedItem;
	}

	@Override
	public List<Order> getAll() {
		List<Order> retour = null;
		try{
			retour = orderDao.getAll();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("la liste de commandes est vide");
		}
		return retour;
	}

	@Override
	public Order getOrderById(int orderId) {
		return orderDao.getOrderById(orderId);
	}
	
}
	



