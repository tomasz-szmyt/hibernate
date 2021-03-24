package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunMemberDaoImplTest {

    RunMemberDao runMemberDao = new RunMemberDaoImpl();

    @BeforeEach
    public void clearTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete RunMember")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        //Given
        RunMember runMember = new RunMember();
        runMember.setName("Testowa nazwa 1");
        runMember.setStartNumber(100);
        runMember.setAge(100);

        //WHEN
        runMemberDao.save(runMember);
        RunMember saved = runMemberDao.findById(runMember.getId());

        //THEN
        assertNotNull(saved);
        assertEquals(runMember.getId(), saved.getId());
        assertEquals(runMember.getName(), saved.getName());
        assertEquals(runMember.getStartNumber(), saved.getAge());
    }


    @Test
    void delete() {
        //Given
        RunMember runMember = new RunMember();
        runMember.setName("Testowa nazwa 1");
        runMember.setStartNumber(100);
        runMember.setAge(100);

        //WHEN
        runMemberDao.save(runMember);
        RunMember saved = runMemberDao.findById(runMember.getId());
        assertNotNull(saved);

        runMemberDao.deleteById(runMember.getId());

        RunMember deleted = runMemberDao.findById(runMember.getId());
        assertNull(deleted);
    }

    @Test
    void findAll() {
        RunMember runMember1 = new RunMember();
        runMember1.setName("Bieg pierwszy");
        runMember1.setStartNumber(1032);

        RunMember runMember2 = new RunMember();
        runMember2.setName("Inny bieg testowy");
        runMember2.setStartNumber(100);

        runMemberDao.save(runMember1);
        runMemberDao.save(runMember2);

        List<RunMember> list = runMemberDao.findAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }
}