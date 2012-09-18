package com.volumetricpixels.utils.tcp.event;

import java.util.ArrayList;
import java.util.List;

public final class EventManager {
    private List<IListener> listeners = new ArrayList<IListener>();

    public void registerListener(IListener listener) {
        if (listener == null || listeners.contains(listener) == false) {
            listeners.add(listener);
        }
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        for (IListener l : listeners) {
            l.messageReceived(event);
        }
    }
    
    public void onServerSocketAccepted(ServerSocketAcceptedEvent event) {
        for (IListener l : listeners) {
            l.serverSocketAccepted(event);
        }
    }
    
    public void onServerSocketStarted(ServerSocketStartedEvent event) {
        for (IListener l : listeners) {
            l.serverSocketStarted(event);
        }
    }
    
    public void onSocketConnected(SocketConnectedEvent event) {
        for (IListener l : listeners) {
            l.socketConnected(event);
        }
    }
    
    public void onSocketDisconnected(SocketDisconnectedEvent event) {
        for (IListener l : listeners) {
            l.socketDisconnected(event);
        }
    }
    
    public void onSocketHandlerReady(SocketHandlerReadyEvent event) {
        for (IListener l : listeners) {
            l.socketHandlerReady(event);
        }
    }
}
