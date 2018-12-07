package package1.DAO.DAOService;

import package1.DAO.DAOService.baseDAO;

public interface userDAO extends baseDAO {
    public abstract Object getUserByUserNameAndPassword(String username, String password);
}
