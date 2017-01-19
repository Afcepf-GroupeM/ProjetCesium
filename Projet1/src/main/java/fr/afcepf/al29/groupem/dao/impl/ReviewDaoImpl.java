package fr.afcepf.al29.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.ReviewDaoApi;
import fr.afcepf.al29.groupem.entities.Review;

@Transactional
@Component
public class ReviewDaoImpl implements ReviewDaoApi{

	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public Review createReview(Review review) {
		entityManager.persist(review);
		return review;
	}

	@Override
	public List<Review> getLastFiveReviewsByItemId(int itemId) {
		return entityManager.createQuery("SELECT r from Review r INNER JOIN r.item i WHERE i.id = :itemId ORDER BY r.creationDate DESC, r.id DESC", Review.class).setParameter("itemId", itemId).setMaxResults(5).getResultList();
	}

	@Override
	public List<Review> getReviewsByItemId(int itemId) {
		return entityManager.createQuery("SELECT r from Review r INNER JOIN r.item i WHERE i.id = :itemId", Review.class).setParameter("itemId", itemId).getResultList();
	}
	
	@Override
	public List<Review> getReviewsByUserId(int userId) {
		return entityManager.createQuery("SELECT r from Review r INNER JOIN r.user u WHERE u.id = :userId", Review.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public Review getReviewById(int reviewId) {
		return entityManager.find(Review.class, reviewId);
	}

	@Override
	public Review updateReview(Review review) {
		entityManager.merge(review);
		return review;
	}

	@Override
	public boolean deleteReview(int reviewId) {
		entityManager.remove(getReviewById(reviewId));
		return (getReviewById(reviewId) == null);
	}

}
