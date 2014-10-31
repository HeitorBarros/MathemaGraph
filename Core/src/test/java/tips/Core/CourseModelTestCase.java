package tips.Core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.tips.core.model.entities.CourseModel;
import br.tips.core.model.entities.Domain;
import br.tips.core.model.entities.EntityBuilder;
import br.tips.core.model.entities.Topic;


public class CourseModelTestCase {

	private static final String c1name = "TestC1";
	private static final String c2name = "TestC2";
	
	private static final String t1name = "TestT1";
	private static final String t2name = "TestT2";
	private static final String t3name = "TestT3";
	
	private static final String d1name = "TestD1";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		EntityBuilder.createNewCourseModel(c1name);
//		EntityBuilder.createNewCourseModel(c2name);
//		EntityBuilder.createNewDomain(d1name);
//		EntityBuilder.createNewTopic(t1name);
//		EntityBuilder.createNewTopic(t2name);
//		EntityBuilder.createNewTopic(t3name);
		//System.out.println("Instâncias criadas para início dos teste: "+GraphPersistence.getInstance().getAllNodes());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		EntityBuilder.buildCourseModel(c1name).delete();
//		EntityBuilder.buildCourseModel(c2name).delete();
//		EntityBuilder.buildDomain(d1name).delete();
//		EntityBuilder.buildTopic(t1name).delete();
//		EntityBuilder.buildTopic(t2name).delete();
//		EntityBuilder.buildTopic(t3name).delete();
		//System.out.println("Instâncias de teste deletadas. Restam no grafo: "+GraphPersistence.getInstance().getAllNodes());
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuilder() {
		CourseModel c1 = EntityBuilder.createNewCourseModel(c1name);
		
		assertEquals(c1name, c1.getLabel());
		
		c1.delete();
	}
	
	@Test
	public void testDomain(){
		CourseModel c1 = EntityBuilder.createNewCourseModel(c1name);
		Domain d1 = EntityBuilder.createNewDomain(d1name);
		
		if (c1.getDomain().size()!=0) {
			fail();
		}
		
		c1.addDomain(d1);
		assertEquals(d1, c1.getDomain().get(0));
		
		c1.removeDomain(d1);
		
		if (c1.getDomain().size()!=0) {
			fail();
		}
		
		c1.delete();
		d1.delete();
		
	}
	
	@Test
	public void testTopic(){
		CourseModel c1 = EntityBuilder.createNewCourseModel(c1name);
		Topic t1 = EntityBuilder.createNewTopic(t1name);
		Topic t2 = EntityBuilder.createNewTopic(t2name);
		Topic t3 = EntityBuilder.createNewTopic(t3name);
		
		c1.addTopic(t1);
		c1.addTopic(t2);
		c1.addTopic(t3);
		
		if (c1.getTopics().size()!=3) {
			fail();
		}
		System.out.println(c1.getTopics().size());
		c1.removeTopic(t2);
		System.out.println(c1.getTopics().size());
		if (c1.getTopics().size()!=2) {
			fail();
		}
		if (c1.getTopics().contains(t2)) {
			fail();
		}
		//t2 não é um tópico de c1, então não deve ser um RootTopic.
		c1.setRootTopic(t2);
		if (c1.getRootTopic()!=null) {
			fail();
		}
		//t3 é tópico de c1, então o método deve funcionar adequadamente.
		c1.setRootTopic(t3);
		if (!c1.getRootTopic().equals(t3)) {
			fail();
		}
		//Vamos remover t3 dos tópicos de c1. Ele deve ser automaticamente excluido
		//da posição de root topic e este relacionamento deve ser nulo.
		
		c1.removeTopic(t3);
		assertNull(c1.getRootTopic());
		
		c1.delete();
		t1.delete();
		t2.delete();
		t3.delete();

	}

}
