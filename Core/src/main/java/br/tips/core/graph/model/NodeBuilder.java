package br.tips.core.graph.model;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import br.tips.core.graph.configuration.ClientConfiguration;
import br.tips.core.graph.configuration.Names;

public class NodeBuilder {

	public static Node createNode(String label){
		Node newNode = new Node(NodeClient.createNode());
		newNode.initializeLabel(label);
		return newNode;
		
	}
	
	public static Node retrieveNode(String uri){
		
		if (NodeClient.getNode(uri)) {
			return new Node(URI.create(uri));
		}
		return null;
	}
	
	public static String idToUri(String id){
		return ClientConfiguration.getGraphPath()+Names.NODE+"/"+id;
	}

	public static Node buildByURI(String substring) {
		// TODO Auto-generated method stub
		return new Node(URI.create(substring));
	}
}
