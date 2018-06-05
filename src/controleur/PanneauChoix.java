package controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.Carre;
import modele.Cercle;
import modele.DessinModele;
import modele.Ellipse;
import modele.Etoile;
import modele.FigureColoree;
import modele.Losange;
import modele.Quadrilatere;
import modele.Rectangle;
import modele.Triangle;
import vue.VueDessin;

// Classe representant le panel de boutons permettant a l'utilisateur de choisir le type de dessin a effectuer
public class PanneauChoix extends JPanel {

	// Le modele de dessin sur lequel s'appuyer pour creer une nouvelle figure a ajouter au dessin
	private DessinModele model;
	// La vue du dessin dont on devra ajouter les listeners ou bien les supprimer
	private VueDessin vue;

	/**
	 * Constructeur du panneau de choix avec les boutons et leurs evenements
	 * 
	 * @param model
	 *            Le modele de dessin pour construire les figures
	 */
	public PanneauChoix(DessinModele model, VueDessin vue) {
		this.model = model;
		this.vue = vue;
		// On dessine le panneau choix sous forme d'une grille de 2 par 1 (ligne x colonne)
		this.setLayout(new GridLayout(2, 1));
		JPanel panelcombobox = new JPanel();
		// On ajoute le choix des couleurs
		final JButton choixcouleur = new JButton("Choix couleur");
		choixcouleur.setFocusable(false);
		choixcouleur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(PanneauChoix.this.getParent(), "Choix de la couleur des figures", Color.BLACK);
				// Si l'utilisateur a choisi une couleur
				if(c != null) {
					if(PanneauChoix.this.model.getFigureEnCours() != null) {
						PanneauChoix.this.model.getFigureEnCours().changeCouleur(c);
					}
					// Si on est en mode de manipulation on change aussi la couleur de la figure selectionnee si il y en a une
					if(PanneauChoix.this.model.getType() == 2) {
						PanneauChoix.this.model.changerCouleur(PanneauChoix.this.model.getFigureSelectionnee(), c);
					}
				}
			}
		});
		// On ajoute le choix des formes des figures
		final JComboBox<String> choixforme = new JComboBox<String>(new String[] { "Quadrilatère", "Triangle", "Rectangle", "Cercle", "Carré", "Losange", "Ellipse", "Etoile" });
		choixforme.setFocusable(false);
		choixforme.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// On construit la figure et on lui applique la couleur de la figure precedemment en construction
				FigureColoree fc = PanneauChoix.this.creeFigure(choixforme.getSelectedIndex());
				FigureColoree ancienne = PanneauChoix.this.model.getFigureEnCours();
				if(ancienne != null) {
					fc.changeCouleur(ancienne.getCouleur());
				}
				PanneauChoix.this.model.construit(fc);
			}
		});
		// On ajoute le choix de l'epaisseur du contour de la figure
		final JSlider choixepaisseur = new JSlider(1, 6, 2);
		choixepaisseur.setMajorTickSpacing(1);
		choixepaisseur.setPaintTicks(true);
		choixepaisseur.setPaintLabels(true);
		choixepaisseur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(PanneauChoix.this.model.getFigureEnCours() != null) {
					PanneauChoix.this.model.getFigureEnCours().changerEpaisseur(choixepaisseur.getValue());
				}
				if(PanneauChoix.this.model.getType() == 2) {
					PanneauChoix.this.model.changerEpaisseur(PanneauChoix.this.model.getFigureSelectionnee(), choixepaisseur.getValue());
				}
			}
		});
		// On ajoute le choix de figure pleine ou non
		final JCheckBox choixpleine = new JCheckBox("Figure pleine");
		choixpleine.setFocusable(false);
		choixpleine.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(PanneauChoix.this.model.getFigureEnCours() != null) {
					PanneauChoix.this.model.getFigureEnCours().mettrePleine(choixpleine.isSelected());
				}
				if(PanneauChoix.this.model.getType() == 2) {
					PanneauChoix.this.model.mettrePleine(PanneauChoix.this.model.getFigureSelectionnee(), choixpleine.isSelected());
				}
				choixepaisseur.setEnabled(!choixpleine.isSelected());
			}
		});
		// On ajoute les choix
		panelcombobox.add(choixpleine);
		panelcombobox.add(choixforme);
		panelcombobox.add(choixcouleur);
		panelcombobox.add(choixepaisseur);
		// On creer les boutons du choix du mode et on leur ajoute le listener associe
		JPanel panelboutons = new JPanel();
		String[] names = { "Nouvelle figure", "Tracé à main levée", "Manipulations" };
		final ButtonGroup group = new ButtonGroup();
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JRadioButton) {
					// On recupere le radio button et on le selectionne tandit qu'on deselectionne les autres
					JRadioButton radiobutton = (JRadioButton) e.getSource();
					group.clearSelection();
					radiobutton.setSelected(true);
					// On supprime tous les listeners de la vue dessin
					for(MouseListener l : PanneauChoix.this.vue.getMouseListeners()) {
						PanneauChoix.this.vue.removeMouseListener(l);
					}
					for(MouseMotionListener l : PanneauChoix.this.vue.getMouseMotionListeners()) {
						PanneauChoix.this.vue.removeMouseMotionListener(l);
					}
					// On deselectionne la figure selectionnee dans le modele
					FigureColoree selectionnee = PanneauChoix.this.model.getFigureSelectionnee();
					if(selectionnee != null) {
						selectionnee.deSelectionne();
						PanneauChoix.this.model.setFigureSelectionnee(null);
					}
					// Si on appui sur le bouton pour la construction d'une nouvelle figure
					if(radiobutton.getText().equals("Nouvelle figure")) {
						choixforme.setEnabled(true);
						choixcouleur.setEnabled(true);
						choixpleine.setEnabled(true);
						choixepaisseur.setEnabled(!choixpleine.isSelected());
						PanneauChoix.this.model.changerType(0);
						PanneauChoix.this.vue.addMouseListener(new FabricantFigures(PanneauChoix.this.model));
						// Si on appui sur le bouton pour tracer des traits
					} else if(radiobutton.getText().equals("Tracé à main levée")) {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
						choixpleine.setEnabled(false);
						choixepaisseur.setEnabled(true);
						PanneauChoix.this.model.changerType(1);
						TraceTrait l = new TraceTrait(PanneauChoix.this.model);
						PanneauChoix.this.vue.addMouseListener(l);
						PanneauChoix.this.vue.addMouseMotionListener(l);
						// Sinon alors on passe en mode manipulation
					} else if(radiobutton.getText().equals("Manipulations")) {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
						choixpleine.setEnabled(true);
						choixepaisseur.setEnabled(!choixpleine.isSelected());
						PanneauChoix.this.model.changerType(2);
						ManipulateurFormes l = new ManipulateurFormes(PanneauChoix.this.model);
						PanneauChoix.this.vue.addMouseListener(l);
						PanneauChoix.this.vue.addMouseMotionListener(l);
					}
					PanneauChoix.this.model.setNbClic(0);
				}
			}
		};
		for(int i = 0; i < names.length; i++) {
			JRadioButton radiobutton = new JRadioButton(names[i]);
			radiobutton.setFocusable(false);
			radiobutton.addActionListener(listener);
			group.add(radiobutton);
			panelboutons.add(radiobutton);
			if(i == 0) {
				radiobutton.setSelected(true);
			}
		}
		// On ajoute le panel boutons et le panel du choix de mode au panneau choix
		this.add(panelboutons);
		this.add(panelcombobox);
		// Et on construit une quadrilatere de base
		this.model.construit(new Quadrilatere());
	}

	/**
	 * Methode permettant de retourner une nouvelle instance d'une figure coloree en fonction de d'un index
	 * 
	 * @param index
	 *            L'indice du type de figure coloree a instancier et retourner
	 * @return Une nouvelle instance de figure coloree
	 */
	private FigureColoree creeFigure(int index) {
		// On instancie une nouvelle figure en fonction de l'indice selectionne
		switch(index) {
		case 0:
			return new Quadrilatere();
		case 1:
			return new Triangle();
		case 2:
			return new Rectangle();
		case 3:
			return new Cercle();
		case 4:
			return new Carre();
		case 5:
			return new Losange();
		case 6:
			return new Ellipse();
		case 7:
			return new Etoile();
		default:
			return null;
		}
	}
}
