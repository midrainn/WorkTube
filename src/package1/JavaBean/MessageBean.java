package package1.JavaBean;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import package1.Golbal.Golbal;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*这个类为短信验证码类，需要申请第三方短信接口*/
public class MessageBean {
    //SDK App ID
    private int appid=;
    //SDK App Key
    private String appkey="";
//    private String phoneNumber="";
    //模板ID
    private int templateid=;
    //签名内容 非ID
    private String smssign="作品展示系统注册验证码";
    public boolean sendPass(String PassNum,String PassTime,String phoneNumber)
    {
        if(!Golbal.isPhonenumber(phoneNumber))return false;
        String []params={PassNum,PassTime};
        SmsSingleSender ssender=new SmsSingleSender(appid,appkey);
        try {
            SmsSingleSenderResult result=ssender.sendWithParam("86",phoneNumber,templateid,params,smssign,"","");

        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
