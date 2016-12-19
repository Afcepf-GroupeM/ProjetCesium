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
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Map<String,Object> userLogged = fc.getExternalContext().getSessionMap();
		
		if (!params.isEmpty()){
			int addressId = Integer.parseInt(params.get("addressId"));
			addressBus.disableAddress(addressId);
			//le paramètre de la map (addressId) est toujours présent donc si on actualise la page ca invalidera une address que l'on aura set à nouveau à valid via un update sur la base
		}
		
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