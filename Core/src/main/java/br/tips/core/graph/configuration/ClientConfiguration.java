package br.tips.core.graph.configuration;

public class ClientConfiguration {

	private static String graphPath = "http://localhost:7474/db/data/";
	
	public static String getGraphPath(){
		return graphPath;
	}
	
	
}
