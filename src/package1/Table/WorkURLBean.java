package package1.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table2")
public class WorkURLBean {
    @Column(name = "id")
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "faceimage")
    private String faceimage;
    @Column(name = "showimage1")
    private String showimage1;
    @Column(name = "showimage2")
    private String showimage2;
    @Column(name = "showimage3")
    private String showimage3;
    @Column(name = "video")
    private String video;
    @Column(name = "program")
    private String program;
    @Column(name = "type")
    private String type;
    @Column(name="writter")
    private String writter;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaceimage() {
        return faceimage;
    }

    public void setFaceimage(String faceimage) {
        this.faceimage = faceimage;
    }

    public String getShowimage1() {
        return showimage1;
    }

    public void setShowimage1(String showimage1) {
        this.showimage1 = showimage1;
    }

    public String getShowimage2() {
        return showimage2;
    }

    public void setShowimage2(String showimage2) {
        this.showimage2 = showimage2;
    }

    public String getShowimage3() {
        return showimage3;
    }

    public void setShowimage3(String showimage3) {
        this.showimage3 = showimage3;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }
}
