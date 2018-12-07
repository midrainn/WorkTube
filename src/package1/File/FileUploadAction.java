package package1.File;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import package1.DAO.DAOImp.waitpassworkImp1;
import package1.DAO.DAOService.waitpassworkDAO;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

public class FileUploadAction extends ActionSupport {
    private String type;
    private String workname="未命名作品";
    private File faceimage;
    private String faceimageContentType;
    private String faceimageFileName;

    private File showimage1;
    private String showimage1ContentType;
    private String showimage1FileName;
    private File showimage2;
    private String showimage2ContentType;
    private String showimage2FileName;
    private File showimage3;
    private String showimage3ContentType;
    private String showimage3FileName;
    private File program;
    private String programContentType;
    private String programFileName;
    private File video;
    private String videoContentType;
    private String videoFileName;
    @Override
    public String execute() throws Exception {
        String userid="";
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        if(ServletActionContext.getContext().getSession().get("id")==null)
        {
            writer.print("获取用户信息失败");
            writer.flush();
            writer.close();
            return null;
        }
        else
        {
            userid=(String) ServletActionContext.getContext().getSession().get("id");
        }
        if(workname==null||workname.equals(""))
        {
           workname="未命名作品";
        }
//        String realpath=ServletActionContext.getServletContext().getRealPath("WorkFile");
        String Imagerealpath=ServletActionContext.getServletContext().getRealPath("WorkFile/Images");
        String Programrealpath=ServletActionContext.getServletContext().getRealPath("WorkFile/programs");
        String Videorealpath=ServletActionContext.getServletContext().getRealPath("WorkFile/videos");
        String faceimageUID=null,showimage1UID=null,showimage2UID=null,showimage3UID=null,programUID=null,videoUID=null;

        if(faceimage!=null)
        {
            faceimageUID=UUID.randomUUID().toString()+getFileTypeA(faceimageFileName);
            faceimage.renameTo(new File(new File(Imagerealpath), faceimageUID));
        }
        if(showimage1!=null)
        {
            showimage1UID=UUID.randomUUID().toString()+getFileTypeA(showimage1FileName);
            showimage1.renameTo(new File(new File(Imagerealpath), showimage1UID));
        }
        if(showimage2!=null)
        {
            showimage2UID=UUID.randomUUID().toString()+getFileTypeA(showimage2FileName);
            showimage2.renameTo(new File(new File(Imagerealpath), showimage2UID));
        }
        if(showimage3!=null)
        {
            showimage3UID=UUID.randomUUID().toString()+getFileTypeA(showimage3FileName);
            showimage3.renameTo(new File(new File(Imagerealpath), showimage3UID));
        }
        if(program!=null)
        {
            programUID=UUID.randomUUID().toString()+getFileTypeA(programFileName);
            program.renameTo(new File(new File(Programrealpath), programUID));
        }
        if(video!=null)
        {
            videoUID=UUID.randomUUID().toString()+getFileTypeA(videoFileName);
            video.renameTo(new File(new File(Videorealpath), videoUID));
        }
        waitpassworkDAO waitpassworkDAO=new waitpassworkImp1();
        if(waitpassworkDAO.AddWork(userid,workname,faceimageUID,showimage1UID,showimage2UID,showimage3UID
                ,videoUID,programUID,type))
        {
            writer.print("上传成功，请等待审核");
            writer.flush();
            writer.close();
            return null;
        }
        else
        {
            writer.print("上传失败");
            writer.flush();
            writer.close();
            return null;
        }
//        return null;
    }
    private String getFileTypeA(String FileName)
    {
        String filetype="";
        boolean flag=false;
        for (int i=(FileName.length()-1);i>=0;--i)
        {
            if(i==0)
            {
                return "ERROR";
            }
            if(FileName.charAt(i)=='.')
            {
                filetype='.'+filetype;
                return filetype;
            }
            else
            {
                filetype=FileName.charAt(i)+filetype;
            }
        }
        return "ERROR";
    }
    private boolean isImage(String imagename)
    {
        String[] supportimage={};
        for (String str:
             supportimage) {
            if(str.equals(imagename))return true;
        }
        return false;
    }
    private boolean isvideo(String videoname)
    {
        String[] supportvideo={};
        for (String str:
                supportvideo) {
            if(str.equals(videoname))return true;
        }
        return false;
    }
    private boolean isprogram(String programname)
    {
        String[] supportprogram = {};
        for (String str :
                supportprogram) {
            if (str.equals(programname)) return true;
        }
        return false;
    }
    private String getFileType()
    {
        return null;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFaceimage() {
        return faceimage;
    }

    public void setFaceimage(File faceimage) {
        this.faceimage = faceimage;
    }

    public String getFaceimageContentType() {
        return faceimageContentType;
    }

    public void setFaceimageContentType(String faceimageContentType) {
        this.faceimageContentType = faceimageContentType;
    }

    public String getFaceimageFileName() {
        return faceimageFileName;
    }

    public void setFaceimageFileName(String faceimageFileName) {
        this.faceimageFileName = faceimageFileName;
    }

    public File getShowimage1() {
        return showimage1;
    }

    public void setShowimage1(File showimage1) {
        this.showimage1 = showimage1;
    }

    public String getShowimage1ContentType() {
        return showimage1ContentType;
    }

    public void setShowimage1ContentType(String showimage1ContentType) {
        this.showimage1ContentType = showimage1ContentType;
    }

    public String getShowimage1FileName() {
        return showimage1FileName;
    }

    public void setShowimage1FileName(String showimage1FileName) {
        this.showimage1FileName = showimage1FileName;
    }

    public File getShowimage2() {
        return showimage2;
    }

    public void setShowimage2(File showimage2) {
        this.showimage2 = showimage2;
    }

    public String getShowimage2ContentType() {
        return showimage2ContentType;
    }

    public void setShowimage2ContentType(String showimage2ContentType) {
        this.showimage2ContentType = showimage2ContentType;
    }

    public String getShowimage2FileName() {
        return showimage2FileName;
    }

    public void setShowimage2FileName(String showimage2FileName) {
        this.showimage2FileName = showimage2FileName;
    }

    public File getShowimage3() {
        return showimage3;
    }

    public void setShowimage3(File showimage3) {
        this.showimage3 = showimage3;
    }

    public String getShowimage3ContentType() {
        return showimage3ContentType;
    }

    public void setShowimage3ContentType(String showimage3ContentType) {
        this.showimage3ContentType = showimage3ContentType;
    }

    public String getShowimage3FileName() {
        return showimage3FileName;
    }

    public void setShowimage3FileName(String showimage3FileName) {
        this.showimage3FileName = showimage3FileName;
    }

    public File getProgram() {
        return program;
    }

    public void setProgram(File program) {
        this.program = program;
    }

    public String getProgramContentType() {
        return programContentType;
    }

    public void setProgramContentType(String programContentType) {
        this.programContentType = programContentType;
    }

    public String getProgramFileName() {
        return programFileName;
    }

    public void setProgramFileName(String programFileName) {
        this.programFileName = programFileName;
    }

    public File getVideo() {
        return video;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }
}
