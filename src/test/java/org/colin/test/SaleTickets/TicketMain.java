package org.colin.test.SaleTickets;

/**
 * @Description: TicketMain
 * @ClassName: TicketMain
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/26 11:32
 * @Version: 1.1.0
 */
public class TicketMain {

    public static void main(String[] args) {
        SaleTickets runTicekt = new SaleTickets();//只定义了一个实例，这就只有一个Object mutex = new Object();即一个锁。
        Thread th1 = new Thread(runTicekt, "窗口1");//每个线程等其他线程释放该锁后，才能执行
        Thread th2 = new Thread(runTicekt, "窗口2");
        Thread th3 = new Thread(runTicekt, "窗口3");
        Thread th4 = new Thread(runTicekt, "窗口4");
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}
