package br.tips.core.graph.model;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.json.JSONUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Relationship {
	
	
	private String id;
	
	public Relationship(String id){
		this.id = id;
	}
	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Node getStart() {
		WebResource resource = Client.create()
	            .resource( this.getRelationship() );
	    
	    ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
	            .type( MediaType.APPLICATION_JSON )
	            .get( ClientResponse.class );
	    
	    if (response.getStatus()!=200) {
			System.out.println("");
	    	return null;
		}
		String jsonResponse = response.getEntity(String.class);
		return new Node(URI.create(JSONUtils.getStart(jsonResponse)));
	}
	public void setStart(String start) {
		
	}
	public String getRelationship() {
		return id;
	}
	public void setRelationship(String relationship) {
		this.id = relationship;
	}
	public Node getEnd() {
		WebResource resource = Client.create()
	            .resource( this.getRelationship() );
	    
	    ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
	            .type( MediaType.APPLICATION_JSON )
	            .get( ClientResponse.class );
	    
	    if (response.getStatus()!=200) {
			System.out.println("");
	    	return null;
		}
		String jsonResponse = response.getEntity(String.class);
		return new Node(URI.create(JSONUtils.getEnd(jsonResponse)));
	}
	public void setEnd(String end) {
		
	}
	@Override
	public String toString() {
		return "Relationship <"+ id + ">";
	}
	
	public void delete(){

		System.out.println("oh man!");
		WebResource resource = Client.create()
	            .resource( this.getRelationship() );
	    
	    ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
	            .type( MediaType.APPLICATION_JSON )
	            .delete( ClientResponse.class );
	    
	    System.out.println( String.format(
	            "DELETE to [%s], status code [%d]",
	            this.getRelationship(), response.getStatus()));
	    
	    
	    if (response.getStatus()!=200) {
			return ;
		}
	    
	}
	public String getType() {
		
		return null;
	}

}
