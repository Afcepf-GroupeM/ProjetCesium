package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Item;


@Component
@ManagedBean
public class RechercheArticleController {

	@Autowired
	private ItemBusApi itemBus;
	
	
	private List<String> listeresultat;
	
	private List<Item> items;
	private String keyword;
	private String keywordSearched;
	
	private String imagePath = "/images/items/";
	
	
	public String doSearch() {
		items = itemBus.searchItems(keyword);
		keywordSearched = keyword;
		keyword ="";
		return "recherche?faces-redirect=true";
	}


	public List<String> complete(String keyword) {
		
		List<Item> listetousitems = itemBus.getItemsByName(keyword);
		listeresultat = new ArrayList<>();
        for (int i = 0; i < 10  && listetousitems.size() > i ; i++) {  
            listeresultat.add(listetousitems.get(i).getName());
        }  
        
        return listeresultat;  
    } 
	
	
	
	public ItemBusApi getItemBus() {
		return itemBus;
	}


	public void setItemBus(ItemBusApi itemBus) {
		this.itemBus = itemBus;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getKeywordSearched() {
		return keywordSearched;
	}


	public void setKeywordSearched(String keywordSearched) {
		this.keywordSearched = keywordSearched;
	}


	public List<String> getListeresultat() {
		return listeresultat;
	}


	public void setListeresultat(List<String> listeresultat) {
		this.listeresultat = listeresultat;
	}
	
	
	
	
	
}
