package package1.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import package1.DAO.DAOImp.nopassworkImp1;
import package1.DAO.DAOImp.waitpassworkImp1;
import package1.DAO.DAOImp.workDAOImp1;
import package1.DAO.DAOImp.worklogDAOImp1;
import package1.DAO.DAOService.nopassworkDAO;
import package1.DAO.DAOService.waitpassworkDAO;
import package1.DAO.DAOService.workDAO;
import package1.DAO.DAOService.worklogDAO;
import package1.Table.NoPassWorkBean;
import package1.Table.WorkBean;
import package1.Table.WorkLogBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class testAction extends ActionSupport {
    public final int EachPageMaxNum=16;
    private int pagenum=0;
    private String act="";
    @Override
    public String execute() throws Exception {
        switch (act)
        {
            case "getlog":getlog();
            break;
            default:
                return null;
        }
        return null;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    private String getlog()
    {
        int maxworknum=0;
        JSONObject jsonObject;
        String userid=(String)ServletActionContext.getContext().getSession().get("id");
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
//            e.printStackTrace();
            return ERROR;
        }
        if(pagenum<1)pagenum=1;
        pagenum--;
        pagenum*=EachPageMaxNum;
        JSONArray jsonArray=new JSONArray();
        worklogDAO worklogdao=new worklogDAOImp1();
        nopassworkDAO nopassworkdao=new nopassworkImp1();
        List<WorkLogBean> PassworkBeanList=worklogdao.getListByPageAndUserID(pagenum,EachPageMaxNum,userid);
        maxworknum=worklogdao.getMax_PageByUserID(userid);
        if(maxworknum%EachPageMaxNum==0)
        {
            maxworknum=maxworknum/EachPageMaxNum;
        }
        else
        {
            maxworknum=maxworknum/EachPageMaxNum+1;
        }
        for (WorkLogBean wkl:PassworkBeanList) {
            String mark="";
            if(wkl.getLog().equals("nopass"))
            {
                mark=((NoPassWorkBean)nopassworkdao.getNewsByID(wkl.getWorkid())).getMark();
            }
            jsonObject=new JSONObject();
            jsonObject.put("name",wkl.getWorkname());
            if(wkl.getLog().equals("wait"))
            {
                jsonObject.put("log","等待审核");
            }
            else if(wkl.getLog().equals("pass"))
            {
                jsonObject.put("log","审核通过");
            }
            else if(wkl.getLog().equals("nopass"))
            {
                jsonObject.put("log","审核未通过");
            }
            else
            {
                jsonObject.put("log","异常");
            }
            jsonObject.put("mark",mark);
            jsonArray.put(jsonObject);
        }
        jsonObject=new JSONObject();
        jsonObject.put("maxpagenum",maxworknum);
        jsonArray.put(jsonObject);
        writer.println(jsonArray);
        writer.flush();
        writer.close();
        return null;
    }
}
