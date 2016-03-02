package com.iup.tp.twitup.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserverImpl;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView
{

	/**
	 * Fenetre du bouchon
	 */
	protected JFrame mainFrame;

	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	public IDatabaseObserverImpl observeur;

	/**
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */
	public TwitupMainView(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.observeur = new IDatabaseObserverImpl();
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		// Init auto de l'IHM au cas ou ;)
		if (mainFrame == null) {
			this.initGUI();
		}

		// Affichage dans l'EDT
		/*SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitupMainView.this.mainFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
						.getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				TwitupMainView.this.mainFrame.setVisible(true);

				TwitupMainView.this.mainFrame.pack();
			}
		});*/
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {

		// Frame principale
		mainFrame = new JFrame("twitup");
		mainFrame.setSize(500, 500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// icone 
		ImageIcon iconAppli = new ImageIcon("./src/resources/images/titi_icon.gif");
		mainFrame.setIconImage(iconAppli.getImage());

		JMenuBar mainMenuBar = new JMenuBar(); 

		JMenu fichierMenu = new JMenu("Fichier");
		JMenu utilisateur = new JMenu("Utilisateur");
		JMenu interrogation = new JMenu("?");

		JMenuItem quitter = new JMenuItem("Quitter");
		ImageIcon iconQuitter = new ImageIcon("./src/resources/images/exitIcon_20.png");
		quitter.setIcon(iconQuitter);
		quitter.setToolTipText("Quitter l'application");
		quitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Fermeture de l'application");
				System.exit(0);
			}
		});

		JMenuItem apropos = new JMenuItem("à propos de");
		apropos.setToolTipText("A propos de");
		apropos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane boiteapropos = new JOptionPane();
				JOptionPane.showMessageDialog(null, "UBO M2-TIIL 2016\nDépartement Informatique", "A propos", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./src/resources/images/aboutIcon.png"));
			}
		});

		JMenuItem changerDossier = new JMenuItem("changer dossier");
		changerDossier.setToolTipText("changer de dossier");
		changerDossier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Je veux changer de dossier");
				choisirDossier();
			}
		});
		
		JCheckBoxMenuItem activeLookAndFeel = new JCheckBoxMenuItem("activé look&feel");
		activeLookAndFeel.setToolTipText("Activer ou non le look & feel");
		activeLookAndFeel.setSelected(true);
		activeLookAndFeel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: activer ou désactiver le look & feel
			}
		});
		
		JMenuItem connexion = new JMenuItem("Connexion");
		connexion.setToolTipText("connexion de l'utilisateur");
		connexion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().invalidate();
				ConnexionView cv = new ConnexionView();				
				mainFrame.getContentPane().add(cv);		
				mainFrame.getContentPane().revalidate();
			}
		});
		
		JMenuItem creation = new JMenuItem("Création");

		fichierMenu.add(changerDossier);	
		fichierMenu.add(activeLookAndFeel);
		fichierMenu.add(quitter);

		utilisateur.add(connexion);
		utilisateur.add(creation);
		
		interrogation.add(apropos);		

		mainMenuBar.add(fichierMenu);
		mainMenuBar.add(utilisateur);
		mainMenuBar.add(interrogation);

		mainFrame.setJMenuBar(mainMenuBar);


		// recuperer la proriété
		Properties prop = PropertiesManager.loadProperties("src/resources/configuration.properties");
		prop.getProperty("EXCHANGE_DIRECTORY");

		if (!prop.getProperty("EXCHANGE_DIRECTORY").isEmpty())
		{
			System.out.println("Il y a déjà un dossier");
		}else{			
			choisirDossier();
		}

		mainFrame.setVisible(true);
	}

	private void choisirDossier() {
		JFileChooser choix = new JFileChooser();
		choix.setCurrentDirectory(new File("."));
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // repertoire uniquement

		int retour= choix.showOpenDialog(mainFrame);

		if(retour==JFileChooser.APPROVE_OPTION){
			choix.getSelectedFile().getName();
			choix.getSelectedFile().getAbsolutePath();
			changeProperties(choix.getSelectedFile().getAbsolutePath());
		}else{

		}
	}

	public void changeProperties(String absolutePath){
		Properties prop = PropertiesManager.loadProperties("src/resources/configuration.properties");
		prop.setProperty("EXCHANGE_DIRECTORY",absolutePath);
		PropertiesManager.writeProperties(prop, "src/resources/configuration.properties");
	}

	/**
	 * Ajoute un utilisateur fictif à la base de donnée.
	 */
	protected void addUserInDatabase() {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser();

		// Ajout de l'utilisateur à la base
		this.mDatabase.addUser(newUser);

		// notification de l'ajout
		observeur.notifyUserAdded(newUser);

	}

	/**
	 * Génération et envoi d'un fichier utilisateur
	 */
	protected void sendUser() {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser();

		// Génération du fichier utilisateur
		this.mEntityManager.sendUser(newUser);

		// notification de l'ajout
		observeur.notifyUserSended(newUser);		

	}

	/**
	 * Génération d'un utilisateur fictif.
	 */
	protected User generateUser() {
		int randomInt = new Random().nextInt(99999);
		String userName = "MockUser" + randomInt;
		User newUser = new User(UUID.randomUUID(), userName, "--", userName,
				new HashSet<String>(), "");

		return newUser;
	}

	/**
	 * Ajoute un twit fictif à la base de données.
	 */
	protected void addTwitInDatabase() {
		// Création 'un twit fictif
		Twit newTwit = this.generateTwit();

		// Ajout du twit
		this.mDatabase.addTwit(newTwit);

		// notification ajout twit
		observeur.notifyTwitAdded(newTwit);
	}

	/**
	 * Génération et envoi d'un fichier twit
	 */
	protected void sendTwit() {
		// Création d'un twit fictif
		Twit newTwit = this.generateTwit();

		// Génération du fichier twit
		this.mEntityManager.sendTwit(newTwit);

		// notification envoi twit
		observeur.notifyTwitSended(newTwit);

	}

	/**
	 * Génération d'un twit fictif.
	 */
	protected Twit generateTwit() {
		// Si la base n'a pas d'utilisateur
		if (this.mDatabase.getUsers().size() == 0) {
			// Création d'un utilisateur
			this.addUserInDatabase();
		}

		// Récupération d'un utilisateur au hazard
		int userIndex = new Random().nextInt(this.mDatabase.getUsers().size());
		User randomUser = new ArrayList<User>(this.mDatabase.getUsers())
				.get(Math.max(0, userIndex - 1));

		// Création d'un twit fictif
		Twit newTwit = new Twit(randomUser, "Twit fictif!! #Mock #test ;)");

		return newTwit;
	}

	public void ActivelookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
	}








}