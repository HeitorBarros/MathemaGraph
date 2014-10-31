package br.tips.core.model.entities;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeBuilder;
import br.tips.core.persistence.GraphPersistence;
import br.tips.core.properties.DataProperties;
import br.tips.core.properties.Types;

public class EntityBuilder {

	public static CourseModel buildCourseModel(String id) {
		Node node = NodeBuilder.retrieveNode(NodeBuilder.idToUri(id));
		if (node == null) {
			return null;
		}
		return new CourseModel(node);
	}

	public static CourseModel createNewCourseModel(String label) {
		CourseModel newCourse = new CourseModel(NodeBuilder.createNode(Types.COURSEMODEL));
		newCourse.setLabel(label);
		return newCourse;
	}

	public static Domain buildDomain(String id) {
		Node node = NodeBuilder.retrieveNode(NodeBuilder.idToUri(id));
		if (node == null) {
			return null;
		}
		return new Domain(node);
	}

	public static Domain createNewDomain(String label) {
		Domain newDomain = new Domain(NodeBuilder.createNode(Types.DOMAIN));
		newDomain.setLabel(label);
		return newDomain;
	}

	public static Topic buildTopic(String id) {
		Node node = NodeBuilder.retrieveNode(NodeBuilder.idToUri(id));
		if (node == null) {
			return null;
		}
		return new Topic(node);
	}

	public static Topic createNewTopic(String label) {

			Topic t = new Topic(NodeBuilder.createNode(Types.TOPIC));
			t.setLabel(label);
			
			return t;
	}

	public static Resource buildResource(String id) {
		Node node = NodeBuilder.retrieveNode(id);
		if (node == null) {
			return null;
		}
		return new Resource(node);
	}

	public static Resource createNewResource(String label) {
		Resource newResource = new Resource(NodeBuilder.createNode(Types.RESOURCE));
		newResource.setLabel(label);
		return newResource;
	}

}
