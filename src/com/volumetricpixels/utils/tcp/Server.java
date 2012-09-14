package com.volumetricpixels.utils.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.volumetricpixels.utils.tcp.event.EmptyListener;
import com.volumetricpixels.utils.tcp.event.EventManager;
import com.volumetricpixels.utils.tcp.event.ServerSocketAcceptedEvent;
import com.volumetricpixels.utils.tcp.event.ServerSocketStartedEvent;
import com.volumetricpixels.utils.tcp.event.SocketHandlerReadyEvent;

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
    private EventManager em;

    public Server(int port) {
        this.port = port;
        this.em = new EventManager();
    }

    private void startListening() {
        try {
            server = new ServerSocket(port);
            em.onServerSocketStarted(new ServerSocketStartedEvent(this));
            while (true) {
                Socket sock = server.accept();
                final SocketHandler handler = new SocketHandler(this, sock, ++counter);

                em.registerListener(new EmptyListener() {
                            @Override
                            public void socketHandlerReady(SocketHandlerReadyEvent evt) {
                                em.onServerSocketAccepted(new ServerSocketAcceptedEvent(this, handler));
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

    public EventManager getEventManager() {
        return em;
    }

    @Override
    public void run() {
        startListening();
    }
}
