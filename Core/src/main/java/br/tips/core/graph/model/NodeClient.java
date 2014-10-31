package br.tips.core.graph.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.core.MediaType;

import org.json.JSONUtils;

import br.tips.core.graph.configuration.ClientConfiguration;
import br.tips.core.graph.configuration.Names;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NodeClient {
	
	private static final String NODE = "node/";

	protected static URI createNode(){
		final String nodeEntryPointUri = ClientConfiguration.getGraphPath() + NODE;
		// http://localhost:7474/db/data/node

		WebResource resource = Client.create()
		        .resource( nodeEntryPointUri );
		// POST {} to the node entry point URI
		ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
		        .type( MediaType.APPLICATION_JSON )
		        .entity( "{}" )
		        .post( ClientResponse.class );

		final URI location = response.getLocation();
		System.out.println( String.format(
		        "POST to [%s], status code [%d], location header [%s]",
		        nodeEntryPointUri, response.getStatus(), location.toString() ) );
		response.close();

		return location;
	}
	
	protected static boolean getNode(String uri){
		final String nodeEntryPointUri = uri;
		boolean result = false;
		
		WebResource resource = Client.create()
		        .resource( nodeEntryPointUri );
		System.out.println(nodeEntryPointUri);
		ClientResponse response = resource.accept( MediaType.APPLICATION_JSON ).get( ClientResponse.class );

		
		System.out.println( String.format(
		        "GET to [%s], status code [%d]",
		        nodeEntryPointUri, response.getStatus()) );
		
		if (response.getStatus()==200) {
			result =true;
		}
		
		response.close();
		
		
		
		return result;
	}
	
	public static void main(String[] args) {
		//System.out.println(NodeClient.getNode("324"));
		
	}

	public static List<Node> getNodesByType(String type) {
		String uri = "http://localhost:7474/db/data/label/"+type+"/nodes";
		List<Node> triples = new ArrayList<Node>();
		
		WebResource resource = Client.create()
		        .resource( URI.create(uri) );
		
		ClientResponse response = resource.accept( MediaType.APPLICATION_JSON ).get( ClientResponse.class );

		
		System.out.println( String.format(
		        "GET to [%s], status code [%d]",
		        uri, response.getStatus()) );
		
		String token ="";
		String node;
		StringTokenizer st = new StringTokenizer(response.getEntity(String.class),",");
		
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			 if(token.contains(JSONUtils.SELF)){
				
				node =  token.substring(token.indexOf(JSONUtils.T1)+3,token.lastIndexOf("\""));
				
				triples.add(new Node(URI.create(node)));
			 }
		}
				
		response.close();
		
		return triples;

	}
	
	

}
