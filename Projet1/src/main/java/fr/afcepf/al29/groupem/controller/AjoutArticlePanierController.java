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
public class AjoutArticlePanierController {

	@Autowired
	private ItemBusApi itemBus;
	
	
	private Item item;
	private List<Item> items;
	private List<CartLine> cartLines;
	
	
	//les methodes getParam permettent de recuperer un parametre passÃ© par la page JSP.Doit ï¿½tre utilise en lien avec la balise <f:param>
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
	
	
	
	
	
	//methode qui ajoute un article dans le panier.Si l'article est deja dans le panier, on en modifie la quantite.
	public void addItem(Item item) {
		boolean itemFound = false;
		for (CartLine cartLine : cartLines) {
			if (cartLine.getItem().equals(item)) {
				cartLine.setQuantity(cartLine.getQuantity() + 1);
				itemFound = true;
			}
		}
	if (!itemFound)
		cartLines.add(new CartLine(1, item));
	
	}
	
	//methode qui permet d'ajouter un article au panier.
	public String addItemToCart() {
		Item item = itemBus.findItem(getParamId("itemId"));
		addItem(item);
		return "item.added";
	}
	
	
}
	
	

