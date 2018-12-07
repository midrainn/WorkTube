package package1.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WaitPassWork")
public class WaitPassWorkBean {
    @Column(name = "id")
    @Id
    private String id;
    @Column(name = "type")
    private String type;
    @Column(name = "writterid")
    private String writterid;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWritterid() {
        return writterid;
    }

    public void setWritterid(String writterid) {
        this.writterid = writterid;
    }
}
