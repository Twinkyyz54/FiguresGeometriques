import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
		this.setLayout(new GridLayout(2, 1));
		JPanel panelcombobox = new JPanel();
		final JComboBox<String> choixcouleur = new JComboBox<String>(
				new String[] { "Noir", "Bleu", "Cyan", "Gris foncé", "Gris", "Vert", "Gris clair", "Violet", "Orange", "Rose", "Rouge", "Blanc", "Jaune" });
		choixcouleur.setFocusable(false);
		choixcouleur.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(PanneauChoix.this.dessin.getFigureEnCours() != null) {
					PanneauChoix.this.dessin.getFigureEnCours().changeCouleur(PanneauChoix.this.determineCouleur(choixcouleur.getSelectedIndex()));
				}
				if(PanneauChoix.this.dessin.getType() == 2) {
					PanneauChoix.this.dessin.changerCouleur(PanneauChoix.this.dessin.getFigureSelectionnee(), PanneauChoix.this.determineCouleur(choixcouleur.getSelectedIndex()));
				}
			}
		});
		final JComboBox<String> choixforme = new JComboBox<String>(new String[] { "Quadrilatère", "Triangle", "Rectangle", "Cercle", "Carré", "Losange", "Ellipse" });
		choixforme.setFocusable(false);
		choixforme.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				FigureColoree fc = PanneauChoix.this.creeFigure(choixforme.getSelectedIndex());
				fc.changeCouleur(PanneauChoix.this.determineCouleur(choixcouleur.getSelectedIndex()));
				PanneauChoix.this.dessin.construit(fc);
			}
		});
		panelcombobox.add(choixforme);
		panelcombobox.add(choixcouleur);
		JPanel panelboutons = new JPanel();
		String[] names = { "Nouvelle figure", "Tracé à main levée", "Manipulations" };
		final ButtonGroup group = new ButtonGroup();
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JRadioButton) {
					JRadioButton radiobutton = (JRadioButton) e.getSource();
					group.clearSelection();
					radiobutton.setSelected(true);
					for(FigureColoree fc : PanneauChoix.this.dessin.getLfg()) {
						fc.deSelectionne();
					}
					if(radiobutton.getText().equals("Nouvelle figure")) {
						choixforme.setEnabled(true);
						choixcouleur.setEnabled(true);
						PanneauChoix.this.dessin.changerType(0);
					} else if(radiobutton.getText().equals("Tracé à main levée")) {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
						PanneauChoix.this.dessin.changerType(1);
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
		this.add(panelboutons);
		this.add(panelcombobox);
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

	/**
	 * Methode permettant de retourner une instance de couleur en fonction d'un indice
	 * 
	 * @param index
	 *            L'indice de la couleur a instancier et retourner
	 * @return L'instance de la couleur
	 */
	private Color determineCouleur(int index) {
		switch(index) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.CYAN;
		case 3:
			return Color.DARK_GRAY;
		case 4:
			return Color.GRAY;
		case 5:
			return Color.GREEN;
		case 6:
			return Color.LIGHT_GRAY;
		case 7:
			return Color.MAGENTA;
		case 8:
			return Color.ORANGE;
		case 9:
			return Color.PINK;
		case 10:
			return Color.RED;
		case 11:
			return Color.WHITE;
		case 12:
			return Color.YELLOW;
		default:
			return null;
		}
	}
}
