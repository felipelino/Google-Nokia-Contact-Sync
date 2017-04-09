package br.contactsync.domain.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.contactsync.domain.GoogleContact;
import br.contactsync.domain.NokiaContact;

/**
 * Cria contatos do Nokia
 * @author Felipe Lino
 */
public class NokiaContactFactory extends ContactFactory
{
	/**
	 * Cria lista de contatos do Nokia a partir de um arquivo CSV
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<NokiaContact> create(File file) throws Exception
	{
		List<NokiaContact> list = new ArrayList<NokiaContact>();
		
		String txt = FileUtils.readFileToString(file,"UTF-8");
		txt = prepare(txt);
		String lines[] = txt.split("\n");
		String header = lines[0];
		for(int i = 0; i < lines.length; i++)
		{	
			if(i > 0)
			{
				String line = lines[i];
				NokiaContact contact = new NokiaContact(header, line);
				System.out.println("Nokia Contact:["+contact+"]");
				list.add(contact);
			}
		}
	
		return list;
	}
	
	/**
	 * Cria uma lista de contatos Nokia a partir de uma lista de contatos do Google
	 * @param googleList
	 * @return
	 * @throws Exception
	 */
	public static List<NokiaContact> create(List<GoogleContact> googleList) throws Exception
	{
		List<NokiaContact> list = new ArrayList<NokiaContact>();
		for(GoogleContact googleContact : googleList)
		{
			NokiaContact nokiaContact = new NokiaContact(googleContact);
			list.add(nokiaContact);
		}
		return list;
	}
}
