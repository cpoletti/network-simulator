/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import simulator.DatagramTransport;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Diego Muller
 */
public class DatagramTransportUDP extends DatagramTransport implements Serializable, Cloneable {

    private int messageLength;
    private int checksum;

    /**
     *
     */
    public DatagramTransportUDP() {

        this.setSourcePort(7000);
        this.setDestinationPort(7100);
        this.messageLength = 2;
        this.checksum = 0;
    }

    /**
     * Return the protocol name (UDP)
     *
     * @return the protocol name
     */
    public String datagramTransportType() {
        return "UDP";
    }

    /**
     * Returns the value of the Message Length field
     *
     * @return the value of the Message Length field
     */
    public int getMessageLength() {
        return messageLength;
    }

    /**
     * Sets the value of the Message Length field
     *
     * @param messageLength the lenght of the UDP package
     */
    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    /**
     * Returns the value of the Checksum field
     *
     * @return the value of the Checksum field
     */
    public int getChecksum() {
        return checksum;
    }

    /**
     * Sets the value of the Checksum field
     *
     * @param checksum the UDP Checksum
     */
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    /**
     * Return an array with the name of this class parents protocols
     *
     * @return an array of parents protocols
     */
    public String[] getDatagramsParent() {
        return new String[]{"IP"};
    }

    /**
     * Returns an DatagramTransport object that represents a response to the
     * current datagram.
     *
     * @return a response to the current datagram
     */
    @Override
    public DatagramTransport getAnswer() {
        DatagramTransportUDP answer = new DatagramTransportUDP();
        answer.setSourcePort(this.getDestinationPort());
        answer.setDestinationPort(this.getSourcePort());
        return answer;
    }

    /**
     * Returns an DatagramTransport ArrayList that represents a automatics
     * responses to the current datagram.
     *
     * @param lastStateName the name of the last state
     * @return a DatagramTransport ArrayList with the answers to the current
     * datagram
     */
    @Override
    public ArrayList<DatagramTransport> getAutomaticAnswer(String lastStateName) {
        ArrayList<DatagramTransport> arrayList = new ArrayList<DatagramTransport>();
        return arrayList;
    }

    /**
     * Returns a copy of the object.
     *
     * @return a copy of the object.
     * @throws CloneNotSupportedException
     */
    @Override
    public DatagramTransportUDP clone() throws CloneNotSupportedException {
        DatagramTransportUDP clone;
        clone = (DatagramTransportUDP) super.clone();

        return clone;
    }
}
