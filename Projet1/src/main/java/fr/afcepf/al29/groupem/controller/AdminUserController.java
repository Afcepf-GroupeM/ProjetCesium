package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.User;

@ManagedBean
@Component
public class AdminUserController {
    
    private String searchType;
    private String searchInput;
    private List<User> searchResult;
    private String messageErrorSearch;
    
    private String adminSearchMessageErrorClass;
    private boolean firstSearchDone = false;
    
    
    private HashMap<String, String> searchTypeMap;
    private List<String> searchTypeArray;
    
    private int pageToPrint = 0;
    private HashMap<Integer, List<User>> pagesOfSearchResults = new HashMap<>();
    private List<Integer> numberOfPagesOfResults;
	private int idUser;
	private User user;
    
    @Autowired
    private UserBusApi userBus;
    
    
    public void initAdminUser(ComponentSystemEvent c) {
    	idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		user = userBus.getUserById(idUser);
        searchTypeArray = new ArrayList<>();
        searchTypeArray.add("1");
        searchTypeArray.add("2");
        searchTypeArray.add("3");
        searchTypeArray.add("4");
        searchTypeMap = new HashMap<>();
        searchTypeMap.put("1", "Id");
        searchTypeMap.put("2", "Email");
        searchTypeMap.put("3", "Nom");
        searchTypeMap.put("4", "Prénom"); 
        
        
        
    
    }
    
    public String searchUsers() {
        firstSearchDone = true;
        boolean isInputValid = false;
        switch (searchType) {
        case "1": //search By ID
            try { 
                Integer.parseInt(searchInput);
                isInputValid = true;
            } catch(NumberFormatException e) { 
                messageErrorSearch = "Id invalide.";
                adminSearchMessageErrorClass = "adminSearchErrorMessage";
            } catch(NullPointerException e) {
                messageErrorSearch = "Id invalide.";
                adminSearchMessageErrorClass = "adminSearchErrorMessage";
            }           
            break;
        case "2": //search By email
            EmailValidator emailValidator = EmailValidator.getInstance();
             isInputValid = emailValidator.isValid(searchInput);
             messageErrorSearch = "E-mail invalide.";  
             adminSearchMessageErrorClass = "adminSearchErrorMessage";
            break;
        case "3": //search By lastName
            isInputValid = !searchInput.isEmpty();
            messageErrorSearch = "Champ vide.";
            adminSearchMessageErrorClass = "adminSearchErrorMessage";
            break;
        case "4": //search By firtsName
            isInputValid = !searchInput.isEmpty();
            messageErrorSearch = "Champ vide.";
            adminSearchMessageErrorClass = "adminSearchErrorMessage";
            break;
        default:
            break;
        }       
        if(isInputValid){
            messageErrorSearch = "";
            searchResult = userBus.searchUsers(searchInput, searchType);
            if(searchResult.isEmpty()){
                adminSearchMessageErrorClass = "adminSearchNotFoundMessage";
                messageErrorSearch = "Aucun utilisateur trouvé pour \""+ searchInput +"\".";
            }else {
                numberOfPagesOfResults = new ArrayList<>();
                int pageNumber = 1;                
                int compteurUser = 0;
                List<User> listUser = new ArrayList<>();
                for (User user : searchResult) {
                    if(compteurUser < 10) {
                        listUser.add(user);
                        compteurUser++;
                    } else {
                        pagesOfSearchResults.put(pageNumber, listUser);                  
                        numberOfPagesOfResults.add(pageNumber);
                        pageNumber++;
                        listUser = new ArrayList<>();
                        listUser.add(user);
                        compteurUser=1;
                    }
                }
                pagesOfSearchResults.put(pageNumber, listUser);
                numberOfPagesOfResults.add(pageNumber);
                
                
                
//                Iterator<User> itUser = searchResult.iterator();
//                while (itUser.hasNext()) {
//                    int nbUserAdded = 0;
//                    List<User> listUser = new ArrayList<>();
//                    while (nbUserAdded <= 10) {
//                        listUser.add((User) itUser.next());
//                        nbUserAdded++;
//                    }
//                    pagesOfSearchResults.put(pageNumber, listUser);
//                    numberOfPagesOfResults.add(pageNumber);
//                    pageNumber++;
//                }
                
                
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

    /**
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * @param paramSearchType the searchType to set
     */
    public void setSearchType(String paramSearchType) {
        searchType = paramSearchType;
    }

    /**
     * @return the searchInput
     */
    public String getSearchInput() {
        return searchInput;
    }

    /**
     * @param paramSearchInput the searchInput to set
     */
    public void setSearchInput(String paramSearchInput) {
        searchInput = paramSearchInput;
    }

    /**
     * @return the searchResult
     */
    public List<User> getSearchResult() {
        return searchResult;
    }

    /**
     * @param paramSearchResult the searchResult to set
     */
    public void setSearchResult(List<User> paramSearchResult) {
        searchResult = paramSearchResult;
    }

    /**
     * @return the messageErrorSearch
     */
    public String getMessageErrorSearch() {
        return messageErrorSearch;
    }

    /**
     * @param paramMessageErrorSearch the messageErrorSearch to set
     */
    public void setMessageErrorSearch(String paramMessageErrorSearch) {
        messageErrorSearch = paramMessageErrorSearch;
    }

    /**
     * @return the searchTypeMap
     */
    public HashMap<String, String> getSearchTypeMap() {
        return searchTypeMap;
    }

    /**
     * @param paramSearchTypeMap the searchTypeMap to set
     */
    public void setSearchTypeMap(HashMap<String, String> paramSearchTypeMap) {
        searchTypeMap = paramSearchTypeMap;
    }

    /**
     * @return the userBus
     */
    public UserBusApi getUserBus() {
        return userBus;
    }

    /**
     * @param paramUserBus the userBus to set
     */
    public void setUserBus(UserBusApi paramUserBus) {
        userBus = paramUserBus;
    }

    /**
     * @return the searchTypeArray
     */
    public List<String> getSearchTypeArray() {
        return searchTypeArray;
    }

    /**
     * @param paramSearchTypeArray the searchTypeArray to set
     */
    public void setSearchTypeArray(List<String> paramSearchTypeArray) {
        searchTypeArray = paramSearchTypeArray;
    }

    /**
     * @return the adminSearchMessageErrorClass
     */
    public String getAdminSearchMessageErrorClass() {
        return adminSearchMessageErrorClass;
    }

    /**
     * @param paramAdminSearchMessageErrorClass the adminSearchMessageErrorClass to set
     */
    public void setAdminSearchMessageErrorClass(
            String paramAdminSearchMessageErrorClass) {
        adminSearchMessageErrorClass = paramAdminSearchMessageErrorClass;
    }

    /**
     * @return the firstSearchDone
     */
    public boolean isFirstSearchDone() {
        return firstSearchDone;
    }

    /**
     * @param paramFirstSearchDone the firstSearchDone to set
     */
    public void setFirstSearchDone(boolean paramFirstSearchDone) {
        firstSearchDone = paramFirstSearchDone;
    }

    /**
     * @return the pageToPrint
     */
    public int getPageToPrint() {
        return pageToPrint;
    }

    /**
     * @param paramPageToPrint the pageToPrint to set
     */
    public void setPageToPrint(int paramPageToPrint) {
        pageToPrint = paramPageToPrint;
    }

    /**
     * @return the pagesOfSearchResults
     */
    public HashMap<Integer, List<User>> getPagesOfSearchResults() {
        return pagesOfSearchResults;
    }

    /**
     * @param paramPagesOfSearchResults the pagesOfSearchResults to set
     */
    public void setPagesOfSearchResults(
            HashMap<Integer, List<User>> paramPagesOfSearchResults) {
        pagesOfSearchResults = paramPagesOfSearchResults;
    }

    /**
     * @return the numberOfPagesOfResults
     */
    public List<Integer> getNumberOfPagesOfResults() {
        return numberOfPagesOfResults;
    }

    /**
     * @param paramNumberOfPagesOfResults the numberOfPagesOfResults to set
     */
    public void setNumberOfPagesOfResults(
            List<Integer> paramNumberOfPagesOfResults) {
        numberOfPagesOfResults = paramNumberOfPagesOfResults;
    }

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
   
    
    
    
    
    

}
