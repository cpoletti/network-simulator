/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelInternetIP.java
 *
 * Created on 08/09/2010, 19:13:34
 */
package simulator;

import datagram.DatagramInternetIP;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego Muller
 */
public class PanelInternetIP extends PanelInternet {

    /**
     * Creates new form PanelInternetIP
     */
    public PanelInternetIP() {
        initComponents();

        jtfVersion.setVisible(false);
        jtfServiceType.setVisible(false);
        jtfTotalLength.setVisible(false);
        jtfIdentification.setVisible(false);
        jtfHeaderLenght.setVisible(false);
        jtfFragmentOffset.setVisible(false);
        jtfTimeToLive.setVisible(false);
        jtfProtocol.setVisible(false);
        jtfChecksum.setVisible(false);
        jtfSourceAddress.setVisible(false);
        jtfDestinationAddress.setVisible(false);
        jtfOptions.setVisible(false);

        jepProtocolDescription.setContentType("text/html");
        translate();
    }

    @Override
    public void setDatagram(DatagramInternet ipDatagram) {

        ((DatagramInternetIP) ipDatagram).setVersion(this.getVersion());
        ((DatagramInternetIP) ipDatagram).setHeaderLenght(this.getHeaderLenght());
        ((DatagramInternetIP) ipDatagram).setServiceType(this.getServiceType());
        ((DatagramInternetIP) ipDatagram).setTotalLenght(this.getTotalLength());
        ((DatagramInternetIP) ipDatagram).setIdentification(this.getIdentification());
        ((DatagramInternetIP) ipDatagram).setDF(this.getDF());
        ((DatagramInternetIP) ipDatagram).setMF(this.getMF());
        ((DatagramInternetIP) ipDatagram).setFragmentOffset(this.getFragmentOffset());
        ((DatagramInternetIP) ipDatagram).setTimeToLive(this.getTimeToLive());
        ((DatagramInternetIP) ipDatagram).setProtocol(this.getProtocol());
        ((DatagramInternetIP) ipDatagram).setHeaderChecksum(this.getChecksum());
        ((DatagramInternetIP) ipDatagram).setSourceIPAddress(this.getSourceAddress());
        ((DatagramInternetIP) ipDatagram).setDestinationIPAddress(this.getDestinationAddress());
        ((DatagramInternetIP) ipDatagram).setOptions(this.getOptions());
    }

    @Override
    public void setPanelFields(DatagramInternet ipDatagram) {
        this.setVersion(((DatagramInternetIP) ipDatagram).getVersion());
        this.setHeaderLenght(((DatagramInternetIP) ipDatagram).getHeaderLenght());
        this.setServiceType(((DatagramInternetIP) ipDatagram).getServiceType());
        this.setTotalLenght(((DatagramInternetIP) ipDatagram).getTotalLenght());
        this.setIdentification(((DatagramInternetIP) ipDatagram).getIdentification());
        this.setDF(((DatagramInternetIP) ipDatagram).isDF());
        this.setMF(((DatagramInternetIP) ipDatagram).isMF());
        this.setFragmentOffset(((DatagramInternetIP) ipDatagram).getFragmentOffset());
        this.setTimeToLive(((DatagramInternetIP) ipDatagram).getTimeToLive());
        this.setProtocol(((DatagramInternetIP) ipDatagram).getProtocol());
        this.setChecksum(((DatagramInternetIP) ipDatagram).getHeaderChecksum());
        this.setSourceAddress(((DatagramInternetIP) ipDatagram).getSourceIPAddress());
        this.setDestinationAddress(((DatagramInternetIP) ipDatagram).getDestinationIPAddress());
        this.setOptions(((DatagramInternetIP) ipDatagram).getOptions());
    }

    @Override
    public void setEditable(boolean edit) {
        jbDF.setEnabled(edit);
        jbFragmentOffset.setEnabled(edit);
        jbIdentification.setEnabled(edit);
        jbMF.setEnabled(edit);
        jbServiceType.setEnabled(edit);
        jbTimeToLive.setEnabled(edit);
        jbProtocol.setEnabled(edit);
        jbVersion.setEnabled(edit);
        jbHeaderLenght.setEnabled(edit);
    }

    /**
     * Return the IP version value
     *
     * @return the IP version value
     */
    public int getVersion() {
        return Integer.parseInt(jtfVersion.getText());
    }

    /**
     * Return the IP Header Lenght value
     *
     * @return the IP Header Lenght value
     */
    public int getHeaderLenght() {
        return Integer.parseInt(jtfHeaderLenght.getText());
    }

    /**
     * Return the IP Service Type value
     *
     * @return the IP Service Type value
     */
    public int getServiceType() {
        return Integer.parseInt(jtfServiceType.getText());
    }

    /**
     * Return the IP Total Length value
     *
     * @return the IP Total Length value
     */
    public int getTotalLength() {
        return Integer.parseInt(jtfTotalLength.getText());
    }

    /**
     * Return the IP Identification value
     *
     * @return the IP Identification value
     */
    public int getIdentification() {
        return Integer.parseInt(jtfIdentification.getText());
    }

    /**
     * Return the IP DF value
     *
     * @return the IP DF value
     */
    public boolean getDF() {
        if (jbDF.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the IP MF value
     *
     * @return the IP MF value
     */
    public boolean getMF() {
        if (jbMF.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the IP Fragment Offset value
     *
     * @return the IP Fragment Offset value
     */
    public int getFragmentOffset() {
        return Integer.parseInt(jtfFragmentOffset.getText());
    }

    /**
     * Return the IP Time to Live value
     *
     * @return the IP Time to Live value
     */
    public int getTimeToLive() {
        return Integer.parseInt(jtfTimeToLive.getText());
    }

    /**
     * Return the IP Protocol value
     *
     * @return the IP Protocol value
     */
    public int getProtocol() {
        return Integer.parseInt(jtfProtocol.getText());
    }

    /**
     * Return the IP Checksum value
     *
     * @return the IP Checksum value
     */
    public int getChecksum() {
        return Integer.parseInt(jtfChecksum.getText());
    }

    /**
     * Return the IP Source Address value
     *
     * @return the IP Source Address value
     */
    public String getSourceAddress() {
        return jtfSourceAddress.getText();
    }

    /**
     * Return the IP Destination Address value
     *
     * @return the IP Destination Address value
     */
    public String getDestinationAddress() {
        return jtfDestinationAddress.getText();
    }

    /**
     * Return the IP Options value
     *
     * @return the IP Options value
     */
    public int getOptions() {
        return Integer.parseInt(jtfOptions.getText());
    }

    /**
     * Sets the IP version value
     *
     * @param version the IP version value
     */
    public void setVersion(int version) {
        jtfVersion.setText(String.valueOf(version));
        updateVersionButton();
    }

    /**
     * Sets the IP header lenght value
     *
     * @param headerLenght the IP header lenght value
     */
    public void setHeaderLenght(int headerLenght) {
        jtfHeaderLenght.setText(String.valueOf(headerLenght));
        updateHeaderLenghtButton();
    }

    /**
     * Sets the IP Service Type value
     *
     * @param service the IP Service Type value
     */
    public void setServiceType(int service) {
        jtfServiceType.setText(String.valueOf(service));
        updateServiceTypeButton();
    }

    /**
     * Sets the IP Total Lenght value
     *
     * @param lenght the IP Total Lenght value
     */
    public void setTotalLenght(int lenght) {
        jtfTotalLength.setText(String.valueOf(lenght));
        updateTotalLengthButton();
    }

    /**
     * Sets the IP Identification value
     *
     * @param identification the IP Identification value
     */
    public void setIdentification(int identification) {
        jtfIdentification.setText(String.valueOf(identification));
        updateIdentificationButton();
    }

    /**
     * Sets the IP DF value
     *
     * @param df the IP DF value
     */
    public void setDF(boolean df) {
        if (df) {
            jbDF.setBackground(enableButtonColor);
        } else {
            jbDF.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the IP MF value
     *
     * @param mf the IP MF value
     */
    public void setMF(boolean mf) {
        if (mf) {
            jbMF.setBackground(enableButtonColor);
        } else {
            jbMF.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the IP Fragment Offset value
     *
     * @param fragment the IP Fragment Offset value
     */
    public void setFragmentOffset(int fragment) {
        jtfFragmentOffset.setText(String.valueOf(fragment));
        updateFragmentOffsetButton();
    }

    /**
     * Sets the IP Time to Live value
     *
     * @param timeToLive the IP Time to Live value
     */
    public void setTimeToLive(int timeToLive) {
        jtfTimeToLive.setText(String.valueOf(timeToLive));
        updateTimeToLiveButton();
    }

    /**
     * Sets the IP Protocol value
     *
     * @param protocol the IP Protocol value
     */
    public void setProtocol(int protocol) {
        jtfProtocol.setText(String.valueOf(protocol));
        updateProtocolButton();
    }

    /**
     * Sets the IP Checksum value
     *
     * @param checksum the IP Checksum value
     */
    public void setChecksum(int checksum) {
        jtfChecksum.setText(String.valueOf(checksum));
        updateChecksumButton();
    }

    /**
     * Sets the IP Source Address value
     *
     * @param sourceAddress the IP Source Address value
     */
    public void setSourceAddress(String sourceAddress) {
        jtfSourceAddress.setText(sourceAddress);
        updateSourceAddressButton();
    }

    /**
     * Sets the IP Destination Address value
     *
     * @param destinationAddress the IP Destination Address value
     */
    public void setDestinationAddress(String destinationAddress) {
        jtfDestinationAddress.setText(destinationAddress);
        updateDestinationAddressButton();
    }

    /**
     * Sets the IP Options value
     *
     * @param options the IP Options value
     */
    public void setOptions(int options) {
        jtfOptions.setText(String.valueOf(options));
        updateOptionsButton();
    }

    /**
     * Change the IP version button text
     */
    private void updateVersionButton() {
        if (jtfVersion.getText().trim().equals("")) {
            jbVersion.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbVersion.text"));
        } else {
            jbVersion.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbVersion.text") + ": " + jtfVersion.getText());
        }
        jbVersion.setVisible(true);
        jtfVersion.setVisible(false);
    }

    /**
     * Change the IP service type button text
     */
    private void updateServiceTypeButton() {
        if (jtfServiceType.getText().trim().equals("")) {
            jbServiceType.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbServiceType.text"));
        } else {
            jbServiceType.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbServiceType.text") + ": " + jtfServiceType.getText());
        }
        jbServiceType.setVisible(true);
        jtfServiceType.setVisible(false);
    }

    /**
     * Change the IP Total Length button text
     */
    private void updateTotalLengthButton() {
        if (jtfTotalLength.getText().trim().equals("")) {
            jbTotalLength.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbTotalLength.text"));
        } else {
            jbTotalLength.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbTotalLength.text") + ": " + jtfTotalLength.getText());
        }
        jbTotalLength.setVisible(true);
        jtfTotalLength.setVisible(false);
    }

    /**
     * Change the IP Identification button text
     */
    private void updateIdentificationButton() {
        if (jtfIdentification.getText().trim().equals("")) {
            jbIdentification.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbIdentification.text"));
        } else {
            jbIdentification.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbIdentification.text") + ": " + jtfIdentification.getText());
        }
        jbIdentification.setVisible(true);
        jtfIdentification.setVisible(false);
    }

    /**
     * Change the IP Header Lenght button text
     */
    private void updateHeaderLenghtButton() {
        if (jtfHeaderLenght.getText().trim().equals("")) {
            jbHeaderLenght.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbHeaderLenght.text"));
        } else {
            jbHeaderLenght.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbHeaderLenght.text") + ": " + jtfHeaderLenght.getText());
        }
        jbHeaderLenght.setVisible(true);
        jtfHeaderLenght.setVisible(false);
    }

    /**
     * Change the IP Fragment Offset button text
     */
    private void updateFragmentOffsetButton() {
        if (jtfFragmentOffset.getText().trim().equals("")) {
            jbFragmentOffset.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbFragmentOffset.text"));
        } else {
            jbFragmentOffset.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbFragmentOffset.text") + ": " + jtfFragmentOffset.getText());
        }
        jbFragmentOffset.setVisible(true);
        jtfFragmentOffset.setVisible(false);
    }

    /**
     * Change the IP Time to Live button text
     */
    private void updateTimeToLiveButton() {
        if (jtfTimeToLive.getText().trim().equals("")) {
            jbTimeToLive.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbTimeToLive.text"));
        } else {
            jbTimeToLive.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbTimeToLive.text") + ": " + jtfTimeToLive.getText());
        }
        jbTimeToLive.setVisible(true);
        jtfTimeToLive.setVisible(false);
    }

    /**
     * Change the IP Protocol button text
     */
    private void updateProtocolButton() {
        if (jtfProtocol.getText().trim().equals("")) {
            jbProtocol.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbProtocol.text"));
        } else {
            jbProtocol.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbProtocol.text") + ": " + jtfProtocol.getText());
        }
        jbProtocol.setVisible(true);
        jtfProtocol.setVisible(false);
    }

    /**
     * Change the IP Checksum button text
     */
    private void updateChecksumButton() {
        if (jtfChecksum.getText().trim().equals("")) {
            jbChecksum.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbChecksum.text"));
        } else {
            jbChecksum.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbChecksum.text") + ": " + jtfChecksum.getText());
        }
        jbChecksum.setVisible(true);
        jtfChecksum.setVisible(false);
    }

    /**
     * Change the IP Source Address button text
     */
    private void updateSourceAddressButton() {
        if (jtfSourceAddress.getText().trim().equals("")) {
            jbSourceAddress.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbSourceAddress.text"));
        } else {
            jbSourceAddress.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbSourceAddress.text") + ": " + jtfSourceAddress.getText());
        }
        jbSourceAddress.setVisible(true);
        jtfSourceAddress.setVisible(false);
    }

    /**
     * Change the IP Source Destination button text
     */
    private void updateDestinationAddressButton() {
        if (jtfDestinationAddress.getText().trim().equals("")) {
            jbDestinationAddress.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbDestinationAddress.text"));
        } else {
            jbDestinationAddress.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbDestinationAddress.text") + ": " + jtfDestinationAddress.getText());
        }
        jbDestinationAddress.setVisible(true);
        jtfDestinationAddress.setVisible(false);
    }

    /**
     * Change the IP Options button text
     */
    private void updateOptionsButton() {
        if (jtfOptions.getText().trim().equals("")) {
            jbOptions.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbOptions.text"));
        } else {
            jbOptions.setText(Config.getInstance().getBundle().getString("PanelInternetIP.jbOptions.text") + ": " + jtfOptions.getText());
        }
        jbOptions.setVisible(true);
        jtfOptions.setVisible(false);
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        updateVersionButton();
        updateHeaderLenghtButton();
        updateServiceTypeButton();
        updateTotalLengthButton();
        updateIdentificationButton();
        updateFragmentOffsetButton();
        updateTimeToLiveButton();
        updateProtocolButton();
        updateChecksumButton();
        updateSourceAddressButton();
        updateDestinationAddressButton();
        updateOptionsButton();

        ResourceBundle bundle = Config.getInstance().getBundle();

        jbDF.setText(bundle.getString("PanelInternetIP.jbDF.text"));
        jbMF.setText(bundle.getString("PanelInternetIP.jbMF.text"));
        jbData.setText(bundle.getString("PanelInternetIP.jbData.text"));

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelInternetIP.jspProtocolDescription.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK, 1, true));
        jspProtocolDescription.setBorder(titledBorder);
        jepProtocolDescription.setText(bundle.getString("PanelInternetIP.jepProtocolDescription.text"));

        jbVersion.setToolTipText(bundle.getString("PanelInternetIP.jbVersion.tooltip"));
        jbHeaderLenght.setToolTipText(bundle.getString("PanelInternetIP.jbHeaderLenght.tooltip"));
        jbHeaderLenght.setToolTipText(bundle.getString("PanelInternetIP.jbHeaderLenght.tooltip"));
        jbServiceType.setToolTipText(bundle.getString("PanelInternetIP.jbServiceType.tooltip"));
        jbTotalLength.setToolTipText(bundle.getString("PanelInternetIP.jbTotalLength.tooltip"));
        jbIdentification.setToolTipText(bundle.getString("PanelInternetIP.jbIdentification.tooltip"));
        jbFragmentOffset.setToolTipText(bundle.getString("PanelInternetIP.jbFragmentOffset.tooltip"));
        jbTimeToLive.setToolTipText(bundle.getString("PanelInternetIP.jbTimeToLive.tooltip"));
        jbProtocol.setToolTipText(bundle.getString("PanelInternetIP.jbProtocol.tooltip"));
        jbChecksum.setToolTipText(bundle.getString("PanelInternetIP.jbChecksum.tooltip"));
        jbSourceAddress.setToolTipText(bundle.getString("PanelInternetIP.jbSourceAddress.tooltip"));
        jbDestinationAddress.setToolTipText(bundle.getString("PanelInternetIP.jbDestinationAddress.tooltip"));
        jbOptions.setToolTipText(bundle.getString("PanelInternetIP.jbOptions.tooltip"));
        jbDF.setToolTipText(bundle.getString("PanelInternetIP.jbDF.tooltip"));
        jbMF.setToolTipText(bundle.getString("PanelInternetIP.jbMF.tooltip"));
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

        jbVersion = new simulator.ToolTipButton();
        jtfVersion = new javax.swing.JTextField();
        jbHeaderLenght = new simulator.ToolTipButton();
        jtfHeaderLenght = new javax.swing.JTextField();
        jbServiceType = new simulator.ToolTipButton();
        jtfServiceType = new javax.swing.JTextField();
        jbTotalLength = new simulator.ToolTipButton();
        jtfTotalLength = new javax.swing.JTextField();
        jbIdentification = new simulator.ToolTipButton();
        jtfIdentification = new javax.swing.JTextField();
        jbReserved = new simulator.ToolTipButton();
        jbDF = new simulator.ToolTipButton();
        jbMF = new simulator.ToolTipButton();
        jbFragmentOffset = new simulator.ToolTipButton();
        jtfFragmentOffset = new javax.swing.JTextField();
        jbTimeToLive = new simulator.ToolTipButton();
        jtfTimeToLive = new javax.swing.JTextField();
        jbProtocol = new simulator.ToolTipButton();
        jtfProtocol = new javax.swing.JTextField();
        jbChecksum = new simulator.ToolTipButton();
        jtfChecksum = new javax.swing.JTextField();
        jbSourceAddress = new simulator.ToolTipButton();
        jtfSourceAddress = new javax.swing.JTextField();
        jbDestinationAddress = new simulator.ToolTipButton();
        jtfDestinationAddress = new javax.swing.JTextField();
        jbOptions = new simulator.ToolTipButton();
        jtfOptions = new javax.swing.JTextField();
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
        jlBit64 = new javax.swing.JLabel();
        jlBit96 = new javax.swing.JLabel();
        jlBit128 = new javax.swing.JLabel();
        jlBit160 = new javax.swing.JLabel();
        jbData = new simulator.ToolTipButton();
        jspProtocolDescription = new javax.swing.JScrollPane();
        jepProtocolDescription = new javax.swing.JEditorPane();

        setBackground(backgroundColorInternet);
        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        jbVersion.setText(bundle.getString("PanelInternetIP.jbVersion.text")); // NOI18N
        jbVersion.setToolTipText(bundle.getString("PanelInternetIP.jbVersion.tooltip")); // NOI18N
        jbVersion.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbVersion.setMaximumSize(new java.awt.Dimension(0, 0));
        jbVersion.setMinimumSize(new java.awt.Dimension(0, 0));
        jbVersion.setPreferredSize(new java.awt.Dimension(0, 0));
        jbVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVersionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbVersion, gridBagConstraints);

        jtfVersion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfVersion.setText("0");
        jtfVersion.setAlignmentX(0.0F);
        jtfVersion.setAlignmentY(0.0F);
        jtfVersion.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfVersion.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfVersionActionPerformed(evt);
            }
        });
        jtfVersion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfVersionFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfVersion, gridBagConstraints);

        jbHeaderLenght.setText(bundle.getString("PanelInternetIP.jbHeaderLenght.text")); // NOI18N
        jbHeaderLenght.setToolTipText(bundle.getString("PanelInternetIP.jbHeaderLenght.tooltip")); // NOI18N
        jbHeaderLenght.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbHeaderLenght.setMaximumSize(new java.awt.Dimension(0, 0));
        jbHeaderLenght.setMinimumSize(new java.awt.Dimension(0, 0));
        jbHeaderLenght.setPreferredSize(new java.awt.Dimension(0, 0));
        jbHeaderLenght.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHeaderLenghtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbHeaderLenght, gridBagConstraints);

        jtfHeaderLenght.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfHeaderLenght.setText("0");
        jtfHeaderLenght.setAlignmentX(0.0F);
        jtfHeaderLenght.setAlignmentY(0.0F);
        jtfHeaderLenght.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfHeaderLenght.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfHeaderLenght.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfHeaderLenghtActionPerformed(evt);
            }
        });
        jtfHeaderLenght.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfHeaderLenghtFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfHeaderLenght, gridBagConstraints);

        jbServiceType.setText(bundle.getString("PanelInternetIP.jbServiceType.text")); // NOI18N
        jbServiceType.setToolTipText(bundle.getString("PanelInternetIP.jbServiceType.tooltip")); // NOI18N
        jbServiceType.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbServiceType.setMaximumSize(new java.awt.Dimension(0, 0));
        jbServiceType.setMinimumSize(new java.awt.Dimension(0, 0));
        jbServiceType.setPreferredSize(new java.awt.Dimension(0, 0));
        jbServiceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbServiceTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbServiceType, gridBagConstraints);

        jtfServiceType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfServiceType.setText("0");
        jtfServiceType.setAlignmentX(0.0F);
        jtfServiceType.setAlignmentY(0.0F);
        jtfServiceType.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfServiceType.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfServiceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfServiceTypeActionPerformed(evt);
            }
        });
        jtfServiceType.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfServiceTypeFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfServiceType, gridBagConstraints);

        jbTotalLength.setText(bundle.getString("PanelInternetIP.jbTotalLength.text")); // NOI18N
        jbTotalLength.setToolTipText(bundle.getString("PanelInternetIP.jbTotalLength.tooltip")); // NOI18N
        jbTotalLength.setEnabled(false);
        jbTotalLength.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbTotalLength.setMaximumSize(new java.awt.Dimension(0, 0));
        jbTotalLength.setMinimumSize(new java.awt.Dimension(0, 0));
        jbTotalLength.setPreferredSize(new java.awt.Dimension(0, 0));
        jbTotalLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTotalLengthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbTotalLength, gridBagConstraints);

        jtfTotalLength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfTotalLength.setText("0");
        jtfTotalLength.setAlignmentX(0.0F);
        jtfTotalLength.setAlignmentY(0.0F);
        jtfTotalLength.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfTotalLength.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfTotalLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTotalLengthActionPerformed(evt);
            }
        });
        jtfTotalLength.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTotalLengthFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfTotalLength, gridBagConstraints);

        jbIdentification.setText(bundle.getString("PanelInternetIP.jbIdentification.text")); // NOI18N
        jbIdentification.setToolTipText(bundle.getString("PanelInternetIP.jbIdentification.tooltip")); // NOI18N
        jbIdentification.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbIdentification.setMaximumSize(new java.awt.Dimension(0, 0));
        jbIdentification.setMinimumSize(new java.awt.Dimension(0, 0));
        jbIdentification.setPreferredSize(new java.awt.Dimension(0, 0));
        jbIdentification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIdentificationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbIdentification, gridBagConstraints);

        jtfIdentification.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfIdentification.setText("0");
        jtfIdentification.setAlignmentX(0.0F);
        jtfIdentification.setAlignmentY(0.0F);
        jtfIdentification.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfIdentification.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfIdentification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdentificationActionPerformed(evt);
            }
        });
        jtfIdentification.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfIdentificationFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfIdentification, gridBagConstraints);

        jbReserved.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbReserved.setActionCommand("RST");
        jbReserved.setEnabled(false);
        jbReserved.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbReserved.setMaximumSize(new java.awt.Dimension(0, 0));
        jbReserved.setMinimumSize(new java.awt.Dimension(0, 0));
        jbReserved.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbReserved, gridBagConstraints);

        jbDF.setBackground(enableButtonColor);
        jbDF.setText(bundle.getString("PanelInternetIP.jbDF.text")); // NOI18N
        jbDF.setToolTipText(bundle.getString("PanelInternetIP.jbDF.tooltip")); // NOI18N
        jbDF.setActionCommand("SYN");
        jbDF.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbDF.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbDF.setMaximumSize(new java.awt.Dimension(0, 0));
        jbDF.setMinimumSize(new java.awt.Dimension(0, 0));
        jbDF.setPreferredSize(new java.awt.Dimension(0, 0));
        jbDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDFActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbDF, gridBagConstraints);

        jbMF.setBackground(enableButtonColor);
        jbMF.setText(bundle.getString("PanelInternetIP.jbMF.text")); // NOI18N
        jbMF.setToolTipText(bundle.getString("PanelInternetIP.jbMF.tooltip")); // NOI18N
        jbMF.setActionCommand("FIN");
        jbMF.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbMF.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbMF.setMaximumSize(new java.awt.Dimension(0, 0));
        jbMF.setMinimumSize(new java.awt.Dimension(0, 0));
        jbMF.setPreferredSize(new java.awt.Dimension(0, 0));
        jbMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMFActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbMF, gridBagConstraints);

        jbFragmentOffset.setText(bundle.getString("PanelInternetIP.jbFragmentOffset.text")); // NOI18N
        jbFragmentOffset.setToolTipText(bundle.getString("PanelInternetIP.jbFragmentOffset.tooltip")); // NOI18N
        jbFragmentOffset.setPreferredSize(new java.awt.Dimension(0, 0));
        jbFragmentOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFragmentOffsetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbFragmentOffset, gridBagConstraints);

        jtfFragmentOffset.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfFragmentOffset.setText("0");
        jtfFragmentOffset.setAlignmentX(0.0F);
        jtfFragmentOffset.setAlignmentY(0.0F);
        jtfFragmentOffset.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfFragmentOffset.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfFragmentOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFragmentOffsetActionPerformed(evt);
            }
        });
        jtfFragmentOffset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFragmentOffsetFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfFragmentOffset, gridBagConstraints);

        jbTimeToLive.setText(bundle.getString("PanelInternetIP.jbTimeToLive.text")); // NOI18N
        jbTimeToLive.setToolTipText(bundle.getString("PanelInternetIP.jbTimeToLive.tooltip")); // NOI18N
        jbTimeToLive.setPreferredSize(new java.awt.Dimension(0, 0));
        jbTimeToLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTimeToLiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbTimeToLive, gridBagConstraints);

        jtfTimeToLive.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfTimeToLive.setText("0");
        jtfTimeToLive.setAlignmentX(0.0F);
        jtfTimeToLive.setAlignmentY(0.0F);
        jtfTimeToLive.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfTimeToLive.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfTimeToLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTimeToLiveActionPerformed(evt);
            }
        });
        jtfTimeToLive.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTimeToLiveFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfTimeToLive, gridBagConstraints);

        jbProtocol.setText(bundle.getString("PanelInternetIP.jbProtocol.text")); // NOI18N
        jbProtocol.setToolTipText(bundle.getString("PanelInternetIP.jbProtocol.tooltip")); // NOI18N
        jbProtocol.setPreferredSize(new java.awt.Dimension(0, 0));
        jbProtocol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProtocolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbProtocol, gridBagConstraints);

        jtfProtocol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfProtocol.setText("0");
        jtfProtocol.setAlignmentX(0.0F);
        jtfProtocol.setAlignmentY(0.0F);
        jtfProtocol.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfProtocol.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfProtocol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfProtocolActionPerformed(evt);
            }
        });
        jtfProtocol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfProtocolFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfProtocol, gridBagConstraints);

        jbChecksum.setText(bundle.getString("PanelInternetIP.jbChecksum.text")); // NOI18N
        jbChecksum.setToolTipText(bundle.getString("PanelInternetIP.jbChecksum.tooltip")); // NOI18N
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbChecksum, gridBagConstraints);

        jtfChecksum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfChecksum.setText("0");
        jtfChecksum.setAlignmentX(0.0F);
        jtfChecksum.setAlignmentY(0.0F);
        jtfChecksum.setMargin(new java.awt.Insets(1, 1, 1, 1));
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfChecksum, gridBagConstraints);

        jbSourceAddress.setText(bundle.getString("PanelInternetIP.jbSourceAddress.text")); // NOI18N
        jbSourceAddress.setToolTipText(bundle.getString("PanelInternetIP.jbSourceAddress.tooltip")); // NOI18N
        jbSourceAddress.setEnabled(false);
        jbSourceAddress.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSourceAddress.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSourceAddress.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSourceAddress.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSourceAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSourceAddressActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbSourceAddress, gridBagConstraints);

        jtfSourceAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSourceAddress.setText("0");
        jtfSourceAddress.setAlignmentX(0.0F);
        jtfSourceAddress.setAlignmentY(0.0F);
        jtfSourceAddress.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfSourceAddress.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfSourceAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSourceAddressActionPerformed(evt);
            }
        });
        jtfSourceAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSourceAddressFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfSourceAddress, gridBagConstraints);

        jbDestinationAddress.setText(bundle.getString("PanelInternetIP.jbDestinationAddress.text")); // NOI18N
        jbDestinationAddress.setToolTipText(bundle.getString("PanelInternetIP.jbDestinationAddress.tooltip")); // NOI18N
        jbDestinationAddress.setEnabled(false);
        jbDestinationAddress.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbDestinationAddress.setMaximumSize(new java.awt.Dimension(0, 0));
        jbDestinationAddress.setMinimumSize(new java.awt.Dimension(0, 0));
        jbDestinationAddress.setPreferredSize(new java.awt.Dimension(0, 0));
        jbDestinationAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDestinationAddressActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbDestinationAddress, gridBagConstraints);

        jtfDestinationAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDestinationAddress.setText("0");
        jtfDestinationAddress.setAlignmentX(0.0F);
        jtfDestinationAddress.setAlignmentY(0.0F);
        jtfDestinationAddress.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfDestinationAddress.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfDestinationAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDestinationAddressActionPerformed(evt);
            }
        });
        jtfDestinationAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDestinationAddressFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfDestinationAddress, gridBagConstraints);

        jbOptions.setText(bundle.getString("PanelInternetIP.jbOptions.text")); // NOI18N
        jbOptions.setToolTipText(bundle.getString("PanelInternetIP.jbOptions.tooltip")); // NOI18N
        jbOptions.setEnabled(false);
        jbOptions.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbOptions.setMaximumSize(new java.awt.Dimension(0, 0));
        jbOptions.setMinimumSize(new java.awt.Dimension(0, 0));
        jbOptions.setPreferredSize(new java.awt.Dimension(0, 0));
        jbOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOptionsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jbOptions, gridBagConstraints);

        jtfOptions.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfOptions.setText("0");
        jtfOptions.setAlignmentX(0.0F);
        jtfOptions.setAlignmentY(0.0F);
        jtfOptions.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfOptions.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfOptionsActionPerformed(evt);
            }
        });
        jtfOptions.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfOptionsFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfOptions, gridBagConstraints);

        jlBitOffset.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBitOffset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBitOffset.setText("Bit Offset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.06;
        add(jlBitOffset, gridBagConstraints);

        jlBi0_1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBi0_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBi0_1.setText("00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBi0_1, gridBagConstraints);

        jlBit1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit1.setText("01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit1, gridBagConstraints);

        jlBit2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit2.setText("02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit2, gridBagConstraints);

        jlBit3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit3.setText("03");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit3, gridBagConstraints);

        jlBit4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit4.setText("04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit4, gridBagConstraints);

        jlBit5.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit5.setText("05");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit5, gridBagConstraints);

        jlBit6.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit6.setText("06");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit6, gridBagConstraints);

        jlBit7.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit7.setText("07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit7, gridBagConstraints);

        jlBit8.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit8.setText("08");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit8, gridBagConstraints);

        jlBit9.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit9.setText("09");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit9, gridBagConstraints);

        jlBit10.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit10.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit10, gridBagConstraints);

        jlBit11.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit11.setText("11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit11, gridBagConstraints);

        jlBit12.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit12.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit12, gridBagConstraints);

        jlBit13.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit13.setText("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit13, gridBagConstraints);

        jlBit14.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit14.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit14, gridBagConstraints);

        jlBit15.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit15.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit15, gridBagConstraints);

        jlBit16.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit16.setText("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit16, gridBagConstraints);

        jlBit17.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit17.setText("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit17, gridBagConstraints);

        jlBit18.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit18.setText("18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit18, gridBagConstraints);

        jlBit19.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit19.setText("19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit19, gridBagConstraints);

        jlBit20.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit20.setText("20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit20, gridBagConstraints);

        jlBit21.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit21.setText("21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit21, gridBagConstraints);

        jlBit22.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit22.setText("22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit22, gridBagConstraints);

        jlBit23.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit23.setText("23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit23, gridBagConstraints);

        jlbit24.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlbit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbit24.setText("24");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlbit24, gridBagConstraints);

        jlBit25.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit25.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit25, gridBagConstraints);

        jlBit26.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit26.setText("26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit26, gridBagConstraints);

        jlBit27.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit27.setText("27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit27, gridBagConstraints);

        jlBit28.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit28.setText("28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit28, gridBagConstraints);

        jlBit29.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit29.setText("29");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit29, gridBagConstraints);

        jlBit30.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit30.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        add(jlBit30, gridBagConstraints);

        jlBit31.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit31.setText("31");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
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
        gridBagConstraints.weighty = 0.125;
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
        gridBagConstraints.weighty = 0.125;
        add(jlBit32, gridBagConstraints);

        jlBit64.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit64.setText("64");
        jlBit64.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        add(jlBit64, gridBagConstraints);

        jlBit96.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit96.setText("96");
        jlBit96.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        add(jlBit96, gridBagConstraints);

        jlBit128.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit128.setText("128");
        jlBit128.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        add(jlBit128, gridBagConstraints);

        jlBit160.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit160.setText("160");
        jlBit160.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        add(jlBit160, gridBagConstraints);

        jbData.setBackground(new java.awt.Color(255, 255, 153));
        jbData.setText(bundle.getString("PanelInternetIP.jbData.text")); // NOI18N
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.187;
        add(jbData, gridBagConstraints);

        jspProtocolDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), bundle.getString("PanelInternetIP.jspProtocolDescription.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black)); // NOI18N

        jepProtocolDescription.setEditable(false);
        jspProtocolDescription.setViewportView(jepProtocolDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 33;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jspProtocolDescription, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfOptionsFocusLost
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsFocusLost

    private void jtfOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfOptionsActionPerformed
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsActionPerformed

    private void jbOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOptionsActionPerformed
        jtfOptions.setVisible(true);
        jbOptions.setVisible(false);
        jtfOptions.requestFocus();
    }//GEN-LAST:event_jbOptionsActionPerformed

    private void jtfDestinationAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationAddressFocusLost
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationAddressFocusLost

    private void jtfDestinationAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDestinationAddressActionPerformed
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationAddressActionPerformed

    private void jbDestinationAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinationAddressActionPerformed
        jtfDestinationAddress.setVisible(true);
        jbDestinationAddress.setVisible(false);
        jtfDestinationAddress.requestFocus();
    }//GEN-LAST:event_jbDestinationAddressActionPerformed

    private void jtfSourceAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSourceAddressFocusLost
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceAddressFocusLost

    private void jtfSourceAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSourceAddressActionPerformed
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceAddressActionPerformed

    private void jtfChecksumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfChecksumFocusLost
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumFocusLost

    private void jtfChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfChecksumActionPerformed
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumActionPerformed

    private void jbSourceAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSourceAddressActionPerformed
        jtfSourceAddress.setVisible(true);
        jbSourceAddress.setVisible(false);
        jtfSourceAddress.requestFocus();
    }//GEN-LAST:event_jbSourceAddressActionPerformed

    private void jbChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChecksumActionPerformed
        jtfChecksum.setVisible(true);
        jbChecksum.setVisible(false);
        jtfChecksum.requestFocus();
    }//GEN-LAST:event_jbChecksumActionPerformed

    private void jtfProtocolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfProtocolFocusLost
        updateProtocolButton();
    }//GEN-LAST:event_jtfProtocolFocusLost

    private void jtfProtocolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfProtocolActionPerformed
        updateProtocolButton();
    }//GEN-LAST:event_jtfProtocolActionPerformed

    private void jbProtocolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProtocolActionPerformed
        jtfProtocol.setVisible(true);
        jbProtocol.setVisible(false);
        jtfProtocol.requestFocus();
    }//GEN-LAST:event_jbProtocolActionPerformed

    private void jtfTimeToLiveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTimeToLiveFocusLost
        updateTimeToLiveButton();
    }//GEN-LAST:event_jtfTimeToLiveFocusLost

    private void jtfTimeToLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTimeToLiveActionPerformed
        updateTimeToLiveButton();
    }//GEN-LAST:event_jtfTimeToLiveActionPerformed

    private void jbTimeToLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTimeToLiveActionPerformed
        jtfTimeToLive.setVisible(true);
        jbTimeToLive.setVisible(false);
        jtfTimeToLive.requestFocus();
    }//GEN-LAST:event_jbTimeToLiveActionPerformed

    private void jtfServiceTypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfServiceTypeFocusLost
        updateServiceTypeButton();
    }//GEN-LAST:event_jtfServiceTypeFocusLost

    private void jtfServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfServiceTypeActionPerformed
        updateServiceTypeButton();
    }//GEN-LAST:event_jtfServiceTypeActionPerformed

    private void jbServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbServiceTypeActionPerformed
        jtfServiceType.setVisible(true);
        jbServiceType.setVisible(false);
        jtfServiceType.requestFocus();
    }//GEN-LAST:event_jbServiceTypeActionPerformed

    private void jtfHeaderLenghtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfHeaderLenghtFocusLost
        updateHeaderLenghtButton();
    }//GEN-LAST:event_jtfHeaderLenghtFocusLost

    private void jtfHeaderLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfHeaderLenghtActionPerformed
        updateHeaderLenghtButton();
    }//GEN-LAST:event_jtfHeaderLenghtActionPerformed

    private void jbHeaderLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHeaderLenghtActionPerformed
        jtfHeaderLenght.setVisible(true);
        jbHeaderLenght.setVisible(false);
        jtfHeaderLenght.requestFocus();
    }//GEN-LAST:event_jbHeaderLenghtActionPerformed

    private void jtfVersionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfVersionFocusLost
        updateVersionButton();
    }//GEN-LAST:event_jtfVersionFocusLost

    private void jtfVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfVersionActionPerformed
        updateVersionButton();
    }//GEN-LAST:event_jtfVersionActionPerformed

    private void jbVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVersionActionPerformed

        jtfVersion.setVisible(true);
        jbVersion.setVisible(false);
        jtfVersion.requestFocus();
    }//GEN-LAST:event_jbVersionActionPerformed

    private void jtfFragmentOffsetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFragmentOffsetFocusLost
        updateFragmentOffsetButton();
    }//GEN-LAST:event_jtfFragmentOffsetFocusLost

    private void jtfFragmentOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFragmentOffsetActionPerformed
        updateFragmentOffsetButton();
    }//GEN-LAST:event_jtfFragmentOffsetActionPerformed

    private void jbFragmentOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFragmentOffsetActionPerformed
        jtfFragmentOffset.setVisible(true);
        jbFragmentOffset.setVisible(false);
        jtfFragmentOffset.requestFocus();
    }//GEN-LAST:event_jbFragmentOffsetActionPerformed

    private void jbMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMFActionPerformed
        if (jbMF.getBackground() == enableButtonColor) {
            jbMF.setBackground(disableButtonColor);
        } else {
            jbMF.setBackground(enableButtonColor);
        }
    }//GEN-LAST:event_jbMFActionPerformed

    private void jbDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDFActionPerformed
        if (jbDF.getBackground() == enableButtonColor) {
            jbDF.setBackground(disableButtonColor);
        } else {
            jbDF.setBackground(enableButtonColor);
        }
    }//GEN-LAST:event_jbDFActionPerformed

    private void jtfIdentificationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdentificationFocusLost
        updateIdentificationButton();
    }//GEN-LAST:event_jtfIdentificationFocusLost

    private void jtfIdentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdentificationActionPerformed
        updateIdentificationButton();
    }//GEN-LAST:event_jtfIdentificationActionPerformed

    private void jbIdentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIdentificationActionPerformed
        jtfIdentification.setVisible(true);
        jbIdentification.setVisible(false);
        jtfIdentification.requestFocus();
    }//GEN-LAST:event_jbIdentificationActionPerformed

    private void jbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataActionPerformed
        setPreviousTab();
    }//GEN-LAST:event_jbDataActionPerformed

    private void jtfTotalLengthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTotalLengthFocusLost
        updateTotalLengthButton();
    }//GEN-LAST:event_jtfTotalLengthFocusLost

    private void jtfTotalLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTotalLengthActionPerformed
        updateTotalLengthButton();
    }//GEN-LAST:event_jtfTotalLengthActionPerformed

    private void jbTotalLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTotalLengthActionPerformed
        jtfTotalLength.setVisible(true);
        jbTotalLength.setVisible(false);
        jtfTotalLength.requestFocus();
    }//GEN-LAST:event_jbTotalLengthActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private simulator.ToolTipButton jbChecksum;
    private simulator.ToolTipButton jbDF;
    private simulator.ToolTipButton jbData;
    private simulator.ToolTipButton jbDestinationAddress;
    private simulator.ToolTipButton jbFragmentOffset;
    private simulator.ToolTipButton jbHeaderLenght;
    private simulator.ToolTipButton jbIdentification;
    private simulator.ToolTipButton jbMF;
    private simulator.ToolTipButton jbOptions;
    private simulator.ToolTipButton jbProtocol;
    private simulator.ToolTipButton jbReserved;
    private simulator.ToolTipButton jbServiceType;
    private simulator.ToolTipButton jbSourceAddress;
    private simulator.ToolTipButton jbTimeToLive;
    private simulator.ToolTipButton jbTotalLength;
    private simulator.ToolTipButton jbVersion;
    private javax.swing.JEditorPane jepProtocolDescription;
    private javax.swing.JLabel jlBi0_1;
    private javax.swing.JLabel jlBit0_2;
    private javax.swing.JLabel jlBit1;
    private javax.swing.JLabel jlBit10;
    private javax.swing.JLabel jlBit11;
    private javax.swing.JLabel jlBit12;
    private javax.swing.JLabel jlBit128;
    private javax.swing.JLabel jlBit13;
    private javax.swing.JLabel jlBit14;
    private javax.swing.JLabel jlBit15;
    private javax.swing.JLabel jlBit16;
    private javax.swing.JLabel jlBit160;
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
    private javax.swing.JLabel jlBit64;
    private javax.swing.JLabel jlBit7;
    private javax.swing.JLabel jlBit8;
    private javax.swing.JLabel jlBit9;
    private javax.swing.JLabel jlBit96;
    private javax.swing.JLabel jlBitOffset;
    private javax.swing.JLabel jlbit24;
    private javax.swing.JScrollPane jspProtocolDescription;
    private javax.swing.JTextField jtfChecksum;
    private javax.swing.JTextField jtfDestinationAddress;
    private javax.swing.JTextField jtfFragmentOffset;
    private javax.swing.JTextField jtfHeaderLenght;
    private javax.swing.JTextField jtfIdentification;
    private javax.swing.JTextField jtfOptions;
    private javax.swing.JTextField jtfProtocol;
    private javax.swing.JTextField jtfServiceType;
    private javax.swing.JTextField jtfSourceAddress;
    private javax.swing.JTextField jtfTimeToLive;
    private javax.swing.JTextField jtfTotalLength;
    private javax.swing.JTextField jtfVersion;
    // End of variables declaration//GEN-END:variables
}
