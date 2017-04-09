package br.contactsync.main;

import br.contactsync.gui.ContactSyncWindow;


/**
 * Programa principal para executar interface gráfica
 * @author Felipe Lino
 */
public class ConvertContactGUI 
{
	public static void main(String[] args) throws Exception
	{
		ContactSyncWindow jMain;
		ContactSyncWindow.setDefaultLookAndFeelDecorated(true);
		jMain = new ContactSyncWindow();
		
		jMain.setDefaultCloseOperation(ContactSyncWindow.EXIT_ON_CLOSE);
		jMain.setFocusable(true);
		jMain.setVisible(true);
	}

}
