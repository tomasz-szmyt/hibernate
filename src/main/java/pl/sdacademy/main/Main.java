package pl.sdacademy.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        //saveTest();
        //readOneTest();
        deleteOne();
        HibernateUtils
                .getInstance()
                .getSessionFactory()
                .close();
    }
    public static void saveTest() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Run run = new Run();
        run.setName("Bieg Hibernate");
        run.setMembersLimit(999);

        session.saveOrUpdate(run);

        session.getTransaction().commit();
        session.close();
    }

    public static void readOneTest() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Run run = session
                .createQuery("from Run where id=:idParam" , Run.class)
                .setParameter("idParam" , 1)
                .uniqueResultOptional().orElse(null);

        session.getTransaction().commit();
        session.close();

        System.out.printf("%d %s %d",
                run.getId(),
                run.getName(),
                run.getMembersLimit());
    }

    public static void deleteOne() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete Run where id=:id")
                .setParameter("id",1)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
