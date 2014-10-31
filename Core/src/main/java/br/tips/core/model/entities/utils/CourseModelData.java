package br.tips.core.model.entities.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CourseModelData extends GenericEntity{

	private GenericEntity root;
	private GenericEntity domain;
	private String description;
	private List<GenericEntity> topics;
	
	public CourseModelData(String jsonInfo){
		topics = new ArrayList<>();
		this.root = new GenericEntity();
		this.domain = new GenericEntity();
		this.parseJson(jsonInfo);
	}

	private void parseJson(String jsonInfo) {
		GenericEntity OtherNode;
		JSONObject jobj = new JSONObject(jsonInfo);
		
		super.setId(jobj.getString("id"));
		super.setLabel(jobj.getString("label"));
		this.setDescription(jobj.getString("description"));

		
		JSONArray arrayOfRelationships = jobj.getJSONArray("relationships");
		
		for (int i = 0; i < arrayOfRelationships.length(); i++) {
			JSONObject anElement = arrayOfRelationships.getJSONObject(i);
			OtherNode = new GenericEntity();
			OtherNode.setId(anElement.getString("id"));
			OtherNode.setLabel(anElement.getString("label"));
			switch (anElement.getString("type")) {
			case "HASTOPIC":
				topics.add(OtherNode);
				break;
			case "ROOTTOPIC":
				root = OtherNode;
				break;
			case "BELONGSTO":
				domain = OtherNode;
				break;
			default:
				System.out.println("Relationship mismatch!");
				break;
			}
				
			
		}
		
	}

	public GenericEntity getRoot() {
		return root;
	}

	public void setRoot(GenericEntity root) {
		this.root = root;
	}

	public List<GenericEntity> getTopics() {
		return topics;
	}

	public void setTopics(List<GenericEntity> topics) {
		this.topics = topics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GenericEntity getDomain() {
		return domain;
	}

	public void setDomain(GenericEntity domain) {
		this.domain = domain;
	}
	
	
}
