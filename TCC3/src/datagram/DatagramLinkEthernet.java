/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import simulator.WorkStation;
import simulator.PacketSend;
import simulator.Config;
import simulator.DatagramLink;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class DatagramLinkEthernet extends DatagramLink implements Serializable, Cloneable {

    private String preamble;
    private String sfd;
    private String destinationAddress;
    private String sourceAddress;
    private int type;
    private int checksum;

    /**
     *
     */
    public DatagramLinkEthernet() {
        WorkStation destination = PacketSend.getDestinationWorkStation();
        Config conf = Config.getInstance();

        this.preamble = "10101010\n10101010\n10101010\n10101010\n10101010\n10101010\n10101010";
        this.sfd = "10101011";
        this.sourceAddress = conf.getMac();
        if (destination != null) {
            this.destinationAddress = destination.getMAC();
        }
        this.type = 50;
        this.checksum = 0;
    }

    /**
     * Return the protocol name (Ethernet)
     *
     * @return the protocol name
     */
    public String datagramLinkType() {
        return "Ethernet";
    }

    /**
     * Returns the value of the Preamble field
     *
     * @return the value of the Preamble field
     */
    public String getPreamble() {
        return preamble;
    }

    /**
     * Sets the value of the Preamble field
     *
     * @param preamble the value of the preamble of the frame
     */
    public void setPreamble(String preamble) {
        this.preamble = preamble;
    }

    /**
     * Returns the value of the Preamble Start Frame Delimiter field
     *
     * @return the value of the Start Frame Delimiter field
     */
    public String getSFD() {
        return sfd;
    }

    /**
     * Sets the value of the Start Frame Delimiter field
     *
     * @param sfd the value of the Start Frame Delimiter
     */
    public void setSFD(String sfd) {
        this.sfd = sfd;
    }

    /**
     * Returns the value of the Destination Address field
     *
     * @return the value of the Destination Address field
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Sets the value of the Destination Address field
     *
     * @param destinationAddress the destination MAC address
     */
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * Returns the value of the Source Address field
     *
     * @return the value of the Source Address field
     */
    public String getSourceAddress() {
        return sourceAddress;
    }

    /**
     * Sets the value of the Source Address field
     *
     * @param sourceAddress the source MAC address
     */
    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * Returns the value of the Type field
     *
     * @return the value of the campo Type field
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the value of the Type field
     *
     * @param type the kind of information transmitted
     */
    public void setType(int type) {
        this.type = type;
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
     * @param checksum the Ethernet frame checksum
     */
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    /**
     * Returns an DatagramLink object that represents a response to 
     * the current datagram.
     *
     * @return a response to the current datagram
     */
    @Override
    public DatagramLink getAnswer() {
        DatagramLinkEthernet ansewer = new DatagramLinkEthernet();
        ansewer.setSourceAddress(this.destinationAddress);
        ansewer.setDestinationAddress(this.sourceAddress);
        return ansewer;
    }

    /**
     * Returns an DatagramLink ArrayList that represents a automatics 
     * responses to the current datagram.
     *
     * @return a DatagramLink ArrayList with the answers to the current 
     */
    @Override
    public ArrayList<DatagramLink> getAutomaticAnswer() {
        ArrayList<DatagramLink> arrayList = new ArrayList<DatagramLink>();
        arrayList.add(getAnswer());
        return arrayList;
    }

    /**
     * Retorna a copy of the object.
     *
     * @return a copy of the object.
     * @throws CloneNotSupportedException
     */
    @Override
    public DatagramLinkEthernet clone() throws CloneNotSupportedException {
        DatagramLinkEthernet clone;
        clone = (DatagramLinkEthernet) super.clone();

        return clone;
    }
}
