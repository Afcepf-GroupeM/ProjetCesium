package fr.afcepf.al29.groupem.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.dao.api.CartDaoApi;
import fr.afcepf.al29.groupem.dao.api.CartLineDaoApi;
import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;

@Transactional
@Component
public class CartBusImpl implements CartBusApi {
	
	@Autowired
	CartDaoApi cartDao;
	
	@Autowired
	CartLineDaoApi cartLineDao;
	
	@Autowired
	UserDaoApi userDao;
	
	@Autowired
	ItemDaoApi itemDao;
	
	
	
	

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

	@Override
	public List<CartLine> getCartLinesByCartId(int cartId) {
		return cartLineDao.getCartLinesByCartId(cartId);
	}

	@Override
	public CartLine createCartLine(int cartId, int itemId, int quantity) {
		CartLine cartLine = new CartLine();
		Item item = itemDao.getItemById(itemId);
		cartLine.setItem(item);
		cartLine.setQuantity(quantity);
		cartLine.setUnitPrice(item.getPrice());
		cartLine.setCart(cartDao.getCartById(cartId));
		return cartLineDao.createCartLine(cartLine);
	}

	@Override
	public boolean destroyCartLine(CartLine cartLine) {
		return cartLineDao.deleteCartLineById(cartLine.getId());
		
	}
	
	@Override
	public CartLine updateCartLine(CartLine cartLine){
		return cartLineDao.updateCartLine(cartLine);
	}
	
	
	
	
	

}
