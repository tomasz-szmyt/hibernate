package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {

    RunDao runDao = new RunDaoImpl();

    @BeforeEach
    public void clearTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete Run")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        //Given
        Run run = new Run();
        run.setName("Testowa nazwa 1");
        run.setMembersLimit(10);

        //WHEN
        runDao.save(run);
        Run saved = runDao.findById(run.getId());

        //THEN
        assertNotNull(saved);
        assertEquals(run.getId(), saved.getId());
        assertEquals(run.getName(), saved.getName());
        assertEquals(run.getMembersLimit(), saved.getMembersLimit());
    }


    @Test
    void delete() {
        //Given
        Run run = new Run();
        run.setName("Testowa nazwa 1");
        run.setMembersLimit(10);

        //WHEN
        runDao.save(run);
        Run saved = runDao.findById(run.getId());
        assertNotNull(saved);

        runDao.deleteById(run.getId());

        Run deleted = runDao.findById(run.getId());
        assertNull(deleted);
    }

    @Test
    void findAll() {
        Run run1 = new Run();
        run1.setName("Bieg pierwszy");
        run1.setMembersLimit(1032);

        Run run2 = new Run();
        run2.setName("Inny bieg testowy");
        run2.setMembersLimit(100);

        runDao.save(run1);
        runDao.save(run2);

        List<Run> list = runDao.findAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }
}
