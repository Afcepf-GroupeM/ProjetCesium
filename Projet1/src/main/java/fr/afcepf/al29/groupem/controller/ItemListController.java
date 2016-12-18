package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;
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
	private List<Category> listCategory;
	private MetaCategory metaCategory;
	private Category category;
	
	private List<Item> listItem;
	private int idCatToPrint;
	private Category chosenCategory;

	private String imagePath = "/images/items/";
	
	
	@Autowired
	CategoryBusApi catBus;
	
	
	
	
	
//	@PostConstruct
	public void init(ComponentSystemEvent e){
		listMetaCategory = new ArrayList<>();
		listMetaCategory = catBus.getAllMetaCategory();
		
		if(idCatToPrint == 0){
			idCatToPrint = 1;
		}
		
		chosenCategory = catBus.getCategoryById(idCatToPrint);
		List<Item> listItemToTrim = chosenCategory.getItems();
		for (Item item : listItemToTrim) {
			String description = item.getDescription();
			int numberOfCharPrinted = 70;
			if (description.length() > numberOfCharPrinted)
			    description = description.substring(0, numberOfCharPrinted-3) + "...";
			item.setDescription(description);
		}	
	}
	
	//TODO
	// - manage multiple pages
	// - link to other categories
	// - link to article page

	
	
	

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

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public Category getChosenCategory() {
		return chosenCategory;
	}

	public void setChosenCategory(Category chosenCategory) {
		this.chosenCategory = chosenCategory;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getIdCatToPrint() {
		return idCatToPrint;
	}

	public void setIdCatToPrint(int idCatToPrint) {
		this.idCatToPrint = idCatToPrint;
	}


	
	
	
	
	

}
