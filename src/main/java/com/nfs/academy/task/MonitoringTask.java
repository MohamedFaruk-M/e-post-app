package com.nfs.academy.task;

import java.util.concurrent.TimeUnit;

import com.nfs.academy.entity.Message;
import com.nfs.academy.exceptions.BusinessException;
import com.nfs.academy.interfaces.Notifiable;

/**
 * if the state {@link Message.Status}
 * transforms to any one of the value {DELIVERED, RETURNED},
 * then this task will trigger the notification to the corresponding sender
 */
public class MonitoringTask implements Runnable, Notifiable {

    private final Message message;

    private static final int MAX_ATTEMPT = 3;

    public MonitoringTask(Message message) {
        this.message = message;
    }

    // listen the status and wait till delivered/returned
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + ": Monitoring action kicked ..");
        synchronized (message) {
            System.out.println(Thread.currentThread().getName()
                    + ": listening the status ");
            int attemptCount = 0;
            while (!message.getStatus().notifyFlag()) {
                System.out.println(Thread.currentThread().getName()
                        + ": attempt count " + ++attemptCount);
                if (MAX_ATTEMPT == attemptCount)
                    throw new BusinessException("Unable to monitor further",
                            new BusinessException("Maximum attempt (" + MAX_ATTEMPT + ") reached"));
                try {
                    System.out.println(Thread.currentThread().getName()
                            + ": waiting along the path till the transition happened");
                    message.wait(TimeUnit.SECONDS.toMillis(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // notify the status to the corresponding sender
            System.out.println(Thread.currentThread().getName()
                    + ": resume the process and notifying the transition to the end user..");
            notify(message.getStatus());
        }

    }

    @Override
    public void notify(Message.Status status) {
        String statusReport = null;
        switch (status) {
            case DELIVERED:
                statusReport = "The given message has been successfully delivered";
                break;
            case RETURNED:
                statusReport = "The given message is unable to delivered hence returned backed";
                break;
        }
        System.out.println(Thread.currentThread().getName()
                + ": Notifying the status..\n" + statusReport);

    }

    // used to monitor the current status by the end user
    public String getCurrentStatus() {
        System.out.println(Thread.currentThread().getName()
                + ": get the current status against the given end user request");
        return message.getStatus().name();
    }

}
