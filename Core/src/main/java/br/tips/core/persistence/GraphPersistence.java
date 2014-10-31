package br.tips.core.persistence;

import java.util.ArrayList;
import java.util.List;

public class GraphPersistence {
//	
//	//private static GraphPersistence instance;
//	
//	private static final String DB_PATH = "c:/core/graph-db";
//	
//	static GraphDatabaseService graphDb;
//	
//	private GraphPersistence(){
//		if (graphDb==null) {
//			graphDb= new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
//			registerShutdownHook(graphDb);
//		}
//		
//	}
//	
//	private static void registerShutdownHook( final GraphDatabaseService graphDb )
//    {
//        // Registers a shutdown hook for the Neo4j instance so that it
//        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
//        // running application).
//        Runtime.getRuntime().addShutdownHook( new Thread()
//        {
//            @Override
//            public void run()
//            {
//                graphDb.shutdown();
//            }
//        } );
//    }
//	
//	public static GraphPersistence getInstance(){
//		//if (instance==null || instance.graphDb==null) {
//		//	instance = new GraphPersistence();
//		//}
//		//return instance;
//		return new GraphPersistence();
//	}
//	
//	public void insertNode(String type, String id){
//		 try ( Transaction tx = graphDb.beginTx() ){
//			 Node newNode = graphDb.createNode(DynamicLabel.label(type));
//			 newNode.setProperty(DataProperties.ID, id);			 
//			 tx.success();
//		 }
//	}
//	
//	public void deleteNode(String type, String id){
//		 try ( Transaction tx = graphDb.beginTx() ){
//			 ResourceIterable<Node> nodesIt = graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, id);
//			 for (Node node : nodesIt) {
//				for(Relationship r :node.getRelationships()){
//					r.delete();
//				}
//				node.delete();
//			}
//			 tx.success();
//		 }
//	}
//	
//	public void changeDataProperty(String type, String id, String property, String newData){
//		 try ( Transaction tx = graphDb.beginTx() ){
//			 for(Node node :graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, id)){
//				 node.setProperty(property, newData);
//			 }
//			 
//			 tx.success();
//		 }
//	}
//	
//	public void changeObjectProperty(String type, String idFormer, RelationshipType property, Node target ){
//		try ( Transaction tx = graphDb.beginTx() ){
//			 for(Node node :graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, idFormer)){
//				 node.createRelationshipTo(target, property);
//			 }
//			
//			tx.success();
//		}
//	}
//	
//	public Node getNode(String type, String id){
//		// If index is working the next line should  retrieve the correct node.
//		//graphDb.index().forNodes(type).get(DataProperties.ID, id).getSingle();
//		
//		ArrayList<Node> nodes = new ArrayList<>(); 
//		try ( Transaction tx = graphDb.beginTx() ){
//			 for(Node n :graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, id)){
//				 return n;
//			 }
//			 tx.success();
//		}
//		return null;
//	}
//	
//	public ArrayList<Node> getObjectProperty(String type, String id, RelationshipType id2){
//		ArrayList<Node> nodes = new ArrayList<>();
//		try ( Transaction tx = graphDb.beginTx() ){
//			for(Node n :graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, id)){
//				for(Relationship rel :n.getRelationships(id2)){
//					nodes.add(rel.getOtherNode(n));
//				}
//				
//			}
//			tx.success();
//		}
//		
//		return nodes;
//		
//	}
//	
//	public ArrayList<Object> getDataProperty(String type, String id, String property){
//		ArrayList<Object> result = new ArrayList<>();
//		try ( Transaction tx = graphDb.beginTx() ){
//			for(Node n :graphDb.findNodesByLabelAndProperty(DynamicLabel.label(type), DataProperties.ID, id)){
//				Object valueOrNull = n.getProperty( DataProperties.ID, null );
//				result.add(valueOrNull);
//			}
//		}
//		
//		return result;
//	}
//	
//	public ArrayList<Node> getAllNodes(){
//		ArrayList<Node> nodes = new ArrayList<>(); 
//		try ( Transaction tx = graphDb.beginTx() ){
//			for(Node n: graphDb.getAllNodes()){
//				nodes.add(n);
//			}
//			 tx.success();
//		}	 		
//		return nodes;
//	}
//
//	public String getId(Node n) {
//		try ( Transaction tx = graphDb.beginTx() ){
//			return n.getProperty(DataProperties.ID).toString();
//		}
//	}
//	
//	public List<Node> getAllNodesByType(String type){
//		List<Node> nodes = new ArrayList<Node>();
//		try ( Transaction tx = graphDb.beginTx() ){
//			for(Node n:GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(type))){
//				nodes.add(n);
//			}
//			
//			
//			return nodes;
//		}
//	}
//	

	

}
