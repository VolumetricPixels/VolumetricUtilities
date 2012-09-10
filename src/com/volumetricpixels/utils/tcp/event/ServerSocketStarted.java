package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for ServerSocketStartedEvent and
 * ServerSocketStartedListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class ServerSocketStarted {
    protected EventListenerList listenerList = new EventListenerList();

    public void addServerSocketStartedEventListener(ServerSocketStartedListener listener) {
        listenerList.add(ServerSocketStartedListener.class, listener);
    }

    public void removeServerSocketStartedEventListener(ServerSocketStartedListener listener) {
        listenerList.remove(ServerSocketStartedListener.class, listener);
    }

    public void executeEvent(ServerSocketStartedEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ServerSocketStartedListener.class) {
                ((ServerSocketStartedListener) listeners[i + 1]).serverSocketStarted(evt);
            }
        }
    }
}
