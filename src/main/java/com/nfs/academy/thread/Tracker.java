package com.nfs.academy.thread;

public class Tracker extends Thread{
    public Tracker(Runnable target) {
        super(target);
    }
}
