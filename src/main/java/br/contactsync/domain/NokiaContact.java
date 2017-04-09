package br.contactsync.domain;


/**
 * Representa um contato do Nokia
 * @author Felipe Lino
 */
public class NokiaContact extends Contact
{
	/*
	public static final String DEFAULT_HEADER = "\"Cargo\";\"Nome\";\"Segundo nome\";\"Sobrenome\";\"Sufixo\";" +
			"\"Cargo\";\"Empresa\";\"Anivers\u00e1rio\";\"Endere\u00e7o SIP\";\"Push-to-talk\";\"Comp. exibi\u00e7\u00e3o\";" +
			"\"ID de usu\u00e1rio\";\"Notas\";\"Celular geral\";\"Telefone geral\";\"E-mail geral\";\"Fax geral\";" +
			"\"Chamada de v\u00eddeo geral\";\"Endere\u00e7o Web geral\";\"Endere\u00e7o VOIP geral\";\"Caixa postal geral\";" +
			"\"Ramal geral\";\"Endere\u00e7o geral\";\"CEP geral\";\"Cidade geral\";\"Estado geral\";\"Pa\u00eds geral\";" +
			"\"Celular pessoal\";\"Telefone residencial\";\"E-mail pessoal\";\"Fax residencial\";" +
			"\"Chamada de v\u00eddeo pessoal\";\"Endere\u00e7o Web residencial\";\"Endere\u00e7o VOIP residencial\";" +
			"\"Caixa postal residencial\";\"Ramal residencial\";\"Endere\u00e7o residencial\";\"CEP residencial\";" +
			"\"Cidade de resid\u00eancia\";\"Estado de resid\u00eancia\";\"Pa\u00eds de resid\u00eancia\";\"Celular comercial\";" +
			"\"Telefone comercial\";\"E-mail comercial\";\"Fax comercial\";\"Chamada de v\u00eddeo comercial\";" +
			"\"Endere\u00e7o Web comercial\";\"Endere\u00e7o VOIP comercial\";\"Caixa postal comercial\";\"Ramal comercial\";" +
			"\"Endere\u00e7o comercial\";\"CEP comercial\";\"Cidade comercial\";\"Estado comercial\";\"Pa\u00eds comercial\";\"\"";
	*/
	public static final String DEFAULT_HEADER = "\"Nome\";\"Segundo nome\";\"Sobrenome\";\"Sufixo\";"+ 
								"\"Cargo\";\"Empresa\";\"Anivers\u00e1rio\";\"Notas\";\"Celular geral\";\"Telefone geral\";\"E-mail geral\";" +
								"\"Endere\u00e7o Web geral\";\"Endere\u00e7o geral\";" +
								"\"CEP geral\";\"Cidade geral\";\"Estado geral\";"+
								"\"Pa\u00eds geral\";\"Celular pessoal\";\"Telefone residencial\";" +
								"\"E-mail pessoal\";\"Endere\u00e7o Web residencial\";\"Endere\u00e7o residencial\";\"CEP residencial\";\"Cidade de resid\u00eancia\";" +
								"\"Estado de resid\u00eancia\";\"Pa\u00eds de resid\u00eancia\";\"Celular comercial\";\"Telefone comercial\";\"E-mail comercial\";" +
								"\"Endere\u00e7o Web comercial\";\"Endere\u00e7o comercial\";\"CEP comercial\";\"Cidade de comercial\";\"Estado de comercial\";" +
								"\"Pa\u00eds de comercial\""; 
	public static final String NEW_HEADER = "\"Cargo\";\"Nome\";\"Segundo nome\";\"Sobrenome\";\"Sufixo\";\"Cargo\";\"Empresa\";\"Aniversário\";\"Endereço SIP\";\"Push-to-talk\";\"Comp. exibição\";\"ID de usuário\";\"Notas\";\"Celular geral\";\"Telefone geral\";\"E-mail geral\";\"Fax geral\";\"Chamada de vídeo geral\";\"Endereço Web geral\";\"Endereço VOIP geral\";\"Caixa postal geral\";\"Ramal geral\";\"Endereço geral\";\"CEP geral\";\"Cidade geral\";\"Estado geral\";\"País geral\";\"Celular pessoal\";\"Telefone residencial\";\"E-mail pessoal\";\"Fax residencial\";\"Chamada de vídeo pessoal\";\"Endereço Web residencial\";\"Endereço VOIP residencial\";\"Caixa postal residencial\";\"Ramal residencial\";\"Endereço residencial\";\"CEP residencial\";\"Cidade de residência\";\"Estado de residência\";\"País de residência\";\"Celular comercial\";\"Telefone comercial\";\"E-mail comercial\";\"Fax comercial\";\"Chamada de vídeo comercial\";\"Endereço Web comercial\";\"Endereço VOIP comercial\";\"Caixa postal comercial\";\"Ramal comercial\";\"Endereço comercial\";\"CEP comercial\";\"Cidade comercial\";\"Estado comercial\";\"País comercial\";\"\"";
	
	/**
	 * Construtor
	 * @param header
	 * @param line
	 */
	public NokiaContact(String header, String line) 
	{
		super(header, line);
	}
	
	/**
	 * A partir de um contato google monta um contato Nokia
	 * @param googleContact
	 */
	public NokiaContact(GoogleContact googleContact)
	{
		super(NEW_HEADER);
		super.addData("\"Nome\"", googleContact.getProperty("Given Name"));
		String str= googleContact.getProperty("Additional Name");
		super.addData("\"Segundo nome\"", str);
		super.addData("\"Sobrenome\"", googleContact.getProperty("Family Name"));
		super.addData("\"Sufixo\"", googleContact.getProperty("Name Suffix"));
		super.addData("\"Cargo\"", googleContact.getSinglePropertyValue("Organization 1 - Title"));
		super.addData("\"Empresa\"", googleContact.getSinglePropertyValue("Organization 1 - Name"));
		super.addData("\"Anivers\u00e1rio\"", googleContact.getProperty("Birthday"));
		super.addData("\"Notas\"", googleContact.getProperty("Notes")+" "+googleContact.getProperty("Nickname"));
		super.addData("\"Celular geral\"", googleContact.getSinglePropertyValue("Phone 1 - Value"));
		super.addData("\"Telefone geral\"", googleContact.getSinglePropertyValue("Phone 2 - Value"));
		super.addData("\"E-mail geral\"", googleContact.getSinglePropertyValue("E-mail 1 - Value"));
		super.addData("\"Endere\u00e7o Web geral\"", googleContact.getSinglePropertyValue("Website 1 - Value"));
		
		//super.addData("\"Endere\u00e7o geral\"", googleContact.getSinglePropertyValue("Address 1 - Formatted"));
		String address = googleContact.getSinglePropertyValue("Address 1 - Street").replaceAll("-", "");
		if(googleContact.getSinglePropertyValue("Address 1 - Extended Address").length()>0){
			address += ", "+googleContact.getSinglePropertyValue("Address 1 - Extended Address");
		}
		super.addData("\"Endere\u00e7o geral\"", address);
		super.addData("\"CEP geral\"", googleContact.getSinglePropertyValue("Address 1 - Postal Code"));
		super.addData("\"Cidade geral\"", googleContact.getSinglePropertyValue("Address 1 - City"));
		super.addData("\"Estado geral\"", googleContact.getSinglePropertyValue("Address 1 - Region"));
		super.addData("\"Pa\u00eds geral\"", googleContact.getSinglePropertyValue("Address 1 - Country"));
		
		super.addData("\"Celular pessoal\"", googleContact.getSinglePropertyValue("Phone 3 - Value"));
		super.addData("\"Telefone residencial\"", googleContact.getSinglePropertyValue("Phone 4 - Value"));
		super.addData("\"E-mail pessoal\"", googleContact.getSinglePropertyValue("E-mail 2 - Value"));
		super.addData("\"Endere\u00e7o Web residencial\"", googleContact.getSinglePropertyValue("Website 2 - Value"));
		
		//super.addData("\"Endere\u00e7o residencial\"", googleContact.getSinglePropertyValue("Address 3 - Formatted"));
		address = googleContact.getSinglePropertyValue("Address 3 - Street").replaceAll("-", "");
		if(googleContact.getSinglePropertyValue("Address 3 - Extended Address").length()>0){
			address += ", "+googleContact.getSinglePropertyValue("Address 3 - Extended Address");
		}
		super.addData("\"Endere\u00e7o residencial\"", address);
		super.addData("\"CEP residencial\"", googleContact.getSinglePropertyValue("Address 3 - Postal Code"));
		super.addData("\"Cidade de resid\u00eancia\"", googleContact.getSinglePropertyValue("Address 3 - City"));
		super.addData("\"Estado de resid\u00eancia\"", googleContact.getSinglePropertyValue("Address 3 - Region"));
		super.addData("\"Pa\u00eds de resid\u00eancia\"", googleContact.getSinglePropertyValue("Address 3 - Country"));
		
		super.addData("\"Celular comercial\"", googleContact.getSinglePropertyValue("Phone 5 - Value"));
		super.addData("\"Telefone comercial\"", googleContact.getSinglePropertyValue("Phone 6 - Value"));
		super.addData("\"E-mail comercial\"", googleContact.getSinglePropertyValue("E-mail 3 - Value"));
		super.addData("\"Endere\u00e7o Web comercial\"", googleContact.getSinglePropertyValue("Website 3 - Value"));
		
		//super.addData("\"Endere\u00e7o comercial\"", googleContact.getSinglePropertyValue("Address 2 - Formatted"));
		address = googleContact.getSinglePropertyValue("Address 2 - Street").replaceAll("-", "");
		if(googleContact.getSinglePropertyValue("Address 2 - Extended Address").length()>0){
			address += ", "+googleContact.getSinglePropertyValue("Address 2 - Extended Address");
		}
		super.addData("\"Endere\u00e7o comercial\"", address);
		super.addData("\"CEP comercial\"", googleContact.getSinglePropertyValue("Address 2 - Postal Code"));
		super.addData("\"Cidade de comercial\"", googleContact.getSinglePropertyValue("Address 2 - City"));
		super.addData("\"Estado de comercial\"", googleContact.getSinglePropertyValue("Address 2 - Region"));
		super.addData("\"Pa\u00eds de comercial\"", googleContact.getSinglePropertyValue("Address 2 - Country"));
	}
	
	
	/*
	 * "Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi," +
			"Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name," +
			"Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation," +
			"Hobby,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type," +
			"E-mail 2 - Value,Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value,Phone 3 - Type,Phone 3 - Value," +
			"Address 1 - Type,Address 1 - Formatted,Address 1 - Street,Address 1 - City,Address 1 - PO Box,Address 1 - Region," +
			"Address 1 - Postal Code,Address 1 - Country,Address 1 - Extended Address"

			Felipe Gon\u00e7alves de Oliveira Lino,Felipe,Gon\u00e7alves de Oliveira,Lino,,,,,,,,,,,,,,,,,,,,,,,* My Contacts,* Home,felipelino44@gmail.com,Other,felipe_lino@yahoo.com.br,Work,felipe.lino.POLITEC@petrobras.com.br,Vivo (Principal),9905-4296,Oi Fixo (Comercial),3487-1198,Celular,6917-4961,Home,"Rua S\u00e3o Francisco Xavier, 927 - S\u00e3o Francisco Xavier - Rio de Janeiro","Rua S\u00e3o Francisco Xavier, 927", - Rio de Janeiro,,,,, - S\u00e3o Francisco Xavier
			
			Bruno Lee,Bruno,,Lee,,,,,,,,,,,,,,,,,,,,,,"Categoria: Pessoal Data de nascimento: 4/10/1985",
			* My Contacts,Home,slipkloco@hotmail.com,* Other,
			* slipkloco@gmail.com,Claro (Celular),9106-3877,
			* Oi Fixo (Residencial),2552-1326,Vivo (Celular),
			* 9620-6226,Home,"Rua Graja\u00fa, 225 / 102  - Graja\u00fa - Rio de Janeiro",
			* "Rua Graja\u00fa, 225 / 102", - Rio de Janeiro,,,,, - Graja\u00fa
	 */

	/**
	 * @see br.contactsync.domain.Contact#getSeparator()
	 */
	@Override
	char getSeparator() 
	{
		return ';';
	}

	/**
	 * @see br.contactsync.domain.Contact#treatValue(java.lang.String)
	 */
	@Override
	String treatValue(String value)
	{
		value = value.replaceAll("\"", "");
		value = "\"" + value + "\"";
		return value;
	}
}

