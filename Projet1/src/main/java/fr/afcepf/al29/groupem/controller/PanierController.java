package fr.afcepf.al29.groupem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;

@Component
@ManagedBean
public class PanierController {

	
	private int idOwnerCart = 1 ;    // For testing pupose, we set the user to id=1;
	
	private Cart cart;
	private List<CartLine> cartLines;
	private boolean newItemAdded;
	private boolean isCartEmpty;
	private float totalAmount = 0f;
	private HashMap<Integer, Float> cartLinesSubtotal;
	
	@ManagedProperty(value="#{param.itemIdToRemove}")
	private int itemToDelete;
	
	@Autowired
	private ItemBusApi itemBus;
	
	@Autowired
	private CartBusApi cartBus;
	
	
	
	@PostConstruct
	public void init() {
	
//	Checking if we are trying to add a new item in the cart
	Integer idNewItem = 0;
	Integer quantityNewItem = 0;
	
//	Checking if the parameters are castable into int, if not, error: no new item added.
	try {
		idNewItem = Integer.valueOf(getParam("idItem"));
		quantityNewItem = Integer.valueOf(getParam("quantity"));
	} catch (NumberFormatException e) {
		idNewItem = 0;
		quantityNewItem = 0;
	}
	
	if((idNewItem==0) || (quantityNewItem==0)){
		setNewItemAdded(false);
	}else{
		setNewItemAdded(true);
	}
	
//	Getting the cart to print: If it doesn't exist, it's created
	setCart(cartBus.getCartByUserId(idOwnerCart));
	
// Getting the cart lines
	setCartLines(cartBus.getCartLinesByCartId(idOwnerCart));
	setCartEmpty(cartLines.isEmpty());
	
	if(isCartEmpty){
		setTotalAmount(0f);
	} else {
		for (CartLine cartLine : cartLines) {
			float subtotal = cartLine.getUnitPrice() * cartLine.getQuantity();
			cartLinesSubtotal.put(cartLine.getId(), subtotal);
			totalAmount += subtotal;
		}
		if(newItemAdded){
			int cartId = cart.getId();
			CartLine newCartline = cartBus.createCartLine(cartId, idNewItem, quantityNewItem);
			float subtotal = newCartline.getUnitPrice() * newCartline.getQuantity();
			cartLinesSubtotal.put(newCartline.getId(), subtotal);
			totalAmount += subtotal;
		}
	}
		
	} // End PostConstruct
	
	
	
	// Get the parameters sent in HTTP Request
	protected String getParam(String param) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String result = map.get(param);
		return result;
	}
	
	
	protected Integer getParamId(String param) {
		Integer result = Integer.valueOf(getParam(param));
		return result;
		
	}
	
	
	
	
	
	//methode qui ajoute un article dans le panier.Si l'article est deja dans le panier, on en modifie la quantite.
	public void addItem(Item item) {
		boolean itemFound = false;
		for (CartLine cartLine : cartLines) {
			if (cartLine.getItem().equals(item)) {
				cartLine.setQuantity(cartLine.getQuantity() + 1);
				itemFound = true;
			}
		}
	if (!itemFound)
		cartLines.add(new CartLine(1, item));
	
	}
	
	//methode qui prend en compte les 2 methodes precedentes et qui permettent d'ajouter un article au panier.
	public String addItemToCart() {
		return "contenuPanier?faces-redirect=true";
	}



	//methode qui supprime un article du panier
	public void removeItem(Item item){
		for (CartLine cartLine: cartLines){
			if (cartLine.getItem().equals(item)) {
				cartLines.remove(cartLine);
				return;
			}
		}
	}
	
	//méthode qui supprime un article du panier en prenant en paramètre l'identifiant de l'article (methode precedente + methode de l'identifiant) 
	public String removeItemFromCart() {
		Item item = itemBus.findItem(getParamId("itemId"));
		removeItem(item);
		return null;
	}
	
	
	
	//la methode subTotal se trouve dans CartLine.java
	
	
	
	//méthode qui additionne le prix de chaque article pour retourner le prix total du panier
	//public Float getTotal() {
		//if (cartLines == null || cartLines.isEmpty())
			//return 0f;
		//Float total = 0f;
		
		//for (CartLine cartLine : cartLines) {
			//total += (cartLine.getSubTotal());
		//}
		//return total;
		//}
	
	
	//Implémentation du bouton "valider commande".Aucune action n'est effectuée, seule la redirection vers la prochaine page est lancée.
	public String validerCommande() {
		return "la page suivante";
	}
	
	
	//Implémentation du bouton "poursuivez vos achats".Aucune action n'est effectuée, seule la redirection vers la prochaine page est lancée.
		public String poursuivreAchat() {
			return "la page précédente";
		}
	
	//On reste sur la meme page lorsqu'on modifie la quantité
		public String updateQuantity() {
			return null;
		}
	
	
	
	
	
	//Getters et setters
	public ItemBusApi getItemBus() {
		return itemBus;
	}



	public void setItemBus(ItemBusApi itemBus) {
		this.itemBus = itemBus;
	}


	public List<CartLine> getCartLines() {
		return cartLines;
	}



	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public boolean isNewItemAdded() {
		return newItemAdded;
	}


	public void setNewItemAdded(boolean newItemAdded) {
		this.newItemAdded = newItemAdded;
	}



	public float getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}



	public boolean isCartEmpty() {
		return isCartEmpty;
	}



	public void setCartEmpty(boolean isCartEmpty) {
		this.isCartEmpty = isCartEmpty;
	}



	public HashMap<Integer, Float> getcartLinesSubtotal() {
		return cartLinesSubtotal;
	}



	public void setcartLinesSubtotal(HashMap<Integer, Float> cartLinesSubtotal) {
		this.cartLinesSubtotal = cartLinesSubtotal;
	}



	public int getItemToDelete() {
		return itemToDelete;
	}



	public void setItemToDelete(int itemToDelete) {
		this.itemToDelete = itemToDelete;
	}
	
	
	
	
	
	
	
}
	
	

