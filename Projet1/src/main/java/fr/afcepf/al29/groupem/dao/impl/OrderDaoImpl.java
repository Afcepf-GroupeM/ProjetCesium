package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.api.OrderDaoApi;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderState;


@Transactional
@Component 
public class OrderDaoImpl implements OrderDaoApi{

	@PersistenceContext(unitName="Projet1") 
	private EntityManager entityManager;
	
	@Override
	public Order createOrder(Order order) {
		entityManager.persist(order);
		return order;
	}

	@Override
	public Order getOrderById(int orderId) {
		Order order = entityManager.find(Order.class, orderId);
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		entityManager.merge(order);
		return order;
	}

	@Override
	public boolean destroyOrder(Order order) {
		// GetReference used to attach entity User
		Order orderToRemove = entityManager.getReference(Order.class, order.getId());
		entityManager.remove(orderToRemove);
		return (getOrderById(order.getId()) == null);
	}

	@Override
	public List<Order> getOrderByUserId(int userId) {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.user.id = :userid", Order.class)
								 .setParameter("userid", userId)
								 .getResultList();
		return listOrders;
	}

	@Override
	public List<Order> getOrdersByState(OrderState state) {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.orderstate = :state", Order.class)
				 .setParameter("state", state)
				 .getResultList();
		return listOrders;
	}
	
	
	

}
