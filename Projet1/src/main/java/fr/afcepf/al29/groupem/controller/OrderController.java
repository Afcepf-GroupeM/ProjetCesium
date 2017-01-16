package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Carrier;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
import fr.afcepf.al29.groupem.entities.TypePayment;

@Scope("session")
@Component
@ManagedBean
public class OrderController {
	
	
	private Order order;
	private Cart cart;
	private List<OrderLine> orderLines;
	private List<CartLine> cartLines;
	private int idOwnerOrder;
	
	private List<Address> addresses;
	private List<Address> billingAddresses;
	
	private Address addressBillingChosen;
	private Address addressShippingChosen;
	private String addressShippingChosenId;
	private String addressBillingChosenId;
	
	private String cardNumber;
	private String cardMonth;
	private String cardYear;
	private String cardCVV;
	private String errorCardNumber;
	private String errorCardDate;
	private String errorCardCVV;
	
	private String cardTypeChosen;
	private List<String> idTypePayment;
	
	
	@Autowired
	private OrderBusApi orderBus;
	
	@Autowired
	private CartBusApi cartBus;
	
	@Autowired
	private UserBusApi userBus;
	
	@Autowired
	private AddressBusApi addressBus;
	
	@Autowired
	private ItemBusApi itemBus;
	
	
	public void initAddressChoice(ComponentSystemEvent c2){
		idOwnerOrder = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		addresses = addressBus.getAddressesByUserId(idOwnerOrder);
		billingAddresses = new ArrayList<>();
		for (Address address : addresses) {
			if(address.isBilling()){
				billingAddresses.add(address);
			}
		}
	}

		
		
	public void initPayment(ComponentSystemEvent c3){
		idTypePayment = new ArrayList<>();
		idTypePayment.add("0"); // Visa
		idTypePayment.add("1"); // Mastercard
		idTypePayment.add("2"); // Amex
		
		
	}
		
	
	
	
	public void initOrderValidate(ComponentSystemEvent c){
		idOwnerOrder = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		int cartId = cartBus.getCartByUserId(idOwnerOrder).getId();
		cart = cartBus.getCartById(cartId);
		cartLines = cartBus.getCartLinesByCartId(cartId);
		order = new Order();
		
		Carrier carrier = new Carrier();
		float totalAmount = 0f;
		carrier.setId(1);
		carrier.setName("UPS");
		carrier.setTrackingUrl("http://www.ups.fr/tracking");
		order.setCarrier(carrier);
		order.setAmount(totalAmount);
		order.setCreationDate(new Date());
		OrderState orderState = OrderState.EnPreparation;
		order.setState(orderState);
		order.setTrackingNumber("ABC1234567489");
		
		order.setUser(userBus.getUserById(idOwnerOrder));
		order.setTypePayment(TypePayment.MasterCard);
		
		addressBillingChosen = addressBus.getAddressById(Integer.parseInt(addressBillingChosenId));
		addressShippingChosen = addressBus.getAddressById(Integer.parseInt(addressShippingChosenId));
		
		order.setAdresseFacturation(addressBillingChosen);
		order.setAdresseLivraison(addressShippingChosen);
		
		order = orderBus.createOrder(order);
		
		orderLines = new ArrayList<>();
		
		for (CartLine cartLine : cartLines) {
			OrderLine orderL = new OrderLine();
			orderL.setOrder(order);
			orderL.setQuantity(cartLine.getQuantity());
			orderL.setUnitPrice(cartLine.getUnitPrice());
			orderL.setItem(cartLine.getItem());
			orderL = orderBus.createOrderLine(orderL);
			orderLines.add(orderL);
			Item itemModified = cartLine.getItem();
			itemModified.setStock(itemModified.getStock() - cartLine.getQuantity());
			itemBus.updateItem(itemModified);
			totalAmount += cartLine.getQuantity() * cartLine.getUnitPrice();
		}
		order.setOrderLines(orderLines);
		order.setAmount(totalAmount);
		
		for (CartLine cartLine : cartLines) {
			cartBus.destroyCartLine(cartLine);
		}
		cartBus.destroyCartById(cartId);
		
	}
	
	
	public String validateAddresses(){
		return "payment?faces-redirect=true";
	}
	
	
	public String validatePayment(){
		errorCardCVV = "";
		errorCardDate = "";
		errorCardNumber = "";
		String returnAddress = null;
		RegexValidator cardValidator = new RegexValidator("[0-9]{16}");
		RegexValidator monthValidator = new RegexValidator("[0][1-9]|[1][0-2]");
		RegexValidator yearValidator = new RegexValidator("[0-9]{2}");
		RegexValidator cvvValidator = new RegexValidator("[0-9]{3}");
		
		boolean isCardValid = cardValidator.isValid(cardNumber) && checkLuhn(cardNumber);
		boolean isDateValid = monthValidator.isValid(cardMonth) && yearValidator.isValid(cardYear);
		boolean isCvvValid = cvvValidator.isValid(cardCVV);
		
		if(!isCardValid){
			errorCardNumber = "Numero de carte invalide.";
		}
		if(!isDateValid){
			errorCardDate = "Date d'expiration invalide.";
		}
		if(!isCvvValid){
			errorCardCVV = "Cryptogramme invalide.";
		}
		
		if(isCardValid && isDateValid && isCvvValid){
			returnAddress = "order-validated?faces-redirect=true";
		} else {
			returnAddress = null;
		}
		
		
//		return returnAddress;  by pass payement validation
		return "order-validated?faces-redirect=true"; 
	}
	
	
	public boolean checkLuhn(String cardNumber){
		int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--){
        	int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate){
            	n *= 2;
                if (n > 9){
                	n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
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



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Cart getCart() {
		return cart;
	}



	public void setCart(Cart cart) {
		this.cart = cart;
	}



	public List<OrderLine> getOrderLines() {
		return orderLines;
	}



	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}



	public List<CartLine> getCartLines() {
		return cartLines;
	}



	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}



	public int getIdOwnerOrder() {
		return idOwnerOrder;
	}



	public void setIdOwnerOrder(int idOwnerOrder) {
		this.idOwnerOrder = idOwnerOrder;
	}



	public OrderBusApi getOrderBus() {
		return orderBus;
	}



	public void setOrderBus(OrderBusApi orderBus) {
		this.orderBus = orderBus;
	}



	public CartBusApi getCartBus() {
		return cartBus;
	}



	public void setCartBus(CartBusApi cartBus) {
		this.cartBus = cartBus;
	}



	public UserBusApi getUserBus() {
		return userBus;
	}



	public void setUserBus(UserBusApi userBus) {
		this.userBus = userBus;
	}



	public AddressBusApi getAddressBus() {
		return addressBus;
	}



	public void setAddressBus(AddressBusApi addressBus) {
		this.addressBus = addressBus;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	public List<Address> getBillingAddresses() {
		return billingAddresses;
	}


	public void setBillingAddresses(List<Address> billingAddresses) {
		this.billingAddresses = billingAddresses;
	}


	public Address getAddressBillingChosen() {
		return addressBillingChosen;
	}


	public void setAddressBillingChosen(Address addressBillingChosen) {
		this.addressBillingChosen = addressBillingChosen;
	}


	public Address getAddressShippingChosen() {
		return addressShippingChosen;
	}


	public void setAddressShippingChosen(Address addressShippingChosen) {
		this.addressShippingChosen = addressShippingChosen;
	}



	public String getAddressShippingChosenId() {
		return addressShippingChosenId;
	}



	public void setAddressShippingChosenId(String addressShippingChosenId) {
		this.addressShippingChosenId = addressShippingChosenId;
	}



	public String getAddressBillingChosenId() {
		return addressBillingChosenId;
	}



	public void setAddressBillingChosenId(String addressBillingChosenId) {
		this.addressBillingChosenId = addressBillingChosenId;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public String getCardMonth() {
		return cardMonth;
	}



	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}



	public String getCardYear() {
		return cardYear;
	}



	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}



	public String getCardCVV() {
		return cardCVV;
	}



	public void setCardCVV(String cardCVV) {
		this.cardCVV = cardCVV;
	}



	public String getErrorCardNumber() {
		return errorCardNumber;
	}



	public void setErrorCardNumber(String errorCardNumber) {
		this.errorCardNumber = errorCardNumber;
	}



	public String getErrorCardDate() {
		return errorCardDate;
	}



	public void setErrorCardDate(String errorCardDate) {
		this.errorCardDate = errorCardDate;
	}



	public String getErrorCardCVV() {
		return errorCardCVV;
	}



	public void setErrorCardCVV(String errorCardCVV) {
		this.errorCardCVV = errorCardCVV;
	}



	public String getCardTypeChosen() {
		return cardTypeChosen;
	}



	public void setCardTypeChosen(String cardTypeChosen) {
		this.cardTypeChosen = cardTypeChosen;
	}



    /**
     * @return the idTypePayment
     */
    public List<String> getIdTypePayment() {
        return idTypePayment;
    }



    /**
     * @param paramIdTypePayment the idTypePayment to set
     */
    public void setIdTypePayment(List<String> paramIdTypePayment) {
        idTypePayment = paramIdTypePayment;
    }



    /**
     * @return the itemBus
     */
    public ItemBusApi getItemBus() {
        return itemBus;
    }



    /**
     * @param paramItemBus the itemBus to set
     */
    public void setItemBus(ItemBusApi paramItemBus) {
        itemBus = paramItemBus;
    }
	
	
	

}
