package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.worklogDAO;
import package1.Table.WorkLogBean;

import javax.persistence.Query;
import java.util.List;

public class worklogDAOImp1 implements worklogDAO {
    @Override
    public boolean AddLog(String workid, String log,String writterid,String name) {
        if(getNewsByID(workid)!=null)return false;
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        WorkLogBean work=new WorkLogBean();
        work.setWorkid(workid);
        work.setLog(log);
        work.setWritterid(writterid);
        work.setWorkname(name);
        try {
            session.save(work);
            transaction.commit();
        }
        catch (Exception e)
        {
            if(transaction!=null)
            {
                transaction.rollback();

            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean UpdateLog(String workid, String log) {
        if(getNewsByID(workid)==null)return false;
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        WorkLogBean work=(WorkLogBean)getNewsByID(workid);
        work.setLog(log);
        try {
            session.update(work);
            transaction.commit();
        }
        catch (Exception e)
        {
            if(transaction!=null)
            {
                transaction.rollback();

            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public Object getNewsByID(String id) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from WorkLogBean where workid=:id");
        query.setParameter("id",id);
        List<WorkLogBean> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public List<WorkLogBean> getListByPageAndUserID(int page, int maxresult, String userid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from WorkLogBean where writterid=:userid");
        query.setParameter("userid", userid);
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
        List<WorkLogBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public int getMax_PageByUserID(String userid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from WorkLogBean where writterid=:id");
        query.setParameter("id", userid);
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }
}
