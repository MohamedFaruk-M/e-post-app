package com.nfs.academy.entity;

import java.io.Serializable;

public class MessageEnvelope implements Serializable {

    private static final long serialVersionUID = 7501049001333345637L;

    // communication details
    private String fromAddress;
    private String toAddress;

    public MessageEnvelope(String fromAddress, String toAddress) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageEnvelope{");
        sb.append("fromAddress='").append(fromAddress).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
