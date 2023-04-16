package fa.training.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import fa.training.entities.Audit;
import fa.training.utils.HibernateConnection;

public class AuditDao implements Dao<Audit> {

	private  Session session = HibernateConnection.getSessionFactory().openSession();

	private List<Audit> audits = new ArrayList<>();

	public AuditDao() {
//		audits.add(new Audit("aA_01"));
//		audits.add(new Audit("aA_02"));
//		audits.add(new Audit("aA_03"));
//		audits.add(new Audit("aA_04"));
//		audits.add(new Audit("aA_05"));
//		audits.add(new Audit("aA_06"));
//		audits.add(new Audit("aA_07"));
//		audits.add(new Audit("aA_08"));
//		audits.add(new Audit("aA_09"));
		audits.add(new Audit("aA_10"));
		audits.add(new Audit("aA_11"));
		audits.add(new Audit("aA_12"));
	}

	@Override
	public void save(Audit audit) {
		session.beginTransaction();
		session.save(audit);
		session.getTransaction().commit();
		session.close();
		System.out.println("save successfully...");
	}
	
	@Override
	public Audit getById(int id) {
		return session.find(Audit.class, id);
	}

	@Override
	public List<Audit> getAll() {
		Query<Audit> q2 = session.createQuery("from Audit", Audit.class);
		List<Audit> lists = q2.list();
		return lists;
	}

	@Override
	public void update(int id) {
		session.beginTransaction();
		Audit audit = session.find(Audit.class, id);
		audit.setPic("changed");
		session.update(audit);
		session.getTransaction().commit();
		session.close();
		System.out.println("update successfully...");
	}

	@Override
	public void delete(int id) {
		Audit audit = session.find(Audit.class, id);
		session.beginTransaction();
		session.delete(audit);
		session.getTransaction().commit();
		session.close();
		System.out.println("delete successfully...");
	}

	public List<Audit> getAudits() {
		return audits;
	}

}
