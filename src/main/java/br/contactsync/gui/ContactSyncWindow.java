package br.contactsync.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import br.contactsync.gui.listener.ButtonPathActionListener;
import br.contactsync.gui.listener.SubmitActionListener;
import br.contactsync.gui.listener.menu.HelpContentActionListener;
import br.util.WindowUtil;
import br.util.file.ExtensionFilter;
import br.util.file.FileUtil;
import br.util.language.gui.listener.AbstractChangeLanguageWindow;
import br.util.language.gui.listener.ChangeLanguageWindow;

/**
 * Janela do Programa Principal
 * @author Felipe Lino
 */
public class ContactSyncWindow extends AbstractChangeLanguageWindow 
{
	/** Default Serial Version UID */
	private static final long serialVersionUID = 1L;

	/* Menu */
	/** Barra de de Menu */
	private JMenuBar menuBar;
	
	/** Menu de Opções */
	private JMenu menuConfig;
	
	/** Opção para converter de Nokia para Google */
	private JRadioButtonMenuItem mCfgNokiaGoogle;
	
	/** Opção para converter de Google para Nokia */
	private JRadioButtonMenuItem mCfgGoogleNokia;
	
	/** Agrupamento de 'Radio' de opções  */
	private ButtonGroup grpCfg;
	
	/** Opção de Idioma */
	private JMenu mCfgLanguage;
	
	/** Menu de Ajuda */
	private JMenu menuHelp;
	
	/** Menu item de Sobre */
	private JMenuItem mHelpAbout;
	
	/* Botões, Campos, etc */
	/** Label para o arquivo de Entrada */
	private JLabel lbInputFile;
	
	/** Field para o arquivo de entrada */
	private JTextField fieldInputFile;
	
	/** Botão para o arquivo de entrada */
	private JButton btInputFile;
	
	/** Label para o arquivo de Saída */
	private JLabel lbOutputFile;
	
	/** Field para o arquivo de saída */
	private JTextField fieldOutputFile;
	
	/** Botão para o arquivo de saída */
	private JButton btOutputFile;
	
	/** Botão para executar a ação */
	private JButton btExecute;
	
	/**
	 * @param configFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ContactSyncWindow() throws FileNotFoundException, IOException 
	{
		super("contactsync.properties");
		
		setSize(530,190);
		setResizable(false);
		
		this.setTitle("Google - Nokia : Contact Sync");
		
		String fileName = FileUtil.findFile("img/update.png").getAbsolutePath();
		Image icon = Toolkit.getDefaultToolkit().getImage(fileName);
		this.setIconImage(icon);
		
		buildMenu();
		buildGUI();
		refreshLabels();
	}
	
	/**
	 * 
	 */
	private void buildGUI() 
	{
		GridBagLayout gridLayout = new GridBagLayout();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 10);
		
		JPanel gridPanel = new JPanel(gridLayout);
		
		this.lbInputFile = super.createLabel("main.window.lb.input", "Input File (CSV):");
		c.gridx = 0;
		c.gridy = 0;
		gridPanel.add(this.lbInputFile, c);
		
		this.fieldInputFile = new JTextField(25);
		c.gridx = 1;
		c.gridy = 0;
		gridPanel.add(this.fieldInputFile, c);
		
		List<String> extensions = new ArrayList<String>(2);
		extensions.add("csv");
		extensions.add("txt");
		ExtensionFilter filter = new ExtensionFilter(extensions);
		
		this.btInputFile = super.createButton("main.window.bt.input", "Open");
		this.btInputFile.addActionListener(new ButtonPathActionListener(this, this.fieldInputFile, true, JFileChooser.FILES_ONLY, filter));
		Icon btPathIcon = WindowUtil.createImageIcon("img/folder.png",null);
		c.gridx = 2;
		c.gridy = 0;
		gridPanel.add(this.btInputFile, c);
		
		this.lbOutputFile = super.createLabel("main.window.lb.output", "Output file (CSV):");
		c.gridx = 0;
		c.gridy = 1;
		gridPanel.add(this.lbOutputFile, c);
		
		this.fieldOutputFile = new JTextField(25);
		c.gridx = 1;
		c.gridy = 1;
		gridPanel.add(this.fieldOutputFile, c);
		
		this.btOutputFile = super.createButton("main.window.bt.output", "Save");
		this.btOutputFile.addActionListener(new ButtonPathActionListener(this, this.fieldOutputFile, true, JFileChooser.FILES_ONLY, filter));
		c.gridx = 2;
		c.gridy = 1;
		gridPanel.add(this.btOutputFile, c);
		
		if(btPathIcon != null)
		{
			this.btInputFile.setIcon(btPathIcon);
			this.btInputFile.setText("");
			
			this.btOutputFile.setIcon(btPathIcon);
			this.btOutputFile.setText("");
		}
		
		this.btExecute = super.createButton("main.window.bt.submit", "OK");
		this.btExecute.addActionListener(new SubmitActionListener(this));
		Icon btSubmitIcon= WindowUtil.createImageIcon("img/submit.gif", null);
		if(btSubmitIcon != null)
		{
			this.btExecute.setIcon(btSubmitIcon);
			this.btExecute.setText(null);
		}
		
		FlowLayout flowLayout = new FlowLayout();
		JPanel panel = new JPanel(flowLayout);
		panel.add(gridPanel);
		panel.add(this.btExecute);
		this.getContentPane().add(panel);
	}

	/**
	 * Constrói os Menus
	 */
	private void buildMenu()
	{
		/* Inclusao dos Menus */
		this.mCfgLanguage = super.createJMenu("main.window.menu.language", "Language"); 
		String propertyOpts = properties.getProperty(ChangeLanguageWindow.LANGUAGE_OPTIONS);
		String[] languageOptions = StringUtils.split(propertyOpts, ';');
		
		for(String language : languageOptions)
		{
			JMenuItem m2Opt = new JMenuItem(language);
			m2Opt.addActionListener(languageListener);
			m2Opt.setName(language);
			this.mCfgLanguage.add(m2Opt);
		}
		
		this.grpCfg = new ButtonGroup();
		this.mCfgGoogleNokia = super.createJRadioButtonMenuItem("main.window.menu.option.convert.nokia", "Convert from Google to Nokia");
		this.mCfgGoogleNokia.setSelected(Boolean.TRUE);
		this.mCfgNokiaGoogle = super.createJRadioButtonMenuItem("main.window.menu.option.convert.google", "Convert from Nokia to Google");
		this.mCfgNokiaGoogle.setSelected(Boolean.FALSE);
		this.grpCfg.add(mCfgGoogleNokia);
		this.grpCfg.add(mCfgNokiaGoogle);
		
		this.menuConfig = super.createJMenu("main.window.menu.option", "Option");
		this.menuConfig.add(this.mCfgGoogleNokia);
		this.menuConfig.add(this.mCfgNokiaGoogle);
		this.menuConfig.addSeparator();
		this.menuConfig.add(this.mCfgLanguage);

		this.mHelpAbout 	= super.createJMenuItem("main.window.menu.help.about", 	"About");
		this.mHelpAbout.addActionListener(new HelpContentActionListener(this));
		this.menuHelp	 	= super.createJMenu("main.window.menu.help", "Help");
		this.menuHelp.add(this.mHelpAbout);
		
		this.menuBar = new JMenuBar();
		this.menuBar.add(menuConfig);
		this.menuBar.add(menuHelp);
		this.setJMenuBar(menuBar);
	}

	/**
	 * @return
	 */
	public JTextField getFieldInputFile() {
		return fieldInputFile;
	}

	/**
	 * @return
	 */
	public JTextField getFieldOutputFile() {
		return fieldOutputFile;
	}

	/**
	 * @return
	 */
	public JRadioButtonMenuItem getmCfgNokiaGoogle() {
		return mCfgNokiaGoogle;
	}

	/**
	 * @return
	 */
	public JRadioButtonMenuItem getmCfgGoogleNokia() {
		return mCfgGoogleNokia;
	}
	
	
	
}
