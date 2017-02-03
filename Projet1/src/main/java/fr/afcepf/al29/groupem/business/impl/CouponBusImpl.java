package fr.afcepf.al29.groupem.business.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CouponBusApi;
import fr.afcepf.al29.groupem.dao.api.CouponDaoApi;
import fr.afcepf.al29.groupem.entities.Coupon;

@Transactional
@Component
public class CouponBusImpl implements CouponBusApi{    
    
    
    @Autowired
    CouponDaoApi couponDao;

    @Override
    public Coupon createCoupon(Coupon paramCoupon) {        
        return couponDao.createCoupon(paramCoupon);
    }

    @Override
    public Coupon updateCoupon(Coupon paramCoupon) {        
        return couponDao.updateCoupon(paramCoupon);
    }

    @Override
    public boolean destroyCoupon(Coupon paramCoupon) {        
        return couponDao.destroyCoupon(paramCoupon);
    }

    @Override
    public Coupon getCouponById(int paramCouponId) {        
        return couponDao.getCouponById(paramCouponId);
    }

    @Override
    public List<Coupon> getCouponByCode(String paramCode) {        
        return couponDao.getCouponByCode(paramCode);
    }

    @Override
    public List<Coupon> getAllCoupons() {        
        return couponDao.getAllCoupons();
    }

    @Override
    public List<Coupon> getCouponsByCatId(int paramCatId) {        
        return couponDao.getCouponsByCatId(paramCatId);
    }

    @Override
    public List<Coupon> getCouponsStartinBefore(Date paramDate) {        
        return couponDao.getCouponsStartinBefore(paramDate);
    }

    @Override
    public List<Coupon> getCouponsStartingAfter(Date paramDate) {        
        return couponDao.getCouponsStartingAfter(paramDate);
    }

    @Override
    public List<Coupon> getCouponsStartingOn(Date paramDate) {        
        return couponDao.getCouponsStartingOn(paramDate);
    }

    @Override
    public List<Coupon> getCouponsEndingBefore(Date paramDate) {        
        return couponDao.getCouponsEndingBefore(paramDate);
    }

    @Override
    public List<Coupon> getCouponsEndingAfter(Date paramDate) {        
        return getCouponsEndingAfter(paramDate);
    }

    @Override
    public List<Coupon> getCouponsEndingOn(Date paramDate) {        
        return couponDao.getCouponsEndingOn(paramDate);
    }

    @Override
    public List<Coupon> getCouponsByRebateLesserThan(int paramRebate) {        
        return couponDao.getCouponsByRebateLesserThan(paramRebate);
    }

    @Override
    public List<Coupon> getCouponsByRebateGreaterThan(int paramRebate) {        
        return couponDao.getCouponsByRebateGreaterThan(paramRebate);
    }

    @Override
    public List<Coupon> getCouponsByRebateEquals(int paramRebate) {        
        return couponDao.getCouponsByRebateEquals(paramRebate);
    }

}
