package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for ready
 * socket handlers in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface SocketHandlerReadyListener extends EventListener {
    public void socketHandlerReady(SocketHandlerReadyEvent evt);
}
