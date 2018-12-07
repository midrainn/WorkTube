package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.workDAO;
import package1.Table.NoPassWorkBean;
import package1.Table.WaitPassWorkBean;
import package1.Table.WorkBean;

import javax.persistence.Query;
import java.util.List;

public class workDAOImp1 implements workDAO {
    @Override
    public int getMax_Page() {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from package1.Table.WorkBean");
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }

    @Override

    public List<WorkBean> getListByPage(int page, int maxresult) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WorkBean");
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
//        Transaction tx= session.beginTransaction();

        List<WorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<WorkBean> getListByPage(int page) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WorkBean");
        query.setMaxResults(10);
        query.setFirstResult(page);
//        Transaction tx= session.beginTransaction();

        List<WorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Object getWorkByID(String id) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WorkBean where id=:ID");
        query.setParameter("ID", id);
        WorkBean wb = (WorkBean) ((org.hibernate.query.Query) query).uniqueResult();
//        Transaction tx= session.beginTransaction();
        session.close();
        return wb;
    }

    @Override
    public int getMax_PageByType(String type) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from package1.Table.WorkBean where type=:type");
        query.setParameter("type", type);
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }

    @Override
    public List<WorkBean> getListByPageAndType(int page, int maxresult, String type) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WorkBean where type=:type");
        query.setParameter("type", type);
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
        List<WorkBean> result = query.getResultList();
        session.close();
//        Transaction tx= session.beginTransaction();
        return result;
    }

    @Override
    public List<WorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WorkBean where writterid=:writterid");
        query.setParameter("writterid", writterID);
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
        List<WorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public int getMax_PageByUserID(String userid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from package1.Table.WorkBean where writterid=:id");
        query.setParameter("id", userid);
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }

    @Override
    public List<NoPassWorkBean> getNoPassListByWritterID(int page, int maxresult, String writterID) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.NoPassWorkBean where writterid=:writterid");
        query.setParameter("writterid", writterID);
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
        List<NoPassWorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<WaitPassWorkBean> getWaitPassListByWritterID(int page, int maxresult, String writterID) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from package1.Table.WaitPassWorkBean where writterid=:writterid");
        query.setParameter("writterid", writterID);
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
        List<WaitPassWorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public int getWaitWorkMax_PageByUserID(String userid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from WaitPassWorkBean where writterid=:id");
        query.setParameter("id", userid);
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }

    @Override
    public int getNoPassWorkMax_PageByUserID(String userid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from NoPassWorkBean where writterid=:id");
        query.setParameter("id", userid);
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }
}
