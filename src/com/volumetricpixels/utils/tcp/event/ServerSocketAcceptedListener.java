package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for accepted
 * server sockets in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface ServerSocketAcceptedListener extends EventListener {
    public void socketAccepted(ServerSocketAcceptedEvent evt);
}
