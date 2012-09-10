package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

@SuppressWarnings("serial")
/**
 * Event fired when a message is received in
 * DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class MessageReceivedEvent extends EventObject {
    private String message;
    private int id;

    public MessageReceivedEvent(Object source, int id, String message) {
        super(source);
        this.message = message;
    }

    public int getID() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
