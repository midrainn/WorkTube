package package1.JavaBean;

import package1.Table.WorkBean;

public class likeBean {
    WorkBean wk;
    boolean liked=false;

    public WorkBean getWk() {
        return wk;
    }

    public void setWk(WorkBean wk) {
        this.wk = wk;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
