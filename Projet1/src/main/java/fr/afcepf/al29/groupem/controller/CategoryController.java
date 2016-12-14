package fr.afcepf.al29.groupem.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Item;


@Component
@ManagedBean
public class CategoryController {

	
private Category category;
private Item item;
private List<Item> items;
private List<Category> categories;
	
	
	@Autowired
	private CategoryBusApi categoryBus;
	
	
	public String ItemGetByCategory() {
		category = categoryBus.findCategory(getParamId("categoryId"));
		items = category.getItems();
		return "items.displayed";
	}


	private int getParamId(String string) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
