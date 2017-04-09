package br.contactsync.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Representa genericamente um contato e guarda suas informações.
 * @author Felipe Lino
 */
public abstract class Contact 
{
	protected static final String SEPARADOR = "#SEPARADOR#";
	
	private Map<Integer, String> mapHeader;
	private Map<String, String> mapData;
	
	/**
	 * Construtor
	 * @param header
	 * @param line
	 */
	protected Contact(String header, String line)
	{
		this.mapHeader = new HashMap<Integer, String>();
		this.mapData = new HashMap<String, String>();
		
		this.mountHeader(header);
		
		line = prepare(line);
		String arrData[] = line.split(Contact.SEPARADOR);
		
		for(int i = 0; i < arrData.length; i++)
		{
			String key = this.mapHeader.get(Integer.valueOf(i));
			String value = arrData[i].trim();
			this.addData(key, value);
		}
	}
	
	
	/**
	 * Construtor
	 * @param header
	 */
	protected Contact(String header)
	{
		this.mapHeader = new HashMap<Integer, String>();
		this.mapData = new HashMap<String, String>();
		
		this.mountHeader(header);
	}
	
	/**
	 * @param header
	 */
	protected void mountHeader(String header)
	{
		header = prepare(header);
		/* Monta Map de Header */
		String[] arrHeader = header.split(Contact.SEPARADOR);
		for(int i = 0; i<arrHeader.length; i++)
		{
			String value = normalize(arrHeader[i]);
			this.mapHeader.put(Integer.valueOf(i), value);
		}
	}
	
	private String normalize(String str)
	{
		String value = str.trim();
		value = value.replaceAll("\"", "#");
		char ch = value.charAt(0);
		if( !(ch == '#' || StringUtils.isAlphanumericSpace(ch+"")) )
		{
			value = value.substring(1);
		}
		value = value.replaceAll("#", "\"");
		return value;
	}
	
	/**
	 * @param key
	 * @param value
	 */
	protected void addData(String key, String value)
	{	
		this.mapData.put(key, value.trim());
	}

	/**
	 * Prepara a linha tratando as virgulas
	 * @param line
	 * @return
	 */
	protected String prepare(String line) 
	{
		/* Camila Oliveira Graciano,Camila,Oliveira,Graciano,,,,,,,,,,,,,,,,,,,,,,,* My Contacts,Other,camilagraciano@r7.com ::: sapeckita16@yahoo.com.br,* Other,cadcamila@hotmail.com,Claro (Celular),(011)9375-8709,NEXTEL - SPO (Celular),(011)7716-6104,Home,"R. Nivaldo Romero, 261 - FátimaGuarulhos - São Paulo, 07191-050","R. Nivaldo Romero, 261",Guarulhos,, - São Paulo,07191-050,, - Fátima,Sister,Irmã */
		/* "R. Nivaldo Romero, 261 - FátimaGuarulhos - São Paulo, 07191-050" */
		String newLine = "";
		boolean flag = false;
		char separator = getSeparator();
		for(char ch : line.toCharArray())
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
			else if(ch == separator && flag == false)
			{
				/* Fora de aspas remove as virgulas (separador) e transforma em outro separador */
				append = SEPARADOR;
			}
			newLine += append;
		}
		newLine = newLine.trim();
		newLine = newLine.replaceAll("\n", "");
		newLine = newLine.replaceAll("\r", "");
		return newLine;
	}

	/**
	 * @param key
	 * @return
	 */
	public String getProperty(String key)
	{
		String value = this.mapData.get(key);
		if(value == null)
		{
			value = "";
		}
		
		return value.trim();
	}
	
	/**
	 * @param key
	 * @return
	 */
	public String getSinglePropertyValue(String key)
	{
		String value = this.getProperty(key);
		if(value.contains(":::"))
		{
			String valueArr[] = value.split(":::");
			value = valueArr[0];
		}
		return value.trim();
	}
	
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "mapHeader:["+this.mapHeader+"]\nmapData:["+this.mapData+"]";
	}
	
	/**
	 * Retorna o contato em forma de CSV
	 * @return
	 */
	public String toCSV()
	{
		StringBuilder csv = new StringBuilder();
		int size = this.mapHeader.size();
		for(int i = 0; i < size; i++)
		{
			Integer key = Integer.valueOf(i);
			String head = this.mapHeader.get(key);
			String value = this.mapData.get(head);
			System.out.println("Key:["+key+"] - Head:["+head+"] - value:["+value+"]");
			if(value != null)
			{
				value = value.trim();
				value = treatValue(value);
				csv.append(value);
				if(i < size -1)
				{
					csv.append(this.getSeparator());
				}	
			}
		}
		return csv.toString();
	}
	
	/**
	 * Trata o valor a ser inserido no CSV
	 * @param value
	 * @return
	 */
	abstract String treatValue(String value);


	/**
	 * Separador default do arquivo CSV
	 * @return
	 */
	abstract char getSeparator();
}
