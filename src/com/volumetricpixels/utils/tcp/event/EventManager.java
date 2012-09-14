package com.volumetricpixels.utils.tcp.event;

import java.util.ArrayList;
import java.util.List;

public final class EventManager {
    private List<Listener> listeners = new ArrayList<Listener>();

    public void registerListener(Listener listener) {
        if (listener == null || listeners.contains(listener) == false) {
            listeners.add(listener);
        }
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        for (Listener l : listeners) {
            l.messageReceived(event);
        }
    }
    
    public void onServerSocketAccepted(ServerSocketAcceptedEvent event) {
        for (Listener l : listeners) {
            l.serverSocketAccepted(event);
        }
    }
    
    public void onServerSocketStarted(ServerSocketStartedEvent event) {
        for (Listener l : listeners) {
            l.serverSocketStarted(event);
        }
    }
    
    public void onSocketConnected(SocketConnectedEvent event) {
        for (Listener l : listeners) {
            l.socketConnected(event);
        }
    }
    
    public void onSocketDisconnected(SocketDisconnectedEvent event) {
        for (Listener l : listeners) {
            l.socketDisconnected(event);
        }
    }
    
    public void onSocketHandlerReady(SocketHandlerReadyEvent event) {
        for (Listener l : listeners) {
            l.socketHandlerReady(event);
        }
    }
}
