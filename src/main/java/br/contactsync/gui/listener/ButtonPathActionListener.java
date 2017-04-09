package br.contactsync.gui.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import br.util.file.FileUtil;

/**
 * Listener para tratar os eventos do botão de abrir Path
 * @author Felipe Lino
 */
public class ButtonPathActionListener implements ActionListener 
{
	/** Parent component */
	private Component parent;
	
	/** TextField que exibe o Path */
	private JTextField pathField;
	
	/** Informa se é um arquivo */
	private boolean isFile;
	
	/** Tipo de seleção de arquivo */
	private int fileSelectionMode;
	
	/** */
	private FileFilter filter;
	
	
	/**
	 * Construtor
	 * @param parent
	 * @param pathField
	 * @param isFile informa se o arquivo a ser selecionado é um arquivo
	 * @param fileSelectionMode @see JFileChooser
	 * @param filter
	 */
	public ButtonPathActionListener(Component parent, JTextField pathField, boolean isFile, int fileSelectionMode, FileFilter filter) 
	{
		super();
		this.parent = parent;
		this.pathField = pathField;
		this.isFile = isFile;
		this.fileSelectionMode = fileSelectionMode;
		this.filter = filter;
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(this.fileSelectionMode);
        if(this.filter != null)
        {
        	fileChooser.setFileFilter(this.filter);
        }
        
        int returnVal = fileChooser.showOpenDialog(this.parent);
        if (returnVal == JFileChooser.APPROVE_OPTION);
        {
            File file = fileChooser.getSelectedFile();
            if( file != null)
            {
            	if(!this.isFile)//Eh diretorio que deve selecionar
            	{
            		file = FileUtil.getDirectory(file);
            	}
            	if(this.pathField != null)
            	{
            		this.pathField.setText(file.getAbsolutePath());
            	}
            }
        } 
	}
}
