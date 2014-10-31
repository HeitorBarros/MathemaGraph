package br.tips.core.model.entities;


import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.Relationship;
import br.tips.core.properties.DataProperties;

public abstract class EntityInGraph {

	protected Node instanceNode;
	
	protected EntityInGraph(Node n) {
		super();
		this.instanceNode = n;
	}

	protected Node getUnderlyingNode(){
		return instanceNode;
	}
	
	public String getId() {
		String fullUri = instanceNode.getUri().toString();
		return fullUri.substring(fullUri.lastIndexOf("/")+1);
		
	}
	
	public void setId(String id){
		
		instanceNode.addProperty(DataProperties.ID, id);
		
	}
	
	public String getDescription(){
			return (String) instanceNode.getProperty(DataProperties.DESCRIPTION);
	}
	
	public void setDescription(String desc){
			instanceNode.addProperty(DataProperties.DESCRIPTION, desc);
	}
	
	public String getLabel(){
			return (String) instanceNode.getProperty(DataProperties.LABEL);
	}
	
	public void setLabel(String label){
			instanceNode.addProperty(DataProperties.LABEL, label);
	}
	
	public void delete() {
				instanceNode.delete();
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "id="+instanceNode.getProperty(DataProperties.ID);
	}
	
	public boolean equals(Object ent) {
		// TODO Auto-generated method stub
		if (ent instanceof EntityInGraph) {
			return instanceNode.equals(((EntityInGraph) ent).instanceNode);
		}else{
			return false;
		}
		
	}

	
}
