package fr.ul.acl.engine;

import java.awt.image.BufferedImage;

import fr.ul.acl.model.Entities;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * 
 * represente la maniere de dessiner sur un JPanel
 * 
 */
public interface GamePainter {

	/**
	 * methode dessiner a completer. Elle construit une image correspondant au
	 * jeu. Game est un attribut de l'afficheur
	 * 
	 * @param image
	 *            image sur laquelle dessiner
	 */
	public abstract void draw(BufferedImage image , Entities entities , long FPS);

	public abstract int getWidth();

	public abstract int getHeight();	
}
