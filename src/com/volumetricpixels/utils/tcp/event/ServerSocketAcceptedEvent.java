package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

import com.volumetricpixels.utils.tcp.SocketHandler;

@SuppressWarnings("serial")
/**
 * Event fired when a server socket is accepted
 * in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class ServerSocketAcceptedEvent extends EventObject {
    private SocketHandler handler;

    public ServerSocketAcceptedEvent(Object source, SocketHandler handler) {
        super(source);
        this.handler = handler;
    }

    public SocketHandler getHandler() {
        return handler;
    }
}
