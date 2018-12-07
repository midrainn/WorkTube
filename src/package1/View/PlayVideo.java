package package1.View;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PlayVideo extends ActionSupport {
    private String VideoSRC;
    @Override
    public String execute() throws Exception {
        ActionContext.getContext().getSession().put("VidoSRC",VideoSRC);
        return SUCCESS;
    }

    public String getVideoSRC() {
        return VideoSRC;
    }

    public void setVideoSRC(String videoSRC) {
        VideoSRC = videoSRC;
    }
}
