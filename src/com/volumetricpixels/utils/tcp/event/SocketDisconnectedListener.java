package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for disconnected
 * sockets in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface SocketDisconnectedListener extends EventListener {
	public void socketDisconnected(SocketDisconnectedEvent evt);
}
