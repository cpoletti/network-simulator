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
public abstract class DatagramInternet implements Serializable, Cloneable {

    private Object data;
    private boolean enableNextTab = true;

    /**
     *
     */
    public DatagramInternet() {
    }

    /**
     * Return the protocol name
     *
     * @return the protocol name
     */
    public abstract String datagramInternetType();

    /**
     * Return an array with the name of this class parents protocols
     *
     * @return an array of parents protocols
     */
    public abstract String[] getDatagramsParent();

    /**
     * Returns an DatagramInternet object that represents a response to the
     * current datagram.
     *
     * @return a response to the current datagram
     */
    public abstract DatagramInternet getAnswer();

    /**
     * Returns an DatagramApplication ArrayList that represents a automatic
     * response to the current datagram.
     *
     * @return a DatagramApplication ArrayList with the answer to the current
     * datagram
     */
    public abstract ArrayList<DatagramInternet> getAutomaticAnswer();

    /**
     * Decapsule the Transport Layer Data
     *
     * @return a DatagramTransport that represents the contents of the Transport
     * Layer
     */
    public DatagramTransport decapsulateData() {

        return (DatagramTransport) this.getData();
    }

    /**
     * Encapsule the Transport Layer Data
     *
     * @param lt a DatagramTransport that represents the contents of the
     * Transport Layer
     */
    public void encapsulateData(DatagramTransport lt) {

        this.data = lt;
    }

    /**
     * Returns the Internet Layer data field value
     *
     * @return the Internet Layer data field value
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the Internet Layer data field value
     *
     * @param data the Internet Layer data field value
     */
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return datagramInternetType();
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
    public DatagramInternet clone() throws CloneNotSupportedException {
        DatagramInternet clone;
        clone = (DatagramInternet) super.clone();

        return clone;
    }
}
