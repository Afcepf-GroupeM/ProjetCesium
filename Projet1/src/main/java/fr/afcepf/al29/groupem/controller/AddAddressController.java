package fr.afcepf.al29.groupem.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.ComplementAddress;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;

@Component
@ManagedBean
public class AddAddressController {
	
	private int id;
	private User user;
	private boolean isValid = true;
	
	private String name;
	private String number;
	private ComplementAddress complement;
	private ComplementAddress[] complementList;
	private RoadType roadType;
	private RoadType[] roadTypeList;
	private String roadName;
	private String city;
	private String zipcode;
	private String country;
	private boolean billing;
	
	private String message;
	
	@Autowired
	private AddressBusApi addressBus;
	@Autowired
	private UserBusApi userBus;
	
	public void init(ComponentSystemEvent event){
		roadTypeList = RoadType.class.getEnumConstants();
		complementList = ComplementAddress.class.getEnumConstants();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,Object> userLogged = fc.getExternalContext().getSessionMap();
		
		int userId = (Integer) userLogged.get("userid");
		user = userBus.getUserById(userId);
		resetFields();
	}
	
	public String action(){
		RegexValidator nameValidator = new RegexValidator("^[0-9A-Za-z\\s-'éèà]*$", false);
		RegexValidator numberValidator = new RegexValidator("^\\d+$", false);		
		RegexValidator stringValidator = new RegexValidator("^[A-Za-z\\s-'éèà]*$", false);
		RegexValidator zipcodeValidator = new RegexValidator("^\\d{5}$", false);
		
		boolean nameValid = nameValidator.isValid(name);
		boolean numberValid = numberValidator.isValid(number) && (!number.isEmpty());		
		boolean roadNameValid = stringValidator.isValid(roadName) && (!roadName.isEmpty());
		boolean cityValid = stringValidator.isValid(city) && (!city.isEmpty());
		boolean countryValid = stringValidator.isValid(country) && (!country.isEmpty());
		boolean zipcodeValid = zipcodeValidator.isValid(zipcode) && (!zipcode.isEmpty());
		
		boolean complementValid = false;
		for (ComplementAddress ca : complementList){
			if (ca.equals(complement)){
				complementValid = true;
			}
		}
		if (complement.equals(null)){
			complementValid = true;
		}
		
		boolean roadTypeValid = false;
		for (RoadType rt : roadTypeList) {
			if(rt.equals(roadType)){
				roadTypeValid = true;
			}
		}
		
		message = "";
		if(!nameValid){message += "Nom de l'adresse invalide!<br/>";}
		if(!numberValid){message += "Numéro de l'adresse invalide!<br/>";}
		if(!complementValid){message += "Le complement d'adresse est invalide!<br/>";}
		if(!roadTypeValid){message += "Le type de voie est invalide!<br/>";}
		if(!roadNameValid){message += "Le nom de la voie est invalide!<br/>";}
		if(!cityValid){message += "Le nom de ville est invalide!<br/>";}
		if(!zipcodeValid){message += "Le code postal est invalide!<br/>";}
		if(!countryValid){message += "Le pays est invalide!<br/>";}
		
		if(numberValid && roadTypeValid && roadNameValid && cityValid && zipcodeValid && countryValid){
			int addressNumber = Integer.parseInt(number);
			
			addressBus.createAddress(name, addressNumber, complement, roadType, roadName, city, zipcode, country, billing, isValid, user);
			return "myaccount?faces-redirect=true";
		}
		return null;
	}
	
	public void resetFields(){
		name = "";
		number = "";
		complement = null;
		roadType = null;
		roadName = "";
		city = "";
		zipcode = "";
		country = "";
		billing = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public ComplementAddress getComplement() {
		return complement;
	}

	public void setComplement(ComplementAddress complement) {
		this.complement = complement;
	}

	public ComplementAddress[] getComplementList() {
		return complementList;
	}

	public void setComplementList(ComplementAddress[] complementList) {
		this.complementList = complementList;
	}

	public RoadType getRoadType() {
		return roadType;
	}

	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;
	}

	public RoadType[] getRoadTypeList() {
		return roadTypeList;
	}

	public void setRoadTypeList(RoadType[] roadTypeList) {
		this.roadTypeList = roadTypeList;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
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

	public boolean isBilling() {
		return billing;
	}

	public void setBilling(boolean billing) {
		this.billing = billing;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
