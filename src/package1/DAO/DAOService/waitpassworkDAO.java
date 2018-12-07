package package1.DAO.DAOService;

import package1.Table.WaitPassWorkBean;

import java.util.List;

public interface waitpassworkDAO extends baseDAO{
    public abstract boolean AddWork(String writterID,String name,String faceimageUID,String showimage1UID,String showimage2UID,String showimage3UID
                                    ,String videoUID,String programUID,String type);
    public abstract List<WaitPassWorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID);
    public abstract List<WaitPassWorkBean> getListByPage(int page, int maxresult);
    public abstract int getMax_Page();
    public abstract String getWorkNameByWorkID(String workid);
}
