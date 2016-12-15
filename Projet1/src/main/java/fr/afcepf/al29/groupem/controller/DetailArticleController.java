package fr.afcepf.al29.groupem.controller;

import java.util.List;
import java.util.Map;

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
	private CartLine cartLine;
	
	
	private String imagePath = "/images/items/";
	
	
	
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
				//"item.displayed";
		
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
	
	
	
	
	
	
}
