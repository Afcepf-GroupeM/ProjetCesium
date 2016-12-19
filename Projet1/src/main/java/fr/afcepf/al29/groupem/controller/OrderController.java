package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Carrier;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
import fr.afcepf.al29.groupem.entities.TypePayment;

@Component
@ManagedBean
public class OrderController {
	
	
	private Order order;
	private Cart cart;
	private List<OrderLine> orderLines;
	private List<CartLine> cartLines;
	private int idOwnerOrder;
	
	
	@Autowired
	private OrderBusApi orderBus;
	
	@Autowired
	private CartBusApi cartBus;
	
	@Autowired
	private UserBusApi userBus;
	
	@Autowired
	private AddressBusApi addressBus;
	
	
	public void initOrderValidate(ComponentSystemEvent c){
		idOwnerOrder = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
//		int cartId = getParamId("cartid");
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
		
		List<Address> addresses = addressBus.getAddressesByUserId(idOwnerOrder);
		Iterator<Address> iter = addresses.iterator();
		Address address = iter.next();
		order.setAdresseFacturation(address);
		order.setAdresseLivraison(address);
		
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
			totalAmount += cartLine.getQuantity() * cartLine.getUnitPrice();
		}
		order.setOrderLines(orderLines);
		order.setAmount(totalAmount);
		
		for (CartLine cartLine : cartLines) {
			cartBus.destroyCartLine(cartLine);
		}
		cartBus.destroyCartById(cartId);
		
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
	
	
	

}
