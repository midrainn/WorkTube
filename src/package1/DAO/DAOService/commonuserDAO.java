package package1.DAO.DAOService;

public interface commonuserDAO extends baseDAO {
    public abstract Object getUserByUserNameAndPassword(String username, String password);
    public abstract boolean CreateAcount(String name, String username, String password, String phonenumbser);
    public abstract Object getNewsByUsername(String username);
    public abstract Object getNewsByPhonenumber(String phonenumber);
    public abstract String getNameByID(String userid);
}
