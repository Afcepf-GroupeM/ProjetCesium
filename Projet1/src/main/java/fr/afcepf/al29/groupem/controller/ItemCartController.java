package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;


@Component
@ManagedBean
public class ItemCartController {
	
	@Autowired
	private ItemBusApi itemBus;
	
	@Autowired
	private CartBusApi cartBus;
	
	private Item item;
	private List<Item> items;
	private List<String> quantityList;
	private String quantity;
	
	private String imagePath = "/images/items/";
	
	
	private int idOwnerCart = 1 ;    // For testing pupose, we set the user to id=1;
	
	private Cart cart;
	private List<CartLine> cartLines;
	private boolean newItemAdded;
	private boolean isCartEmpty;
	private float totalAmount = 0f;
	private HashMap<Integer, Float> cartLinesSubtotal = new HashMap<>();
	int idNewItem;
	int quantityNewItem;
	
	
	
	
	public void initCartDetail(ComponentSystemEvent c1){
		totalAmount = 0f;
		setCart(cartBus.getCartByUserId(idOwnerCart));
		setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
		setCartEmpty(cartLines.isEmpty());
			
			if(isCartEmpty){
				setTotalAmount(0f);
			} else {
				for (CartLine cartLine : cartLines) {
					float subtotal = cartLine.getUnitPrice() * cartLine.getQuantity();
					cartLinesSubtotal.put(cartLine.getId(), subtotal);
					totalAmount += subtotal;
				}
			}
		
	}
	
	public void initItemDetail(ComponentSystemEvent c){
		quantity = "1";
		int stock = item.getStock();
		List<String> tabQuantity = new ArrayList<>();
		if(stock <= 10){
			int i = 1;
			while(i <= stock){
				tabQuantity.add(String.valueOf(i));
				i++;
			}
		} else{
			tabQuantity.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10"));
		}
		quantityList = new ArrayList<>();
		quantityList = tabQuantity;
		
	}
	
	
	
	
	
public String addItemToCart() {
			
		String idNewItemString;
		String quantityNewItemString;
		try {		
			idNewItemString = String.valueOf(item.getId());
			quantityNewItemString = String.valueOf(quantity);

			
		} catch (NullPointerException e1) {
			idNewItemString = "";
			quantityNewItemString = "";
		}
		try {
			idNewItem = Integer.valueOf(idNewItemString);
			quantityNewItem = Integer.valueOf(quantityNewItemString);
		} catch (NumberFormatException e) {
			idNewItem = 0;
			quantityNewItem = 0;
		}
		
		if((idNewItem==0) || (quantityNewItem==0)){
			setNewItemAdded(false);
		}else{
			setNewItemAdded(true);
		}
		
		if(newItemAdded){
			setCart(cartBus.getCartByUserId(idOwnerCart));
			int cartId = cart.getId();
			boolean itemAlreadyInCart = false;
			for (CartLine cartLine : cart.getCartLines()){
				if(cartLine.getItem().getId() == idNewItem){
					cartLine.setQuantity(cartLine.getQuantity() + quantityNewItem);
					cartBus.updateCartLine(cartLine);
					float subtotal = cartLine.getUnitPrice() * cartLine.getQuantity();
					cartLinesSubtotal.put(cartLine.getId(), subtotal);
					itemAlreadyInCart = true;
				}
			}
			if(!itemAlreadyInCart){			
				CartLine newCartline = cartBus.createCartLine(cartId, idNewItem, quantityNewItem);
				float subtotal = newCartline.getUnitPrice() * newCartline.getQuantity();
				cartLinesSubtotal.put(newCartline.getId(), subtotal);
				totalAmount += subtotal;
			}
		}

		return "contenuPanier?faces-redirect=true";
	}

	public String removeItemFromCart() {
		Item itemToRemove = itemBus.getItemById(Integer.valueOf(getParam("itemIdToRemove")));
		Iterator<CartLine> iterator = cartLines.iterator();
		while(iterator.hasNext()){
			CartLine cartLine = iterator.next();
			if (cartLine.getItem().getId() == itemToRemove.getId()) {
				iterator.remove();
				cartBus.destroyCartLine(cartLine);  // Return true if succeeded
			}
		}
		return null;
	}
	

	
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
	
	public String doFindItem() {
		item = itemBus.findItem(getParamId("itemId"));
		return "detailArticle?faces-redirect=true";
				
		
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public List<String> getQuantityList() {
		return quantityList;
	}


	public void setQuantityList(List<String> quantityList) {
		this.quantityList = quantityList;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ItemBusApi getItemBus() {
		return itemBus;
	}

	public void setItemBus(ItemBusApi itemBus) {
		this.itemBus = itemBus;
	}

	public CartBusApi getCartBus() {
		return cartBus;
	}

	public void setCartBus(CartBusApi cartBus) {
		this.cartBus = cartBus;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}

	public boolean isNewItemAdded() {
		return newItemAdded;
	}

	public void setNewItemAdded(boolean newItemAdded) {
		this.newItemAdded = newItemAdded;
	}

	public boolean isCartEmpty() {
		return isCartEmpty;
	}

	public void setCartEmpty(boolean isCartEmpty) {
		this.isCartEmpty = isCartEmpty;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public HashMap<Integer, Float> getCartLinesSubtotal() {
		return cartLinesSubtotal;
	}

	public void setCartLinesSubtotal(HashMap<Integer, Float> cartLinesSubtotal) {
		this.cartLinesSubtotal = cartLinesSubtotal;
	}

	public int getIdNewItem() {
		return idNewItem;
	}

	public void setIdNewItem(int idNewItem) {
		this.idNewItem = idNewItem;
	}

	public int getQuantityNewItem() {
		return quantityNewItem;
	}

	public void setQuantityNewItem(int quantityNewItem) {
		this.quantityNewItem = quantityNewItem;
	}

	public int getIdOwnerCart() {
		return idOwnerCart;
	}

	public void setIdOwnerCart(int idOwnerCart) {
		this.idOwnerCart = idOwnerCart;
	}
	
	
	

}
