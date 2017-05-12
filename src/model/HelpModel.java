
package model;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Larissa
 */
public class HelpModel {
    
    public void callAboutWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(
                "<html><b>Notepad Software Product Line<BR></b>"
                + "Versão 1.0 - 2012</html>");
        
        frame.setTitle("About Notepad SPL");
        frame.setSize(250, 125);
        frame.setResizable(false);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 2,
                (screenSize.height - frame.getHeight()) / 2);
        
        frame.add(label);
        frame.setVisible(true);
    }
}
