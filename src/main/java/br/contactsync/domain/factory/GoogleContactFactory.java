package br.contactsync.domain.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.contactsync.domain.GoogleContact;
import br.contactsync.domain.NokiaContact;

/**
 * Cria contatos do Google
 * @author Felipe Lino
 */
public class GoogleContactFactory extends ContactFactory
{
	/**
	 * Cria uma lista de contatos do google a partir de um arquivo CSV
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<GoogleContact> create(File file) throws Exception
	{
		List<GoogleContact> list = new ArrayList<GoogleContact>();
		
		String txt = FileUtils.readFileToString(file, "UTF-8");
		txt = prepare(txt);
		String[] lines = txt.split("\n");
		String header = lines[0];
		for(int i = 0; i < lines.length; i++)
		{	
			if(i > 0)
			{
				String line = lines[i];
				GoogleContact contact = new GoogleContact(header, line);
				list.add(contact);
			}
		}
	
		return list;
	}
	
	/**
	 * Cria uma lista de contatos Google a partir de uma lista de contatos do Nokia
	 * @param googleList
	 * @return
	 * @throws Exception
	 */
	public static List<GoogleContact> create(List<NokiaContact> nokiaList) throws Exception
	{
		List<GoogleContact> list = new ArrayList<GoogleContact>();
		for(NokiaContact nokiaContact : nokiaList)
		{
			GoogleContact googleContact = new GoogleContact(nokiaContact);
			list.add(googleContact);
		}
		return list;
	}
}
