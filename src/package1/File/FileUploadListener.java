package package1.File;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FileUploadListener implements ProgressListener {
    private HttpSession session;
    public FileUploadListener(HttpServletRequest request)
    {
        super();
        this.session=request.getSession();
    }
    @Override
    public void update(long uploadbyte, long filesizebyte, int i) {
        if (filesizebyte==-1)
        {
            System.out.println("上传完毕");
        }
        else
        {
            FileBean fileBean;
            if(session.getAttribute("filebean")==null)
            {
                fileBean=new FileBean();
                fileBean.setFileBean(uploadbyte,filesizebyte,i);
            }
            else
            {
                fileBean=(FileBean)session.getAttribute("filebean");
                fileBean.setFileBean(uploadbyte,filesizebyte,i);
            }
            ActionContext.getContext().getSession().put("filebean","aaaa");
            session.setAttribute("filebean",fileBean);
//            System.out.print(fileBean.getFileIndex()+":");
//            System.out.println(fileBean.getPercent()+"%");
        }
    }
}
