package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
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
public class AjoutArticlePanierController {

	@Autowired
	private ItemBusApi itemBus;
	
	
	//méthode qui permet d'instancier la liste de lignes paniers
	@PostConstruct
	public void initialize() {
		cartLines = new ArrayList<CartLine>();
	}
	
	
	
	
	
	private Item item;
	
	//liste des articles
	private List<Item> items;
	
	//liste des lignes paniers
	private List<CartLine> cartLines;
	
	
	
	
	
	
	
	//les 2 methodes getParam ci-dessous permettent de recuperer un parametre passÃ© par la page JSP.Doit ï¿½tre utilise en lien avec la balise <f:param>
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
	
	//methode qui prend en compte les 2 methodes precedentes et qui permettent d'ajouter un article au panier.
	public String addItemToCart() {
		Item item = itemBus.findItem(getParamId("itemId"));
		addItem(item);
		return "/contenuPanier.xhtml?faces-redirect=true";
				
	}


	//methode qui supprime un article du panier
	public void removeItem(Item item){
		for (CartLine cartLine: cartLines){
			if (cartLine.getItem().equals(item)) {
				cartLines.remove(cartLine);
				return;
			}
		}
	}
	
	//méthode qui supprime un article du panier en prenant en paramètre l'identifiant de l'article (methode precedente + methode de l'identifiant) 
	public String removeItemFromCart() {
		Item item = itemBus.findItem(getParamId("itemId"));
		removeItem(item);
		return null;
	}
	
	
	
	//création d'une méthode qui permet de récupérer le prix d'une ligne de panier dans l'entité CartLine : methode SubTotal
	
	
	
	
	//méthode qui additionne le prix de chaque article pour retourner le prix total du panier
	public Float getTotal() {
		if (cartLines == null || cartLines.isEmpty())
			return 0f;
		Float total = 0f;
		
		for (CartLine cartLine : cartLines) {
			total += (cartLine.getSubTotal());
		}
		return total;
		}
	
	
	//Implémentation du bouton "valider commande".Aucune action n'est effectuée, seule la redirection vers la prochaine page est lancée.
	public String validerCommande() {
		return "la page suivante";
	}
	
	
	//Implémentation du bouton "poursuivez vos achats".Aucune action n'est effectuée, seule la redirection vers la prochaine page est lancée.
		public String poursuivreAchat() {
			return "la page précédente";
		}
	
	//On reste sur la meme page lorsqu'on modifie la quantité
		public String updateQuantity() {
			return null;
		}
	
	
	
	
	
	//Getters et setters
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



	public List<CartLine> getCartLines() {
		return cartLines;
	}



	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	
	
	
	
	
	
	
}
	
	

