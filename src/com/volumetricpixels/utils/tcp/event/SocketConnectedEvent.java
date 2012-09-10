package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

import com.volumetricpixels.utils.tcp.SocketHandler;

@SuppressWarnings("serial")
/**
 * Event fired when a socket is connected in
 * DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class SocketConnectedEvent extends EventObject {
    private int id;
    private SocketHandler handler;

    public SocketConnectedEvent(Object source, SocketHandler handler, int id) {
        super(source);
        this.id = id;
        this.handler = handler;
    }

    public SocketHandler getHandler() {
        return handler;
    }

    public int getID() {
        return id;
    }
}
