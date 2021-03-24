package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.dao.RunMemberDao;

import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

public class RunMemberDaoImpl implements RunMemberDao {

    @Override
    public void save(RunMember runMember) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(runMember);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public RunMember findById(Integer id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        RunMember runMember = session
                .createQuery("from RunMember where id=:id", RunMember.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse(null);

        session.getTransaction().commit();
        session.close();

        return runMember;
    }

    @Override
    public List<RunMember> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<RunMember> list = session
                .createQuery("from RunMember")
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

        session.createQuery("delete RunMember where id=:id")
                .setParameter("id",id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();

    }
}
