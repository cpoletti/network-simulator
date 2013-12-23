/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PainelTCP.java
 *
 * Created on 30/08/2010, 19:25:43
 */
package simulator;

import datagram.DatagramTransportUDP;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego Muller
 */
public class PanelTransportUDP extends PanelTransport {

    /**
     * Creates new form PainelTCP
     */
    public PanelTransportUDP() {
        initComponents();

        jtfSourcePort.setVisible(false);
        jtfDestinationPort.setVisible(false);
        jtfMessageLenght.setVisible(false);
        jtfChecksum.setVisible(false);

        translate();
    }

    @Override
    public void setDatagram(DatagramTransport pacoteUDP) {
        ((DatagramTransportUDP) pacoteUDP).setSourcePort(this.getSourcePort());
        ((DatagramTransportUDP) pacoteUDP).setDestinationPort(this.getDestinationPort());
        ((DatagramTransportUDP) pacoteUDP).setMessageLength(this.getMessageLenght());
        ((DatagramTransportUDP) pacoteUDP).setChecksum(this.getChecksum());
        ((DatagramTransportUDP) pacoteUDP).setData(this.getData());
    }

    @Override
    public void setPanelFields(DatagramTransport pacoteUDP) {
        this.setSourcePort(((DatagramTransportUDP) pacoteUDP).getSourcePort());
        this.setDestinationPort(((DatagramTransportUDP) pacoteUDP).getDestinationPort());
        this.setMessageLenght(((DatagramTransportUDP) pacoteUDP).getMessageLength());
        this.setChecksum(((DatagramTransportUDP) pacoteUDP).getChecksum());
        this.setData(((DatagramTransportUDP) pacoteUDP).getData());
    }

    @Override
    public void setEditable(boolean edit) {
        jbDestinationPort.setEnabled(edit);
        jbSourcePort.setEnabled(edit);
        jbData.setEnabled(edit);
    }

    /**
     * Return the Source Port value
     *
     * @return the Source Port value
     */
    public int getSourcePort() {
        return Integer.parseInt(jtfSourcePort.getText());
    }

    /**
     * Return the Destination Port value
     *
     * @return the Destination Port value
     */
    public int getDestinationPort() {
        return Integer.parseInt(jtfDestinationPort.getText());
    }

    /**
     * Return the Message Lenght value
     *
     * @return the Message Lenght value
     */
    public int getMessageLenght() {
        return Integer.parseInt(jtfMessageLenght.getText());
    }

    /**
     * Return the Checksum value
     *
     * @return the Checksum value
     */
    public int getChecksum() {
        return Integer.parseInt(jtfChecksum.getText());
    }

    /**
     * Return the Data value
     *
     * @return the Data value
     */
    public Object getData() {
        return jtaData.getText();
    }

    /**
     * Sets the Source Port value
     *
     * @param sourcePort the Source Port value
     */
    public void setSourcePort(int sourcePort) {
        jtfSourcePort.setText(String.valueOf(sourcePort));
        updateSourcePortButton();
    }

    /**
     * Sets the Destination Port value
     *
     * @param destinationPort the Destination Port value
     */
    public void setDestinationPort(int destinationPort) {
        jtfDestinationPort.setText(String.valueOf(destinationPort));
        updateDestinationPortButton();
    }

    /**
     * Sets the Message Lenght value
     *
     * @param lenght the Message Lenght value
     */
    public void setMessageLenght(int lenght) {
        jtfMessageLenght.setText(String.valueOf(lenght));
        updateMessageLenghtButton();
    }

    /**
     * Sets the Checksum value
     *
     * @param checksum the Checksum value
     */
    public void setChecksum(int checksum) {
        jtfChecksum.setText(String.valueOf(checksum));
        updateChecksumButton();
    }

    /**
     * Sets the data value
     *
     * @param data the data value
     */
    public void setData(Object data) {
        this.jtaData.setText((String) data);
        updateDataButton();
    }

    /**
     * Change the Data button text
     */
    private void updateDataButton() {
        if (jtaData.getText().trim().equals("")) {
            jbData.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbData.text"));
        } else {
            jbData.setText("<html>" + Config.getInstance().getBundle().getString("PanelTransportUDP.jbData.text") + ":<br>" + jtaData.getText().replace("\n", "<br>") + "</html>");
        }
        jbData.setVisible(true);
        jtaData.setVisible(false);
    }

    /**
     * Change the Source Port button text
     */
    private void updateSourcePortButton() {
        if (jtfSourcePort.getText().trim().equals("")) {
            jbSourcePort.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbSourcePort.text"));
        } else {
            jbSourcePort.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbSourcePort.text") + ": " + jtfSourcePort.getText());
        }
        jbSourcePort.setVisible(true);
        jtfSourcePort.setVisible(false);
    }

    /**
     * Change the Destination Port button text
     */
    private void updateDestinationPortButton() {
        if (jtfDestinationPort.getText().trim().equals("")) {
            jbDestinationPort.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbDestinationPort.text"));
        } else {
            jbDestinationPort.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbDestinationPort.text") + ": " + jtfDestinationPort.getText());
        }
        jbDestinationPort.setVisible(true);
        jtfDestinationPort.setVisible(false);
    }

    /**
     * Change the Message Lenght button text
     */
    private void updateMessageLenghtButton() {
        if (jtfMessageLenght.getText().trim().equals("")) {
            jbMessageLenght.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbMessageLenght.text"));
        } else {
            jbMessageLenght.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbMessageLenght.text") + ": " + jtfMessageLenght.getText());
        }
        jbMessageLenght.setVisible(true);
        jtfMessageLenght.setVisible(false);
    }

    /**
     * Change the Checksum button text
     */
    private void updateChecksumButton() {
        if (jtfChecksum.getText().trim().equals("")) {
            jbChecksum.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbChecksum.text"));
        } else {
            jbChecksum.setText(Config.getInstance().getBundle().getString("PanelTransportUDP.jbChecksum.text") + ": " + jtfChecksum.getText());
        }
        jbChecksum.setVisible(true);
        jtfChecksum.setVisible(false);
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();
        updateDestinationPortButton();
        updateSourcePortButton();
        updateChecksumButton();
        updateMessageLenghtButton();
        updateDataButton();

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelTransportUDP.jspProtocolDescription.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK, 1, true));
        jspProtocolDescription.setBorder(titledBorder);
        jepProtocolDescription.setText(bundle.getString("PanelTransportUDP.jepProtocolDescription.text"));

        jbDestinationPort.setToolTipText(bundle.getString("PanelTransportUDP.jbDestinationPort.tooltip"));
        jbSourcePort.setToolTipText(bundle.getString("PanelTransportUDP.jbSourcePort.tooltip"));
        jbMessageLenght.setToolTipText(bundle.getString("PanelTransportUDP.jbMessageLenght.tooltip"));
        jbChecksum.setToolTipText(bundle.getString("PanelTransportUDP.jbChecksum.tooltip"));
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

        jbSourcePort = new simulator.ToolTipButton();
        jtfSourcePort = new javax.swing.JTextField();
        jbDestinationPort = new simulator.ToolTipButton();
        jtfDestinationPort = new javax.swing.JTextField();
        jbMessageLenght = new simulator.ToolTipButton();
        jtfMessageLenght = new javax.swing.JTextField();
        jbChecksum = new simulator.ToolTipButton();
        jtfChecksum = new javax.swing.JTextField();
        jlBitOffset = new javax.swing.JLabel();
        jlBi0_1 = new javax.swing.JLabel();
        jlBit1 = new javax.swing.JLabel();
        jlBit2 = new javax.swing.JLabel();
        jlBit3 = new javax.swing.JLabel();
        jlBit4 = new javax.swing.JLabel();
        jlBit5 = new javax.swing.JLabel();
        jlBit6 = new javax.swing.JLabel();
        jlBit7 = new javax.swing.JLabel();
        jlBit8 = new javax.swing.JLabel();
        jlBit9 = new javax.swing.JLabel();
        jlBit10 = new javax.swing.JLabel();
        jlBit11 = new javax.swing.JLabel();
        jlBit12 = new javax.swing.JLabel();
        jlBit13 = new javax.swing.JLabel();
        jlBit14 = new javax.swing.JLabel();
        jlBit15 = new javax.swing.JLabel();
        jlBit16 = new javax.swing.JLabel();
        jlBit17 = new javax.swing.JLabel();
        jlBit18 = new javax.swing.JLabel();
        jlBit19 = new javax.swing.JLabel();
        jlBit20 = new javax.swing.JLabel();
        jlBit21 = new javax.swing.JLabel();
        jlBit22 = new javax.swing.JLabel();
        jlBit23 = new javax.swing.JLabel();
        jlbit24 = new javax.swing.JLabel();
        jlBit25 = new javax.swing.JLabel();
        jlBit26 = new javax.swing.JLabel();
        jlBit27 = new javax.swing.JLabel();
        jlBit28 = new javax.swing.JLabel();
        jlBit29 = new javax.swing.JLabel();
        jlBit30 = new javax.swing.JLabel();
        jlBit31 = new javax.swing.JLabel();
        jlBit0_2 = new javax.swing.JLabel();
        jlBit32 = new javax.swing.JLabel();
        jbData = new simulator.ToolTipButton();
        jspProtocolDescription = new javax.swing.JScrollPane();
        jepProtocolDescription = new javax.swing.JEditorPane();
        jspData = new javax.swing.JScrollPane();
        jtaData = new javax.swing.JTextPane();

        setBackground(backgroundColorTransport);
        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        jbSourcePort.setText(bundle.getString("PanelTransportUDP.jbSourcePort.text")); // NOI18N
        jbSourcePort.setToolTipText(bundle.getString("PanelTransportUDP.jbSourcePort.tooltip")); // NOI18N
        jbSourcePort.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSourcePort.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSourcePort.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSourcePort.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSourcePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSourcePortActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.weighty = 0.125;
        add(jbSourcePort, gridBagConstraints);

        jtfSourcePort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSourcePort.setText("0");
        jtfSourcePort.setAlignmentX(0.0F);
        jtfSourcePort.setAlignmentY(0.0F);
        jtfSourcePort.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfSourcePort.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfSourcePort.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfSourcePort.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfSourcePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSourcePortActionPerformed(evt);
            }
        });
        jtfSourcePort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSourcePortFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfSourcePort, gridBagConstraints);

        jbDestinationPort.setText(bundle.getString("PanelTransportUDP.jbDestinationPort.text")); // NOI18N
        jbDestinationPort.setToolTipText(bundle.getString("PanelTransportUDP.jbDestinationPort.tooltip")); // NOI18N
        jbDestinationPort.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbDestinationPort.setMaximumSize(new java.awt.Dimension(0, 0));
        jbDestinationPort.setMinimumSize(new java.awt.Dimension(0, 0));
        jbDestinationPort.setPreferredSize(new java.awt.Dimension(0, 0));
        jbDestinationPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDestinationPortActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.weighty = 0.125;
        add(jbDestinationPort, gridBagConstraints);

        jtfDestinationPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDestinationPort.setText("0");
        jtfDestinationPort.setAlignmentX(0.0F);
        jtfDestinationPort.setAlignmentY(0.0F);
        jtfDestinationPort.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfDestinationPort.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfDestinationPort.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfDestinationPort.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfDestinationPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDestinationPortActionPerformed(evt);
            }
        });
        jtfDestinationPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDestinationPortFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfDestinationPort, gridBagConstraints);

        jbMessageLenght.setText(bundle.getString("PanelTransportUDP.jbMessageLenght.text")); // NOI18N
        jbMessageLenght.setToolTipText(bundle.getString("PanelTransportUDP.jbMessageLenght.tooltip")); // NOI18N
        jbMessageLenght.setEnabled(false);
        jbMessageLenght.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbMessageLenght.setMaximumSize(new java.awt.Dimension(0, 0));
        jbMessageLenght.setMinimumSize(new java.awt.Dimension(0, 0));
        jbMessageLenght.setPreferredSize(new java.awt.Dimension(0, 0));
        jbMessageLenght.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMessageLenghtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jbMessageLenght, gridBagConstraints);

        jtfMessageLenght.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfMessageLenght.setText("0");
        jtfMessageLenght.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfMessageLenght.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfMessageLenght.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfMessageLenght.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfMessageLenght.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfMessageLenghtActionPerformed(evt);
            }
        });
        jtfMessageLenght.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfMessageLenghtFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jtfMessageLenght, gridBagConstraints);

        jbChecksum.setText(bundle.getString("PanelTransportUDP.jbChecksum.text")); // NOI18N
        jbChecksum.setToolTipText(bundle.getString("PanelTransportUDP.jbChecksum.tooltip")); // NOI18N
        jbChecksum.setEnabled(false);
        jbChecksum.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbChecksum.setMaximumSize(new java.awt.Dimension(0, 0));
        jbChecksum.setMinimumSize(new java.awt.Dimension(0, 0));
        jbChecksum.setPreferredSize(new java.awt.Dimension(0, 0));
        jbChecksum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChecksumActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jbChecksum, gridBagConstraints);

        jtfChecksum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfChecksum.setText("0");
        jtfChecksum.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfChecksum.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfChecksum.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfChecksum.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfChecksum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfChecksumActionPerformed(evt);
            }
        });
        jtfChecksum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfChecksumFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jtfChecksum, gridBagConstraints);

        jlBitOffset.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBitOffset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBitOffset.setText("Bit Offset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.0625;
        add(jlBitOffset, gridBagConstraints);

        jlBi0_1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBi0_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBi0_1.setText("00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBi0_1, gridBagConstraints);

        jlBit1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit1.setText("01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit1, gridBagConstraints);

        jlBit2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit2.setText("02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit2, gridBagConstraints);

        jlBit3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit3.setText("03");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit3, gridBagConstraints);

        jlBit4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit4.setText("04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit4, gridBagConstraints);

        jlBit5.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit5.setText("05");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit5, gridBagConstraints);

        jlBit6.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit6.setText("06");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit6, gridBagConstraints);

        jlBit7.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit7.setText("07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit7, gridBagConstraints);

        jlBit8.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit8.setText("08");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit8, gridBagConstraints);

        jlBit9.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit9.setText("09");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit9, gridBagConstraints);

        jlBit10.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit10.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit10, gridBagConstraints);

        jlBit11.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit11.setText("11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit11, gridBagConstraints);

        jlBit12.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit12.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit12, gridBagConstraints);

        jlBit13.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit13.setText("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit13, gridBagConstraints);

        jlBit14.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit14.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit14, gridBagConstraints);

        jlBit15.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit15.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit15, gridBagConstraints);

        jlBit16.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit16.setText("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit16, gridBagConstraints);

        jlBit17.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit17.setText("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit17, gridBagConstraints);

        jlBit18.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit18.setText("18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit18, gridBagConstraints);

        jlBit19.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit19.setText("19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit19, gridBagConstraints);

        jlBit20.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit20.setText("20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit20, gridBagConstraints);

        jlBit21.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit21.setText("21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit21, gridBagConstraints);

        jlBit22.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit22.setText("22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit22, gridBagConstraints);

        jlBit23.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit23.setText("23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit23, gridBagConstraints);

        jlbit24.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlbit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbit24.setText("24");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlbit24, gridBagConstraints);

        jlBit25.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit25.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit25, gridBagConstraints);

        jlBit26.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit26.setText("26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit26, gridBagConstraints);

        jlBit27.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit27.setText("27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit27, gridBagConstraints);

        jlBit28.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit28.setText("28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit28, gridBagConstraints);

        jlBit29.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit29.setText("29");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit29, gridBagConstraints);

        jlBit30.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit30.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit30, gridBagConstraints);

        jlBit31.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit31.setText("31");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.0625;
        add(jlBit31, gridBagConstraints);

        jlBit0_2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit0_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit0_2.setText("0");
        jlBit0_2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.35;
        add(jlBit0_2, gridBagConstraints);

        jlBit32.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit32.setText("32");
        jlBit32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.35;
        add(jlBit32, gridBagConstraints);

        jbData.setText(bundle.getString("PanelTransportUDP.jbData.text")); // NOI18N
        jbData.setActionCommand("");
        jbData.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbData.setMaximumSize(new java.awt.Dimension(0, 0));
        jbData.setMinimumSize(new java.awt.Dimension(0, 0));
        jbData.setPreferredSize(new java.awt.Dimension(0, 0));
        jbData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDataActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.2;
        add(jbData, gridBagConstraints);

        jspProtocolDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), bundle.getString("PanelTransportUDP.jspProtocolDescription.title"))); // NOI18N

        jepProtocolDescription.setEditable(false);
        jspProtocolDescription.setViewportView(jepProtocolDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 33;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jspProtocolDescription, gridBagConstraints);

        jtaData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtaDataLostFocus(evt);
            }
        });
        jspData.setViewportView(jtaData);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.2;
        add(jspData, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSourcePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSourcePortActionPerformed

        jtfSourcePort.setVisible(true);
        jbSourcePort.setVisible(false);
        jtfSourcePort.requestFocus();
}//GEN-LAST:event_jbSourcePortActionPerformed

    private void jtfSourcePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSourcePortActionPerformed
        updateSourcePortButton();
}//GEN-LAST:event_jtfSourcePortActionPerformed

    private void jbDestinationPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinationPortActionPerformed
        jtfDestinationPort.setVisible(true);
        jbDestinationPort.setVisible(false);
        jtfDestinationPort.requestFocus();
}//GEN-LAST:event_jbDestinationPortActionPerformed

    private void jtfDestinationPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDestinationPortActionPerformed
        updateDestinationPortButton();
}//GEN-LAST:event_jtfDestinationPortActionPerformed

    private void jbMessageLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMessageLenghtActionPerformed
        jtfMessageLenght.setVisible(true);
        jbMessageLenght.setVisible(false);
        jtfMessageLenght.requestFocus();
}//GEN-LAST:event_jbMessageLenghtActionPerformed

    private void jtfMessageLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfMessageLenghtActionPerformed
        updateMessageLenghtButton();
}//GEN-LAST:event_jtfMessageLenghtActionPerformed

    private void jbChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChecksumActionPerformed
        jtfChecksum.setVisible(true);
        jbChecksum.setVisible(false);
        jtfChecksum.requestFocus();
}//GEN-LAST:event_jbChecksumActionPerformed

    private void jtfChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfChecksumActionPerformed
        updateChecksumButton();
}//GEN-LAST:event_jtfChecksumActionPerformed

    private void jtfSourcePortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSourcePortFocusLost
        updateSourcePortButton();
    }//GEN-LAST:event_jtfSourcePortFocusLost

    private void jtfDestinationPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationPortFocusLost
        updateDestinationPortButton();
    }//GEN-LAST:event_jtfDestinationPortFocusLost

    private void jtfMessageLenghtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfMessageLenghtFocusLost
        updateMessageLenghtButton();
    }//GEN-LAST:event_jtfMessageLenghtFocusLost

    private void jtfChecksumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfChecksumFocusLost
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumFocusLost

    private void jbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataActionPerformed
        jtaData.setVisible(true);
        jbData.setVisible(false);
        jtaData.requestFocus();
}//GEN-LAST:event_jbDataActionPerformed

    private void jtaDataLostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaDataLostFocus
        // TODO add your handling code here:
        updateDataButton();
    }//GEN-LAST:event_jtaDataLostFocus
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private simulator.ToolTipButton jbChecksum;
    private simulator.ToolTipButton jbData;
    private simulator.ToolTipButton jbDestinationPort;
    private simulator.ToolTipButton jbMessageLenght;
    private simulator.ToolTipButton jbSourcePort;
    private javax.swing.JEditorPane jepProtocolDescription;
    private javax.swing.JLabel jlBi0_1;
    private javax.swing.JLabel jlBit0_2;
    private javax.swing.JLabel jlBit1;
    private javax.swing.JLabel jlBit10;
    private javax.swing.JLabel jlBit11;
    private javax.swing.JLabel jlBit12;
    private javax.swing.JLabel jlBit13;
    private javax.swing.JLabel jlBit14;
    private javax.swing.JLabel jlBit15;
    private javax.swing.JLabel jlBit16;
    private javax.swing.JLabel jlBit17;
    private javax.swing.JLabel jlBit18;
    private javax.swing.JLabel jlBit19;
    private javax.swing.JLabel jlBit2;
    private javax.swing.JLabel jlBit20;
    private javax.swing.JLabel jlBit21;
    private javax.swing.JLabel jlBit22;
    private javax.swing.JLabel jlBit23;
    private javax.swing.JLabel jlBit25;
    private javax.swing.JLabel jlBit26;
    private javax.swing.JLabel jlBit27;
    private javax.swing.JLabel jlBit28;
    private javax.swing.JLabel jlBit29;
    private javax.swing.JLabel jlBit3;
    private javax.swing.JLabel jlBit30;
    private javax.swing.JLabel jlBit31;
    private javax.swing.JLabel jlBit32;
    private javax.swing.JLabel jlBit4;
    private javax.swing.JLabel jlBit5;
    private javax.swing.JLabel jlBit6;
    private javax.swing.JLabel jlBit7;
    private javax.swing.JLabel jlBit8;
    private javax.swing.JLabel jlBit9;
    private javax.swing.JLabel jlBitOffset;
    private javax.swing.JLabel jlbit24;
    private javax.swing.JScrollPane jspData;
    private javax.swing.JScrollPane jspProtocolDescription;
    private javax.swing.JTextPane jtaData;
    private javax.swing.JTextField jtfChecksum;
    private javax.swing.JTextField jtfDestinationPort;
    private javax.swing.JTextField jtfMessageLenght;
    private javax.swing.JTextField jtfSourcePort;
    // End of variables declaration//GEN-END:variables
}
