package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
            try {
                Configuration config = new Configuration();
                config.configure();
                sessionFactory = config.buildSessionFactory();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
