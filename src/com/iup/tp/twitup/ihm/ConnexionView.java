package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConnexionView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnexionView()
	{
		this.showGUI();
	}


	public void showGUI() {

		System.out.println("Je charge cette vue");

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weightx = 0.0;
		c.gridwidth = GridBagConstraints.REMAINDER;

		JLabel label_id = new JLabel("Identifiant");
		gridbag.setConstraints(label_id, c);
		add(label_id);
		
		JTextField text_pseudo = new JTextField("pseudo");
		gridbag.setConstraints(text_pseudo, c);
		add(text_pseudo);
		
		JLabel label_mdp = new JLabel("Mot de passe");
		gridbag.setConstraints(label_mdp, c);
		add(label_mdp);
		
		JPasswordField text_mdp = new JPasswordField("Mot de passe");
		gridbag.setConstraints(text_mdp, c);
		add(text_mdp);

		JButton button = new JButton("Connexion");
		gridbag.setConstraints(button, c);
		add(button);
		button.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Connexion de \n pseudo = "+ text_pseudo.getText() +" mdp ="+ text_mdp.getText());
			}
		});
	}


}
