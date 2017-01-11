package fr.afcepf.al29.groupem.controller;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.ReviewBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.User;

@Component
@ManagedBean
public class ReviewController {

	private int id;
	private float rating;
	private String comment;
	private Date dateCreation;
	private User user;
	private Item item;
	
	private String message;
	
	@Autowired
	private ReviewBusApi reviewBus;
	@Autowired
	private UserBusApi userBus;
	@Autowired
	private ItemBusApi itemBus;
	
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
				
		resetFields();
	}
	
	public String createReview(){
		RegexValidator ratingValidator = new RegexValidator("^[0-9A-Za-z\\s-'éèà]*$", false);
		RegexValidator commentValidator = new RegexValidator("^\\d+$", false);
		
		return null;
	}
	
	public void resetFields(){
		rating = 0.0f;
		comment = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
