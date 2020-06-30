package com.example.demo;

public class MainClass {
    public static void main(String[] args) {

        Station station1=new Station("窗口1");
        Station station2=new Station("窗口2");
        Station station3=new Station("窗口3");
        Station station4=new Station("窗口4");


        station1.start();
        station2.start();
        station3.start();
        station4.start();
    }
}
