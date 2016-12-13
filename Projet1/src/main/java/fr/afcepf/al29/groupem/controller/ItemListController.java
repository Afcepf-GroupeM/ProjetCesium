package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Component
@ManagedBean
public class ItemListController {
	
	private List<MetaCategory> listMetaCategory;
	private MetaCategory metaCategory;
	private Category category;
	
	private List<Item> listItem;
	
	@Autowired
	CategoryBusApi catBus;
	
	
	
	
	
	@PostConstruct
	public void init(){
		listMetaCategory = new ArrayList<>();
		listMetaCategory = catBus.getAllMetaCategory();
		
	}
	
	
	
	
	

	public List<MetaCategory> getListMetaCategory() {
		return listMetaCategory;
	}

	public void setListMetaCategory(List<MetaCategory> listMetaCategory) {
		this.listMetaCategory = listMetaCategory;
	}

	public MetaCategory getMetaCategory() {
		return metaCategory;
	}

	public void setMetaCategory(MetaCategory metaCategory) {
		this.metaCategory = metaCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}
	
	
	
	
	
	

}
