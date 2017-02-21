package fr.afcepf.al29.groupem.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.User;
import fr.afcepf.al29.groupem.utils.DataGeneration;

@Component
@ManagedBean
public class GenerationController {
	
	private DataGeneration dataGen = new DataGeneration();
	
	private Integer nbUserTogenerate;
	private String messageUserGenerate;
	private List<User> listUsers;
	
	
	public String generateUsers() {
		
		listUsers = dataGen.generateUsers(nbUserTogenerate);
		messageUserGenerate = "Succes - "+ nbUserTogenerate + " utilisateurs ajoutés à la base!";
		
		return null;
		
	}


	
	//////////////////////
	// 		Ajax  		//
	//////////////////////
	
	
	public void ajaxAddUsers(AjaxBehaviorEvent event) {
		listUsers = dataGen.generateUsers(nbUserTogenerate);
		messageUserGenerate = "Succes - "+ nbUserTogenerate + " utilisateurs ajoutés à la base!";
	}
	
	
	//////////////////////
	// Getter / Setter  //
	//////////////////////
	
	public DataGeneration getDataGen() {
		return dataGen;
	}


	public void setDataGen(DataGeneration dataGen) {
		this.dataGen = dataGen;
	}


	public Integer getNbUserTogenerate() {
		return nbUserTogenerate;
	}


	public void setNbUserTogenerate(Integer nbUserTogenerate) {
		this.nbUserTogenerate = nbUserTogenerate;
	}



	public String getMessageUserGenerate() {
		return messageUserGenerate;
	}



	public void setMessageUserGenerate(String messageUserGenerate) {
		this.messageUserGenerate = messageUserGenerate;
	}



	public List<User> getListUsers() {
		return listUsers;
	}



	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	
	
	
	

}
