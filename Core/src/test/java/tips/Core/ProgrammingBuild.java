package tips.Core;

import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;

public class ProgrammingBuild {
	private static final String newboston = "C-Programming-NewBoston";
	
	private static void addTopic(String cmid, String topicId){
		CourseModel cm = EntityBuilder.buildCourseModel(cmid);
		Topic t = EntityBuilder.createNewTopic(topicId);
		cm.addTopic(t);
		
	}
	
	public static void main(String[] args) {
		//EntityBuilder.createNewCourseModel("C-Programming-NewBoston");
		CourseModel cm =EntityBuilder.buildCourseModel(newboston);
		System.out.println(cm.getTopics());
		
		Topic t1 = EntityBuilder.buildTopic("Intro_to_Arrays");
		
		Topic t2 = EntityBuilder.buildTopic("Simple_Array_Program");
		
		t1.addSubtopic(t2);
		System.out.println(t1.getSubtopics());

		
		
	}
	

}
