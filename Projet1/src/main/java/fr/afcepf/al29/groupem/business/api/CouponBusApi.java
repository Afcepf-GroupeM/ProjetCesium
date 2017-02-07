package fr.afcepf.al29.groupem.business.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Coupon;

public interface CouponBusApi {
    
    Coupon createCoupon(Coupon paramCoupon);
    Coupon updateCoupon(Coupon coupon);
    boolean destroyCoupon(Coupon coupon);
    
    
    Coupon getCouponById(int paramCouponId);
    List<Coupon> getCouponByCode(String code);
    
    List<Coupon> getAllCoupons();
    
    List<Coupon> getCouponsByCatId(int catId);
    
    List<Coupon> getCouponsStartinBefore(Date date);
    List<Coupon> getCouponsStartingAfter(Date date);
    List<Coupon> getCouponsStartingOn(Date date);
    
    List<Coupon> getCouponsEndingBefore(Date date);
    List<Coupon> getCouponsEndingAfter(Date date);
    List<Coupon> getCouponsEndingOn(Date date);
    
    List<Coupon> getCouponsByRebateLesserThan(float rebate);
    List<Coupon> getCouponsByRebateGreaterThan(float rebate);
    List<Coupon> getCouponsByRebateEquals(float rebate);

}
