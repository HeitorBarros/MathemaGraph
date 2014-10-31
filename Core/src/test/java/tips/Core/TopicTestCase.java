package tips.Core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;
import br.tips.core.persistence.GraphPersistence;

public class TopicTestCase {
	private static final String c1name = "TestC1";
	private static final String c2name = "TestC2";
	
	private static final String t1name = "TestT1";
	private static final String t2name = "TestT2";
	private static final String t3name = "TestT3";
	private static final String t4name = "TestT4";
	private static final String t5name = "TestT5";
	private static final String t6name = "TestT6";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EntityBuilder.createNewCourseModel(c1name);
		EntityBuilder.createNewCourseModel(c2name);

		EntityBuilder.createNewTopic(t1name);
		EntityBuilder.createNewTopic(t2name);
		EntityBuilder.createNewTopic(t3name);
		EntityBuilder.createNewTopic(t6name);
		EntityBuilder.createNewTopic(t5name);
		EntityBuilder.createNewTopic(t4name);
		//System.out.println("Instâncias criadas para início dos teste: "+GraphPersistence.getInstance().getAllNodes());
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		EntityBuilder.buildCourseModel(c1name).delete();
		EntityBuilder.buildCourseModel(c2name).delete();

		EntityBuilder.buildTopic(t1name).delete();
		EntityBuilder.buildTopic(t2name).delete();
		EntityBuilder.buildTopic(t3name).delete();

		EntityBuilder.buildTopic(t4name).delete();
		EntityBuilder.buildTopic(t5name).delete();
		EntityBuilder.buildTopic(t6name).delete();
		//System.out.println("Instâncias de teste deletadas. Restam no grafo: "+GraphPersistence.getInstance().getAllNodes());
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNext() {
		//CourseModel c1 = EntityBuilder.buildCourseModel(c1name);
		//CourseModel c2 = EntityBuilder.buildCourseModel(c2name);
		
		Topic t1 = EntityBuilder.buildTopic(t1name);
		Topic t2 = EntityBuilder.buildTopic(t2name);
		Topic t3 = EntityBuilder.buildTopic(t3name);
		//Topic t4 = EntityBuilder.buildTopic(t4name);
		//Topic t5 = EntityBuilder.buildTopic(t5name);
		//Topic t6 = EntityBuilder.buildTopic(t6name);
		
		t1.addNext(t2);
		t1.addNext(t3);
		assertEquals(2, t1.getNext().size());
		
		t1.removeNext(t3);
		assertEquals(1, t1.getNext().size());
		
		t2.addNext(t3);
		
		assertEquals(t3, t1.getFirstNext().getFirstNext());
		
	
	}
	
	@Test
	public void testParentOf(){
		Topic t1 = EntityBuilder.buildTopic(t1name);
		Topic t2 = EntityBuilder.buildTopic(t2name);
		Topic t3 = EntityBuilder.buildTopic(t3name);
		
		t1.addSubtopic(t2);
		
		assertTrue(t1.getSubtopics().contains(t2));
		
		t1.addSubtopic(t3);
		assertEquals(2, t1.getSubtopics().size());
		
		t1.removeSubtopic(t2);
		assertEquals(1, t1.getSubtopics().size());
		
	}
	
	@Test
	public void testMappingEquivalence(){
		
	}

}
