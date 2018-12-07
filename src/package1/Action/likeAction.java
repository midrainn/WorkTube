package package1.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import package1.Table.WorkBean;

import java.util.List;

public class likeAction extends ActionSupport {
    private String id="-1";
    @Override
    public String execute() throws Exception {
        List<WorkBean> wklist=(List<WorkBean>)ActionContext.getContext().getSession().get("WorkList");
        if(id.equals("-1")||wklist==null)
        {
            return ERROR;
        }
        for (WorkBean wk:
             wklist) {
            if(wk.getId().equals(id))
            {
                int index=wklist.indexOf(wk);
                wk.setLike_num(wk.getLike_num()+1);
                wklist.set(index,wk);
                ActionContext.getContext().getSession().put("WorkList",wklist);
                break;
            }
        }
        return SUCCESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
