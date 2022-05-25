package com.nfs.academy.thread;

public class PostMan extends Thread {

    public PostMan(Runnable target) {
        super(target);
    }
}
