package fa.training.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;

import fa.training.dao.AuditDao;
import fa.training.dao.ClazzDao;
import fa.training.dao.Dao;
import fa.training.entities.Audit;
import fa.training.entities.Clazz;
import fa.training.utils.Check;
import fa.training.utils.HibernateConnection;

public class BusinessServiceImpl implements BusinessService {

	private Dao<Audit> auditDao;
	private Dao<Clazz> clazzDao;
	private Session session = HibernateConnection.getSessionFactory().openSession();

	// Business operation

	@Override
	public void insert() {
		ClazzDao clazzDao = new ClazzDao();
		AuditDao auditDao = new AuditDao();

		Clazz clazz = clazzDao.getClazz();
		List<Audit> audits = auditDao.getAudits();

		Check.isValid(clazz);

		session.beginTransaction();
		for (int i = 0; i < audits.size(); i++) {

			Check.isValid(audits.get(i));

			clazz.getAuditList().add(audits.get(i));
			audits.get(i).setClazzId(clazz);

			session.save(audits.get(i));
		}

		session.save(clazz);
		session.getTransaction().commit();
		session.close();
		System.out.println("create successfully...");
	}

	@Override
	public int getTotalTrainees(String skill, int year) {

		session.beginTransaction();

		String query = String.format("from Clazz where (name = '%s' AND startDate >= '%d' AND startDate<='%d')", skill,
				year, year + 1);
		Query<Clazz> q2 = session.createQuery(query, Clazz.class);
		List<Clazz> lists = q2.list();
		int sum = 0;
		for (Clazz clazz : lists) {
			System.out.println(clazz);
			sum += 1;

		}
		session.getTransaction().commit();
		session.close();

		return sum;
	}

	@Override
	public void pivotTableClazz(int month) {

		session.beginTransaction();

		Query<Object[]> query = session.createQuery("select c.skill,c.status,c.startDate from Clazz c", Object[].class);
		List<Object[]> lists = query.list();

		Map<Integer, List<Object[]>> maps = new HashMap<>();
		for (Object[] obj : lists) {
			int mon = ((LocalDate) obj[2]).getMonthValue();
			maps.putIfAbsent(mon, new ArrayList<Object[]>());
			Object[] values = { obj[0], obj[1] };
			maps.get(mon).add(values);

		}
		for (Integer mon : maps.keySet()) {

			System.out.print("Month " + mon + " : ");
			for (Object[] obj : maps.get(mon)) {
				for (int i = 0; i < obj.length; i++) {
					System.out.print(obj[i] + " ");
				}
				System.out.print(", ");
			}
			System.out.println();
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Clazz> findByAdmin(String clazzAdmin) {
		String query = String.format("from Clazz where admin = '%s'", clazzAdmin);
		Query<Clazz> getClazz = session.createQuery(query, Clazz.class);
		List<Clazz> clazzs = getClazz.list();
		for (Clazz clazz : clazzs) {
			System.out.println(clazz);
		}

		return clazzs;
	}

	@Override
	public List<Object[]> findAllDetail() {

		session.beginTransaction();

		Query<Object[]> q = session.createQuery("from Clazz c join c.auditList a ON c.id = a.clazzId.id",
				Object[].class);
		List<Object[]> objList = q.list();

		Map<Clazz, ArrayList<Audit>> maps = new HashMap<>();
		for (Object[] obj : objList) {
			maps.putIfAbsent((Clazz) obj[0], new ArrayList<Audit>());
			maps.get((Clazz) obj[0]).add((Audit) obj[1]);
		}
		for (Clazz clazz : maps.keySet()) {
			System.out.println(clazz);
			System.out.println(maps.get(clazz));
		}
		session.getTransaction().commit();
		session.close();

		return objList;
	}

	// DAO operation
	public Clazz getClazzById(int id) {
		return clazzDao.getById(id);
	}

	public List<Clazz> getAllClazz() {
		return clazzDao.getAll();
	}

	public void saveClazz(Clazz clazz) {
		clazzDao.save(clazz);
	}

	public void updateClazzById(int id) {
		clazzDao.update(id);
	}

	public void deleleteClazzById(int id) {
		clazzDao.delete(id);
	}

	// DAO operation
	
	public Audit getAuditById(int id) {
		return auditDao.getById(id);
	}

	public List<Audit> getAllAudit() {
		return auditDao.getAll();
	}

	public void saveAudit(Audit audit) {
		auditDao.save(audit);
	}

	public void updateAuditById(int id) {
		auditDao.update(id);
	}

	public void deleteAuditById(int id) {
		auditDao.delete(id);
	}

}
