package com.nfs.academy;

import static com.nfs.academy.entity.Message.Status.REGISTERED;
import static com.nfs.academy.util.GeoLocationUtil.Area.CHENNAI;
import static com.nfs.academy.util.GeoLocationUtil.Area.MUMBAI;

import com.nfs.academy.entity.Message;
import com.nfs.academy.entity.MessageEnvelope;
import com.nfs.academy.task.DeliveryTask;
import com.nfs.academy.task.MonitoringTask;
import com.nfs.academy.thread.PostMan;
import com.nfs.academy.thread.Tracker;
import com.nfs.academy.util.MessageQueue;
import com.nfs.academy.util.Validator;

public class EPostApplication {

    public static void main(String[] args) {

// Sender SOP:
        // compose the message
        String content = "Be happy and stay healthy while coding";
        // encapsulate with envelope
        Message messageFromSender = new Message.Builder()
                .withEnvelope(new MessageEnvelope(CHENNAI.name(),
                        MUMBAI.name()))
                .withContent(content).build();

        // submit to the post office
        System.out.println(Thread.currentThread().getName()
                + ": Sender successfully submit the " + messageFromSender + " into the post office");
        MessageQueue.submit(messageFromSender);
// Post officer SOP:
        // validate the message
        Message msgFromPO = MessageQueue.consume();
        Validator.validateMetaInfo(msgFromPO.getEnvelope());

        // generate the tracking id
        Message.generateId();

        // assign it to the post man w.r.t region

        // return the ack receipt to the sender
        System.out.println(Thread.currentThread().getName()
                + ": mark the status as " + REGISTERED);
        msgFromPO.setStatus(REGISTERED);
// Post man SOP:
        // bundle the work with worker and init the delivery action
        Runnable action = new DeliveryTask(msgFromPO);
        PostMan person = new PostMan(action);
        person.setName("Delivery-Person");


// Tracker SOP:
        // init tracking
        MonitoringTask system = new MonitoringTask(msgFromPO);
        Tracker monitor = new Tracker(new MonitoringTask(msgFromPO));
        monitor.setName("Monitoring-Person");

        person.start();
        monitor.start();

        System.out.println(Thread.currentThread().getName() + ": Current status of the given message"
                + msgFromPO + " is " + system.getCurrentStatus());
    }

}
