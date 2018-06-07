package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

import controleur.FabricantFigures;
import controleur.ManipulateurFormes;
import controleur.PanneauChoix;
import controleur.TraceTrait;
import modele.DessinModele;
import modele.Dessinable;
import modele.FigureColoree;
import modele.Trait;
import vue.VueDessin;

// Classe representant la fenetre
public class Fenetre {

	// Attribut correspondant au modele de dessin pour creer des figures
	private DessinModele model;
	// Attribut correspondant au panel de dessin de la fenetre
	private VueDessin vue;

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
		// On creer la fenetre
		JFrame frame = new JFrame(titre);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On cree la barre de menu pour sauvegarder, charger et exporter
		JMenuBar bar = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		bar.add(fichier);
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		ouvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si on souhait ouvrir un fichier
				JFileChooser choix = new JFileChooser();
				// On rajoute le filtre pour seulement afficher les dossiers et les fichiers de sauvegarde .fg
				choix.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Fichiers dessin (.fg)";
					}

					@Override
					public boolean accept(File f) {
						return f != null && (f.isDirectory() || f.getPath().endsWith(".fg"));
					}
				});
				// On ouvre la fenetre de choix de fichier
				int retour = choix.showOpenDialog(frame);
				// Si l'utilisateur selectionne un fichier et valide
				if(retour == JFileChooser.APPROVE_OPTION) {
					try {
						// On tente de charger le fichier choisi
						Fenetre.this.charger(choix.getSelectedFile().getAbsolutePath());
					} catch(IOException | ClassNotFoundException ex) {
					}
				}
			}
		});
		// On ajoute a la barre de menu
		fichier.add(ouvrir);
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		sauvegarder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si l'utilisateur souhaite sauvegarder
				JFileChooser choix = new JFileChooser();
				// On rajoute le filtre pour seulement afficher les dossiers et les fichiers de sauvegarde .fg
				choix.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Fichiers dessin (.fg)";
					}

					@Override
					public boolean accept(File f) {
						return f != null && (f.isDirectory() || f.getPath().endsWith(".fg"));
					}
				});
				// On ouvre la fenetre de choix de fichier
				int retour = choix.showSaveDialog(frame);
				// Si l'utilisateur choisi un nom de fichier et valide
				if(retour == JFileChooser.APPROVE_OPTION) {
					try {
						// On tente de sauvegarder dans le nom de fichier choisi
						Fenetre.this.sauvegarder(choix.getSelectedFile().getAbsolutePath() + ".fg");
					} catch(IOException ex) {
					}
				}
			}
		});
		// On ajoute a la barre de menu
		fichier.add(sauvegarder);
		JMenuItem exporter = new JMenuItem("Exporter");
		exporter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si le joueur souhaite exporter en fichier image .png
				JFileChooser choix = new JFileChooser();
				// On rajoute le filtre pour seulement afficher les dossiers et les fichiers de sauvegarde .png
				choix.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Fichiers image (.png)";
					}

					@Override
					public boolean accept(File f) {
						return f != null && (f.isDirectory() || f.getPath().endsWith(".png"));
					}
				});
				// On ouvre la fenetre de choix de fichier
				int retour = choix.showSaveDialog(frame);
				// Si l'utilisateur choisi un nom de fichier et valide
				if(retour == JFileChooser.APPROVE_OPTION) {
					// On tente d'exporter dans le nom de fichier choisi
					try {
						Fenetre.this.exporter(choix.getSelectedFile().getAbsolutePath() + ".png");
					} catch(IOException ex) {
					}
				}
			}
		});
		// On ajoute a la barre de menu
		fichier.add(exporter);
		// On defini la barre de menu de la fenetre comme etant notre barre creee
		frame.setJMenuBar(bar);
		// On creer le jpanel qui va contenir le paneau choix et la vue dessin
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(largeur, hauteur));
		panel.setLayout(new BorderLayout());
		// On instancie un dessin modele pour les controleurs
		this.model = new DessinModele();
		// On ajoute le controleur a la vue dessin pour la fabrication de figures
		this.vue = new VueDessin();
		this.vue.addMouseListener(new FabricantFigures(this.model));
		// On defini la vue dessin comme etant un observer de notre modele de dessin
		this.model.addObserver(this.vue);
		// On defini un scrollpane pour la vue dessin
		JScrollPane scrollpane = new JScrollPane(this.vue, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// On ajoute le scrollpane avec la vue dessin et le panneau choix
		panel.add(new PanneauChoix(this.model, this.vue), BorderLayout.NORTH);
		panel.add(scrollpane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(600, 600));
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Methode permettant de sauvegarder le dessin dans un fichier
	 * 
	 * @param urlfichier
	 *            Le chemin ou doit se trouver le fichier de sauvegarde
	 * @throws IOException
	 *             S il y a une erreur lors de la l'ecriture du fichier de sauvegarde
	 */
	public void sauvegarder(String urlfichier) throws IOException {
		// Si le chemin du fichier n'est pas null
		if(urlfichier != null) {
			// On serialize la liste des figures dans le fichier
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(urlfichier));
			out.writeObject(this.model.getLfg());
			out.close();
		}
	}

	/**
	 * Methode permettant de changer un dessin a partir d'un fichier de sauvegarder
	 * 
	 * @param urlfichier
	 *            Le chemin vers le fichier de sauvegarde du dessin
	 * @throws IOException
	 *             Si il y a une erreur lors de la lecture du fichier de sauvegarde
	 * @throws ClassNotFoundException
	 *             Si l'interieur du fichier de sauvegarde ne correspond pas au bon contenu
	 */
	@SuppressWarnings("unchecked")
	public void charger(String urlfichier) throws IOException, ClassNotFoundException {
		// Si le chemin du fichier n'est pas null et que le modele de dessin non plus
		if(urlfichier != null && this.model != null) {
			// On deserialize en tant que la liste de figures a partir du fichier
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(urlfichier));
			ArrayList<Dessinable> lfg = (ArrayList<Dessinable>) in.readObject();
			this.model.setLfg(lfg);
			this.model.setNbClic(0);
			in.close();
		}
	}

	/**
	 * Methode permettant d'exporter un dessin en tant qu'image au format .png
	 * 
	 * @param urlfichier
	 *            Le chemin ou doit se trouver l'image
	 * @throws IOException
	 *             Si il y a une erreur lors de l'ecriture du fichie image .png
	 */
	public void exporter(String urlfichier) throws IOException {
		// Si le chemin du fichier n'est pas null
		if(urlfichier != null) {
			Dimension size = this.vue.getPreferredSize();
			// On creer un image coloree de la taille de la vue dessin
			BufferedImage image = new BufferedImage((int) Math.round(size.getWidth()), (int) Math.round(size.getHeight()), BufferedImage.TYPE_INT_RGB);
			// On recupere un graphique de l'image
			Graphics g = image.createGraphics();
			// On affiche le dessin de vue dessin sur le graphique
			this.vue.paint(g);
			// On libere les ressources qui ne sont plus utiles du graphique
			g.dispose();
			// On tente de creer le fichier correspond a l'image au format png
			ImageIO.write(image, "png", new File(urlfichier));
		}
	}
}
