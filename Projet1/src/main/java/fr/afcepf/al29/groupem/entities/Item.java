package fr.afcepf.al29.groupem.entities;

public class Item {
	
	private int id;
	private String name;
	private String description;
	private int stock;
	private String reference;
	private float price;
	private int categoryId;
	private String imagePath;
	
	public Item() {
		
	}
	
	public Item(int id, String name, String description, int stock, String reference, float price, int categoryId, String imagePath) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.reference = reference;
		this.price = price;
		this.categoryId = categoryId;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	

}
