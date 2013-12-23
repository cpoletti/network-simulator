/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that sends objects using sockets
 *
 * @author Diego Muller
 */
public class ConnectionSend {

    private int sendPort = 7000;
    private String ip;
    private Object sentObject;
    boolean errorSent = false;
    Socket clientSocket = null;

    /**
     *
     * @param ip the remote IP to set the object
     * @param sentObject the object to sent
     */
    public ConnectionSend(String ip, Object sentObject) {

        this.ip = ip;
        this.sentObject = sentObject;
    }

    /**
     *
     * @param ip the remote IP to set the object
     * @param sentObject the object to sent
     * @param sendPort the remote port to set the object
     */
    public ConnectionSend(String ip, Object sentObject, int sendPort) {

        this.ip = ip;
        this.sentObject = sentObject;
        this.sendPort = sendPort;
    }

    /**
     * Function that sends the object
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void start() throws UnknownHostException, IOException {

        clientSocket = new Socket(ip, sendPort);

        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

        outToServer.writeObject(sentObject);

        clientSocket.close();
        outToServer.close();
    }
}
