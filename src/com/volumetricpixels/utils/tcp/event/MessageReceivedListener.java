package com.volumetricpixels.utils.tcp.event;

import java.util.EventListener;

/**
 * Interfaces for classes that listen for received
 * messages in DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public interface MessageReceivedListener extends EventListener {
    public void messageReceived(MessageReceivedEvent evt);
}
