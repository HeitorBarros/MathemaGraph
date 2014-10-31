package br.tips.core.cypher;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CypherQuery {

	public String queryOutgoingRelationships(String id){
final String nodeEntryPointUri = "http://localhost:7474/db/data/cypher";
		
		WebResource resource = Client.create()
		        .resource( nodeEntryPointUri );
		System.out.println(nodeEntryPointUri);
		ClientResponse response = resource.accept( MediaType.APPLICATION_JSON ).post( ClientResponse.class , 
				"{  \"query\" : \"start entity=node("+id+") match (entity)-[r]->(n) return type(r), id(n), n.label\"}");

		
		System.out.println( String.format(
		        "GET to [%s], status code [%d]",
		        nodeEntryPointUri, response.getStatus()) );
		
		
		
		String jsonResponse = response.getEntity(String.class);
		
		response.close();
		
		return jsonResponse;
	}
	
	public String queryIncomingRelationships(String id){
		final String nodeEntryPointUri = "http://localhost:7474/db/data/cypher";
				
				WebResource resource = Client.create()
				        .resource( nodeEntryPointUri );
				//System.out.println(nodeEntryPointUri);
				ClientResponse response = resource.accept( MediaType.APPLICATION_JSON ).post( ClientResponse.class , 
						"{  \"query\" : \"start entity=node("+id+") match (n)-[r]->(entity) return type(r), id(n), n.label\"}");

				
				//System.out.println( String.format("GET to [%s], status code [%d]", nodeEntryPointUri, response.getStatus()) );
				
				
				
				String jsonResponse = response.getEntity(String.class);
				
				response.close();
				
				return jsonResponse;
			}
	
	
	public static void main(String[] args) {
		
		
		System.out.println(new CypherQuery().queryIncomingRelationships("13"));
		System.out.println(new CypherQuery().queryOutgoingRelationships("13"));
		
	
	}
}
