package fr.afcepf.al29.groupem.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.ReviewBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Review;
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
	
	private boolean hasOrderedItem;
	private Review review;
	private List<Review> itemReviews;
	private String stringActionReview;
	private String errorMessage;

	@Autowired
	private ReviewBusApi reviewBus;
	@Autowired
	private UserBusApi userBus;
	@Autowired
	private ItemBusApi itemBus;
	@Autowired
	private OrderBusApi orderBus;
	
	public void initInArticleSheet(ComponentSystemEvent event){
		int itemId = Integer.parseInt(getParam("itemId"));
		boolean isLogged = Boolean.parseBoolean(getParam("isLogged"));
		
		if (isLogged){
			int userId = getUserIdLogged("userid");
			
			hasOrderedItem = orderBus.hasOrderedItem(itemId, userId);
		}		
		
		itemReviews = reviewBus.getLastFiveReviewsByItemId(itemId);
	}
	
	public void initInReviewPage(ComponentSystemEvent event){
		
	}
	
	public void init(ComponentSystemEvent event){
		stringActionReview = getParam("actionReview");
		
		if (stringActionReview.equals("Creation")) {
			int itemId = Integer.parseInt(getParam("itemId"));
			int userId = getUserIdLogged("userid");
			
			item = itemBus.getItemById(itemId);
			user = userBus.getUserById(userId);
			
			resetFields();
		}
		else if (stringActionReview.equals("Modification")) {
			int reviewId = Integer.parseInt(getParam("reviewId"));
			
			review = reviewBus.getReviewById(reviewId);
			item = review.getItem();
			user = review.getUser();
			
			initFields(review);
		}
	}
	
	public String actionReview(){
		RegexValidator ratingValidator = new RegexValidator("^([0-4]\\.[05])|([0-4],[05])|([0-5])$", false);
		
		boolean ratingValid = ratingValidator.isValid(rating) && (!rating.isEmpty());
		
		errorMessage = "";
		if(!ratingValid){errorMessage += "La note attribuée au produit est invalide!<br/>";}
		
		if(ratingValid){
			rating = rating.replace(",", ".");
			
			float ratingNumber = Float.parseFloat(rating);
			Date creationDate = new Date();
			
			if (stringActionReview.equals("Creation")){
				reviewBus.createReview(ratingNumber, comment, creationDate, item, user);
			}
			else if (stringActionReview.equals("Modification")){
				reviewBus.updateReview(review.getId(), ratingNumber, comment, creationDate, item, user);
			}
			
			return "article?faces-redirect=true&itemId=" + item.getId();
		}
		
		return null;
	}
	
	public String createReview(){
		int itemId = Integer.parseInt(getParam("itemId"));
		stringActionReview = getParam("actionReview");
		
		return "actionReview?faces-redirect=true&itemId=" + itemId + "&actionReview=" + stringActionReview;
	}
	
	public String displayItemReviews(){
		int itemId = Integer.parseInt(getParam("itemId"));
		
		return "itemReviews?faces-redirect=true&itemId=" + itemId;
	}
	
	public String updateReview(){
		int reviewId = Integer.parseInt(getParam("reviewId"));
		stringActionReview = getParam("actionReview");
		
		return "actionReview?faces-redirect=true&reviewId=" + reviewId + "&actionReview=" + stringActionReview;
	}
	
	public String deleteReview(){
		int itemId = Integer.parseInt(getParam("itemId"));
		int reviewId = Integer.parseInt(getParam("reviewId"));
		
		reviewBus.deleteReview(reviewId);
		
		return "article?faces-redirect=true&itemId=" + itemId;
	}
	
	public String getParam(String param){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String result = params.get(param);
		
		return result;
	}
	
	public Integer getUserIdLogged(String param){
		Map<String,Object> userLogged = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Integer userId = (Integer) userLogged.get(param);
		
		return userId;
	}
	
	public void resetFields(){
		rating = "";
		comment = "";
	}
	
	public void initFields(Review review){
		rating = Float.toString(review.getRating());
		comment = review.getComment();
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
	
	public List<Review> getItemReviews() {
		return itemReviews;
	}

	public void setItemReviews(List<Review> itemReviews) {
		this.itemReviews = itemReviews;
	}
	
	public String getStringActionReview() {
		return stringActionReview;
	}

	public void setStringActionReview(String stringActionReview) {
		this.stringActionReview = stringActionReview;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isHasOrderedItem() {
		return hasOrderedItem;
	}

	public void setHasOrderedItem(boolean hasOrderedItem) {
		this.hasOrderedItem = hasOrderedItem;
	}
	
}
