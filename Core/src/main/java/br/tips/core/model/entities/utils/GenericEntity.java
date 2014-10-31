package br.tips.core.model.entities.utils;

public class GenericEntity {

	private String id;
	private String label;
	
	public GenericEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "(" + id + ", " + label + ")";
	}
	
	
	
	
	
}
