package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.WorkURLDAO;
import package1.DAO.DAOService.nopassworkDAO;
import package1.DAO.DAOService.waitpassworkDAO;
import package1.DAO.DAOService.worklogDAO;
import package1.Table.*;

import javax.persistence.Query;
import java.util.List;

public class nopassworkImp1 implements nopassworkDAO {
    @Override
    public boolean NoPassWork(String WorkID,String mark) {
        if(WorkID==null)return false;
        waitpassworkDAO waitpassworkDAO=new waitpassworkImp1();
        WaitPassWorkBean waitPassWork=(WaitPassWorkBean)waitpassworkDAO.getNewsByID(WorkID);
        if(waitPassWork==null)return false;
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        NoPassWorkBean work=new NoPassWorkBean();
        worklogDAO worklogDAO=new worklogDAOImp1();
        work.setId(WorkID);
        work.setWritterid(waitPassWork.getWritterid());
        work.setMark(mark);
        try {
            worklogDAO.UpdateLog(WorkID,"nopass");
            session.save(work);
            session.remove(waitPassWork);
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
        Query query = session.createQuery("from NoPassWorkBean where id=:id");
        query.setParameter("id",id);
        List<inviteBean> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public List<NoPassWorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID) {
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
}
