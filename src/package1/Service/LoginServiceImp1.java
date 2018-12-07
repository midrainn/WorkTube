package package1.Service;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOService.commonuserDAO;
import package1.DAO.DAOService.userDAO;
import package1.Table.CommonUser;
import package1.Table.User;

public class LoginServiceImp1 implements LoginService {
    @Override

    public boolean Login(String username, String password) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        userDAO userdao=(userDAO)act.getBean("userDAOImp1");
        User user=(User)userdao.getUserByUserNameAndPassword(username,password);
        if(user!=null)
        {
            ActionContext.getContext().getSession().put("id",user.getId());
            ActionContext.getContext().getSession().put("adminname",user.getName());
            ActionContext.getContext().getSession().put("PageNum",0);
            ActionContext.getContext().getSession().put("WorkAction","getWorkByPage");
            return true;
        }
        else return false;
    }
    public boolean commonLogin(String username, String password) {
        ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
        commonuserDAO userdao=(commonuserDAO)act.getBean("commuserDAOImp1");
        CommonUser user=(CommonUser)userdao.getUserByUserNameAndPassword(username,password);
        if(user!=null)
        {
            ActionContext.getContext().getSession().put("id",user.getId());
            ActionContext.getContext().getSession().put("name",user.getName());
            ActionContext.getContext().getSession().put("PageNum",0);
            ActionContext.getContext().getSession().put("WorkAction","getWorkByPage");
            return true;
        }
        else return false;
    }
}
