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
import br.tips.core.properties.PropertyNames;


@Path(value = "/CourseModel")
public class CourseModelService{

	@PUT
	@Path("/new/{label}")
	public void createNewModel(@PathParam("label") String label){
		CourseModel cm = EntityBuilder.createNewCourseModel(label);
		//CourseModel cm = EntityBuilder.buildCourseModel(id);
		//System.out.println(cm.getId());
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteModel(@PathParam("id") String id){
		CourseModel cm = EntityBuilder.buildCourseModel(id);
		
		if (cm ==null) {
			return Response.notModified().build();
		}
		cm.delete();
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}/description")
	public String getDescription(@PathParam("id") String id){
		CourseModel cm = EntityBuilder.buildCourseModel(id);
		
		return cm.getCompleteDescription();
	}
	
	@GET
	@Path("/{id}/label")
	public String getLabel(@PathParam("id") String id){

		
		CourseModel cm = EntityBuilder.buildCourseModel(id);
		String response = cm.getLabel();
		
		return response;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllModels(){
			
		List<String> cmIds = new ArrayList<String>();
		for(CourseModel cm: CourseModel.getAllCourseModels()){
			cmIds.add(cm.getId());
		}
		ResponseList response = new ResponseList(cmIds);
		
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("/{id}/topics")
	@Produces(MediaType.APPLICATION_XML)
	public Response getTopics(@PathParam("id") String id){
		
		List<String> topicIds = new ArrayList<>(); 
		CourseModel cm = EntityBuilder.buildCourseModel(id);
		if (cm==null) {
			return Response.noContent().build();
		}
		for(Topic t: cm.getTopics()){
			System.out.println("->>>"+t);
			topicIds.add(t.getId());
		}
		
		System.out.println("->"+topicIds);
		
		ResponseList response = new ResponseList();
		response.setList(topicIds);

		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("/{id}/topics/labels")
	@Produces(MediaType.APPLICATION_XML)
	public Response getTopicsAndLabels(@PathParam("id") String id){
		
		List<String> topics = new ArrayList<>(); 
		CourseModel cm = EntityBuilder.buildCourseModel(id);
		if (cm==null) {
			return Response.noContent().build();
		}
		topics.addAll( cm.getTopicsAndLabels());
		
		ResponseList response = new ResponseList();
		response.setList(topics);
		//System.out.println("a\na\na\na-0>>"+topics);
		return Response.ok().entity(response).build();
	}

	@POST
	@Path("/{id}/addTopic/{tid}")
	public void addTopic(@PathParam("id") String modelId, @PathParam("tid")String topicId){
		CourseModel cm = EntityBuilder.buildCourseModel(modelId);
		Topic t = EntityBuilder.buildTopic(topicId);
		if (cm==null) {
			return;
		}
		
		cm.addTopic(t);
	}
	
	@POST
	@Path("/{mid}/removeTopic/{tid}")
	public void removeTopic(@PathParam("mid")String modelId, @PathParam("tid")String topicId){
		CourseModel cm = EntityBuilder.buildCourseModel(modelId);
		Topic t = EntityBuilder.buildTopic(topicId);
		if (cm==null) {
			return;
		}
		cm.removeTopic(t);
	}
	
	@POST
	@Path("/{mid}/setRoot/{tid}")
	public void setRootTopic(@PathParam("mid")String modelId, @PathParam("tid")String topicId){
		CourseModel cm = EntityBuilder.buildCourseModel(modelId);
		Topic t = EntityBuilder.buildTopic(topicId);
		if (cm==null||t==null) {
			return;
		}
		cm.setRootTopic(t);
	}
	
	@GET
	@Path("/{mid}/getRoot/")
	public String getRootTopic(@PathParam("mid")String modelId){
		
		CourseModel cm = EntityBuilder.buildCourseModel(modelId);
		//System.out.println("->>"+cm.getRootTopic());
		if (cm==null||cm.getRootTopic()==null) {
			return "";
		}
		String root = cm.getRootTopic().getId();
		return root;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/info/")
	public Response getModel(@PathParam("id")String id){
		String json ="";
		
		CourseModel model = EntityBuilder.buildCourseModel(id);
		
		
		//json = new JsonTopicBuilder(topic).generateJson();
		
		return Response.ok(model.toJSON()).build();
	}
	
}
