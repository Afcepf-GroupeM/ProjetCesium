package fr.afcepf.al29.groupem.controller;

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
	
	
	private List<Item> items;
	private String keyword;
	
	
	public String doSearch() {
		items = itemBus.searchItems(keyword);
		return null;
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
	
	
}
