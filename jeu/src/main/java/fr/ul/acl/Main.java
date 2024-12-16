package fr.ul.acl;

import fr.ul.acl.engine.GameEngineGraphical;
import fr.ul.acl.model.PacmanController;
import fr.ul.acl.model.PacmanGame;
import fr.ul.acl.model.PacmanPainter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		PacmanPainter painter = new PacmanPainter();
		PacmanGame game = new PacmanGame("helpFilePacman.txt" , painter.getWidth(),painter.getHeight());
		PacmanController controller = new PacmanController();
		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run(game);// ajoute le joueur aux classes a update pour avoir la position de la souri
		engine.get_jframe().addMouseMotionListener(game.get_Entities().get_player());
		//engine.get_jframe().addMouseListener(gestion_enttities.get_player());
	}

}