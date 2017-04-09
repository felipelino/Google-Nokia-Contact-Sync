package br.contactsync.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import br.contactsync.gui.ContactSyncWindow;
import br.contactsync.main.ConvertToGoogle;
import br.contactsync.main.ConvertToNokia;
import br.util.language.LanguageTool;

/**
 * Executa a ação para conversão dos contatos
 * @author Felipe Lino
 */
public class SubmitActionListener implements ActionListener 
{
	/** Logger da classe */
	private static final Logger log = Logger.getLogger(SubmitActionListener.class);
	
	/** Janela Pai */
	private ContactSyncWindow window;
	
	/**
	 * Construtor
	 * @param window
	 */
	public SubmitActionListener(ContactSyncWindow window)
	{
		this.window = window;
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		String errorTitle = LanguageTool.getString("submit.dialog.error.title", "Conversion error");
		try
		{
			String input = window.getFieldInputFile().getText();
			String output = window.getFieldOutputFile().getText();
			File inputFile = FileUtils.getFile(input);
			File outputFile = FileUtils.getFile(output);
			
			if(!inputFile.isFile())
			{
				String errorMsg = LanguageTool.getString("submit.dialog.error.msg1", "Input file is a directory.");
				JOptionPane.showMessageDialog(this.window, errorMsg, errorTitle, JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(!outputFile.exists())
			{
				boolean isSuccess = outputFile.createNewFile();
				if(!isSuccess)
				{
					String errorMsg = LanguageTool.getString("submit.dialog.error.msg3", "Fail to create output file.");
					JOptionPane.showMessageDialog(this.window, errorMsg, errorTitle, JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!outputFile.isFile())
				{
					String errorMsg = LanguageTool.getString("submit.dialog.error.msg2", "Output file is a directory.");
					JOptionPane.showMessageDialog(this.window, errorMsg, errorTitle, JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			int count = -1;
			if(window.getmCfgGoogleNokia().isSelected())
			{
				int answer = confirmMessage("Google", "Nokia");
				if(JOptionPane.YES_OPTION == answer)
				{
					count = ConvertToNokia.convert(inputFile, outputFile);
				}
			}
			else
			{
				int answer = confirmMessage("Nokia", "Google");
				if(JOptionPane.YES_OPTION == answer)
				{
					count = ConvertToGoogle.convert(inputFile, outputFile);
				}
			}
			if(count > -1)
			{
				String finalMsg = LanguageTool.getString("submit.final.message", "contacts converted sucessfully.");
				finalMsg = count + " " + finalMsg;
				String title = LanguageTool.getString("submit.dialog.final.title", "Contacts conversion");
				JOptionPane.showMessageDialog(this.window, finalMsg, title, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception exc) 
		{
			log.error("Fail on conversion contatcts.", exc);
			String errorMsg = "Fail on conversion contatcts.\nStack Trace:\n" + ExceptionUtils.getStackTrace(exc);
			JOptionPane.showMessageDialog(this.window, errorMsg, errorTitle, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Exibe mensagem de confirmação
	 * @param from
	 * @param to
	 * @return
	 */
	private int confirmMessage(String from, String to)
	{
		String defaultMsg = "Do you want convert contacts from %s to %s?";
		String format = LanguageTool.getString("submit.confirm.message", defaultMsg);
		String message = String.format(format, new Object[]{from, to});

		Object[] options = {LanguageTool.getString("submit.dialog.yes", "Yes"), 
							LanguageTool.getString("submit.dialog.no", "No")};
		
		String title = LanguageTool.getString("submit.dialog.title", "Confirm conversion");
		
		int answer = JOptionPane.showOptionDialog(this.window,
												    message,
												    title,
												    JOptionPane.YES_NO_OPTION,
												    JOptionPane.QUESTION_MESSAGE,
												    null,
												    options,
												    options[0]);
		
		return answer;
	}

}
