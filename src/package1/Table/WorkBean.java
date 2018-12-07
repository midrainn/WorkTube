package package1.Table;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity


@Table(name = "table1")
public class WorkBean {
    @Id
    @Column(name="ID")
    private String id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Url")
    private String url;
    @Column(name = "Writter")
    private String writter;
    @Column(name = "Like_num")
    private int like_num;
    @Column(name ="type")
    private String type;
    @Column(name = "writterID")
    private String writterid;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
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
