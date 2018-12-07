package package1.DAO.DAOImp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.inviteDAO;
import package1.Golbal.Golbal;
import package1.Table.inviteBean;

import javax.persistence.Query;
import java.util.List;

public class inviteDAOImp1 implements inviteDAO {
    @Override
    public Object getNewsByID(String id) {
        return true;
    }
//    public Object getNewsByID(String id) {
//        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
//        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
//        Session session=sf.openSession();
//        Query query = session.createQuery("from inviteBean where id=:id");
//        query.setParameter("id",id);
////        Transaction tx= session.beginTransaction();
//        List<inviteBean> list=query.getResultList();
//        session.close();
//        if(list.size()!=0)
//        {
//            return list.get(0);
//        }
//        else return null;
//    }

    @Override
    public boolean delNewsByObject(inviteBean inviteBean) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.delete(inviteBean);
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
    public boolean CreateInviteNum() {
        String InviteNum=Golbal.BuildUID();
        while (getNewsByID(InviteNum)!=null)
        {
            InviteNum=Golbal.BuildUID();
        }
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        inviteBean inviteBean=new inviteBean();
        inviteBean.setId(InviteNum);
        try {
            session.save(inviteBean);
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
    public boolean UseInviteNum(String s) {
        inviteBean inviteBean=(inviteBean)getNewsByID(s);
        if(inviteBean==null)
        {
            return false;
        }
        else
        {
            if(delNewsByObject(inviteBean))
            {
                return true;
            }
            return false;
        }
    }
}
