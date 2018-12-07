package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.commonuserDAO;
import package1.Golbal.Golbal;
import package1.Table.CommonUser;
import package1.Table.User;

import javax.persistence.Query;
import java.util.List;

public class comuserDAOImp1 implements commonuserDAO {
    @Override
    public Object getUserByUserNameAndPassword(String username, String password) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from package1.Table.CommonUser where username=:uname and password=md5(:passwd)");
        query.setParameter("uname",username);
        query.setParameter("passwd",password);
//        Transaction tx= session.beginTransaction();
        List<User> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public Object getNewsByID(String id)
    {

        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from package1.Table.CommonUser where id=:id");
        query.setParameter("id",id);
//        Transaction tx= session.beginTransaction();
        List<User> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public Object getNewsByUsername(String username) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from package1.Table.CommonUser where username=:uname");
        query.setParameter("uname",username);
        List<User> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public boolean CreateAcount(String name, String username, String password, String phonenumbser) {
        if(getNewsByUsername(username)!=null)return false;
        String uid=Golbal.BuildUID();
        while (getNewsByID(uid)!=null)
        {
            uid=Golbal.BuildUID();
        }
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        CommonUser user=new CommonUser();
        user.setId(uid);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhonenumber(phonenumbser);
        try {
            session.save(user);
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
    public Object getNewsByPhonenumber(String phonenumber) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from CommonUser where phonenumber=:phonenumber");
        query.setParameter("phonenumber",phonenumber);
//        Transaction tx= session.beginTransaction();
        List<User> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0);
        }
        else return null;
    }

    @Override
    public String getNameByID(String userid) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from CommonUser where id=:userid");
        query.setParameter("userid",userid);
//        Transaction tx= session.beginTransaction();
        List<CommonUser> list=query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0).getName();
        }
        else return null;
    }
}
