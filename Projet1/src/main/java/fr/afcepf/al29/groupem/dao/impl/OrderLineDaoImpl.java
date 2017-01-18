package fr.afcepf.al29.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.OrderLineDaoApi;
import fr.afcepf.al29.groupem.entities.OrderLine;

@Transactional
@Component
public class OrderLineDaoImpl implements OrderLineDaoApi {

	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public OrderLine createOrderLine(OrderLine orderLine) {
		entityManager.persist(orderLine);
		return orderLine;
	}

	@Override
	public List<OrderLine> getOrderLinesByOrderId(int orderId) {
		return entityManager.createQuery("SELECT ol FROM OrderLine ol INNER JOIN ol.order o WHERE o.id = :orderId", OrderLine.class).setParameter("orderId", orderId).getResultList();
	}

	@Override
	public OrderLine getOrderLineById(int orderLineId) {
		return entityManager.find(OrderLine.class, orderLineId);
	}

	@Override
	public OrderLine updateOrderLine(OrderLine orderLine) {
		entityManager.merge(orderLine);
		return orderLine;
	}

	@Override
	public boolean deleteOrderLineById(int orderLineId) {
		entityManager.remove(getOrderLineById(orderLineId));
		return (getOrderLineById(orderLineId) == null);
	}

	@Override
	public List<OrderLine> getOrderLinesByItemId(int itemId) {
		return entityManager.createQuery("SELECT ol FROM OrderLine ol INNER JOIN ol.item i WHERE i.id = :itemId", OrderLine.class).setParameter("itemId", itemId).getResultList();
	}

}
