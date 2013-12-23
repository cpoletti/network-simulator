/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import simulator.DatagramInternet;

/**
 *
 * @author Cristiano
 */
public class DatagramInternetICMP extends DatagramInternetIP implements Serializable, Cloneable {

    private String icmpType;
    private int code;
    private int checksum;
    private String dataICMP;
    private LinkedHashMap<String, Integer> types = new LinkedHashMap<String, Integer>();
    private LinkedHashMap<Integer, String[]> codes = new LinkedHashMap<Integer, String[]>();

    /**
     *
     */
    public DatagramInternetICMP() {

        types.put("0:Echo Reply", 0);
        types.put("3:Destination Unreachable", 3);
        types.put("4:Source Quench", 4);
        types.put("5:Redirect", 5);
        types.put("8:Echo Request", 8);
        types.put("11:Time Exceeded", 11);
        types.put("12:Parameter Problem", 12);
        types.put("13:Timestamp Request", 13);
        types.put("14:Timestamp Reply", 14);
        types.put("15:Information Request", 15);
        types.put("16:Information Reply", 16);
        types.put("17:Address Mask Request", 17);
        types.put("18:Address Mask Reply", 18);

        String codes0[] = {"0:Echo reply"};
        codes.put(0, codes0);

        String codes3[] = {
            "0:Destination network unreachable",
            "1:Destination host unreachable",
            "2:Destination protocol unreachable",
            "3:Destination port unreachable",
            "4:Fragmentation required, and DF flag set",
            "5:Source route failed",
            "6:Destination network unknown",
            "7:Destination host unknown",
            "8:Source host isolated",
            "9:Network administratively prohibited",
            "10:Host administratively prohibited",
            "11:Network unreachable for TOS",
            "12:Host unreachable for TOS",
            "13:Communication administratively prohibited",
            "14:Host Precedence Violation",
            "15:Precedence cutoff in effect"
        };
        codes.put(3, codes3);

        String codes4[] = {"0:Source quench (congestion control)"};
        codes.put(4, codes4);

        String codes5[] = {
            "0:Redirect Datagram for the Network",
            "1:Redirect Datagram for the Host",
            "2:Redirect Datagram for the TOS & network",
            "3:Redirect Datagram for the TOS & host"
        };
        codes.put(5, codes5);

        String codes8[] = {"0:Echo request (used to ping)"};
        codes.put(8, codes8);

        String codes11[] = {
            "0:TTL expired in transit",
            "1:Fragment reassembly time exceeded"
        };
        codes.put(11, codes11);

        String codes12[] = {
            "0:Pointer indicates the error",
            "1:Missing a required option",
            "2:Bad length"
        };
        codes.put(12, codes12);

        String codes13[] = {"0:Timestamp request"};
        codes.put(13, codes13);

        String codes14[] = {"0:Timestamp reply"};
        codes.put(14, codes14);

        String codes15[] = {"0:Information Request"};
        codes.put(15, codes15);

        String codes16[] = {"0:Information Reply"};
        codes.put(16, codes16);

        String codes17[] = {"0:Address Mask Request"};
        codes.put(17, codes17);

        String codes18[] = {"0:Address Mask Reply"};
        codes.put(18, codes18);

        this.icmpType = "0:Echo Reply";
        this.code = 0;

    }

    /**
     * Return the protocol name (ICMP)
     *
     * @return the protocol name
     */
    @Override
    public String datagramInternetType() {
        return "ICMP";
    }

    /**
     * Returns the value of the ICMP type field
     *
     * @return the value of the ICMP type field
     */
    public String getICMPType() {
        return icmpType;
    }

    /**
     * Sets the value of the ICMP type field
     *
     * @param icmpType the ICMP type
     */
    public void setICMPType(String icmpType) {
        this.icmpType = icmpType;
    }

    /**
     * Returns the value of the ICMP code field
     *
     * @return the value of the ICMP code field
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the value of the ICMP code field
     *
     * @param code the ICMP code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Returns the value of the checksum field
     *
     * @return the value of the checksum field
     */
    public int getChecksum() {
        return checksum;
    }

    /**
     * Sets the value of the checksum field
     *
     * @param checksum the checksum field
     */
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    /**
     * Returns the value of the IICMP Data field
     *
     * @return  the value of the IICMP Data field
     */
    public String getDataICMP() {
        return dataICMP;
    }

    /**
     * Sets the value of ICMP Data field
     *
     * @param dataICMP  the ICMP Data value
     */
    public void setDataICMP(String dataICMP) {
        this.dataICMP = dataICMP;
    }

    /**
     * Returns a HashMap containing the possible options of the ICMP Type field
     *
     * @return a HashMap with ICMP types
     */
    public LinkedHashMap<String, Integer> getTypes() {
        return types;
    }

    /**
     * Returns a HashMap containing the possible options of the ICMP Code field
     *
     * @return a HashMap with ICMP codes
     */
    public LinkedHashMap<Integer, String[]> getCodes() {
        return codes;
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
        DatagramInternetICMP answer = new DatagramInternetICMP();
        answer.setSourceIPAddress(this.destinationIPAddress);
        answer.setDestinationIPAddress(this.sourceIPAddress);
        return answer;

    }
    
    /**
     * Returns an DatagramInternet ArrayList that represents a automatic responses to the current datagram.
     *
     * @return a DatagramInternet ArrayList with the answers to the current datagram
     */
    @Override
    public ArrayList<DatagramInternet> getAutomaticAnswer() {
        ArrayList<DatagramInternet> arrayList = new ArrayList<DatagramInternet>();
        DatagramInternetICMP answer = new DatagramInternetICMP();

        answer.setSourceIPAddress(this.destinationIPAddress);
        answer.setDestinationIPAddress(this.sourceIPAddress);
        int tipo = types.get(this.icmpType);
        switch (tipo) {
            case 8:
                answer.setICMPType("0:Echo Reply");
                answer.setCode(0);
                arrayList.add(answer);
                break;
            default:
                break;

        }
        return arrayList;
    }

    /**
     *Returns a copy of the object.
     *
     * @return a copy of the object.
     * @throws CloneNotSupportedException
     */
    @Override
    public DatagramInternetICMP clone() throws CloneNotSupportedException {
        DatagramInternetICMP clone;
        clone = (DatagramInternetICMP) super.clone();

        return clone;
    }
}
