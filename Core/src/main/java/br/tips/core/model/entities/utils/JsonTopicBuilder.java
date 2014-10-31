package br.tips.core.model.entities.utils;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.tips.core.graph.model.Node;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;
import br.tips.core.persistence.CypherQuery;

public class JsonTopicBuilder {
	
	private Topic topic;
	private Node node;
	private JSONObject object; 
	private JSONArray relArray;
	//public JsonTopicBuilder(Topic t){
		//topic=t;
	//}
	
	public JsonTopicBuilder(Node n){
		node = n;

		String fullUri = node.getUri().toString();
		String id= fullUri.substring(fullUri.lastIndexOf("/")+1);
		topic = EntityBuilder.buildTopic(id);
		
		object = new JSONObject();
		relArray = new JSONArray();
	}
	
	public String generateJson(){
		
		String id = topic.getId();
		object.put("id", id);
		object.put("label", topic.getLabel());
		
		System.out.println("aaa -"+object);

		
		this.incomingRelationshipsToJson(new CypherQuery().queryIncomingRelationships(id));
		this.outgoingRelationshipsToJson(new CypherQuery().queryOutgoingRelationships(id));
		
		object.put("relationships", relArray);
		return object.toString();
	}

	private void outgoingRelationshipsToJson(String queryOutgoingRelationships) {
		JSONArray graphArray = new JSONObject(queryOutgoingRelationships).getJSONArray("data");
		
		
		for (int i = 0; i < graphArray.length(); i++) {
			JSONArray obj = graphArray.getJSONArray(i);
			String relType = obj.getString(0);
			switch (relType) {
			
			case "PARENTOF":
				JSONObject parent = new JSONObject();
				parent.put("type", "PARENTOF");
				parent.put("id", obj.get(1).toString());
				parent.put("label", obj.getString(2));
				relArray.put(parent);
				
				break;
				
			default:
				
				break;
			}
			
		}
		
	}

	private void incomingRelationshipsToJson(String incomingRelationships) {
		System.out.println("ok-"+incomingRelationships);
		JSONArray graphArray = new JSONObject(incomingRelationships).getJSONArray("data");
		
		
		for (int i = 0; i < graphArray.length(); i++) {
			JSONArray obj = graphArray.getJSONArray(i);
			String relType = obj.getString(0);
			switch (relType) {
			case "HASTOPIC":
				JSONObject model = new JSONObject();
				model.put("type", "HASTOPIC");
				model.put("id", obj.get(1).toString());
				model.put("label", obj.getString(2));
				relArray.put(model);
				break;
			
			case "PARENTOF":
				JSONObject parent = new JSONObject();
				parent.put("type", "PARENT");
				parent.put("id", obj.get(1).toString());
				parent.put("label", obj.getString(2));
				relArray.put(parent);
				
				
			default:
				
				break;
			}
			
		}
		
	}
	
	private JSONObject ParentsTransformation(JSONObject obj) {
		JSONObject newObject = new JSONObject();
		newObject.put("type", "PARENT");
		String id = JsonTopicBuilder.uriToId(obj.getString("start"));
		newObject.put("id", id);
		newObject.put("label", getTopicLabel(id));
		
		return newObject;
	}

	private JSONObject ParentOfTransformation(JSONObject obj) {
		JSONObject newObject = new JSONObject();
		newObject.put("type", "PARENTOF");
		String id = JsonTopicBuilder.uriToId(obj.getString("end"));
		newObject.put("id", id);
		newObject.put("label", getTopicLabel(id));
		
		return newObject;
	}

	private JSONObject HasTopicTransformation(JSONObject obj){
		JSONObject newObject = new JSONObject();
		newObject.put("type", "HASTOPIC");
		String id = JsonTopicBuilder.uriToId(obj.getString("start"));
		newObject.put("id", id);
		
		String label = EntityBuilder.buildCourseModel(id).getLabel();
		newObject.put("label", label);
		
		return newObject;
	}
	
	private String getTopicLabel(String id){
		return EntityBuilder.buildTopic(id).getLabel();
	}
	
	private static String uriToId(String uri){
		String fullUri = uri.toString();
		String id= fullUri.substring(fullUri.lastIndexOf("/")+1);
		
		return id;
	}
	
}
