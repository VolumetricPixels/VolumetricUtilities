package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for SocketHandlerReaderEvent and
 * SocketHandlerReadyListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class SocketHandlerReady {
    protected EventListenerList listenerList = new EventListenerList();

    public void addSocketHandlerReadyEventListener(SocketHandlerReadyListener listener) {
        listenerList.add(SocketHandlerReadyListener.class, listener);
    }

    public void removeSocketHandlerReadyEventListener(SocketHandlerReadyListener listener) {
        listenerList.remove(SocketHandlerReadyListener.class, listener);
    }

    public void executeEvent(SocketHandlerReadyEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SocketHandlerReadyListener.class) {
                ((SocketHandlerReadyListener) listeners[i + 1]).socketHandlerReady(evt);
            }
        }
    }
}
