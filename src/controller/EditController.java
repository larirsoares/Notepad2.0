
package controller;


import javax.swing.undo.UndoManager;

import model.EditModel;
import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class EditController {
    private EditModel editModel = new EditModel();
    
    
    public void findController(MainPanel painelExtendido) {
    	editModel.find(painelExtendido);
    }
    
    public void findBinaryController(MainPanel painelExtendido) {
    	editModel.findBinary(painelExtendido);
    }
    
    public void findNextController(MainPanel painelExtendido) {
    	editModel.findNext(painelExtendido);
    }
    
    
    public void undo(UndoManager undo) {
    	editModel.undo(undo);
    }
    
    public void redo(UndoManager undo) {
    	editModel.redo(undo);
    }
    
    public void copy(MainPanel painelExtendido) {
    editModel.copy(painelExtendido);
    }
    
    public void paste(MainPanel painelExtendido) {
    	editModel.paste(painelExtendido);
    }
    
    public void cut(MainPanel painelExtendido) {
    	editModel.cut(painelExtendido);
    }
    
    public void selectedAll(MainPanel painelExtendido) {
    	editModel.selectedAll(painelExtendido);
    }
}
