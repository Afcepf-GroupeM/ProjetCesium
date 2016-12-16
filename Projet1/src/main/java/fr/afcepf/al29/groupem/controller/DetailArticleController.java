package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Item;


@Component
@ManagedBean
public class DetailArticleController {

	
	@Autowired
	private ItemBusApi itemBus;
	
	
	private Item item;
	private List<Item> items;
	private CartLine cartLine = new CartLine();
	
	
	private int quantity;
	
	
	private String imagePath = "/images/items/";
	
	
	private List<Integer> quantityList;

	@PostConstruct
	public void init(){
	quantityList = new ArrayList<>();
	quantityList.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));}
	
	
	
	
	//les m�thodes getParam permettent de r�cup�rer un param�tre pass� par la page JSP.Doit �tre utilis� en lien avec la balise <f:param>
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
	
	
	//m�thode qui retourne la fiche d�taill�e d'un article � partir de son id
	public String doFindItem() {
		//item = itemBus.findItem(2);
		item = itemBus.findItem(getParamId("itemId"));
		return "/detailArticle.xhtml?faces-redirect=true";
				
		
	}



	//GETTERS et SETTERS
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



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public CartLine getCartLine() {
		return cartLine;
	}



	public void setCartLine(CartLine cartLine) {
		this.cartLine = cartLine;
	}





	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public List<Integer> getQuantityList() {
		return quantityList;
	}




	public void setQuantityList(List<Integer> quantityList) {
		this.quantityList = quantityList;
	}
	
	
	
	
	
	
}
