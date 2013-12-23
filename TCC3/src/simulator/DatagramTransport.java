/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Diego Muller
 */
public abstract class DatagramTransport implements Serializable, Cloneable {

    /**
     * Revived packet
     */
    public static final int RECIVED = 1;
    /**
     * Sent packet
     */
    public static final int SENT = 2;
    private int sourcePort;
    private int destinationPort;
    private Object data;
    private boolean connects = false;
    private boolean conenectionState = false;
    private boolean enableNextTab = true;

    /**
     * Return the protocol name
     *
     * @return the protocol name
     */
    public abstract String datagramTransportType();

    /**
     * Return an array with the name of this class parents protocols
     *
     * @return an array of parents protocols
     */
    public abstract String[] getDatagramsParent();

    /**
     * Returns an DatagramTransport object that represents a response to the
     * current datagram.
     *
     * @return a response to the current datagram
     */
    public abstract DatagramTransport getAnswer();

    /**
     * Returns an DatagramApplication ArrayList that represents a automatic
     * response to the current datagram.
     *
     * @param lastStateName the name of the last state
     * @return a DatagramApplication ArrayList with the answer to the current
     * datagram
     */
    public abstract ArrayList<DatagramTransport> getAutomaticAnswer(String lastStateName);

    /**
     * Decapsule the Application Layer Data
     *
     * @return a DatagramApplication that represents the contents of the
     * Application Layer
     */
    public DatagramApplication decapsulateData() {

        if (this.getData() instanceof DatagramApplication) {
            return (DatagramApplication) this.getData();
        } else {
            return null;
        }
    }

    /**
     * Encapsule the Application Layer Data
     *
     * @param la a DatagramApplication that represents the contents of the
     * Application Layer
     */
    public void encapsulateData(DatagramApplication la) {

        this.data = la;
    }

    /**
     * Returns the source port number
     *
     * @return the source port number
     */
    public int getSourcePort() {
        return sourcePort;
    }

    /**
     * Sets the source port number
     *
     * @param sourcePort the source port number
     */
    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    /**
     * Returns the destination port number
     *
     * @return the destination port number
     */
    public int getDestinationPort() {
        return destinationPort;
    }

    /**
     * Sets the destination port number
     *
     * @param destinationPort the destination port number
     */
    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    /**
     * Returns the Transport Layer data field value
     *
     * @return the Transport Layer data field value
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the Transport Layer data field value
     *
     * @param data the Transport Layer data field value
     */
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return datagramTransportType();
    }

    /**
     * Returns true if the protocol establishes a connection
     *
     * @return true if the protocol establishes a connection or false if didn't
     */
    public boolean establishesConnection() {
        return connects;
    }

    /**
     * Sets if the protocol establishes a connection
     *
     * @param connects true if the protocol establishes a connection or false if
     * didn't
     */
    public void setConnects(boolean connects) {
        this.connects = connects;
    }

    /**
     * Returns true if the protocol have a connection states
     *
     * @return true if the protocol have a connections states or false if
     * haven't
     */
    public boolean haveConnectionStates() {
        return conenectionState;
    }

    /**
     * Sets if the protocol have a connections states
     *
     * @param conenectionState true if the protocol have a connections states or
     * false if haven't
     */
    public void setConnectionStates(boolean conenectionState) {
        this.conenectionState = conenectionState;
    }

    /**
     * Returns the conection state number
     *
     * @return
     */
    public int getState() {
        return 0;
    }

    /**
     * Returns the next conection state number
     *
     * @return
     */
    public int getNextState() {
        return 0;
    }

    /**
     * Returns the conection state name
     *
     * @return
     */
    public String getStateName() {
        return "";
    }

    /**
     * Returns the name of the next state of the protocol, returns null if the
     * package is invalid
     *
     * @param lastStateName the last state of the protocol
     * @param packetType 1 - if the packet is received; 2 if the package is sent
     * @param lastReciveDatagram a DatagramTransport representing the last
     * packet sent
     *
     * @return null if invalid package or the name of the next state
     */
    public String getNextStateName(String lastStateName, int packetType, DatagramTransport lastReciveDatagram) {
        return "";
    }

    /**
     * Returns if is possible change the connection port
     *
     * @param lastState the name of the last state
     * @return if is possible change the conection port
     */
    public boolean allowChangePort(String lastState) {
        return true;
    }

    /**
     * Returns if it is the last state
     *
     * @param stateName the state name
     * @return if it is the last state
     */
    public boolean isLastState(String stateName) {
        return false;
    }

    /**
     * Enable the tabs of the children protocol layer
     */
    public void enableNextTab() {
        this.enableNextTab = true;
    }

    /**
     * Disable the tabs of the children protocol layer
     */
    public void disableNextTab() {
        this.enableNextTab = false;
    }

    /**
     * Returns if is enable the tabs of the children protocol layer
     *
     * @return the status of the tabs of the children protocol layer
     */
    public boolean isEnableNextPort() {
        return enableNextTab;
    }

    @Override
    public DatagramTransport clone() throws CloneNotSupportedException {
        DatagramTransport clone;
        clone = (DatagramTransport) super.clone();

        if (clone.data instanceof DatagramApplication) {
            clone.data = ((DatagramApplication) clone.data).clone();
        }

        return clone;
    }
}
