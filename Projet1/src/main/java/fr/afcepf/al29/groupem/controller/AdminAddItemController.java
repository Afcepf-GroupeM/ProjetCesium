package fr.afcepf.al29.groupem.controller;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CartBusApi;
import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Scope("session")
@Component
@ManagedBean
public class AdminAddItemController{
	private int idUser;
	private String name;
	private String reference;
	private List<MetaCategory> listeMetaCategory;
	//private List<String> listeNameMetaCategory;
	private MetaCategory metaCategory;
	private List<Category> listeCategory;
	private Category category;
	private double price;
	private int quantity;
	private String urlImage;
	private String message;
	

	@Autowired
	private CategoryBusApi categoryBusApi;
	
	public void init(ComponentSystemEvent e){
		idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		System.out.println("****************************idUser = "+idUser);
		listeMetaCategory = categoryBusApi.getAllMetaCategory();
		listeCategory = categoryBusApi.getCategoryByMetaId(1);
	}

	
	public String importerUnImage(){
		//Path pathCompletedeImage = Paths.get(xxx);
		//Files.copy(part.getInputStream(), pathCompletedeImage, StandardCopyOption.REPLACE_EXISTING);
		return null;
	}
	
	public String ajouterUnItem(){
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<MetaCategory> getListeMetaCategory() {
		return listeMetaCategory;
	}

	public void setListeMetaCategory(List<MetaCategory> listeMetaCategory) {
		this.listeMetaCategory = listeMetaCategory;
	}

	public MetaCategory getMetaCategory() {
		return metaCategory;
	}

	public void setMetaCategory(MetaCategory metaCategory) {
		this.metaCategory = metaCategory;
	}	

	public List<Category> getListeCategory() {
		return listeCategory;
	}

	public void setListeCategory(List<Category> listeCategory) {
		this.listeCategory = listeCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
}
