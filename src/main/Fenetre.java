package main;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.FabricantFigures;
import controleur.ManipulateurFormes;
import controleur.PanneauChoix;
import controleur.TraceTrait;
import modele.DessinModele;
import vue.VueDessin;

// Classe representant la fenetre
public class Fenetre {

	// Attribut correspondant au modele de dessin pour creer des figures
	private DessinModele model;
	// Attribut correspondant au panel de dessin de la fenetre
	private VueDessin dessin;

	/**
	 * Methode lancee au demarrage du programme
	 * 
	 * @param args
	 *            Argumments pour le programme
	 */
	public static void main(String[] args) {
		new Fenetre("Figures Géométriques", 500, 500);
	}

	/**
	 * Constructeur d'une nouvelle fenetre a partir de son titre, sa largeur et sa hauteur
	 * 
	 * @param titre
	 * @param largeur
	 * @param hauteur
	 */
	public Fenetre(String titre, int largeur, int hauteur) {
		if(titre == null)
			titre = "";
		JFrame frame = new JFrame(titre);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(largeur, hauteur));
		panel.setLayout(new BorderLayout());
		this.model = new DessinModele();
		panel.add(new PanneauChoix(this.model), BorderLayout.NORTH);
		this.dessin = new VueDessin();
		this.dessin.addMouseListener(new FabricantFigures(this.model));
		TraceTrait tt = new TraceTrait(this.model);
		this.dessin.addMouseListener(tt);
		this.dessin.addMouseMotionListener(tt);
		ManipulateurFormes mf = new ManipulateurFormes(this.model);
		this.dessin.addMouseListener(mf);
		this.dessin.addMouseMotionListener(mf);
		this.model.addObserver(this.dessin);
		panel.add(dessin, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
