package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.*;
import package1.Table.*;

import javax.persistence.Query;
import java.util.List;

public class passworkImp1 implements passworkDAO {
    @Override
    public boolean PassWork(String workID) {
        if(workID==null)return false;
        waitpassworkDAO waitpassworkDAO=new waitpassworkImp1();
        WorkURLDAO workurl=new WorkURLDAOImp1();
        WaitPassWorkBean waitPassWork=(WaitPassWorkBean)waitpassworkDAO.getNewsByID(workID);
        WorkURLBean workURLBean=(WorkURLBean)workurl.getNewsByID(workID);

        if(waitPassWork==null||workURLBean==null)return false;
        commonuserDAO commonuserDAO=new comuserDAOImp1();
        String username=waitPassWork.getWritterid();
        if(username==null)return false;
        username=((CommonUser)commonuserDAO.getNewsByID(username)).getName();
        if(username==null)return false;
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        WorkBean work=new WorkBean();
        worklogDAO worklogDAO=new worklogDAOImp1();
        work.setLike_num(0);
        work.setId(workID);
        work.setName(workURLBean.getName());
        work.setUrl("WorkFile/Images/"+workURLBean.getFaceimage());
        work.setWritterid(waitPassWork.getWritterid());
        work.setType(waitPassWork.getType());
        work.setWritter(username);
        try {
            session.save(work);
            session.remove(waitPassWork);
            worklogDAO.UpdateLog(workID,"pass");
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
        Query query = session.createQuery("from WorkBean where id=:id");
        query.setParameter("id",id);
        List<inviteBean> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }
}
