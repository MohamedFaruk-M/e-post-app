package com.nfs.academy.task;

import static com.nfs.academy.entity.Message.Status.DELIVERED;
import static com.nfs.academy.entity.Message.Status.RETURNED;

import com.nfs.academy.entity.Message;
import com.nfs.academy.util.GeoLocationUtil;

/**
 * To maintain the before-happens relation,
 * this task implements Runnable on phase 1
 * while on ph2 the Runnable will be replaced by Callable
 */
public class DeliveryTask implements Runnable {

    private final Message message;

    public DeliveryTask(Message message) {
        this.message = message;
    }


    @Override
    public void run() {
        // navigate to the destination
        System.out.println(Thread.currentThread().getName()
                + ": Delivery guy begun his duty ..");
        String code = null;
        try {
            // attempt to reach the end receiver, then submit the message
            System.out.println(Thread.currentThread().getName()
                    + ": Attempting to reach the end receiver ..");
            code = GeoLocationUtil.findDivision(
                    GeoLocationUtil.Area.valueOf(message.getEnvelope().getToAddress()));
            System.out.println(Thread.currentThread().getName()
                    + ": Routing the given message to the divisional post: " + code);
        } catch (IllegalArgumentException ex) {
            System.out.println(Thread.currentThread().getName()
                    + ": System couldn't able to route the message bcoz of " + ex.getMessage());
        }
        synchronized (message) {
            if (code == null)
                message.setStatus(RETURNED);
            else
                message.setStatus(DELIVERED);
            // upon success/failure attempt, notify the tracker
            System.out.println(Thread.currentThread().getName()
                    + ": notifying the tracker on the process");
            message.notify();
        }
    }

}
