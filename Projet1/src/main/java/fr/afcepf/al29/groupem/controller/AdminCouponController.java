package fr.afcepf.al29.groupem.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.RegexValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.business.api.CouponBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Coupon;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@SessionScoped
@ManagedBean
@Component
public class AdminCouponController {
	
	@Autowired
    private CouponBusApi couponBus;
    
    @Autowired
    private CategoryBusApi catBus;
    
    private List<MetaCategory> listMetaCategories;
    private String metaCategoryChosenId;
    
    private List<Category> listCategories;
    private String categoryChosenId;
    
    private List<MetaCategory> listMetaCategoriesSearch;
    private String metaCategoryChosenIdSearch;
    
    private List<Category> listCategoriesSearch;
    private String categoryChosenIdSearch;
    
    private List<Coupon> listCouponsToPrint;
    private Coupon coupon;
    private String codeCoupon;
    private String errorMessageCodeCoupon;
    
    private String description;
    private String errorMessageDescription;
    
    private Date startDate;
    private String startDateString;
    private String errorMessageStartDate;
    
    private Date endDate;
    private String endDateString;
    private String errorMessageEndDate;
    
    private String rebate; // in percent
    private String errorMessageRebate;
    
    private String resultCreation;
    
    private boolean isCouponCodeValid;
    private boolean isRebateValid;
    
    private boolean firstLoad = true;
    
    private String searchType = "1";  //v1
    private String errorSearch = "";
    private String codeToSearch; // v20
    
    private String searchDateOption1 = "start"; //v30
    private String searchDateOption2 = "before"; //v31
    private String dateToSearch; //v32
    
    private String rebateSearchOption = "lesserthan"; //v40
    private String rebateToSearch; //v41
    
    private List<Coupon> searchResult;
    
    private boolean hasSearchBeenDone = false;
    
    
    
    
    public void initAdminCoupon(ComponentSystemEvent c){
        listMetaCategories = catBus.getAllMetaCategory(); 
        listMetaCategoriesSearch = listMetaCategories;
        if(firstLoad){
            Iterator<MetaCategory> it = listMetaCategories.iterator();
            listCategories = catBus.getCategoryByMetaId(it.next().getId());
            listCategoriesSearch = listCategories;
            firstLoad = false;
        }
    }
    
    
    public String createCoupon() {
        resultCreation = "";
        RegexValidator couponValidator = new RegexValidator("([0-9A-Za-z])\\w+" , true);
        RegexValidator rebateValidator = new RegexValidator("(100)|([1-9]?[0-9])", false);
        isCouponCodeValid = couponValidator.isValid(codeCoupon);
        isRebateValid = rebateValidator.isValid(rebate);
        
        if(!isCouponCodeValid){
            errorMessageCodeCoupon = "Code coupon invalide: caracteres alphanumeriques uniquement";
        } else {
            if(couponBus.getCouponByCode(codeCoupon) == null){
                errorMessageCodeCoupon = "Le code " + codeCoupon + " existe deja pour un autre coupon";
            } else {
                errorMessageCodeCoupon = ""; 
            }
        }
        
        if(!isRebateValid){
            errorMessageRebate = "Réduction invalide: valeur entre 1 et 100";
        } else {
            errorMessageRebate = "";
        }
        
        boolean isDescriptionEmpty = description.isEmpty();
        
        if(isDescriptionEmpty){
            errorMessageDescription = "La description est vide.";
        } else {
            errorMessageDescription = "";
        }
        
        RegexValidator dateRegexValidator = new RegexValidator("([0-3][0-9])[/]([0-1][0-9])[/]([0-9]{4})",false);
        DateValidator dateValidator = DateValidator.getInstance();
        
        boolean isStartDateInputValid = dateRegexValidator.isValid(startDateString);
        boolean isStartDateValid = dateValidator.isValid(startDateString, "dd/MM/yyy");
        if(!isStartDateInputValid){
            errorMessageStartDate = "Format de date invalide.";
        } else {
            if(!isStartDateValid){
                errorMessageStartDate = "Date invalide (cette date n'existe pas).";
            } else {
                errorMessageStartDate = "";
            }              
        }
        
        boolean isEndDateInputValid = dateRegexValidator.isValid(endDateString);
        boolean isEndDateValid = dateValidator.isValid(endDateString, "dd/MM/yyy");
        if(!isEndDateInputValid){
            errorMessageEndDate = "Format de date invalide.";
        } else {
            if(!isEndDateValid){
                errorMessageEndDate = "Date invalide (cette date n'existe pas).";
            } else {
                errorMessageEndDate = "";
            }              
        }

        if(isEndDateValid && !isDescriptionEmpty && isStartDateValid && isRebateValid && isCouponCodeValid){
            Coupon coupon = new Coupon();
            coupon.setCategory(catBus.getCategoryById(Integer.parseInt(categoryChosenId)));
            coupon.setCode(codeCoupon);
            coupon.setRebate(Float.parseFloat(rebate));
            DateFormat dateFormater = new SimpleDateFormat("dd/MM/yyy");         
            try {
                startDate = dateFormater.parse(startDateString);
                endDate = dateFormater.parse(endDateString);
            } catch (ParseException e) {
                System.out.println("ERREUR - Parsing start/end Date in AdminCouponController - createCoupon()" + e.getMessage());
            }
            coupon.setStartDate(startDate);
            coupon.setEndDate(endDate);
            coupon.setDescription(description);
            coupon.setImagePath("url_image");
            
            coupon = couponBus.createCoupon(coupon);
            
            resultCreation = "Coupon \" " + codeCoupon +" \" créé. Id: " + coupon.getId() + ".";
            resetCreateCouponForm();
        }        
        return null;
    }
    
    
    public String getAllCoupons() {
    	searchResult = null;
    	searchResult = couponBus.getAllCoupons();
    	hasSearchBeenDone = true;
    	
    	return null;
    }
    
  public String searchByCode() {
	  System.out.println("AdminCouponController - searchByCode - Method Entry");
	  errorSearch = "";
	  searchResult = null;
	  if(codeToSearch.isEmpty() || codeToSearch == null){
		  errorSearch = "Champ vide.";
	  } else {		  
		  searchResult = couponBus.getCouponByCode(codeToSearch);
		  hasSearchBeenDone = true;
	  }
	  return null;
  }
  
  public String searchByDate() {
	  System.out.println("AdminCouponController - searchByDate - Method Entry");
	  searchResult = null;
	  DateValidator dateValidator = DateValidator.getInstance();
	  boolean isDateValid = dateValidator.isValid(dateToSearch, "dd/MM/yyy");
	  errorSearch = "";
	  
	  if(!isDateValid){
		  errorSearch = "Format ou date invalide. (Format JJ/MM/AAAA)";
	  } else {  
		  DateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
		  Date date = null;
		  try {
			date = dateFormater.parse(dateToSearch);
		} catch (ParseException e) {
			System.out.println("AdminCouponController - searchByDate - Erreur de parsing de la date. \nMessage: " + e.getMessage());
		}
		  String option = searchDateOption1 + searchDateOption2;
		  System.out.println("AdminCouponController SearchByDate - Option choisie: " + option);
		  switch (option) {
		case "startbefore":
			searchResult = couponBus.getCouponsStartinBefore(date);
			break;
		case "startafter":
			searchResult = couponBus.getCouponsStartingAfter(date);
			break;
		case "starton":
			searchResult = couponBus.getCouponsStartingOn(date);
			break;
		case "finishbefore":
			searchResult = couponBus.getCouponsEndingBefore(date);
			break;
		case "finishafter":
			searchResult = couponBus.getCouponsEndingAfter(date);
			break;
		case "finishon":
			searchResult = couponBus.getCouponsEndingOn(date);
			break;
	
		default:
			break;
		}
		  hasSearchBeenDone = true;
		  
	}
	  return null;
  }
  
  public String searchByRebate() {
	  System.out.println("AdminCouponController - searchByRebate - Method Entry");
	  System.out.println("AdminCouponController searchByRebate - Option choisie: " + rebateSearchOption);
	  searchResult = null;
	  RegexValidator rebateValidator = new RegexValidator("(100)|([1-9]?[0-9]{1}(\\.[0-9]?[0-9]?)?)", false);
	  boolean isValidRebateToSearch = rebateValidator.isValid(rebateToSearch);
	  errorSearch = "";
	  if(!isValidRebateToSearch){
		  errorSearch = "Réduction non valide. Entrer une valeur entre 0 et 100. Deux chiffres après la virgule.";
	  } else {
		  switch (rebateSearchOption) {
		case "lesserthan":
			searchResult = couponBus.getCouponsByRebateLesserThan(Float.valueOf(rebateToSearch));
			break;
		case "greaterthan":
			searchResult = couponBus.getCouponsByRebateGreaterThan(Float.valueOf(rebateToSearch));
			break;
		case "equal":
			searchResult = couponBus.getCouponsByRebateEquals(Float.valueOf(rebateToSearch));
			break;
		default:
			break;
		}
		  hasSearchBeenDone = true;
	  }
	  return null;
  }
  
  public String searchByCat() {
	  System.out.println("AdminCouponController - searchByCat - Method Entry");
	  searchResult = null;
	  errorSearch= "";
	  searchResult = couponBus.getCouponsByCatId(Integer.parseInt(categoryChosenIdSearch));
	  hasSearchBeenDone = true;
	  return null;
  }
    
  
  
  public void resetCreateCouponForm() {
	  codeCoupon = "";
	  rebate = "";
	  startDate = null;
	  endDate = null;
	  description = "";
	  
  }
    
    
    
//  #################
//  	  AJAX
//  #################
    
    
    
    public void ajaxChangeMeta(AjaxBehaviorEvent event) {
        listCategories = catBus.getCategoryByMetaId(Integer.parseInt(metaCategoryChosenId));
    }
    
    public void ajaxChangeMetaSearch(AjaxBehaviorEvent event) {
        listCategoriesSearch = catBus.getCategoryByMetaId(Integer.parseInt(metaCategoryChosenIdSearch));
    }
    
    public void ajaxChangeSearchType(AjaxBehaviorEvent event) {
        
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
        return listCouponsToPrint;
    }

    /**
     * @param paramListCoupons the listCoupons to set
     */
    public void setListCoupons(List<Coupon> paramListCoupons) {
        listCouponsToPrint = paramListCoupons;
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


    /**
     * @return the isCouponCodeValid
     */
    public boolean isCouponCodeValid() {
        return isCouponCodeValid;
    }


    /**
     * @param paramIsCouponCodeValid the isCouponCodeValid to set
     */
    public void setCouponCodeValid(boolean paramIsCouponCodeValid) {
        isCouponCodeValid = paramIsCouponCodeValid;
    }


    /**
     * @return the isRebateValid
     */
    public boolean isRebateValid() {
        return isRebateValid;
    }


    /**
     * @param paramIsRebateValid the isRebateValid to set
     */
    public void setRebateValid(boolean paramIsRebateValid) {
        isRebateValid = paramIsRebateValid;
    }


    /**
     * @return the firstLoad
     */
    public boolean isFirstLoad() {
        return firstLoad;
    }


    /**
     * @param paramFirstLoad the firstLoad to set
     */
    public void setFirstLoad(boolean paramFirstLoad) {
        firstLoad = paramFirstLoad;
    }


    /**
     * @return the listCouponsToPrint
     */
    public List<Coupon> getListCouponsToPrint() {
        return listCouponsToPrint;
    }


    /**
     * @param paramListCouponsToPrint the listCouponsToPrint to set
     */
    public void setListCouponsToPrint(List<Coupon> paramListCouponsToPrint) {
        listCouponsToPrint = paramListCouponsToPrint;
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param paramDescription the description to set
     */
    public void setDescription(String paramDescription) {
        description = paramDescription;
    }


    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }


    /**
     * @param paramStartDate the startDate to set
     */
    public void setStartDate(Date paramStartDate) {
        startDate = paramStartDate;
    }


    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * @param paramEndDate the endDate to set
     */
    public void setEndDate(Date paramEndDate) {
        endDate = paramEndDate;
    }


    /**
     * @return the errorMessageDescription
     */
    public String getErrorMessageDescription() {
        return errorMessageDescription;
    }


    /**
     * @param paramErrorMessageDescription the errorMessageDescription to set
     */
    public void setErrorMessageDescription(String paramErrorMessageDescription) {
        errorMessageDescription = paramErrorMessageDescription;
    }


    /**
     * @return the errorMessageStartDate
     */
    public String getErrorMessageStartDate() {
        return errorMessageStartDate;
    }


    /**
     * @param paramErrorMessageStartDate the errorMessageStartDate to set
     */
    public void setErrorMessageStartDate(String paramErrorMessageStartDate) {
        errorMessageStartDate = paramErrorMessageStartDate;
    }


    /**
     * @return the errorMessageEndDate
     */
    public String getErrorMessageEndDate() {
        return errorMessageEndDate;
    }


    /**
     * @param paramErrorMessageEndDate the errorMessageEndDate to set
     */
    public void setErrorMessageEndDate(String paramErrorMessageEndDate) {
        errorMessageEndDate = paramErrorMessageEndDate;
    }


    /**
     * @return the startDateString
     */
    public String getStartDateString() {
        return startDateString;
    }


    /**
     * @param paramStartDateString the startDateString to set
     */
    public void setStartDateString(String paramStartDateString) {
        startDateString = paramStartDateString;
    }


    /**
     * @return the endDateString
     */
    public String getEndDateString() {
        return endDateString;
    }


    /**
     * @param paramEndDateString the endDateString to set
     */
    public void setEndDateString(String paramEndDateString) {
        endDateString = paramEndDateString;
    }


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getErrorSearch() {
		return errorSearch;
	}


	public void setErrorSearch(String errorSearch) {
		this.errorSearch = errorSearch;
	}


	public String getCodeToSearch() {
		return codeToSearch;
	}


	public void setCodeToSearch(String codeToSearch) {
		this.codeToSearch = codeToSearch;
	}


	public String getSearchDateOption1() {
		return searchDateOption1;
	}


	public void setSearchDateOption1(String searchDateOption1) {
		this.searchDateOption1 = searchDateOption1;
	}


	public String getSearchDateOption2() {
		return searchDateOption2;
	}


	public void setSearchDateOption2(String searchDateOption2) {
		this.searchDateOption2 = searchDateOption2;
	}


	public String getDateToSearch() {
		return dateToSearch;
	}


	public void setDateToSearch(String dateToSearch) {
		this.dateToSearch = dateToSearch;
	}


	public String getRebateSearchOption() {
		return rebateSearchOption;
	}


	public void setRebateSearchOption(String rebateSearchOption) {
		this.rebateSearchOption = rebateSearchOption;
	}


	public String getRebateToSearch() {
		return rebateToSearch;
	}


	public void setRebateToSearch(String rebateToSearch) {
		this.rebateToSearch = rebateToSearch;
	}


	public List<Coupon> getSearchResult() {
		return searchResult;
	}


	public void setSearchResult(List<Coupon> searchResult) {
		this.searchResult = searchResult;
	}


	public boolean isHasSearchBeenDone() {
		return hasSearchBeenDone;
	}


	public void setHasSearchBeenDone(boolean hasSearchBeenDone) {
		this.hasSearchBeenDone = hasSearchBeenDone;
	}


	public List<MetaCategory> getListMetaCategoriesSearch() {
		return listMetaCategoriesSearch;
	}


	public void setListMetaCategoriesSearch(List<MetaCategory> listMetaCategoriesSearch) {
		this.listMetaCategoriesSearch = listMetaCategoriesSearch;
	}


	public String getMetaCategoryChosenIdSearch() {
		return metaCategoryChosenIdSearch;
	}


	public void setMetaCategoryChosenIdSearch(String metaCategoryChosenIdSearch) {
		this.metaCategoryChosenIdSearch = metaCategoryChosenIdSearch;
	}


	public List<Category> getListCategoriesSearch() {
		return listCategoriesSearch;
	}


	public void setListCategoriesSearch(List<Category> listCategoriesSearch) {
		this.listCategoriesSearch = listCategoriesSearch;
	}


	public String getCategoryChosenIdSearch() {
		return categoryChosenIdSearch;
	}


	public void setCategoryChosenIdSearch(String categoryChosenIdSearch) {
		this.categoryChosenIdSearch = categoryChosenIdSearch;
	}
    

    
    
    

}
