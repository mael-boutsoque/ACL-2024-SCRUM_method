package fr.ul.acl.model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private ArrayList<Cmd> commandeEnCours;
	public static boolean isKeyPressed;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = new ArrayList<Cmd>();
		isKeyPressed = false;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public ArrayList<Cmd> getCommand() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		//gestion touches
		//System.out.println(e.toString());
			char keychar = e.getKeyChar();

			// controlle du joueur
			if( keychar == 'z' && !commandeEnCours.contains(Cmd.UP)) this.commandeEnCours.add(Cmd.UP);
			if( keychar == 's' && !commandeEnCours.contains(Cmd.DOWN)) this.commandeEnCours.add(Cmd.DOWN);
			if( keychar == 'q' && !commandeEnCours.contains(Cmd.LEFT)) this.commandeEnCours.add(Cmd.LEFT);
			if( keychar == 'd' && !commandeEnCours.contains(Cmd.RIGHT)) this.commandeEnCours.add(Cmd.RIGHT);
			if (e.getKeyCode() == KeyEvent.VK_SPACE && !commandeEnCours.contains(Cmd.SHOOT)){this.commandeEnCours.add(Cmd.SHOOT);
				System.out.println("space");
			}

			// controlle du menu d'amélioration
			if(keychar == 'm' && !commandeEnCours.contains(Cmd.OPENMENU)){//OPENMENU
				this.commandeEnCours.add(Cmd.OPENMENU);
			}
			if( keychar == '&' && !commandeEnCours.contains(Cmd.MENU_1)) this.commandeEnCours.add(Cmd.MENU_1);
			if( keychar == 'é' && !commandeEnCours.contains(Cmd.MENU_2)) this.commandeEnCours.add(Cmd.MENU_2);
			if( keychar == '"' && !commandeEnCours.contains(Cmd.MENU_3)) this.commandeEnCours.add(Cmd.MENU_3);

			// fermer le jeu
			if( e.getKeyCode()==27) {
				System.out.println("echap");
				System.exit(0);
			}
			isKeyPressed = true;
	
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		char keychar = e.getKeyChar();
			// joueur
			if( keychar == 'z' ) this.commandeEnCours.remove(Cmd.UP);
			if( keychar == 's' ) this.commandeEnCours.remove(Cmd.DOWN);
			if( keychar == 'q' ) this.commandeEnCours.remove(Cmd.LEFT);
			if( keychar == 'd' ) this.commandeEnCours.remove(Cmd.RIGHT);
			// menu
			if( keychar == 'm' ) this.commandeEnCours.remove(Cmd.OPENMENU);
			if( keychar == '&' ) this.commandeEnCours.remove(Cmd.MENU_1);
			if( keychar == 'é' ) this.commandeEnCours.remove(Cmd.MENU_2);
			if( keychar == '"' ) this.commandeEnCours.remove(Cmd.MENU_3);
			isKeyPressed = false;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
