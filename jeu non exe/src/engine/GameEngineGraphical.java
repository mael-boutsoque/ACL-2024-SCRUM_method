package engine;

import java.util.ArrayList;

import model.Entities;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	private Entities entities;
	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController, Entities gestion_entities) {
		// creation du game
		this.entities = gestion_entities;
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			ArrayList<Cmd> c = this.gameController.getCommand();

			// fait evoluer le game
			this.game.evolve(c,entities);
			//this.gamePainter.set_pos(entities.get_player().get_x(),entities.get_player().get_y());
			// affiche le game
			this.gui.paint(this.game , entities);
			// met en attente
			Thread.sleep(3);
		}
	}

}
