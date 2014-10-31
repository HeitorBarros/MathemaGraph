package br.tips.core.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;
import br.tips.core.model.entities.utils.JsonTopicBuilder;
import br.tips.core.properties.PropertyNames;

@Path("/Topic")
public class TopicService {

	@PUT
	@Path("/new/{label}")
	public Response createNewTopic(@PathParam("label") String label){
		Topic t = EntityBuilder.createNewTopic(label);
		
		if (t==null) {
			return Response.notModified().build();
		}
		return Response.ok().build();
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteTopic(@PathParam("id") String id){
		Topic t = EntityBuilder.buildTopic(id);
		
		if (t==null) {
			return Response.notModified().build();
		}
		t.delete();
		return Response.ok().build();
	}
	
	@POST
	@Path("/{tid1}/addNext/{idNext}")
	public void addNext(@PathParam("tid1") String tid1, @PathParam("idNext") String next){
		Topic t1 = EntityBuilder.buildTopic(tid1);
		Topic tnext = EntityBuilder.buildTopic(next);
		
		if (t1==null||tnext==null) {
			return;
		}
		
		t1.addNext(tnext);
	}
	
	@GET
	@Path("/{id}/next")
	@Produces(MediaType.APPLICATION_XML)
	public Response getNext(@PathParam("id") String id){
		
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic nextTopic : t.getNext()) {
			topicIds.add(nextTopic.getId());
		}

		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	
	@GET
	@Path("/{id}/previous")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPrevious(@PathParam("id") String id){
		
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic previousTopic : t.getPrevious()) {
			topicIds.add(previousTopic.getId());
		}

		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	@POST
	@Path("/{id}/removeNext/{next}")
	public void removeNext(@PathParam("id") String tid1, @PathParam("next") String next){
		Topic t= EntityBuilder.buildTopic(tid1);
		Topic tNext = EntityBuilder.buildTopic(next);
		
		if (t==null||tNext==null) {
			return;
		}
		
		t.removeNext(tNext);
		
	}
	
	@GET
	@Path("/{id}/parents")
	@Produces(MediaType.APPLICATION_XML)
	public Response getParents(@PathParam("id") String id){
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		//System.out.println("HEY "+t);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic parentTopic : t.getSupertopics()) {
			topicIds.add(parentTopic.getId());
		}
		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	
	@GET
	@Path("/{id}/children")
	@Produces(MediaType.APPLICATION_XML)
	public Response getSubtopics(@PathParam("id") String id){
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic previousTopic : t.getSubtopics()) {
			topicIds.add(previousTopic.getId());
		}

		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	
	
	@GET
	@Path("/{id}/mapping/equivalent")
	@Produces(MediaType.APPLICATION_XML)
	public Response getEquivalentMapping(@PathParam("id") String id){
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic equiTopic : t.getEquivalentMapping()) {
			topicIds.add(equiTopic.getId());
		}

		return Response.ok().entity(new ResponseList(topicIds)).build();	
	}
	
	@GET
	@Path("/{id}/mapping/precedes")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPrecedesMapping(@PathParam("id") String id){
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic subsequentTopic : t.getPrecedesMapping()) {
			topicIds.add(subsequentTopic.getId());
		}
		
		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	
	@GET
	@Path("/{id}/mapping/specialized")
	@Produces(MediaType.APPLICATION_XML)
	public Response getSpecializedMapping(@PathParam("id") String id){
		
		ArrayList<String> topicIds = new ArrayList<String>();
		Topic t = EntityBuilder.buildTopic(id);
		if (t==null) {
			return Response.noContent().build();
		}
		for (Topic specTopic : t.getSpecializedMapping()) {
			topicIds.add(specTopic.getId());
		}
		
		return Response.ok().entity(new ResponseList(topicIds)).build();
		
	}
	
	@GET
	@Path("/{id}/model/")
	public String getRootTopic(@PathParam("id")String id){
		Topic t = EntityBuilder.buildTopic(id);
		if(t==null){
			return "";
		}
		CourseModel cm = t.getModel();
		if (cm==null) {
			return "";
		}
		String root =cm.getId();

		return root;
	}
	
	@GET
	@Path("/{id}/classification/")
	public String getClassification(@PathParam("id")String id){
		
		Topic t = EntityBuilder.buildTopic(id);
		if(t==null){
			return "";
		}
		String classification =t.getClassification(); 
		return classification; 
	}
	
	@GET
	@Path("/{id}/label/")
	public String getLabel(@PathParam("id")String id){
		Topic t = EntityBuilder.buildTopic(id);
		if(t==null){
			return "";
		}
		
		String label = t.getLabel(); 
		return label;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/info/")
	public Response getTopic(@PathParam("id")String id){
		String json ="";
		
		Topic topic = EntityBuilder.buildTopic(id);
		
		
		//json = new JsonTopicBuilder(topic).generateJson();
		
		return Response.ok(topic.toJSON()).build();
	}
	
	@POST
	@Path("/cmm")
	public void doIt(){
	}
	
}
