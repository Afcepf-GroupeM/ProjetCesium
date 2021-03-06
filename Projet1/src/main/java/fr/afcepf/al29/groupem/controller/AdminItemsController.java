package fr.afcepf.al29.groupem.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
@ManagedBean
public class AdminItemsController{	
	private int idUser;
	public String ajouterUnItem(){
		return "/admin-additem.jsf?faces-redirect = true";
	} 
	
	public void init(ComponentSystemEvent e){
		idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		System.out.println("****************************idUser = "+idUser);
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
}
