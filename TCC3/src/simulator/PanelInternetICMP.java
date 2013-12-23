/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import datagram.DatagramInternetICMP;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Cristiano
 */
public class PanelInternetICMP extends PanelInternet {

    /**
     * PanelInternet Creates new form PanelInternetICMP
     */
    LinkedHashMap<String, Integer> types = null;
    LinkedHashMap<Integer, String[]> codes = null;

    /**
     * Creates new form PanelInternetICMP
     */
    public PanelInternetICMP() {
        initComponents();

        //IP Data
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

        //ICMP Data
        DatagramInternetICMP icmpDatagram = new DatagramInternetICMP();
        types = icmpDatagram.getTypes();
        Iterator<String> it = types.keySet().iterator();
        while (it.hasNext()) {
            String type = it.next();
            jcbICMPType.addItem(type);
        }
        codes = icmpDatagram.getCodes();

        jcbICMPType.setVisible(false);
        jcbICMPCode.setVisible(false);
        jtfChecksumICMP.setVisible(false);

        jepProtocolDescription.setContentType("text/html");
        translate();
    }

    @Override
    public void setDatagram(DatagramInternet icmpDatagram) {
        //IP Data
        ((DatagramInternetICMP) icmpDatagram).setVersion(this.getVersion());
        ((DatagramInternetICMP) icmpDatagram).setHeaderLenght(this.getHeaderLenght());
        ((DatagramInternetICMP) icmpDatagram).setServiceType(this.getServiceType());
        ((DatagramInternetICMP) icmpDatagram).setTotalLenght(this.getTotalLength());
        ((DatagramInternetICMP) icmpDatagram).setIdentification(this.getIdentification());
        ((DatagramInternetICMP) icmpDatagram).setDF(this.getDF());
        ((DatagramInternetICMP) icmpDatagram).setMF(this.getMF());
        ((DatagramInternetICMP) icmpDatagram).setFragmentOffset(this.getFragmentOffset());
        ((DatagramInternetICMP) icmpDatagram).setTimeToLive(this.getTimeToLive());
        ((DatagramInternetICMP) icmpDatagram).setProtocol(this.getProtocol());
        ((DatagramInternetICMP) icmpDatagram).setHeaderChecksum(this.getChecksum());
        ((DatagramInternetICMP) icmpDatagram).setSourceIPAddress(this.getSourceAddress());
        ((DatagramInternetICMP) icmpDatagram).setDestinationIPAddress(this.getDestinationAddress());
        ((DatagramInternetICMP) icmpDatagram).setOptions(this.getOptions());

        //ICMP Data
        ((DatagramInternetICMP) icmpDatagram).setICMPType(this.getICMPType());
        ((DatagramInternetICMP) icmpDatagram).setCode(this.getICMPCode());
        ((DatagramInternetICMP) icmpDatagram).setDataICMP(this.getICMPData());
    }

    @Override
    public void setPanelFields(DatagramInternet icmpDatagram) {
        //IP Data
        this.setVersion(((DatagramInternetICMP) icmpDatagram).getVersion());
        this.setHeaderLenght(((DatagramInternetICMP) icmpDatagram).getHeaderLenght());
        this.setServiceType(((DatagramInternetICMP) icmpDatagram).getServiceType());
        this.setTotalLenght(((DatagramInternetICMP) icmpDatagram).getTotalLenght());
        this.setIdentification(((DatagramInternetICMP) icmpDatagram).getIdentification());
        this.setDF(((DatagramInternetICMP) icmpDatagram).isDF());
        this.setMF(((DatagramInternetICMP) icmpDatagram).isMF());
        this.setFragmentOffset(((DatagramInternetICMP) icmpDatagram).getFragmentOffset());
        this.setTimeToLive(((DatagramInternetICMP) icmpDatagram).getTimeToLive());
        this.setProtocol(((DatagramInternetICMP) icmpDatagram).getProtocol());
        this.setChecksum(((DatagramInternetICMP) icmpDatagram).getHeaderChecksum());
        this.setSourceAddress(((DatagramInternetICMP) icmpDatagram).getSourceIPAddress());
        this.setDestinationAddress(((DatagramInternetICMP) icmpDatagram).getDestinationIPAddress());
        this.setOptions(((DatagramInternetICMP) icmpDatagram).getOptions());

        //ICMP Data
        this.setICMPType(((DatagramInternetICMP) icmpDatagram).getICMPType());
        this.setICMPCode(((DatagramInternetICMP) icmpDatagram).getCode());
        this.setICMPData(((DatagramInternetICMP) icmpDatagram).getDataICMP());
    }

    @Override
    public void setEditable(boolean edit) {
        //IP Data
        jbDF.setEnabled(edit);
        jbFragmentOffset.setEnabled(edit);
        jbIdentification.setEnabled(edit);
        jbMF.setEnabled(edit);
        jbServiceType.setEnabled(edit);
        jbTimeToLive.setEnabled(edit);
        jbProtocol.setEnabled(edit);
        jbVersion.setEnabled(edit);
        jbHeaderLenght.setEnabled(edit);

        //ICMP Data
        jbICMPType.setEnabled(edit);
        jbICMPCode.setEnabled(edit);
        jbICMPData.setEnabled(edit);
    }

    /**
     * Return the ICMP type value
     *
     * @return the ICMP type value
     */
    public String getICMPType() {
        return ((String) jcbICMPType.getSelectedItem());
    }

    /**
     * Sets the ICMP type value
     *
     * @param icmpType the ICMP type value
     */
    public void setICMPType(String icmpType) {
        jcbICMPType.setSelectedItem(icmpType);
        updateICMPTypeButton();
    }

    /**
     * Return the ICMP code value
     *
     * @return the ICMP code value
     */
    public int getICMPCode() {
        return jcbICMPCode.getSelectedIndex();
    }

    /**
     * Sets the ICMP code value
     *
     * @param icmpCode the ICMP code value
     */
    public void setICMPCode(int icmpCode) {
        jcbICMPCode.setSelectedIndex(icmpCode);
        updateICMPCodeButton();
    }

    /**
     * Return the ICMP data value
     *
     * @return the ICMP data value
     */
    public String getICMPData() {
        return jtfICMPData.getText();
    }

    /**
     * Sets the ICMP data value
     *
     * @param icmpData the ICMP data value
     */
    public void setICMPData(String icmpData) {
        jtfICMPData.setText(icmpData);
        updateICMPDataButton();
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
     * Change the ICMP type button text
     */
    private void updateICMPTypeButton() {
        if (jcbICMPType.getSelectedItem() != null) {
            jcbICMPCode.removeAllItems();

            if (((String) (jcbICMPType.getSelectedItem())).trim().equals("")) {
                jbICMPType.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPType.text"));
            } else {
                String type = String.valueOf(jcbICMPType.getSelectedItem());
                jbICMPType.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPType.text") + ": " + type);

                //change the icmp codes values
                DatagramInternetICMP datagramICMP = new DatagramInternetICMP();
                codes = datagramICMP.getCodes();
                int icmpTypes = types.get(type);

                String[] icmpCodes = codes.get(new Integer(icmpTypes));
                for (int i = 0; i < icmpCodes.length; i++) {
                    jcbICMPCode.addItem(icmpCodes[i]);
                }
                jcbICMPCode.setSelectedIndex(0);

                updateICMPCodeButton();
            }
        }
        jbICMPType.setVisible(true);
        jcbICMPType.setVisible(false);
    }

    /**
     * Change the ICMP code button text
     */
    private void updateICMPCodeButton() {
        if (jcbICMPCode.getSelectedItem() != null) {

            if (((String) (jcbICMPCode.getSelectedItem())).trim().equals("")) {
                jbICMPCode.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPCode.text"));
            } else {
                String codigo = String.valueOf(jcbICMPCode.getSelectedItem());
                jbICMPCode.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPCode.text") + ": " + codigo);
            }
        }
        jbICMPCode.setVisible(true);
        jcbICMPCode.setVisible(false);
    }

    /**
     * Change the IP Checksum button text
     */
    private void updateICMPChecksumButton() {
        if (jtfChecksumICMP.getText().trim().equals("")) {
            jbChecksumICMP.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbChecksumICMP.text"));
        } else {
            jbChecksumICMP.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbChecksumICMP.text") + ": " + jtfChecksum.getText());
        }
        jbChecksumICMP.setVisible(true);
        jtfChecksumICMP.setVisible(false);
    }

    /**
     * Change the ICMP data button text
     */
    private void updateICMPDataButton() {
        if (jtfICMPData.getText().trim().equals("")) {
            jbICMPData.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPData.text"));
        } else {
            jbICMPData.setText(Config.getInstance().getBundle().getString("PanelInternetICMP.jbICMPData.text") + ": " + jtfICMPData.getText());
        }
        jbICMPData.setVisible(true);
        jtfICMPData.setVisible(false);
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

        updateICMPTypeButton();
        updateICMPCodeButton();
        updateICMPChecksumButton();
        updateICMPDataButton();

        ResourceBundle bundle = Config.getInstance().getBundle();

        jbDF.setText(bundle.getString("PanelInternetIP.jbDF.text"));
        jbMF.setText(bundle.getString("PanelInternetIP.jbMF.text"));

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelInternetICMP.jspProtocolDescription.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK, 1, true));
        jspProtocolDescription.setBorder(titledBorder);
        jepProtocolDescription.setText(bundle.getString("PanelInternetICMP.jepProtocolDescription.text"));

        TitledBorder titledBorderIP = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelInternetICMP.jpIP.title"));
        titledBorderIP.setBorder(new LineBorder(Color.BLACK));
        jpIP.setBorder(titledBorderIP);

        TitledBorder titledBorderICMP = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelInternetICMP.jpICMP.title"));
        titledBorderICMP.setBorder(new LineBorder(Color.BLACK));
        jpICMP.setBorder(titledBorderICMP);

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

        jbICMPType.setToolTipText(bundle.getString("PanelInternetICMP.jbICMPType.tooltip"));
        jbICMPCode.setToolTipText(bundle.getString("PanelInternetICMP.jbICMPCode.tooltip"));
        jbChecksumICMP.setToolTipText(bundle.getString("PanelInternetICMP.jbChecksumICMP.tooltip"));
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

        jspProtocolDescription = new javax.swing.JScrollPane();
        jepProtocolDescription = new javax.swing.JEditorPane();
        jpIP = new javax.swing.JPanel();
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
        jpICMP = new javax.swing.JPanel();
        jbICMPType = new simulator.ToolTipButton();
        jcbICMPType = new javax.swing.JComboBox();
        jbICMPCode = new simulator.ToolTipButton();
        jcbICMPCode = new javax.swing.JComboBox();
        jbChecksumICMP = new simulator.ToolTipButton();
        jtfChecksumICMP = new javax.swing.JTextField();
        jbICMPData = new simulator.ToolTipButton();
        jtfICMPData = new javax.swing.JTextField();
        jlBitOffset1 = new javax.swing.JLabel();
        jlBi0_2 = new javax.swing.JLabel();
        jlBit24 = new javax.swing.JLabel();
        jlBit33 = new javax.swing.JLabel();
        jlBit34 = new javax.swing.JLabel();
        jlBit35 = new javax.swing.JLabel();
        jlBit36 = new javax.swing.JLabel();
        jlBit37 = new javax.swing.JLabel();
        jlBit38 = new javax.swing.JLabel();
        jlBit39 = new javax.swing.JLabel();
        jlBit40 = new javax.swing.JLabel();
        jlBit41 = new javax.swing.JLabel();
        jlBit42 = new javax.swing.JLabel();
        jlBit43 = new javax.swing.JLabel();
        jlBit44 = new javax.swing.JLabel();
        jlBit45 = new javax.swing.JLabel();
        jlBit46 = new javax.swing.JLabel();
        jlBit47 = new javax.swing.JLabel();
        jlBit48 = new javax.swing.JLabel();
        jlBit49 = new javax.swing.JLabel();
        jlBit50 = new javax.swing.JLabel();
        jlBit51 = new javax.swing.JLabel();
        jlBit52 = new javax.swing.JLabel();
        jlBit53 = new javax.swing.JLabel();
        jlBit54 = new javax.swing.JLabel();
        jlbit25 = new javax.swing.JLabel();
        jlBit55 = new javax.swing.JLabel();
        jlBit56 = new javax.swing.JLabel();
        jlBit57 = new javax.swing.JLabel();
        jlBit58 = new javax.swing.JLabel();
        jlBit59 = new javax.swing.JLabel();
        jlBit60 = new javax.swing.JLabel();
        jlBit61 = new javax.swing.JLabel();
        jlBit0_3 = new javax.swing.JLabel();
        jlBit97 = new javax.swing.JLabel();

        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(292, 70));
        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        jspProtocolDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), bundle.getString("PanelInternetICMP.jspProtocolDescription.title"))); // NOI18N

        jepProtocolDescription.setEditable(false);
        jepProtocolDescription.setText(bundle.getString("PanelInternetIP.jepProtocolDescription.text")); // NOI18N
        jspProtocolDescription.setViewportView(jepProtocolDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jspProtocolDescription, gridBagConstraints);

        jpIP.setBackground(backgroundColorInternet);
        jpIP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("PanelInternetICMP.jpIP.title"))); // NOI18N
        jpIP.setLayout(new java.awt.GridBagLayout());

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
        jpIP.add(jbVersion, gridBagConstraints);

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
        jpIP.add(jtfVersion, gridBagConstraints);

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
        jpIP.add(jbHeaderLenght, gridBagConstraints);

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
        jpIP.add(jtfHeaderLenght, gridBagConstraints);

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
        jpIP.add(jbServiceType, gridBagConstraints);

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
        jpIP.add(jtfServiceType, gridBagConstraints);

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
        jpIP.add(jbTotalLength, gridBagConstraints);

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
        jpIP.add(jtfTotalLength, gridBagConstraints);

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
        jpIP.add(jbIdentification, gridBagConstraints);

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
        jpIP.add(jtfIdentification, gridBagConstraints);

        jbReserved.setActionCommand("RST");
        jbReserved.setEnabled(false);
        jbReserved.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbReserved.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbReserved.setMaximumSize(new java.awt.Dimension(0, 0));
        jbReserved.setMinimumSize(new java.awt.Dimension(0, 0));
        jbReserved.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpIP.add(jbReserved, gridBagConstraints);

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
        jpIP.add(jbDF, gridBagConstraints);

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
        jpIP.add(jbMF, gridBagConstraints);

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
        jpIP.add(jbFragmentOffset, gridBagConstraints);

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
        jpIP.add(jtfFragmentOffset, gridBagConstraints);

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
        jpIP.add(jbTimeToLive, gridBagConstraints);

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
        jpIP.add(jtfTimeToLive, gridBagConstraints);

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
        jpIP.add(jbProtocol, gridBagConstraints);

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
        jpIP.add(jtfProtocol, gridBagConstraints);

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
        jpIP.add(jbChecksum, gridBagConstraints);

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
        jpIP.add(jtfChecksum, gridBagConstraints);

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
        jpIP.add(jbSourceAddress, gridBagConstraints);

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
        jpIP.add(jtfSourceAddress, gridBagConstraints);

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
        jpIP.add(jbDestinationAddress, gridBagConstraints);

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
        jpIP.add(jtfDestinationAddress, gridBagConstraints);

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
        jpIP.add(jbOptions, gridBagConstraints);

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
        jpIP.add(jtfOptions, gridBagConstraints);

        jlBitOffset.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBitOffset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBitOffset.setText("Bit Offset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.06;
        jpIP.add(jlBitOffset, gridBagConstraints);

        jlBi0_1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBi0_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBi0_1.setText("00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBi0_1, gridBagConstraints);

        jlBit1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit1.setText("01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit1, gridBagConstraints);

        jlBit2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit2.setText("02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit2, gridBagConstraints);

        jlBit3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit3.setText("03");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit3, gridBagConstraints);

        jlBit4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit4.setText("04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit4, gridBagConstraints);

        jlBit5.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit5.setText("05");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit5, gridBagConstraints);

        jlBit6.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit6.setText("06");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit6, gridBagConstraints);

        jlBit7.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit7.setText("07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit7, gridBagConstraints);

        jlBit8.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit8.setText("08");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit8, gridBagConstraints);

        jlBit9.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit9.setText("09");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit9, gridBagConstraints);

        jlBit10.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit10.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit10, gridBagConstraints);

        jlBit11.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit11.setText("11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit11, gridBagConstraints);

        jlBit12.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit12.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit12, gridBagConstraints);

        jlBit13.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit13.setText("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit13, gridBagConstraints);

        jlBit14.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit14.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit14, gridBagConstraints);

        jlBit15.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit15.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit15, gridBagConstraints);

        jlBit16.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit16.setText("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit16, gridBagConstraints);

        jlBit17.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit17.setText("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit17, gridBagConstraints);

        jlBit18.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit18.setText("18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit18, gridBagConstraints);

        jlBit19.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit19.setText("19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit19, gridBagConstraints);

        jlBit20.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit20.setText("20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit20, gridBagConstraints);

        jlBit21.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit21.setText("21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit21, gridBagConstraints);

        jlBit22.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit22.setText("22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit22, gridBagConstraints);

        jlBit23.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit23.setText("23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit23, gridBagConstraints);

        jlbit24.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlbit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbit24.setText("24");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlbit24, gridBagConstraints);

        jlBit25.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit25.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit25, gridBagConstraints);

        jlBit26.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit26.setText("26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit26, gridBagConstraints);

        jlBit27.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit27.setText("27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit27, gridBagConstraints);

        jlBit28.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit28.setText("28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit28, gridBagConstraints);

        jlBit29.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit29.setText("29");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit29, gridBagConstraints);

        jlBit30.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit30.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit30, gridBagConstraints);

        jlBit31.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit31.setText("31");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpIP.add(jlBit31, gridBagConstraints);

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
        jpIP.add(jlBit0_2, gridBagConstraints);

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
        jpIP.add(jlBit32, gridBagConstraints);

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
        jpIP.add(jlBit64, gridBagConstraints);

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
        jpIP.add(jlBit96, gridBagConstraints);

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
        jpIP.add(jlBit128, gridBagConstraints);

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
        jpIP.add(jlBit160, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        add(jpIP, gridBagConstraints);

        jpICMP.setBackground(backgroundColorInternet);
        jpICMP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("PanelInternetICMP.jpICMP.title"))); // NOI18N
        jpICMP.setLayout(new java.awt.GridBagLayout());

        jbICMPType.setText(bundle.getString("PanelInternetICMP.jbICMPType.text")); // NOI18N
        jbICMPType.setToolTipText(bundle.getString("PanelInternetICMP.jbICMPType.tooltip")); // NOI18N
        jbICMPType.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbICMPType.setMaximumSize(new java.awt.Dimension(0, 0));
        jbICMPType.setMinimumSize(new java.awt.Dimension(0, 0));
        jbICMPType.setPreferredSize(new java.awt.Dimension(0, 0));
        jbICMPType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbICMPTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jbICMPType, gridBagConstraints);

        jcbICMPType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbICMPTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jcbICMPType, gridBagConstraints);

        jbICMPCode.setText(bundle.getString("PanelInternetICMP.jbICMPCode.text")); // NOI18N
        jbICMPCode.setToolTipText(bundle.getString("PanelInternetICMP.jbICMPCode.tooltip")); // NOI18N
        jbICMPCode.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbICMPCode.setMaximumSize(new java.awt.Dimension(0, 0));
        jbICMPCode.setMinimumSize(new java.awt.Dimension(0, 0));
        jbICMPCode.setPreferredSize(new java.awt.Dimension(0, 0));
        jbICMPCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbICMPCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jbICMPCode, gridBagConstraints);

        jcbICMPCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbICMPCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jcbICMPCode, gridBagConstraints);

        jbChecksumICMP.setText(bundle.getString("PanelInternetICMP.jbChecksumICMP.text")); // NOI18N
        jbChecksumICMP.setToolTipText(bundle.getString("PanelInternetICMP.jbChecksumICMP.tooltip")); // NOI18N
        jbChecksumICMP.setEnabled(false);
        jbChecksumICMP.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbChecksumICMP.setMaximumSize(new java.awt.Dimension(0, 0));
        jbChecksumICMP.setMinimumSize(new java.awt.Dimension(0, 0));
        jbChecksumICMP.setPreferredSize(new java.awt.Dimension(0, 0));
        jbChecksumICMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChecksumICMPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jbChecksumICMP, gridBagConstraints);

        jtfChecksumICMP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfChecksumICMP.setText("0");
        jtfChecksumICMP.setAlignmentX(0.0F);
        jtfChecksumICMP.setAlignmentY(0.0F);
        jtfChecksumICMP.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfChecksumICMP.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfChecksumICMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfChecksumICMPActionPerformed(evt);
            }
        });
        jtfChecksumICMP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfChecksumICMPFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jtfChecksumICMP, gridBagConstraints);

        jbICMPData.setText(bundle.getString("PanelInternetICMP.jbICMPData.text")); // NOI18N
        jbICMPData.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbICMPData.setMaximumSize(new java.awt.Dimension(0, 0));
        jbICMPData.setMinimumSize(new java.awt.Dimension(0, 0));
        jbICMPData.setPreferredSize(new java.awt.Dimension(0, 0));
        jbICMPData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbICMPDataActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jbICMPData, gridBagConstraints);

        jtfICMPData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfICMPData.setText("0");
        jtfICMPData.setAlignmentX(0.0F);
        jtfICMPData.setAlignmentY(0.0F);
        jtfICMPData.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfICMPData.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfICMPData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfICMPDataActionPerformed(evt);
            }
        });
        jtfICMPData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfICMPDataFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpICMP.add(jtfICMPData, gridBagConstraints);

        jlBitOffset1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBitOffset1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBitOffset1.setText("Bit Offset");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.06;
        jpICMP.add(jlBitOffset1, gridBagConstraints);

        jlBi0_2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBi0_2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBi0_2.setText("00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBi0_2, gridBagConstraints);

        jlBit24.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit24.setText("01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit24, gridBagConstraints);

        jlBit33.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit33.setText("02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit33, gridBagConstraints);

        jlBit34.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit34.setText("03");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit34, gridBagConstraints);

        jlBit35.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit35.setText("04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit35, gridBagConstraints);

        jlBit36.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit36.setText("05");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit36, gridBagConstraints);

        jlBit37.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit37.setText("06");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit37, gridBagConstraints);

        jlBit38.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit38.setText("07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit38, gridBagConstraints);

        jlBit39.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit39.setText("08");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit39, gridBagConstraints);

        jlBit40.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit40.setText("09");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit40, gridBagConstraints);

        jlBit41.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit41.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit41, gridBagConstraints);

        jlBit42.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit42.setText("11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit42, gridBagConstraints);

        jlBit43.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit43.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit43, gridBagConstraints);

        jlBit44.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit44.setText("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit44, gridBagConstraints);

        jlBit45.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit45.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit45, gridBagConstraints);

        jlBit46.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit46.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit46, gridBagConstraints);

        jlBit47.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit47.setText("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit47, gridBagConstraints);

        jlBit48.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit48.setText("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit48, gridBagConstraints);

        jlBit49.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit49.setText("18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit49, gridBagConstraints);

        jlBit50.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit50.setText("19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit50, gridBagConstraints);

        jlBit51.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit51.setText("20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit51, gridBagConstraints);

        jlBit52.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit52.setText("21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit52, gridBagConstraints);

        jlBit53.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit53.setText("22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit53, gridBagConstraints);

        jlBit54.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit54.setText("23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit54, gridBagConstraints);

        jlbit25.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlbit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbit25.setText("24");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlbit25, gridBagConstraints);

        jlBit55.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit55.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit55, gridBagConstraints);

        jlBit56.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit56.setText("26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit56, gridBagConstraints);

        jlBit57.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit57.setText("27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit57, gridBagConstraints);

        jlBit58.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit58.setText("28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit58, gridBagConstraints);

        jlBit59.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit59.setText("29");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit59, gridBagConstraints);

        jlBit60.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit60.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit60, gridBagConstraints);

        jlBit61.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlBit61.setText("31");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jpICMP.add(jlBit61, gridBagConstraints);

        jlBit0_3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit0_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit0_3.setText("192");
        jlBit0_3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        jpICMP.add(jlBit0_3, gridBagConstraints);

        jlBit97.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jlBit97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBit97.setText("96");
        jlBit97.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.04;
        gridBagConstraints.weighty = 0.125;
        jpICMP.add(jlBit97, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        add(jpICMP, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVersionActionPerformed

        jtfVersion.setVisible(true);
        jbVersion.setVisible(false);
        jtfVersion.requestFocus();
    }//GEN-LAST:event_jbVersionActionPerformed

    private void jtfVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfVersionActionPerformed
        updateVersionButton();
    }//GEN-LAST:event_jtfVersionActionPerformed

    private void jtfVersionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfVersionFocusLost
        updateVersionButton();
    }//GEN-LAST:event_jtfVersionFocusLost

    private void jbHeaderLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHeaderLenghtActionPerformed
        jtfHeaderLenght.setVisible(true);
        jbHeaderLenght.setVisible(false);
        jtfHeaderLenght.requestFocus();
    }//GEN-LAST:event_jbHeaderLenghtActionPerformed

    private void jtfHeaderLenghtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfHeaderLenghtActionPerformed
        updateHeaderLenghtButton();
    }//GEN-LAST:event_jtfHeaderLenghtActionPerformed

    private void jtfHeaderLenghtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfHeaderLenghtFocusLost
        updateHeaderLenghtButton();
    }//GEN-LAST:event_jtfHeaderLenghtFocusLost

    private void jbServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbServiceTypeActionPerformed
        jtfServiceType.setVisible(true);
        jbServiceType.setVisible(false);
        jtfServiceType.requestFocus();
    }//GEN-LAST:event_jbServiceTypeActionPerformed

    private void jtfServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfServiceTypeActionPerformed
        updateServiceTypeButton();
    }//GEN-LAST:event_jtfServiceTypeActionPerformed

    private void jtfServiceTypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfServiceTypeFocusLost
        updateServiceTypeButton();
    }//GEN-LAST:event_jtfServiceTypeFocusLost

    private void jbTotalLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTotalLengthActionPerformed
        jtfTotalLength.setVisible(true);
        jbTotalLength.setVisible(false);
        jtfTotalLength.requestFocus();
    }//GEN-LAST:event_jbTotalLengthActionPerformed

    private void jtfTotalLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTotalLengthActionPerformed
        updateTotalLengthButton();
    }//GEN-LAST:event_jtfTotalLengthActionPerformed

    private void jtfTotalLengthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTotalLengthFocusLost
        updateTotalLengthButton();
    }//GEN-LAST:event_jtfTotalLengthFocusLost

    private void jbIdentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIdentificationActionPerformed
        jtfIdentification.setVisible(true);
        jbIdentification.setVisible(false);
        jtfIdentification.requestFocus();
    }//GEN-LAST:event_jbIdentificationActionPerformed

    private void jtfIdentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdentificationActionPerformed
        updateIdentificationButton();
    }//GEN-LAST:event_jtfIdentificationActionPerformed

    private void jtfIdentificationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdentificationFocusLost
        updateIdentificationButton();
    }//GEN-LAST:event_jtfIdentificationFocusLost

    private void jbDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDFActionPerformed
        if (jbDF.getBackground() == enableButtonColor) {
            jbDF.setBackground(disableButtonColor);
        } else {
            jbDF.setBackground(enableButtonColor);
        }
    }//GEN-LAST:event_jbDFActionPerformed

    private void jbMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMFActionPerformed
        if (jbMF.getBackground() == enableButtonColor) {
            jbMF.setBackground(disableButtonColor);
        } else {
            jbMF.setBackground(enableButtonColor);
        }
    }//GEN-LAST:event_jbMFActionPerformed

    private void jbFragmentOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFragmentOffsetActionPerformed
        jtfFragmentOffset.setVisible(true);
        jbFragmentOffset.setVisible(false);
        jtfFragmentOffset.requestFocus();
    }//GEN-LAST:event_jbFragmentOffsetActionPerformed

    private void jtfFragmentOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFragmentOffsetActionPerformed
        updateFragmentOffsetButton();
    }//GEN-LAST:event_jtfFragmentOffsetActionPerformed

    private void jtfFragmentOffsetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFragmentOffsetFocusLost
        updateFragmentOffsetButton();
    }//GEN-LAST:event_jtfFragmentOffsetFocusLost

    private void jbTimeToLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTimeToLiveActionPerformed
        jtfTimeToLive.setVisible(true);
        jbTimeToLive.setVisible(false);
        jtfTimeToLive.requestFocus();
    }//GEN-LAST:event_jbTimeToLiveActionPerformed

    private void jtfTimeToLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTimeToLiveActionPerformed
        updateTimeToLiveButton();
    }//GEN-LAST:event_jtfTimeToLiveActionPerformed

    private void jtfTimeToLiveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTimeToLiveFocusLost
        updateTimeToLiveButton();
    }//GEN-LAST:event_jtfTimeToLiveFocusLost

    private void jbProtocolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProtocolActionPerformed
        jtfProtocol.setVisible(true);
        jbProtocol.setVisible(false);
        jtfProtocol.requestFocus();
    }//GEN-LAST:event_jbProtocolActionPerformed

    private void jtfProtocolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfProtocolActionPerformed
        updateProtocolButton();
    }//GEN-LAST:event_jtfProtocolActionPerformed

    private void jtfProtocolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfProtocolFocusLost
        updateProtocolButton();
    }//GEN-LAST:event_jtfProtocolFocusLost

    private void jbChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChecksumActionPerformed
        jtfChecksum.setVisible(true);
        jbChecksum.setVisible(false);
        jtfChecksum.requestFocus();
    }//GEN-LAST:event_jbChecksumActionPerformed

    private void jtfChecksumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfChecksumActionPerformed
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumActionPerformed

    private void jtfChecksumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfChecksumFocusLost
        updateChecksumButton();
    }//GEN-LAST:event_jtfChecksumFocusLost

    private void jbSourceAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSourceAddressActionPerformed
        jtfSourceAddress.setVisible(true);
        jbSourceAddress.setVisible(false);
        jtfSourceAddress.requestFocus();
    }//GEN-LAST:event_jbSourceAddressActionPerformed

    private void jtfSourceAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSourceAddressActionPerformed
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceAddressActionPerformed

    private void jtfSourceAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSourceAddressFocusLost
        updateSourceAddressButton();
    }//GEN-LAST:event_jtfSourceAddressFocusLost

    private void jbDestinationAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinationAddressActionPerformed
        jtfDestinationAddress.setVisible(true);
        jbDestinationAddress.setVisible(false);
        jtfDestinationAddress.requestFocus();
    }//GEN-LAST:event_jbDestinationAddressActionPerformed

    private void jtfDestinationAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDestinationAddressActionPerformed
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationAddressActionPerformed

    private void jtfDestinationAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationAddressFocusLost
        updateDestinationAddressButton();
    }//GEN-LAST:event_jtfDestinationAddressFocusLost

    private void jbOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOptionsActionPerformed
        jtfOptions.setVisible(true);
        jbOptions.setVisible(false);
        jtfOptions.requestFocus();
    }//GEN-LAST:event_jbOptionsActionPerformed

    private void jtfOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfOptionsActionPerformed
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsActionPerformed

    private void jtfOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfOptionsFocusLost
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsFocusLost

    private void jbICMPCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbICMPCodeActionPerformed
        // TODO add your handling code here:
        jcbICMPCode.setVisible(true);
        jbICMPCode.setVisible(false);
        jcbICMPCode.requestFocus();
    }//GEN-LAST:event_jbICMPCodeActionPerformed

    private void jbChecksumICMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChecksumICMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbChecksumICMPActionPerformed

    private void jtfChecksumICMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfChecksumICMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfChecksumICMPActionPerformed

    private void jtfChecksumICMPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfChecksumICMPFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfChecksumICMPFocusLost

    private void jbICMPDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbICMPDataActionPerformed
        // TODO add your handling code here:
        jtfICMPData.setVisible(true);
        jbICMPData.setVisible(false);
        jtfICMPData.requestFocus();
    }//GEN-LAST:event_jbICMPDataActionPerformed

    private void jtfICMPDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfICMPDataActionPerformed
        // TODO add your handling code here:
        updateICMPDataButton();
    }//GEN-LAST:event_jtfICMPDataActionPerformed

    private void jtfICMPDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfICMPDataFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfICMPDataFocusLost

    private void jbICMPTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbICMPTypeActionPerformed
        // TODO add your handling code here:
        jcbICMPType.setVisible(true);
        jbICMPType.setVisible(false);
        jcbICMPType.requestFocus();
    }//GEN-LAST:event_jbICMPTypeActionPerformed

    private void jcbICMPCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbICMPCodeActionPerformed
        // TODO add your handling code here:
        updateICMPCodeButton();
    }//GEN-LAST:event_jcbICMPCodeActionPerformed

    private void jcbICMPTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbICMPTypeActionPerformed
        // TODO add your handling code here:
        updateICMPTypeButton();
    }//GEN-LAST:event_jcbICMPTypeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private simulator.ToolTipButton jbChecksum;
    private simulator.ToolTipButton jbChecksumICMP;
    private simulator.ToolTipButton jbDF;
    private simulator.ToolTipButton jbDestinationAddress;
    private simulator.ToolTipButton jbFragmentOffset;
    private simulator.ToolTipButton jbHeaderLenght;
    private simulator.ToolTipButton jbICMPCode;
    private simulator.ToolTipButton jbICMPData;
    private simulator.ToolTipButton jbICMPType;
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
    private javax.swing.JComboBox jcbICMPCode;
    private javax.swing.JComboBox jcbICMPType;
    private javax.swing.JEditorPane jepProtocolDescription;
    private javax.swing.JLabel jlBi0_1;
    private javax.swing.JLabel jlBi0_2;
    private javax.swing.JLabel jlBit0_2;
    private javax.swing.JLabel jlBit0_3;
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
    private javax.swing.JLabel jlBit24;
    private javax.swing.JLabel jlBit25;
    private javax.swing.JLabel jlBit26;
    private javax.swing.JLabel jlBit27;
    private javax.swing.JLabel jlBit28;
    private javax.swing.JLabel jlBit29;
    private javax.swing.JLabel jlBit3;
    private javax.swing.JLabel jlBit30;
    private javax.swing.JLabel jlBit31;
    private javax.swing.JLabel jlBit32;
    private javax.swing.JLabel jlBit33;
    private javax.swing.JLabel jlBit34;
    private javax.swing.JLabel jlBit35;
    private javax.swing.JLabel jlBit36;
    private javax.swing.JLabel jlBit37;
    private javax.swing.JLabel jlBit38;
    private javax.swing.JLabel jlBit39;
    private javax.swing.JLabel jlBit4;
    private javax.swing.JLabel jlBit40;
    private javax.swing.JLabel jlBit41;
    private javax.swing.JLabel jlBit42;
    private javax.swing.JLabel jlBit43;
    private javax.swing.JLabel jlBit44;
    private javax.swing.JLabel jlBit45;
    private javax.swing.JLabel jlBit46;
    private javax.swing.JLabel jlBit47;
    private javax.swing.JLabel jlBit48;
    private javax.swing.JLabel jlBit49;
    private javax.swing.JLabel jlBit5;
    private javax.swing.JLabel jlBit50;
    private javax.swing.JLabel jlBit51;
    private javax.swing.JLabel jlBit52;
    private javax.swing.JLabel jlBit53;
    private javax.swing.JLabel jlBit54;
    private javax.swing.JLabel jlBit55;
    private javax.swing.JLabel jlBit56;
    private javax.swing.JLabel jlBit57;
    private javax.swing.JLabel jlBit58;
    private javax.swing.JLabel jlBit59;
    private javax.swing.JLabel jlBit6;
    private javax.swing.JLabel jlBit60;
    private javax.swing.JLabel jlBit61;
    private javax.swing.JLabel jlBit64;
    private javax.swing.JLabel jlBit7;
    private javax.swing.JLabel jlBit8;
    private javax.swing.JLabel jlBit9;
    private javax.swing.JLabel jlBit96;
    private javax.swing.JLabel jlBit97;
    private javax.swing.JLabel jlBitOffset;
    private javax.swing.JLabel jlBitOffset1;
    private javax.swing.JLabel jlbit24;
    private javax.swing.JLabel jlbit25;
    private javax.swing.JPanel jpICMP;
    private javax.swing.JPanel jpIP;
    private javax.swing.JScrollPane jspProtocolDescription;
    private javax.swing.JTextField jtfChecksum;
    private javax.swing.JTextField jtfChecksumICMP;
    private javax.swing.JTextField jtfDestinationAddress;
    private javax.swing.JTextField jtfFragmentOffset;
    private javax.swing.JTextField jtfHeaderLenght;
    private javax.swing.JTextField jtfICMPData;
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
