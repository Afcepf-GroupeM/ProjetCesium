package fr.afcepf.al29.groupem.business.api;

import fr.afcepf.al29.groupem.entities.Cart;

public interface CartBusApi {

	Cart createCart(Cart cart);
	Cart getCartById(int cartId);
	boolean destroyCartById(int cartId);
	Cart getCartByUserId(int userId);
//	List<CartLines>
}
