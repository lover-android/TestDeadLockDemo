package com.example.administrator.testdeadlockdemo;

/**
 * Created by administrator on 18-7-18.
 */

public class TestDeadLocked implements Runnable{

    A a = new A();
    B b = new B();

    public void init() throws InterruptedException{
        Thread.currentThread().setName("主线程");
        a.waitMethod(b);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        try {
            b.waitMethod(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args) throws InterruptedException{
        TestDeadLocked testDeadLocked = new TestDeadLocked();
        Thread thread = new Thread(testDeadLocked);
        thread.start();
        testDeadLocked.init();
    }*/

    public class A {
        public synchronized void waitMethod(B b) throws InterruptedException{
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：正在执行a的等待方法，持有a的对象锁");
            Thread.sleep(2000L);
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：试图调用b的死锁方法，尝试获取b的对象锁");
            b.deadLockMethod();
        }

        public synchronized void deadLockMethod(){
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：正在执行a的死锁方法，持有a的对象锁");
        }
    }

    public class B {
        public synchronized void waitMethod(A a) throws InterruptedException{
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：正在执行b的等待方法，持有b的对象锁");
            Thread.sleep(2000L);
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：试图调用a的死锁方法，尝试获取a的对象锁");
            a.deadLockMethod();
        }

        public synchronized void deadLockMethod(){
            System.out.println("ThreadId = "+android.os.Process.myTid() + ",  " + Thread.currentThread().getName() + "：正在执行B的死锁方法，持有B的对象锁");
        }
    }
}



