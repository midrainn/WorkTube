package package1.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import package1.Service.LoginService;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.PrintWriter;

public class  LoginAction extends ActionSupport
{
    public static final String LOGINSUCCESS="loginsuccess";
    public static final String LOGINERROR="loginerror";
    public static final String LOGINOUT="loginout";
    private String username="";
    private String password="";
    private LoginService ms;
    private String loginaction="";
    private String checkbox1="";
    public void setUsername(String username) {
        ServletActionContext.getRequest().getSession().setAttribute("username",username);
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMs(LoginService ms) {
        this.ms = ms;
    }

    public void setCheckbox1(String checkbox1) {
        this.checkbox1 = checkbox1;
    }

    public void setLoginaction(String loginaction) {
        this.loginaction = loginaction;
    }

    public  String execute() throws Exception
    {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        switch (loginaction) {
            case "adminlogin":
                if (ms.Login(username, password)) {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                    return null;
                } else {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                    return null;
                }
            case "commonlogin":
                if(ms.commonLogin(username,password))
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                    return null;
                } else {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                    return null;
                }
            case "loginout":
                if(ActionContext.getContext().getSession().get("name")!=null)
                {
                    ActionContext.getContext().getSession().remove("name");
                }

                if(ActionContext.getContext().getSession().get("adminname")!=null)
                {
                    ActionContext.getContext().getSession().remove("adminname");
                }
                writer.print("true");
                writer.flush();
                writer.close();
                return null;
            default:
                return ERROR;
        }
    }

}
