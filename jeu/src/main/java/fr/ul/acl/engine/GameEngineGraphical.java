package fr.ul.acl.engine;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

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

	private long previousTime = System.nanoTime();
	private long TARGET_FPS = 100;
	public long FPS = 0;

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
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run(Game game) throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			ArrayList<Cmd> c = this.gameController.getCommand();

			// fait evoluer le game
			this.game.evolve(c);
			//this.gamePainter.set_pos(entities.get_player().get_x(),entities.get_player().get_y());
			// affiche le game
			this.gui.paint(this.game , FPS);
			this.gui.get_jframe().addMouseMotionListener(game.get_Entities().get_player());
			//this.gui.get_jframe().addMouseListener(player);
			// met en attente
			long currentTime = System.nanoTime();
            long elapsedTime = currentTime - previousTime;
            previousTime = currentTime;
			//Thread.sleep(5);
			long min_wait = 3;
			Thread.sleep(FPS);
			long waitTime = 1000000000 / TARGET_FPS - elapsedTime;
			FPS = Math.max(min_wait,waitTime / 1000000);
		}
	}

	public JFrame get_jframe(){
		return gui.get_jframe();
	}

}
