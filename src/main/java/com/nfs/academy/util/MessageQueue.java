package com.nfs.academy.util;

import java.util.ArrayDeque;
import java.util.Queue;

import com.nfs.academy.entity.Message;

public class MessageQueue {

    public static final Queue<Message> holder = new ArrayDeque<>();

    public static boolean submit(Message message) {
        synchronized (holder) {
            return holder.offer(message);
        }
    }

    public static Message consume() {
        synchronized (holder) {
            return holder.poll();
        }
    }

}
