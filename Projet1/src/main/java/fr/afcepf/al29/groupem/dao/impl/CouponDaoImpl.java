package fr.afcepf.al29.groupem.dao.impl;

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
    public Coupon getCouponByCode(String paramCode) {
        Coupon coupon = null;
        try {
            coupon = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.code = :couponcode",Coupon.class)
                                         .setParameter("couponcode", paramCode)
                                         .getSingleResult();
        } catch (Exception e) {}
        return coupon;
    }


    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup",Coupon.class).getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByCatId(int paramCatId) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.category.id = :catid",Coupon.class)
                                                .setParameter("catid", paramCatId)
                                                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartinBefore(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate <= :stdate",Coupon.class)
                                                .setParameter("stdate", paramDate)
                                                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartingAfter(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate >= :stdate",Coupon.class)
                .setParameter("stdate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsStartingOn(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.startDate = :stdate",Coupon.class)
                .setParameter("stdate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingBefore(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate <= :enddate",Coupon.class)
                .setParameter("eddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingAfter(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate >= :enddate",Coupon.class)
                .setParameter("enddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsEndingOn(Date paramDate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.endDate = :enddate",Coupon.class)
                .setParameter("enddate", paramDate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateLesserThan(int paramRebate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate <= :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateGreaterThan(int paramRebate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate >= :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }


    @Override
    public List<Coupon> getCouponsByRebateEquals(int paramRebate) {
        List<Coupon> listCoupons = entityManager.createQuery("SELECT coup FROM Coupon coup WHERE coup.rebate = :rebate",Coupon.class)
                .setParameter("rebate", paramRebate)
                .getResultList();
        return listCoupons;
    }
    
    

}
