package tips.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;
import br.tips.core.persistence.GraphPersistence;
import br.tips.core.properties.Types;

public class MathReader {
	
	
	public static List<String> getTopics() throws Exception{
		List<String> topics = new ArrayList<String>();
		File f = new File("math.xml");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line="";
		while ((line = reader.readLine())!=null) {
			if (line.contains("value=")) {
				
				topics.add(line.split("\"")[1]);
			}
				
			
		}
		
		return topics;
	}
	
	public static void putInGraph(CourseModel cm, List<String> topics){
		String encdl = "ENCdl_";
		Topic root = EntityBuilder.createNewTopic(encdl+"Math");
		//Topic root = EntityBuilder.buildTopic("4");
		cm.addTopic(root);
		cm.setRootTopic(root);
		
		for(String tpc: topics){
			String[] tokens = tpc.split(":");
			if (tokens.length==1) {
				String validName = toValidName(tokens[0]);
				if(EntityBuilder.buildTopic(toValidName(validName))==null){
					Topic secondLevelTopic = EntityBuilder.createNewTopic(validName);
					cm.addTopic(secondLevelTopic);
					root.addSubtopic(secondLevelTopic);
				}
			}else {
				for (int i = 1; i < tokens.length; i++) {
					String topicName = toValidName(tokens[i]);
					String parentName = toValidName(tokens[i-1]);
					Topic newTopic = EntityBuilder.buildTopic(topicName);
					Topic parentTopic = EntityBuilder.buildTopic(parentName);
					
					if (newTopic==null) {
						newTopic = EntityBuilder.createNewTopic(topicName);
						cm.addTopic(newTopic);
						parentTopic.addSubtopic(newTopic);
					}
				}
				
				
			}
			

		}
		
	}
	
	public static void putInGraph2(CourseModel cm, List<String> data){
		//Topic root = EntityBuilder.buildTopic("4");
		Topic root = EntityBuilder.createNewTopic("ENCdl_Math");
		addSons(root, getFirstOfLine(data), data, cm);;
		
		System.out.println(getSons("Data presentation", data));
	}
	
	public static void addSons(Topic parent, Set<String> sons, List<String> data, CourseModel cm){
		for(String topic:sons){
			Topic aSon = EntityBuilder.createNewTopic(topic);
			cm.addTopic(aSon);
			parent.addSubtopic(aSon);
			addSons(aSon, getSons(topic, data), data, cm);
		}
	}
	
	public static Set<String> getFirstOfLine(List<String> data){
		Set<String> firsts = new HashSet<>();
		for(String line: data){
			firsts.add(line.split(":")[0]);
		}
		
		return firsts;
	}
	
	public static Set<String> getSons(String key, List<String> data){
		Set<String> sons = new HashSet<>();
		for(String line: data){
			if (line.contains(key+":")) {
				String afterKey = line.split(key+":")[1];
				if (afterKey.contains(":")) {
					sons.add(afterKey.split(":")[0]);
				}else{
					sons.add(afterKey);
				}
			}
		}
		
		return sons;
	}
	
	public static String toValidName(String topicName){
		
		return "ENCdl_"+topicName.replace(" ", "_");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MathReader.getTopics().size());

		CourseModel cm = EntityBuilder.createNewCourseModel(toValidName("Math Course"));
		//CourseModel cm = EntityBuilder.buildCourseModel("0");
		//Topic root = EntityBuilder.createNewTopic(toValidName("Math_root"));
		//cm.addTopic(root);
		//cm.setRootTopic(root);
		MathReader.putInGraph2(cm, MathReader.getTopics());
		
		//for(Topic t :Topic.getAllTopics()){
			//t.setLabel(t.getId());
			//System.out.println(t);
		//}
		
		System.out.println(cm.getTopics().size());
		
	}
	
}
