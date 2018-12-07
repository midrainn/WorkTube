package package1.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import package1.DAO.DAOService.workDAO;
import package1.Table.WorkBean;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class WorkAction extends ActionSupport {
    public static final String GETWORKLIST="getWorklist";
    private String act=null;
    private String page=null;
    private workDAO wdao;
    private int pagenum=0;
    private int maxpage=0;
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        PrintWriter writer=response.getWriter();
        act=(String)ActionContext.getContext().getSession().get("WorkAction");
        pagenum=(int)ActionContext.getContext().getSession().get("PageNum");
        switch (act)
        {
            //TODO
            case "getWorkByPage":
            maxpage=wdao.getMax_Page();
                List<WorkBean> wk=wdao.getListByPage(pagenum);
            ActionContext.getContext().getSession().put("WorkList",wdao.getListByPage(pagenum));
                writer.print("true");
                writer.flush();
                writer.close();
                return null;
            default:
                return ERROR;
        }
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public workDAO getWdao() {
        return wdao;
    }

    public void setWdao(workDAO wdao) {
        this.wdao = wdao;
    }
}
