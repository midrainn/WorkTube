package package1.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import package1.DAO.DAOImp.workDAOImp1;
import package1.DAO.DAOService.workDAO;
import package1.Table.WorkBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class MyWorkAction extends ActionSupport {
    public final int EachPageMaxNum=16;
    private int pagenum=0;
    private String act="";
    @Override
    public String execute() throws Exception {
        switch (act)
        {
            case "getmywork":getMyWork();
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

    private String getMyWork()
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
        workDAO workDAO=new workDAOImp1();
        List<WorkBean> workBeanList=workDAO.getListByPageAndWritterID(pagenum,EachPageMaxNum,userid);
        maxworknum=workDAO.getMax_PageByUserID(userid);
        if(maxworknum%EachPageMaxNum==0)
        {
            maxworknum=maxworknum/EachPageMaxNum;
        }
        else
        {
            maxworknum=maxworknum/EachPageMaxNum+1;
        }
        for (WorkBean wk:workBeanList) {
            jsonObject=new JSONObject();
            jsonObject.put("name",wk.getName());
            jsonObject.put("likenum",wk.getLike_num());
            jsonObject.put("url",wk.getUrl());
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
