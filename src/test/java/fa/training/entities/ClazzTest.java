package fa.training.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import fa.training.utils.HibernateConnection;

class ClazzTest {

	private static SessionFactory sessionFactory;
	private static Session session;

	
	@BeforeAll
	public static void setup() {
		sessionFactory = HibernateConnection.getSessionFactory();
		System.out.println("session factory is created");
	}
	@AfterAll
	public static void tearDown() {
		if(sessionFactory != null) sessionFactory.close();
		System.out.println("SessionFactory is destroyed");
	}
	
	
	@Test
	@Disabled
	void createClazz() {
		System.out.println("creating Clazz...");
		session.beginTransaction();
		Clazz clazz = new Clazz("a1","Java","Duong",LocalDate.parse("2020-01-10"),"backend",10);
		Integer id = (Integer)session.save(clazz);
		session.getTransaction().commit();
		
		Assertions.assertTrue(id > 0);
	}
	@Test
	@Disabled
	void getClazzById() {
		System.out.println("geting clazz...");
		Integer id = 1;
		Clazz clazz = session.find(Clazz.class, id);
		assertEquals("a1",clazz.getCode());
	}
	@Test
	@Disabled
	void getAllClazz() {
		System.out.println("geting all clazz...");
		Query<Clazz> query = session.createQuery("from Clazz",Clazz.class);
		List<Clazz> clazzList = query.getResultList();
		
		Assertions.assertFalse(clazzList.isEmpty());
	}
	@Test
	@Disabled
	void updateClazzByID() {
		System.out.println("updating clazz");
		Integer id = 1;
		Clazz clazz = new Clazz("a1","Java","Duong",LocalDate.parse("2021-01-10"),"backend",10);
		session.beginTransaction();
		session.update(clazz);
		session.getTransaction().commit();
		System.out.println("ok");
		Clazz updatedClazz = session.find(Clazz.class, id);
		
		assertEquals("c1",updatedClazz.getCode());
	}
	
	@Test
	@Disabled
	void deleteClazz() {
		System.out.println("deleting clazz...");
		Integer id = 3;
		Clazz clazz = session.find(Clazz.class, id);
		
		session.beginTransaction();
		session.delete(clazz);
		session.getTransaction().commit();
		
		Clazz deletedClazz = session.find(Clazz.class, id);
		
		Assertions.assertNull(deletedClazz);
	}
	

	@BeforeEach
	public void openSession() {
		session = sessionFactory.openSession();
		System.out.println("session is created");
	}
	
	@AfterEach
	public void closeSession() {
		if(session != null) session.close();
		System.out.println("session is closed\n");
	}
	
	
}
