package com.example.demo;

// 线程之间通信
public class MySignal {
    private boolean hasDataToProcess = false;

    public boolean getHasDataToProcess() {
        return hasDataToProcess;
    }

    public void setHasDataToProcess(boolean hasDataToProcess) {
        this.hasDataToProcess = hasDataToProcess;
    }

    public static void main(String[] args) {
        // 同一个对象
        final MySignal mySignal = new MySignal();
        //线程1 设置 hasDataToProcess 值为 true
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mySignal.setHasDataToProcess(true);
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   //等待线程 1 完成然后取值
                   t1.join();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               mySignal.getHasDataToProcess();
               System.out.println("ti 改变以后的值："+ mySignal.hasDataToProcess);
            }
        });
        t2.start();
    }
}
