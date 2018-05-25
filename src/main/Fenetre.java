package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import modele.FigureColoree;
import modele.Trait;
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
		JMenuBar bar = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		bar.add(fichier);
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		ouvrir.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser();
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
				int retour = choix.showOpenDialog(frame);
				if(retour == JFileChooser.APPROVE_OPTION) {
					try {
						Fenetre.this.charger(choix.getSelectedFile().getAbsolutePath());
					} catch(IOException | ClassNotFoundException ex) { }
				}
			}
		});
		fichier.add(ouvrir);
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		sauvegarder.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser();
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
				int retour = choix.showSaveDialog(frame);
				if(retour == JFileChooser.APPROVE_OPTION) {
					try {
						Fenetre.this.sauvegarder(choix.getSelectedFile().getAbsolutePath() + ".fg");
					} catch(IOException ex) { }
				}
			}
		});
		fichier.add(sauvegarder);
		JMenuItem exporter = new JMenuItem("Exporter");
		exporter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser();
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
				int retour = choix.showSaveDialog(frame);
				if(retour == JFileChooser.APPROVE_OPTION) {
					Fenetre.this.exporter(choix.getSelectedFile().getAbsolutePath() + ".png");
				}
			}
		});
		fichier.add(exporter);
		frame.setJMenuBar(bar);
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
		JScrollPane scrollpane = new JScrollPane(this.dessin, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollpane, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void sauvegarder(String urlfichier) throws FileNotFoundException, IOException {
		if(urlfichier != null) {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(urlfichier));
			Object[] objets = new Object[2];
			objets[0] = this.model.getLfg();
			objets[1] = this.model.getTraits();
			out.writeObject(objets);
			out.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void charger(String urlfichier) throws FileNotFoundException, IOException, ClassNotFoundException {
		if(urlfichier != null && this.model != null) {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(urlfichier));
			Object[] objets = (Object[]) in.readObject();
			if(objets.length == 2) {
				this.model.setLfg((ArrayList<FigureColoree>) objets[0]);
				this.model.setNbClic(0);
				this.model.setTraits((ArrayList<Trait>) objets[1]);
			}
			in.close();
		}
	}
	
	public void exporter(String urlfichier) {
		if(urlfichier != null) {
			BufferedImage image = new BufferedImage(this.dessin.getWidth(), this.dessin.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			this.dessin.paint(g);
			g.dispose();
			try { 
			    ImageIO.write(image, "png", new File(urlfichier)); 
			} catch (IOException e) { }
		}
	}
}
