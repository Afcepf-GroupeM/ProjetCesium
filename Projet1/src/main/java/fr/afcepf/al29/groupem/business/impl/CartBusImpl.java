package fr.afcepf.al29.groupem.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.dao.api.CartDaoApi;
import fr.afcepf.al29.groupem.dao.api.CartLineDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Cart;

@Transactional
@Component
public class CartBusImpl implements CartBusApi {
	
	@Autowired
	CartDaoApi cartDao;
	
	@Autowired
	CartLineDaoApi cartLineDao;
	
	@Autowired
	UserDaoApi userDao;
	
	
	
	

	@Override
	public Cart createCart(Cart cart) {

		return cartDao.createCart(cart);
	}

	@Override
	public Cart getCartByUserId(int userId) {
		Cart cart = cartDao.getCartByUserId(userId);
		if(cart == null){
			Cart newCart = new Cart();
			newCart.setUser(userDao.getUserById(userId));
			newCart.setCreationDate(new Date());
			cart = createCart(newCart);
		}
		return cart;
	}

	@Override
	public boolean destroyCartById(int cartId) {
		return cartDao.destroyCartById(cartId);
	}

	@Override
	public Cart getCartById(int cartId) {
		return cartDao.getCartById(cartId);
	}
	
	
	
	
	

}
