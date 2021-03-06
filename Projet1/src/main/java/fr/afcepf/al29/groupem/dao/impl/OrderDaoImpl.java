package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getOrderIdsByUserId(int userId) {
		return entityManager.createQuery("SELECT ord.id FROM Order ord WHERE ord.user.id = :userid").setParameter("userid", userId).getResultList();
	}

	@Override
	public List<Order> getOrdersByState(OrderState state) {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.state = :state", Order.class)
				 .setParameter("state", state)
				 .getResultList();
		return listOrders;
	}

	@Override
	public List<Order> getOrdersSince(Date date) {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.creationDate > :date", Order.class)
				 .setParameter("date", date)
				 .getResultList();
		return listOrders;
	}
	
	@Override
	public Order getOrderByTrackingNumber(String trackingNumber) {
		Order order = null;
		try {
			order = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.trackingNumber = :ordertrackingnumber",Order.class).setParameter("ordertrackingnumber", trackingNumber).getSingleResult();
		} catch (Exception e) {} // Catches exception if email doesn't exists. Mandatory with getSingleResult. Return null
		return order;
	}

	@Override
	public List<Order> getOrderByState(String state) {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord WHERE ord.state = :state", Order.class)
				 .setParameter("state",state)
				 .getResultList();
		return listOrders;
	}

	@Override
	public List<Order> getAll() {
		List<Order> listOrders = new ArrayList<>();
		listOrders = entityManager.createQuery("SELECT ord FROM Order ord ORDER BY creationDate DESC", Order.class).getResultList();
		return listOrders;
	}

	@Override
	public double getCaToday(Date currentDate) {
		double caToday = 0d;
		
		List<Double> results = entityManager.createQuery("SELECT SUM(o.amount) FROM Order o WHERE creationDate = :currentDate").setParameter("currentDate", currentDate).getResultList();
		
		if (results.get(0) != null){
			caToday = results.get(0);
		}
		
		return caToday;
	}

	@Override
	public double getCaMonth(Date currentDate) {
		double caMonth = 0d;
		
		List<Double> results = entityManager.createQuery("SELECT SUM(o.amount) FROM Order o WHERE MONTH(creationDate) = MONTh(:currentDate) AND YEAR(creationDate) = YEAR(:currentDate)").setParameter("currentDate", currentDate).getResultList();
		
		if (results.get(0) != null){
			caMonth = results.get(0);
		}
		
		return caMonth;
	}

	@Override
	public int getNumberOrdersToday(Date currentDate) {
		int numberOrdersToday = 0;
		
		List<Long> results = entityManager.createQuery("SELECT COUNT(o) FROM Order o WHERE creationDate = :currentDate").setParameter("currentDate", currentDate).getResultList();
		
		if (!results.isEmpty()){
			numberOrdersToday = ((Long) results.get(0)).intValue();
		}
		
		return numberOrdersToday;
	}
	
}
