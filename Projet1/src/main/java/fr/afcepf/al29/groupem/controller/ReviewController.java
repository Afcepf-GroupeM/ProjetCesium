package fr.afcepf.al29.groupem.controller;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.ReviewBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.User;

@Component
@ManagedBean
public class ReviewController {

	private int id;
	private String rating;
	private String comment;
	private Date dateCreation;
	private User user;
	private Item item;
	
	private boolean hasOrdered;
	private String errorMessage;
	
	@Autowired
	private ReviewBusApi reviewBus;
	@Autowired
	private UserBusApi userBus;
	@Autowired
	private ItemBusApi itemBus;
	@Autowired
	private OrderBusApi orderBus;
	
	public void initInArticleSheet(){
		
	}
	
	public void initInReviewPage(){
		
	}
	
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Map<String,Object> userLogged = fc.getExternalContext().getSessionMap();
		
		int itemId = Integer.parseInt(params.get("itemId"));
		int userId = (Integer) userLogged.get("userid");
		
		item = itemBus.getItemById(itemId);
		user = userBus.getUserById(userId);
		hasOrdered = orderBus.hasOrderedItem(item.getId(), user.getId());
		System.out.println("@@@@@@@@@@@@@ Ordered? " + hasOrdered);
				
		resetFields();
	}
	
	public String createReview(){
		RegexValidator ratingValidator = new RegexValidator("^([0-4]\\.[05])|([0-4],[05])|([0-5])$", false);
		
		boolean ratingValid = ratingValidator.isValid(rating) && (!rating.isEmpty());
		
		errorMessage = "";
		if(!ratingValid){errorMessage += "La note attribuée au produit est invalide!<br/>";}
		
		if(ratingValid){
			rating = rating.replace(",", ".");
			
			float ratingNumber = Float.parseFloat(rating);
			Date creationDate = new Date();
			
			reviewBus.createReview(ratingNumber, comment, creationDate, item, user);
			
			return "article?faces-redirect=true&itemId=" + item.getId();
		}
		
		return null;
	}
	
	public void resetFields(){
		rating = "";
		comment = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isHasOrdered() {
		return hasOrdered;
	}

	public void setHasOrdered(boolean hasOrdered) {
		this.hasOrdered = hasOrdered;
	}
	
}
