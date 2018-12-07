package test;

public class testPX extends Thread {
    private int time;
    public testPX(int time)
    {
        this.time=time;
    }
    @Override
    public void run() {
        try {
            sleep(time/100);
            System.out.print("线程:"+time);
           testMain.result.add(""+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
