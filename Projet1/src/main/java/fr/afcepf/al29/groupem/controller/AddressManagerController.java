package fr.afcepf.al29.groupem.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.ComplementAddress;
import fr.afcepf.al29.groupem.entities.User;

@Scope("session")
@Component
@ManagedBean
public class AddressManagerController {

	private String complement;
	private User currentUser;
	List<Address> userAddresses;
	
	@Autowired
	private UserBusApi userBus;
	@Autowired
	private AddressBusApi addressBus;
	
	public void init(ComponentSystemEvent event){
		Map<String,Object> userLogged = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		int userId = (Integer) userLogged.get("userid");
		
		currentUser = userBus.getUserById(userId);
		userAddresses = addressBus.getAddressesByUserId(currentUser.getId());
		
		for (Address address : userAddresses){
			if (address.getComplement() == null){
				complement = "";
			}
			else{
				ComplementAddress[] caList = ComplementAddress.values();
				for (ComplementAddress ca : caList){
					if (ca.equals(address.getComplement())){
						complement = ca.name();
					}
				}
			}
		}
	}
	
	public String updateAddress(){
		int addressId = Integer.parseInt(getParam("addressId"));
		
		return "updateAddress?faces-redirect=true&addressId=" + addressId;
	}
	
	public String deleteAddress(){
		int addressId = Integer.parseInt(getParam("addressId"));
		
		addressBus.disableAddress(addressId);
		
		return "myaccount?faces-redirect=true";
	}
	
	public String getParam(String param){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String result = params.get(param);
		
		return result;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<Address> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}
}