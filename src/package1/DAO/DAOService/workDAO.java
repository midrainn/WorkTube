package package1.DAO.DAOService;

import package1.Table.NoPassWorkBean;
import package1.Table.WaitPassWorkBean;
import package1.Table.WorkBean;

import java.util.List;

public interface workDAO{
    public abstract int getMax_Page();
    public abstract List<WorkBean> getListByPage(int page, int maxresult);
    public abstract List<WorkBean> getListByPageAndType(int page, int maxresult, String type);
    public abstract List<WorkBean> getListByPage(int page);
    public abstract List<WorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID);
    public abstract List<NoPassWorkBean> getNoPassListByWritterID(int page, int maxresult, String writterID);
    public abstract List<WaitPassWorkBean> getWaitPassListByWritterID(int page, int maxresult, String writterID);
    public  abstract  Object getWorkByID(String ID);
    public abstract int getMax_PageByType(String type);
    public abstract int getMax_PageByUserID(String userid);
    public abstract int getWaitWorkMax_PageByUserID(String userid);
    public abstract int getNoPassWorkMax_PageByUserID(String userid);
}
