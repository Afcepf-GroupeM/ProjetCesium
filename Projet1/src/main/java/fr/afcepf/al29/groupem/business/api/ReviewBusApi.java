package fr.afcepf.al29.groupem.business.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Review;
import fr.afcepf.al29.groupem.entities.User;

public interface ReviewBusApi {

	Review createReview(float rating, String comment, Date creationDate, Item item, User user);
	List<Review> getReviewsByItemId(int itemId);
	List<Review> getReviewsByUserId(int userId);
	Review getReviewById(int reviewId);
	double getMeanRating(int itemId);
	Review updateReview(int id, float rating, String comment, Date creationDate, Item item, User user);
	boolean deleteReview(int reviewId);

}
