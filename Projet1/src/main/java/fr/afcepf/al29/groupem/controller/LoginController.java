package fr.afcepf.al29.groupem.controller;

import javax.faces.bean.ManagedBean;

import org.apache.commons.validator.routines.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.UserBusApi;

@Component
@ManagedBean
public class LoginController {
	
	private String login;
	private String password;
	private String errorMessage;
	
	@Autowired
	private UserBusApi userBus; 
	
	
	public String action(){
		EmailValidator emailValidator = EmailValidator.getInstance();
		boolean isLoginValid = emailValidator.isValid(login);
		
		if(!isLoginValid){
			errorMessage = "Format d'adresse mail invalide.";
		} else{	
			errorMessage ="";
			if(userBus.checkUserCredential(login, password)){
				errorMessage = "Acces OK";
			} else {
				errorMessage = "Nom d'utilisateur et/ou mot de passe invalide(s).";
			}	
		}
		return null;
	}

	

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
