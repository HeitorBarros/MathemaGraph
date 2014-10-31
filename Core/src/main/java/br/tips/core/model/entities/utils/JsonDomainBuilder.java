package br.tips.core.model.entities.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import br.tips.core.graph.model.Node;
import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.Domain;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.persistence.CypherQuery;

public class JsonDomainBuilder {
	
	private Domain domain;
	private Node node;
	private JSONObject object; 
	private JSONArray relArray;
	
	public JsonDomainBuilder(Node n) {
		node = n;

		String fullUri = node.getUri().toString();
		String id= fullUri.substring(fullUri.lastIndexOf("/")+1);
		domain = EntityBuilder.buildDomain(id);
		
		object = new JSONObject();
		relArray = new JSONArray();
	}
	
	private String generateJson(){
		//TODO
		String id = domain.getId();
		object.put("id", id);
		object.put("label", domain.getLabel());
		object.put("description", ""+domain.getDescription());
		
		

		
		this.incomingRelationshipsToJson(new CypherQuery().queryIncomingRelationships(id));
		
		object.put("relationships", relArray);
		return object.toString();
	}

	private void outgoingRelationshipsToJson(String queryOutgoingRelationships) {
		JSONArray graphArray = new JSONObject(queryOutgoingRelationships).getJSONArray("data");
		System.out.println( new JSONObject(queryOutgoingRelationships));
		
		for (int i = 0; i < graphArray.length(); i++) {
			JSONArray obj = graphArray.getJSONArray(i);
			String relType = obj.getString(0);
			
			switch (relType) {
			
			case "HASTOPIC":
				JSONObject parent = new JSONObject();
				parent.put("type", "HASTOPIC");
				parent.put("id", obj.get(1).toString());
				parent.put("label", obj.getString(2));
				relArray.put(parent);
				
				break;
			
			case "ROOTTOPIC":
				JSONObject root = new JSONObject();
				root.put("type", "ROOTTOPIC");
				root.put("id", obj.get(1).toString());
				root.put("label", obj.getString(2));
				relArray.put(root);
				
				break;	
				
			default:
				
				break;
			}
			
		}
		
	}

	private void incomingRelationshipsToJson(String queryIncomingRelationships) {
		// TODO Auto-generated method stub
		
	}
	
}
