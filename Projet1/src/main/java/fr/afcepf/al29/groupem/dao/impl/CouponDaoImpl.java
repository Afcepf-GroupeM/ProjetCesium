package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.CouponDaoApi;
import fr.afcepf.al29.groupem.entities.Coupon;

@Transactional
@Component
public class CouponDaoImpl implements CouponDaoApi{
    
    
    
    @PersistenceContext(unitName="Projet1") 
    private EntityManager entityManager;
    
    
    @Override
    public Coupon createCoupon(Coupon coupon){
        entityManager.persist(coupon);
        return coupon;
    }


    @Override
    public Coupon updateCoupon(Coupon paramCoupon) {
        entityManager.merge(paramCoupon);
        return paramCoupon;
    }


    @Override
    public boolean destroyCoupon(Coupon paramCoupon) {
        Coupon couponToRemove = entityManager.getReference(Coupon.class, paramCoupon.getId());
        entityManager.remove(couponToRemove);
        return (getCouponById(paramCoupon.getId()) == null);
    }


    @Override
    public Coupon getCouponById(int paramCouponId) {
        Coupon coupon = entityManager.find(Coupon.class, paramCouponId);
        return coupon;
    }


    @Override
    public List<Coupon> getCouponByCode(String paramCode) {
        List<Coupon> coupons = new ArrayList<>();      
            coupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.code LIKE :couponcode",Coupon.class)
                                         .setParameter("couponcode", '%'+paramCode+'%')
                                         .getResultList();
        
        return coupons;
    }


    @Override
    public List<Coupon> getAllCoupons() {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup",Coupon.class).getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByCatId(int paramCatId) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.category.id = :catid",Coupon.class)
                                                .setParameter("catid", paramCatId)
                                                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartinBefore(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate <= :stdate",Coupon.class)
                                                .setParameter("stdate", paramDate)
                                                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartingAfter(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate >= :stdate",Coupon.class)
                .setParameter("stdate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartingOn(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate = :stdate",Coupon.class)
                .setParameter("stdate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingBefore(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate <= :enddate",Coupon.class)
                .setParameter("enddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingAfter(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate >= :enddate",Coupon.class)
                .setParameter("enddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingOn(Date paramDate) {
    	List<Coupon> listCoupons = new ArrayList<>();
    	System.out.println("CouponDaoImpl - getCouponsEndingOn - paramDate : " + paramDate);
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate LIKE :enddate",Coupon.class)
                .setParameter("enddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateLesserThan(float paramRebate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate <= :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateGreaterThan(float paramRebate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate >= :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateEquals(float paramRebate) {
    	List<Coupon> listCoupons = new ArrayList<>();
        listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate = :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }
    
    

}
