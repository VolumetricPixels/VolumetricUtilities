package com.volumetricpixels.utils.tcp.event;

/**
 * Abstract listener so people don't have to listen to
 * all events when they have a listener.
 *
 * @author DziNeIT
 */
public abstract class EmptyListener implements Listener {
    @Override
    public void messageReceived(MessageReceivedEvent evt) {
    }

    @Override
    public void socketDisconnected(SocketDisconnectedEvent evt) {
    }

    @Override
    public void socketConnected(SocketConnectedEvent evt) {
    }

    @Override
    public void serverSocketStarted(ServerSocketStartedEvent evt) {
    }

    @Override
    public void serverSocketAccepted(ServerSocketAcceptedEvent evt) {
    }

    @Override
    public void socketHandlerReady(SocketHandlerReadyEvent evt) {
    }
}
