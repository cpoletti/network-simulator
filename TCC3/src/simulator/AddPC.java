/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddPC.java
 *
 * Created on 17/10/2009, 18:23:17
 */
package simulator;

import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Muller
 */
public class AddPC extends javax.swing.JFrame {

    NetMap map = null;
    private String ip = "";
    private int port = 7000;

    /**
     * Creates new AddPC form
     *
     * @param map a NetMap object
     */
    public AddPC(NetMap map) {
        this.map = map;
        initComponents();
        this.setLocationRelativeTo(null);

        translate();
    }

    /**
     * Format a IP adress
     *
     * @param ip the IP adress
     */
    public void formatIPAdress(String ip) {
        System.out.println("formattedIPAntes = " + ip);
        String formattedIP = "";

        for (int i = 0; i < ip.length(); i++) {

            if (ip.charAt(i) == '.') {
                formattedIP = formattedIP + Integer.parseInt(ip.substring(0, i)) + '.';
                ip = ip.substring(i + 1);
                i = 0;
            }
        }
        formattedIP += Integer.parseInt(ip);
        jtfIP.setText(formattedIP);
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();

        this.setTitle(bundle.getString("AddPC.title"));
        jbCancel.setText(bundle.getString("AddPC.jbCancel.text"));
        jbAdd.setText(bundle.getString("AddPC.jbAdd.text"));
        jlIP.setText(bundle.getString("AddPC.jlIP.text"));
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

        jtfIP = new javax.swing.JTextField();
        jlIP = new javax.swing.JLabel();
        jbCancel = new javax.swing.JButton();
        jbAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Estação");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jtfIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 5, 25);
        getContentPane().add(jtfIP, gridBagConstraints);

        jlIP.setText("IP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 5, 5);
        getContentPane().add(jlIP, gridBagConstraints);

        jbCancel.setText("Cancelar");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 20, 5);
        getContentPane().add(jbCancel, gridBagConstraints);

        jbAdd.setText("Adicionar");
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 20, 20);
        getContentPane().add(jbAdd, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed

        int auxPort = 7000;

        if (Menu.isValidIPv4(jtfIP.getText())) {

            formatIPAdress(jtfIP.getText());
            String auxIP = jtfIP.getText();

            System.out.println("auxIP = " + auxIP);
            System.out.println("auxPort = " + auxPort);
            if (!map.existWorkStation(auxIP, auxPort)) {
                ConnectionSend envio = new ConnectionSend(auxIP, (Object) "addWorkStation=" +Menu.getIP()+";" + Menu.getNick() + ";" + Menu.getMAC()+";" + Menu.getPort());

                try {
                    envio.start();
                } catch (Exception e) {
                    SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("AddPc.message.notPossible"), SimulatorNotification.MESSAGE_ERROR);
                    not.setLocationRelativeTo(this);
                    not.showNotification();
                }

                this.dispose();
            } else {
                SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("AddPc.message.alreadyExists"), SimulatorNotification.MESSAGE_ERROR);
                not.setLocationRelativeTo(this);
                not.showNotification();
            }
        } else {
            JOptionPane.showMessageDialog(null,Config.getInstance().getBundle().getString("AddPc.message.invalidIP"));
        }



    }//GEN-LAST:event_jbAddActionPerformed

private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
    this.dispose();
}//GEN-LAST:event_jbCancelActionPerformed

private void jtfIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIPActionPerformed
    jbAddActionPerformed(evt);
}//GEN-LAST:event_jtfIPActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbCancel;
    private javax.swing.JLabel jlIP;
    private javax.swing.JTextField jtfIP;
    // End of variables declaration//GEN-END:variables

    /**
     * Returns the IP adress
     *
     * @return the ip adress
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the IP adress
     *
     * @param ip the ip adress
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Returns the connection port
     *
     * @return the connection port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the connection port
     *
     * @param port the connection port
     */
    public void setPort(int port) {
        this.port = port;
    }
}