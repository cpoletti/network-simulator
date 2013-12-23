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

import datagram.DatagramLinkEthernet;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego Muller
 */
public class PanelLinkEthernet extends PanelLink {

    /**
     * Creates new form PainelTCP
     */
    public PanelLinkEthernet() {
        initComponents();

        jtfPreamble.setVisible(false);
        jtfDestinationMAC.setVisible(false);
        jtfSourceMAC.setVisible(false);
        jtfType.setVisible(false);
        jtfChecksum.setVisible(false);

        jepProtocolDescription.setContentType("text/html");
        translate();
    }

    @Override
    public void setDatagram(DatagramLink pacoteEthernet) {
        ((DatagramLinkEthernet) pacoteEthernet).setPreamble(this.getPreamble());
        ((DatagramLinkEthernet) pacoteEthernet).setSFD(this.getSFD());
        ((DatagramLinkEthernet) pacoteEthernet).setDestinationAddress(this.getDestinationAddress());
        ((DatagramLinkEthernet) pacoteEthernet).setSourceAddress(this.getSourceAddress());
        ((DatagramLinkEthernet) pacoteEthernet).setType(this.getType());
        ((DatagramLinkEthernet) pacoteEthernet).setChecksum(this.getChecksum());
    }

    @Override
    public void setPanelFields(DatagramLink pacoteEthernet) {
        this.setPreamble(((DatagramLinkEthernet) pacoteEthernet).getPreamble());
        this.setSFD(((DatagramLinkEthernet) pacoteEthernet).getSFD());
        this.setDestinationAddress(((DatagramLinkEthernet) pacoteEthernet).getDestinationAddress());
        this.setSourceAddress(((DatagramLinkEthernet) pacoteEthernet).getSourceAddress());
        this.setType(((DatagramLinkEthernet) pacoteEthernet).getType());
        this.setChecksum(((DatagramLinkEthernet) pacoteEthernet).getChecksum());
    }

    @Override
    public void setEditable(boolean edit) {
    }

    /**
     * Return the Preamble value
     *
     * @return the Preamble value
     */
    public String getPreamble() {
        return jtfPreamble.getText();
    }

    /**
     * Return the SFD value
     *
     * @return the SFD value
     */
    public String getSFD() {
        return jtfSFD.getText();
    }

    /**
     * Return the Destination Address value
     *
     * @return the Destination Address value
     */
    public String getDestinationAddress() {
        return jtfDestinationMAC.getText();
    }

    /**
     * Return the Source Address value
     *
     * @return the Source Address value
     */
    public String getSourceAddress() {
        return jtfSourceMAC.getText();
    }

    /**
     * Return the Type value
     *
     * @return the Type value
     */
    public int getType() {
        return Integer.parseInt(jtfType.getText());
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
     * Sets the Preamble value
     *
     * @param preamble the Preamble value
     */
    public void setPreamble(String preamble) {
        jtfPreamble.setText(preamble);
        updatePreambleButton();
    }

    /**
     * Sets the SFD value
     *
     * @param sfd the SFD value
     */
    public void setSFD(String sfd) {
        jtfSFD.setText(sfd);
        updateSFDButton();
    }

    /**
     * Sets the Destination Address value
     *
     * @param destinationAddress the Destination Address value
     */
    public void setDestinationAddress(String destinationAddress) {
        jtfDestinationMAC.setText(destinationAddress);
        updateDestinationAddressButton();
    }

    /**
     * Sets the Source Address value
     *
     * @param sourceAddress the Source Address value
     */
    public void setSourceAddress(String sourceAddress) {
        jtfSourceMAC.setText(sourceAddress);
        updateSourceAddressButton();
    }

    /**
     * Sets the Type value
     *
     * @param type the Type value
     */
    public void setType(int type) {
        jtfType.setText(String.valueOf(type));
        updateLenghtButton();
    }

    /**
     * Sets the checksum value
     *
     * @param type the checksum value
     */
    public void setChecksum(int checksum) {
        jtfChecksum.setText(String.valueOf(checksum));
        updateChecksumButton();
    }

    /**
     * Change the Preamble button text
     */
    private void updatePreambleButton() {
        if (jtfPreamble.getText().trim().equals("")) {
            jbPreamble.setText(Config.getInstance().getBundle().getString("PanelLinkEthernet.jbPreamble.text"));
        } else {
            jbPreamble.setText("<html><font color=\"BEBEBE\">" + Config.getInstance().getBundle().getString("PanelLinkEthernet.jbPreamble.text") + ":<br>" + jtfPreamble.getText().replace("\n", "<br>") + "</font></html>");
        }
        jbPreamble.setVisible(true);
        jtfPreamble.setVisible(false);
    }

    /**
     * Change the SFD button text
     */
    private void updateSFDButton() {
        String aux = Config.getInstance().getBundle().getString("PanelLinkEthernet.jbSFD.text");
        String stfText = "";
        for (int i = 0; i < aux.length(); i++) {
            stfText += aux.charAt(i) + "<br/>";
        }

        if (jtfSFD.getText().trim().equals("")) {
            jbSFD.setText("<html><font color=\"BEBEBE\">" + stfText + "<br/></font></html>");
        } else {
            String stf = "";
            for (int i = 0; i < jtfSFD.getText().length(); i++) {
                stf += jtfSFD.getText().charAt(i) + "<br/>";
            }
            jbSFD.setText("<html><font color=\"BEBEBE\">" + stfText + "<br/>" + stf + "</font></html>");
        }

        jbSFD.setVisible(true);
        jtfSFD.setVisible(false);
    }

    /**
     * Change the Destination Address button text
     */
    private void updateDestinationAddressButton() {
        if (jtfDestinationMAC.getText().trim().equals("")) {
            jbDestinationMAC.setText(Config.getInstance().getBundle().getString("PanelLinkEthernet.jbDestinationMAC.text"));
        } else {
            jbDestinationMAC.setText("<html><font color=\"BEBEBE\">" + Config.getInstance().getBundle().getString("PanelLinkEthernet.jbDestinationMAC.text") + ":<br/>" + jtfDestinationMAC.getText() + "</font></html>");
        }
        jbDestinationMAC.setVisible(true);
        jtfDestinationMAC.setVisible(false);
    }

    /**
     * Change the Source Address button text
     */
    private void updateSourceAddressButton() {
        if (jtfSourceMAC.getText().trim().equals("")) {
            jbSourceMAC.setText(Config.getInstance().getBundle().getString("PanelLinkEthernet.jbDestinationMAC.text"));
        } else {
            jbSourceMAC.setText("<html><font color=\"BEBEBE\">" + Config.getInstance().getBundle().getString("PanelLinkEthernet.jbSourceMAC.text") + ":<br/>" + jtfSourceMAC.getText() + "</font></html>");
        }
        jbSourceMAC.setVisible(true);
        jtfSourceMAC.setVisible(false);
    }

    /**
     * Change the Lenght button text
     */
    private void updateLenghtButton() {
        if (jtfType.getText().trim().equals("")) {
            jbType.setText(Config.getInstance().getBundle().getString("PanelLinkEthernet.jbType.text"));
        } else {
            jbType.setText("<html><font color=\"BEBEBE\">" + Config.getInstance().getBundle().getString("PanelLinkEthernet.jbType.text") + ":<br/>" + jtfType.getText() + "</font></html>");
        }
        jbType.setVisible(true);
        jtfType.setVisible(false);
    }

    /**
     * Change the Checksum button text
     */
    private void updateChecksumButton() {
        if (jtfChecksum.getText().trim().equals("")) {
            jbChecksum.setText(Config.getInstance().getBundle().getString("PanelLinkEthernet.jbChecksum.text"));
        } else {
            jbChecksum.setText("<html><font color=\"BEBEBE\">" + Config.getInstance().getBundle().getString("PanelLinkEthernet.jbChecksum.text") + ":<br/>" + jtfChecksum.getText() + "</font></html>");
        }
        jbChecksum.setVisible(true);
        jtfChecksum.setVisible(false);
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();
        updatePreambleButton();
        updateSFDButton();
        updateDestinationAddressButton();
        updateSourceAddressButton();
        updateLenghtButton();
        updateChecksumButton();
        jbData.setText(bundle.getString("PanelLinkEthernet.jbData.text"));

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelLinkEthernet.jspProtocolDescription.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK, 1, true));
        jspProtocolDescription.setBorder(titledBorder);

        jepProtocolDescription.setText(bundle.getString("PanelLinkEthernet.jepProtocolDescription.text"));

        jbPreamble.setToolTipText(bundle.getString("PanelLinkEthernet.jbPreamble.tooltip"));
        jbSFD.setToolTipText(bundle.getString("PanelLinkEthernet.jbSFD.tooltip"));
        jbDestinationMAC.setToolTipText(bundle.getString("PanelLinkEthernet.jbDestinationMAC.tooltip"));
        jbSourceMAC.setToolTipText(bundle.getString("PanelLinkEthernet.jbSourceMAC.tooltip"));
        jbType.setToolTipText(bundle.getString("PanelLinkEthernet.jbType.tooltip"));
        jbData.setToolTipText(bundle.getString("PanelLinkEthernet.jbData.tooltip"));
        jbChecksum.setToolTipText(bundle.getString("PanelLinkEthernet.jbChecksum.tooltip"));
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

        jbPreamble = new simulator.ToolTipButton();
        jtfPreamble = new javax.swing.JTextField();
        jbSFD = new simulator.ToolTipButton();
        jtfSFD = new javax.swing.JTextField();
        jbDestinationMAC = new simulator.ToolTipButton();
        jtfDestinationMAC = new javax.swing.JTextField();
        jbSourceMAC = new simulator.ToolTipButton();
        jtfSourceMAC = new javax.swing.JTextField();
        jbType = new simulator.ToolTipButton();
        jtfType = new javax.swing.JTextField();
        jbData = new simulator.ToolTipButton();
        jbChecksum = new simulator.ToolTipButton();
        jtfChecksum = new javax.swing.JTextField();
        jlBytes = new javax.swing.JLabel();
        jlByte08 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlByte06 = new javax.swing.JLabel();
        jlByte06_2 = new javax.swing.JLabel();
        jlByte02 = new javax.swing.JLabel();
        jlByte46a1500 = new javax.swing.JLabel();
        jlByte04 = new javax.swing.JLabel();
        jspProtocolDescription = new javax.swing.JScrollPane();
        jepProtocolDescription = new javax.swing.JEditorPane();

        setBackground(backgroundColorLink);
        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        jbPreamble.setText(bundle.getString("PanelLinkEthernet.jbPreamble.text")); // NOI18N
        jbPreamble.setToolTipText(bundle.getString("PanelLinkEthernet.jbPreamble.tooltip")); // NOI18N
        jbPreamble.setEnabled(false);
        jbPreamble.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbPreamble.setMaximumSize(new java.awt.Dimension(0, 0));
        jbPreamble.setMinimumSize(new java.awt.Dimension(0, 0));
        jbPreamble.setPreferredSize(new java.awt.Dimension(0, 0));
        jbPreamble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPreambleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbPreamble, gridBagConstraints);

        jtfPreamble.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPreamble.setAlignmentX(0.0F);
        jtfPreamble.setAlignmentY(0.0F);
        jtfPreamble.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfPreamble.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfPreamble.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfPreamble.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfPreamble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPreambleActionPerformed(evt);
            }
        });
        jtfPreamble.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfPreambleFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jtfPreamble, gridBagConstraints);

        jbSFD.setText(bundle.getString("PanelLinkEthernet.jbSFD.text")); // NOI18N
        jbSFD.setToolTipText(bundle.getString("PanelLinkEthernet.jbSFD.tooltip")); // NOI18N
        jbSFD.setActionCommand("");
        jbSFD.setEnabled(false);
        jbSFD.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSFD.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSFD.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSFD.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSFDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbSFD, gridBagConstraints);

        jtfSFD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSFD.setAlignmentX(0.0F);
        jtfSFD.setAlignmentY(0.0F);
        jtfSFD.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfSFD.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfSFD.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfSFD.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfSFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSFDActionPerformed(evt);
            }
        });
        jtfSFD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSFDFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jtfSFD, gridBagConstraints);

        jbDestinationMAC.setText(bundle.getString("PanelLinkEthernet.jbDestinationMAC.text")); // NOI18N
        jbDestinationMAC.setToolTipText(bundle.getString("PanelLinkEthernet.jbDestinationMAC.tooltip")); // NOI18N
        jbDestinationMAC.setEnabled(false);
        jbDestinationMAC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbDestinationMAC.setMaximumSize(new java.awt.Dimension(0, 0));
        jbDestinationMAC.setMinimumSize(new java.awt.Dimension(0, 0));
        jbDestinationMAC.setPreferredSize(new java.awt.Dimension(0, 0));
        jbDestinationMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDestinationMACActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbDestinationMAC, gridBagConstraints);

        jtfDestinationMAC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDestinationMAC.setAlignmentX(0.0F);
        jtfDestinationMAC.setAlignmentY(0.0F);
        jtfDestinationMAC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfDestinationMAC.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfDestinationMAC.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfDestinationMAC.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfDestinationMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDestinationMACActionPerformed(evt);
            }
        });
        jtfDestinationMAC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDestinationMACFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jtfDestinationMAC, gridBagConstraints);

        jbSourceMAC.setText(bundle.getString("PanelLinkEthernet.jbSourceMAC.text")); // NOI18N
        jbSourceMAC.setToolTipText(bundle.getString("PanelLinkEthernet.jbSourceMAC.tooltip")); // NOI18N
        jbSourceMAC.setEnabled(false);
        jbSourceMAC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSourceMAC.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSourceMAC.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSourceMAC.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSourceMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSourceMACActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbSourceMAC, gridBagConstraints);

        jtfSourceMAC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSourceMAC.setAlignmentX(0.0F);
        jtfSourceMAC.setAlignmentY(0.0F);
        jtfSourceMAC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfSourceMAC.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfSourceMAC.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfSourceMAC.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfSourceMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSourceMACActionPerformed(evt);
            }
        });
        jtfSourceMAC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSourceMACFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jtfSourceMAC, gridBagConstraints);

        jbType.setText(bundle.getString("PanelLinkEthernet.jbType.text")); // NOI18N
        jbType.setToolTipText(bundle.getString("PanelLinkEthernet.jbType.tooltip")); // NOI18N
        jbType.setEnabled(false);
        jbType.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbType.setMaximumSize(new java.awt.Dimension(0, 0));
        jbType.setMinimumSize(new java.awt.Dimension(0, 0));
        jbType.setPreferredSize(new java.awt.Dimension(0, 0));
        jbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbType, gridBagConstraints);

        jtfType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfType.setAlignmentX(0.0F);
        jtfType.setAlignmentY(0.0F);
        jtfType.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfType.setMaximumSize(new java.awt.Dimension(0, 0));
        jtfType.setMinimumSize(new java.awt.Dimension(0, 0));
        jtfType.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTypeActionPerformed(evt);
            }
        });
        jtfType.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTypeFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jtfType, gridBagConstraints);

        jbData.setBackground(new java.awt.Color(255, 204, 102));
        jbData.setText(bundle.getString("PanelLinkEthernet.jbData.text")); // NOI18N
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
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 46;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        add(jbData, gridBagConstraints);

        jbChecksum.setText(bundle.getString("PanelLinkEthernet.jbChecksum.text")); // NOI18N
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
        gridBagConstraints.gridx = 69;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 10);
        add(jbChecksum, gridBagConstraints);

        jtfChecksum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfChecksum.setAlignmentX(0.0F);
        jtfChecksum.setAlignmentY(0.0F);
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
        gridBagConstraints.gridx = 69;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 10);
        add(jtfChecksum, gridBagConstraints);

        jlBytes.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBytes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBytes.setText("Nr. Bytes");
        jlBytes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        add(jlBytes, gridBagConstraints);

        jlByte08.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte08.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte08.setText("07");
        jlByte08.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.05;
        add(jlByte08, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("01");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 0.05;
        add(jLabel1, gridBagConstraints);

        jlByte06.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte06.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte06.setText("06");
        jlByte06.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.05;
        add(jlByte06, gridBagConstraints);

        jlByte06_2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte06_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte06_2.setText("06");
        jlByte06_2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.05;
        add(jlByte06_2, gridBagConstraints);

        jlByte02.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte02.setText("02");
        jlByte02.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.05;
        add(jlByte02, gridBagConstraints);

        jlByte46a1500.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte46a1500.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte46a1500.setText("46 a 1500");
        jlByte46a1500.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 46;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.05;
        add(jlByte46a1500, gridBagConstraints);

        jlByte04.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlByte04.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlByte04.setText("04");
        jlByte04.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        add(jlByte04, gridBagConstraints);

        jspProtocolDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), bundle.getString("PanelLinkEthernet.jspProtocolDescription.title"))); // NOI18N

        jepProtocolDescription.setEditable(false);
        jepProtocolDescription.setBorder(null);
        jspProtocolDescription.setViewportView(jepProtocolDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 73;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(jspProtocolDescription, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSourceMACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSourceMACActionPerformed
        jtfSourceMAC.setVisible(true);
        jbSourceMAC.setVisible(false);
        jtfSourceMAC.requestFocus();
}//GEN-LAST:event_jbSourceMACActionPerformed

    private void jbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTypeActionPerformed
        jtfType.setVisible(true);
        jbType.setVisible(false);
        jtfType.requestFocus();
}//GEN-LAST:event_jbTypeActionPerformed

    private void jbChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChecksumActionPerformed
        jtfChecksum.setVisible(true);
        jbChecksum.setVisible(false);
        jtfChecksum.requestFocus();
}//GEN-LAST:event_jbChecksumActionPerformed

    private void jbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataActionPerformed
        setPreviousTab();
}//GEN-LAST:event_jbDataActionPerformed

    private void jbDestinationMACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinationMACActionPerformed
        jtfDestinationMAC.setVisible(true);
        jbDestinationMAC.setVisible(false);
        jtfDestinationMAC.requestFocus();
    }//GEN-LAST:event_jbDestinationMACActionPerformed

    private void jtfPreambleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPreambleActionPerformed
        updatePreambleButton();
}//GEN-LAST:event_jtfPreambleActionPerformed

    private void jtfPreambleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfPreambleFocusLost
        updatePreambleButton();
}//GEN-LAST:event_jtfPreambleFocusLost

    private void jtfDestinationMACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDestinationMACActionPerformed
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationMACActionPerformed

    private void jtfDestinationMACFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationMACFocusLost
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationMACFocusLost

    private void jtfSourceMACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSourceMACActionPerformed
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceMACActionPerformed

    private void jtfSourceMACFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSourceMACFocusLost
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceMACFocusLost

    private void jtfTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTypeActionPerformed
        updateLenghtButton();
    }//GEN-LAST:event_jtfTypeActionPerformed

    private void jtfTypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTypeFocusLost
        updateLenghtButton();
    }//GEN-LAST:event_jtfTypeFocusLost

    private void jtfChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfChecksumActionPerformed
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumActionPerformed

    private void jtfChecksumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfChecksumFocusLost
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumFocusLost

    private void jbPreambleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPreambleActionPerformed
        jtfPreamble.setVisible(true);
        jbPreamble.setVisible(false);
        jtfPreamble.requestFocus();
    }//GEN-LAST:event_jbPreambleActionPerformed

    private void jbSFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSFDActionPerformed

    private void jtfSFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSFDActionPerformed

    private void jtfSFDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSFDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSFDFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private simulator.ToolTipButton jbChecksum;
    private simulator.ToolTipButton jbData;
    private simulator.ToolTipButton jbDestinationMAC;
    private simulator.ToolTipButton jbPreamble;
    private simulator.ToolTipButton jbSFD;
    private simulator.ToolTipButton jbSourceMAC;
    private simulator.ToolTipButton jbType;
    private javax.swing.JEditorPane jepProtocolDescription;
    private javax.swing.JLabel jlByte02;
    private javax.swing.JLabel jlByte04;
    private javax.swing.JLabel jlByte06;
    private javax.swing.JLabel jlByte06_2;
    private javax.swing.JLabel jlByte08;
    private javax.swing.JLabel jlByte46a1500;
    private javax.swing.JLabel jlBytes;
    private javax.swing.JScrollPane jspProtocolDescription;
    private javax.swing.JTextField jtfChecksum;
    private javax.swing.JTextField jtfDestinationMAC;
    private javax.swing.JTextField jtfPreamble;
    private javax.swing.JTextField jtfSFD;
    private javax.swing.JTextField jtfSourceMAC;
    private javax.swing.JTextField jtfType;
    // End of variables declaration//GEN-END:variables
}
