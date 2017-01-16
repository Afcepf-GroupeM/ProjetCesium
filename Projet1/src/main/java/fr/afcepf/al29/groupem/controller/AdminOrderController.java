package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.User;

@ManagedBean
@Component
public class AdminOrderController {

	
	
	 private String searchType;
	    private String searchInput;
	    private List<Order> searchResult;
	    private String messageErrorSearch;
	    
	    private String adminSearchMessageErrorClass;
	    private boolean firstSearchDone = false;
	    
	    
	    private HashMap<String, String> searchTypeMap;
	    private List<String> searchTypeArray;
	    
	    private int pageToPrint = 0;
	    private HashMap<Integer, List<Order>> pagesOfSearchResults = new HashMap<>();
	    private List<Integer> numberOfPagesOfResults;
	    
	    @Autowired
	    private OrderBusApi orderBus;
	    
	    
	    public void initAdminOrder(ComponentSystemEvent c) {
	        searchTypeArray = new ArrayList<>();
	        searchTypeArray.add("1");
	        searchTypeArray.add("2");
	        searchTypeMap = new HashMap<>();
	        searchTypeMap.put("1", "Numero");
	        searchTypeMap.put("2", "Etat");
	        
	        
	        
	        
	    
	    }
	    
	    public String searchOrders() {
	        firstSearchDone = true;
	        boolean isInputValid = false;
	        switch (searchType) {
	        case "1": //search By trackingNumber
	            try { 
	                String searchInput;
	                isInputValid = true;
	            } catch(NumberFormatException e) { 
	                messageErrorSearch = "Numéro de commande invalide.";
	                adminSearchMessageErrorClass = "adminSearchErrorMessage";
	            } catch(NullPointerException e) {
	                messageErrorSearch = "Numéro de commande invalide.";
	                adminSearchMessageErrorClass = "adminSearchErrorMessage";
	            }           
	            break;
	        case "2": //search By state
	        	isInputValid = !searchInput.isEmpty();
	            messageErrorSearch = "Champ vide.";
	            adminSearchMessageErrorClass = "adminSearchErrorMessage";
	            break;
	        default:
	            break;
	        }       
	        if(isInputValid){
	            messageErrorSearch = "";
	            searchResult = orderBus.searchOrders(searchInput, searchType);
	            if(searchResult.isEmpty()){
	                adminSearchMessageErrorClass = "adminSearchNotFoundMessage";
	                messageErrorSearch = "Aucune commande trouvée pour \""+ searchInput +"\".";
	            }else {
	                numberOfPagesOfResults = new ArrayList<>();
	                int pageNumber = 1;                
	                int compteurOrder = 0;
	                List<Order> listOrder = new ArrayList<>();
	                for (Order order : searchResult) {
	                    if(compteurOrder < 10) {
	                        listOrder.add(order);
	                        compteurOrder++;
	                    } else {
	                        pagesOfSearchResults.put(pageNumber, listOrder);                  
	                        numberOfPagesOfResults.add(pageNumber);
	                        pageNumber++;
	                        listOrder = new ArrayList<>();
	                        listOrder.add(order);
	                        compteurOrder=1;
	                    }
	                }
	                pagesOfSearchResults.put(pageNumber, listOrder);
	                numberOfPagesOfResults.add(pageNumber);
	                
	                
	                
	                pageToPrint = 1;               
	        }       
	                   
	        }       
	        return null;
	    }
	    
	    
	    public String changePage() {
	        pageToPrint = Integer.valueOf(getParam("pageNumber"));      
	        return null;
	    }
	    
	    
	    protected String getParam(String param) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	        String result = map.get(param);
	        return result;
	    }

		public String getSearchType() {
			return searchType;
		}

		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}

		public String getSearchInput() {
			return searchInput;
		}

		public void setSearchInput(String searchInput) {
			this.searchInput = searchInput;
		}

		public List<Order> getSearchResult() {
			return searchResult;
		}

		public void setSearchResult(List<Order> searchResult) {
			this.searchResult = searchResult;
		}

		public String getMessageErrorSearch() {
			return messageErrorSearch;
		}

		public void setMessageErrorSearch(String messageErrorSearch) {
			this.messageErrorSearch = messageErrorSearch;
		}

		public String getAdminSearchMessageErrorClass() {
			return adminSearchMessageErrorClass;
		}

		public void setAdminSearchMessageErrorClass(String adminSearchMessageErrorClass) {
			this.adminSearchMessageErrorClass = adminSearchMessageErrorClass;
		}

		public boolean isFirstSearchDone() {
			return firstSearchDone;
		}

		public void setFirstSearchDone(boolean firstSearchDone) {
			this.firstSearchDone = firstSearchDone;
		}

		public HashMap<String, String> getSearchTypeMap() {
			return searchTypeMap;
		}

		public void setSearchTypeMap(HashMap<String, String> searchTypeMap) {
			this.searchTypeMap = searchTypeMap;
		}

		public List<String> getSearchTypeArray() {
			return searchTypeArray;
		}

		public void setSearchTypeArray(List<String> searchTypeArray) {
			this.searchTypeArray = searchTypeArray;
		}

		public int getPageToPrint() {
			return pageToPrint;
		}

		public void setPageToPrint(int pageToPrint) {
			this.pageToPrint = pageToPrint;
		}

		public HashMap<Integer, List<Order>> getPagesOfSearchResults() {
			return pagesOfSearchResults;
		}

		public void setPagesOfSearchResults(HashMap<Integer, List<Order>> pagesOfSearchResults) {
			this.pagesOfSearchResults = pagesOfSearchResults;
		}

		public List<Integer> getNumberOfPagesOfResults() {
			return numberOfPagesOfResults;
		}

		public void setNumberOfPagesOfResults(List<Integer> numberOfPagesOfResults) {
			this.numberOfPagesOfResults = numberOfPagesOfResults;
		}

		public OrderBusApi getOrderBus() {
			return orderBus;
		}

		public void setOrderBus(OrderBusApi orderBus) {
			this.orderBus = orderBus;
		}
	    
	    
	    

}
