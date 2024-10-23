package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;

import fr.ul.acl.engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 1920/2;
	protected static final int HEIGHT = 1080/2;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter() {
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im , Entities entities) {

		// creation du crayon pour dessiner
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setStroke(new BasicStroke(3));
		crayon.setColor(Color.RED);

		// parcour les entities pour les dessiner
		for(int i=0;i<entities.size();i++){
			Entity entitee = entities.get_by_id(i);
			crayon.drawImage(entitee.get_image(), entitee.get_x(), entitee.get_y(), entitee.get_width(), entitee.get_height(), null, null);

			// show hitbox to debbug
			if(true) {
				crayon.setColor(Color.BLUE);
				crayon.drawRect(entitee.hitbox.get_x(), entitee.hitbox.get_y(), entitee.hitbox.get_width(), entitee.hitbox.get_height());
			}
		
			// dessine le joueur
			entitee = entities.get_player();
			crayon.drawImage(entitee.get_image(), entitee.get_x(), entitee.get_y(), entitee.get_width(), entitee.get_height(), null, null);
			
			
				
		}

		crayon.dispose();
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}
}
