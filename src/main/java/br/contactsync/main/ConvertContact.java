package br.contactsync.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ConvertContact 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception 
	{
		BufferedReader buff= new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Select one of the options:\n" +
				"\t[0] - Convert Google to Nokia\n" +
				"\t[1] - Convert Nokia To Google\n\n" +
				"\tOption: ");
		String line = buff.readLine();
		line = line.trim();
		if("0".equalsIgnoreCase(line))
		{
			System.out.print("\nPath to input file Google CSV:");
			line = buff.readLine().trim();
			File inputFile = new File(line);
			
			System.out.print("\nPath to output file Nokia CSV:");
			line = buff.readLine().trim();
			File outputFile = new File(line);
			
			ConvertToNokia.convert(inputFile, outputFile);
			
		}
		else if("1".equalsIgnoreCase(line))
		{
			System.out.print("\nPath to input file Nokia CSV:");
			line = buff.readLine().trim();
			File inputFile = new File(line);
			
			System.out.print("\nPath to output file Google CSV:");
			line = buff.readLine().trim();
			File outputFile = new File(line);
			
			ConvertToGoogle.convert(inputFile, outputFile);
		}
		else
		{
			System.err.println("Invalid option selected:["+line+"]. Execute the program again.");
			System.exit(1);
		}
	}
}
