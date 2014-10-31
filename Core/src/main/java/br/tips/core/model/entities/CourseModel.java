package br.tips.core.model.entities;

import java.util.ArrayList;
import java.util.List;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeClient;
import br.tips.core.graph.model.Relationship;
import br.tips.core.model.entities.utils.JsonCourseModelBuilder;
import br.tips.core.properties.CourseModelProperties;
import br.tips.core.properties.DataProperties;
import br.tips.core.properties.Types;

public class CourseModel extends EntityInGraph {

	protected CourseModel(Node n) {
		super(n);

	}

	protected Node getUnderlyingNode() {
		return instanceNode;
	}

	public List<Domain> getDomain() {
		List<Domain> domains = new ArrayList<>();

		for (Relationship rel : instanceNode
				.getRelationships(CourseModelProperties.BELONGSTO)) {
			if (rel.getEnd() != null) {
				domains.add(new Domain(rel.getEnd()));
			}
		}

		return domains;
	}

	public void addDomain(Domain domain) {
		if (domain != null) {
			for (Relationship r : instanceNode
					.getRelationships(CourseModelProperties.BELONGSTO)) {
				if (r.getEnd().equals(domain.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(domain.instanceNode,
					CourseModelProperties.BELONGSTO);

		}
	}

	public void removeDomain(Domain domain) {
		for (Relationship r : instanceNode
				.getRelationships(CourseModelProperties.BELONGSTO)) {

			if (r.getEnd().equals(domain.instanceNode)) {
				r.delete();
			}
		}
	}

	public ArrayList<Topic> getTopics() {
		ArrayList<Topic> topics = new ArrayList<>();
		for (Node n : instanceNode
				.getEndNodesOfRelationships(CourseModelProperties.HASTOPIC)) {
			topics.add(new Topic(n));
		}

		return topics;
	}

	public void addTopic(Topic topic) {
		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(CourseModelProperties.HASTOPIC)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					CourseModelProperties.HASTOPIC);

		}
	}

	public void removeTopic(Topic topic) {
		for (Relationship r : instanceNode
				.getRelationships(CourseModelProperties.HASTOPIC)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}

		}
		Relationship rootTopicRelationship = instanceNode
				.getSingleRelationship(CourseModelProperties.ROOTTOPIC);
		if (rootTopicRelationship != null
				&& topic.instanceNode.equals(rootTopicRelationship.getEnd())) {
			instanceNode.getSingleRelationship(CourseModelProperties.ROOTTOPIC)
					.delete();
		}

	}

	public Topic getRootTopic() {

		Relationship r = instanceNode
				.getSingleRelationship(CourseModelProperties.ROOTTOPIC);

		if (r == null || r.getEnd() == null) {
			return null;
		}
		return new Topic(r.getEnd());

	}

	public void setRootTopic(Topic topic) {
		//System.out.println(this.getTopics());
		if (!this.getTopics().contains(topic)) {
			System.out.println("Entrou aqui");
			return;
		}
		Relationship r = instanceNode
				.getSingleRelationship(CourseModelProperties.ROOTTOPIC);
		if (r != null) {
			System.out.println("oi!");
			r.delete();
		}
		instanceNode.addRelationship(topic.instanceNode,
				CourseModelProperties.ROOTTOPIC);
	}

	public String toString() {

		return "Course Model: "
				+ instanceNode.getProperty(DataProperties.LABEL);
	}

	public String getCompleteDescription() {
		StringBuilder desc = new StringBuilder();
		desc.append("Course Model: " + this.getId());

		desc.append("\nDomain: " + this.getDomain());
		desc.append("\nRoot Topic: " + this.getRootTopic());
		desc.append("\nTopics: " + this.getTopics());

		return desc.toString();
	}

	public static List<CourseModel> getAllCourseModels() {
		List<CourseModel> models = new ArrayList<>();
		List<Node> cmNodes = NodeClient.getNodesByType(Types.COURSEMODEL);

		for (Node node : cmNodes) {
			models.add(new CourseModel(node));
		}

		return models;

	}

	public List<String> getTopicsAndLabels() {
		ArrayList<String> topics = new ArrayList<>();
		for (Node n : instanceNode
				.getEndNodesOfRelationships(CourseModelProperties.HASTOPIC)) {
			topics.add(n.getUri().toString());
		}

		return topics;
	}

	public String toJSON() {
		String response;
		response = new JsonCourseModelBuilder(instanceNode).generateJson();
		return response;
	}

}
