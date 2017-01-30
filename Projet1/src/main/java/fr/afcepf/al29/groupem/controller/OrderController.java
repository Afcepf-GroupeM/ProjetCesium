package fr.afcepf.al29.groupem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.RegexValidator;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
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
import fr.afcepf.al29.groupem.entities.User;

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
	
	private String shippingOptionChosen;
	private float shippingCost;
	
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
	private String errorBillingAddress;
	private String errorShippingAddress;
	private String errorLastName;
	private String errorResponseOrch;
	
	private String lastName;
	private int optionLivraison; //in days max to deliver
	
	private String cardTypeChosen;
	private List<String> idTypePayment;
	private HashMap<String, String> labelTypePayment;
	
	private boolean responseOrchOk;
	
	
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
		shippingOptionChosen = getParam("shipid");
		System.out.println("\nOrderController - initAdresse - shippingOptionChosen : " + shippingOptionChosen);
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
		System.out.println("\nOrderController - initPayment - shippingOptionChosen : " + shippingOptionChosen);	
		idTypePayment = new ArrayList<>();
		idTypePayment.add("0"); // Visa
		idTypePayment.add("1"); // Mastercard
		idTypePayment.add("2"); // Amex
		
		labelTypePayment = new HashMap<>();
		labelTypePayment.put("0", "Visa");
		labelTypePayment.put("1", "Master Card");
		labelTypePayment.put("2", "American Express");
		
	}
		
	
	
	
	public void initOrderValidate(ComponentSystemEvent c){
		System.out.println("\nOrderController - initOrderValidate - shippingOptionChosen : " + shippingOptionChosen);	
		switch (shippingOptionChosen) {
		case "1":
			shippingCost = 4.90f;
			break;
		case "2":
			shippingCost = 6.90f;
			break;
		case "3":
			shippingCost = 11.90f;
		break;

		default:
			break;
		}
		
		
		
		
		
		addressBillingChosen = addressBus.getAddressById(Integer.parseInt(addressBillingChosenId));
		addressShippingChosen = addressBus.getAddressById(Integer.parseInt(addressShippingChosenId));
		idOwnerOrder = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		int cartId = cartBus.getCartByUserId(idOwnerOrder).getId();
		cart = cartBus.getCartById(cartId);
		cartLines = cartBus.getCartLinesByCartId(cartId);
		
		
		JSONObject responseOrch = validateOrderOrch();
		responseOrchOk = responseOrch.getInt("returnCode") == 0;
		
		
		if(responseOrchOk) {
			
		
		
		
		
		
		order = new Order();
		order.setAmount(getOrderAmount(cart) + shippingCost);
		
		Carrier carrier = new Carrier();
		
		carrier.setId(1);
		carrier.setName(responseOrch.getString("transporteurName"));
		carrier.setTrackingUrl(responseOrch.getString("trackingUrl"));
		order.setCarrier(carrier);
		order.setCreationDate(new Date());
		OrderState orderState = OrderState.EnPreparation;
		order.setState(orderState);
		order.setTrackingNumber(responseOrch.getString("trackingCode"));
		
		order.setUser(userBus.getUserById(idOwnerOrder));
		
		TypePayment typePayment = null;
		
		switch (cardTypeChosen) {
        case "0":
            typePayment = TypePayment.Visa;
            break;
        case "1":
            typePayment = TypePayment.MasterCard;
            break;
        case "2":
            typePayment = TypePayment.AmericanExpress;
            break;
        default:
            break;
        }
		
		order.setTypePayment(typePayment);
		
		
		
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
		}
		order.setOrderLines(orderLines);
		
		
		for (CartLine cartLine : cartLines) {
			cartBus.destroyCartLine(cartLine);
		}
		cartBus.destroyCartById(cartId);
		
		} else {  // if responseOrchOk false
			errorResponseOrch = responseOrch.getString("message");
		}
		
	}
	
	
	public String validateAddresses(){
		
	    String returnAddress = null;
	    
	    if(addressBillingChosenId.isEmpty()) {
	        errorBillingAddress = "Choisir une adresse de facturation";
	    } else {
	        errorBillingAddress = "";
	    }
	    if(addressShippingChosenId.isEmpty()) {
            errorShippingAddress = "Choisir une adresse de livraison";
        }else {
            errorShippingAddress = "";
        }
	    if(!addressShippingChosenId.isEmpty() && !addressBillingChosenId.isEmpty()) {
	        returnAddress = "payment?faces-redirect=true";
	    }
	    
	    
		return returnAddress;
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
		
		
		return returnAddress;   
//		return "order-validated?faces-redirect=true"; // by pass payement validation
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
	
	
	
	public JSONObject validateOrderOrch() {
		String urlOrch = "http://localhost:8080/OrchestrateurJava/Orchestrateur/validation";
		
		JSONObject request = new JSONObject();
		User user = userBus.getUserById(idOwnerOrder);
		request.put("lastname", lastName);
		request.put("firstname", user.getfirstName());
		request.put("roadNumber", addressShippingChosen.getNumber());
		request.put("complement", addressShippingChosen.getComplement());
		request.put("roadType", addressShippingChosen.getRoadType());
		request.put("roadName", addressShippingChosen.getRoadName());
		request.put("city", addressShippingChosen.getCity());
		request.put("zipcode", addressShippingChosen.getZipcode());
		request.put("country", addressShippingChosen.getCountry());
		request.put("cardNumber", cardNumber);
		int month = Integer.parseInt(cardMonth);
		request.put("monthValidity", month);
		int year = Integer.parseInt(cardYear);
		request.put("yearValidity", year);
		request.put("cryptogram", cardCVV);
		request.put("montant", (getOrderAmount(cart)+ shippingCost));
		request.put("nbOfItems", getCartNbItems(cart));
		
		int nbDays = 10;
		switch (shippingOptionChosen) {
		case "1":
			nbDays = 5;
			break;
		case "2":
			nbDays = 3;
			break;
		case "3":
			nbDays = 2;
		break;

		default:
			nbDays = 5;
			break;
		}
		
		request.put("nbDaysMaxToDeliver", nbDays);
		
		JSONObject response = null;
		try {
			URL url = new URL(urlOrch);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(request.toString());
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String sortie = "";
			while ((line = in.readLine()) != null) {
				sortie += line;
			}
			response = new JSONObject(sortie);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("OrderController - validateOrderOrch MalformedURLException");
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("OrderController - validateOrderOrch JSONException");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("OrderController - validateOrderOrch IOException");
		}		
		return response;
	}
	
	
	public String demoStaticFields(){
		cardNumber = "4716653949676335";
		cardMonth = "11";
		cardYear = "18";
		cardCVV = "007";
		cardTypeChosen = "0";
		lastName="Lagaffe";
		return null;
	}
	
	public String demoFillFields() {
	    String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	    String[] card = {"4716653949676335","4916164152576186","4024007156307968","4532286725151165","4024007185853529","4024007118560977","4929147839867222","4916910893700091","4532378203387609","4539276159659679","4485216286589740","4532072977138294","4716398053510181","4024007157622886","4716751650805877","4485708588672521","4796881650675641","4485795803262402","4485410993604658","4496083796073681","4916837581323508","4532846756964396","4532844311543037","4929258699227140","4024007105842917","4985371269593733","4716969673119130","4485095267705230","4024007177947776","4024007120689319","4916528819162376","4916679465533624","4532426430025839","4532440018831831","4253895303760481","4024007162015464","4539461588904843","4556767923722098","4556596653836954","4024007116929174","4532120399549430","4532106711886295","4716771901300767","4756352081168646","4578470492263388","4024007125411990","4929972226293231","4532755538500032","4532578668636180","4024007150985801","4556229123369824","4716865200903568","4532432703982045","4024007144256962","4716728178186238","4556711300120161","4485257322300973","4485172424803065","4532656734312000","4716255069430673","4929406069517927","4539515636195405","4024007168438884","4916248777668666","4539355194735114","4916496989615211","4532346568181842","4916234299393488","4024007122406308","4539389615045753","4485530315136947","4532360250920482","4024007153539746","4449111138961079","4797729102900886","4716356578532056","4929620058286449","4024007113978513","4024007144074688","4916129837195496","4024007130307928","4556416922396005","4929310424112434","4916543501450484","4556028301990033","4556691083942637","4475791780946515","4916973491605419","4716860808477693","4929936346138196","4916889934292773","4916907748455558","4539895232471446","4024007165640359","4532869699958804","4817312582635482","4532136942681389","4556646196024079","4556245608209400","4929638289008291"};
	    Random rand = new Random();
	    cardNumber = card[rand.nextInt(100 - 1 + 1)];
	    cardMonth = month[rand.nextInt((12 - 1 + 1))];
	    cardYear = String.valueOf(rand.nextInt(16 - 10 +1) + 10);
	    int cvvRand = rand.nextInt(999 - 1 +1) + 1;
	    cardCVV = String.valueOf(cvvRand);
	    if(cvvRand < 100) {
	        if(cvvRand < 10){
	            cardCVV = "00" + cardCVV;
	        } else {
	            cardCVV = "0" + cardCVV;
	        }
	    }
	    cardTypeChosen = String.valueOf(rand.nextInt(3));
	    return null;
	}
	
	
	public Float getOrderAmount(Cart cart) {
	    float totalAmount = 0f;
	    List<CartLine> cartLines = cartBus.getCartLinesByCartId(cart.getId());
	    for (CartLine paramCartLine : cartLines) {
	        totalAmount += paramCartLine.getQuantity() * paramCartLine.getUnitPrice();
        }
	    return totalAmount;
	}
	
	public int getCartNbItems(Cart cart) {
		int nbItems = 0;
		List<CartLine> cartLines = cartBus.getCartLinesByCartId(cart.getId());
		for (CartLine cartLine : cartLines) {
			nbItems += cartLine.getQuantity();
		}
		return nbItems;
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



    /**
     * @return the labelTypePayment
     */
    public HashMap<String, String> getLabelTypePayment() {
        return labelTypePayment;
    }



    /**
     * @param paramLabelTypePayment the labelTypePayment to set
     */
    public void setLabelTypePayment(HashMap<String, String> paramLabelTypePayment) {
        labelTypePayment = paramLabelTypePayment;
    }



    /**
     * @return the errorBillingAddress
     */
    public String getErrorBillingAddress() {
        return errorBillingAddress;
    }



    /**
     * @param paramErrorBillingAddress the errorBillingAddress to set
     */
    public void setErrorBillingAddress(String paramErrorBillingAddress) {
        errorBillingAddress = paramErrorBillingAddress;
    }



    /**
     * @return the errorShippingAddress
     */
    public String getErrorShippingAddress() {
        return errorShippingAddress;
    }



    /**
     * @param paramErrorShippingAddress the errorShippingAddress to set
     */
    public void setErrorShippingAddress(String paramErrorShippingAddress) {
        errorShippingAddress = paramErrorShippingAddress;
    }



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getErrorLastName() {
		return errorLastName;
	}



	public void setErrorLastName(String errorLastName) {
		this.errorLastName = errorLastName;
	}



	public int getOptionLivraison() {
		return optionLivraison;
	}



	public void setOptionLivraison(int optionLivraison) {
		this.optionLivraison = optionLivraison;
	}



	public boolean isResponseOrchOk() {
		return responseOrchOk;
	}



	public void setResponseOrchOk(boolean responseOrchOk) {
		this.responseOrchOk = responseOrchOk;
	}



	public String getErrorResponseOrch() {
		return errorResponseOrch;
	}



	public void setErrorResponseOrch(String errorResponseOrch) {
		this.errorResponseOrch = errorResponseOrch;
	}



	public String getShippingOptionChosen() {
		return shippingOptionChosen;
	}



	public void setShippingOptionChosen(String shippingOptionChosen) {
		this.shippingOptionChosen = shippingOptionChosen;
	}



	public float getShippingCost() {
		return shippingCost;
	}



	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	
	

}
