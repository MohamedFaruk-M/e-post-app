package com.nfs.academy.interfaces;

import com.nfs.academy.entity.Message;

/**
 * mixer interface to trigger the notification in the implementation layer (tracker)
 * w.r.t the given status of {@link com.nfs.academy.entity.Message.Status}
 */
public interface Notifiable {

    void notify(Message.Status status);

}
