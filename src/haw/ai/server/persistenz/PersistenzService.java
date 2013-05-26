package haw.ai.server.persistenz;

import haw.ai.common.Log;
import haw.ai.server.common.HESEntity;

import org.hibernate.Session;

public class PersistenzService {
	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static void saveEntity(HESEntity entity) {
		Log.log(PersistenzService.class.getName(), "saveEntity");
		getSession().beginTransaction();
		getSession().save(entity);
		getSession().getTransaction().commit();
	}

	public static Session getSession() {
		Log.log(PersistenzService.class.getName(), "getSession");
		if (session == null) {
			Log.log(PersistenzService.class.getName(), "getSession", "opening new session");
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}

	public static void closeSession() {
		if (session != null) {
			Log.log(PersistenzService.class.getName(), "closeSession");
			session.close();
			session = null;
		}
	}

}
