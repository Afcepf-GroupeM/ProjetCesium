package fr.afcepf.al29.groupem.controller.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class SessionListener implements PhaseListener{


	private static final long serialVersionUID = -9141102809722263950L;

		public void afterPhase(PhaseEvent event) {
		 
			FacesContext facesContextOriginal = event.getFacesContext();
			FacesContext facesContext = event.getFacesContext();
			String currentPage = facesContext.getViewRoot().getViewId();
			 
		
//			 ADD PAGE WITH LOGIN IN REQUIERED HERE
			boolean isPageWithLoginInRequiered = (currentPage.lastIndexOf("secret.xhtml") > -1) 
//												 || (currentPage.lastIndexOf("page.xhtml") > -1) 
												 || (currentPage.lastIndexOf("addressManager.xhtml") > -1) 
												 || (currentPage.lastIndexOf("addAddress.xhtml") > -1) 
												 || (currentPage.lastIndexOf("updateAddress.xhtml") > -1) 
												 ;
			
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			
//			ALL AUTHORIZED BUT THE ONE WE FORBIDE
//			--------------------------------------
			
			if(isPageWithLoginInRequiered){
				if(session==null){
					nh.handleNavigation(facesContextOriginal, null, "session-expired");
				} else {
					Object currentUser = session.getAttribute("userid");
					if (!(currentUser == null || currentUser == "")) {
						nh.handleNavigation(facesContextOriginal, null, null);
					} else{
						nh.handleNavigation(facesContextOriginal, null, "error-needtobeconnected.xhtml");				
					}
				}			
			} else {
				nh.handleNavigation(facesContextOriginal, null, null);	
			}
	
			
//			ALL FORBIDDEN BUT THE ONE WE AUTHORISE
//			--------------------------------------
			
//			if(session==null){
//				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//				if(isPageWithoutLoginInRequiered){
//					// If login not requiered, stay on the page
//					nh.handleNavigation(facesContext, null, "null");
//				} else {
//					// If login requiered, show "Session expired"
//					nh.handleNavigation(facesContext, null, "session-expired");
//					}
//				} else{
//					Object currentUser = session.getAttribute("userid");
//					if (!isPageWithoutLoginInRequiered && (currentUser == null || currentUser == "")) {
//						NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//						nh.handleNavigation(facesContext, null, "login");
//					}
//				}
		}	
		 
		
		public void beforePhase(PhaseEvent event) {
		 
		}
		 
		public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
		}	
}
	

