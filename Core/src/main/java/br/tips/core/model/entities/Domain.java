package br.tips.core.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeClient;
import br.tips.core.model.entities.utils.DomainData;
import br.tips.core.persistence.CypherQuery;
import br.tips.core.properties.DataProperties;
import br.tips.core.properties.Types;

public class Domain extends EntityInGraph {

	public Domain(Node n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return "Domain: " + instanceNode.getProperty(DataProperties.ID);
	}
	
	
	public static List<DomainData> getAllDomains(){
		List<DomainData> domains = new ArrayList<DomainData>();
		DomainData newDomain;
		
		String DomainsInJson = new CypherQuery().getAllDomains(); 
		JSONObject responseJson = new JSONObject(DomainsInJson);
		
		JSONArray responseData = responseJson.getJSONArray("data");
		
		for (int i = 0; i < responseData.length(); i++) {
			JSONArray aResponseLine = responseData.getJSONArray(i);
			newDomain = new DomainData(""+aResponseLine.get(0), ""+aResponseLine.getString(1));
			domains.add(newDomain);
		}
		
		return domains;
	}

}
