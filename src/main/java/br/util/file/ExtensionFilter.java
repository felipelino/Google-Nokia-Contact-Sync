package br.util.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ExtensionFilter extends javax.swing.filechooser.FileFilter implements FilenameFilter 
{
	/** Extensões aceitas */
	private List<String> extensions = null;
	
	/**
	 * Construtor
	 * @param extension
	 */
	public ExtensionFilter()
	{
		this.extensions = new ArrayList<String>();	
	}
	
	/**
	 * Construtor
	 * @param extensions
	 */
	public ExtensionFilter(List<String> extensions)
	{
		this.extensions = new ArrayList<String>();
		for(String extension : extensions)
		{
			String test = StringUtils.trimToNull(extension);
			if(test != null)
			{
				test = StringUtils.lowerCase(test);
				this.extensions.add(test);
			}
		}	
	}

	/**
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File file, String fileName) 
	{
		Boolean isAccept = Boolean.FALSE;
		if(file.isDirectory())
		{
			isAccept = Boolean.TRUE;
		}
		else if(this.extensions == null || this.extensions.size() == 0)
		{
			isAccept = Boolean.TRUE;
		}
		else
		{
			if(fileName != null && fileName.length() > 0)
			{
				int index = fileName.lastIndexOf(".");
				String ext = fileName.substring(index+1);
				ext = StringUtils.lowerCase(ext);
				if( this.extensions.contains(ext) )
				{
					isAccept = Boolean.TRUE;
				}
			}
		}	
		return isAccept;
	}

	/**
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) 
	{
		String fileName = file.getName();
		return this.accept(file, fileName);
	}

	/**
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() 
	{
		String description = "all";
		if(this.extensions != null && this.extensions.size() > 0)
		{
			description = this.extensions.toString();
			description = description.substring(1, description.length()-1);
		}
		return description;
	}
}
