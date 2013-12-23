/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristiano
 */
public class Netstat extends javax.swing.JInternalFrame {

    private NetMap map;
    /**
     * controlling connection to each destination ports: Key -> port destination
     * Array -> 0 = source port, 1 = source workstation, 2 = last received
     * packet
     *
     */
    private HashMap<String, ArrayList<Object>> portControl;
    private ArrayList<String> portList;
    private DefaultTableModel tableModel;
    private int initialPort = 6000;
    private int maximumPort = 65535;
    //saves all packets received from connection initiation from a particular worksation
    private HashMap<String, ArrayList<Object[]>> initialPackets;
    private boolean closeConection = false;

    /**
     * Creates new form NetMap
     * 
     * @param map a reference to net map class 
     */
    public Netstat(NetMap map) {
        initComponents();
        this.map = map;

        tableModel = (DefaultTableModel) jtNetstat.getModel();
        portControl = new HashMap<String, ArrayList<Object>>();
        initialPackets = new HashMap<String, ArrayList<Object[]>>();
        portList = new ArrayList<String>();

        ListSelectionModel cellSelectionModel = jtNetstat.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                createStates();
            }
        });

        translate();
    }

    /**
     * Add a state image in the label
     */
    public void createStates() {

        //clear the state image
        jpConectionDetail.removeAll();

        //if have a connection
        if (tableModel.getRowCount() > 0) {

            //if haven't a selected row, select the first
            if (jtNetstat.getSelectedRow() < 0) {
                jtNetstat.setRowSelectionInterval(0, 0);
            }

            //get the protocol and state of the selected row
            int index = jtNetstat.getSelectedRow();
            String protocol = (String) tableModel.getValueAt(index, 0);
            String state = (String) tableModel.getValueAt(index, 3);

            //if the selected row have state and protocol
            if (protocol != null && state != null) {

                protocol = protocol.toLowerCase().trim();

                state = state.toLowerCase().trim();
                state = state.replaceAll(" ", "_");
                state += ".png";

                //crete a image url
                URL url = getClass().getClassLoader().getResource("images/" + protocol + "/" + state);

                if (url != null) {

                    try {
                        BufferedImage originalImage = ImageIO.read(url);
                        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                        //get panel dimensions
                        int panelWidth = jpConectionDetail.getWidth();
                        int panelHeight = jpConectionDetail.getHeight();

                        //get image dimensions
                        double imageWidth = (double) originalImage.getWidth();
                        double imageHeight = (double) originalImage.getHeight();

                        //calculates image proportion
                        double widthProp = ((panelWidth - 50) / imageWidth);
                        double heightProp = ((panelHeight - 50) / imageHeight);
                        double propResize = Math.min(widthProp, heightProp);

                        int imageWidthRisize = (int) imageWidth;
                        int imageHeightRisize = (int) imageHeight;

                        //if the image is larger than panel. resize the image
                        if (propResize < 1.0) {
                            imageWidthRisize = ((int) (imageWidthRisize * propResize));
                            imageHeightRisize = ((int) (imageHeightRisize * propResize));
                        }
                        BufferedImage resizeImagePng = resizeImageWithHint(originalImage, type, imageWidthRisize, imageHeightRisize);

                        //add a label with the state image
                        jpConectionDetail.setLayout(null);
                        JLabel jl = new JLabel();
                        jpConectionDetail.add(jl);
                        ImageIcon icon = new ImageIcon(resizeImagePng);
                        jl.setIcon(icon);
                        jl.setVerticalTextPosition(SwingConstants.CENTER);
                        jl.setHorizontalTextPosition(SwingConstants.CENTER);
                        jl.setHorizontalAlignment(SwingConstants.CENTER);
                        jl.setVerticalAlignment(SwingConstants.CENTER);
                        jl.setBounds(0, 0, panelWidth, panelHeight);

                    } catch (Exception e) {
                        //debug exeption
                        System.out.println(e.toString());
                    }
                } else {
                    //clear the state image
                    jpConectionDetail.removeAll();
                }
            } else {
                //clear the state image
                jpConectionDetail.removeAll();
            }
        } else {
            //clear the state image
            jpConectionDetail.removeAll();
        }

        //refersh the panel image
        jpConectionDetail.validate();
        jpConectionDetail.repaint();
    }

    /**
     * Resize a iamge
     *
     * @param originalImage the original image
     * @param type the image type
     * @param width the resize width
     * @param height the resize height
     *
     * @return the resized image
     */
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    /**
     * Validate received packet
     *
     * @param pk the packet to validate
     *
     * @return if is a valid packet
     */
    public boolean validateReceivedPacket(Packet pk) {
        DatagramTransport dt;
        WorkStation ws;
        boolean validPort;
        this.closeConection = false;

        try {
            ws = pk.getSourceWorkStation();
            dt = pk.getDatagramTransport();

            //validate the transport datagram
            if (dt != null) {
                validPort = validateReceivedDatagrams(dt, ws);
            } else {
                validPort = true;
            }

            if (!validPort) {
                //if is not a valid package sets it as dropped
                pk.setStatus(Packet.DROPPED);
            } else {
                //else sets it as unread
                pk.setStatus(Packet.UNREAD);
            }

            //add the packet in the net map
            map.addReceivedPacket(pk, ws.getNick());

        } catch (Exception ex) {
            //debug error
            System.out.println("Exception Object: " + ex.getMessage());
            validPort = false;
        }

        return validPort;
    }

    /**
     * Validate the transport datagram received
     *
     * @param dt the received datagram
     * @param sourceWS the source workstation
     *
     * @return if is a valid datagram
     */
    public boolean validateReceivedDatagrams(DatagramTransport dt, WorkStation sourceWS) {
        int destinationPort = dt.getDestinationPort();
        int sourcePort = dt.getSourcePort();

        //checks if the port is free
        if (!portControl.containsKey(destinationPort + "")) {

            //checks if the datagram protocol establishes a connection
            if (dt.establishesConnection()) {

                //checks if a packet is invalid
                if (dt.getNextStateName(null, DatagramTransport.RECIVED, null) == null) {
                    return false;
                } else {

                    //save destinations that tried to connect
                    if (!initialPackets.containsKey(sourceWS.getIP() + ":" + sourceWS.getPort())) {
                        ArrayList<Object[]> array = new ArrayList<Object[]>();
                        Object[] arrayObject = {sourcePort + "", dt};
                        array.add(arrayObject);
                        initialPackets.put(sourceWS.getIP() + ":" + sourceWS.getPort(), array);
                    } else {
                        ArrayList<Object[]> array = initialPackets.get(sourceWS.getIP() + ":" + sourceWS.getPort());
                        Object[] arrayObject = {sourcePort + "", dt};
                        array.add(arrayObject);
                    }
                }
            }

        } else {
            //if the port is busy, get the recipient information (source port + Workstation)
            ArrayList<Object> array = portControl.get(destinationPort + "");
            WorkStation auxWS = (WorkStation) array.get(1);

            //checks if the packet came from the same workstation
            if (auxWS.getIP().equals(sourceWS.getIP()) && auxWS.getPort() == sourceWS.getPort()) {

                //get index in the hash
                int index = portList.indexOf(destinationPort + "");

                //get the protocol of the last packet
                String protocol = (String) tableModel.getValueAt(index, 0);

                //check if the datagram sent is from the same protocol
                if (protocol.equalsIgnoreCase(dt.datagramTransportType())) {

                    //get source port previously defined
                    String auxPort = (String) array.get(0);

                    //get the previous state of the port
                    String currentState = (String) tableModel.getValueAt(index, 3);

                    //check if can change the source port in the current state
                    boolean changePort = false;
                    if (dt.allowChangePort(currentState)) {
                        //change the source port previously defined
                        auxPort = sourcePort + "";
                        changePort = true;
                    }

                    //if the packet came from the same source port
                    if (auxPort.equals(sourcePort + "")) {
                        String nextState = dt.getNextStateName(currentState, DatagramTransport.RECIVED, null);

                        if (nextState != null) {

                            tableModel.setValueAt(nextState, index, 3);
                            if (changePort) {
                                array.set(0, sourcePort + "");
                            }
                            array.set(2, dt);
                            tableModel.setValueAt(sourceWS.getIP() + ":" + sourcePort, index, 2);

                            //if is the last state close the connection
                            if (dt.isLastState(nextState)) {
                                removePort(destinationPort + "");
                                this.closeConection = true;
                            } else {
                                jtNetstat.setRowSelectionInterval(index, index);
                            }
                            createStates();

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate sent packet
     *
     * @param pk the packet to validate
     *
     * @return if is a valid packet
     */
    public boolean validateSentPacket(Packet pk) {
        DatagramTransport dt;
        WorkStation ws;
        boolean validPort;

        try {
            ws = pk.getDestinationWorkStation();
            dt = pk.getDatagramTransport();

            //validate the transport datagram
            if (dt != null) {
                validPort = validateSentDatagrams(dt, ws);
            } else {
                validPort = true;
            }

            //if is not a valid package sets it as dropped
            if (!validPort) {
                pk.setStatus(Packet.DROPPED);
            }

            map.addSentPacket(pk, ws.getNick());

        } catch (Exception ex) {
            //debug exception
            System.out.println("Exception Object: " + ex.getMessage());
            validPort = false;
        }

        return validPort;
    }

    /**
     * Validate the transport datagram sent
     *
     * @param dt the sent datagram
     * @param destinationWS the destination workstation
     *
     * @return if is a valid datagram
     */
    public boolean validateSentDatagrams(DatagramTransport dt, WorkStation destinationWS) {
        int destinationPort = dt.getDestinationPort();
        int sourcePort = dt.getSourcePort();
        Config conf = Config.getInstance();

        //check has not been sent a package by this port (my source port)
        if (!portControl.containsKey(sourcePort + "")) {

            //check if the protocol datagram establishes a connection
            if (dt.establishesConnection()) {

                DatagramTransport lastDatagram = null;

                //check if the destination workstation tried to establish a connection and get the packet sent
                if (initialPackets.containsKey(destinationWS.getIP() + ":" + destinationWS.getPort())) {

                    ArrayList<Object[]> array = initialPackets.get(destinationWS.getIP() + ":" + destinationWS.getPort());
                    for (int i = 0; i < array.size(); i++) {
                        Object[] arrayObject = array.get(i);
                        String estabilishDestianationPort = (String) arrayObject[0];

                        //checks if came from the same source port I'm trying to send
                        if (estabilishDestianationPort.equalsIgnoreCase(destinationPort + "")) {
                            lastDatagram = (DatagramTransport) arrayObject[1];
                            //remove this packet from the initial packets array (the connection is already started)
                            array.remove(i);
                            break;
                        }
                    }
                }

                //get the next state name
                String state;
                if (dt.haveConnectionStates()) {
                    state = dt.getNextStateName(null, DatagramTransport.SENT, lastDatagram);
                } else {
                    state = "";
                }

                //if it is a valid packet includes in the ports control
                if (state != null) {

                    //add the port to the list of ports busy
                    portList.add(sourcePort + "");

                    //add the station in the table
                    ArrayList<Object> array = new ArrayList<Object>();
                    array.add(destinationPort + "");
                    array.add(destinationWS);
                    array.add(null);
                    portControl.put(sourcePort + "", array);
                    tableModel.addRow(new String[]{dt.datagramTransportType(), conf.getIp() + ":" + sourcePort, destinationWS.getIP() + ":" + destinationPort, state});
                    jtNetstat.setRowSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
                }
            }
            return true;

        } else {
            //get information from the last packet sent by the door (my source port)
            ArrayList<Object> array = portControl.get(sourcePort + "");
            WorkStation auxWS = (WorkStation) array.get(1);

            //check if the pocote was sent to the same Workstation
            if (auxWS.getIP().equals(destinationWS.getIP()) && auxWS.getPort() == destinationWS.getPort()) {

                //get index in the hash
                int index = portList.indexOf(sourcePort + "");

                //get the protocol from the last packet
                String protocol = (String) tableModel.getValueAt(index, 0);

                //check if the datagram sent is from the same protocol
                if (protocol.equalsIgnoreCase(dt.datagramTransportType())) {

                    //get destination port previously defined
                    String auxPort = (String) array.get(0);

                    //get previous state of the port
                    String prevStatus = (String) tableModel.getValueAt(index, 3);

                    //get last received datagram
                    DatagramTransport lastDatagram = (DatagramTransport) array.get(2);

                    //check that was sent to the same destination port
                    if (auxPort.equals(destinationPort + "")) {
                        String proximoEstado = dt.getNextStateName(prevStatus, DatagramTransport.SENT, lastDatagram);
                        if (proximoEstado != null) {
                            tableModel.setValueAt(proximoEstado, index, 3);

                            //if is the last state remove the row from the table
                            if (dt.isLastState(proximoEstado)) {
                                removePort(sourcePort + "");
                            } else {
                                //else select the row
                                jtNetstat.setRowSelectionInterval(index, index);
                            }

                            createStates();
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Find a free local port to connect
     *
     * @return the local port number
     */
    public int findFreePort() {
        for (int i = initialPort; i <= maximumPort; i++) {
            if (!portControl.containsKey(i + "")) {
                initialPort = i;
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove the row with the defined source port
     *
     * @param sourcePort the source port number
     */
    public void removePort(String sourcePort) {
        if (portControl.containsKey(sourcePort)) {
            int index = portList.indexOf(sourcePort + "");
            portList.remove(index);
            tableModel.removeRow(index);
            portControl.remove(sourcePort);
            createStates();
        }
    }

    /**
     * Gets the current status of the connection
     *
     * @param sourcePort the local source port of the connection
     * @param destinationWS the destination workstation of the connection
     * @param destinationPort the local destination port of the connection
     *
     * @return the name of the current connection status
     */
    public String getPortStatus(int sourcePort, WorkStation destinationWS, int destinationPort) {

        //check if the port have status
        if (portControl.containsKey(sourcePort + "")) {

            //get port information
            ArrayList<Object> array = portControl.get(sourcePort + "");
            WorkStation auxWS = (WorkStation) array.get(1);

            //check if it's the same Workstation
            if (auxWS.getIP().equals(destinationWS.getIP()) && auxWS.getPort() == destinationWS.getPort()) {

                //get index in the hash
                int index = portList.indexOf(sourcePort + "");

                //get source port previously defined
                String auxPort = (String) array.get(0);

                //if the packet came from the same source port
                if (auxPort.equals(destinationPort + "")) {
                    //get the previous state of the port
                    String currentStateName = (String) tableModel.getValueAt(index, 3);
                    return currentStateName;
                }
            }
        }
        return null;
    }

    /**
     * Returns if the connection is closed
     *
     * @return the connection sttus
     */
    public boolean isConnectionClosed() {
        return this.closeConection;
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();

        this.setTitle(bundle.getString("Netstat.title"));
        tableModel.setColumnIdentifiers(
                new String[]{
                    bundle.getString("Netstat.table.protocol"),
                    bundle.getString("Netstat.table.localAdress"),
                    bundle.getString("Netstat.table.externalAdress"),
                    bundle.getString("Netstat.table.state")
                });

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("Netstat.jpConectionDetail.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        jpConectionDetail.setBorder(titledBorder);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtNetstat = new javax.swing.JTable();
        jpConectionDetail = new javax.swing.JPanel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        setTitle(bundle.getString("Netstat.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        jtNetstat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Protocolo", "Endereço Local", "Endereço Externo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtNetstat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtNetstat);
        jtNetstat.getColumnModel().getColumn(0).setResizable(false);
        jtNetstat.getColumnModel().getColumn(1).setResizable(false);
        jtNetstat.getColumnModel().getColumn(2).setResizable(false);
        jtNetstat.getColumnModel().getColumn(3).setResizable(false);

        getContentPane().add(jScrollPane1);

        jpConectionDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("Netstat.jpConectionDetail.title"))); // NOI18N
        jpConectionDetail.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jpConectionDetailComponentResized(evt);
            }
        });
        jpConectionDetail.setLayout(new javax.swing.BoxLayout(jpConectionDetail, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jpConectionDetail);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpConectionDetailComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpConectionDetailComponentResized
        // TODO add your handling code here:
        createStates();
        jpConectionDetail.validate();
        jpConectionDetail.repaint();
    }//GEN-LAST:event_jpConectionDetailComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpConectionDetail;
    private javax.swing.JTable jtNetstat;
    // End of variables declaration//GEN-END:variables
}
