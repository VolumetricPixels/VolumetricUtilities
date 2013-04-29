package com.volumetricpixels.utils.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import com.volumetricpixels.utils.tcp.event.EventManager;
import com.volumetricpixels.utils.tcp.event.MessageReceivedEvent;
import com.volumetricpixels.utils.tcp.event.SocketConnectedEvent;
import com.volumetricpixels.utils.tcp.event.SocketDisconnectedEvent;
import com.volumetricpixels.utils.tcp.event.SocketHandlerReadyEvent;

/**
 * SocketHandler TCP socket library
 */
public class SocketHandler extends Thread {
    private Socket sock;
    private int bytesReceived = 0;
    private int messageSize = -1;
    private InputStream in;
    private OutputStream out;
    private EventManager em;
    
    private byte[] buffer = new byte[4];
    private String hostName;
    private int id;

    public SocketHandler(Server server) {
        this.em = server.getEventManager();
    }
    
    public SocketHandler(Client client) {
        this.em = client.getEventManager();
    }

    public SocketHandler(Server server, Socket sock, int id) {
        this(server);
        this.sock = sock;
        this.id = id;
    }
    
    public SocketHandler(Client client, Socket sock, int id) {
        this(client);
        this.sock = sock;
        this.id = id;
    }

    private void handleConnection() {
        if (sock == null) {
            disconnect();
            return;
        }
        try {
            this.hostName = sock.getInetAddress().getCanonicalHostName();
            in = sock.getInputStream();
            out = sock.getOutputStream();
            
            if (in == null || out == null) {
                disconnect();
                return;
            }

            em.onSocketHandlerReady(new SocketHandlerReadyEvent(this, this));
            em.onSocketConnected(new SocketConnectedEvent(this, this, id));
            startReading();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        if (sock.isConnected() && !sock.isClosed()) {
            writeToStream(message);
        }
    }

    private void startReading() {
        if (!sock.isConnected() || sock.isClosed()) {
            disconnect();
            return;
        }
        buffer = new byte[buffer.length - bytesReceived];
        try {
            if (bytesReceived == -1) {
                disconnect();
                return;
            }

            bytesReceived += in.read(buffer);

            if (messageSize == -1) {
                if (bytesReceived == 4) {
                    messageSize = ByteBuffer.wrap(buffer).getInt(0);
                    
                    if (messageSize < 0) {
                        throw new Exception();
                    }

                    buffer = new byte[messageSize];
                    bytesReceived = 0;
                }

                if (messageSize != 0) {
                    startReading();
                }
            } else {
                if (bytesReceived == messageSize) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(new String(buffer));
                    em.onMessageReceived(new MessageReceivedEvent(this, id, sb.toString()));

                    bytesReceived = 0;
                    messageSize = -1;
                    buffer = new byte[4];

                    startReading();
                } else {
                    startReading();
                }
            }
        } catch (Exception ignore) {
        }
    }

    private void writeToStream(String message) {
        if (!sock.isConnected() || sock.isClosed() || out == null) {
            return;
        }

        byte[] sizeinfo = new byte[4];
        byte[] data = message.getBytes();

        ByteBuffer bb = ByteBuffer.allocate(sizeinfo.length);
        bb.putInt(data.length);

        try {
            out.write(bb.array());
            out.write(data);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            System.out.println("Client disconnecting");
            sock.shutdownInput();
            sock.shutdownOutput();
            sock.close();
            em.onSocketDisconnected(new SocketDisconnectedEvent(this, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket sock) {
        this.sock = sock;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public EventManager getEventManager() {
        return em;
    }

    public Socket getSocket() {
        return sock;
    }

    public void run() {
        if (this.sock == null) {
            return;
        }

        handleConnection();
    }
}
