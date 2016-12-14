package fr.afcepf.al29.groupem.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Item;


@Component
@ManagedBean
public class DetailArticleController {

	
	@Autowired
	private ItemBusApi itemBus;
	
	
	private Item item;
	private List<Item> items;
	
	
	
	//les methodes getParam permettent de recuperer un parametre passé par la page JSP.Doit �tre utilise en lien avec la balise <f:param>
	protected String getParam(String param) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String result = map.get(param);
		
		return result;
	}
	
	
	
	protected Integer getParamId(String param) {
		Integer result = Integer.valueOf(getParam(param));
		
		return result;
		
	}
	
	
	//methode qui retourne la fiche dun article a partir de son id
	public String doFindItem() {
		item = itemBus.findItem(getParamId("itemId"));
		return "item.displayed";
		
	}



	public ItemBusApi getItemBus() {
		return itemBus;
	}



	public void setItemBus(ItemBusApi itemBus) {
		this.itemBus = itemBus;
	}



	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
	
	
	
}
