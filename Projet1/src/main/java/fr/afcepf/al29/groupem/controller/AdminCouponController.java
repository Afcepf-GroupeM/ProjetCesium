package fr.afcepf.al29.groupem.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.business.api.CouponBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Coupon;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@ManagedBean
@Component
public class AdminCouponController {
    
    private List<MetaCategory> listMetaCategories;
    private String metaCategoryChosenId;
    
    private List<Category> listCategories;
    private String categoryChosenId;
    
    private List<Coupon> listCoupons;
    private Coupon coupon;
    private String codeCoupon;
    private String errorMessageCodeCoupon;
    
    private String rebate; // in percent
    private String errorMessageRebate;
    
    private String resultCreation;
    
    
    @Autowired
    private CouponBusApi couponBus;
    
    @Autowired
    private CategoryBusApi catBus;
    
    
    
    
    public void initAdminCoupon(ComponentSystemEvent c){
        listMetaCategories = catBus.getAllMetaCategory();
    }
    
    
    public String createCoupon() {
        
        return null;
    }
    
    public void ajaxChangeMeta(AjaxBehaviorEvent event) {
        listCategories = catBus.getCategoryByMetaId(Integer.parseInt(metaCategoryChosenId));
    }
    
    
    
    
    
    
    
//    #################
//    GETTERs / SETTERs
//    #################

    /**
     * @return the listMetaCategories
     */
    public List<MetaCategory> getListMetaCategories() {
        return listMetaCategories;
    }

    /**
     * @param paramListMetaCategories the listMetaCategories to set
     */
    public void setListMetaCategories(List<MetaCategory> paramListMetaCategories) {
        listMetaCategories = paramListMetaCategories;
    }

    /**
     * @return the listCategories
     */
    public List<Category> getListCategories() {
        return listCategories;
    }

    /**
     * @param paramListCategories the listCategories to set
     */
    public void setListCategories(List<Category> paramListCategories) {
        listCategories = paramListCategories;
    }

    /**
     * @return the listCoupons
     */
    public List<Coupon> getListCoupons() {
        return listCoupons;
    }

    /**
     * @param paramListCoupons the listCoupons to set
     */
    public void setListCoupons(List<Coupon> paramListCoupons) {
        listCoupons = paramListCoupons;
    }

    /**
     * @return the coupon
     */
    public Coupon getCoupon() {
        return coupon;
    }

    /**
     * @param paramCoupon the coupon to set
     */
    public void setCoupon(Coupon paramCoupon) {
        coupon = paramCoupon;
    }

    /**
     * @return the codeCoupon
     */
    public String getCodeCoupon() {
        return codeCoupon;
    }

    /**
     * @param paramCodeCoupon the codeCoupon to set
     */
    public void setCodeCoupon(String paramCodeCoupon) {
        codeCoupon = paramCodeCoupon;
    }

    /**
     * @return the categoryChosenId
     */
    public String getCategoryChosenId() {
        return categoryChosenId;
    }

    /**
     * @param paramCategoryChosenId the categoryChosenId to set
     */
    public void setCategoryChosenId(String paramCategoryChosenId) {
        categoryChosenId = paramCategoryChosenId;
    }

    /**
     * @return the couponBus
     */
    public CouponBusApi getCouponBus() {
        return couponBus;
    }

    /**
     * @param paramCouponBus the couponBus to set
     */
    public void setCouponBus(CouponBusApi paramCouponBus) {
        couponBus = paramCouponBus;
    }

    /**
     * @return the catBus
     */
    public CategoryBusApi getCatBus() {
        return catBus;
    }

    /**
     * @param paramCatBus the catBus to set
     */
    public void setCatBus(CategoryBusApi paramCatBus) {
        catBus = paramCatBus;
    }
    /**
     * @return the errorMessageCodeCoupon
     */
    public String getErrorMessageCodeCoupon() {
        return errorMessageCodeCoupon;
    }

    /**
     * @param paramErrorMessageCodeCoupon the errorMessageCodeCoupon to set
     */
    public void setErrorMessageCodeCoupon(String paramErrorMessageCodeCoupon) {
        errorMessageCodeCoupon = paramErrorMessageCodeCoupon;
    }

    /**
     * @return the metaCategoryChosenId
     */
    public String getMetaCategoryChosenId() {
        return metaCategoryChosenId;
    }

    /**
     * @param paramMetaCategoryChosenId the metaCategoryChosenId to set
     */
    public void setMetaCategoryChosenId(String paramMetaCategoryChosenId) {
        metaCategoryChosenId = paramMetaCategoryChosenId;
    }

    /**
     * @return the rebate
     */
    public String getRebate() {
        return rebate;
    }

    /**
     * @param paramRebate the rebate to set
     */
    public void setRebate(String paramRebate) {
        rebate = paramRebate;
    }

    /**
     * @return the errorMessageRebate
     */
    public String getErrorMessageRebate() {
        return errorMessageRebate;
    }

    /**
     * @param paramErrorMessageRebate the errorMessageRebate to set
     */
    public void setErrorMessageRebate(String paramErrorMessageRebate) {
        errorMessageRebate = paramErrorMessageRebate;
    }


    /**
     * @return the resultCreation
     */
    public String getResultCreation() {
        return resultCreation;
    }


    /**
     * @param paramResultCreation the resultCreation to set
     */
    public void setResultCreation(String paramResultCreation) {
        resultCreation = paramResultCreation;
    }
    
    
    
    

}
