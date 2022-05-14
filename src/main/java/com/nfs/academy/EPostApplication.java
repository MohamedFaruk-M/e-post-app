package com.nfs.academy;

public class EPostApplication {

    public static void main(String[] args) {

// Sender SOP:
        // compose the message

        // encapsulate with envelope

        // submit to the post office

// Post officer SOP:
        // validate the message

        // generate the tracking id

        // assign it to the post man w.r.t region

        // return the ack receipt to the sender

// Post man SOP:
        // bundle the messages

        // navigate to the destination

        // attempt to reach the end receiver, then submit the message

        // upon success/failure attempt, notify the tracker

        // iterate the next message


// Tracker SOP:
        // listen the status and wait till delivered/returned

        // notify the status to the corresponding sender

    }

}
