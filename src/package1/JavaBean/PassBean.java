package package1.JavaBean;

import java.util.Date;

public class PassBean {
    private String passNum="";
    private Date date=null;
    private String phonenumber="";
    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isOverTime()
    {
        if(date==null)return true;
        else
        {
            Date nodate=new Date();
            long second=nodate.getTime()-date.getTime();
            second=second/1000;
            if(second>60)return true;
            else return false;
        }
    }
    public PassBean(String passNum,Date date,String phonenumber)
    {
        this.passNum=passNum;
        this.date=date;
        this.phonenumber=phonenumber;
    }
}
