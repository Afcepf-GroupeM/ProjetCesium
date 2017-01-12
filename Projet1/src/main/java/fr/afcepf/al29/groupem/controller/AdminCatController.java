package fr.afcepf.al29.groupem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@ManagedBean
@Component
@RequestScoped
public class AdminCatController {
	
	
	private List<Category> listCategory;
	private List<MetaCategory> listMetaCategory;
	private MetaCategory metaCatChosen;
	private boolean hasOneMetaCatBeenChosen = false;
	private HashMap<Integer, Integer> metaMap = new HashMap<>();
	private HashMap<Integer, Integer> catMap = new HashMap<>();
	
	
	private String newMetaCatName;
	private String newCatName;
	private String idMetaCatNewCat;
	
	private String messageAddMetaCat;
	private String messageAddCat;
	
	
	@Autowired
	private CategoryBusApi catBus;
	
	
	
	
	
	public void initAdminCat(ComponentSystemEvent c){
		listMetaCategory = catBus.getAllMetaCategory();
		for (MetaCategory metaCategory : listMetaCategory) {
			metaMap.put(metaCategory.getId(), catBus.numberOfCategoriesInMeta(metaCategory.getId()));
		}
		
	}
	
	public String printCategories(){
		int idMetaCat = Integer.parseInt(getParam("idMetaCat"));
		metaCatChosen = catBus.getMetaCategoryById(idMetaCat);
		listCategory = catBus.getCategoryByMetaId(idMetaCat);
		
		for (Category category : listCategory) {
			catMap.put(category.getId(), catBus.numberOfItemsInCat(category.getId()));
		}
		hasOneMetaCatBeenChosen = true;
		return null;
	}
	
	
	public String addMetaCat(){
	    
	    if(newMetaCatName.isEmpty()){
	        messageAddMetaCat = "Erreur: nom vide.";
	    } else {
	        MetaCategory metCat = new MetaCategory();
	        metCat.setName(newMetaCatName);
	        metCat = catBus.createMetaCategory(metCat);
	        if(metCat.getId() != null){
	            messageAddMetaCat = "MetaCategorie ajoutée!";
	        } else {
	            messageAddMetaCat = "/!\\ Erreur lors de l'ajout.";
	        }
	    }
	    newCatName = "";
	    return null;
	}
	
	public String addCat(){
	    if(newCatName.isEmpty()){
	        messageAddCat = "Erreur: nom vide.";
	    } else {
	        Category cat = new Category();
	        cat.setName(newCatName);
	        cat.setMetaCategory(catBus.getMetaCategoryById(Integer.valueOf(idMetaCatNewCat)));
	        cat = catBus.createCategory(cat);
	        if(cat.getId() != null){
	            messageAddCat = "Catégorie ajoutée!";
	        } else {
	            messageAddCat = "/!\\ Erreur lors de l'ajout.";
	        }
	    }
	    newCatName = "";
        return null;
    }
	
	
	protected String getParam(String param) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String result = map.get(param);
		return result;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public List<MetaCategory> getListMetaCategory() {
		return listMetaCategory;
	}

	public void setListMetaCategory(List<MetaCategory> listMetaCategory) {
		this.listMetaCategory = listMetaCategory;
	}

	public CategoryBusApi getCatBus() {
		return catBus;
	}

	public void setCatBus(CategoryBusApi catBus) {
		this.catBus = catBus;
	}

	public MetaCategory getMetaCatChosen() {
		return metaCatChosen;
	}

	public void setMetaCatChosen(MetaCategory metaCatChosen) {
		this.metaCatChosen = metaCatChosen;
	}

	public boolean isHasOneMetaCatBeenChosen() {
		return hasOneMetaCatBeenChosen;
	}

	public void setHasOneMetaCatBeenChosen(boolean hasOneMetaCatBeenChosen) {
		this.hasOneMetaCatBeenChosen = hasOneMetaCatBeenChosen;
	}

	public HashMap<Integer, Integer> getMetaMap() {
		return metaMap;
	}

	public void setMetaMap(HashMap<Integer, Integer> metaMap) {
		this.metaMap = metaMap;
	}

	public HashMap<Integer, Integer> getCatMap() {
		return catMap;
	}

	public void setCatMap(HashMap<Integer, Integer> catMap) {
		this.catMap = catMap;
	}

    /**
     * @return the newMetaCatName
     */
    public String getNewMetaCatName() {
        return newMetaCatName;
    }

    /**
     * @param paramNewMetaCatName the newMetaCatName to set
     */
    public void setNewMetaCatName(String paramNewMetaCatName) {
        newMetaCatName = paramNewMetaCatName;
    }

    /**
     * @return the newCatName
     */
    public String getNewCatName() {
        return newCatName;
    }

    /**
     * @param paramNewCatName the newCatName to set
     */
    public void setNewCatName(String paramNewCatName) {
        newCatName = paramNewCatName;
    }

    /**
     * @return the idMetaCatNewCat
     */
    public String getIdMetaCatNewCat() {
        return idMetaCatNewCat;
    }

    /**
     * @param paramIdMetaCatNewCat the idMetaCatNewCat to set
     */
    public void setIdMetaCatNewCat(String paramIdMetaCatNewCat) {
        idMetaCatNewCat = paramIdMetaCatNewCat;
    }

    /**
     * @return the messageAddMetaCat
     */
    public String getMessageAddMetaCat() {
        return messageAddMetaCat;
    }

    /**
     * @param paramMessageAddMetaCat the messageAddMetaCat to set
     */
    public void setMessageAddMetaCat(String paramMessageAddMetaCat) {
        messageAddMetaCat = paramMessageAddMetaCat;
    }

    /**
     * @return the messageAddCat
     */
    public String getMessageAddCat() {
        return messageAddCat;
    }

    /**
     * @param paramMessageAddCat the messageAddCat to set
     */
    public void setMessageAddCat(String paramMessageAddCat) {
        messageAddCat = paramMessageAddCat;
    }
	
	
	
	

	

	
	
}
