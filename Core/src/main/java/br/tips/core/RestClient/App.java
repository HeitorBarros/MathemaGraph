package br.tips.core.RestClient;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeBuilder;
import br.tips.core.graph.model.NodeClient;
import br.tips.core.properties.TopicProperties;

/**
 * Hello world!
 *
 */
public class App 
{
	private static void apagarTodosOsNós(){
		for(Node n:NodeClient.getNodesByType("Topic")){
			n.delete();
		}
		
		for(Node n:NodeClient.getNodesByType("Domain")){
			n.delete();
		}
		
		for(Node n:NodeClient.getNodesByType("Course Model")){
			n.delete();
		}
		
		for(Node n:NodeClient.getNodesByType("topic")){
			n.delete();
		}
	}
	
    public static void main( String[] args )
    {
//    	//Node n1 = NodeBuilder.createNode();
//    	Node n1 = NodeBuilder.retrieveNode(NodeBuilder.idToUri("10"));
//    	Node n2 = NodeBuilder.retrieveNode(NodeBuilder.idToUri("11"));
//    	Node n4 = NodeBuilder.retrieveNode(NodeBuilder.idToUri("5"));
//
//    	//n1.addRelationship(n2, TopicProperties.NEXTTOPIC);
//    	
//    	//n1.addProperty("type", "Test");
//    	
//    	Node n3 = NodeBuilder.createNode("Topic");
//    	n3.delete();
//    	
//    	System.out.println(n1.getProperty("type"));
//    	
//    	
//    	n1.addProperty("type", "Test2");
//    	System.out.println(n1.getProperty("type"));
//    	
//    	n2.addProperty("type", "test3");
//    	System.out.println(n2.getProperty("type"));
//    	n2.removeProperty("type");
//    	System.out.println(n2.getProperty("type"));
//    	
//    	n1.getAllRelationships();
    	//apagarTodosOsNós();
    
    }
}
