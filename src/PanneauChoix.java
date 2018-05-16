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

public class PanneauChoix extends JPanel {
	
	private DessinModele dessin;
	
	public PanneauChoix(DessinModele dessin) {
		this.dessin = dessin;
		this.setLayout(new GridLayout(2, 1));
		JPanel panelcombobox = new JPanel();
		final JComboBox<String> choixcouleur = new JComboBox<String>(new String[] {"Noir", "Bleu", "Cyan", "Gris foncé", "Gris", "Vert", "Gris clair", "Violet", "Orange", "Rose", "Rouge", "Blanc", "Jaune"});
		choixcouleur.setFocusable(false);
		choixcouleur.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				FigureColoree fc = PanneauChoix.this.dessin.getFigureEnCours();
				if(fc != null) {
					fc.changeCouleur(PanneauChoix.this.determineCouleur(choixcouleur.getSelectedIndex()));
				}
			}
		});
		final JComboBox<String> choixforme = new JComboBox<String>(new String[] {"Quadrilatère", "Triangle", "Rectangle", "Cercle"});
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
		String[] names = {"Nouvelle figure", "Tracé à main levée", "Manipulations"};
		final ButtonGroup group = new ButtonGroup();
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JRadioButton) {
					JRadioButton radiobutton = (JRadioButton) e.getSource();
					group.clearSelection();
					radiobutton.setSelected(true);
					if(radiobutton.getText().equals("Nouvelle figure")) {
						choixforme.setEnabled(true);
						choixcouleur.setEnabled(true);
					} else if(radiobutton.getText().equals("Tracé à main levée")) {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
					} else {
						choixforme.setEnabled(false);
						choixcouleur.setEnabled(true);
					}
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
		default:
			return null;
		}
	}
	
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
