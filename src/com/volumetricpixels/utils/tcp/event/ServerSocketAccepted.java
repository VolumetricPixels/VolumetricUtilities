package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for ServerSocketAcceptedEvent and
 * ServerSocketAcceptedListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class ServerSocketAccepted {
    protected EventListenerList listenerList = new EventListenerList();

    public void addServerSocketAcceptedEventListener(ServerSocketAcceptedListener listener) {
        listenerList.add(ServerSocketAcceptedListener.class, listener);
    }

    public void removeServerSocketAcceptedEventListener(ServerSocketAcceptedListener listener) {
        listenerList.remove(ServerSocketAcceptedListener.class, listener);
    }

    public void executeEvent(ServerSocketAcceptedEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ServerSocketAcceptedListener.class) {
                ((ServerSocketAcceptedListener) listeners[i + 1]).socketAccepted(evt);
            }
        }
    }
}
