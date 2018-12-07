package package1.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkLog")
public class WorkLogBean {
    @Id
    @Column(name = "workid")
    private String workid;
    @Column(name = "log")
    private String log;
    @Column(name = "writterid")
    private String writterid;
    @Column(name = "name")
    private String workname;

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getWritterid() {
        return writterid;
    }

    public void setWritterid(String writterid) {
        this.writterid = writterid;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
