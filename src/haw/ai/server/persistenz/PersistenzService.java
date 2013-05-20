package haw.ai.server.persistenz;

import org.hibernate.Session;

import haw.ai.server.common.HESEntity;

public class PersistenzService {
	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static void saveEntity(HESEntity entity) {
		getSession().beginTransaction();
		getSession().save(entity);
		getSession().getTransaction().commit();
	}

	public static Session getSession() {
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}

	public static void closeSession() {
		if (session != null) {
			session.close();
			session = null;
		}
	}

}
