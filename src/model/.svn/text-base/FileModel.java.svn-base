
package model;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import view.MainPanel;

/**
 *
 * @author Larissa
 */
public class FileModel {
	
	 
    
	public void newFile(MainPanel painelPrincipal, MainPanel painelInicial, JTabbedPane painelTabulado){
		
		int selecionaOpcao;
		
		if (!painelPrincipal.getExist()) {
	        selecionaOpcao = JOptionPane.showConfirmDialog(null,  
                    " O arquivo ainda não existe, deseja salva-lo? ", null,  
                    JOptionPane.OK_CANCEL_OPTION);  
            if (selecionaOpcao == JOptionPane.OK_OPTION) {
            	saveAs(painelPrincipal);
            }				
		} else{
			selecionaOpcao = JOptionPane.showConfirmDialog(null,  
			"Deseja Salvar o arquivo existente? ", null,  
			JOptionPane.OK_CANCEL_OPTION);  
			if (selecionaOpcao == JOptionPane.OK_OPTION) {  
				saveNormal(painelPrincipal, painelTabulado);	  
			 }
			painelPrincipal.setExist(false);
		}
		painelTabulado.setTitleAt(0, "Untitled");
		painelInicial.getPainelTexto().setText("");		
        
	}
	
    
    public void saveNormal(MainPanel painelPrincipal, JTabbedPane painelAuxiliar) {
  
			PrintWriter fout = null;
			
			String text = painelPrincipal.getPainelTexto().getText();
			if (!painelPrincipal.getExist()) {
				int selecionaOpcao = JOptionPane.showConfirmDialog(null,"O arquivo não existe, deseja salva-lo?", null,JOptionPane.OK_CANCEL_OPTION);  
				if (selecionaOpcao == JOptionPane.OK_OPTION) { 
					painelAuxiliar.setTitleAt(painelAuxiliar.getSelectedIndex(), saveAs(painelPrincipal));
				}
			}
			else{
	    			try {
	    				
	                    if (painelPrincipal.getPath().contains(".txt")) {	                    	
	                    	fout = new PrintWriter(new FileWriter(painelPrincipal.getPath()));
	                    } else {
	                    	fout = new PrintWriter(new FileWriter(painelPrincipal.getPath()+".txt"));
	                    }
	
	    				StringTokenizer st = new StringTokenizer(text, System
	    						.getProperty("line.separator"));
	    				while (st.hasMoreTokens()) {
	    					
	    					fout.println(st.nextToken());
	    				}
	    				
	    				fout.close();
	    			} catch (IOException ioe) {
	    				System.err.println("I/O Error on Save");
	    			}
			}
    }
    
    public void openFileSingle(JFrame janelaPrincipal, MainPanel painelPrincipal, JTabbedPane painelTabulado) {
    	JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(janelaPrincipal);
        String fileName = "";
        painelPrincipal.getPainelTexto().setText("");
        try {
            // to get the name of the selected file
            fileName = fileChooser.getSelectedFile().getPath();
            // to read the selected file
            Reader in = new FileReader(fileChooser.getSelectedFile());
            StringBuilder sb = new StringBuilder();
            // 100000 is the max. char can be written in the text area
            char[] buff = new char[100000];
            int nch;
            while ((nch = in.read(buff, 0, buff.length)) != -1) {
                sb.append(buff, 0, nch);
            }
            String text = sb.toString();
            painelPrincipal.getPainelTexto().setText(text);
            
            painelTabulado.setTitleAt(painelTabulado.getSelectedIndex(), formatTitle(fileName));
            painelPrincipal.setExist(true);
            painelPrincipal.setPath(fileChooser.getSelectedFile().getPath());
        } catch (FileNotFoundException x) {
            // no action
        } catch (IOException ioe) {
            //System.err.println("I/O Error on Open");
        }catch (Exception ioe) {
            //System.err.println("I/O Error on Open");
        }
        
       
    }
    
    public String formatTitle(String text) {
        String[] title = text.split("\\\\");
        String[] titleFinal = title[title.length - 1].split("\\.");
        
        return titleFinal[0];
    }
    
    public void exitTabSingle(MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        if ("".equals(painelPrincipal.getPainelTexto().getText())) {
            System.exit(0);
        } else {
            int option = JOptionPane.showConfirmDialog(null,
                    "Do you want to save the changes?");
            
            if (option == 0) {
                saveAs(painelPrincipal);
                System.exit(0);
            } else if (option == 1) {
                System.exit(0);
            }
        }
        
    }
    
    public String saveAs(MainPanel painelPrincipal) {
        NoteFileFilter filter = new NoteFileFilter();
        JFileChooser fileChooser = new JFileChooser();
        String name = "";
        String fileName = "";
        String text = "";

        // filter the kind of files, we want only TXT file
        filter.addExtension("txt");
        // to set a description for the file (TXT)
        filter.setDescription("TXT Documents");
        // setting the FileFilter to JFileChooser
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(painelPrincipal.getPainelTexto());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // initializing the PrintWriter, to save the text in a new file
            PrintWriter fout = null;
            try {
                // Se existe extensÃ£o no arquivo
                if (fileChooser.getSelectedFile().toString().indexOf(".") != -1) {

                    // Se o arquivo selecionado nÃ£o tem a extensÃ£o txt, ele
                    // adiciona
                    if (fileChooser.getSelectedFile().toString().substring(
                            fileChooser.getSelectedFile().toString().indexOf("."))
                            .equals(".txt")) {
                        
                        fout = new PrintWriter(new FileWriter(fileChooser
                                .getSelectedFile()));
                        fileName = fileChooser.getSelectedFile().getPath();
                    } else {
                        fout = new PrintWriter(new FileWriter(fileChooser
                                .getSelectedFile()
                                + ".txt"));
                        fileName = fileChooser.getSelectedFile().getPath() + ".txt";
                    }
                } else {
                    fout = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()
                            + ".txt"));
                    fileName = fileChooser.getSelectedFile().getPath() + ".txt";
                }

                // getting the text from the text area
                text = painelPrincipal.getPainelTexto().getText();
                // getting the name of the selected file
                // fileName = jfc.getSelectedFile().getPath();
                // using StringTokenizer for the 'fileContent' String
                StringTokenizer st = new StringTokenizer(text, System
                        .getProperty("line.separator"));
                while (st.hasMoreTokens()) {
                    // write the string (text) in the selected file
                    fout.println(st.nextToken());
                }
                // closing 'fout'
                fout.close();
                
                int i = fileName.lastIndexOf(".txt") - 1;
                int j = fileName.lastIndexOf('\\');
                do {
                    System.out.println(fileName.charAt(i));
                    name = fileName.charAt(i) + name;
                    i--;
                } while (i > j);
               painelPrincipal.setExist(true);
               painelPrincipal.setPath(fileChooser.getSelectedFile().getPath() + ".txt");
            } catch (IOException ioe) {
                System.err.println("I/O Error on Save");
            }
        }
        
        return name;
    }
    
    public void exitTabMulti(MainPanel painelPrincipal, JTabbedPane painelEmAbas) {
        if ("".equals(painelPrincipal.getPainelTexto().getText()) && painelEmAbas.getTabCount() == 1) {
            System.exit(0);
        }else if (!"".equals(painelPrincipal.getPainelTexto().getText()) && painelEmAbas.getTabCount() == 1) {
            int option = JOptionPane.showConfirmDialog(null,
                    "Do you want to save the changes?");
            if (option == 0) {
                saveAs(painelPrincipal);
                System.exit(0);
            } else if (option == 1) {
                System.exit(0);
            }
        } else if ("".equals(painelPrincipal.getPainelTexto().getText()) && painelEmAbas.getTabCount() > 1) {
            painelEmAbas.remove(painelEmAbas.getSelectedIndex());
        } else if (!"".equals(painelPrincipal.getPainelTexto().getText()) && painelEmAbas.getTabCount() > 1){
            int option = JOptionPane.showConfirmDialog(null,
                    "Do you want to save the changes?");
            if (option == 0) {
                saveAs(painelPrincipal);
                painelEmAbas.remove(painelEmAbas.getSelectedIndex());
            } else if (option == 1) {
                painelEmAbas.remove(painelEmAbas.getSelectedIndex());
            }
        }
    }
    
    public void openFileMulti(JFrame janelaPrincipal, MainPanel painelPrincipal, JTabbedPane painelTabulado) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(janelaPrincipal);
        String fileName = "";
        try {
            // to get the name of the selected file
            fileName = fileChooser.getSelectedFile().getPath();
            // to read the selected file
            Reader in = new FileReader(fileChooser.getSelectedFile());
            StringBuilder sb = new StringBuilder();
            // 100000 is the max. char can be written in the text area
            char[] buff = new char[100000];
            int nch;
            while ((nch = in.read(buff, 0, buff.length)) != -1) {
                sb.append(buff, 0, nch);
            }
            String text = sb.toString();
            if (painelPrincipal.getPainelTexto().getText().equals("")) {
                painelPrincipal.getPainelTexto().setText(text);
                painelTabulado.setTitleAt(painelTabulado.getSelectedIndex(), formatTitle(fileName));
            } else {
                MainPanel painel = new MainPanel();
                painel.getPainelTexto().setText(text);
                painelTabulado.add(formatTitle(fileName), painel);
                painelTabulado.setSelectedIndex(painelTabulado.getTabCount()-1);
                painelPrincipal = painel;
            }
            painelPrincipal.setExist(true);
            painelPrincipal.setPath(fileChooser.getSelectedFile().getPath());
        } catch (FileNotFoundException x) {
            // no action
        } catch (IOException ioe) {
            System.err.println("I/O Error on Open");
        }
    }
    
    public void fileProperties(JFrame janelaPrincipal, MainPanel painelPrincipal) {
    	if(painelPrincipal.getExist()){
    		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		File file = new File(painelPrincipal.getPath());
    		String props = "Propriedades do arquivo:" +
    		"\nLocalização: " + file.getPath() +
    		"\nTipo: " + (file.isDirectory() ? "Diretório" : "Arquivo") +
    		"\nÚltima modificação: " + 
    		df.format(new Date( file.lastModified() ) ) +
    		"\nOculto: " + (file.isHidden() ? "Sim" : "Não") +
    		"\nPermissões: " + 
    		"\n - Leitura: " + (file.canRead() ? "Sim" : "Não") +
    		"\n - Escrita: " + (file.canWrite() ? "Sim" : "Não") +
    		"\n - Execução: " + (file.canExecute() ? "Sim" : "Não");
    		showMessageDialog(janelaPrincipal,props);
      	} else {
      		showMessageDialog(janelaPrincipal,"O arquivo não foi salvo!");
      	}
    }
    
    public void printPage(){
        PrinterJob printer = PrinterJob.getPrinterJob();
        //printer.setPrintable( this);
        HashPrintRequestAttributeSet printAttr = new HashPrintRequestAttributeSet();
        
        if(printer.printDialog(printAttr))     // Display print dialog
        {            // If true is returned...
            try
            {
                printer.print(printAttr);    // then print
            }
            catch(PrinterException e)
            {
                //JOptionPane.showMessageDialog(this,"Failed to print the file: "+e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    
    
}
