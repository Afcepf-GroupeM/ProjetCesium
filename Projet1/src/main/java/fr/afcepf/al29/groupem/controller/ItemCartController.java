package fr.afcepf.al29.groupem.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;

@SessionScoped
@Component
@ManagedBean(name="itemCartController")
public class ItemCartController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1099785566722272315L;

	@Autowired
    private ItemBusApi itemBus;

    @Autowired
    private CartBusApi cartBus;

    private Item item;
    private List<Item> items;
    private List<String> quantityList;
    private String quantity;

    private String imagePath = "/images/items/";

    private String messageIncorrectCart = "";

    private Integer idOwnerCart ;  

    private Cart cart;
    private List<CartLine> cartLines;
    private boolean newItemAdded;
    private boolean isCartEmpty;
    private float totalAmount = 0f;
    private HashMap<Integer, Float> cartLinesSubtotal = new HashMap<>();
    int idNewItem;
    int quantityNewItem;
    
    private String shippingOptionChosen = "1";
    private List<String> shippingOptions ;
    private HashMap<String, String> shippingOptionsMap;
    private HashMap<String, Float> shippingOptionsPriceMap;




    public void initCartDetail(ComponentSystemEvent c1){
    	shippingOptions = new ArrayList<>();
    	shippingOptions.add("1");
    	shippingOptions.add("2");
    	shippingOptions.add("3");
    	
    	shippingOptionsMap = new HashMap<>(); 
    	shippingOptionsMap.put("1", "Standard - 5 jours ouvrés - 4€90");
    	shippingOptionsMap.put("2", "Rapide - 3 jours ouvrés - 6€90");
    	shippingOptionsMap.put("3", "24h Garanti - 11€90");
    	
    	shippingOptionsPriceMap  = new HashMap<>();
    	shippingOptionsPriceMap.put("1", 4.90f);
    	shippingOptionsPriceMap.put("2", 6.90f);
    	shippingOptionsPriceMap.put("3", 11.90f);


        idOwnerCart = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        totalAmount = shippingOptionsPriceMap.get(shippingOptionChosen);

        if(idOwnerCart != null){
            setCart(cartBus.getCartByUserId(idOwnerCart));
            setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
            setIsCartEmpty(cartLines.isEmpty());		    
        } else {		    		  
            setCart(cartBus.getCartBySessionId(getSessionUuid()));
            setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
            setIsCartEmpty(cartLines.isEmpty());
            
        }

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
        item = itemBus.findItem(getParamId("itemId"));
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
        try {
            idOwnerCart = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        } catch (NullPointerException e2) {
            idOwnerCart = null;
        }
//        setCart(cartBus.getCartByUserId(idOwnerCart));
//        setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
//        setIsCartEmpty(cartLines.isEmpty());
        
        if(idOwnerCart != null){
            setCart(cartBus.getCartByUserId(idOwnerCart));
            setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
            setIsCartEmpty(cartLines.isEmpty());            
        } else {                      
            setCart(cartBus.getCartBySessionId(getSessionUuid()));
            setCartLines(cartBus.getCartLinesByCartId(cart.getId()));
            setIsCartEmpty(cartLines.isEmpty());
            
        }
        
        
        
        


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
            
            if(idOwnerCart != null){
                setCart(cartBus.getCartByUserId(idOwnerCart));
            } else {
                setCart(cartBus.getCartBySessionId(getSessionUuid()));
            }
           
            int cartId = cart.getId();
            boolean itemAlreadyInCart = false;
            if(!isCartEmpty){
                for (CartLine cartLine : cart.getCartLines()){
                    if(cartLine.getItem().getId() == idNewItem){
                        cartLine.setQuantity(cartLine.getQuantity() + quantityNewItem);
                        cartBus.updateCartLine(cartLine);
                        float subtotal = cartLine.getUnitPrice() * cartLine.getQuantity();
                        cartLinesSubtotal.put(cartLine.getId(), subtotal);
                        itemAlreadyInCart = true;
                    }
                }
            }
            if(!itemAlreadyInCart){			
                CartLine newCartline = cartBus.createCartLine(cartId, idNewItem, quantityNewItem);
                float subtotal = newCartline.getUnitPrice() * newCartline.getQuantity();
                cartLinesSubtotal.put(newCartline.getId(), subtotal);
                totalAmount += subtotal;
            }
        }

        return "panier?faces-redirect=true";
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


    public String validateCart(){
        String returnPage = "address-choice?faces-redirect=true";
        int lineInError = 1;
        for (CartLine cartLine : cartLines) {
            Item itemToCheck = cartLine.getItem();
            Item itemInDB = itemBus.getItemById(itemToCheck.getId());
            if(itemInDB.getStock() < cartLine.getQuantity()){
                messageIncorrectCart += "Erreur ligne " + lineInError 
                        + " : Il ne reste que " + itemInDB.getStock() + " " + itemInDB.getName() +" disponible(s)."
                        + " Vous en avez choisi " + (cartLine.getQuantity() - itemInDB.getStock()) + " de trop!\n";
                lineInError++;
                returnPage = null;
            }
        }

        return returnPage;
    }
    
    
    public void ajaxChangeShipOption (AjaxBehaviorEvent event) {
  	
    	totalAmount += shippingOptionsPriceMap.get(shippingOptionChosen);
    }



    protected String getParam(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String result = map.get(param);

        return result;
    }


    protected String getSessionUuid() {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(false); //false return null if session doesn't exists
        String uuid = session.getId();
        return uuid ;
    }

    protected Integer getParamId(String param) {
        Integer result = Integer.valueOf(getParam(param));

        return result;

    }

    public String doFindItem() {
        item = itemBus.findItem(getParamId("itemId"));
        return "article?faces-redirect=true";


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

    public boolean getIsCartEmpty() {
        return isCartEmpty;
    }

    public void setIsCartEmpty(boolean isCartEmpty) {
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

    public Integer getIdOwnerCart() {
        return idOwnerCart;
    }

    public void setIdOwnerCart(Integer idOwnerCart) {
        this.idOwnerCart = idOwnerCart;
    }

    public String getMessageIncorrectCart() {
        return messageIncorrectCart;
    }

    public void setMessageIncorrectCart(String messageIncorrectCart) {
        this.messageIncorrectCart = messageIncorrectCart;
    }

    public void setCartEmpty(boolean isCartEmpty) {
        this.isCartEmpty = isCartEmpty;
    }

	public String getShippingOptionChosen() {
		return shippingOptionChosen;
	}

	public void setShippingOptionChosen(String shippingOptionChosen) {
		this.shippingOptionChosen = shippingOptionChosen;
	}

	public List<String> getShippingOptions() {
		return shippingOptions;
	}

	public void setShippingOptions(List<String> shippingOptions) {
		this.shippingOptions = shippingOptions;
	}

	public HashMap<String, String> getShippingOptionsMap() {
		return shippingOptionsMap;
	}

	public void setShippingOptionsMap(HashMap<String, String> shippingOptionsMap) {
		this.shippingOptionsMap = shippingOptionsMap;
	}

	public HashMap<String, Float> getShippingOptionsPriceMap() {
		return shippingOptionsPriceMap;
	}

	public void setShippingOptionsPriceMap(HashMap<String, Float> shippingOptionsPriceMap) {
		this.shippingOptionsPriceMap = shippingOptionsPriceMap;
	}

	


}
