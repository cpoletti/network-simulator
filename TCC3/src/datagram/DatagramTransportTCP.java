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
public class DatagramTransportTCP extends DatagramTransport implements Serializable, Cloneable {

    /**
     * TCP CLOSED state
     */
    public static final int CLOSED = 0;
    
     /**
     * TCP LISTEN state
     */
    public static final int LISTEN = 1;
    
     /**
     * TCP SYN RCVD state
     */
    public static final int SYN_RCVD = 2;
    
     /**
     * TCP SYN SENT state
     */
    public static final int SYN_SENT = 3;
    
     /**
     * TCP ESTABLISHED state
     */
    public static final int ESTABLISHED = 4;
    
     /**
     * TCP FIN WAIT 1 state
     */
    public static final int FIN_WAIT_1 = 5;
    
     /**
     * TCP FIN WAIT 2 state
     */
    public static final int FIN_WAIT_2 = 6;
    
     /**
     * TCP TIME WAIT state
     */
    public static final int TIME_WAIT = 7;
    
     /**
     * TCP CLOSING state
     */
    public static final int CLOSING = 8;
    
     /**
     * TCP CLOSE WAIT state
     */
    public static final int CLOSE_WAIT = 9;
    
     /**
     * TCP LAST ACK state
     */
    public static final int LAST_ACK = 10;
    
    private String[] statesName = {"CLOSED", "LISTEN", "SYN RCVD", "SYN SENT", "ESTABLISHED", "FIN WAIT 1", "FIN WAIT 2", "TIME WAIT", "CLOSING", "CLOSE WAIT", "LAST ACK"};
    private int state = 0;
    private String sequenceNumber;
    private String acknowledgementNumber;
    private int headerLenght;
    private boolean cwr;
    private boolean ece;
    private boolean urg;
    private boolean ack;
    private boolean psh;
    private boolean rst;
    private boolean syn;
    private boolean fin;
    private int windowSize;
    private int urgentPointer;
    private int options;

    /**
     * 
     */
    public DatagramTransportTCP() {

        this.setSourcePort(7000);
        this.setDestinationPort(80);
        this.sequenceNumber = "4294967295";
        this.acknowledgementNumber = "4294967295";
        this.headerLenght = 5;
        this.cwr = false;
        this.ece = false;
        this.urg = false;
        this.ack = false;
        this.psh = false;
        this.rst = false;
        this.syn = false;
        this.fin = false;
        this.windowSize = 65535;
        this.urgentPointer = 65535;
        this.setConnects(true);
        this.setConnectionStates(true);
    }

    /**
     * Return the protocol name (TCP)
     *
     * @return the protocol name
     */
    public String datagramTransportType() {
        return "TCP";
    }

    /**
     * Returns the value of the Sequence Number field
     *
     * @return the value of the Sequence Number field
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the Sequence Number field
     *
     * @param sequenceNumber the position of the data in the data stream
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Returns the value of the Acknowledgement Number field
     *
     * @return the value of the Acknowledgement Number field
     */
    public String getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    /**
     * Sets the value of the Acknowledgement Number field
     *
     * @param acknowledgementNumber the value of the Acknowledgement Number field
     */
    public void setAcknowledgementNumber(String acknowledgementNumber) {
        this.acknowledgementNumber = acknowledgementNumber;
    }

    /**
     * Returns the value of the Header Lenght field
     *
     * @return the value of the Header Lenght field
     */
    public int getHeaderLenght() {
        return headerLenght;
    }

    /**
     * Sets the value of the Header Lenght field
     *
     * @param headerLenght the TCP header
     */
    public void setHeaderLenght(int headerLenght) {
        this.headerLenght = headerLenght;
    }

    /**
     * Returns the value of the CWR field
     *
     * @return the value of the CWR field
     */
    public boolean isCWR() {
        return cwr;
    }

    /**
     * Sets the value of the CWR field
     *
     * @param cwr the value of the CWR field
     */
    public void setCWR(boolean cwr) {
        this.cwr = cwr;
    }

    /**
     * Returns the value of the ECE field
     *
     * @return the value of the ECE field
     */
    public boolean isECE() {
        return ece;
    }

    /**
     * Sets the value of the ECE field
     *
     * @param ece the value of the ECE field
     */
    public void setECE(boolean ece) {
        this.ece = ece;
    }

    /**
     * Returns the value of the URG field
     *
     * @return the value of the URG field
     */
    public boolean isURG() {
        return urg;
    }
    
    /**
     * Sets the value of the URG field
     *
     * @param urg the value of the URG field
     */
    public void setURG(boolean urg) {
        this.urg = urg;
    }
    
     /**
     * Returns the value of the ACK field
     *
     * @return the value of the ACK field
     */
    public boolean isACK() {
        return ack;
    }
    
    /**
     * Sets the value of the ACK field
     *
     * @param ack the value of the ACK field
     */
    public void setACK(boolean ack) {
        this.ack = ack;
    }
    
     /**
     * Returns the value of the PSH field
     *
     * @return the value of the PSH field
     */
    public boolean isPSH() {
        return psh;
    }
    
    /**
     * Sets the value of the PSH field
     *
     * @param psh the value of the PSH field
     */
    public void setPSH(boolean psh) {
        this.psh = psh;
    }
    
     /**
     * Returns the value of the RST field
     *
     * @return the value of the RST field
     */
    public boolean isRST() {
        return rst;
    }
    
     /**
     * Sets the value of the RST field
     *
     * @param rst the value of the RST field
     */
    public void setRST(boolean rst) {
        this.rst = rst;
    }
    
     /**
     * Returns the value of the SYN field
     *
     * @return the value of the SYN field
     */
    public boolean isSYN() {
        return syn;
    }
    
     /**
     * Sets the value of the SYN field
     *
     * @param syn the value of the SYN field
     */
    public void setSYN(boolean syn) {
        this.syn = syn;
    }
    
     /**
     * Returns the value of the FIN field
     *
     * @return the value of the FIN field
     */
    public boolean isFIN() {
        return fin;
    }
    
    /**
     * Sets the value of the FIN field
     *
     * @param fin the value of the FIN field
     */
    public void setFIN(boolean fin) {
        this.fin = fin;
    }
    
     /**
     * Returns the value of the Window Size field
     *
     * @return the value of the Window Size field
     */
    public int getWindowSize() {
        return windowSize;
    }
    
    /**
     * Sets the value of the Window Size field
     *
     * @param windowSize the size of the TCP Window Size
     */
    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }
    
     /**
     * Returns the value of the Urgent Pointer field
     *
     * @return the value of the Urgent Pointer field
     */
    public int getUrgentPointer() {
        return urgentPointer;
    }
    
    /**
     * Sets the value of the Urgent Pointer field
     *
     * @param urgentPointer the position of the urgent data
     */
    public void setUrgentPointer(int urgentPointer) {
        this.urgentPointer = urgentPointer;
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
     * @param options the extra options of the TCP protocol
     */
    public void setOptions(int options) {
        this.options = options;
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
     * Returns the name of the current state
     * @return the current state name
     */
    @Override
    public String getStateName() {
        return statesName[state];
    }
    
    /**
     * Returns an DatagramTransport object that represents a response to 
     * the current datagram.
     *
     * @return ua response to the current datagram
     */
    @Override
    public DatagramTransport getAnswer() {
        DatagramTransportTCP answer = new DatagramTransportTCP();
        answer.setSourcePort(this.getDestinationPort());
        answer.setDestinationPort(this.getSourcePort());
        return answer;
    }

    /**
     * Returns the name of the next state of the protocol, 
     * returns null if the package is invalid
     *
     * @param lastStateName the last state of the protocol
     * @param packetType 1 - if the packet is received; 2 if the package is sent
     * @param lastReciveDatagram a DatagramTransport representing the last packet sent
     * 
     * @return null if invalid package or the name of the next state
     */
    @Override
    public String getNextStateName(String lastStateName, int packetType, DatagramTransport lastReciveDatagram) {

        DatagramTransportTCP lastReciveTCPDatagram = (DatagramTransportTCP) lastReciveDatagram;

        //if is the first packet of the connection
        if (lastStateName == null) {
            if (this.fin) {
                return null;
            } else {
                //if sent the package
                if (packetType == SENT) {
                    //if is the start of connection
                    if (this.syn && !this.ack) {
                        return statesName[SYN_SENT];
                    }

                    //if is a connection response
                    if (this.syn && this.ack) {
                        if (lastReciveDatagram != null && (lastReciveTCPDatagram.syn && !lastReciveTCPDatagram.ack && !lastReciveTCPDatagram.fin)) {
                            return statesName[SYN_RCVD];
                        }
                    }
                }

                //if received the package
                if (packetType == RECIVED) {
                    //only accept packages start connection
                    if (this.syn && !this.ack) {
                        return "";
                    }
                }
            }

        } else {
            int lastState = findStateByName(lastStateName);
            if (lastState > 0) {
                switch (lastState) {
                    case CLOSED:
                        break;
                    case LISTEN:
                        break;
                    case SYN_RCVD:
                        //if received the package
                        if (packetType == RECIVED) {
                            //accepts packets with active SYN and inactive ACK (third step of the handshake)
                            if (this.ack && !this.syn && !this.fin) {
                                //retorna estado ESTABLISHED
                                return statesName[ESTABLISHED];
                            }
                        }
                        break;
                    case SYN_SENT:
                        //if received the package
                        if (packetType == RECIVED) {
                            //accepts packets with active SYN and active ACK (second step of the handshake)
                            if (this.ack && this.syn && !this.fin) {
                                return statesName[SYN_SENT];
                            }
                        }

                        //if answer
                        if (packetType == SENT) {
                            //checks if received packet with SYN and ACK active
                            if (lastReciveDatagram != null && (lastReciveTCPDatagram.syn && lastReciveTCPDatagram.ack && !lastReciveTCPDatagram.fin)) {
                                //if answered with active ACK
                                if (this.ack && !this.syn && !this.fin) {
                                    return statesName[ESTABLISHED];
                                }
                            }
                        }
                        break;
                    case ESTABLISHED:
                        //if sent the package
                        if (packetType == SENT) {
                            //checks if already received a packet with FIN active (request for close connection)
                            System.out.println("lastReciveTCPDatagram = " + lastReciveTCPDatagram);
                            System.out.println("SYN = " + lastReciveTCPDatagram.syn + ";ACK = " + lastReciveTCPDatagram.ack + ";FIN = " + lastReciveTCPDatagram.fin);
                            if (lastReciveDatagram != null && (!lastReciveTCPDatagram.syn && !lastReciveTCPDatagram.ack && lastReciveTCPDatagram.fin)) {
                                //check if was answered with an active ACK
                                if (this.ack && !this.syn && !this.syn) {
                                    return statesName[CLOSE_WAIT];
                                }

                            } else {
                                //checks that was sent a package with FIN active (request for close connection)
                                if (this.fin && !this.ack && !this.syn) {
                                    return statesName[FIN_WAIT_1];
                                }
                            }

                        }

                        //if received the package
                        if (packetType == RECIVED) {
                            //checks if it is a package with FIN active (request close connection)
                            if (this.fin && !this.ack && !this.syn) {
                                //maintain the ESTABLISHED state until it is answered with an ACK
                                return statesName[ESTABLISHED];
                            }
                        }

                        //If was received a normal package checks if came with the active ACK
                        if (this.ack && !this.syn && !this.syn) {
                            return statesName[ESTABLISHED];
                        }

                        break;
                    case FIN_WAIT_1:
                        //if the packet was received with active ACK goes to the FIN_WAIT_2 state
                        if (packetType == RECIVED) {
                            if (!this.fin && this.ack && !this.syn) {
                                return statesName[FIN_WAIT_2];
                            }
                        }
                        break;
                    case FIN_WAIT_2:

                        //if received the package
                        if (packetType == RECIVED) {
                            //accepts packets with active FIN (second step of the close connection) and continues in the FIN_WAIT_2 state until responding with ative ACK
                            if (!this.ack && !this.syn && this.fin) {
                                return statesName[FIN_WAIT_2];
                            }
                        }

                        //if answer
                        if (packetType == SENT) {
                            //checks if has received a packet with active FIIN
                            if (lastReciveDatagram != null && (!lastReciveTCPDatagram.syn && !lastReciveTCPDatagram.ack && lastReciveTCPDatagram.fin)) {
                                //if answered with ACK
                                if (this.ack && !this.syn && !this.fin) {
                                    return statesName[CLOSED];
                                }
                            }
                        }

                        break;
                    case TIME_WAIT:
                        break;
                    case CLOSING:
                        break;
                    case CLOSE_WAIT:

                        if (packetType == SENT) {
                            //check was sent a package with FIN active (request for close connection) 
                            if (this.fin && !this.ack && !this.syn) {
                                return statesName[LAST_ACK];
                            }

                        }
                        break;
                    case LAST_ACK:
                        if (packetType == RECIVED) {
                            //accepts packets with active ACK (confirmation of close connection)
                            if (this.ack && !this.syn && !this.fin) {
                                return statesName[CLOSED];
                            }
                        }

                        break;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Search the state code from this name
     * 
     * @param stateName the state name 
     * @return 
     */
    public int findStateByName(String stateName) {
        if (stateName != null) {
            for (int i = 0; i < statesName.length; i++) {
                if (stateName.equalsIgnoreCase(statesName[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Checks if is possible change the TCP connection port
     * 
     * @param lastState the name of the last state
     * @return if is possible change the conection port
     */
    @Override
    public boolean allowChangePort(String lastState) {
        return lastState.equalsIgnoreCase(statesName[SYN_SENT]);
    }

    /**
     * Checks if it is the last state
     * 
     * @param stateName the state name
     * @return if it is the last state 
     */
    @Override
    public boolean isLastState(String stateName) {
        return stateName.equalsIgnoreCase(statesName[CLOSED]);
    }
    
    /**
     * Returns an DatagramTransport ArrayList that represents a automatics 
     * responses to the current datagram.
     *
     * @param lastStateName the name of the last state
     * @return a DatagramTransport ArrayList with the answers to the current 
     * datagram
     * 
     */
    @Override
    public ArrayList<DatagramTransport> getAutomaticAnswer(String lastStateName) {
        ArrayList<DatagramTransport> arrayList = new ArrayList<DatagramTransport>();
        DatagramTransportTCP answer = (DatagramTransportTCP) this.getAnswer();

        //if is the first packet of the connection
        if (lastStateName == null) {
            answer.ack = true;
            answer.syn = true;
            answer.disableNextTab();
            arrayList.add(answer);
        } else {
            int lastState = findStateByName(lastStateName);
            if (lastState > 0) {
                switch (lastState) {
                    case CLOSED:
                        break;
                    case LISTEN:
                        break;
                    case SYN_RCVD:
                        break;
                    case SYN_SENT:
                        answer.ack = true;
                        answer.disableNextTab();
                        arrayList.add(answer);
                    case ESTABLISHED:

                        //if received a request for close connection reply with ACK and sending request for close connection
                        if (this.fin && !this.ack && !this.syn) {
                            answer.ack = true;
                            answer.disableNextTab();
                            arrayList.add(answer);

                            DatagramTransportTCP answer2 = (DatagramTransportTCP) this.getAnswer();
                            answer2.fin = true;
                            answer2.disableNextTab();
                            arrayList.add(answer2);
                        }

                        //answer packets only if the application protocol is active
                        if (this.isEnableNextPort()) {
                            if (!this.fin && this.ack && !this.syn) {
                                answer.ack = true;
                                answer.enableNextTab();
                                arrayList.add(answer);
                            }
                        }

                        break;
                    case FIN_WAIT_2:
                        if (this.fin && !this.ack && !this.syn) {
                            answer.ack = true;
                            answer.disableNextTab();
                            arrayList.add(answer);
                        }
                        break;
                }
            }
        }
        return arrayList;
    }

    /**
     * Returns a copy of the object.
     * 
     * @return a copy of the object.
     * @throws CloneNotSupportedException 
     */
    @Override
    public DatagramTransportTCP clone() throws CloneNotSupportedException {
        DatagramTransportTCP clone;
        clone = (DatagramTransportTCP) super.clone();

        return clone;
    }
}
