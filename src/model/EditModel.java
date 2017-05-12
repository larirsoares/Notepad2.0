
package model;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class EditModel {
	private String wordToFind;
	private int findIndex;
    private Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private MainPanel janelaPrincipal;
	
	public void find(MainPanel painelExtendido) {
		wordToFind = JOptionPane.showInputDialog("Type the word to find");
		if(wordToFind!=null){
			wordToFind = wordToFind.toUpperCase();
			try {
				findIndex = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().indexOf(wordToFind);
			} catch (BadLocationException e) {
				JOptionPane.showMessageDialog(null, "Word not found!", "No match", JOptionPane.WARNING_MESSAGE);
			}
	
			if (findIndex == -1) {
				JOptionPane.showMessageDialog(null, "Word not found!", "No match",
						JOptionPane.WARNING_MESSAGE);
			} else {
				selectFound(painelExtendido.getPainelTexto());
			}
		}
	}
	
	public void findBinary(MainPanel painelExtendido) {
		wordToFind = JOptionPane.showInputDialog("Type the word to find");
		
		if(wordToFind!=null){
			wordToFind = wordToFind.toUpperCase();
			try {
				String[] arrayWords ;
				arrayWords = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().split(" ");
			
				Arrays.sort(arrayWords);
				int pos = Arrays.binarySearch(arrayWords, wordToFind);
				
				if(pos>=0)
					findIndex = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().indexOf(arrayWords[pos]);
				else
					findIndex = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().indexOf(wordToFind);
		
				if (findIndex == -1) {
					JOptionPane.showMessageDialog(null, "Word not found!", "No match", JOptionPane.WARNING_MESSAGE);
				} else {
					selectFound(painelExtendido.getPainelTexto());
				}
			} catch (BadLocationException e) {
				JOptionPane.showMessageDialog(null, "Word not found!", "No match", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void findNext(MainPanel painelExtendido) {
		int cont = 0;
		wordToFind = JOptionPane.showInputDialog(null,"Type the word to find",painelExtendido.getPainelTexto().getSelectedText());
		
		if(wordToFind!=null){
			wordToFind = wordToFind.toUpperCase();
			try {
				findIndex = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().indexOf(wordToFind);
			} catch (BadLocationException e) {
				JOptionPane.showMessageDialog(null, "Word not found!", "No match", JOptionPane.WARNING_MESSAGE);
			}
	
			if (findIndex == -1) {
				JOptionPane.showMessageDialog(null, "Word not found!", "No match",
						JOptionPane.WARNING_MESSAGE);
			} else {
				while (findIndex < painelExtendido.getPainelTexto().getDocument().getLength() && findIndex != -1) {
					selectFound(painelExtendido.getPainelTexto());
					cont++;
	
					findIndex = findIndex + wordToFind.length();
	
					try {
						findIndex = painelExtendido.getPainelTexto().getDocument().getText(0,painelExtendido.getPainelTexto().getDocument().getLength()).toUpperCase().indexOf(wordToFind, findIndex);
					} catch (BadLocationException e) {
					}
	
					int response = JOptionPane.showConfirmDialog(null,
							"Find next match?", "FindNext",
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
							null);
	
					if (response == JOptionPane.NO_OPTION) {
						break;
					}
				}
	
				JOptionPane.showMessageDialog(null,
						"End of text! \nNumber of matches: " + cont);
			}
		}
	}
	
	private void selectFound(JTextComponent textComp) {
		textComp.grabFocus();
		textComp.select(findIndex, findIndex + wordToFind.length());
	}
	
	public void undo(UndoManager undo) {

		try {
			undo.undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			Toolkit.getDefaultToolkit().beep();
		}

	}

	public void redo(UndoManager undo) {

		try {
			undo.redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			Toolkit.getDefaultToolkit().beep();
		}

	}
        
        public void copy(MainPanel painelExtendido){
             String selection = painelExtendido.getPainelTexto().getSelectedText();
             StringSelection clipString = new StringSelection(selection);
             clip.setContents(clipString,clipString);
         }
        
        public void paste(MainPanel painelExtendido){             
             Transferable clipTransition = clip.getContents(painelExtendido);
             try{
                 String selection = (String)clipTransition.getTransferData(DataFlavor.stringFlavor);
                 if(selection.isEmpty()){
                     JOptionPane.showMessageDialog(janelaPrincipal, "Selecione um Texto!");
                 }
                 painelExtendido.getPainelTexto().replaceSelection(selection);
             }
             catch(Exception exc){
                JOptionPane.showMessageDialog(janelaPrincipal, "Selecione um Texto!");
             }
         }
        
        public void cut(MainPanel painelExtendido){
            String selection = painelExtendido.getPainelTexto().getSelectedText();
            StringSelection ss = new StringSelection(selection);
            clip.setContents(ss,ss);
            painelExtendido.getPainelTexto().replaceSelection("");
        }
        
        public void selectedAll(MainPanel painelExtendido){
            painelExtendido.getPainelTexto().selectAll();
        }
    
}
