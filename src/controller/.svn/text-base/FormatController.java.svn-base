
package controller;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

import model.FormatModel;
import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class FormatController {

    private FormatModel formatModel = new FormatModel();

    public void ocultaReguaController(MainPanel panel) {
        formatModel.ocultaRegua(panel.getRegua());
    }
    
    public void ajustaFonte(String fontName, int fontType, String fontSize, JLabel fontPreview, MainPanel painelExtendido) {
        Font font = formatModel.setFont(fontName, fontType, fontSize, painelExtendido);
        
        fontPreview.setFont(font);
        fontPreview.repaint();
    }
    
    public void highlight(JTextComponent textComp) {
        formatModel.highLight(textComp);
    }
    
    public void removeHighlight(JTextComponent textComp) {
        formatModel.removeHighlights(textComp);
    }
}
