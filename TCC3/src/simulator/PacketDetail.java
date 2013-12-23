/*
 * PacketDetail.java
 *
 * Created on 14 de Outubro de 2009, 21:00
 */
package simulator;

import java.awt.Container;
import java.util.ResourceBundle;

/**
 *
 * @author Diego Muller, Cristiano Poletti
 */
public class PacketDetail extends javax.swing.JInternalFrame {

    private PanelApplication pApplication;
    private PanelTransport pTransport;
    private PanelInternet pInternet;
    private PanelLink pLink;
    private boolean send;
    private boolean dropped = false;
    private DatagramApplication dApplication;
    private DatagramTransport dTransport;
    private DatagramInternet dInternet;
    private DatagramLink dLink;
    private String ipDestination;
    private int portDestination = 7000;
    private WorkStation destinationWorkstation;

    /**
     * Creates new form PacketDetail
     * 
     * @param pk the packet to build
     * @param send if true is a sent packet else is a received packet
     */
    public PacketDetail(Packet pk, boolean send) {

        this.send = send;
        this.dLink = pk.getDatagramLink();
        this.dInternet = pk.getDatagramInternet();
        this.dTransport = pk.getDatagramTransport();
        this.dApplication = pk.getDatagramApplication();

        WorkStation destination;
        //if is a sent packet the destination is the Destination WorkStation
        if (send) {
            destination = pk.getDestinationWorkStation();
        } else {
            //else defines the destination as the source workstation, to respond later
            destination = pk.getSourceWorkStation();
        }
        
        destinationWorkstation = destination;
        this.ipDestination = destination.getIP();
        this.portDestination = destination.getPort();

        if (pk.getStatus() == Packet.DROPPED) {
            this.dropped = true;
        }
        initComponents();

        translate();
    }
    
    /**
     * create the packet layout
     */
    public void createPacket() {
        
        //enable edition if is a send packet
        boolean enableComponent = this.send;
        
        jbPacketSend.setVisible(enableComponent);
        //enable answer button if is a received and not dropped packet
        jbPacketAnswer.setVisible(!this.send && !this.dropped);
        String linkTab = this.dLink.datagramLinkType();

        try {
            //get the datagram application
            if (this.dApplication != null) {
                //get the protocol name
                String applicationTab = this.dApplication.datagramApplicationType();
                
                //try instantiate the panel class
                pApplication = (PanelApplication) Class.forName("simulator.PanelApplication" + applicationTab).newInstance();

                jtpDetail.addTab(applicationTab, pApplication);
                pApplication.setEditable(enableComponent);
                jtpDetail.setSelectedComponent(pApplication);
                pApplication.setPanelFields(this.dApplication);
            }

            if (this.dTransport != null) {
                String transportTab = this.dTransport.datagramTransportType();
                pTransport = (PanelTransport) Class.forName("simulator.PanelTransport" + transportTab).newInstance();
                
                jtpDetail.addTab(transportTab, pTransport);
                pTransport.setEditable(enableComponent);
                jtpDetail.setSelectedComponent(pTransport);
                pTransport.setPanelFields(this.dTransport);
            }

            if (this.dInternet != null) {
                String internetTab = this.dInternet.datagramInternetType();
                pInternet = (PanelInternet) Class.forName("simulator.PanelInternet" + internetTab).newInstance();
                
                jtpDetail.addTab(internetTab, pInternet);
                pInternet.setEditable(enableComponent);
                jtpDetail.setSelectedComponent(pInternet);
                pInternet.setPanelFields(this.dInternet);
            }
            
            pLink = (PanelLink) Class.forName("simulator.PanelLink" + linkTab).newInstance();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }

        jtpDetail.addTab(linkTab, pLink);
        jtpDetail.setSelectedComponent(pLink);
        pLink.setEditable(enableComponent);
        pLink.setPanelFields(this.dLink);
    }
    
    /**
     * Select the previous tab
     */
    public void setPreviousTab() {
        jtpDetail.setSelectedIndex(jtpDetail.getSelectedIndex() - 1);
    }

    /**
     * Enable the next tab
     */
    public void enableNextTab() {
        jtpDetail.setEnabledAt(jtpDetail.getSelectedIndex() - 1, true);
    }
    
    /**
     * Disable the next tab
     */
    public void disableNextTab() {
        jtpDetail.setEnabledAt(jtpDetail.getSelectedIndex() - 1, false);
    }
    
    /**
     * Sets the datagram application
     * 
     * @param da the datagram application
     */
    public void setDatagramApplication(DatagramApplication da) {
        this.dApplication = da;
    }

    /**
     * Sets the datagram transport
     * 
     * @param dt the datagram transport
     */
    public void setDatagramTransport(DatagramTransport dt) {
        this.dTransport = dt;
    }
    
     /**
     * Sets the datagram internet
     * 
     * @param di the datagram internet
     */
    public void setDatagramInternet(DatagramInternet di) {
        this.dInternet = di;
    }
    
     /**
     * Sets the datagram link
     * 
     * @param dl the datagram link
     */
    public void setDatagramLink(DatagramLink dl) {
        this.dLink = dl;
    }
    
    /**
     * Returns the name of the connection state in the transport layer
     * 
     * @param destinationPort the destination port
     * @param sourcePort the source port
     * 
     * @return the name of the state of the connection in the source port
     */
    public String getStatePort(int sourcePort, int destinationPort) {
        Container parent = this.getParent();
        while (!(parent instanceof Menu)) {
            parent = parent.getParent();
        }

        return ((Menu) parent).getStatePort(sourcePort, destinationWorkstation, destinationPort);
    }
    
    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();

        this.setTitle(bundle.getString("PacketDetail.title"));
        jbPacketAnswer.setText(bundle.getString("PacketDetail.jbPacketAnswer.text"));
        jbPacketSend.setText(bundle.getString("PacketDetail.jbPacketSend.text"));

        if (pLink != null) {
            pLink.translate();
        }

        if (pInternet != null) {
            pInternet.translate();
        }

        if (pTransport != null) {
            pTransport.translate();
        }
        
        if (pApplication != null) {
            pApplication.translate();
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

        jtpDetail = new javax.swing.JTabbedPane();
        jbPacketSend = new javax.swing.JButton();
        jbPacketAnswer = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        setTitle(bundle.getString("PacketDetail.title")); // NOI18N

        jtpDetail.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jtpDetail.setMinimumSize(new java.awt.Dimension(0, 0));

        jbPacketSend.setText(bundle.getString("PacketDetail.jbPacketSend.text")); // NOI18N
        jbPacketSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPacketSendActionPerformed(evt);
            }
        });

        jbPacketAnswer.setText(bundle.getString("PacketDetail.jbPacketAnswer.text")); // NOI18N
        jbPacketAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPacketAnswerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbPacketSend)
                    .addComponent(jbPacketAnswer, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 405, Short.MAX_VALUE)
                        .addComponent(jbPacketAnswer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPacketSend))
                    .addComponent(jtpDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//send the packet to destination
private void jbPacketSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPacketSendActionPerformed

    //build the packet
    if (pApplication != null) {
        this.pApplication.setDatagram(dApplication);
        this.dTransport.encapsulateData(this.dApplication);
    }

    if (pTransport != null) {
        this.pTransport.setDatagram(dTransport);
        this.dInternet.encapsulateData(this.dTransport);
    }

    if (pInternet != null) {
        this.pInternet.setDatagram(dInternet);
        this.dLink.encapsulateData(this.dInternet);
    }
    this.pLink.setDatagram(dLink);

    //create a workstation with local settings
    Config conf = Config.getInstance();
    WorkStation sourceWorkstation = new WorkStation(conf.getIp(), conf.getNick(), conf.getMac(), conf.getPort());
    
    //create a new packet
    Packet pack = new Packet(dLink, sourceWorkstation, destinationWorkstation);

    Container pai = this.getParent();
    while (!(pai instanceof Menu)) {
        pai = pai.getParent();
    }

    //validate packet and try send it
    try {
        ((Menu) pai).validateSentPacket(pack);
        ConnectionSend sendPacket = new ConnectionSend(this.ipDestination, (Object) pack, this.portDestination);
        sendPacket.start();
        
        //show success message
        SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("PacketDetail.sendMessage"));
        not.setLocationRelativeTo(this);
        not.showNotification();
        this.dispose();
    } catch (Exception e) {
        
        //show error message
        SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("PacketDetail.errorMessage"),SimulatorNotification.MESSAGE_ERROR);
        not.setLocationRelativeTo(this);
        not.showNotification();
    }
}//GEN-LAST:event_jbPacketSendActionPerformed

//create a answer packet
    private void jbPacketAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPacketAnswerActionPerformed
        // TODO add your handling code here:
        
        //clear all panel components 
        jtpDetail.removeAll();

        //create the answer packet
        String LinkTab = this.dLink.datagramLinkType();
        this.dLink = this.dLink.getAnswer();
        
        try {
            if (this.dApplication != null) {
                this.dApplication = this.dApplication.getAnswer();
                String applicationTab = this.dApplication.datagramApplicationType();
                pApplication = (PanelApplication) Class.forName("simulator.PanelApplication" + applicationTab).newInstance();
                jtpDetail.addTab(applicationTab, pApplication);
                jtpDetail.setSelectedComponent(pApplication);
                pApplication.setPanelFields(this.dApplication);
                pApplication.setEditable(true);
            }

            if (this.dTransport != null) {
                this.dTransport = this.dTransport.getAnswer();
                String transportTab = this.dTransport.datagramTransportType();
                pTransport = (PanelTransport) Class.forName("simulator.PanelTransport" + transportTab).newInstance();
                jtpDetail.addTab(transportTab, pTransport);
                jtpDetail.setSelectedComponent(pTransport);
                pTransport.setEditable(true);
                pTransport.setPanelFields(this.dTransport);
            }

            if (this.dInternet != null) {
                this.dInternet = this.dInternet.getAnswer();
                String internetTab = this.dInternet.datagramInternetType();
                pInternet = (PanelInternet) Class.forName("simulator.PanelInternet" + internetTab).newInstance();
                jtpDetail.addTab(internetTab, pInternet);
                jtpDetail.setSelectedComponent(pInternet);
                pInternet.setPanelFields(this.dInternet);
                pInternet.setEditable(true);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }

        jtpDetail.addTab(LinkTab, pLink);
        jtpDetail.setSelectedComponent(pLink);
        pLink.setPanelFields(this.dLink);
        pLink.setEditable(true);
        
        //show send button
        jbPacketSend.setVisible(true);
        
        //hide answer button
        jbPacketAnswer.setVisible(false);
    }//GEN-LAST:event_jbPacketAnswerActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbPacketAnswer;
    private javax.swing.JButton jbPacketSend;
    private javax.swing.JTabbedPane jtpDetail;
    // End of variables declaration//GEN-END:variables
}
