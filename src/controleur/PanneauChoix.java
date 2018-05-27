package controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import modele.Carre;
import modele.Cercle;
import modele.DessinModele;
import modele.Ellipse;
import modele.FigureColoree;
import modele.Losange;
import modele.Quadrilatere;
import modele.Rectangle;
import modele.Triangle;

// Classe representant le panel de boutons permettant a l'utilisateur de choisir le type de dessin a effectuer
public class PanneauChoix extends JPanel {

	// Le modele de dessin sur lequel s'appuyer pour creer une nouvelle figure a ajouter au dessin
	private DessinModele dessin;

	/**
	 * Constructeur du panneau de choix avec les boutons et leurs evenements
	 * 
	 * @param dessin
	 *            Le modele de dessin pour construire les figures
	 */
	public PanneauChoix(DessinModele dessin) {
		this.dessin = dessin;
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
					if(PanneauChoix.this.dessin.getFigureEnCours() != null) {
						PanneauChoix.this.dessin.getFigureEnCours().changeCouleur(c);
					}
					// Si on est en mode de manipulation on change aussi la couleur de la figure selectionnee si il y en a une
					if(PanneauChoix.this.dessin.getType() == 2) {
						PanneauChoix.this.dessin.changerCouleur(PanneauChoix.this.dessin.getFigureSelectionnee(), c);
					}
				}
			}
		});
		// On a ajoute le choix des formesdes figures
		final JComboBox<String> choixforme = new JComboBox<String>(new String[] { "Quadrilatère", "Triangle", "Rectangle", "Cercle", "Carré", "Losange", "Ellipse" });
		choixforme.setFocusable(false);
		choixforme.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// On construit la figure et on lui applique la couleur de la figure precedemment en construction
				FigureColoree fc = PanneauChoix.this.creeFigure(choixforme.getSelectedIndex());
				FigureColoree ancienne = PanneauChoix.this.dessin.getFigureEnCours();
				if(ancienne != null) {
					fc.changeCouleur(ancienne.getCouleur());
				}
				PanneauChoix.this.dessin.construit(fc);
			}
		});
		// On ajoute les deux combobox
		panelcombobox.add(choixforme);
		panelcombobox.add(choixcouleur);
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
					// On deselectionne la figure selectionnee dans le modele
					FigureColoree selectionnee = PanneauChoix.this.dessin.getFigureSelectionnee();
					if(selectionnee != null) {
						selectionnee.deSelectionne();
						PanneauChoix.this.dessin.setFigureSelectionnee(null);
					}
					// Si on appui sur le bouton pour la construction d'une nouvelle figure
					if(radiobutton.getText().equals("Nouvelle figure")) {
						choixforme.setEnabled(true);
						choixcouleur.setEnabled(true);
						PanneauChoix.this.dessin.changerType(0);
						// Si on appui sur le bouton pour tracer des traits
					} else if(radiobutton.getText().equals("Tracé à main levée")) {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
						PanneauChoix.this.dessin.changerType(1);
						// Sinon alors on passe en mode manipulation
					} else {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
						PanneauChoix.this.dessin.changerType(2);
					}
					PanneauChoix.this.dessin.setNbClic(0);
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
		this.dessin.construit(new Quadrilatere());
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
		default:
			return null;
		}
	}
}
