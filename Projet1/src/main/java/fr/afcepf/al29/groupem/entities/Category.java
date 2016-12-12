package fr.afcepf.al29.groupem.entities;

public class Category {
	
	private int id;
	private String name;
	private int metaCategoryId;
	
	public Category() {
	}
	
	
	public Category(int id, String name, int metaCategoryId) {
		this.id = id;
		this.name = name;
		this.metaCategoryId = metaCategoryId;
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
	public int getMetaCategoryId() {
		return metaCategoryId;
	}
	public void setMetaCategoryId(int metaCategoryId) {
		this.metaCategoryId = metaCategoryId;
	}
	
	

}
