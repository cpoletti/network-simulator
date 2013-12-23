package simulator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author user
 */
public class MultiCastClient {

    private MulticastSocket socket;
    private InetAddress group;
    private byte data[] = new byte[300];

    /**
     *
     * @throws IOException
     */
    public MultiCastClient() throws IOException {
        group = InetAddress.getByName("228.5.6.7");
        socket = new MulticastSocket(9000);
        socket.joinGroup(group);
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
        socket.receive(p);
        return p;
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
        DatagramPacket packet = new DatagramPacket(sentData, packetNumber, sentData.length, group, 9000);
        socket.send(packet);
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
        socket.send(packet);
    }

    /**
     * Closes multicast connection
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.leaveGroup(group);
        socket.disconnect();
    }

    /**
     * Return the host IP address
     *
     * @return the host IP address
     */
    public String getAddress() {
        return socket.getLocalAddress().getHostAddress();
    }
}