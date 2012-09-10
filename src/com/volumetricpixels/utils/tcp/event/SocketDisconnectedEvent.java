package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

@SuppressWarnings("serial")
/**
 * Event fired when a socket is disconnected in
 * DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class SocketDisconnectedEvent extends EventObject {
    private int id;

    public SocketDisconnectedEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
