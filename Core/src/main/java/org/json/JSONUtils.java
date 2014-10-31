package org.json;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.sun.jndi.toolkit.url.Uri;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeBuilder;
import br.tips.core.graph.model.Relationship;

public class JSONUtils {
	private static String START = "start";
	private static String END = "\"end\" :";
	public static String SELF = "\"self\" :";
	public static String T1 = ": \"";
	
	
	public static List<Relationship> jsonToRelationship(String json){
		System.out.println(json);
		List<Relationship> triples = new ArrayList<Relationship>();
		String token ="";
		String relationship;
		Relationship newRelationship=null;
		StringTokenizer st = new StringTokenizer(json,",");
		
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			 if(token.contains(SELF)){
				newRelationship = new Relationship();
				relationship =  token.substring(token.indexOf(T1)+3,token.lastIndexOf("\""));
				newRelationship.setRelationship(relationship);
				triples.add(newRelationship);
			 }
		}
		
		
		return triples;
	}
	
	public static List<Relationship> parseJsonToRelationship (String json, int beginIndex, List<Relationship> relationships){
		if (!json.contains(SELF)) {
			return relationships;
		}
		int newIndex = json.indexOf(SELF, beginIndex)+10;
		int endIndex = json.indexOf("\"", newIndex);
		//String relationshipUri = json.substring(newIndex,endIndex);
		if (newIndex< beginIndex) {
			return relationships;
		}
		
		Relationship newRelationship = new Relationship();
		newRelationship.setRelationship(json.substring(newIndex,endIndex));
		System.out.println("-----"+ json);
		relationships.add(newRelationship);
		return parseJsonToRelationship(json, newIndex , relationships);
	}
	
	public static List<Relationship> jsonToRelationship2(String json){
		return parseJsonToRelationship(json, 0, new ArrayList<Relationship>());
	}
	
	
	public static List<Node> parseJsonToEndNode(String json, int beginIndex, List<Node> nodes, String breaker){
		int newIndex = json.indexOf(breaker, beginIndex)+9;
		int endIndex = json.indexOf("\"", newIndex);
		//String relationshipUri = json.substring(newIndex,endIndex);
		if (newIndex< beginIndex) {
			return nodes;
		}
		
		Node newNode = NodeBuilder.buildByURI(json.substring(newIndex,endIndex));
		nodes.add(newNode);
		return parseJsonToEndNode(json, newIndex , nodes, breaker);
	}
	
	public static List<Node> getEndNodeMultipleRelationships(String json){
		return parseJsonToEndNode(json, 0, new ArrayList<Node>(), END);
		
	}
	
	
	public static List<Node> jsonToNode(String json){
		List<Node> triples = new ArrayList<Node>();
		String token ="";
		String node;
		StringTokenizer st = new StringTokenizer(json,",");
		
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			 if(token.contains(SELF)){
				
				node =  token.substring(token.indexOf(T1)+3,token.lastIndexOf("\""));
				
				triples.add(NodeBuilder.retrieveNode(node));
			 }
		}
		
		
		return triples;
	}
	
	public static String getStart(String jsonResponse){
		JSONObject json = new JSONObject(jsonResponse);
		if (json.keySet().contains(START)) {
		//	System.out.println("-> "+json.getString(START));
			return json.getString(START);
		}
		return "";
	}
	
	public static String getEnd(String jsonResponse){
		String end = "end";
		//System.out.println(jsonResponse);
		JSONObject json = new JSONObject(jsonResponse);
		if (json.keySet().contains(end)) {
			return json.getString(end);
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		Node n = NodeBuilder.retrieveNode(NodeBuilder.idToUri("2"));
		new Relationship("http://localhost:7474/db/data/relationship/5").delete();
		for(Relationship r:n.getAllRelationships()){
			r.delete();
		}
	}

}
