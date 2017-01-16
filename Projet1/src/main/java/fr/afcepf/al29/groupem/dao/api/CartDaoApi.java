package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.Cart;

public interface CartDaoApi {
	
	
	Cart createCart(Cart cart);
	Cart getCartById(int cartId);
	boolean destroyCartById(int cartId);
	Cart getCartByUserId(int userId);
    Cart getCartBySessionId(String paramSessionId);

}
