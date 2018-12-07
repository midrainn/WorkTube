package package1.DAO.DAOService;

import package1.Table.WorkLogBean;

import java.util.List;

public interface worklogDAO extends baseDAO {
    public abstract boolean AddLog(String workid,String log,String writterid,String name);
    public abstract boolean UpdateLog(String workid,String log);
    public abstract List<WorkLogBean> getListByPageAndUserID(int page, int maxresult, String userid);
    public abstract int getMax_PageByUserID(String userid);
}
