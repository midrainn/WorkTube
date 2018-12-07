package package1.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NoPassWork")
public class NoPassWorkBean {
    @Column(name = "id")
    @Id
    private String id;
    @Column(name = "mark")
    private String mark;
    @Column(name = "writterid")
    private String writterid;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getWritterid() {
        return writterid;
    }

    public void setWritterid(String writterid) {
        this.writterid = writterid;
    }
}
