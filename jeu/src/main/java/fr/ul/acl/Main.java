package fr.ul.acl;

import fr.ul.acl.engine.GameEngineGraphical;
import fr.ul.acl.model.Entities;
import fr.ul.acl.model.PacmanController;
import fr.ul.acl.model.PacmanGame;
import fr.ul.acl.model.PacmanPainter;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();
		Entities gestion_enttities = new Entities(painter.getWidth(),painter.getHeight());

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller,gestion_enttities);
		engine.run(gestion_enttities.get_player());// ajoute le joueur aux classes a update pour avoir la position de la souri
		engine.get_jframe().addMouseMotionListener(gestion_enttities.get_player());
		engine.get_jframe().addMouseListener(gestion_enttities.get_player());
	}

}
