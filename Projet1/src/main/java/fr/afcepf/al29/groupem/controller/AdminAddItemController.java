package fr.afcepf.al29.groupem.controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Scope("session")
@Component
@ManagedBean
public class AdminAddItemController{
	private int idUser;
	private String name;
	private String reference;
	private List<MetaCategory> listeMetaCategory;
	//private List<String> listeNameMetaCategory;
	private int idMetaCategory;
	private MetaCategory metaCategory;	
	private List<Category> listeCategory;
	private Category category = new Category();
	private int idCategory;
	private float price;
	private int quantity;
	private String description;
	private String urlImageSource;
	private String urlImageTarget;
	private String message;	
	private Part file;
	private String fileContent;

	@Autowired
	private CategoryBusApi categoryBusApi;
	
	@Autowired
	private ItemBusApi  itemBusApi;	
	
	public void init(ComponentSystemEvent e){
		message ="";
		idUser= (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
		System.out.println("****************************idUser = "+idUser);
		listeMetaCategory = categoryBusApi.getAllMetaCategory();
		listeCategory = categoryBusApi.getCategoryByMetaId(1);
	}
	
	public String importerUnImage(){
		/*
		final JFileChooser fileChooser = new JFileChooser();
		 int returnVal = fileChooser.showOpenDialog(this);
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fileChooser.getSelectedFile();
		        try {
		          // What to do with the file, e.g. display it in a TextArea
		          textarea.read( new FileReader( file.getAbsolutePath() ), null );
		        } catch (IOException ex) {
		          System.out.println("problem accessing file"+file.getAbsolutePath());
		        }
		    } else {
		        System.out.println("File access cancelled by user.");
		    }
		    */
		
		//Path pathCompletedeImage = Paths.get(xxx);
		//Files.copy(part.getInputStream(), pathCompletedeImage, StandardCopyOption.REPLACE_EXISTING);
		
		//FacesContext facesContext = FacesContext.getCurrentInstance();
		//sfacesContext.getExternalContext().getRealPath(path)
		/*
		String url = "C:/Users/Stagiaire/Pictures/MixHighTech";
		
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            System.out.println("after desktop*******************");
            try {
                desktop.browse(new URI(url));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }    
        */
		
		return urlImageSource;
	}
	
	public String saveUnImage(){
		/*
		System.out.println("entrée de fonction**************");
		try {
			//file.write("C:/Users/Stagiaire/Pictures/MixHighTech"+getFilename(file));
			
			InputStream inputStream = file.getInputStream();	
			String filename = getFilename(file);
			System.out.println("****filename=" + filename);
	        FileOutputStream outputStream = new FileOutputStream("C://Users//Stagiaire//Pictures//MixHighTech//"+getFilename(file));
	         System.out.println("***************filename"+getFilename(file));
	        byte[] buffer = new byte[4096];        
	        int bytesRead = 0;
	        while(true) {                        
	            bytesRead = inputStream.read(buffer);
	            if(bytesRead > 0) {
	                outputStream.write(buffer, 0, bytesRead);
	            }else {
	                break;
	            }                       
	        }
	        outputStream.close();
	        inputStream.close();
	       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  
		*/
	    return null;  //pour rester sur la même page
		
		//TODO: save un image into the folder chosen here and return urlImageTarget
		/*try {
		      fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }*/
		//return urlImageTarget;
		
	}
	
	private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
	
	public Item ajouterUnItem(){
		System.out.println("***controller: id category ="+idCategory);
		category = categoryBusApi.getCategoryById(idCategory);			
		System.out.println("***controller:category ="+category);
		Item item = new Item();
		item.setReference(reference);
		item.setName(name);
		item.setDescription(description);
		item.setPrice(price);
		item.setStock(quantity);
		item.setCategory(category);		
		return itemBusApi.createItem(item);
	}
	
	//getteurs et setteurs:	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<MetaCategory> getListeMetaCategory() {
		return listeMetaCategory;
	}

	public void setListeMetaCategory(List<MetaCategory> listeMetaCategory) {
		this.listeMetaCategory = listeMetaCategory;
	}

	public MetaCategory getMetaCategory() {
		return metaCategory;
	}

	public void setMetaCategory(MetaCategory metaCategory) {
		this.metaCategory = metaCategory;
	}	

	public List<Category> getListeCategory() {
		return listeCategory;
	}

	public void setListeCategory(List<Category> listeCategory) {
		this.listeCategory = listeCategory;
	}	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImageSource() {
		return urlImageSource;
	}

	public void setUrlImageSource(String urlImageSource) {
		this.urlImageSource = urlImageSource;
	}

	public String getUrlImageTarget() {
		return urlImageTarget;
	}


	public void setUrlImageTarget(String urlImageTarget) {
		this.urlImageTarget = urlImageTarget;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdMetaCategory() {
		return idMetaCategory;
	}

	public void setIdMetaCategory(int idMetaCategory) {
		this.idMetaCategory = idMetaCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
	
	
}
