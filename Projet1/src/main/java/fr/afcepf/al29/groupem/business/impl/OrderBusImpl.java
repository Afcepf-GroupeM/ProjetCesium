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

	@Override
	public boolean hasOrderedItem(int itemId, int userId) {
		boolean hasOrderedItem = false;
		/*List<Order> userOrders;
		List<OrderLine> orderLines;
		List<Integer> ordersId = new ArrayList<Integer>();
		
		userOrders = orderDao.getOrderByUserId(userId);		
		orderLines = orderLineDao.getOrderLinesByItemId(itemId);
		
		for (Order o : userOrders){
			ordersId.add(o.getId());
		}
		
		for (OrderLine ol : orderLines){
			hasOrderedItem = ordersId.contains(ol.getOrder().getId());
			
			if (hasOrderedItem){
				break;
			}
		}*/
		Integer idItemOrdered = orderDao.hasOrderedItem(itemId, userId);
		System.out.println("@@@@@@@@@@@@ idItemOrdered = " + idItemOrdered);
		if (idItemOrdered != 0){
			hasOrderedItem = true;
		}
		
		return hasOrderedItem;
	}
	


}
