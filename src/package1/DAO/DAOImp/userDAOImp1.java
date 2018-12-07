package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.Table.User;
import package1.DAO.DAOService.userDAO;

import javax.persistence.Query;
import java.util.List;

public class userDAOImp1 implements userDAO {
    @Override
    public Object getNewsByID(String id) {
        return null;
    }

    @Override
    public Object getUserByUserNameAndPassword(String username,String password) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from package1.Table.User where username=:uname and password=md5(:passwd)");
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
}
