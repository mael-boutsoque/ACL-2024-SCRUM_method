package fr.ul.acl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.upgrades.Menu;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {

	private int entity_delay = 0;
	private boolean game_is_close = false;
	private Menu menu_upgrades;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source , int width , int height) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}

		this.menu_upgrades = new Menu(width , height);
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(ArrayList<Cmd> commandes , Entities entities) {
		//deplacement joueur
		//System.out.println(commandes.toString());
		int x=0,y=0;
		boolean changes_shooting_state=false;
		Cmd commande = Cmd.IDLE;
		for(int i=0;i<commandes.size();i++){
			commande = commandes.get(i);

			switch (commande) {
				case IDLE:
					break;
				case UP:
					y += -2;
					break;
				case DOWN:
					y += 2;
					break;
				case LEFT:
					x += -2;
					break;
				case RIGHT:
					x += 2;
					break;
				case SHOOT:
					changes_shooting_state=true;
					commandes.remove(Cmd.SHOOT);
					break;

				case OPENMENU:
					if(menu_upgrades.is_opened()){
						menu_upgrades.close();
					}
					else{
						menu_upgrades.open();
					}
					break;
			}
		}

		if(menu_upgrades.is_opened()){ // on fait evoluer le menu si il est ouvert
		}

		else{ // on fait evoluer le jeu si le menu n'est pas ouvert
			int speed = entities.get_player().get_speed();

			if (changes_shooting_state){
				entities.get_player().change_shooting_state();
			}
			if (entities.get_player().can_move(0, y*speed, entities)){
				entities.player_move(0, y*speed, entities);
			}
			if (entities.get_player().can_move(x*speed, 0, entities)){
				entities.player_move(x*speed, 0, entities);
			}

			// show entities hitbox if collision to debug
			for(int i=0;i<entities.enemies.size();i++){
				if(entities.get_enemi(i).colidable() && entities.get_player().colide(entities.get_enemi(i))){
					entities.get_enemi(i).show_hitbox = true ;
				}
			}
			
			entities.kill_dead_entities();
			//fait évoluer les entitiés
			entities.get_player().evolve(entities);

			if(entity_delay>1){
				entity_delay=0;

				for(int i =0;i<entities.enemies.size();i++) {
					entities.get_enemi(i).evolve(entities);
				}

				for(int i =0;i<entities.projectiles.size();i++) {
					entities.get_projectile(i).evolve(entities);
				}

				for(int i =0;i<entities.obstacles.size();i++) {
					entities.get_obstacle(i).evolve(entities);
				}
			}
			else entity_delay++;
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return game_is_close;
	}

	public Menu getMenu(){
		return menu_upgrades;
	}

}
