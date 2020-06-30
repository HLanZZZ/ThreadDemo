package com.example.demo;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Resource r = new Resource();
        Resource.Producer producer = new Resource.Producer(r);
        Resource.Comsumer comsumer = new Resource.Comsumer(r);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(comsumer);

        t1.start();
        t2.start();
    }
}
