package fr.afcepf.al29.groupem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
import fr.afcepf.al29.groupem.entities.User;

@Scope("session")
@Component
@ManagedBean
public class EspaceClientController {
	private String messageInfoPerson;
	private User userConnect;
	
	private List<Order> listOrder;
	
	private String messageOrdering;		
	private List<Order> listOrdering;
	private Order ordering;
	private OrderLine orderingLine;
	
	private String messageOldOrder;
	private List<Order> listOldOrder;
	private Order oldOrder;
	private OrderLine oldOrderLine;
	
	private String messageAddress;
	private Address address;
	private List<Address> listAddress;
	
	//variables from WSLogi
	private String trackingCode;
	private String dateDemande;
	private String datePriseEnCharge;
	private String dateLivraison;
	private String lastName;
	private String firstName;
	private int numero;
	private String complement;
	private String typeVoie;
	private String nomVoie;
	private String city;
	private String zipcode;
	private String country;
	private String transporteurName;
	private List<Map<String, String>> statutLines;
	
	@Autowired
	UserBusApi userBusApi;
	@Autowired
	OrderBusApi orderBusApi;
	@Autowired	
	AddressBusApi addressBusApi;
	
	public void init(ComponentSystemEvent e){
		int idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		
		messageInfoPerson ="";
		
		userConnect =  userBusApi.getUserById(idUser);
		messageInfoPerson = "";
		
		listOrder = new ArrayList<>();
		listOrder = orderBusApi.getOrderByUserId(idUser);
		
		listOldOrder = new ArrayList<>();
		listOrdering = new ArrayList<>();
		for(Order order:listOrder){
			OrderState state = order.getState();
			if(state==OrderState.Livree ||state==OrderState.RemboursementClient){
				listOldOrder.add(order);				
			}else{
				listOrdering.add(order);
			}
		}
		
		if(listOrdering.size()==0){
			messageOrdering = "Vous n'avez pas de commande en cours";
		}else{
			messageOrdering = "";
		}
		
		if(listOldOrder.size()==0){
			messageOldOrder = "Vous n'avez pas de commande historique";
		}else{
			messageOrdering = "";
		}
		
		messageAddress="";
		
	}
	
	public void initStatutViewer(ComponentSystemEvent e){
		int orderId = Integer.parseInt(getParam("orderId"));
		trackingCode = getParam("trackingCode");
		String urlWSLogi = "http://localhost:8080/WebServiceLogistique/StatutLivraison/send";
		JSONObject returnWSLogiJson = null;
		
		ordering = orderBusApi.getOrderById(orderId);
		
		JSONObject statutRequestJson = new JSONObject();
		statutRequestJson.put("trackingCode", trackingCode);
		
		System.out.println("\n----------\n.: Call: WS Logistique - sendStatut :.");
		
		try {
			URL url = new URL(urlWSLogi);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			System.out.println("Request sent to WS Logistique : " + statutRequestJson.toString());
			out.write(statutRequestJson.toString());
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String sortie = "";
			
			while ((line = in.readLine()) != null) {
				sortie += line;
			}
			
			System.out.println("Response from WS Logistique : " + sortie );
			
			try {
				returnWSLogiJson = new JSONObject(sortie);
				
				trackingCode = returnWSLogiJson.getString("trackingCode");
				dateDemande = returnWSLogiJson.getString("dateDemande");
				datePriseEnCharge = returnWSLogiJson.getString("datePriseEnCharge");
				dateLivraison = returnWSLogiJson.getString("dateLivraison");
				lastName = returnWSLogiJson.getString("lastName");
				firstName = returnWSLogiJson.getString("firstName");
				transporteurName = returnWSLogiJson.getString("transporteurName");
				
				numero = returnWSLogiJson.getInt("numero");
				
				//si complement null alors renvoie une erreur "Null key."
				/*if (returnWSLogiJson.getString(complement) != null){
					complement = returnWSLogiJson.getString(complement);
				}
				else{
					complement = "";
				}
				
				*<h:outputText value="Complement: #{espaceClientController.complement}"/>
						<br/>
				*/
				typeVoie = returnWSLogiJson.getString("typeVoie");
				nomVoie = returnWSLogiJson.getString("nomVoie");
				city = returnWSLogiJson.getString("city");
				zipcode = returnWSLogiJson.getString("zipcode");
				country = returnWSLogiJson.getString("country");
				
				statutLines = new ArrayList<>();
				JSONArray array = returnWSLogiJson.getJSONArray("statutLines");
				
				for (int i = 0; i < array.length(); i++) {
				    String dateUpdate = array.getJSONObject(i).getString("dateUpdate");
				    String locationUpdate = array.getJSONObject(i).getString("locationUpdate");
				    String detailsUpdate = array.getJSONObject(i).getString("detailsUpdate");
				    
				    Map<String, String> sLinesInfos = new HashMap<>();
				    sLinesInfos.put("dateUpdate", dateUpdate);
				    sLinesInfos.put("locationUpdate", locationUpdate);
				    sLinesInfos.put("detailsUpdate", detailsUpdate);
				    
				    statutLines.add(sLinesInfos);
				}
				
			} catch (JSONException err) {
				System.out.println("Error with WS Bank :\n\tJSONException on response from WS Logistique");
				err.printStackTrace();
			}
		} catch (MalformedURLException err) {
			System.out.println("Error with WS Bank :\n\tMalformedURLException");
			err.printStackTrace();
		} catch (IOException err) {
			System.out.println("Error with WS Bank :\n\tIOException");
			err.printStackTrace();
		}
	}
	
	public String goToStatutViewer(){
		int orderId = Integer.parseInt(getParam("orderId"));
		String trackingCode = getParam("trackingCode");
		
		return "statutViewer?faces-redirect=true&orderId=" + orderId + "&trackingCode=" + trackingCode;
	}
	
	public String getParam(String param){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String result = params.get(param);
		
		return result;
	}
	
	public String userModify(){
		return "/account-modify.jsf?faces-redirect = true";
	}
	
	public String getMessageInfoPerson() {
		return messageInfoPerson;
	}
	public void setMessageInfoPerson(String messageInfoPerson) {
		this.messageInfoPerson = messageInfoPerson;
	}
	public User getUserConnect() {
		return userConnect;
	}
	public void setUserConnect(User userConnect) {
		this.userConnect = userConnect;
	}
	public List<Order> getListOrder() {
		return listOrder;
	}
	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}
	public String getMessageOrdering() {
		return messageOrdering;
	}
	public void setMessageOrdering(String messageOrdering) {
		this.messageOrdering = messageOrdering;
	}
	public List<Order> getListOrdering() {
		return listOrdering;
	}
	public void setListOrdering(List<Order> listOrdering) {
		this.listOrdering = listOrdering;
	}
	public Order getOrdering() {
		return ordering;
	}
	public void setOrdering(Order ordering) {
		this.ordering = ordering;
	}
	public OrderLine getOrderingLine() {
		return orderingLine;
	}
	public void setOrderingLine(OrderLine orderingLine) {
		this.orderingLine = orderingLine;
	}
	public List<Order> getListOldOrder() {
		return listOldOrder;
	}
	public void setListOldOrder(List<Order> listOldOrder) {
		this.listOldOrder = listOldOrder;
	}
	public Order getOldOrder() {
		return oldOrder;
	}
	public void setOldOrder(Order oldOrder) {
		this.oldOrder = oldOrder;
	}
	public OrderLine getOldOrderLine() {
		return oldOrderLine;
	}

	public void setOldOrderLine(OrderLine oldOrderLine) {
		this.oldOrderLine = oldOrderLine;
	}

	public String getMessageAddress() {
		return messageAddress;
	}
	public void setMessageAddress(String messageAddress) {
		this.messageAddress = messageAddress;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Address> getListAddress() {
		return listAddress;
	}
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}
	public String getMessageOldOrder() {
		return messageOldOrder;
	}
	public void setMessageOldOrder(String messageOldOrder) {
		this.messageOldOrder = messageOldOrder;
	}
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public String getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}
	public String getDatePriseEnCharge() {
		return datePriseEnCharge;
	}
	public void setDatePriseEnCharge(String datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}
	public String getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getTypeVoie() {
		return typeVoie;
	}

	public void setTypeVoie(String typeVoie) {
		this.typeVoie = typeVoie;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTransporteurName() {
		return transporteurName;
	}
	public void setTransporteurName(String transporteurName) {
		this.transporteurName = transporteurName;
	}
	public List<Map<String, String>> getStatutLines() {
		return statutLines;
	}
	public void setStatutLines(List<Map<String, String>> statutLines) {
		this.statutLines = statutLines;
	}
	
}
