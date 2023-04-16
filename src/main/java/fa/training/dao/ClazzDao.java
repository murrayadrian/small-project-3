package fa.training.dao;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import fa.training.entities.Clazz;
import fa.training.utils.HibernateConnection;

public class ClazzDao implements Dao<Clazz> {

	private Session session = HibernateConnection.getSessionFactory().openSession();

	private Clazz clazz = new Clazz();

	public ClazzDao() {
//		clazz = new Clazz("a1","Java","Duong",LocalDate.parse("2020-01-10"),"backend",200);
//		clazz = new Clazz("a2","Js","Phi",LocalDate.parse("2021-01-10"),"frontend",404);
//		clazz = new Clazz("a3","Java","Phi",LocalDate.parse("2020-12-24"),"backend",404);
		clazz = new Clazz("a4", "Java", "Huy", LocalDate.parse("2021-12-18"), "backend", 200);
	}

	@Override
	public void save(Clazz clazz) {
		session.beginTransaction();
		session.save(clazz);
		session.getTransaction().commit();
		session.close();
		System.out.println("save successfully...");
	}

	@Override
	public Clazz getById(int id) {
		return session.find(Clazz.class, id);
	}

	@Override
	public List<Clazz> getAll() {
		Query<Clazz> clazz = session.createQuery("from Clazz", Clazz.class);
		List<Clazz> lists = clazz.list();
		return lists;
	}

	@Override
	public void update(int id) {
		session.beginTransaction();
		Clazz clazz = session.find(Clazz.class, id);
		clazz.setAcceptedNumber(100);
		session.save(clazz);
		session.getTransaction().commit();
		session.close();
		System.out.println("update successfully...");
	}

	@Override
	public void delete(int id) {
		session.beginTransaction();
		Clazz clazz = session.find(Clazz.class, id);
		session.delete(clazz);
		session.getTransaction().commit();
		session.close();
		System.out.println("delete successfully...");
	}

	public Clazz getClazz() {
		return clazz;
	}

}
