package fr.afcepf.al29.groupem.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@SessionScoped
@Component
@ManagedBean
public class testController {
    
    private String userId;
    private String sessionUuid;
    
    
    
    
//    
//    @PostConstruct
//    public void initTest(){
//        
//    }
        
        
        
    public String valideLol(){
       
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(false); //false return null if session doesn't exists
        sessionUuid = session.getId();
        
        userId = String.valueOf(extContext.getSessionMap().get("userid"));
        sessionUuid = session.getId();
        
        return null;
     
        
    }




    /**
     * @return the message1
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param paramMessage1 the message1 to set
     */
    public void setUserId(String paramMessage1) {
        userId = paramMessage1;
    }




    /**
     * @return the sessionUuid
     */
    public String getSessionUuid() {
        return sessionUuid;
    }




    /**
     * @param paramSessionUuid the sessionUuid to set
     */
    public void setSessionUuid(String paramSessionUuid) {
        sessionUuid = paramSessionUuid;
    }
    
    
    
   

}
