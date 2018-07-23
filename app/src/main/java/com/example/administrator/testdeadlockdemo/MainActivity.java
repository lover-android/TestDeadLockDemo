package com.example.administrator.testdeadlockdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DeadLock lock = new DeadLock();
        TestDeadLock t1 = new TestDeadLock(true, lock);
        TestDeadLock t2 = new TestDeadLock(false, lock);
        t1.start();
        t2.start();*/

        Lock l = new Lock();
        l.isLocked = true;

        Lock.MyRunnable r1 = new Lock.MyRunnable(l, 0);
        Lock.MyRunnable r2 = new Lock.MyRunnable(l, 0);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

        //時而鎖住，時而釋放，因為另外兩條線程沒有有时捕捉不到isLocked = false
        for (int i = 0; i < 100; i++) {
            l.isLocked = false;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /*TestDeadLocked testDeadLocked = new TestDeadLocked();
        Thread thread = new Thread(testDeadLocked);
        thread.start();
        try {
            testDeadLocked.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
