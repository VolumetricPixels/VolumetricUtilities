package com.volumetricpixels.utils.tcp.event;

import javax.swing.event.EventListenerList;

/**
 * Handler for MessageReceivedEvent and
 * MessageReceivedListener for DziNeIT's TCP
 * socket library
 *
 * @author DziNeIT
 */
public class MessageReceived {
    protected EventListenerList listenerList = new EventListenerList();

    public void addMessageReceivedEventListener(MessageReceivedListener listener) {
        listenerList.add(MessageReceivedListener.class, listener);
    }

    public void removeMessageReceivedEventListener(MessageReceivedListener listener) {
        listenerList.remove(MessageReceivedListener.class, listener);
    }

    public void executeEvent(MessageReceivedEvent evt) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == MessageReceivedListener.class) {
                ((MessageReceivedListener) listeners[i + 1]).messageReceived(evt);
            }
        }
    }
}
