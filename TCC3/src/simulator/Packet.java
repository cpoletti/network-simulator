/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.Serializable;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Cristiano
 */
public class Packet implements Serializable {

    /**
     * represents a unread packet
     */
    public static final int UNREAD = 1;
    /**
     * represents a read packet
     */
    public static final int READ = 2;
    /**
     * represents a dropped packet
     */
    public static final int DROPPED = 3;
    private URL urlUnread = getClass().getClassLoader().getResource("images/unread.png");
    private ImageIcon iconUnread = new ImageIcon(urlUnread);
    private URL urlRead = getClass().getClassLoader().getResource("images/read.png");
    private ImageIcon iconRead = new ImageIcon(urlRead);
    private URL urlDropped = getClass().getClassLoader().getResource("images/dropped.png");
    private ImageIcon iconDropped = new ImageIcon(urlDropped);
    private DatagramLink datagramLink;
    private DatagramApplication datagramApplication;
    private DatagramTransport datagramTransport;
    private DatagramInternet datagramInternet;
    private WorkStation sourceWorkStation;
    private WorkStation destinationWorkStation;
    private int status = 1;

    /**
     * Class contructor
     *
     * @param dl a link datagram
     * @param sourceWS the source workstation
     * @param destiinationWS the destination workstation
     */
    public Packet(DatagramLink dl, WorkStation sourceWS, WorkStation destiinationWS) {
        datagramLink = dl;
        sourceWorkStation = sourceWS;
        destinationWorkStation = destiinationWS;

        if (this.datagramLink != null) {
            this.datagramInternet = this.datagramLink.decapsulateData();
        }

        if (this.datagramInternet != null) {
            this.datagramTransport = this.datagramInternet.decapsulateData();
        }

        if (this.datagramTransport != null) {
            this.datagramApplication = this.datagramTransport.decapsulateData();
        }
    }

    /**
     * Returns the packet datagram link
     *
     * @return the packet datagram link
     */
    public DatagramLink getDatagramLink() {
        return datagramLink;
    }

    /**
     * Sets the packet datagram link
     *
     * @param datagram the packet datagram link
     */
    public void setDatagramLink(DatagramLink datagram) {
        this.datagramLink = datagram;
    }

    /**
     * Returns the packet source workstation
     *
     * @return the packet source workstation
     */
    public WorkStation getSourceWorkStation() {
        return sourceWorkStation;
    }

    /**
     * Sets the packet source workstation
     *
     * @param sourceWorkStation the packet source workstation
     */
    public void setSourceWorkStation(WorkStation sourceWorkStation) {
        this.sourceWorkStation = sourceWorkStation;
    }

    /**
     * Returns the status of the packet
     *
     * @return the status of the packet
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the packet
     *
     * @param status the status of the packet
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns an image icon that represents the status of the package
     *
     * @return an image icon
     */
    public ImageIcon getIcon() {
        switch (this.status) {
            case UNREAD:
                return iconUnread;
            case READ:
                return iconRead;
            case DROPPED:
                return iconDropped;
            default:
                return iconUnread;
        }
    }

    /**
     * Returns the packet destination workstation
     *
     * @return the packet destination workstation
     */
    public WorkStation getDestinationWorkStation() {
        return destinationWorkStation;
    }

    /**
     * Sets the packet source workstation
     *
     * @param destinationWorkStation the packet destination workstation
     */
    public void setDestinationWorkStation(WorkStation destinationWorkStation) {
        this.destinationWorkStation = destinationWorkStation;
    }

    /**
     * Returns the packet datagram application
     *
     * @return the packet datagram application
     */
    public DatagramApplication getDatagramApplication() {
        return datagramApplication;
    }

    /**
     * Sets the packet datagram application
     *
     * @param datagramApplication the packet datagram application
     */
    public void setDatagramApplication(DatagramApplication datagramApplication) {
        this.datagramApplication = datagramApplication;
    }

    /**
     * Returns the packet datagram transport
     *
     * @return the packet datagram transport
     */
    public DatagramTransport getDatagramTransport() {
        return datagramTransport;
    }

    /**
     * Sets the packet datagram transport
     *
     * @param datagramTransport the packet datagram transport
     */
    public void setDatagramTransport(DatagramTransport datagramTransport) {
        this.datagramTransport = datagramTransport;
    }

    /**
     * Returns the packet datagram internet
     *
     * @return the packet datagram internet
     */
    public DatagramInternet getDatagramInternet() {
        return datagramInternet;
    }

    /**
     * Sets the packet datagram internet
     *
     * @param datagramInternet the packet datagram internet
     */
    public void setDatagramInternet(DatagramInternet datagramInternet) {
        this.datagramInternet = datagramInternet;
    }
}
