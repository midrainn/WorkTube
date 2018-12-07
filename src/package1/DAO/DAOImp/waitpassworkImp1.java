package package1.DAO.DAOImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.commonuserDAO;
import package1.DAO.DAOService.waitpassworkDAO;
import package1.DAO.DAOService.worklogDAO;
import package1.Golbal.Golbal;
import package1.Table.CommonUser;
import package1.Table.WaitPassWorkBean;
import package1.Table.WorkURLBean;
import package1.Table.inviteBean;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class waitpassworkImp1 implements waitpassworkDAO {
    @Override
    public boolean AddWork(String writterID, String name, String faceimageUID, String showimage1UID, String showimage2UID, String showimage3UID, String videoUID, String programUID, String type) {
        if(writterID==null)return false;
        String uuid=UUID.randomUUID().toString();
        while (getNewsByID(uuid)!=null)
        {
            uuid=UUID.randomUUID().toString();
        }
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf=(SessionFactory) act.getBean("sessionFactory");
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();
        WaitPassWorkBean waitPassWork=new WaitPassWorkBean();
        WorkURLBean workURL=new WorkURLBean();
        worklogDAO worklogDAO=new worklogDAOImp1();

        if(faceimageUID==null)
        {
            if(type.equals("C/C++"))
            {
                faceimageUID="C++.jpg";
            }
            else if(type.equals("C#"))
            {
                faceimageUID="Csharp.jpg";
            }
            else
            {
                faceimageUID=type+".jpg";
            }
        }
        if(type.equals("C/C++"))
        {
            type="C/Cplus";
        }
        commonuserDAO comuserDAO=new comuserDAOImp1();
        waitPassWork.setId(uuid);
        waitPassWork.setType(type);
        waitPassWork.setWritterid(writterID);
        workURL.setWritter(comuserDAO.getNameByID(writterID));
        workURL.setId(uuid);
        workURL.setName(name);
        workURL.setFaceimage(faceimageUID);
        workURL.setShowimage1(showimage1UID);
        workURL.setShowimage2(showimage2UID);
        workURL.setShowimage3(showimage3UID);
        workURL.setProgram(programUID);
        workURL.setVideo(videoUID);
        workURL.setType(type);
        try {
            session.save(waitPassWork);
            session.save(workURL);
            worklogDAO.AddLog(uuid,"wait",writterID,name);
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
        Query query = session.createQuery("from WaitPassWorkBean where id=:id");
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
    public List<WaitPassWorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID) {
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
    public List<WaitPassWorkBean> getListByPage(int page, int maxresult) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from WaitPassWorkBean");
        query.setMaxResults(maxresult);
        query.setFirstResult(page);
//        Transaction tx= session.beginTransaction();

        List<WaitPassWorkBean> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public int getMax_Page() {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("select count(*) from WaitPassWorkBean");
        int maxpage = new Long((long) ((org.hibernate.query.Query) query).uniqueResult()).intValue();
        session.close();
        return maxpage;
    }

    @Override
    public String getWorkNameByWorkID(String workid) {
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
        SessionFactory sf = (SessionFactory) act.getBean("sessionFactory");
        Session session = sf.openSession();
        Query query = session.createQuery("from WorkURLBean where id=:id");
        query.setParameter("id",workid);
        List<WorkURLBean> list = query.getResultList();
        session.close();
        if(list.size()!=0)
        {
            return list.get(0).getName();
        }
        else return null;
    }
}
