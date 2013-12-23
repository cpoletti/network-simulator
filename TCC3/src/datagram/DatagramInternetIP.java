/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import simulator.WorkStation;
import simulator.PacketSend;
import simulator.Config;
import simulator.DatagramInternet;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Diego Muller
 */
public class DatagramInternetIP extends DatagramInternet implements Serializable, Cloneable {

    private int version;
    private int headerLenght;
    private int serviceType;
    private int totalLenght;
    private int identification;
    private boolean df;
    private boolean mf;
    private int fragmentOffset;
    private int timeToLive;
    private int protocol;
    private int headerChecksum;
    /**
     * Source IP address
     */
    protected String sourceIPAddress;
    /**
     * Destination IP address
     */
    protected String destinationIPAddress;
    private int options;

    /**
     *
     */
    public DatagramInternetIP() {
        WorkStation destination = PacketSend.getDestinationWorkStation();
        Config conf = Config.getInstance();

        this.version = 4;
        this.headerLenght = 5;
        this.serviceType = 255;
        this.totalLenght = 65535;
        this.identification = 65535;
        this.df = true;
        this.mf = true;
        this.fragmentOffset = 8191;
        this.timeToLive = 255;
        this.protocol = 4;
        this.headerChecksum = 65535;

        this.sourceIPAddress = conf.getIp();
        if (destination != null) {
            this.destinationIPAddress = destination.getIP();
        }
    }

    /**
     * Return the protocol name (ICMP)
     *
     * @return the protocol name
     */
    public String datagramInternetType() {
        return "IP";
    }

    /**
     * Returns the value of the Version field
     *
     * @return the value of the Version field
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the Version field
     *
     * @param version version of the IP protocol
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Returns the value of the Header Length field
     *
     * @return the value of the Header Length field
     */
    public int getHeaderLenght() {
        return headerLenght;
    }

    /**
     * Sets the value of the Header Length field
     *
     * @param headerLenght the length of the IP header
     */
    public void setHeaderLenght(int headerLenght) {
        this.headerLenght = headerLenght;
    }

    /**
     * Returns the value of the Service Type field
     *
     * @return the value of the Service Type field
     */
    public int getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the Service Type field
     *
     * @param serviceType the type of service IP
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Returns the value of the Total Length field
     *
     * @return the value of the Total Length field
     */
    public int getTotalLenght() {
        return totalLenght;
    }

    /**
     * Sets the value of the Total Length field
     *
     * @param totalLenght the total size of header + data
     */
    public void setTotalLenght(int totalLenght) {
        this.totalLenght = totalLenght;
    }

    /**
     * Returns the value of the Identification field
     *
     * @return the value of the Identification field
     */
    public int getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the Identification field
     *
     * @param identification the identification of the original datagram
     */
    public void setIdentification(int identification) {
        this.identification = identification;
    }

    /**
     * Returns the value of the Don't Fragment field
     *
     * @return the value of the Don't Fragment field
     */
    public boolean isDF() {
        return df;
    }

    /**
     * Sets the value of the Don't Fragment field
     *
     * @param df the value of the Don't Fragment field
     */
    public void setDF(boolean df) {
        this.df = df;
    }

    /**
     * Returns the value of the More Fragments field
     *
     * @return the the value of the More Fragments field
     */
    public boolean isMF() {
        return mf;
    }

    /**
     * Sets the value of the More Fragments field
     *
     * @param mf the value of the More Fragments field
     */
    public void setMF(boolean mf) {
        this.mf = mf;
    }

    /**
     * Returns the value of the Fragment Offset field
     *
     * @return the the value of the Fragment Offset field
     */
    public int getFragmentOffset() {
        return fragmentOffset;
    }

    /**
     * Sets the value of the Fragment Offset field
     *
     * @param fragmentOffset the starting position of the current fragment
     */
    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    /**
     * Returns the value of the Time to Live field
     *
     * @return the value of the Time to Live field
     */
    public int getTimeToLive() {
        return timeToLive;
    }

    /**
     * Sets the value of the Time to Live field
     *
     * @param timeToLive the Time to Live of the package
     */
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * Returns the value of the Protocol field
     *
     * @return the value of the Protocol field
     */
    public int getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the Protocol field
     *
     * @param protocol the code of the transport protocol layer to deliver 
     * the package
     */
    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    /**
     * Returns the value of the Header Checksum field
     *
     * @return the value of the Header Checksum
     */
    public int getHeaderChecksum() {
        return headerChecksum;
    }

    /**
     * Sets the value of the Header Checksum field
     *
     * @param headerChecksum the Header Checksum
     */
    public void setHeaderChecksum(int headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    /**
     * Returns the value of the Source Address field
     *
     * @return the value of the Source Address field
     */
    public String getSourceIPAddress() {
        return sourceIPAddress;
    }

    /**
     * Sets the value of the Source Address field
     *
     * @param sourceIPAddress the source IP address
     */
    public void setSourceIPAddress(String sourceIPAddress) {
        this.sourceIPAddress = sourceIPAddress;
    }

    /**
     * Returns the value of the Destination Address field
     *
     * @return the value of the Destination Address field
     */
    public String getDestinationIPAddress() {
        return destinationIPAddress;
    }

    /**
     * Sets the value of the Destination Address field
     *
     * @param destinationIPAddress the destination IP address
     */
    public void setDestinationIPAddress(String destinationIPAddress) {
        this.destinationIPAddress = destinationIPAddress;
    }

    /**
     * Returns the value of the Options field
     *
     * @return the value of the Options field
     */
    public int getOptions() {
        return options;
    }

    /**
     * Sets the value of the Options field
     *
     * @param options the additional options package
     */
    public void setOptions(int options) {
        this.options = options;
    }

    /**
     * Return an array with the name of this class parents protocols
     *
     * @return an array of parents protocols
     */
    @Override
    public String[] getDatagramsParent() {
        return new String[]{"Ethernet"};
    }

    /**
     * Returns an DatagramInternet object that represents a response to 
     * the current datagram.
     *
     * @return a response to the current datagram
     */
    @Override
    public DatagramInternet getAnswer() {
        DatagramInternetIP answer = new DatagramInternetIP();
        answer.setSourceIPAddress(this.destinationIPAddress);
        answer.setDestinationIPAddress(this.sourceIPAddress);
        return answer;

    }

    /**
     * Returns an DatagramApplication ArrayList that represents a automatics 
     * responses to the current datagram.
     *
     * @return a DatagramApplication ArrayList with the answers to the current 
     * datagram
     */
    @Override
    public ArrayList<DatagramInternet> getAutomaticAnswer() {
        ArrayList<DatagramInternet> arrayList = new ArrayList<DatagramInternet>();
        arrayList.add(getAnswer());
        return arrayList;
    }

    /**
     * Returns a copy of the object.
     *
     * @return a copy of the object.
     * @throws CloneNotSupportedException
     */
    @Override
    public DatagramInternetIP clone() throws CloneNotSupportedException {
        DatagramInternetIP clone;
        clone = (DatagramInternetIP) super.clone();

        return clone;
    }
}
