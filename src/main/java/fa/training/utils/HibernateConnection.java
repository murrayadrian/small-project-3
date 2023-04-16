package fa.training.utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import fa.training.entities.Audit;
import fa.training.entities.Clazz;


public class HibernateConnection {
	private static SessionFactory sessionFactory = null;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			configuration.addAnnotatedClass(Clazz.class).addAnnotatedClass(Audit.class);
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}
}
