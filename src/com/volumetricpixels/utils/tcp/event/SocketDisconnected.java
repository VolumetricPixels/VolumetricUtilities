package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for SocketDisconnectedEvent and
 * SocketDisconnectedListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class SocketDisconnected {
    protected EventListenerList listenerList = new EventListenerList();

    public void addSocketDisconnectedEventListener(SocketDisconnectedListener listener) {
        listenerList.add(SocketDisconnectedListener.class, listener);
    }

    public void removeSocketDisconnectedEventListener(SocketDisconnectedListener listener) {
        listenerList.remove(SocketDisconnectedListener.class, listener);
    }

    public void executeEvent(SocketDisconnectedEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SocketDisconnectedListener.class) {
                ((SocketDisconnectedListener) listeners[i + 1]).socketDisconnected(evt);
            }
        }
    }
}
