import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(largeur, hauteur));
		panel.setLayout(new BorderLayout());
		this.model = new DessinModele();
		panel.add(new PanneauChoix(this.model), BorderLayout.NORTH);
		this.dessin = new VueDessin();
		this.dessin.addMouseListener(new FabricantFigures(this.model));	
		this.model.addObserver(this.dessin);
		panel.add(dessin, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
