package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;

public interface CartBusApi {

	Cart createCart(Cart cart);
	Cart getCartById(int cartId);
	boolean destroyCartById(int cartId);
	Cart getCartByUserId(int userId);
	List<CartLine> getCartLinesByCartId(int CartId);
	CartLine createCartLine(int cartId, int itemId, int quantity);
}
