package com.volumetricpixels.utils.tcp.event;

/**
 * Listener for all events in the TCP socket library
 */
public interface IListener {
    public void messageReceived(MessageReceivedEvent evt);
    
    public void socketDisconnected(SocketDisconnectedEvent evt);
    
    public void socketConnected(SocketConnectedEvent evt);
    
    public void serverSocketStarted(ServerSocketStartedEvent evt);
    
    public void serverSocketAccepted(ServerSocketAcceptedEvent evt);
    
    public void socketHandlerReady(SocketHandlerReadyEvent evt);
}
