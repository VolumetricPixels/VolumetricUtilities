package com.volumetricpixels.utils.tcp.event;

/**
 * Listener for ALL events in DziNeIT's TCP sockets library
 *
 * @author DziNeIT
 */
public interface Listener {
    public void messageReceived(MessageReceivedEvent evt);
    
    public void socketDisconnected(SocketDisconnectedEvent evt);
    
    public void socketConnected(SocketConnectedEvent evt);
    
    public void serverSocketStarted(ServerSocketStartedEvent evt);
    
    public void serverSocketAccepted(ServerSocketAcceptedEvent evt);
    
    public void socketHandlerReady(SocketHandlerReadyEvent evt);
}
