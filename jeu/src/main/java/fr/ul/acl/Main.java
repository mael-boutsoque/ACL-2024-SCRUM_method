package fr.ul.acl;

import fr.ul.acl.model.PacmanPainter;
import fr.ul.acl.model.Entities;
import fr.ul.acl.engine.GameEngineGraphical;
import fr.ul.acl.model.PacmanController;
import fr.ul.acl.model.PacmanGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();
		Entities gestion_enttities = new Entities();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller,gestion_enttities);
		engine.run();
	}

}
