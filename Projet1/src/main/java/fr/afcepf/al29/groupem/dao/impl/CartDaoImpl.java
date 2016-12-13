package fr.afcepf.al29.groupem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.CartDaoApi;
import fr.afcepf.al29.groupem.entities.Cart;

@Transactional
@Component
public class CartDaoImpl implements CartDaoApi {

	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public Cart createCart(Cart cart) {
		entityManager.persist(cart);
		return cart;
	}

	@Override
	public Cart getCartById(int cartId) {
		Cart cart = entityManager.find(Cart.class, cartId);
		return cart;
	}

	@Override
	public boolean destroyCartById(int cartId) {
		entityManager.remove(getCartById(cartId));
		return (getCartById(cartId) == null);
	}

	@Override
	public Cart getCartByUserId(int userId) {
		return entityManager.createQuery("SELECT MAX(c) FROM Cart c INNER JOIN c.user usr WHERE usr.id = :userId", Cart.class).setParameter("userId", userId).getSingleResult();
	}
}
