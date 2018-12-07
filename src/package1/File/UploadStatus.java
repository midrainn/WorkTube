package package1.File;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UploadStatus extends ActionSupport {
    private String act="";
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        PrintWriter writer=response.getWriter();
        FileBean fileBean=null;
        if(ActionContext.getContext().getSession().get("filebean")!=null)
        {
            fileBean=(FileBean)ActionContext.getContext().getSession().get("filebean");
        }
        switch (act)
        {
            case "getstatus":
                if(fileBean!=null)
                {
                    writer.print(fileBean.getPercent());
                    writer.flush();
                    writer.close();
                    return null;
                }
                else
                {
                    writer.print(0);
                    writer.flush();
                    writer.close();
                    return null;
                }

        }
        return null;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
}
