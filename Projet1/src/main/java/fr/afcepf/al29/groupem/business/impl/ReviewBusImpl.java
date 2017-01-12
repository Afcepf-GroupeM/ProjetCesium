package fr.afcepf.al29.groupem.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.ReviewBusApi;
import fr.afcepf.al29.groupem.dao.api.ReviewDaoApi;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Review;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component
public class ReviewBusImpl implements ReviewBusApi {

	@Autowired
	private ReviewDaoApi reviewDao;
	
	@Override
	public Review createReview(float rating, String comment, Date creationDate, Item item, User user) {
		Review review = new Review();
		review.setRating(rating);
		review.setComment(comment);
		review.setCreationDate(creationDate);
		review.setItem(item);
		review.setUser(user);
		
		Review reviewCreated = reviewDao.createReview(review);
		return reviewCreated;
	}

	@Override
	public List<Review> getLastFiveReviewsByItemId(int itemId) {
		return reviewDao.getLastFiveReviewsByItemId(itemId);
	}

	@Override
	public List<Review> getReviewsByItemId(int itemId) {
		return reviewDao.getReviewsByItemId(itemId);
	}

	@Override
	public List<Review> getReviewsByUserId(int userId) {
		return reviewDao.getReviewsByUserId(userId);
	}

	@Override
	public Review getReviewById(int reviewId) {
		return reviewDao.getReviewById(reviewId);
	}

	@Override
	public Review updateReview(int id, float rating, String comment, Date creationDate, Item item, User user) {
		Review review = new Review();
		review.setId(id);
		review.setRating(rating);
		review.setComment(comment);
		review.setCreationDate(creationDate);
		review.setItem(item);
		review.setUser(user);
		
		Review reviewUpdated = reviewDao.updateReview(review);
		return reviewUpdated;
	}

	@Override
	public boolean deleteReview(int reviewId) {
		return reviewDao.deleteReview(reviewId);
	}

}
