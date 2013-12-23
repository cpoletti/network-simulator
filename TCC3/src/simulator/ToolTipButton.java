/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.ToolTipManager;

/**
 *
 * @author Cristiano
 */
public class ToolTipButton extends JButton {

    /**
     * Create a tooltip button
     */
    public ToolTipButton() {
        super();

        this.addMouseListener(new MouseAdapter() {
            final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
            final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes

            @Override
            public void mouseEntered(MouseEvent me) {
                ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
            }
        });
    }
    
    /**
     * Create a tooltip button
     * 
     * @param text the tooltip text 
     */
    public ToolTipButton(String text) {
        super(text);

        this.addMouseListener(new MouseAdapter() {
            final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
            final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes

            @Override
            public void mouseEntered(MouseEvent me) {
                ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
            }
        });
    }
    
    @Override
    public void setToolTipText(String text) {
        text = "<html><div style='width:250px'>"+text+"</div></html>";
        super.setToolTipText(text);
    }
}
