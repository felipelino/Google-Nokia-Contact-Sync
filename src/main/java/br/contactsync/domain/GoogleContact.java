package br.contactsync.domain;


/**
 * Representa um contato do Google
 * @author Felipe Lino
 */
public class GoogleContact extends Contact 
{
	private static final String SEPARATOR_AUX = "#AUX#";
	public static final char DEAFULT_SEPARATOR =',';
	public static final String DEFAULT_HEADER = "Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi," +
			"Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name," +
			"Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation," +
			"Hobby,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type," +
			"E-mail 2 - Value,E-mail 3 - Type,E-mail 3 - Value,Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value," +
			"Phone 3 - Type,Phone 3 - Value," +
			"Phone 4 - Type,Phone 4 - Value," +
			"Phone 5 - Type,Phone 5 - Value," +
			"Phone 6 - Type,Phone 6 - Value," +
			"Address 1 - Type,Address 1 - Formatted,Address 1 - Street,Address 1 - City," +
			"Address 1 - PO Box,Address 1 - Region,Address 1 - Postal Code,Address 1 - Country,Address 1 - Extended Address," +
			"Address 2 - Type,Address 2 - Formatted,Address 2 - Street,Address 2 - City," +
			"Address 2 - PO Box,Address 2 - Region,Address 2 - Postal Code,Address 2 - Country,Address 2 - Extended Address," +
			"Address 3 - Type,Address 3 - Formatted,Address 3 - Street,Address 3 - City," +
			"Address 3 - PO Box,Address 3 - Region,Address 3 - Postal Code,Address 3 - Country,Address 3 - Extended Address," +
			"Organization 1 - Type,Organization 1 - Name,Organization 1 - Yomi Name,Organization 1 - Title," +
			"Organization 1 - Department,Organization 1 - Symbol,Organization 1 - Location,Organization 1 - Job Description," +
			"Website 1 - Type,Website 1 - Value," +
			"Website 2 - Type,Website 2 - Value," +
			"Website 3 - Type,Website 3 - Value";
	/**
	 * Construtor
	 * @param header
	 * @param line
	 */
	public GoogleContact(String header, String line) 
	{
		super(header, line);
	}
	
	/**
	 * Transforma um contato Nokia em Contato Google
	 * @param nokiaContact
	 */
	public GoogleContact(NokiaContact nokiaContact)
	{
		super(DEFAULT_HEADER);
		super.addData("Name", nokiaContact.getProperty("\"Nome\""));
		super.addData("Given Name", getGivenName(nokiaContact.getProperty("\"Nome\"")));
		super.addData("Additional Name", nokiaContact.getProperty("\"Segundo nome\""));
		super.addData("Family Name", nokiaContact.getProperty("\"Sobrenome\""));
		super.addData("Yomi Name", "");
		super.addData("Given Name Yomi", "");
		super.addData("Additional Name Yomi", "");
		super.addData("Family Name Yomi", "");
		super.addData("Name Prefix", "");
		super.addData("Name Suffix", nokiaContact.getProperty("\"Sufixo\""));
		super.addData("Initials", "");
		super.addData("Nickname", "");
		super.addData("Short Name", "");
		super.addData("Maiden Name", "");
		
		super.addData("Birthday", nokiaContact.getProperty("\"Anivers\u00e1rio\""));
		super.addData("Gender", "");
		super.addData("Location", "");
		super.addData("Billing Information", "");
		super.addData("Directory Server", "");
		super.addData("Mileage", "");
		super.addData("Occupation", "");
		super.addData("Hobby", "");
		super.addData("Sensitivity", "");
		super.addData("Priority", "");
		super.addData("Subject", "");
				 							
		super.addData("Notes", SEPARATOR_AUX + nokiaContact.getProperty("\"Notas\"") + SEPARATOR_AUX);
		super.addData("Group Membership", "* My Contacts");
		
		super.addData("E-mail 1 - Type", "E-mail geral");
		super.addData("E-mail 1 - Value", nokiaContact.getSinglePropertyValue("\"E-mail geral\""));
		super.addData("E-mail 2 - Type", "E-mail pessoal");
		super.addData("E-mail 2 - Value", nokiaContact.getSinglePropertyValue("\"E-mail pessoal\""));
		super.addData("E-mail 3 - Type", "E-mail comercial");
		super.addData("E-mail 3 - Value", nokiaContact.getSinglePropertyValue("\"E-mail comercial\""));
		
		super.addData("Phone 1 - Type", "Celular geral");
		super.addData("Phone 1 - Value", nokiaContact.getSinglePropertyValue("\"Celular geral\""));
		
		super.addData("Phone 2 - Type", "Telefone geral");
		super.addData("Phone 2 - Value", nokiaContact.getSinglePropertyValue("\"Telefone geral\""));
		
		super.addData("Phone 3 - Type", "Celular pessoal");
		super.addData("Phone 3 - Value", nokiaContact.getSinglePropertyValue("\"Celular pessoal\""));
		
		super.addData("Phone 4 - Type", "Telefone residencial");
		super.addData("Phone 4 - Value", nokiaContact.getSinglePropertyValue("\"Telefone residencial\""));
		
		super.addData("Phone 5 - Type", "Celular comercial");
		super.addData("Phone 5 - Value", nokiaContact.getSinglePropertyValue("\"Celular comercial\""));
		
		super.addData("Phone 6 - Type", "Telefone comercial");
		super.addData("Phone 6 - Value", nokiaContact.getSinglePropertyValue("\"Telefone comercial\""));
		
		super.addData("Address 1 - Type", "Home");
		super.addData("Address 1 - Formatted", "");
		super.addData("Address 1 - Street",SEPARATOR_AUX + getStreet(nokiaContact.getSinglePropertyValue("\"Endere\u00e7o geral\"")) + SEPARATOR_AUX);
		super.addData("Address 1 - Postal Code", nokiaContact.getSinglePropertyValue("\"CEP geral\""));
		super.addData("Address 1 - City", nokiaContact.getSinglePropertyValue("\"Cidade geral\""));
		super.addData("Address 1 - PO Box", nokiaContact.getSinglePropertyValue("\"Caixa postal geral\""));
		super.addData("Address 1 - Region", nokiaContact.getSinglePropertyValue("\"Estado geral\""));
		super.addData("Address 1 - Country", nokiaContact.getSinglePropertyValue("\"Pa\u00eds geral\""));
		super.addData("Address 1 - Extended Address", "");
		
		super.addData("Address 2 - Type", "Comercial");
		super.addData("Address 2 - Formatted", "");
		super.addData("Address 2 - Street", SEPARATOR_AUX + getStreet(nokiaContact.getSinglePropertyValue("\"Endere\u00e7o comercial\"")) + SEPARATOR_AUX);
		super.addData("Address 2 - Postal Code", nokiaContact.getSinglePropertyValue("\"CEP comercial\""));
		super.addData("Address 2 - City", nokiaContact.getSinglePropertyValue("\"Cidade de comercial\""));
		super.addData("Address 2 - PO Box", nokiaContact.getSinglePropertyValue("\"Caixa postal comercial\""));
		super.addData("Address 2 - Region", nokiaContact.getSinglePropertyValue("\"Estado de comercial\""));
		super.addData("Address 2 - Country", nokiaContact.getSinglePropertyValue("\"Pa\u00eds de comercial\""));
		super.addData("Address 2 - Extended Address", "");
		
		super.addData("Address 3 - Type", "Outros");
		super.addData("Address 3 - Formatted", "");
		super.addData("Address 3 - Street", SEPARATOR_AUX + getStreet(nokiaContact.getSinglePropertyValue("\"Endere\u00e7o residencial\"")) + SEPARATOR_AUX);
		super.addData("Address 3 - Postal Code", nokiaContact.getSinglePropertyValue("\"CEP residencial\""));
		super.addData("Address 3 - City", nokiaContact.getSinglePropertyValue("\"Cidade de resid\u00eancia\""));
		super.addData("Address 3 - Region", nokiaContact.getSinglePropertyValue("\"Estado de resid\u00eancia\""));
		super.addData("Address 3 - PO Box", nokiaContact.getSinglePropertyValue("\"Caixa postal de resid\u00eancia\""));
		super.addData("Address 3 - Country", nokiaContact.getSinglePropertyValue("\"Pa\u00eds de resid\u00eancia\""));
		super.addData("Address 3 - Extended Address", "");
		
		super.addData("Organization 1 - Type", "");
		super.addData("Organization 1 - Name", nokiaContact.getSinglePropertyValue("\"Empresa\""));
		super.addData("Organization 1 - Yomi Name", "");
		super.addData("Organization 1 - Title", nokiaContact.getSinglePropertyValue("\"Cargo\""));
		super.addData("Organization 1 - Department", "");
		super.addData("Organization 1 - Symbol", "");
		super.addData("Organization 1 - Location", "");
		super.addData("Organization 1 - Job Description", "");

		super.addData("Website 1 - Type", "Endere\u00e7o Web geral");
		super.addData("Website 1 - Value", nokiaContact.getSinglePropertyValue("\"Endere\u00e7o Web geral\""));
		super.addData("Website 2 - Type", "Endere\u00e7o Web residencial");
		super.addData("Website 2 - Value", nokiaContact.getSinglePropertyValue("\"Endere\u00e7o Web residencial\""));
		super.addData("Website 3 - Type", "Endere\u00e7o Web comercial");
		super.addData("Website 3 - Value", nokiaContact.getSinglePropertyValue("\"Endere\u00e7o Web comercial\""));

	}

	/**
	 * @param street
	 * @return
	 */
	private String getStreet(String street) 
	{
		int fromIndex = street.indexOf(',');
		if(fromIndex > 1)
		{
			String newStreet = street.substring(0, fromIndex);
			newStreet+="#SEPARADOR#";
			String aux = street.substring(fromIndex).replaceAll(",", " ");
			newStreet += aux;
			newStreet = newStreet.replaceAll("#SEPARADOR#", ",");
			return newStreet;
		}
		else
		{
			return street;
		}
	}

	/**
	 * @param fullName
	 * @return
	 */
	private String getGivenName(String fullName) 
	{
		String givenName = fullName;
		int	pos = fullName.indexOf(" ");
		if(pos > 0)
		{
			givenName = fullName.substring(0, pos);
			givenName = givenName.trim();
		}
		return givenName;
	}

	/**
	 * @see br.contactsync.domain.Contact#getSeparator()
	 */
	@Override
	char getSeparator() 
	{
		return DEAFULT_SEPARATOR;
	}

	/**
	 * @see br.contactsync.domain.Contact#treatValue(java.lang.String)
	 */
	@Override
	String treatValue(String value)
	{
		value = value.replaceAll("\"", "");
		value = value.replaceAll(SEPARATOR_AUX, "\"");
		return value;
	}
}
