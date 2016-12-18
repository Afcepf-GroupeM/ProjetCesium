package fr.afcepf.al29.groupem.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import org.apache.commons.validator.routines.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.User;

@Scope("session")
@Component
@ManagedBean
public class LoginController {
	
	
	//  implements Serializable
//	private static final long serialVersionUID = 100042L;
	
	private String login;
	private String password;
	private String errorMessage ="";
	private User userLogged;	
	
	
	
	
	
	@Autowired
	private UserBusApi userBus; 
	
	
	public String loginaction(){
		String returnPage = null;
		EmailValidator emailValidator = EmailValidator.getInstance();
		boolean isLoginValid = emailValidator.isValid(login);
		
		if(!isLoginValid){
			errorMessage = "Format d'adresse mail invalide.";
		} else{	
			if(!(userBus.checkUserCredential(login, password))){
				errorMessage = "Nom d'utilisateur et/ou mot de passe invalide(s).";
			} else {
				errorMessage = "Connecté!";
				
				userLogged = userBus.getUserByLogin(login);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userid", userLogged.getId());
				
				
			}	
		}
		return returnPage;
	}

	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		userLogged = null;
		errorMessage = "";
		return "logout?faces-redirect=true";
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


	public User getUserLogged() {
		return userLogged;
	}


	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

	

}
