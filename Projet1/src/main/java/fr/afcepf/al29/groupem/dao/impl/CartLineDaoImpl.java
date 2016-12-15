package fr.afcepf.al29.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.CartLineDaoApi;
import fr.afcepf.al29.groupem.entities.CartLine;

@Transactional
@Component
public class CartLineDaoImpl implements CartLineDaoApi {

	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public CartLine createCartLine(CartLine cartLine) {
		entityManager.persist(cartLine);
		return cartLine;
	}

	@Override
	public List<CartLine> getCartLinesByCartId(int cartId) {
		return entityManager.createQuery("SELECT cl FROM CartLine cl INNER JOIN cl.cart c WHERE c.id = :cartId", CartLine.class).setParameter("cartId", cartId).getResultList();
	}

	@Override
	public CartLine getCartLineById(int cartLineId) {
		return entityManager.find(CartLine.class, cartLineId);
	}

	@Override
	public CartLine updateCartLine(CartLine cartLine) {
		entityManager.merge(cartLine);
		return cartLine;
	}

	@Override
	public boolean deleteCartLineById(int cartLineId) {
		entityManager.remove(getCartLineById(cartLineId));
		return (getCartLineById(cartLineId) == null);
	}

}
