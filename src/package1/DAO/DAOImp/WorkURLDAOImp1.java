package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.WorkURLDAO;
import package1.Table.inviteBean;

import javax.persistence.Query;
import java.util.List;

public class WorkURLDAOImp1 implements WorkURLDAO {
    @Override
    public Object getNewsByID(String id) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Query query = session.createQuery("from WorkURLBean where id=:id");
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
