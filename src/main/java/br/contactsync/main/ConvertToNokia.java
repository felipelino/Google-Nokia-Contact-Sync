package br.contactsync.main;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import br.contactsync.domain.GoogleContact;
import br.contactsync.domain.NokiaContact;
import br.contactsync.domain.factory.GoogleContactFactory;
import br.contactsync.domain.factory.NokiaContactFactory;

/**
 * Classe responsável por converter os contato do Google para o Nokia
 * @author Felipe Lino
 */
public class ConvertToNokia
{
	/** Logger da classe */
	private static final Logger log = Logger.getLogger(ConvertToNokia.class);
	
	/**
	 * Programa principal para converter contatos Google para Nokia
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception
	{
		if(args.length == 2)
		{
			String fileName = args[0];
			String outFileName = args[1];
			
			File inputFile = new File(fileName);
			File outputFile = new File(outFileName);
			convert(inputFile, outputFile);
		}
		else
		{
			System.out.println("Usage:\n\t java ConvertToNokia /path/to/input/file_google.csv /path/to/output/file_nokia.csv");
		}
	}
	
	/**
	 * Converte os contatos google do arquivo de entrada em nokia para o arquivo de saída.
	 * @param inputFile
	 * @param outputFile
	 * @throws Exception
	 */
	public static int convert(File inputFile, File outputFile) throws Exception
	{
		log.info("Reading Google's Contact from:["+inputFile.getAbsolutePath()+"]");
		
		List<GoogleContact> googleList = GoogleContactFactory.create(inputFile);
		log.info("Google's contacts was read successfully.");
		
		log.info("Converting contacts from google to nokia.");
		
		List<NokiaContact> nokiaList = NokiaContactFactory.create(googleList);
		log.info("Contacts converted successfully.");
		
		log.info("Writing Nokia's contacts to:["+outputFile.getAbsolutePath()+"]");
		
		outputFile.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(outputFile));
		out.print(NokiaContact.DEFAULT_HEADER);
		int i = 0;
		for(NokiaContact nokiaContact : nokiaList )
		{
			i++;
			log.info("Writing contact: "+i+"/"+nokiaList.size());
			out.print("\n");
			String csv = nokiaContact.toCSV();
			out.print(csv);
		}
		out.close();
		log.info("Contacts written successfully.");
		return nokiaList.size();
	}
}
