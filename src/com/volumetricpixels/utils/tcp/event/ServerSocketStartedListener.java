package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for started
 * server sockets in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface ServerSocketStartedListener extends EventListener {
    public void serverSocketStarted(ServerSocketStartedEvent evt);
}
