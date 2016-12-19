package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.dao.api.OrderDaoApi;
import fr.afcepf.al29.groupem.dao.api.OrderLineDaoApi;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;

@Transactional
@Component
public class OrderBusImpl implements OrderBusApi{
	
	@Autowired
	private OrderDaoApi orderDaoApi;
	
	@Autowired
	private OrderLineDaoApi orderLineDao;

	@Override
	public List<Order> getOrderByUserId(int idUser) {
		return orderDaoApi.getOrderByUserId(idUser);		
	}
	
	@Override
	public OrderLine createOrderLine(OrderLine orderLine){
		
		return orderLineDao.createOrderLine(orderLine);
	}
	
	@Override
	public Order createOrder(Order order){
		
		return orderDaoApi.createOrder(order);
	}
	


}
