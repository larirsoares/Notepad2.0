
package model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class FormatModel {
    
    private Highlighter.HighlightPainter myHighlighter = new MyHighlightPainter(Color.GREEN);
    
    public void ocultaRegua(JLabel regua) {
        if (regua.isVisible()) {
            regua.setVisible(false);
            regua.setEnabled(false);
        } else {
            regua.setVisible(true);
            regua.setEnabled(true);
        }
    }

    public Font setFont(String fontName, int fontType, String fontSize, MainPanel painelExtendido) {
        Font font = new Font(fontName, fontType, Integer.parseInt(fontSize));
	painelExtendido.getPainelTexto().setFont(font);
        return font;
    }
    
    String [] javaWords = {"abstract", "do", "import", "public", "throws", "boolean", "double", "instanceof", "return", "transient", "break", "else", " int ", "short","try", "byte", "extends", "interface", "static", "void", "case", "final", "long", "strictfp", "volatile", "catch", "finally", "native", "super", "while", "char", "float","new", "switch", "class", "for", "package", "synchronized", "continue", "if", "private","this", "default", "implements", "protected", "const", "goto", "null", "true", "false"};
    
    // Creates highlights around all occurrences of pattern in textComp
    public void highLight(JTextComponent textComp) {   
        // First remove all old highlights
        removeHighlights(textComp);

        try {
              
         Highlighter hilite = textComp.getHighlighter();                                
         Document doc = textComp.getDocument();      
         String text = doc.getText(0, doc.getLength());         
         for (int i = 0; i < javaWords.length; i++) {                          
            int pos = 0;
             // Search for pattern
             while ((pos = text.indexOf(javaWords[i], pos)) >= 0) {

                hilite.addHighlight(pos, pos + javaWords[i].length(),
                                                    myHighlighter);             
                pos += javaWords[i].length();

             }
         }
         } catch (BadLocationException e) {}

    }
    
    // Removes only our private highlights
     public void removeHighlights(JTextComponent textComp) {

       Highlighter hilite = textComp.getHighlighter();

       Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i = 0; i < hilites.length; i++) {

          if (hilites[i].getPainter() instanceof MyHighlightPainter) {

                  hilite.removeHighlight(hilites[i]);
          }
       }
    }
     
    private class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
          
      public MyHighlightPainter(Color color) {
             
            super(color);                
              
       }
    }
}
