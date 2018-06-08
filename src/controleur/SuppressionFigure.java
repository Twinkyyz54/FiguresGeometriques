package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import modele.DessinModele;
import vue.VueDessin;

// Classe controlleur permettant de supprimer des dessins
public class SuppressionFigure implements MouseListener {

	// Modele pour supprimer les dessins
	private DessinModele model;
	// Vue pour afficher le popup de choix de suppression
	private VueDessin vue;

	/**
	 * Constructeur du controlleur de suppression de figures
	 * 
	 * @param model
	 *            Modele pour supprimer les figures
	 * @param vue
	 *            Vue pour afficher le popup de choix de suppression
	 */
	public SuppressionFigure(DessinModele model, VueDessin vue) {
		this.model = model;
		this.vue = vue;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Si le model n'est aps null
		if(this.model != null) {
			int button = e.getButton();
			// Si le bouton clique est le bouton gauche
			if(button == MouseEvent.BUTTON1) {
				// On supprime la premiere figure a la position de la souris si il y en a une
				int x = e.getX();
				int y = e.getY();
				this.model.supprimerFigure(x, y);
				// Sinon si le bouton clique est le droit et que la vue n'est pas null
			} else if(button == MouseEvent.BUTTON3 && this.vue != null) {
				int x = e.getX();
				int y = e.getY();
				// On affiche un popup de choix de suppression a la position de la souris
				String[] itemnames = { "Supprimer les figures colorées", "Supprimer les tracés", "Supprimer tous les dessins" };
				JPopupMenu popup = new JPopupMenu();
				for(String itemname : itemnames) {
					final JMenuItem item = new JMenuItem(itemname);
					item.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(item.getText().equals("Supprimer les figures colorées")) {
								SuppressionFigure.this.model.supprimerFiguresColorees();
							} else if(item.getText().equals("Supprimer les tracés")) {
								SuppressionFigure.this.model.supprimerTraces();
							} else if(item.getText().equals("Supprimer tous les dessins")) {
								SuppressionFigure.this.model.supprimerDessins();
							}
						}
					});
					popup.add(item);
				}
				popup.show(this.vue, x, y);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
