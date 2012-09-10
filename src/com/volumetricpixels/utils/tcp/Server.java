package com.volumetricpixels.utils.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.volumetricpixels.utils.tcp.event.ServerSocketAccepted;
import com.volumetricpixels.utils.tcp.event.ServerSocketAcceptedEvent;
import com.volumetricpixels.utils.tcp.event.ServerSocketStarted;
import com.volumetricpixels.utils.tcp.event.ServerSocketStartedEvent;
import com.volumetricpixels.utils.tcp.event.SocketHandlerReadyEvent;
import com.volumetricpixels.utils.tcp.event.SocketHandlerReadyListener;

/**
 * Server for DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class Server extends Thread {
    private int port;
    private int counter = 0;

    private List<SocketHandler> handlers = new ArrayList<SocketHandler>();
    private ServerSocket server;
    private ServerSocketStarted started;
    private ServerSocketAccepted accepted;

    public Server(int port) {
        this.port = port;
        this.started = new ServerSocketStarted();
        this.accepted = new ServerSocketAccepted();
    }

    private void startListening() {
        try {
            server = new ServerSocket(port);
            started.executeEvent(new ServerSocketStartedEvent(this));
            while (true) {
                Socket sock = server.accept();
                final SocketHandler handler = new SocketHandler(sock, ++counter);

                handler.getReady().addSocketHandlerReadyEventListener(new SocketHandlerReadyListener() {
                            @Override
                            public void socketHandlerReady(SocketHandlerReadyEvent evt) {
                                accepted.executeEvent(new ServerSocketAcceptedEvent(this, handler));
                            }
                        });

                handler.start();
                handlers.add(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdownAll() {
        for (SocketHandler handler : handlers) {
            handler.disconnect();
        }
        handlers.clear();
    }

    public void stopServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketHandler[] getHandlers() {
        return handlers.toArray(new SocketHandler[handlers.size()]);
    }

    public SocketHandler getHandler(int index) {
        return handlers.get(index);
    }

    public ServerSocketStarted getServerSocketStarted() {
        return started;
    }

    public ServerSocketAccepted getSocketAccepted() {
        return accepted;
    }

    @Override
    public void run() {
        startListening();
    }
}
