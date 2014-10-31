package br.tips.core.model.entities;

import java.util.ArrayList;
import java.util.List;

import br.tips.core.graph.model.Node;
import br.tips.core.graph.model.NodeClient;
import br.tips.core.graph.model.Relationship;
import br.tips.core.model.entities.utils.JsonTopicBuilder;
import br.tips.core.persistence.GraphPersistence;
import br.tips.core.properties.CourseModelProperties;
import br.tips.core.properties.DataProperties;
import br.tips.core.properties.MappingProperties;
import br.tips.core.properties.TopicProperties;
import br.tips.core.properties.Types;

public class Topic extends EntityInGraph {

	protected Topic(Node n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	// /////////////////
	// NEXT TOPICS
	// ////////////////
	public List<Topic> getNext() {
		List<Topic> topics = new ArrayList<>();
		for (Relationship rel : instanceNode
				.getOutgoingRelationships(TopicProperties.NEXTTOPIC)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getEnd()));
			}
		}
		return topics;

	}

	public Topic getFirstNext() {
		if (this.getNext() == null || this.getNext().size() == 0) {
			return null;
		}
		return this.getNext().get(0);
	}

	public void addNext(Topic topic) {
		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(TopicProperties.NEXTTOPIC)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					TopicProperties.NEXTTOPIC);

		}
	}

	public void removeNext(Topic topic) {

		for (Relationship r : instanceNode
				.getRelationships(TopicProperties.NEXTTOPIC)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}
		}

	}

	public List<Topic> getPrevious() {
		List<Topic> topics = new ArrayList<>();
		for (Relationship rel : instanceNode
				.getIncomingRelationships(TopicProperties.NEXTTOPIC)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getStart()));
			}
		}
		return topics;
	}

	// /////////////////
	// SUBTOPICS
	// ////////////////
	public List<Topic> getSubtopics() {
		List<Topic> topics = new ArrayList<>();
		for (Relationship rel : instanceNode
				.getOutgoingRelationships(TopicProperties.PARENTOF)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getEnd()));
			}
		}
		return topics;

	}

	public void addSubtopic(Topic topic) {

		if (topic != null) {
			for (Relationship r : instanceNode
					.getOutgoingRelationships(TopicProperties.PARENTOF)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					TopicProperties.PARENTOF);

		}
	}

	public void removeSubtopic(Topic topic) {
		for (Relationship r : instanceNode
				.getOutgoingRelationships(TopicProperties.PARENTOF)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}
		}

	}

	public List<Topic> getSupertopics() {
		List<Topic> topics = new ArrayList<>();

		for (Relationship rel : instanceNode
				.getIncomingRelationships(TopicProperties.PARENTOF)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getStart()));
			}
		}
		return topics;

	}

	// /////////////////
	// DATA PROPERTIES
	// ////////////////

	public String getClassification() {

		return "" + instanceNode.getProperty(DataProperties.CLASSIFICATION);

	}

	public void setClassification(String classification) {
		instanceNode.addProperty(DataProperties.CLASSIFICATION, classification);
	}

	// /////////////////
	// MAPPING PROPERTIES: Equivalent
	// ////////////////

	public List<Topic> getEquivalentMapping() {
		List<Topic> topics = new ArrayList<>();
		for (Relationship rel : instanceNode
				.getRelationships(MappingProperties.EQUIVALENTTO)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getEnd()));
			}
		}
		return topics;

	}

	public void addEquivalentMapping(Topic topic) {

		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(MappingProperties.EQUIVALENTTO)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					MappingProperties.EQUIVALENTTO);

		}

	}

	public void removeEquivalentMapping(Topic topic) {

		for (Relationship r : instanceNode
				.getRelationships(MappingProperties.EQUIVALENTTO)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}
		}

	}

	// /////////////////
	// MAPPING PROPERTIES: precedes
	// ////////////////

	public List<Topic> getPrecedesMapping() {
		List<Topic> topics = new ArrayList<>();

		for (Relationship rel : instanceNode
				.getRelationships(MappingProperties.PRECEDES)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getEnd()));
			}
		}
		return topics;

	}

	public void addPrecedesMapping(Topic topic) {
		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(MappingProperties.PRECEDES)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					MappingProperties.PRECEDES);

		}

	}

	public void removePrecedesMapping(Topic topic) {
		for (Relationship r : instanceNode
				.getRelationships(MappingProperties.PRECEDES)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}
		}

	}

	// /////////////////
	// MAPPING PROPERTIES: specializedBy
	// ////////////////

	public List<Topic> getSpecializedMapping() {
		List<Topic> topics = new ArrayList<>();

		for (Relationship rel : instanceNode
				.getRelationships(MappingProperties.SPECIALIZEDBY)) {
			if (rel.getEnd() != null) {
				topics.add(new Topic(rel.getEnd()));
			}
		}
		return topics;

	}

	public void addSpecializedMapping(Topic topic) {

		if (topic != null) {
			for (Relationship r : instanceNode
					.getRelationships(MappingProperties.SPECIALIZEDBY)) {
				if (r.getEnd().equals(topic.instanceNode)) {
					return;
				}
			}
			instanceNode.addRelationship(topic.instanceNode,
					MappingProperties.SPECIALIZEDBY);

		}

	}

	public void removeSpecializedMapping(Topic topic) {

		for (Relationship r : instanceNode
				.getRelationships(MappingProperties.SPECIALIZEDBY)) {

			if (r.getEnd().equals(topic.instanceNode)) {
				r.delete();
			}
		}

	}

	// /////////
	// GetModel

	public CourseModel getModel() {

		Relationship r = instanceNode
				.getSingleRelationship(CourseModelProperties.HASTOPIC);
		System.out.println("....."+r);
		return new CourseModel(r.getStart());

	}

	@Override
	public String toString() {

		return "Topic: " + instanceNode.getProperty(DataProperties.LABEL);

	}

	public String getCompleteDescription() {
		StringBuilder desc = new StringBuilder();
		desc.append("\nTopic: " + this.getId());
		desc.append("\nLabel: " + this.getLabel());
		desc.append("\nNext Topics: " + this.getNext());
		desc.append("\nParent of: " + this.getSubtopics());
		desc.append("\nMappings:");
		desc.append("\nEquivalent Mapping: " + this.getEquivalentMapping());
		desc.append("\nPrecedes Mapping: " + this.getPrecedesMapping());
		desc.append("\nSpecialized by Mapping: " + this.getSpecializedMapping());
		return "" + desc;
	}

	public static List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		for (Node n : NodeClient.getNodesByType(Types.TOPIC)) {
			topics.add(new Topic(n));
		}
		return topics;
	}
	
	public String toJSON(){
		String jsonResponse ="";
		
		jsonResponse = new JsonTopicBuilder(instanceNode).generateJson();
		//System.out.println(jsonResponse);
		return jsonResponse;
	}

}
