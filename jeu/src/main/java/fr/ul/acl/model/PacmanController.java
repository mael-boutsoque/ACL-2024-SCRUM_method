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
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = new ArrayList<Cmd>();
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
			char keychar = e.getKeyChar();
			if( keychar == 'z' && !commandeEnCours.contains(Cmd.UP)) this.commandeEnCours.add(Cmd.UP);
			if( keychar == 's' && !commandeEnCours.contains(Cmd.DOWN)) this.commandeEnCours.add(Cmd.DOWN);
			if( keychar == 'q' && !commandeEnCours.contains(Cmd.LEFT)) this.commandeEnCours.add(Cmd.LEFT);
			if( keychar == 'd' && !commandeEnCours.contains(Cmd.RIGHT)) this.commandeEnCours.add(Cmd.RIGHT);
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		char keychar = e.getKeyChar();
			if( keychar == 'z' ) this.commandeEnCours.remove(Cmd.UP);
			if( keychar == 's' ) this.commandeEnCours.remove(Cmd.DOWN);
			if( keychar == 'q' ) this.commandeEnCours.remove(Cmd.LEFT);
			if( keychar == 'd' ) this.commandeEnCours.remove(Cmd.RIGHT);
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
