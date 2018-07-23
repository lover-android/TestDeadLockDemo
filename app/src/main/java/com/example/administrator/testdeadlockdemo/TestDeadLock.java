package com.example.administrator.testdeadlockdemo;

/**
 * Created by administrator on 18-7-17.
 */

public class TestDeadLock extends Thread{
    boolean b;
    DeadLock lock;
    public TestDeadLock(boolean b, DeadLock lock) {
        super();
        this.b = b;
        this.lock = lock;
    }
    /*public static void main(String[] args) {
        DeadLock lock = new DeadLock();
        TestDeadLock t1 = new TestDeadLock(true, lock);
        TestDeadLock t2 = new TestDeadLock(false, lock);
        t1.start();
        t2.start();
    }*/
    @Override
    public void run() {
        if(this.b){
            lock.m1();
        }else{
            lock.m2();
        }
    }
}

class DeadLock {
    Object o1 = new Object();
    Object o2 = new Object();

    void m1(){
        synchronized(o1){
            System.out.println("m1 Lock o1 first");
            synchronized(o2){
                System.out.println("m1 Lock o2 second");
            }
        }
    }
    void m2(){
        synchronized(o2){
            System.out.println("m2 Lock o2 first");
            synchronized(o1){
                System.out.println("m2 Lock o1 second");
            }
        }
    }
}
