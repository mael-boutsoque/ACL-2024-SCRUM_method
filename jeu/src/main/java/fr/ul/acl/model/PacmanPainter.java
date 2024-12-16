package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;

import fr.ul.acl.engine.Game;
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
	private int fps_show_counter=0;
	private double fps_temp = 0;

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
	public void draw(BufferedImage im , Game game , long FPS) {

		// creation du crayon pour dessiner
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setStroke(new BasicStroke(3));
		crayon.setColor(Color.GREEN);


		// parcour les entities pour les dessiner
		game.get_Entities().draw(crayon);		

		crayon = null;
		crayon = (Graphics2D) im.getGraphics();
		crayon.setStroke(new BasicStroke(3));
		crayon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		crayon.setColor(Color.GREEN);
		crayon.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if(game.get_Menu().is_opened()){
			// dessine le menu
			game.get_Menu().draw(crayon);
		}

		//dessine FPS
		if(Math.abs(FPS)<1000 && fps_show_counter > 100){
			fps_temp=FPS;
			fps_show_counter=0;
		}
		else{
			fps_show_counter++;
		}
		crayon.drawString("FPS="+String.valueOf(fps_temp),10,30);
		

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
