package com.example.demo;

public class Resource {

    private String name;
    private int count = 1;
    private boolean flag = false;

    private synchronized void set(String name) {
        while (flag) {
            try {
                //线程等待，消费者消费资源
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + "----" + count++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
        flag = true;
        // 唤醒等待中的消费者
        this.notifyAll();
    }

    public synchronized void out() {
        // 消费资源
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
        flag = false;
        this.notifyAll();
    }

    //生产者
    static class Producer implements Runnable {

        private Resource resource;

        Producer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
                resource.set("商品");
            }
        }
    }

    //消费者消费资源

    static class Comsumer implements Runnable {

        private Resource resource;

        Comsumer(Resource resource1) {
            this.resource = resource1;
        }
        @Override
        public void run() {
            resource.out();
        }
    }
}
