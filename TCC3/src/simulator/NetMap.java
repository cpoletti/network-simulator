/*
 * NetMap.java
 *
 * Created on 14 de Outubro de 2009, 20:55
 */
package simulator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego Muller
 */
public class NetMap extends javax.swing.JInternalFrame {

    private URL urlSend = getClass().getClassLoader().getResource("images/send.png");
    private ImageIcon iconSend = new ImageIcon(urlSend);
    private int receivedPackets = 0;
    private int sentPackets = 0;
    private ArrayList<WorkStation> dataArray = new ArrayList<WorkStation>();
    private TreeMap<Integer, Object> receivedRow = new TreeMap<Integer, Object>();
    private TreeMap<Integer, Object> sentRow = new TreeMap<Integer, Object>();
    private TreeMap<String, Object> workStations = new TreeMap<String, Object>();
    private int port = 7000;
    private String ip = "";
    private WorkStation myWorkStiation = null;
    private ArpTable arpTable;
    private FindStation fs;
    private PacketSend ps;
    private int selectedNetMapIndex = -1;

    /**
     * Creates new form NetMap
     */
    public NetMap() {

        initComponents();
        translate();
    }

    /**
     * Add a new sent packet in the queue packets
     *
     * @param sentObject the packet sent
     * @param nick the nickname of the destination workstation
     */
    public void addSentPacket(Object sentObject, String nick) {
        sentPackets += 1;

        JLabel newSentPacket = new JLabel(nick);
        newSentPacket.setIcon(iconSend);

        newSentPacket.addMouseListener(new QueuePacketsMouseListener(jpSent, newSentPacket));
        jpSent.add(newSentPacket, sentPackets - 1);

        sentRow.put(sentPackets - 1, sentObject);

        jpSent.validate();
        jpSent.repaint();
        jspSent.setViewportView(jpSent);

        jtfSentTotal.setText(String.valueOf(sentPackets));
    }

    /**
     * Add a new received packet in the queue packets
     *
     * @param receivedObject the packet received
     * @param nick the nickname of the source workstation
     */
    public void addReceivedPacket(Object receivedObject, String nick) {

        receivedPackets += 1;

        Packet pk = (Packet) receivedObject;
        ImageIcon icon = pk.getIcon();

        JLabel newReceivedPacket = new JLabel(nick);
        newReceivedPacket.setIcon(icon);

        newReceivedPacket.addMouseListener(new QueuePacketsMouseListener(jpReceived, newReceivedPacket));
        jpReceived.add(newReceivedPacket, receivedPackets - 1);

        receivedRow.put(receivedPackets - 1, receivedObject);

        jpReceived.validate();
        jpReceived.repaint();
        jspReceived.setViewportView(jpReceived);

        jtfReceivedTotal.setText(String.valueOf(receivedPackets));
    }

    /**
     * Add a new workstation in the NetMap
     *
     * @param ip the IP addrss of the workstation
     * @param nickname the nickname of the workstation
     * @param nrMAC the MAC addrss of the workstation
     * @param port the socket port of the workstation
     *
     * @return true if successful in add a workstation or false if not
     */
    public boolean addWorkStation(String ip, String nickname, String nrMAC, int port) {

        //if not contains the workstation
        if (!workStations.containsKey(ip + ':' + port)) {
            URL url = getClass().getClassLoader().getResource("images/pc.png");
            ImageIcon iconWorkstation = new ImageIcon(url);

            //create a jLabel that represents the new workstation
            JLabel newWorkstation = new JLabel("<html><center>" + ip + "<br/>"
                    + nickname + "<br/>"
                    + Config.getInstance().getBundle().getString("NetMap.newStation.port") + " " + port + "</center>");

            newWorkstation.setIcon(iconWorkstation);
            newWorkstation.setVerticalTextPosition(SwingConstants.CENTER);
            newWorkstation.setHorizontalTextPosition(SwingConstants.RIGHT);
            newWorkstation.setHorizontalAlignment(SwingConstants.CENTER);
            newWorkstation.setVerticalAlignment(SwingConstants.CENTER);
            newWorkstation.addMouseListener(new NetMapMouseListener(jpNetMap, newWorkstation, this));

            jpNetMap.add(newWorkstation);
            jpNetMap.validate();

            WorkStation wks = new WorkStation(ip, nickname, nrMAC, port);
            workStations.put(ip + ':' + port, wks);

            dataArray.add(wks);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a workstation of the NetMap
     *
     * @param ip the IP addrss of the workstation
     * @param port the socket port of the workstation
     */
    public void removeWorkStation(String ip, int port) {

        WorkStation removedWorkstation = (WorkStation) workStations.remove(ip + ':' + port);
        int index = dataArray.indexOf(removedWorkstation);

        if (index >= 0) {
            jpNetMap.remove(index);
            jpNetMap.validate();
            jpNetMap.repaint();
            dataArray.remove(index);

            this.selectedNetMapIndex = -1;
        }
    }

    /**
     * Returns if the netmap contains the workstation
     *
     * @param ip the IP addrss of the workstation
     * @param port the socket port of the workstation
     *
     * @return true if the workstation exists or false if not
     */
    public boolean existWorkStation(String ip, int port) {
        if (workStations.containsKey(ip + ':' + port)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the reference to ARPTable class
     *
     * @param arpTable the ARPTable reference
     */
    public void setArpTable(ArpTable arpTable) {
        this.arpTable = arpTable;
    }

    /**
     * Sets the workstation with local settings
     *
     * @param ws the workstation with local settings
     */
    public void setMyWorkStation(WorkStation ws) {
        this.myWorkStiation = ws;
    }

    /**
     * Sets the index of the selected workstation in the net map
     *
     * @param index the of the workstation
     */
    public void setSelectedNetMapIndex(int index) {
        this.selectedNetMapIndex = index;
    }

    /**
     * Sets the socket port used by simulator
     *
     * @param port the port used by simulator
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Sets the IP address of the simulator
     *
     * @param ip the IP address of the simulator
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Mouselistener used by net map components
     */
    public class NetMapMouseListener extends MouseAdapter {

        private JLabel netMapLabel;
        private JPanel netMapPanel;
        private NetMap map;

        /**
         * Class contructor
         * 
         * @param panel the parent panel of the component 
         * @param label the label to listner
         * @param map a reference to NetMap class
         */
        public NetMapMouseListener(JPanel panel, JLabel label, NetMap map) {
            this.netMapLabel = label;
            this.netMapPanel = panel;
            this.map = map;
        }

        @Override
        public void mouseClicked(MouseEvent evt) {

            //get the selected JLabel
            Object source = evt.getSource();
            if (source instanceof JLabel) {
                this.netMapLabel = (JLabel) source;
            }

            Component[] components = this.netMapPanel.getComponents();
            int index = 0;

            for (Component c : components) {
                if (c instanceof JLabel) {

                    JLabel label = ((JLabel) c);
                    if (label.equals(this.netMapLabel)) {
                        //set border in JLabel and save the component index
                        this.netMapLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        this.map.setSelectedNetMapIndex(index);
                    } else {
                        //remove the border
                        label.setBorder(null);
                    }
                }
                index++;
            }
        }
    }

    /**
     * Mouselistener used by queue packets components
     */
    public class QueuePacketsMouseListener extends MouseAdapter {

        private JLabel queuePacketsLabel;
        private JPanel queuePacketsPanel;

        /**
         * Class contructor
         * 
         * @param panel the parent panel of the component 
         * @param label the label to listner
         */
        public QueuePacketsMouseListener(JPanel panel, JLabel label) {
            this.queuePacketsLabel = label;
            this.queuePacketsPanel = panel;
        }

        @Override
        public void mouseClicked(MouseEvent evt) {

            //if double click
            if ((evt.getClickCount() == 2)) {

                Component[] components = this.queuePacketsPanel.getComponents();
                for (Component c : components) {
                    if (((JLabel) c).getBorder() != null) {

                        Object object = null;
                        JLabel jl = (JLabel) c;
                        boolean received = true;
                        boolean send = false;

                        //if click in a received packet
                        if (jpReceived.getComponentZOrder(c) != -1) {
                            object = receivedRow.get(jpReceived.getComponentZOrder(c));
                            send = false;
                        }
                        
                        //if click in a sent packet
                        if (jpSent.getComponentZOrder(c) != -1) {
                            object = sentRow.get(jpSent.getComponentZOrder(c));
                            received = false;
                            send = true;
                        }

                        Packet pack = (Packet) object;
                        
                        //if is a received and unread packet change the icon 
                        if (received && pack.getStatus() == Packet.UNREAD) {
                            pack.setStatus(Packet.READ);
                            jl.setIcon(pack.getIcon());
                        }

                        Container pai = getParent();
                        while (!(pai instanceof Menu)) {
                            pai = pai.getParent();
                        }
                        
                        //create de packet view
                        ((Menu) pai).createPacketDetail(pack, send);
                    }
                }
            }
            
            //remove the border of the labels
            Component[] components = this.queuePacketsPanel.getComponents();
            for (Component c : components) {
                if (c instanceof JLabel) {
                    ((JLabel) c).setBorder(null);
                }
            }
            
            //add the border in the clicked label
            Object source = evt.getSource();
            if (source instanceof JLabel) {
                this.queuePacketsLabel = (JLabel) source;
                this.queuePacketsLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            }
        }
    }
    
     /**
     * Translate the Frame texts
     */
    public final void translate() {
        Config conf = Config.getInstance();
        ResourceBundle bundle = conf.getBundle();

        this.setTitle(bundle.getString("NetMap.title"));

        jbAdd.setText(bundle.getString("NetMap.jbAdd.text"));
        jbRemove.setText(bundle.getString("NetMap.jbRemove.text"));
        jbFindStations.setText(bundle.getString("NetMap.jbFindStations.text"));
        jbCreate.setText(bundle.getString("NetMap.jbCreate.text"));

        TitledBorder titledBorderMapaRede = javax.swing.BorderFactory.createTitledBorder(bundle.getString("NetMap.jpNetMap.title"));
        titledBorderMapaRede.setBorder(new LineBorder(Color.BLACK));
        jpNetMap.setBorder(titledBorderMapaRede);

        TitledBorder titledBorderPacketRow = javax.swing.BorderFactory.createTitledBorder(bundle.getString("NetMap.jpPacketRow.title"));
        titledBorderPacketRow.setBorder(new LineBorder(Color.BLACK));
        jpPacketRow.setBorder(titledBorderPacketRow);

        jlReceived.setText(bundle.getString("NetMap.jlReceived.text"));
        jlReceivedTotal.setText(bundle.getString("NetMap.jlReceivedTotal.text"));
        jlSent.setText(bundle.getString("NetMap.jlSent.text"));
        jlSentTotal.setText(bundle.getString("NetMap.jlSentTotal.text"));

        //translate net map components
        Component[] components = jpNetMap.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JLabel) {
                JLabel workstaion = (JLabel) components[i];

                String workstaionString = workstaion.getText();
                String[] vec = workstaionString.split("<br/>");

                String workstationPort = vec[2].substring(vec[2].indexOf(": ") + 1, vec[2].indexOf("</center>"));
                String newText = vec[0] + "<br/>" + vec[1] + "<br/>" + bundle.getString("NetMap.newStation.port") + " " + workstationPort + "</center>";
                workstaion.setText(newText);
            }
        }

        if (fs != null) {
            fs.translate();
        }

        if (ps != null) {
            ps.translate();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpNetMap = new javax.swing.JPanel();
        jbAdd = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();
        jpPacketRow = new javax.swing.JPanel();
        jlSent = new javax.swing.JLabel();
        jspSent = new javax.swing.JScrollPane();
        jpSent = new javax.swing.JPanel();
        jlReceived = new javax.swing.JLabel();
        jspReceived = new javax.swing.JScrollPane();
        jpReceived = new javax.swing.JPanel();
        jlSentTotal = new javax.swing.JLabel();
        jtfSentTotal = new javax.swing.JTextField();
        jlReceivedTotal = new javax.swing.JLabel();
        jtfReceivedTotal = new javax.swing.JTextField();
        jbCreate = new javax.swing.JButton();
        jbFindStations = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        setTitle(bundle.getString("NetMap.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpNetMap.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("NetMap.jpNetMap.title"))); // NOI18N
        jpNetMap.setMaximumSize(new java.awt.Dimension(0, 0));
        jpNetMap.setMinimumSize(new java.awt.Dimension(0, 0));
        jpNetMap.setPreferredSize(new java.awt.Dimension(0, 0));
        jpNetMap.setLayout(new java.awt.GridLayout(4, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.3;
        getContentPane().add(jpNetMap, gridBagConstraints);
        jpNetMap.getAccessibleContext().setAccessibleName(""); // NOI18N

        jbAdd.setText(bundle.getString("NetMap.jbAdd.text")); // NOI18N
        jbAdd.setMaximumSize(new java.awt.Dimension(0, 0));
        jbAdd.setMinimumSize(new java.awt.Dimension(0, 0));
        jbAdd.setPreferredSize(new java.awt.Dimension(0, 0));
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        getContentPane().add(jbAdd, gridBagConstraints);

        jbRemove.setText(bundle.getString("NetMap.jbRemove.text")); // NOI18N
        jbRemove.setMaximumSize(new java.awt.Dimension(0, 0));
        jbRemove.setMinimumSize(new java.awt.Dimension(0, 0));
        jbRemove.setPreferredSize(new java.awt.Dimension(0, 0));
        jbRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jbRemove, gridBagConstraints);

        jpPacketRow.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("NetMap.jpPacketRow.title"))); // NOI18N
        jpPacketRow.setMaximumSize(new java.awt.Dimension(0, 0));
        jpPacketRow.setMinimumSize(new java.awt.Dimension(0, 0));
        jpPacketRow.setPreferredSize(new java.awt.Dimension(0, 0));
        jpPacketRow.setLayout(new java.awt.GridBagLayout());

        jlSent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlSent.setText(bundle.getString("NetMap.jlSent.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jpPacketRow.add(jlSent, gridBagConstraints);

        jspSent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jpSent.setLayout(new javax.swing.BoxLayout(jpSent, javax.swing.BoxLayout.LINE_AXIS));
        jspSent.setViewportView(jpSent);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.8;
        jpPacketRow.add(jspSent, gridBagConstraints);

        jlReceived.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlReceived.setText(bundle.getString("NetMap.jlReceived.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jpPacketRow.add(jlReceived, gridBagConstraints);

        jspReceived.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jpReceived.setLayout(new javax.swing.BoxLayout(jpReceived, javax.swing.BoxLayout.LINE_AXIS));
        jspReceived.setViewportView(jpReceived);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.8;
        jpPacketRow.add(jspReceived, gridBagConstraints);

        jlSentTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlSentTotal.setText(bundle.getString("NetMap.jlSentTotal.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jpPacketRow.add(jlSentTotal, gridBagConstraints);

        jtfSentTotal.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jpPacketRow.add(jtfSentTotal, gridBagConstraints);

        jlReceivedTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlReceivedTotal.setText(bundle.getString("NetMap.jlReceivedTotal.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 5, 0);
        jpPacketRow.add(jlReceivedTotal, gridBagConstraints);

        jtfReceivedTotal.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 5, 0);
        jpPacketRow.add(jtfReceivedTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        getContentPane().add(jpPacketRow, gridBagConstraints);

        jbCreate.setText(bundle.getString("NetMap.jbCreate.text")); // NOI18N
        jbCreate.setMaximumSize(new java.awt.Dimension(0, 0));
        jbCreate.setMinimumSize(new java.awt.Dimension(0, 0));
        jbCreate.setPreferredSize(new java.awt.Dimension(0, 0));
        jbCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jbCreate, gridBagConstraints);

        jbFindStations.setText(bundle.getString("NetMap.jbFindStations.text")); // NOI18N
        jbFindStations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findNewStations(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jbFindStations, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed

        AddPC newPC = new AddPC(this);
        newPC.setPort(this.port);
        newPC.setIp(this.ip);
        newPC.setVisible(true);

    }//GEN-LAST:event_jbAddActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveActionPerformed

        int index = this.selectedNetMapIndex;

        if (index >= 0) {
            WorkStation removedWorkStation = dataArray.get(index);
            String removedWorkStationIP = removedWorkStation.getIP();
            int removedWorkStationIPort = removedWorkStation.getPort();
            
            //send a message warning the remote workstation
            ConnectionSend envio = new ConnectionSend(ip, (Object) "removeWorkStation=" + this.ip + ";" + this.port, port);

            try {
                envio.start();
            } catch (Exception e) {
                SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("Netmap.message.cantRemove"), SimulatorNotification.MESSAGE_ERROR);
                not.setLocationRelativeTo(this);
                not.showNotification();
            }
            
            //remove the workstaion of the netmap
            removeWorkStation(removedWorkStationIP, removedWorkStationIPort);
            //remove the workstaion of the ARP table
            arpTable.removeWorkStation(removedWorkStationIP, removedWorkStationIPort);
        }
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void jbCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreateActionPerformed

        //create a new packet
        if (workStations.size() > 0) {
            try {
                ps = new PacketSend(jpNetMap, this.workStations);
                ps.setLocationRelativeTo(this);
                ps.setVisible(true);
            } catch (InstantiationException ex) {
                Logger.getLogger(NetMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("Netmap.message.addStaton"), SimulatorNotification.MESSAGE_ERROR);
            not.setLocationRelativeTo(this);
            not.showNotification();
        }

    }//GEN-LAST:event_jbCreateActionPerformed

    private void findNewStations(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findNewStations
        // TODO add your handling code here:
        fs = new FindStation();
        fs.setWorkStation(this.myWorkStiation);
        fs.setLocationRelativeTo(this);
        fs.startThread();
        fs.setVisible(true);
    }//GEN-LAST:event_findNewStations
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbCreate;
    private javax.swing.JButton jbFindStations;
    private javax.swing.JButton jbRemove;
    private javax.swing.JLabel jlReceived;
    private javax.swing.JLabel jlReceivedTotal;
    private javax.swing.JLabel jlSent;
    private javax.swing.JLabel jlSentTotal;
    private javax.swing.JPanel jpNetMap;
    private javax.swing.JPanel jpPacketRow;
    private javax.swing.JPanel jpReceived;
    private javax.swing.JPanel jpSent;
    private javax.swing.JScrollPane jspReceived;
    private javax.swing.JScrollPane jspSent;
    private javax.swing.JTextField jtfReceivedTotal;
    private javax.swing.JTextField jtfSentTotal;
    // End of variables declaration//GEN-END:variables
}
