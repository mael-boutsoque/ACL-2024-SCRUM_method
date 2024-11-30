package fr.ul.acl.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entities {
    public ArrayList<Entity> obstacles;
    public ArrayList<Entity> enemies;
    public ArrayList<Entity> projectiles;
    private Player player;
    protected int nbMonstre = 0;
    protected int nbMonstreMax = 10;

    /*
     * the map is always the first entity
     */
    public Entities(int win_width,int win_height){
        final int coef_carte = 6;
        final int carte_x = 1200;
        final int carte_y = 630;
        obstacles = new ArrayList<Entity>() ;
        enemies = new ArrayList<Entity>() ;
        projectiles = new ArrayList<Entity>() ;
        
        obstacles.add(new Map(0,0,1200*coef_carte,630*coef_carte,this));

        //liste.add(new Entity(0,0,100,100));
        //liste.add(new Entity(300, 300,100,70));

        //Monstre
        //liste.add(new MonstreTest(700,300,110,110,2));

        //Spawner
        //obstacles.add(new Spawner(2000,800,110,110));
        add_enemi(new Zombie_tireur(2000,900,100,100,1000));
        add_enemi(new Zombie_quick(100, 100, 100, 100, 100));

        player = new Player( 1536/2 , 864/2 ,this,1000);
        this.player.move(1902, 1080,this);

        add_enemi(new Zombie(1000,1000,200,200,1000));
        add_enemi(new Zombie(1000,1200,100,100,100));

        add_enemi(new Boss(4500,2000,400,400,10000));
        
    }

    public void supp_entities(Entity entity) {
    	for(int i =0; i<obstacles.size();i++) {
    		if (obstacles.get(i)==entity) {
    			obstacles.remove(i);
    		}
    	}
        for(int i =0; i<enemies.size();i++) {
    		if (enemies.get(i)==entity) {
    			enemies.remove(i);
    		}
    	}
        for(int i =0; i<projectiles.size();i++) {
    		if (projectiles.get(i)==entity) {
    			projectiles.remove(i);
    		}
    	}
    }

    public Monstre get_enemi(int id){
        return (Monstre) enemies.get(id);
    }
    public void add_enemi(Monstre enemi){
        enemies.add(enemi);
    }

    public Entity get_obstacle(int id){
        return obstacles.get(id);
    }
    public void add_obstacle(Entity obstacle){
        obstacles.add(obstacle);
    }

    public Bullet get_projectile(int id){
        return (Bullet) projectiles.get(id);
    }
    public void add_projectile(Bullet projectile){
        projectiles.add(projectile);
    }

    public Player get_player(){
        return player;
    }
    
    public void player_move(int x,int y,Entities entities){
        for(int i=0;i<projectiles.size();i++){
            projectiles.get(i).move_relative(-x, -y,entities);
        }
        for(int i=0;i<obstacles.size();i++){
            obstacles.get(i).move_relative(-x, -y,entities);
        }
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).move_relative(-x, -y,entities);
        }
    }

    public void draw(Graphics2D crayon){
        for(int i=0;i<obstacles.size();i++){
			obstacles.get(i).draw(crayon);
        }
        for(int i=0;i<enemies.size();i++){
			enemies.get(i).draw(crayon);
        }
        for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).draw(crayon);
        }
        this.get_player().draw(crayon);
    }

    public void kill_dead_entities(){
        for(int i=projectiles.size()-1 ; i>=0 ; i--){
            if (projectiles.get(i).is_dead){
                projectiles.remove(i);
            }
        }
        for(int i=obstacles.size()-1 ; i>=0 ; i--){
            if (obstacles.get(i).is_dead){
                obstacles.remove(i);
            }
        }
        for(int i=enemies.size()-1 ; i>=0 ; i--){
            if (enemies.get(i).is_dead){
                if(!(enemies.get(i) instanceof Bullet_enemi)){
                    player.xp += 2;
                }
                enemies.remove(i);
            }
        }
    }

    public String toString(){
        return "[obstacles : "+String.valueOf(obstacles.size())+" , monstres : "+String.valueOf(enemies.size())+" , balles : "+String.valueOf(projectiles.size())+"]";

    }
}
