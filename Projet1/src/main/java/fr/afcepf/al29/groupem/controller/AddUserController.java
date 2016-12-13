package fr.afcepf.al29.groupem.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Civilite;

@Component
@ManagedBean
public class AddUserController {
	
	private int id;
	
	private Civilite civilite;
	private Civilite[] listeCivilite; 
	
	private String lastName;
	private String firstName;
	private Date birthDate;
	private String email;
	private String phone;
	private String password1;
	private String password2;
	private String message;
	
	


	
	
	@Autowired
	private UserBusApi userBus;
	
	@PostConstruct
	public void init(){
		listeCivilite = civilite.getDeclaringClass().getEnumConstants();
	}
	
	public String action(){
		
		return null;		
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getPassword1() {
		return password1;
	}




	public void setPassword1(String password1) {
		this.password1 = password1;
	}




	public String getPassword2() {
		return password2;
	}




	public void setPassword2(String password2) {
		this.password2 = password2;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public Civilite[] getListeCivilite() {
		return listeCivilite;
	}




	public void setListeCivilite(Civilite[] listeCivilite) {
		this.listeCivilite = listeCivilite;
	}
	

	
	
	
}
