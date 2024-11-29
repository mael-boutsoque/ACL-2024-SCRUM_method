package fr.ul.acl.engine;

import javax.swing.JFrame;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanel panel;

	private JFrame f;
	
	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 * 
	 * @param gamePainter l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 * 
	 */
	public GraphicalInterface(GamePainter gamePainter, GameController gameController){
		f=new JFrame();
		//f.setPreferredSize(new Dimension(1920,1080));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// attacher le panel contenant l'afficheur du game
		this.panel=new DrawingPanel(gamePainter);
		f.setContentPane(this.panel);
		f.setUndecorated(true);
		
		// attacher controller au panel du game
		this.panel.addKeyListener(gameController);	
		
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	
	/**
	 * mise a jour du dessin
	 * @param game 
	 */
	public void paint(Game game , long FPS) {
		this.panel.drawGame(game , FPS);	
	}

	public JFrame get_jframe(){
		return this.f;
	}
	
}
