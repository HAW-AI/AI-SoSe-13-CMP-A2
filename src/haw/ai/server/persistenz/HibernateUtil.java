package haw.ai.server.persistenz;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final String ABS_PATH_TO_CONFIG = "/Users/patrick/Dropbox/HAW/AI/ws/Aufgabe2";
	private static Session session;

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			File confFile = new File(ABS_PATH_TO_CONFIG+File.separator+"hibernate.cfg.xml");
			Configuration conf = new Configuration().configure(confFile.getAbsoluteFile());
			SchemaExport schema = new SchemaExport(conf);
			schema.create(false, true);
			return conf.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
