import java.awt.Dimension;

import javax.swing.JFrame;

import oracle.jrockit.jfr.JFR;

public class Fenetre {
	
	private DessinModele model;
	private VueDessin dessin;
	
	public static void main(String[] args) {
		new Fenetre("Figures Géométriques", 500, 500);
	}
	
	public Fenetre(String titre, int largeur, int hauteur) {
		if(titre == null)
			titre = "";
		JFrame frame = new JFrame(titre);
		frame.setPreferredSize(new Dimension(largeur, hauteur));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dessin = new VueDessin();
		frame.add(dessin);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
