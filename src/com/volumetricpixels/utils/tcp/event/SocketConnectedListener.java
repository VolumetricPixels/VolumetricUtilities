package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for connected
 * sockets in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface SocketConnectedListener extends EventListener {
    public void socketConnected(SocketConnectedEvent evt);
}
