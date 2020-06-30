package com.example.demo;

public class Station extends Thread{

    public Station(String name) {
        super(name);//给线程名字赋值
    }

    //为了保持票数一致，设置一个静态的票数量
    static int num = 100;

    //创建一个静态钥匙
    static Object object = "aaa"; //任意值

    //重写run方法，实现买票操作

    @Override
    public void run() {
        while (num > 0) {
            synchronized (object) { //这个很重要，必须使用一把锁
                //进去的人必须要使用一把钥匙，出来之后要拿出来
                if (num >0) {
                    System.out.println(getName() + "卖出了第" + num + "张票");
                    --num;
                } else {
                    System.out.println("票买完了");
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
