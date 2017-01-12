package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Review;

public interface ReviewDaoApi {

	// CRUD (Create - Read - Update - Delete)
	Review createReview(Review review);
	List<Review> getLastFiveReviewsByItemId(int itemId);
	List<Review> getReviewsByItemId(int itemId);
	List<Review> getReviewsByUserId(int userId);
	Review getReviewById(int reviewId);
	Review updateReview(Review review);
	boolean deleteReview(int reviewId);

}
