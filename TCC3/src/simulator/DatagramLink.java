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
public abstract class DatagramLink implements Serializable, Cloneable {

    private Object data;
    private boolean enableNextTab;

    /**
     *
     */
    public DatagramLink() {
    }

    /**
     * Return the protocol name
     *
     * @return the protocol name
     */
    public abstract String datagramLinkType();

    /**
     * Returns an DatagramLink object that represents a response to the current
     * datagram.
     *
     * @return a response to the current datagram
     */
    public abstract DatagramLink getAnswer();

    /**
     * Returns an DatagramLink ArrayList that represents a automatic response to
     * the current datagram.
     *
     * @return a DatagramLink ArrayList with the answer to the current datagram
     */
    public abstract ArrayList<DatagramLink> getAutomaticAnswer();

    /**
     * Decapsule the Internet Layer Data
     *
     * @return a DatagramInternet that represents the contents of the Internet
     * Layer
     */
    public DatagramInternet decapsulateData() {

        return (DatagramInternet) this.getData();
    }

    /**
     * Encapsule the Internet Layer Data
     *
     * @param li a DatagramInternet that represents the contents of the
     * Transport Layer
     */
    public void encapsulateData(DatagramInternet li) {

        this.data = li;
    }

    /**
     * Returns the Link Layer data field value
     *
     * @return the Link Layer data field value
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the Link Layer data field value
     *
     * @param data the Link Layer data field value
     */
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return datagramLinkType();
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
    public DatagramLink clone() throws CloneNotSupportedException {
        DatagramLink clone;
        clone = (DatagramLink) super.clone();

        if (clone.data instanceof DatagramInternet) {
            clone.data = ((DatagramInternet) clone.data).clone();
        }

        return clone;
    }
}
