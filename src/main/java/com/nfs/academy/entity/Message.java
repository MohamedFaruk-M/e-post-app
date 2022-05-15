package com.nfs.academy.entity;

import java.io.Serializable;

import com.nfs.academy.interfaces.Deliverable;

public class Message implements Deliverable, Serializable {

    private static final long serialVersionUID = 9127415459192408857L;

    // track the id for getting the present status
    private static int id = 0;

    private MessageEnvelope envelope;
    private String messageBody;

    private Status status;

    // instantiate done only via builder
    private Message() {
    }

    private Message(MessageEnvelope envelope, String messageBody) {
        this.envelope = envelope;
        this.messageBody = messageBody;
    }

    // invoke once the validation passed
    public static int generateId() {
        System.out.println(Thread.currentThread().getName()
                + ": generating the unique id upon validation passed");
        return ++id;
    }

    public MessageEnvelope getEnvelope() {
        return envelope;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Status getStatus() {
        return status;
    }

    // modifiable field
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * state to describe whether the sender should be notified over the transition
     */
    public enum Status {

        ARRIVED {
            @Override
            public boolean notifyFlag() {
                return false;
            }
        },
        REGISTERED {
            @Override
            public boolean notifyFlag() {
                return false;
            }
        },
        DISPATCHED {
            @Override
            public boolean notifyFlag() {
                return false;
            }
        },
        DELIVERED {
            @Override
            public boolean notifyFlag() {
                return true;
            }
        },
        RETURNED {
            @Override
            public boolean notifyFlag() {
                return true;
            }
        };

        // to derive the constant specific behavior
        public abstract boolean notifyFlag();
    }


    public static class Builder {

        private MessageEnvelope envelope;
        private String content;

        public Builder withEnvelope(MessageEnvelope envelope) {
            this.envelope = envelope;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            Message message = new Message(envelope, content);
            message.setStatus(Status.ARRIVED);
            return message;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("envelope=").append(envelope);
        sb.append(", messageBody='").append(messageBody).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
