package package1.Action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.arch.Processor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.DAO.DAOImp.workDAOImp1;
import package1.Table.WorkBean;

import java.util.List;

public class indexAction extends ActionSupport {

    int MaxPageNum=-1;
    private String act1="";
    private int ChangePage=-1;
    private String programtype="all";
    @Override
    public String execute() throws Exception {
        int PageNum=0;
        if(programtype.equals("Csharp"))
        {
            programtype="C#";
        }
        if(programtype.equals("C/Cplus")) {
            ActionContext.getContext().getSession().put("ProgramType", "C/C++");
        }
        else {
            ActionContext.getContext().getSession().put("ProgramType",programtype);
        }
        if(ActionContext.getContext().getSession().get("PageNum")!=null)
        {
            PageNum=(Integer) ActionContext.getContext().getSession().get("PageNum");
        }
        //TODO
        ApplicationContext act= new ClassPathXmlApplicationContext("beans.xml");
        workDAOImp1 wdao=(workDAOImp1)act.getBean("workDao");
        if(MaxPageNum<=0)
        {
            if(programtype.equals("all"))
            {
                MaxPageNum=wdao.getMax_Page();
            }
            else
            {
                MaxPageNum=wdao.getMax_PageByType(programtype);
            }
            ActionContext.getContext().getSession().put("MaxPageNum",MaxPageNum);
        }
        else
        {
            ActionContext.getContext().getSession().put("MaxPageNum",MaxPageNum);
        }
        switch (act1)
        {
            case "NextPage":
                if(PageNum<MaxPageNum-9)
                {
                    PageNum+=9;

                }
                break;
            case "LastPage":
                if(PageNum>=9)
                {
                    PageNum-=9;
                }
                break;
        }
        if(ChangePage>=0)
        {
            PageNum=ChangePage*9;
        }
        ActionContext.getContext().getSession().put("PageNum",PageNum);
        List<WorkBean> workBeanList=null;
        if(programtype.equals("all"))
        {
            workBeanList=(List<WorkBean>)wdao.getListByPage(PageNum,9);
        }
        else
        {
            workBeanList=(List<WorkBean>)wdao.getListByPageAndType(PageNum,9,programtype);
        }
        if(MaxPageNum<0||workBeanList==null)
        {
            return ERROR;
        }
        else
        {
            ActionContext.getContext().getSession().put("WorkList",workBeanList);
            return SUCCESS;
        }
    }

    public String getAct1() {
        return act1;
    }

    public void setAct1(String act1) {
        this.act1 = act1;
    }

    public int getChangePage() {
        return ChangePage;
    }

    public void setChangePage(int changePage) {
        ChangePage = changePage;
    }

    public void setProgramtype(String programtype) {
        this.programtype = programtype;
    }
}
