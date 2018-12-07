package package1.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import package1.DAO.DAOImp.*;
import package1.DAO.DAOService.commonuserDAO;
import package1.DAO.DAOService.nopassworkDAO;
import package1.DAO.DAOService.passworkDAO;
import package1.DAO.DAOService.waitpassworkDAO;
import package1.Table.CommonUser;
import package1.Table.WaitPassWorkBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UpdateWorkAction extends ActionSupport {
    public final int EachPageMaxNum=16;
    private int pagenum=0;
    private String act="";
    private String workid;
    private String mark;
    @Override
    public String execute() throws Exception {
        JSONObject jsonObject;
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
//            e.printStackTrace();
            return ERROR;
        }
        switch (act)
        {
            case "getwaitwork":getwaitwork();
            return null;
            case "PassWorkByID":
                if(PassworkByID(workid))
                {
                    writer.print("操作成功");
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.print("操作失败");
                    writer.flush();
                    writer.close();
                }
                return null;
            case "noPassWorkByID":
                if(NoPassworkByID(workid,mark))
                {
                    writer.print("操作成功");
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.print("操作失败");
                    writer.flush();
                    writer.close();
                }
                return null;
        }
        return null;
    }
    private boolean getwaitwork()
    {
        int maxworknum=0;
        JSONObject jsonObject;
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
//            e.printStackTrace();
            return false;
        }
        if(pagenum<1)pagenum=1;
        pagenum--;
        pagenum*=EachPageMaxNum;
        JSONArray jsonArray=new JSONArray();
        waitpassworkDAO waitpassworkdao=new waitpassworkImp1();
        commonuserDAO commonuserdao=new comuserDAOImp1();
        List<WaitPassWorkBean> waitPassWorkBeanList=waitpassworkdao.getListByPage(pagenum,EachPageMaxNum);
        maxworknum=waitpassworkdao.getMax_Page();
        if(maxworknum%EachPageMaxNum==0)
        {
            maxworknum=maxworknum/EachPageMaxNum;
        }
        else
        {
            maxworknum=maxworknum/EachPageMaxNum+1;
        }
        for (WaitPassWorkBean wpwk:waitPassWorkBeanList) {
            jsonObject=new JSONObject();
            jsonObject.put("id",wpwk.getId());
            jsonObject.put("writter",commonuserdao.getNameByID(wpwk.getWritterid()));
            jsonObject.put("workname",waitpassworkdao.getWorkNameByWorkID(wpwk.getId()));
            jsonObject.put("type",wpwk.getType());
            jsonArray.put(jsonObject);
        }
        jsonObject=new JSONObject();
        jsonObject.put("maxpagenum",maxworknum);
        jsonArray.put(jsonObject);
        writer.println(jsonArray);
        writer.flush();
        writer.close();
        return true;
    }
    private boolean PassworkByID(String workID)
    {
        if(workID==null)return false;
        passworkDAO passworkdao=new passworkImp1();
        return passworkdao.PassWork(workID);
    }
    private boolean NoPassworkByID(String workID,String mark)
    {
        if(workID==null)return false;
        nopassworkDAO nopassworkdao=new nopassworkImp1();
        if(mark==null)
        {
            return nopassworkdao.NoPassWork(workID,"暂无备注");
        }
        else
        {
            return nopassworkdao.NoPassWork(workID,mark);
        }
    }
    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public int getEachPageMaxNum() {
        return EachPageMaxNum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
