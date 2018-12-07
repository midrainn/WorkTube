package package1.File;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class FileBean {
    private long uploadByte; // 已经上传的字节数，单位：字节
    private long fileSizeByte; // 所有文件的总长度，单位：字节
    private int fileIndex; // 正在上传第几个文件
    private long startTime; // 开始上传的时间，用于计算上传速度等
    private int percent; // 上传百分比
    private long speed;
    private long time;
    private static final SimpleDateFormat SIMPLEFORMAT = new SimpleDateFormat("HH:mm:ss");

    public FileBean() {
        startTime = System.currentTimeMillis();
        percent = 0;
        speed=0L;
    }

    // 从State状态类中取得状态的字符串，用字符串的形式拼成XML文件内容
    public synchronized void setFileBean(long uploadByte, long fileSizeByte, int fileIndex) {
        this.uploadByte = uploadByte;
        this.fileSizeByte = fileSizeByte;
        this.fileIndex = fileIndex;
        this.speed=uploadByte-speed;
        this.time=(System.currentTimeMillis()-startTime)/1000;
        if ((Long.valueOf(uploadByte) * 100 / Long.valueOf(fileSizeByte) <= 100)) {
            // 生成当前上传进度的公式，加入判断条件的含义在于不需要重复计算
            percent = (int) (Long.valueOf(uploadByte) * 100 / Long.valueOf(fileSizeByte));
        }
    }

    public long getUploadByte() {
        return uploadByte;
    }

    public void setUploadByte(long uploadByte) {
        this.uploadByte = uploadByte;
    }

    public long getFileSizeByte() {
        return fileSizeByte;
    }

    public void setFileSizeByte(long fileSizeByte) {
        this.fileSizeByte = fileSizeByte;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
