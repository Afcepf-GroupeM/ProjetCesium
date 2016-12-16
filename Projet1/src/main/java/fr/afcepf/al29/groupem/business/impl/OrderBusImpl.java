package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.dao.api.OrderDaoApi;
import fr.afcepf.al29.groupem.entities.Order;

@Transactional
@Component
public class OrderBusImpl implements OrderBusApi{
	
	@Autowired
	private OrderDaoApi orderDaoApi;

	@Override
	public List<Order> getOrderByUserId(int idUser) {
		return orderDaoApi.getOrderByUserId(idUser);		
	}


}
