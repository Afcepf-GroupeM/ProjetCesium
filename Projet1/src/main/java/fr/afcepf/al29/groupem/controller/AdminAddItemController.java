package fr.afcepf.al29.groupem.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Scope("session")
@Component
@ManagedBean
public class AdminAddItemController {
	private String name;
	private String reference;
	private List<MetaCategory> listeMetaCategorie;
	private MetaCategory metaCategorie;
	private List<Category> ListeCategory;
	private Category category;
	private double price;
//	private TypeMoney typeMoney;
//	private List<TypeMoney> listeMoney;
	private int quantity;
	private String urlImage;
	private String message;
	
	public void init(ComponentSystemEvent e){
		int idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		System.out.println("****************************idUser = "+idUser);
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
	public List<MetaCategory> getListeMetaCategorie() {
		return listeMetaCategorie;
	}
	public void setListeMetaCategorie(List<MetaCategory> listeMetaCategorie) {
		this.listeMetaCategorie = listeMetaCategorie;
	}
	public MetaCategory getMetaCategorie() {
		return metaCategorie;
	}
	public void setMetaCategorie(MetaCategory metaCategorie) {
		this.metaCategorie = metaCategorie;
	}
	public List<Category> getListeCategory() {
		return ListeCategory;
	}
	public void setListeCategory(List<Category> listeCategory) {
		ListeCategory = listeCategory;
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
	
	
}
