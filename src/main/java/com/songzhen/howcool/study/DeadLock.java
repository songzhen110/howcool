package com.songzhen.howcool.study;

import java.util.HashMap;

class DemoSource implements Runnable{

    private String lockA;
    private String lockB;

    public DemoSource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+ "\t 当前持有" + lockA +"\t 尝试获取"+lockB);

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+ "\t 当前持有" + lockB +"\t 尝试获取"+lockA);
            }
        }

    }
}

public class DeadLock {



    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DemoSource(lockA,lockB),"AAA").start();
        new Thread(new DemoSource(lockB,lockA),"BBB").start();

    }
}
