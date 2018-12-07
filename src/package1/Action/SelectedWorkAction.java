package package1.Action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import package1.DAO.DAOImp.WorkURLDAOImp1;
import package1.DAO.DAOImp.workDAOImp1;
import package1.DAO.DAOService.WorkURLDAO;
import package1.DAO.DAOService.workDAO;
import package1.Table.WorkBean;
import package1.Table.WorkURLBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectedWorkAction extends ActionSupport {
    private String act;
    private String WorkID;
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        System.out.println("aaa");
        switch (act)
        {
            case "GetWork":
                if(!GetWork())
                {
                    writer.println("Error");
                    writer.flush();
                    writer.close();
                    return null;
                }
                break;
            case "SetWork":
                ActionContext.getContext().getSession().put("WorkID",WorkID);
                workDAO workDAO=new workDAOImp1();
                WorkBean workBean=(WorkBean) workDAO.getWorkByID(WorkID);
                ActionContext.getContext().getSession().put("Pass",workBean);
                writer.print("Success");
                writer.flush();
                writer.close();
                break;

        }
        return null;
    }
    private boolean GetWork()
    {
        JSONObject jsonObject;
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(ActionContext.getContext().getSession().get("WorkID")==null)
        {
            return false;
        }
        else
        {

            WorkID=(String)ActionContext.getContext().getSession().get("WorkID");
            JSONArray jsonArray=new JSONArray();
            WorkURLDAO workURLDAO=new WorkURLDAOImp1();
            WorkURLBean workURLBean=(WorkURLBean)workURLDAO.getNewsByID(WorkID);

            if(workURLBean==null)return false;
            jsonObject=new JSONObject();
            jsonObject.put("name",workURLBean.getName());
            jsonObject.put("program",workURLBean.getProgram());
            jsonObject.put("Showimage1",workURLBean.getShowimage1());
            jsonObject.put("Showimage2",workURLBean.getShowimage2());
            jsonObject.put("Showimage3",workURLBean.getShowimage3());
            jsonObject.put("writter",workURLBean.getWritter());
            jsonObject.put("VideoSRC",workURLBean.getVideo());
            jsonObject.put("ProgramSRC",workURLBean.getProgram());
//            ActionContext.getContext().getSession().put("VideoSRC",workURLBean.getVideo());
//            ActionContext.getContext().getSession().put("ProgramSRC",workURLBean.getProgram());
            jsonArray.put(jsonObject);
            writer.println(jsonArray);
            writer.flush();
            writer.close();
            return true;
        }
    }
    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getWorkID() {
        return WorkID;
    }

    public void setWorkID(String workID) {
        WorkID = workID;
    }
}
