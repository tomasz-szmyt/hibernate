package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

public class RunDaoImpl implements RunDao {

    @Override
    public void save(Run run) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(run);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Run findById(Integer id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Run run = session
                .createQuery("from Run where id=:id", Run.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse(null);

        session.getTransaction().commit();
        session.close();

        return run;
    }

    @Override
    public List<Run> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Run> list = session
                .createQuery("from Run")
                .list();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete Run where id=:id")
                .setParameter("id",id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();

    }
}
