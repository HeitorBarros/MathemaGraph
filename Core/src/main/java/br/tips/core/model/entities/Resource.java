package br.tips.core.model.entities;

import java.util.ArrayList;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.Relationship;
import br.tips.core.properties.DataProperties;
import br.tips.core.properties.ResourceProperties;

public class Resource extends EntityInGraph {

	protected Resource(Node n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public String getLink() {
		return "" + instanceNode.getProperty(DataProperties.LINK);

	}

	public void setLink(String link) {

		instanceNode.addProperty(DataProperties.LINK, link);
	}

	public String getClassification() {

		return "" + instanceNode.getProperty(DataProperties.CLASSIFICATION);

	}

	public void setClassification(String classification) {

		instanceNode.addProperty(DataProperties.CLASSIFICATION, classification);
	}

	public ArrayList<Topic> getTopics() {
		ArrayList<Topic> topics = new ArrayList<>();

		for (Relationship r : instanceNode
				.getRelationships(ResourceProperties.COVERSTOPIC)) {
			topics.add(new Topic(r.getEnd()));
		}

		return topics;
	}

	public void addTopic(Topic topic) {
		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(ResourceProperties.COVERSTOPIC)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					ResourceProperties.COVERSTOPIC);

		}
	}

	public void removeTopic(Topic topic) {

		for (Relationship r : instanceNode
				.getRelationships(ResourceProperties.COVERSTOPIC)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}

		}
	}

}
