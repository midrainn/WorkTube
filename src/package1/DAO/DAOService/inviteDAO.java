package package1.DAO.DAOService;

import package1.Table.inviteBean;

public interface inviteDAO extends baseDAO{
    public abstract boolean delNewsByObject(inviteBean invitebean);
    public abstract boolean CreateInviteNum();
    public abstract boolean UseInviteNum(String invitenum);
}
