package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.CartLine;

public interface CartLineDaoApi {

	CartLine createCartLine(CartLine cartLine);
	List<CartLine> getCartLinesByCartId(int cartId);
	CartLine getCartLineById(int cartLineId);
	CartLine updateCartLine(CartLine cartLine);
	boolean deleteCartLineById(int cartLineId);
}
