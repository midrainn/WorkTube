package package1.Action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import package1.DAO.DAOService.inviteDAO;
import package1.Golbal.Golbal;
import package1.JavaBean.MessageBean;
import package1.JavaBean.PassBean;
import package1.DAO.DAOService.commonuserDAO;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

public class RegisterAction extends ActionSupport {
    public static final String RegisterError="registererror";
    public static final String RegisterSuccess="registersuccess";
    public static final String GetPassNumError="getpassnumerror";
    public static final String GetPassNumSuccess="getpassnumsuccess";
    public static final String UsePassNumError="usepasserror";
    private String username="";
    private String password="";
    private String phonenumber="";
    private String passnum="";
    private String name="";
    private String act="UsePassNum";
    private String invitenum="";
    private PassBean pb=null;
    private commonuserDAO commonuserdao;
    private inviteDAO invitedao;
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();

        switch (act)
        {
            case "GetPassNum":
                if(invitedao.getNewsByID(invitenum)==null)
                {
                    writer.print("该邀请码无效");
                    writer.flush();
                    writer.close();
                    return null;
                }
                if(commonuserdao.getNewsByPhonenumber(phonenumber)!=null)
                {
                    writer.print("该手机已经注册");
                    writer.flush();
                    writer.close();
                    return null;
                }
                if(ActionContext.getContext().getSession().get("PassBean")==null)
                {
                    if(!Golbal.isPhonenumber(phonenumber))
                    {
                        writer.print("无法发送验证码到该手机");
                        writer.flush();
                        writer.close();
//                        return GetPassNumError;

                    }
                    else
                    {
                        PassBean pb=NewPB();
                        sendPassNum(pb,phonenumber);
                        ActionContext.getContext().getSession().put("PassBean",pb);
                        writer.print("验证码获取成功");
                        writer.flush();
                        writer.close();
                    }
                    return null;
                }
                else
                {
                    if(!Golbal.isPhonenumber(phonenumber))
                    {
                        writer.print("无法发送验证码到该手机");
                        writer.flush();
                        writer.close();
//                        return GetPassNumError;
                        return null;

                    }
                    PassBean pb=(PassBean)ActionContext.getContext().getSession().get("PassBean");
                    if(pb.isOverTime())
                    {
                        pb=NewPB();
                        sendPassNum(pb,phonenumber);
                        ActionContext.getContext().getSession().put("PassBean",pb);
                        writer.print("验证码获取成功");
                        writer.flush();
                        writer.close();
                        return null;
                    }
                    else
                    {
                        writer.print("验证码获取失败");
                        writer.flush();
                        writer.close();
                        return null;
//                        return GetPassNumError;
                    }
                }
            case "UsePassNum":
                if(invitedao.getNewsByID(invitenum)==null)
                {
                    writer.print("该邀请码无效");
                    writer.flush();
                    writer.close();
                    return null;
                }
                if(!Golbal.isUserName(username)||!Golbal.isUserName(password))
                {
                    writer.print("账号或密码格式错误!");
                    writer.flush();
                    writer.close();
                    return null;
                }
                if(!Golbal.isName(name))
                {
                    writer.print("昵称格式错误!");
                    writer.flush();
                    writer.close();
                    return null;
                }
                if(ActionContext.getContext().getSession().get("PassBean")==null)
                {
                    writer.print("验证码验证失败");
                    writer.flush();
                    writer.close();
                    return null;
                }
                else
                {
                    PassBean pb=(PassBean)ActionContext.getContext().getSession().get("PassBean");
                    if(!pb.getPhonenumber().equals(phonenumber))
                    {
                        writer.print("验证码验证失败");
                        writer.flush();
                        writer.close();
                        return null;
                    }
                    if(pb.isOverTime())
                    {
                        writer.print("验证码已过期");
                        writer.flush();
                        writer.close();
                        return null;
                    }
                    else
                    {
                        if(pb.getPassNum().equals(passnum))
                        {
                            if(commonuserdao.getNewsByUsername(username)!=null)
                            {
                                writer.print("用户名已存在");
                                writer.flush();
                                writer.close();
                            }
                            else if (commonuserdao.CreateAcount(name,username,password,phonenumber))
//                                    &&invitedao.UseInviteNum(invitenum))
                            {
                                writer.print("注册成功");
                                writer.flush();
                                writer.close();
                            }
                            else
                            {
                                writer.print("注册失败");
                                writer.flush();
                                writer.close();
                            }
                            return null;
                        }
                        writer.print("验证码验证失败");
                        writer.flush();
                        writer.close();
                        return null;
                    }
                }
            case "isPhoneNumber":
                if(commonuserdao.getNewsByPhonenumber(phonenumber)!=null)
                {
                    writer.print("false1");
                    writer.flush();
                    writer.close();
                }
                else if(Golbal.isPhonenumber(phonenumber))
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                }
                    return null;
            case "isUserName":
                if(Golbal.isUserName(username))
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                }
                return null;
            case "isPassword":
                if(Golbal.isUserName(password))
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                }
                else {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                }
                return null;
            case "isName":
                if(Golbal.isName(name))
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                }
                else {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                }
                return null;
            case "isInviteNum":
                if(invitedao.getNewsByID(invitenum)!=null)
                {
                    writer.print("true");
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.print("false");
                    writer.flush();
                    writer.close();
                }
                return null;
                default:
                    return ERROR;
        }
    }
    public PassBean NewPB()
    {
        String passnum="";
        Random rd=new Random();
        passnum=""+(rd.nextInt(899999)+100000);
        System.out.println(passnum);
        return new PassBean(passnum,new Date(),phonenumber);
    }
    private boolean sendPassNum(PassBean passBean,String phonenumber)
    {
//        return true;
        MessageBean messageBean=new MessageBean();
        return messageBean.sendPass(passBean.getPassNum(),"1",phonenumber);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPassnum(String passnum) {
        this.passnum = passnum;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public commonuserDAO getCommonuserdao() {
        return commonuserdao;
    }

    public void setCommonuserdao(commonuserDAO commonuserdao) {
        this.commonuserdao = commonuserdao;
    }

    public String getInvitenum() {
        return invitenum;
    }

    public void setInvitenum(String invitenum) {
        this.invitenum = invitenum;
    }

    public inviteDAO getInvitedao() {
        return invitedao;
    }

    public void setInvitedao(inviteDAO invitedao) {
        this.invitedao = invitedao;
    }
}
