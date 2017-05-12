
package controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import model.FileModel;
import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class FileController {
    
    private FileModel fileModel = new FileModel();
    
    
    
    public void newFileMulti(JTabbedPane painelPrincipalEmAbas, final UndoManager undo) {
        painelPrincipalEmAbas.add(new MainPanel());
        painelPrincipalEmAbas.setTitleAt(painelPrincipalEmAbas.getTabCount()-1, "Untitled");
        painelPrincipalEmAbas.setIconAt(painelPrincipalEmAbas.getTabCount()-1, new ImageIcon((getClass().getResource("/imagens/fileUntitled.png"))));
        painelPrincipalEmAbas.setSelectedIndex(painelPrincipalEmAbas.getTabCount()-1);
        ((MainPanel)painelPrincipalEmAbas.getSelectedComponent()).getPainelTexto().getDocument().addUndoableEditListener( new UndoableEditListener() {
			public void undoableEditHappened( UndoableEditEvent e ) {
				//Remember the edit and update the menus
				undo.addEdit( e.getEdit() );
				//undoAction.update();
				//redoAction.update();
			}
		} );
        
    }
    
    public void newFileSingle(MainPanel painelPrincipal, MainPanel painelInicial, JTabbedPane painelAuxiliar, String fileName) {
        /*JOptionPane novoFile = new JOptionPane();
        int option = novoFile.showConfirmDialog(null, "Do you want to save the current file?","Save file?", JOptionPane.YES_NO_OPTION);
        
        painelInicial.getPainelTexto().setText(""); //limpa a caixa de texto atual
        if (option==0) {
            fileModel.save(fileName, painelAuxiliar); //aqui salva;
        }*/
    	fileModel.newFile(painelPrincipal, painelInicial,painelAuxiliar);
    }
    
    public void openFileSingle(JFrame janelaPrincipal, MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        fileModel.openFileSingle(janelaPrincipal, painelPrincipal, painelEmAbas);
    }
    
    public void exitSingle(MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        fileModel.exitTabSingle(painelPrincipal, painelEmAbas);
    }

    public void exitMulti(MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        fileModel.exitTabMulti(painelPrincipal, painelEmAbas);
    }

    public void openFileMulti(JFrame janelaPrincipal, MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        fileModel.openFileMulti(janelaPrincipal, painelPrincipal, painelEmAbas);
    }

    public void saveAs(MainPanel painelPrincipal, JTabbedPane painelAuxiliar) {
    	painelAuxiliar.setTitleAt(painelAuxiliar.getSelectedIndex(), fileModel.saveAs(painelPrincipal));        
    }
    
    public void saveNormal(MainPanel painelPrincipal, JTabbedPane painelAuxiliar) {
        fileModel.saveNormal(painelPrincipal,painelAuxiliar);
    }
    
   public void fileProperties(JFrame janelaPrincipal, MainPanel painelPrincipal) {
        fileModel.fileProperties(janelaPrincipal, painelPrincipal);
    }
   
   public void printPage() {
       fileModel.printPage();
   }
   
}
