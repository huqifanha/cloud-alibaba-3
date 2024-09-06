package com.example.order;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock(false);
//        lock.lock();
//        try {
//            lock.lockInterruptibly();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            lock.unlock();
//        }

//        AtomicInteger integer = new AtomicInteger(1);
//        integer.compareAndSet(1, 2);
//        System.out.println(integer);
//
//        integer.compareAndSet(1, 3);
//        System.out.println(integer);

        semaphoreTest();
    }

    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void semaphoreTest() {


        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data");
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        threadPool.shutdown();

    }


    static void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 start");
                try {
                    cyclicBarrier.await();
                    System.out.println("thread1 end");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 start");
                try {
                    cyclicBarrier.await();
                    System.out.println("thread2 end");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread3 start");
                try {
                    cyclicBarrier.await();
                    System.out.println("thread3 end");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }


}
