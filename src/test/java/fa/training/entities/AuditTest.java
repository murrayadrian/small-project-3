package fa.training.entities;

import static org.junit.jupiter.api.Assertions.*;
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

class AuditTest {

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
	void createAudit() {
		System.out.println("creating Audit...");
		session.beginTransaction();
		Audit audit = new Audit();
		Integer id = (Integer)session.save(audit);
		session.getTransaction().commit();
		
		Assertions.assertTrue(id > 0);
	}
	@Test
	@Disabled
	void getAuditById() {
		System.out.println("geting audit...");
		Integer id = 1;
		Audit audit = session.find(Audit.class, id);
		assertEquals("2020-01-02",audit.getDate());
	}
	@Test
	@Disabled
	void getAllAudit() {
		System.out.println("geting all audit...");
		Query<Audit> query = session.createQuery("from Audit",Audit.class);
		List<Audit> auditList = query.getResultList();
		
		Assertions.assertFalse(auditList.isEmpty());
	}
	@Test
	@Disabled
	void updateAuditByID() {
		System.out.println("updating audit");
		Integer id = 1;
		Audit audit = new Audit();
		session.beginTransaction();
		session.update(audit);
		session.getTransaction().commit();
		Audit updatedAudit = session.find(Audit.class, id);
		
		assertEquals("2020-01-02",updatedAudit.getDate());
	}
	
	@Test
	@Disabled
	void deleteAudit() {
		System.out.println("deleting audit...");
		Integer id = 1;
		Audit audit = session.find(Audit.class, id);
		
		session.beginTransaction();
		session.delete(audit);
		session.getTransaction().commit();
		
		Audit deletedAudit = session.find(Audit.class, id);
		
		Assertions.assertNull(deletedAudit);
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
