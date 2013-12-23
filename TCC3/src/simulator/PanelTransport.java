/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelTransport.java
 *
 * Created on 16/09/2010, 19:09:51
 */

package simulator;

import java.awt.Color;
import java.awt.Container;

/**
 *
 * @author Diego Muller
 */
public abstract class PanelTransport extends javax.swing.JPanel {

    Color backgroundColorTransport = new Color (255,255,153);
    Color enableButtonColor = new Color(50,255,50);
    Color disableButtonColor = new Color(255,75,75);

    /** Creates new form PanelTransport */
    public PanelTransport() {
        initComponents();
    }
    
    /**
     * Sets the datagrams attributes with panel fields
     *
     * @param dt the datagram internet
     */
    public abstract void setDatagram(DatagramTransport dt);
    
    /**
     * Populate panel fields with datagrams attributes
     *
     * @param dt the datagram internet
     */
    public abstract void setPanelFields(DatagramTransport dt);
    
    /**
     * Sets the editable status of the panel fields
     *
     * @param edit the editable status of the panel fields
     */
    public abstract void setEditable(boolean edit);
    
    /**
     * Translate the Frame texts
     */
    public abstract void translate();
    
     /**
     * Select the previous tab
     */
    public void setPreviousTab(){
        Container parent = this.getParent();
        while(!(parent instanceof PacketDetail)){
            parent = parent.getParent();
        }
        ((PacketDetail) parent).setPreviousTab();
    }
    
    /**
     * Enable the next tab
     */
    public void enableNextTab(){
        Container parent = this.getParent();
        while(!(parent instanceof PacketDetail)){
            parent = parent.getParent();
        }
        ((PacketDetail) parent).enableNextTab();
    }
    
    /**
     * Disable the next tab
     */
    public void disableNextTab(){
        Container parent = this.getParent();
        while(!(parent instanceof PacketDetail)){
            parent = parent.getParent();
        }
        ((PacketDetail) parent).disableNextTab();
    }
    
    /**
     * Returns the name of the connection state in the transport layer
     * 
     * @param destinationPort the destination port
     * @param sourcePort the source port
     * 
     * @return the name of the state of the connection in the source port
     */
    public String getStatePort(int sourcePort, int destinationPort){
        Container parent = this.getParent();
        while(!(parent instanceof PacketDetail)){
            parent = parent.getParent();
        }
        return ((PacketDetail) parent).getStatePort(sourcePort,destinationPort);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
