    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author user
 */
public class MultiCastServer {

    MulticastSocket multi;
    InetAddress group;
    byte data[];

    /**
     * Class constructor
     *
     * @throws IOException
     */
    public MultiCastServer() throws IOException {
        group = InetAddress.getByName("228.5.6.7");
        multi = new MulticastSocket(9000);
        multi.joinGroup(group);
    }

    /**
     * Sends a maulticas string
     *
     * @param s the string to send
     * @throws IOException
     */
    public void sendMessage(String s) throws IOException {
        data = new byte[s.length()];
        data = s.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, group, 9000);
        multi.send(packet);
    }

    /**
     * Sends a multicast datagram
     *
     * @param sentData the data to send
     * @param packetNumber the packet offset
     *
     * @throws IOException
     */
    public void sendMessage(byte sentData[], int packetNumber) throws IOException {
        DatagramPacket packet = new DatagramPacket(sentData, 0, sentData.length, group, 9000);
        multi.send(packet);

    }

    /**
     * Receives a multicast datagram
     *
     * @return the multicast datagram recived
     * @throws IOException
     */
    public DatagramPacket receive() throws IOException {
        data = new byte[300];
        DatagramPacket p = new DatagramPacket(data, data.length);
        multi.receive(p);
        return p;
    }

    /**
     * Closes multicast connection
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        multi.leaveGroup(group);
        multi.disconnect();
        multi.close();
    }

    /**
     * Return the host IP address
     *
     * @return the host IP address
     * @throws SocketException
     */
    public String getAddress() throws SocketException {
        return multi.getLocalSocketAddress().toString();
    }
}
