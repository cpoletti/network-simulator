/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.Serializable;

/**
 *
 * @author Diego Muller
 */
public abstract class DatagramApplication implements Serializable, Cloneable {

    private Object data;
    private boolean enableNextTab = true;

    /**
     *  Return the protocol name
     * 
     * @return  the protocol name
     */
    public abstract String datagramApplicationType();

    /**
     * Return an array with the name of this class parents protocols
     * 
     * @return an array of parents protocols
     */
    public abstract String[] getDatagramsParent();

    /**
     * Returns an DatagramApplication object that represents a response to 
     * the current datagram.
     * 
     * @return a response to the current datagram
     */
    public abstract DatagramApplication getAnswer();

    /**
     * Returns an DatagramApplication Object that represents a automatic
     * response to the current datagram.
     *
     * @return a DatagramApplication Object with the answer to the current
     * datagram
     */
    public abstract DatagramApplication getAutomaticAnswer();

    /**
     * Returns the Application Layer data field value
     * 
     * @return the Application Layer data field value
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the Application Layer data field value
     * 
     * @param data the Application Layer data field value
     */
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return datagramApplicationType();
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
    public DatagramApplication clone() throws CloneNotSupportedException {
        DatagramApplication clone;
        clone = (DatagramApplication) super.clone();

        return clone;
    }
}
