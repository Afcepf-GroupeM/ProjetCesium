package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.Cart;

public interface CartDaoApi {
	
	
	boolean createCart(Cart cart);
	Cart getCartById(int cartId);
	Cart updateCartById(int cartId);
	boolean destroyCartById(int cartId);
	
	Cart getCartByUserId(int userId);

}
