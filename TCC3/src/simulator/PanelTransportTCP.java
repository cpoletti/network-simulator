/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelTransportTCP.java
 *
 * Created on 30/08/2010, 19:25:43
 */
package simulator;

import datagram.DatagramTransportTCP;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Diego Muller
 */
public class PanelTransportTCP extends PanelTransport {

    DatagramTransportTCP tcpDatagram;
    boolean editable = true;
    boolean enableNextTab = true;

    /**
     * Creates new form PanelTransportTCP
     */
    public PanelTransportTCP() {
        initComponents();

        jtfSourcePort.setVisible(false);
        jtfDestinationPort.setVisible(false);
        jtfSequence.setVisible(false);
        jtfAcknowledgment.setVisible(false);
        jtfDataOffset.setVisible(false);
        jtfWindowSize.setVisible(false);
        jtfUrgentPointer.setVisible(false);
        jtfOptions.setVisible(false);

        translate();
    }

    @Override
    public void setDatagram(DatagramTransport tcpDatagram) {
        ((DatagramTransportTCP) tcpDatagram).setSourcePort(this.getSourcePort());
        ((DatagramTransportTCP) tcpDatagram).setDestinationPort(this.getDestinationPort());
        ((DatagramTransportTCP) tcpDatagram).setSequenceNumber(this.getSequence());
        ((DatagramTransportTCP) tcpDatagram).setAcknowledgementNumber(this.getAcknowledgment());
        ((DatagramTransportTCP) tcpDatagram).setHeaderLenght(this.getDataOffset());
        ((DatagramTransportTCP) tcpDatagram).setCWR(this.getCWR());
        ((DatagramTransportTCP) tcpDatagram).setECE(this.getECE());
        ((DatagramTransportTCP) tcpDatagram).setURG(this.getURG());
        ((DatagramTransportTCP) tcpDatagram).setACK(this.getACK());
        ((DatagramTransportTCP) tcpDatagram).setPSH(this.getPSH());
        ((DatagramTransportTCP) tcpDatagram).setRST(this.getRST());
        ((DatagramTransportTCP) tcpDatagram).setSYN(this.getSYN());
        ((DatagramTransportTCP) tcpDatagram).setFIN(this.getFIN());
        ((DatagramTransportTCP) tcpDatagram).setWindowSize(this.getWindowSize());
        ((DatagramTransportTCP) tcpDatagram).setUrgentPointer(this.getUrgentPointer());
        ((DatagramTransportTCP) tcpDatagram).setOptions(this.getOptions());
        if (enableNextTab) {
            ((DatagramTransportTCP) tcpDatagram).enableNextTab();
        } else {
            ((DatagramTransportTCP) tcpDatagram).disableNextTab();
        }
    }

    @Override
    public void setPanelFields(DatagramTransport tcpDatagram) {
        this.tcpDatagram = ((DatagramTransportTCP) tcpDatagram);
        this.setSourcePort(((DatagramTransportTCP) tcpDatagram).getSourcePort());
        this.setDestinationPort(((DatagramTransportTCP) tcpDatagram).getDestinationPort());
        this.setSequence(((DatagramTransportTCP) tcpDatagram).getSequenceNumber());
        this.setAcknowledgment(((DatagramTransportTCP) tcpDatagram).getAcknowledgementNumber());
        this.setDataOffset(((DatagramTransportTCP) tcpDatagram).getHeaderLenght());
        this.setCWR(((DatagramTransportTCP) tcpDatagram).isCWR());
        this.setECE(((DatagramTransportTCP) tcpDatagram).isECE());
        this.setURG(((DatagramTransportTCP) tcpDatagram).isURG());
        this.setACK(((DatagramTransportTCP) tcpDatagram).isACK());
        this.setPSH(((DatagramTransportTCP) tcpDatagram).isPSH());
        this.setRST(((DatagramTransportTCP) tcpDatagram).isRST());
        this.setSYN(((DatagramTransportTCP) tcpDatagram).isSYN());
        this.setFIN(((DatagramTransportTCP) tcpDatagram).isFIN());
        this.setWindowSize(((DatagramTransportTCP) tcpDatagram).getWindowSize());
        this.setUrgentPointer(((DatagramTransportTCP) tcpDatagram).getUrgentPointer());
        this.setOptions(((DatagramTransportTCP) tcpDatagram).getOptions());

        this.enableApplicationTab();
    }

    @Override
    public void setEditable(boolean edit) {
        jbACK.setEnabled(edit);
        jbAcknowledgment.setEnabled(edit);
        jbCWR.setEnabled(edit);
        jbDestinationPort.setEnabled(edit);
        jbECE.setEnabled(edit);
        jbFIN.setEnabled(edit);
        jbPSH.setEnabled(edit);
        jbRST.setEnabled(edit);
        jbSYN.setEnabled(edit);
        jbSequence.setEnabled(edit);
        jbSourcePort.setEnabled(edit);
        jbURG.setEnabled(edit);

        this.editable = edit;
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
     * Return the Sequence value
     *
     * @return the Sequence value
     */
    public String getSequence() {
        return jtfSequence.getText();
    }

    /**
     * Return the Acknowledgment value
     *
     * @return the Acknowledgment value
     */
    public String getAcknowledgment() {
        return jtfAcknowledgment.getText();
    }

    /**
     * Return the Data Offset value
     *
     * @return the Data Offset value
     */
    public int getDataOffset() {
        return Integer.parseInt(jtfDataOffset.getText());
    }

    /**
     * Return the CWR value
     *
     * @return the CWR value
     */
    public boolean getCWR() {
        if (jbCWR.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the ECE value
     *
     * @return the ECE value
     */
    public boolean getECE() {
        if (jbECE.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the URG value
     *
     * @return the URG value
     */
    public boolean getURG() {
        if (jbURG.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the ACK value
     *
     * @return the ACK value
     */
    public boolean getACK() {
        if (jbACK.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the PSH value
     *
     * @return the PSH value
     */
    public boolean getPSH() {
        if (jbPSH.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the RST value
     *
     * @return the RST value
     */
    public boolean getRST() {
        if (jbRST.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the SYN value
     *
     * @return the SYN value
     */
    public boolean getSYN() {
        if (jbSYN.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the FIN value
     *
     * @return the FIN value
     */
    public boolean getFIN() {
        if (jbFIN.getBackground() == enableButtonColor) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the Window Size value
     *
     * @return the Window Size value
     */
    public int getWindowSize() {
        return Integer.parseInt(jtfWindowSize.getText());
    }

    /**
     * Return the Urgent Pointer value
     *
     * @return the Urgent Pointer value
     */
    public int getUrgentPointer() {
        return Integer.parseInt(jtfUrgentPointer.getText());
    }

    /**
     * Return the Options value
     *
     * @return the Options value
     */
    public int getOptions() {
        return Integer.parseInt(jtfOptions.getText());
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
     * Sets the sequence value
     *
     * @param sequence the sequence value
     */
    public void setSequence(String sequence) {
        jtfSequence.setText(sequence);
        updateSequenceButton();
    }

    /**
     * Sets the acknowledgment value
     *
     * @param acknowledgment the acknowledgment value
     */
    public void setAcknowledgment(String acknowledgment) {
        jtfAcknowledgment.setText(acknowledgment);
        updateAcknowledgmentButton();
    }

    /**
     * Sets the Data Offset value
     *
     * @param dataOffset the Data Offset value
     */
    public void setDataOffset(int dataOffset) {
        jtfDataOffset.setText(String.valueOf(dataOffset));
        updateDataOffsetButton();
    }

    /**
     * Sets the CWR value
     *
     * @param cwr the CWR value
     */
    public void setCWR(boolean cwr) {
        if (cwr) {
            jbCWR.setBackground(enableButtonColor);
        } else {
            jbCWR.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the ECE value
     *
     * @param ece the ECE value
     */
    public void setECE(boolean ece) {
        if (ece) {
            jbECE.setBackground(enableButtonColor);
        } else {
            jbECE.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the URG value
     *
     * @param urg the URG value
     */
    public void setURG(boolean urg) {
        if (urg) {
            jbURG.setBackground(enableButtonColor);
        } else {
            jbURG.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the ACK value
     *
     * @param ack the ACK value
     */
    public void setACK(boolean ack) {
        if (ack) {
            jbACK.setBackground(enableButtonColor);
        } else {
            jbACK.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the PSH value
     *
     * @param psh the PSH value
     */
    public void setPSH(boolean psh) {
        if (psh) {
            jbPSH.setBackground(enableButtonColor);
        } else {
            jbPSH.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the RST value
     *
     * @param rst the RST value
     */
    public void setRST(boolean rst) {
        if (rst) {
            jbRST.setBackground(enableButtonColor);
        } else {
            jbRST.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the SYN value
     *
     * @param syn the SYN value
     */
    public void setSYN(boolean syn) {
        if (syn) {
            jbSYN.setBackground(enableButtonColor);
        } else {
            jbSYN.setBackground(disableButtonColor);
        }
    }

    /**
     * Sets the FIN value
     *
     * @param fin the FIN value
     */
    public void setFIN(boolean fin) {
        if (fin) {
            jbFIN.setBackground(enableButtonColor);
        } else {
            jbFIN.setBackground(disableButtonColor);
        }
    }
    
    /**
     * Sets the Window Size value
     *
     * @param windowSize the Window Size value
     */
    public void setWindowSize(int windowSize) {
        jtfWindowSize.setText(String.valueOf(windowSize));
        updateWindowSizeButton();
    }
    
    /**
     * Sets the Urgent Pointer value
     *
     * @param urgPointer the Urgent Pointer value
     */
    public void setUrgentPointer(int urgPointer) {
        jtfUrgentPointer.setText(String.valueOf(urgPointer));
        updateUrgentPointerButton();
    }
    
    /**
     * Sets the Options value
     *
     * @param options the Options value
     */
    public void setOptions(int options) {
        jtfOptions.setText(String.valueOf(options));
        updateOptionsButton();
    }

    /**
     * Change the Source Port button text
     */
    private void updateSourcePortButton() {
        if (jtfSourcePort.getText().trim().equals("")) {
            jbSourcePort.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbSourcePort.text"));
        } else {
            jbSourcePort.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbSourcePort.text") + ": " + jtfSourcePort.getText());
        }
        jbSourcePort.setVisible(true);
        jtfSourcePort.setVisible(false);
    }

    /**
     * Change the Destination Port button text
     */
    private void updateDestinationPortButton() {
        if (jtfDestinationPort.getText().trim().equals("")) {
            jbDestinationPort.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbDestinationPort.text"));
        } else {
            jbDestinationPort.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbDestinationPort.text") + ": " + jtfDestinationPort.getText());
        }
        jbDestinationPort.setVisible(true);
        jtfDestinationPort.setVisible(false);
    }
    
    /**
     * Change the Sequence button text
     */
    private void updateSequenceButton() {
        if (jtfSequence.getText().trim().equals("")) {
            jbSequence.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbSequence.text"));
        } else {
            jbSequence.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbSequence.text") + ": " + jtfSequence.getText());
        }
        jbSequence.setVisible(true);
        jtfSequence.setVisible(false);
    }
    
    /**
     * Change the Acknowledgment button text
     */
    private void updateAcknowledgmentButton() {
        if (jtfAcknowledgment.getText().trim().equals("")) {
            jbAcknowledgment.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbAcknowledgment.text"));
        } else {
            jbAcknowledgment.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbAcknowledgment.text") + ": " + jtfAcknowledgment.getText());
        }
        jbAcknowledgment.setVisible(true);
        jtfAcknowledgment.setVisible(false);
    }

    /**
     * Change the Data Offset button text
     */
    private void updateDataOffsetButton() {
        if (jtfDataOffset.getText().trim().equals("")) {
            jbDataOffset.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbDataOffset.text"));
        } else {
            jbDataOffset.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbDataOffset.text") + ": " + jtfDataOffset.getText());
            jbOptions.setEnabled(Integer.parseInt(jtfDataOffset.getText()) > 5);
        }
        jbDataOffset.setVisible(true);
        jtfDataOffset.setVisible(false);
    }

    /**
     * Change the Window Size button text
     */
    private void updateWindowSizeButton() {
        if (jtfWindowSize.getText().trim().equals("")) {
            jbWindowSize.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbWindowSize.text"));
        } else {
            jbWindowSize.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbWindowSize.text") + ": " + jtfWindowSize.getText());
        }
        jbWindowSize.setVisible(true);
        jtfWindowSize.setVisible(false);
    }

    /**
     * Change the Urgent Pointer button text
     */
    private void updateUrgentPointerButton() {
        if (jtfUrgentPointer.getText().trim().equals("")) {
            jbUrgentPointer.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbUrgentPointer.text"));
        } else {
            jbUrgentPointer.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbUrgentPointer.text") + ": " + jtfUrgentPointer.getText());
        }
        jbUrgentPointer.setVisible(true);
        jtfUrgentPointer.setVisible(false);
    }

    /**
     * Change the Options button text
     */
    private void updateOptionsButton() {
        if (jtfOptions.getText().trim().equals("")) {
            jbOptions.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbOptions.text"));
        } else {
            jbOptions.setText(Config.getInstance().getBundle().getString("PanelTransportTCP.jbOptions.text") + ": " + jtfOptions.getText());
        }
        jbOptions.setVisible(true);
        jtfOptions.setVisible(false);
    }

    /**
     * Enable or disable application tab according with the port state
     */
    public void enableApplicationTab() {

        if (this.editable) {
            String portStateName = this.getStatePort(this.getSourcePort(), this.getDestinationPort());
            int portState = this.tcpDatagram.findStateByName(portStateName);

            if (portState == DatagramTransportTCP.ESTABLISHED) {
                if (this.getFIN()) {
                    this.disableNextTab();
                    this.jbData.setEnabled(false);
                    enableNextTab = false;
                } else {
                    this.enableNextTab();
                    enableNextTab = true;
                    this.jbData.setEnabled(true);
                }
            } else {
                this.disableNextTab();
                enableNextTab = false;
                this.jbData.setEnabled(false);
            }
        } else {
            if (this.tcpDatagram.isEnableNextPort()) {
                this.enableNextTab();
                this.jbData.setEnabled(true);
            } else {
                this.disableNextTab();
                this.jbData.setEnabled(false);
            }
        }
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();
        updateDestinationPortButton();
        updateSourcePortButton();
        updateSequenceButton();
        updateAcknowledgmentButton();
        updateWindowSizeButton();
        updateUrgentPointerButton();
        updateOptionsButton();
        updateDataOffsetButton();
        jbReserved.setText(bundle.getString("PanelTransportTCP.jbReserved.text"));
        jbCWR.setText(bundle.getString("PanelTransportTCP.jbCWR.text"));
        jbECE.setText(bundle.getString("PanelTransportTCP.jbECE.text"));
        jbURG.setText(bundle.getString("PanelTransportTCP.jbURG.text"));
        jbACK.setText(bundle.getString("PanelTransportTCP.jbACK.text"));
        jbPSH.setText(bundle.getString("PanelTransportTCP.jbPSH.text"));
        jbRST.setText(bundle.getString("PanelTransportTCP.jbRST.text"));
        jbSYN.setText(bundle.getString("PanelTransportTCP.jbSYN.text"));
        jbFIN.setText(bundle.getString("PanelTransportTCP.jbFIN.text"));
        jbData.setText(bundle.getString("PanelTransportTCP.jbData.text"));

        TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(bundle.getString("PanelTransportTCP.jspProtocolDescription.title"));
        titledBorder.setBorder(new LineBorder(Color.BLACK, 1, true));
        jspProtocolDescription.setBorder(titledBorder);
        jepProtocolDescription.setText(bundle.getString("PanelTransportTCP.jepProtocolDescription.text"));

        jbDestinationPort.setToolTipText(bundle.getString("PanelTransportTCP.jbDestinationPort.tooltip"));
        jbSourcePort.setToolTipText(bundle.getString("PanelTransportTCP.jbSourcePort.tooltip"));
        jbSequence.setToolTipText(bundle.getString("PanelTransportTCP.jbSequence.tooltip"));
        jbAcknowledgment.setToolTipText(bundle.getString("PanelTransportTCP.jbAcknowledgment.tooltip"));
        jbDataOffset.setToolTipText(bundle.getString("PanelTransportTCP.jbDataOffset.tooltip"));
        jbReserved.setToolTipText(bundle.getString("PanelTransportTCP.jbReserved.tooltip"));
        jbCWR.setToolTipText(bundle.getString("PanelTransportTCP.jbCWR.tooltip"));
        jbECE.setToolTipText(bundle.getString("PanelTransportTCP.jbECE.tooltip"));
        jbURG.setToolTipText(bundle.getString("PanelTransportTCP.jbURG.tooltip"));
        jbACK.setToolTipText(bundle.getString("PanelTransportTCP.jbACK.tooltip"));
        jbPSH.setToolTipText(bundle.getString("PanelTransportTCP.jbPSH.tooltip"));
        jbRST.setToolTipText(bundle.getString("PanelTransportTCP.jbRST.tooltip"));
        jbSYN.setToolTipText(bundle.getString("PanelTransportTCP.jbSYN.tooltip"));
        jbFIN.setToolTipText(bundle.getString("PanelTransportTCP.jbFIN.tooltip"));
        jbWindowSize.setToolTipText(bundle.getString("PanelTransportTCP.jbWindowSize.tooltip"));
        jbCheckSum.setToolTipText(bundle.getString("PanelTransportTCP.jbCheckSum.tooltip"));
        jbUrgentPointer.setToolTipText(bundle.getString("PanelTransportTCP.jbUrgentPointer.tooltip"));
        jbOptions.setToolTipText(bundle.getString("PanelTransportTCP.jbOptions.tooltip"));
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
        jbSequence = new simulator.ToolTipButton();
        jtfSequence = new javax.swing.JTextField();
        jbAcknowledgment = new simulator.ToolTipButton();
        jtfAcknowledgment = new javax.swing.JTextField();
        jbDataOffset = new simulator.ToolTipButton();
        jtfDataOffset = new javax.swing.JTextField();
        jbReserved = new simulator.ToolTipButton();
        jbCWR = new simulator.ToolTipButton();
        jbECE = new simulator.ToolTipButton();
        jbURG = new simulator.ToolTipButton();
        jbACK = new simulator.ToolTipButton();
        jbPSH = new simulator.ToolTipButton();
        jbRST = new simulator.ToolTipButton();
        jbSYN = new simulator.ToolTipButton();
        jbFIN = new simulator.ToolTipButton();
        jbWindowSize = new simulator.ToolTipButton();
        jtfWindowSize = new javax.swing.JTextField();
        jbCheckSum = new simulator.ToolTipButton();
        jbUrgentPointer = new simulator.ToolTipButton();
        jtfUrgentPointer = new javax.swing.JTextField();
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

        setBackground(backgroundColorTransport);
        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        jbSourcePort.setText(bundle.getString("PanelTransportTCP.jbSourcePort.text")); // NOI18N
        jbSourcePort.setToolTipText(bundle.getString("PanelTransportTCP.jbSourcePort.tooltip")); // NOI18N
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
        jtfSourcePort.setAlignmentX(0.0F);
        jtfSourcePort.setAlignmentY(0.0F);
        jtfSourcePort.setMargin(new java.awt.Insets(1, 1, 1, 1));
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

        jbDestinationPort.setText(bundle.getString("PanelTransportTCP.jbDestinationPort.text")); // NOI18N
        jbDestinationPort.setToolTipText(bundle.getString("PanelTransportTCP.jbDestinationPort.tooltip")); // NOI18N
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
        jtfDestinationPort.setAlignmentX(0.0F);
        jtfDestinationPort.setAlignmentY(0.0F);
        jtfDestinationPort.setMargin(new java.awt.Insets(1, 1, 1, 1));
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

        jbSequence.setText(bundle.getString("PanelTransportTCP.jbSequence.text")); // NOI18N
        jbSequence.setToolTipText(bundle.getString("PanelTransportTCP.jbSequence.tooltip")); // NOI18N
        jbSequence.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSequence.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSequence.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSequence.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSequenceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jbSequence, gridBagConstraints);

        jtfSequence.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSequence.setAlignmentX(0.0F);
        jtfSequence.setAlignmentY(0.0F);
        jtfSequence.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfSequence.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfSequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSequenceActionPerformed(evt);
            }
        });
        jtfSequence.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSequenceFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfSequence, gridBagConstraints);

        jbAcknowledgment.setText(bundle.getString("PanelTransportTCP.jbAcknowledgment.text")); // NOI18N
        jbAcknowledgment.setToolTipText(bundle.getString("PanelTransportTCP.jbAcknowledgment.tooltip")); // NOI18N
        jbAcknowledgment.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbAcknowledgment.setMaximumSize(new java.awt.Dimension(0, 0));
        jbAcknowledgment.setMinimumSize(new java.awt.Dimension(0, 0));
        jbAcknowledgment.setPreferredSize(new java.awt.Dimension(0, 0));
        jbAcknowledgment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAcknowledgmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.125;
        add(jbAcknowledgment, gridBagConstraints);

        jtfAcknowledgment.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfAcknowledgment.setAlignmentX(0.0F);
        jtfAcknowledgment.setAlignmentY(0.0F);
        jtfAcknowledgment.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfAcknowledgment.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfAcknowledgment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfAcknowledgmentActionPerformed(evt);
            }
        });
        jtfAcknowledgment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfAcknowledgmentFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfAcknowledgment, gridBagConstraints);

        jbDataOffset.setText(bundle.getString("PanelTransportTCP.jbDataOffset.text")); // NOI18N
        jbDataOffset.setToolTipText(bundle.getString("PanelTransportTCP.jbDataOffset.tooltip")); // NOI18N
        jbDataOffset.setEnabled(false);
        jbDataOffset.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbDataOffset.setMaximumSize(new java.awt.Dimension(0, 0));
        jbDataOffset.setMinimumSize(new java.awt.Dimension(0, 0));
        jbDataOffset.setPreferredSize(new java.awt.Dimension(0, 0));
        jbDataOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDataOffsetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.12;
        gridBagConstraints.weighty = 0.125;
        add(jbDataOffset, gridBagConstraints);

        jtfDataOffset.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDataOffset.setAlignmentX(0.0F);
        jtfDataOffset.setAlignmentY(0.0F);
        jtfDataOffset.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfDataOffset.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfDataOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDataOffsetActionPerformed(evt);
            }
        });
        jtfDataOffset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDataOffsetFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfDataOffset, gridBagConstraints);

        jbReserved.setText(bundle.getString("PanelTransportTCP.jbReserved.text")); // NOI18N
        jbReserved.setToolTipText(bundle.getString("PanelTransportTCP.jbReserved.tooltip")); // NOI18N
        jbReserved.setEnabled(false);
        jbReserved.setFocusable(false);
        jbReserved.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbReserved.setMaximumSize(new java.awt.Dimension(0, 0));
        jbReserved.setMinimumSize(new java.awt.Dimension(0, 0));
        jbReserved.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.12;
        gridBagConstraints.weighty = 0.125;
        add(jbReserved, gridBagConstraints);

        jbCWR.setBackground(enableButtonColor);
        jbCWR.setText(bundle.getString("PanelTransportTCP.jbCWR.text")); // NOI18N
        jbCWR.setToolTipText(bundle.getString("PanelTransportTCP.jbAcknowledgment.tooltip")); // NOI18N
        jbCWR.setActionCommand("");
        jbCWR.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbCWR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbCWR.setMaximumSize(new java.awt.Dimension(0, 0));
        jbCWR.setMinimumSize(new java.awt.Dimension(0, 0));
        jbCWR.setPreferredSize(new java.awt.Dimension(0, 0));
        jbCWR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCWRActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbCWR, gridBagConstraints);

        jbECE.setBackground(enableButtonColor);
        jbECE.setText(bundle.getString("PanelTransportTCP.jbECE.text")); // NOI18N
        jbECE.setToolTipText(bundle.getString("PanelTransportTCP.jbECE.tooltip")); // NOI18N
        jbECE.setActionCommand("");
        jbECE.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbECE.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbECE.setMaximumSize(new java.awt.Dimension(0, 0));
        jbECE.setMinimumSize(new java.awt.Dimension(0, 0));
        jbECE.setPreferredSize(new java.awt.Dimension(0, 0));
        jbECE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbECEActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbECE, gridBagConstraints);

        jbURG.setBackground(enableButtonColor);
        jbURG.setText(bundle.getString("PanelTransportTCP.jbURG.text")); // NOI18N
        jbURG.setToolTipText(bundle.getString("PanelTransportTCP.jbURG.tooltip")); // NOI18N
        jbURG.setActionCommand("");
        jbURG.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbURG.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbURG.setMaximumSize(new java.awt.Dimension(0, 0));
        jbURG.setMinimumSize(new java.awt.Dimension(0, 0));
        jbURG.setPreferredSize(new java.awt.Dimension(0, 0));
        jbURG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbURGActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbURG, gridBagConstraints);

        jbACK.setBackground(enableButtonColor);
        jbACK.setText(bundle.getString("PanelTransportTCP.jbACK.text")); // NOI18N
        jbACK.setToolTipText(bundle.getString("PanelTransportTCP.jbACK.tooltip")); // NOI18N
        jbACK.setActionCommand("");
        jbACK.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbACK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbACK.setMaximumSize(new java.awt.Dimension(0, 0));
        jbACK.setMinimumSize(new java.awt.Dimension(0, 0));
        jbACK.setPreferredSize(new java.awt.Dimension(0, 0));
        jbACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbACKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbACK, gridBagConstraints);

        jbPSH.setBackground(enableButtonColor);
        jbPSH.setText(bundle.getString("PanelTransportTCP.jbPSH.text")); // NOI18N
        jbPSH.setToolTipText(bundle.getString("PanelTransportTCP.jbPSH.tooltip")); // NOI18N
        jbPSH.setActionCommand("");
        jbPSH.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbPSH.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbPSH.setMaximumSize(new java.awt.Dimension(0, 0));
        jbPSH.setMinimumSize(new java.awt.Dimension(0, 0));
        jbPSH.setPreferredSize(new java.awt.Dimension(0, 0));
        jbPSH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPSHActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbPSH, gridBagConstraints);

        jbRST.setBackground(enableButtonColor);
        jbRST.setText(bundle.getString("PanelTransportTCP.jbRST.text")); // NOI18N
        jbRST.setToolTipText(bundle.getString("PanelTransportTCP.jbRST.tooltip")); // NOI18N
        jbRST.setActionCommand("");
        jbRST.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbRST.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbRST.setMaximumSize(new java.awt.Dimension(0, 0));
        jbRST.setMinimumSize(new java.awt.Dimension(0, 0));
        jbRST.setPreferredSize(new java.awt.Dimension(0, 0));
        jbRST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRSTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbRST, gridBagConstraints);

        jbSYN.setBackground(enableButtonColor);
        jbSYN.setText(bundle.getString("PanelTransportTCP.jbSYN.text")); // NOI18N
        jbSYN.setToolTipText(bundle.getString("PanelTransportTCP.jbSYN.tooltip")); // NOI18N
        jbSYN.setActionCommand("");
        jbSYN.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbSYN.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbSYN.setMaximumSize(new java.awt.Dimension(0, 0));
        jbSYN.setMinimumSize(new java.awt.Dimension(0, 0));
        jbSYN.setPreferredSize(new java.awt.Dimension(0, 0));
        jbSYN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSYNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbSYN, gridBagConstraints);

        jbFIN.setBackground(enableButtonColor);
        jbFIN.setText(bundle.getString("PanelTransportTCP.jbFIN.text")); // NOI18N
        jbFIN.setToolTipText(bundle.getString("PanelTransportTCP.jbFIN.tooltip")); // NOI18N
        jbFIN.setActionCommand("");
        jbFIN.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jbFIN.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbFIN.setMaximumSize(new java.awt.Dimension(0, 0));
        jbFIN.setMinimumSize(new java.awt.Dimension(0, 0));
        jbFIN.setPreferredSize(new java.awt.Dimension(0, 0));
        jbFIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFINActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 0.125;
        add(jbFIN, gridBagConstraints);

        jbWindowSize.setText(bundle.getString("PanelTransportTCP.jbWindowSize.text")); // NOI18N
        jbWindowSize.setToolTipText(bundle.getString("PanelTransportTCP.jbFIN.tooltip")); // NOI18N
        jbWindowSize.setEnabled(false);
        jbWindowSize.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbWindowSize.setMaximumSize(new java.awt.Dimension(0, 0));
        jbWindowSize.setMinimumSize(new java.awt.Dimension(0, 0));
        jbWindowSize.setPreferredSize(new java.awt.Dimension(0, 0));
        jbWindowSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWindowSizeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.weighty = 0.125;
        add(jbWindowSize, gridBagConstraints);

        jtfWindowSize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfWindowSize.setAlignmentX(0.0F);
        jtfWindowSize.setAlignmentY(0.0F);
        jtfWindowSize.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfWindowSize.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfWindowSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfWindowSizeActionPerformed(evt);
            }
        });
        jtfWindowSize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfWindowSizeFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfWindowSize, gridBagConstraints);

        jbCheckSum.setText(bundle.getString("PanelTransportTCP.jbCheckSum.text")); // NOI18N
        jbCheckSum.setToolTipText(bundle.getString("PanelTransportTCP.jbCheckSum.tooltip")); // NOI18N
        jbCheckSum.setEnabled(false);
        jbCheckSum.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbCheckSum.setMaximumSize(new java.awt.Dimension(0, 0));
        jbCheckSum.setMinimumSize(new java.awt.Dimension(0, 0));
        jbCheckSum.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.weighty = 0.125;
        add(jbCheckSum, gridBagConstraints);

        jbUrgentPointer.setText(bundle.getString("PanelTransportTCP.jbUrgentPointer.text")); // NOI18N
        jbUrgentPointer.setToolTipText(bundle.getString("PanelTransportTCP.jbUrgentPointer.tooltip")); // NOI18N
        jbUrgentPointer.setEnabled(false);
        jbUrgentPointer.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jbUrgentPointer.setMaximumSize(new java.awt.Dimension(0, 0));
        jbUrgentPointer.setMinimumSize(new java.awt.Dimension(0, 0));
        jbUrgentPointer.setPreferredSize(new java.awt.Dimension(0, 0));
        jbUrgentPointer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUrgentPointerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.weighty = 0.125;
        add(jbUrgentPointer, gridBagConstraints);

        jtfUrgentPointer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfUrgentPointer.setAlignmentX(0.0F);
        jtfUrgentPointer.setAlignmentY(0.0F);
        jtfUrgentPointer.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jtfUrgentPointer.setPreferredSize(new java.awt.Dimension(0, 0));
        jtfUrgentPointer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUrgentPointerActionPerformed(evt);
            }
        });
        jtfUrgentPointer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfUrgentPointerFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jtfUrgentPointer, gridBagConstraints);

        jbOptions.setText(bundle.getString("PanelTransportTCP.jbOptions.text")); // NOI18N
        jbOptions.setToolTipText(bundle.getString("PanelTransportTCP.jbOptions.tooltip")); // NOI18N
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
        gridBagConstraints.weightx = 0.96;
        gridBagConstraints.weighty = 0.3125;
        add(jbOptions, gridBagConstraints);

        jtfOptions.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
        gridBagConstraints.weighty = 0.3125;
        add(jlBit160, gridBagConstraints);

        jbData.setBackground(new java.awt.Color(153, 255, 153));
        jbData.setText(bundle.getString("PanelTransportTCP.jbData.text")); // NOI18N
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

        jspProtocolDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), bundle.getString("PanelTransportTCP.jspProtocolDescription.title"))); // NOI18N

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

    private void jbSourcePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSourcePortActionPerformed

        jtfSourcePort.setVisible(true);
        jbSourcePort.setVisible(false);
        jtfSourcePort.requestFocus();
}//GEN-LAST:event_jbSourcePortActionPerformed

    private void jtfSourcePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSourcePortActionPerformed
        updateSourcePortButton();
        enableApplicationTab();
}//GEN-LAST:event_jtfSourcePortActionPerformed

    private void jbDestinationPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinationPortActionPerformed
        jtfDestinationPort.setVisible(true);
        jbDestinationPort.setVisible(false);
        jtfDestinationPort.requestFocus();
}//GEN-LAST:event_jbDestinationPortActionPerformed

    private void jtfDestinationPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDestinationPortActionPerformed
        updateDestinationPortButton();
        enableApplicationTab();
}//GEN-LAST:event_jtfDestinationPortActionPerformed

    private void jbSequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSequenceActionPerformed
        jtfSequence.setVisible(true);
        jbSequence.setVisible(false);
        jtfSequence.requestFocus();
}//GEN-LAST:event_jbSequenceActionPerformed

    private void jtfSequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSequenceActionPerformed
        updateSequenceButton();
}//GEN-LAST:event_jtfSequenceActionPerformed

    private void jbAcknowledgmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAcknowledgmentActionPerformed
        jtfAcknowledgment.setVisible(true);
        jbAcknowledgment.setVisible(false);
        jtfAcknowledgment.requestFocus();
}//GEN-LAST:event_jbAcknowledgmentActionPerformed

    private void jtfAcknowledgmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfAcknowledgmentActionPerformed
        updateAcknowledgmentButton();
}//GEN-LAST:event_jtfAcknowledgmentActionPerformed

    private void jbDataOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataOffsetActionPerformed
        jtfDataOffset.setVisible(true);
        jbDataOffset.setVisible(false);
        jtfDataOffset.requestFocus();
}//GEN-LAST:event_jbDataOffsetActionPerformed

    private void jtfDataOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDataOffsetActionPerformed
        updateDataOffsetButton();
}//GEN-LAST:event_jtfDataOffsetActionPerformed

    private void jbCWRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCWRActionPerformed
        if (jbCWR.getBackground() == enableButtonColor) {
            jbCWR.setBackground(disableButtonColor);
        } else {
            jbCWR.setBackground(enableButtonColor);
        }
}//GEN-LAST:event_jbCWRActionPerformed

    private void jbECEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbECEActionPerformed
        if (jbECE.getBackground() == enableButtonColor) {
            jbECE.setBackground(disableButtonColor);
        } else {
            jbECE.setBackground(enableButtonColor);
        }
}//GEN-LAST:event_jbECEActionPerformed

    private void jbURGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbURGActionPerformed
        if (jbURG.getBackground() == enableButtonColor) {
            jbURG.setBackground(disableButtonColor);
        } else {
            jbURG.setBackground(enableButtonColor);
        }
}//GEN-LAST:event_jbURGActionPerformed

    private void jbACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbACKActionPerformed
        if (jbACK.getBackground() == enableButtonColor) {
            jbACK.setBackground(disableButtonColor);
        } else {
            jbACK.setBackground(enableButtonColor);
        }
        enableApplicationTab();
}//GEN-LAST:event_jbACKActionPerformed

    private void jbPSHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPSHActionPerformed
        if (jbPSH.getBackground() == enableButtonColor) {
            jbPSH.setBackground(disableButtonColor);
        } else {
            jbPSH.setBackground(enableButtonColor);
        }
}//GEN-LAST:event_jbPSHActionPerformed

    private void jbRSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRSTActionPerformed
        if (jbRST.getBackground() == enableButtonColor) {
            jbRST.setBackground(disableButtonColor);
        } else {
            jbRST.setBackground(enableButtonColor);
        }
}//GEN-LAST:event_jbRSTActionPerformed

    private void jbSYNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSYNActionPerformed
        if (jbSYN.getBackground() == enableButtonColor) {
            jbSYN.setBackground(disableButtonColor);
        } else {
            jbSYN.setBackground(enableButtonColor);
        }
        enableApplicationTab();
}//GEN-LAST:event_jbSYNActionPerformed

    private void jbFINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFINActionPerformed
        if (jbFIN.getBackground() == enableButtonColor) {
            jbFIN.setBackground(disableButtonColor);
        } else {
            jbFIN.setBackground(enableButtonColor);
        }
        enableApplicationTab();
}//GEN-LAST:event_jbFINActionPerformed

    private void jtfSourcePortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSourcePortFocusLost
        updateSourcePortButton();
    }//GEN-LAST:event_jtfSourcePortFocusLost

    private void jtfDestinationPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationPortFocusLost
        updateDestinationPortButton();
    }//GEN-LAST:event_jtfDestinationPortFocusLost

    private void jtfSequenceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSequenceFocusLost
        updateSequenceButton();
    }//GEN-LAST:event_jtfSequenceFocusLost

    private void jtfAcknowledgmentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAcknowledgmentFocusLost
        updateAcknowledgmentButton();
    }//GEN-LAST:event_jtfAcknowledgmentFocusLost

    private void jtfDataOffsetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDataOffsetFocusLost
        updateDataOffsetButton();
    }//GEN-LAST:event_jtfDataOffsetFocusLost

    private void jtfWindowSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfWindowSizeActionPerformed
        updateWindowSizeButton();
    }//GEN-LAST:event_jtfWindowSizeActionPerformed

    private void jtfWindowSizeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfWindowSizeFocusLost
        updateWindowSizeButton();
    }//GEN-LAST:event_jtfWindowSizeFocusLost

    private void jbWindowSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbWindowSizeActionPerformed
        jtfWindowSize.setVisible(true);
        jbWindowSize.setVisible(false);
        jtfWindowSize.requestFocus();
    }//GEN-LAST:event_jbWindowSizeActionPerformed

    private void jtfUrgentPointerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfUrgentPointerActionPerformed
        updateUrgentPointerButton();
    }//GEN-LAST:event_jtfUrgentPointerActionPerformed

    private void jtfUrgentPointerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfUrgentPointerFocusLost
        updateUrgentPointerButton();
    }//GEN-LAST:event_jtfUrgentPointerFocusLost

    private void jbUrgentPointerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUrgentPointerActionPerformed
        jtfUrgentPointer.setVisible(true);
        jbUrgentPointer.setVisible(false);
        jtfUrgentPointer.requestFocus();
    }//GEN-LAST:event_jbUrgentPointerActionPerformed

    private void jtfOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfOptionsActionPerformed
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsActionPerformed

    private void jtfOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfOptionsFocusLost
        updateOptionsButton();
    }//GEN-LAST:event_jtfOptionsFocusLost

    private void jbOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOptionsActionPerformed
        jtfOptions.setVisible(true);
        jbOptions.setVisible(false);
        jtfOptions.requestFocus();
    }//GEN-LAST:event_jbOptionsActionPerformed

    private void jbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataActionPerformed
        setPreviousTab();
    }//GEN-LAST:event_jbDataActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private simulator.ToolTipButton jbACK;
    private simulator.ToolTipButton jbAcknowledgment;
    private simulator.ToolTipButton jbCWR;
    private simulator.ToolTipButton jbCheckSum;
    private simulator.ToolTipButton jbData;
    private simulator.ToolTipButton jbDataOffset;
    private simulator.ToolTipButton jbDestinationPort;
    private simulator.ToolTipButton jbECE;
    private simulator.ToolTipButton jbFIN;
    private simulator.ToolTipButton jbOptions;
    private simulator.ToolTipButton jbPSH;
    private simulator.ToolTipButton jbRST;
    private simulator.ToolTipButton jbReserved;
    private simulator.ToolTipButton jbSYN;
    private simulator.ToolTipButton jbSequence;
    private simulator.ToolTipButton jbSourcePort;
    private simulator.ToolTipButton jbURG;
    private simulator.ToolTipButton jbUrgentPointer;
    private simulator.ToolTipButton jbWindowSize;
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
    private javax.swing.JTextField jtfAcknowledgment;
    private javax.swing.JTextField jtfDataOffset;
    private javax.swing.JTextField jtfDestinationPort;
    private javax.swing.JTextField jtfOptions;
    private javax.swing.JTextField jtfSequence;
    private javax.swing.JTextField jtfSourcePort;
    private javax.swing.JTextField jtfUrgentPointer;
    private javax.swing.JTextField jtfWindowSize;
    // End of variables declaration//GEN-END:variables
}
