package br.tips.core.model.entities.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TopicData extends GenericEntity{
	
	private GenericEntity model;
	private List<GenericEntity> previous, next, parents, parentOf, precedes, specializedBy, equivalent; 

 
	
	public TopicData(String topicJson){
		previous = new ArrayList<>();
		next = new ArrayList<>();
		parents  = new ArrayList<>();
		parentOf = new ArrayList<>();
		precedes = new ArrayList<>();
		specializedBy = new ArrayList<>();
		equivalent = new ArrayList<>();
		this.parseJson(topicJson);
		
	}

	private void parseJson(String topicJson) {
		GenericEntity OtherNode;
		JSONObject jobj = new JSONObject(topicJson);
		
		super.setId(jobj.getString("id"));
		super.setLabel(jobj.getString("label"));
		
		JSONArray arrayOfRelationships = jobj.getJSONArray("relationships");
		
		for (int i = 0; i < arrayOfRelationships.length(); i++) {
			JSONObject anElement = arrayOfRelationships.getJSONObject(i);
			OtherNode = new GenericEntity();
			OtherNode.setId(anElement.getString("id"));
			OtherNode.setLabel(anElement.getString("label"));
			switch (anElement.getString("type")) {
			case "HASTOPIC":
				this.setModel(OtherNode);
				break;
			case "PREVIOUS":
				previous.add(OtherNode);
				break;
			case "NEXT":
				next.add(OtherNode);
				break;
			case "PARENT":
				parents.add(OtherNode);
				break;
			case "PARENTOF":
				parentOf.add(OtherNode);
				break;
			case "PRECEDES":
				precedes.add(OtherNode);
				break;
			case "SPECIALIZEDBY":
				specializedBy.add(OtherNode);
				break;
			case "EQUIVALENT":
				equivalent.add(OtherNode);
				break;
			default:
				break;
			}
		}
		
	}
	
	public GenericEntity getModel() {
		return model;
	}

	public void setModel(GenericEntity model) {
		this.model = model;
	}

	public List<GenericEntity> getPrevious() {
		return previous;
	}

	public void setPrevious(List<GenericEntity> previous) {
		this.previous = previous;
	}

	public List<GenericEntity> getNext() {
		return next;
	}

	public void setNext(List<GenericEntity> next) {
		this.next = next;
	}

	public List<GenericEntity> getParents() {
		return parents;
	}

	public void setParents(List<GenericEntity> parents) {
		this.parents = parents;
	}

	public List<GenericEntity> getParentOf() {
		return parentOf;
	}

	public void setParentOf(List<GenericEntity> parentOf) {
		this.parentOf = parentOf;
	}

	public List<GenericEntity> getPrecedes() {
		return precedes;
	}

	public void setPrecedes(List<GenericEntity> precedes) {
		this.precedes = precedes;
	}

	public List<GenericEntity> getSpecializedBy() {
		return specializedBy;
	}

	public void setSpecializedBy(List<GenericEntity> specializedBy) {
		this.specializedBy = specializedBy;
	}

	public List<GenericEntity> getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(List<GenericEntity> equivalent) {
		this.equivalent = equivalent;
	}

	@Override
	public String toString() {
		return "TopicData [id ="+super.getId()+", label="+super.getLabel()+", model=" + model + ", previous=" + previous
				+ ", next=" + next + ", parents=" + parents + ", parentOf="
				+ parentOf + ", precedes=" + precedes + ", specializedBy="
				+ specializedBy + ", equivalent=" + equivalent + "]";
	}
	
	
	

}
