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
 * Converte os contatos do Nokia para o Google
 * @author Felipe Lino
 */
public class ConvertToGoogle
{
	/** Logger da classe */
	private static final Logger log = Logger.getLogger(ConvertToGoogle.class);
	
	public static void main(String args[]) throws Exception
	{
		if(args.length == 2 )
		{
			String fileName = args[0];
			String outFileName = args[1];
			
			File inputFile = new File(fileName);
			File outputFile = new File(outFileName);
			
			convert(inputFile, outputFile);
		}
		else
		{
			System.out.println("Usage:\n\t java ConvertToGoogle /path/to/input/file_nokia.csv /path/to/output/file_google.csv");
		}
	}
	
	/**
	 * Converte os contatos nokia do arquivo de entrada em google para o arquivo de saída.
	 * @param inputFile
	 * @param outputFile
	 * @throws Exception
	 */
	public static int convert(File inputFile, File outputFile) throws Exception
	{
		log.info("Reading Nokia's Contact from:["+inputFile.getAbsolutePath()+"]");
		
		List<NokiaContact> nokiaList = NokiaContactFactory.create(inputFile);
		log.info("Nokia's contacts was read successfully.");
		
		log.info("Converting contacts from nokia to google.");
		
		List<GoogleContact> googleList = GoogleContactFactory.create(nokiaList);
		log.info("Contacts converted successfully.");
		
		log.info("Writing Google's contacts to:["+outputFile.getAbsolutePath()+"]");
		
		outputFile.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(outputFile));
		out.print(GoogleContact.DEFAULT_HEADER);
		int i = 0;
		for(GoogleContact googleContact : googleList )
		{
			i++;
			log.info("Writing contact: "+i+"/"+googleList.size());
			out.print("\n");
			String csv = googleContact.toCSV();
			out.print(csv);
		}
		out.close();
		log.info("Contacts written successfully.");
		return googleList.size();
	}
}
