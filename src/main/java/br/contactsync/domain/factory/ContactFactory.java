package br.contactsync.domain.factory;

public abstract class ContactFactory 
{
	/**
	 * Trata caracteres terminadores de linha: quebra de linha e retorno de carro
	 * @param str
	 * @return
	 */
	protected static String prepare(String str) 
	{
		String newLine = "";
		boolean flag = false;
		for(char ch : str.toCharArray())
		{
			String append = String.valueOf(ch);
			if(ch == '"' && flag == false)
			{
				flag = true; //Flag true informando que abriu aspas
			}
			else if(ch == '"' && flag == true)
			{
				flag = false; //Flag false informando que fechou aspas
			}
			else if(ch == '\r' && flag == true)
			{
				/* dentro das aspas remove retorno de carro */
				append = "";
			}
			else if(ch == '\n' && flag == true)
			{
				/* dentro das aspas remove quebra de linha e transforma em outro separador */
				append = " ";
			}
			newLine += append;
		}

		return newLine;
	}
}
