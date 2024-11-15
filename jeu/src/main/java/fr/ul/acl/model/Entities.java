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
        obstacles = new ArrayList<Entity>() ;
        enemies = new ArrayList<Entity>() ;
        projectiles = new ArrayList<Entity>() ;
        obstacles.add(new Map(0,0,1200*coef_carte,630*coef_carte));

        //liste.add(new Entity(0,0,100,100));
        //liste.add(new Entity(300, 300,100,70));

        //bord de map
        obstacles.add(new Mur_h(0, -16*coef_carte,1200*coef_carte,16*coef_carte));//haut
        obstacles.add(new Mur_h(-12*coef_carte, (630-3)*coef_carte,(1200+2*12)*coef_carte,16*coef_carte));//bas
        obstacles.add(new Mur_v(-12*coef_carte, -13*coef_carte,12*coef_carte,(630+13)*coef_carte));//gauche
        obstacles.add(new Mur_v(1200*coef_carte, -13*coef_carte,12*coef_carte,(630+13)*coef_carte));//droite

        //Monstre
        //liste.add(new MonstreTest(700,300,110,110,2));

        //Spawner
        obstacles.add(new Spawner(2000,800,110,110));

        //Obstacle 
        //Bloc Gris
        obstacles.add(new Invisible(110*coef_carte,25*coef_carte,45*coef_carte,144*coef_carte));
        obstacles.add(new Invisible(206*coef_carte,25*coef_carte,70*coef_carte,96*coef_carte));
        obstacles.add(new Invisible(374*coef_carte,337*coef_carte,70*coef_carte,120*coef_carte));
        //Lac bleu
        obstacles.add(new Invisible(88*coef_carte,373*coef_carte,88*coef_carte,127*coef_carte));
        obstacles.add(new Invisible(71*coef_carte,400*coef_carte,126*coef_carte,72*coef_carte));
        //Bloc gris avec escalier
        obstacles.add(new Invisible(158*coef_carte,169*coef_carte,1*coef_carte,144*coef_carte));
        obstacles.add(new Invisible(158*coef_carte,169*coef_carte,261*coef_carte,1*coef_carte));
        obstacles.add(new Invisible(419*coef_carte,169*coef_carte,1*coef_carte,96*coef_carte));
        obstacles.add(new Invisible(161*coef_carte,286*coef_carte,21*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(185*coef_carte,310*coef_carte,45*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(251*coef_carte,310*coef_carte,72*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(323*coef_carte,286*coef_carte,48*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(371*coef_carte,262*coef_carte,24*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(395*coef_carte,238*coef_carte,24*coef_carte,27*coef_carte));
        //Bloc gris 2 étages
        obstacles.add(new Invisible(737*coef_carte,310*coef_carte,69*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(827*coef_carte,310*coef_carte,312*coef_carte,27*coef_carte));
        obstacles.add(new Invisible(734*coef_carte,121*coef_carte,1*coef_carte,213*coef_carte));
        obstacles.add(new Invisible(734*coef_carte,121*coef_carte,22*coef_carte,1*coef_carte));
        obstacles.add(new Invisible(758*coef_carte,96*coef_carte,1*coef_carte,94*coef_carte));
        obstacles.add(new Invisible(758*coef_carte,193*coef_carte,50*coef_carte,21*coef_carte));
        obstacles.add(new Invisible(806*coef_carte,193*coef_carte,1*coef_carte,40*coef_carte));
        obstacles.add(new Invisible(806*coef_carte,236*coef_carte,72*coef_carte,26*coef_carte));
        obstacles.add(new Invisible(899*coef_carte,238*coef_carte,21*coef_carte,24*coef_carte));
        obstacles.add(new Invisible(758*coef_carte,73*coef_carte,70*coef_carte,24*coef_carte));
        obstacles.add(new Invisible(828*coef_carte,97*coef_carte,95*coef_carte,1*coef_carte));
        obstacles.add(new Invisible(923*coef_carte,25*coef_carte,216*coef_carte,240*coef_carte));
        obstacles.add(new Invisible(1139*coef_carte,265*coef_carte,1*coef_carte,42*coef_carte));
        //Gazon
        obstacles.add(new Invisible(151*coef_carte,483*coef_carte,61*coef_carte,51*coef_carte));
        obstacles.add(new Invisible(200*coef_carte,385*coef_carte,51*coef_carte,102*coef_carte));
        //Cactus
        obstacles.add(new Invisible(350*coef_carte,28*coef_carte,45*coef_carte,42*coef_carte));
        obstacles.add(new Invisible(182*coef_carte,556*coef_carte,39*coef_carte,38*coef_carte));
        obstacles.add(new Invisible(254*coef_carte,460*coef_carte,45*coef_carte,43*coef_carte));
        obstacles.add(new Invisible(758*coef_carte,532*coef_carte,42*coef_carte,42*coef_carte));
        //Rochers
        obstacles.add(new Invisible(806*coef_carte,389*coef_carte,39*coef_carte,41*coef_carte));
        obstacles.add(new Invisible(280*coef_carte,550*coef_carte,40*coef_carte,23*coef_carte));
        obstacles.add(new Invisible(1022*coef_carte,478*coef_carte,40*coef_carte,23*coef_carte));

        player = new Player( win_width/2 , win_height/2 , this);
        this.player.move(1902, 1080,this);

        add_enemi(new MonstreTest(1000,1000,200,200,1000));
        add_enemi(new MonstreTest(1000,1200,100,100,100));
        
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
                enemies.remove(i);
                player.xp += 2;
            }
        }
    }

    public String toString(){
        return "[obstacles : "+String.valueOf(obstacles.size())+" , monstres : "+String.valueOf(enemies.size())+" , balles : "+String.valueOf(projectiles.size())+"]";

    }
}
