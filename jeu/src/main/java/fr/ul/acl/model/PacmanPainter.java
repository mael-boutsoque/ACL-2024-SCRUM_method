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
	protected static final int WIDTH = 1920;
	protected static final int HEIGHT = 1080;

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
		crayon.setColor(Color.GREEN);

		// parcour les entities pour les dessiner
		entities.draw(crayon);

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
