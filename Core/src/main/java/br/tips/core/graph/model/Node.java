package br.tips.core.graph.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.json.JSONUtils;

import br.tips.core.graph.configuration.Names;





import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Node {

	private URI nodeUri;

	protected Node(URI uri) {
		nodeUri = uri;
	}

	public URI getUri() {
		return this.nodeUri;
	}

	public void delete() {

		for (Relationship r : this.getAllRelationships()) {
			r.delete();
		}

		WebResource resource = Client.create().resource(nodeUri);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);

		System.out.println(String.format("DELETE to [%s], status code [%d]",
				nodeUri, response.getStatus()));

		response.close();
	}

	public void addProperty(String propertyName, String propertyValue) {
		String propertyUri = nodeUri.toString() + "/" + Names.PROPERTIES + "/"
				+ propertyName;
		// http://localhost:7474/db/data/node/{node_id}/properties/{property_name}

		WebResource resource = Client.create().resource(propertyUri);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.entity("\"" + propertyValue + "\"").put(ClientResponse.class);

		System.out.println(String.format("PUT to [%s], status code [%d]",
				propertyUri, response.getStatus()));
		response.close();
	}

	public String getProperty(String propertyName) {
		String propertyUri = nodeUri.toString() + "/" + Names.PROPERTIES;
		// http://localhost:7474/db/data/node/{node_id}/properties/{property_name}
		System.out.println(propertyUri);
		WebResource resource = Client.create().resource(propertyUri);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			return null;
		}

		String res = response.getEntity(String.class);

		JSONObject json = new JSONObject(res);
		if (json.keySet().contains(propertyName)) {
			System.out.println(String.format("GET to [%s], response [%s]",
					propertyUri, res));
			return json.getString(propertyName);
		}
		return "";
	}

	public void removeProperty(String propertyName) {
		String finalUri = nodeUri + "/" + Names.PROPERTIES + "/" + propertyName;
		WebResource resource = Client.create().resource(finalUri);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);

		System.out.println(String.format("DELETE to [%s], status code [%d]",
				finalUri, response.getStatus()));

		response.close();
	}

	public URI addRelationship(Node otherNode, Enum belongsto) {
		URI fromUri;
		try {
			fromUri = new URI(nodeUri.toString() + "/relationships");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		String relationshipJson = "{\"to\" : \"" + otherNode.getUri()
				+ "\", \"type\" : \"" + belongsto + "\"}";
		WebResource resource = Client.create().resource(fromUri);
		// POST JSON to the relationships URI
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).entity(relationshipJson)
				.post(ClientResponse.class);

		final URI location = response.getLocation();
		System.out.println(String.format(
				"POST to [%s], status code [%d], location header [%s]",
				fromUri, response.getStatus(), location.toString()));

		response.close();
		return location;
	}

	public List<Relationship> getAllRelationships() {
		String allRelationships = this.nodeUri + "/relationships/all";

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.jsonToRelationship(response.getEntity(String.class));
	}
	
	public String getAllRelationshipsReturnsJson() {
		String allRelationships = this.nodeUri + "/relationships/all";

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return response.getEntity(String.class);
	}

	public List<Relationship> getIncomingRelationships() {
		String allRelationships = this.nodeUri + "/relationships/in";

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.jsonToRelationship(response.getEntity(String.class));
	}

	protected void initializeLabel(String label) {
		String labels = nodeUri + "/labels";
		WebResource resource = Client.create().resource(labels);
		// POST {} to the node entry point URI
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).entity("\"" + label + "\"")
				.post(ClientResponse.class);

		System.out.println(String.format(
				"POST to [%s], status code [%d], location header [%s]", labels,
				response.getStatus(), ""));
		response.close();

	}

	public List<Relationship> getRelationships(Enum relationship) {
		String allRelationships = this.nodeUri + "/relationships/all/"
				+ relationship;

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.jsonToRelationship2(response.getEntity(String.class));
	}
	
	public List<Node> getEndNodesOfRelationships(Enum relationship) {
		String allRelationships = this.nodeUri + "/relationships/all/"
				+ relationship;

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.getEndNodeMultipleRelationships(response.getEntity(String.class));
	}

	public Relationship getSingleRelationship(Enum relationship) {
		String allRelationships = this.nodeUri + "/relationships/all/"
				+ relationship;

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}
		List<Relationship> relationships = JSONUtils
				.jsonToRelationship2(response.getEntity(String.class));
		return (relationships == null||(relationships.size()==0)) ? null : relationships.get(0);
	}

	public List<Relationship> getOutgoingRelationships(Enum relationship) {
		String allRelationships = this.nodeUri + "/relationships/out/"
				+ relationship;

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.jsonToRelationship(response.getEntity(String.class));
	}

	public List<Relationship> getIncomingRelationships(Enum relationship) {
		String allRelationships = this.nodeUri + "/relationships/in/"+relationship;

		WebResource resource = Client.create().resource(allRelationships);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		System.out.println(String.format("GET to [%s], status code [%d]",
				allRelationships, response.getStatus()));

		if (response.getStatus() != 200) {
			return null;
		}

		return JSONUtils.jsonToRelationship(response.getEntity(String.class));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeUri == null) ? 0 : nodeUri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeUri == null) {
			if (other.nodeUri != null)
				return false;
		} else if (!nodeUri.toString().equals(other.nodeUri.toString()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nodeUri.toString();
	}

}
