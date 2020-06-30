package com.example.demo;
/**
* 一个简单的死锁demo
 * 当DeathLock类的对象 flag== 1时(td1),先锁定 o1，睡眠500毫秒
*  而 td1 在睡眠的时候另一个 flag==0 的对象（td2）线程启动，先锁定 o2,睡眠 500 毫秒
*  td1 睡眠结束后需要锁定 o2 才能继续执行，而此时 o2 已被 td2 锁定；
*  td2 睡眠结束后需要锁定 o1 才能继续执行，而此时 o1 已被 td1 锁定；
 *  td1、td2 相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */
public class DeathLock implements Runnable{

    public int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag"+flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DeathLock td1 = new DeathLock();
        DeathLock td2 = new DeathLock();
        td1.flag = 1;
        td2.flag = 0;
        new Thread(td1).start();
        new Thread(td2).start();
    }
}
