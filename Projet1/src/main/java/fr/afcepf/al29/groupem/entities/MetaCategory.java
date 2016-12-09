package fr.afcepf.al29.groupem.entities;

public class MetaCategory {
	
	private int id;
	private String name;
	
	public MetaCategory() {
		// TODO Auto-generated constructor stub
	}
	
	public MetaCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	

}
