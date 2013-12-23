/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Cristiano
 */
public class SimulatorNotification extends JFrame {

    /**
     * An information message
     */
    public final static int MESSAGE_INFORMATION = 1;
    
    /**
     * An error message
     */
    public final static int MESSAGE_ERROR = 2;
    
    /**
     * Create a new notification message
     * 
     * @param message the message text 
     * @param type the message type
     */
    public SimulatorNotification(String message, int type) {
        this.setResizable(false);
        this.setSize(500, 75);
        this.setAlwaysOnTop(true);
        JPanel panel = new JPanel();
        panel.setSize(500, 50);
        
        //change the backgraond color
        switch (type) {
            case MESSAGE_INFORMATION:
                panel.setBackground(new Color(51, 255, 51));
                break;
            case MESSAGE_ERROR:
                panel.setBackground(new Color(255, 52, 38));
                break;
            default:
                panel.setBackground(new Color(51, 255, 51));
                break;
        }

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel headingLabel = new JLabel("");
        headingLabel.setOpaque(false);
        panel.add(headingLabel, constraints);
        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        JButton cloesButton = new JButton("X");
        cloesButton.setMargin(new Insets(1, 4, 1, 4));
        cloesButton.setFocusable(false);
        cloesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeNotification();
            }
        });
        panel.add(cloesButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel messageLabel = new JLabel("<HtMl>" + message);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, constraints);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //this.setLocationRelativeTo(null);
    }

    /**
     * Show the notification and close after 2.5 seconds
     */
    public void showNotification() {
        Timer timer = new Timer(2500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeNotification();
            }
        });
        timer.setRepeats(false);
        timer.start();

        this.setVisible(true);
    }

    /**
     * Create a new notification with MESSAGE_INFORMATION type
     * 
     * @param text the notification text
     */
    public SimulatorNotification(String text) {
        this(text, MESSAGE_INFORMATION);
    }

    /**
     * Close the notification
     */
    public void closeNotification() {
        this.dispose();
    }
}
