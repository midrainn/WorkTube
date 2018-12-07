package package1.DAO.DAOService;

import package1.Table.NoPassWorkBean;

import java.util.List;

public interface nopassworkDAO extends baseDAO {
    public abstract boolean NoPassWork(String WorkID,String mark);
    public abstract List<NoPassWorkBean> getListByPageAndWritterID(int page, int maxresult, String writterID);
}
