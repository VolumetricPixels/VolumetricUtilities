package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for SocketConnectedEvent and
 * SocketConnectedListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class SocketConnected {
    protected EventListenerList listenerList = new EventListenerList();

    public void addSocketConnectedEventListener(SocketConnectedListener listener) {
        listenerList.add(SocketConnectedListener.class, listener);
    }

    public void removeSocketConnectedEventListener(SocketConnectedListener listener) {
        listenerList.remove(SocketConnectedListener.class, listener);
    }

    public void executeEvent(SocketConnectedEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SocketConnectedListener.class) {
                ((SocketConnectedListener) listeners[i + 1]).socketConnected(evt);
            }
        }
    }
}
