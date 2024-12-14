package fr.ul.acl.model;

import java.awt.Graphics2D;

//import java.util.ArrayList;

public class Map extends Entity {
    Map(int x,int y,int width,int height, Entities entities){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "src/main/resources/map_4.png";
        load_image();
    }

    public void load_map(int coef_carte , Entities entities){
    	
        //bord de map
        entities.obstacles.add(new Mur_h(0, -16*coef_carte,1200*coef_carte,16*coef_carte));//haut
        entities.obstacles.add(new Mur_h(-12*coef_carte, (630-3)*coef_carte,(1200+2*12)*coef_carte,16*coef_carte));//bas
        entities.obstacles.add(new Mur_v(-12*coef_carte, -13*coef_carte,12*coef_carte,(630+13)*coef_carte));//gauche
        entities.obstacles.add(new Mur_v(1200*coef_carte, -13*coef_carte,12*coef_carte,(630+13)*coef_carte));//droite
        //Obstacle 
        //Bloc Gris
        entities.obstacles.add(new Invisible(110*coef_carte,25*coef_carte,45*coef_carte,144*coef_carte));
        entities.obstacles.add(new Invisible(206*coef_carte,25*coef_carte,70*coef_carte,96*coef_carte));
        entities.obstacles.add(new Invisible(374*coef_carte,337*coef_carte,70*coef_carte,120*coef_carte));
        
        //Lac bleu
        entities.obstacles.add(new Invisible(88*coef_carte,373*coef_carte,88*coef_carte,127*coef_carte));
        entities.obstacles.add(new Invisible(71*coef_carte,400*coef_carte,126*coef_carte,72*coef_carte));
        //Bloc gris avec escalier
        entities.obstacles.add(new Invisible(158*coef_carte,169*coef_carte,1*coef_carte,144*coef_carte));
        entities.obstacles.add(new Invisible(158*coef_carte,169*coef_carte,261*coef_carte,1*coef_carte));
        entities.obstacles.add(new Invisible(419*coef_carte,169*coef_carte,1*coef_carte,96*coef_carte));
        entities.obstacles.add(new Invisible(161*coef_carte,286*coef_carte,21*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(185*coef_carte,310*coef_carte,45*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(251*coef_carte,310*coef_carte,72*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(323*coef_carte,286*coef_carte,48*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(371*coef_carte,262*coef_carte,24*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(395*coef_carte,238*coef_carte,24*coef_carte,27*coef_carte));
        //Bloc gris 2 étages
        entities.obstacles.add(new Invisible(737*coef_carte,310*coef_carte,69*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(827*coef_carte,310*coef_carte,312*coef_carte,27*coef_carte));
        entities.obstacles.add(new Invisible(734*coef_carte,121*coef_carte,1*coef_carte,213*coef_carte));
        entities.obstacles.add(new Invisible(734*coef_carte,121*coef_carte,22*coef_carte,1*coef_carte));
        entities.obstacles.add(new Invisible(758*coef_carte,96*coef_carte,1*coef_carte,94*coef_carte));
        entities.obstacles.add(new Invisible(758*coef_carte,193*coef_carte,50*coef_carte,21*coef_carte));
        entities.obstacles.add(new Invisible(806*coef_carte,193*coef_carte,1*coef_carte,40*coef_carte));
        entities.obstacles.add(new Invisible(806*coef_carte,236*coef_carte,72*coef_carte,26*coef_carte));
        entities.obstacles.add(new Invisible(899*coef_carte,238*coef_carte,21*coef_carte,24*coef_carte));
        entities.obstacles.add(new Invisible(758*coef_carte,73*coef_carte,70*coef_carte,24*coef_carte));
        entities.obstacles.add(new Invisible(828*coef_carte,97*coef_carte,95*coef_carte,1*coef_carte));
        entities.obstacles.add(new Invisible(923*coef_carte,25*coef_carte,216*coef_carte,240*coef_carte));
        entities.obstacles.add(new Invisible(1139*coef_carte,265*coef_carte,1*coef_carte,42*coef_carte));
        //Gazon
        entities.obstacles.add(new Invisible(151*coef_carte,483*coef_carte,61*coef_carte,51*coef_carte));
        entities.obstacles.add(new Invisible(200*coef_carte,400*coef_carte,51*coef_carte,85*coef_carte));
        //Cactus
        //entities.obstacles.add(new Invisible(350*coef_carte,28*coef_carte,45*coef_carte,42*coef_carte));
        //entities.obstacles.add(new Invisible(182*coef_carte,556*coef_carte,39*coef_carte,38*coef_carte));
        //entities.obstacles.add(new Invisible(254*coef_carte,460*coef_carte,45*coef_carte,43*coef_carte));
        //entities.obstacles.add(new Invisible(758*coef_carte,532*coef_carte,42*coef_carte,42*coef_carte));
        //Rochers
        //entities.obstacles.add(new Invisible(806*coef_carte,389*coef_carte,39*coef_carte,41*coef_carte));
        //entities.obstacles.add(new Invisible(280*coef_carte,550*coef_carte,40*coef_carte,23*coef_carte));
        //entities.obstacles.add(new Invisible(1022*coef_carte,478*coef_carte,40*coef_carte,23*coef_carte));
         
    }
    
    public void load_graphe_pathfinding(int coef_carte , Entities entities) {
    	
    	entities.pathfinder = new GraphePathfinding(entities.obstacles);
    	
    	//bord gauche
    	entities.pathfinder.add_waypoint(80*coef_carte, 5*coef_carte);
    	entities.pathfinder.add_waypoint(20*coef_carte, 350*coef_carte);
    	entities.pathfinder.add_waypoint(90*coef_carte, 200*coef_carte);
    	
    	//bord superieur
    	entities.pathfinder.add_waypoint(175*coef_carte, 5*coef_carte);
    	entities.pathfinder.add_waypoint(175*coef_carte, 130*coef_carte);
    	entities.pathfinder.add_waypoint(300*coef_carte, 5*coef_carte);
    	entities.pathfinder.add_waypoint(300*coef_carte, 130*coef_carte);
    	entities.pathfinder.add_waypoint(750*coef_carte, 5*coef_carte);
    	entities.pathfinder.add_waypoint(500*coef_carte, 130*coef_carte);
    	entities.pathfinder.add_waypoint(650*coef_carte, 130*coef_carte);
    	entities.pathfinder.add_waypoint(1155*coef_carte, 5*coef_carte);
    	
    	//Bloc gris avec escalier
    	entities.pathfinder.add_waypoint(325*coef_carte, 360*coef_carte);
    	entities.pathfinder.add_waypoint(232*coef_carte, 360*coef_carte);
    	entities.pathfinder.add_waypoint(232*coef_carte, 280*coef_carte);
    	entities.pathfinder.add_waypoint(180*coef_carte, 200*coef_carte);
    	entities.pathfinder.add_waypoint(400*coef_carte, 200*coef_carte);
    	entities.pathfinder.add_waypoint(400*coef_carte, 300*coef_carte);
    	
    	//Bloc gris 2 etages
    	entities.pathfinder.add_waypoint(1155*coef_carte, 350*coef_carte);
    	entities.pathfinder.add_waypoint(1155*coef_carte, 600*coef_carte);
    	entities.pathfinder.add_waypoint(810*coef_carte, 350*coef_carte);
    	entities.pathfinder.add_waypoint(720*coef_carte, 350*coef_carte);
    	entities.pathfinder.add_waypoint(810*coef_carte, 280*coef_carte);
    	entities.pathfinder.add_waypoint(750*coef_carte, 280*coef_carte);
    	entities.pathfinder.add_waypoint(1100*coef_carte, 280*coef_carte);
    	entities.pathfinder.add_waypoint(880*coef_carte, 280*coef_carte);
    	entities.pathfinder.add_waypoint(880*coef_carte, 150*coef_carte);

    	//Lac bleu
    	entities.pathfinder.add_waypoint(130*coef_carte, 340*coef_carte);
    	entities.pathfinder.add_waypoint(20*coef_carte, 500*coef_carte);
    	entities.pathfinder.add_waypoint(20*coef_carte, 600*coef_carte);
    	entities.pathfinder.add_waypoint(150*coef_carte, 600*coef_carte);
    	entities.pathfinder.add_waypoint(500*coef_carte, 600*coef_carte);
    	entities.pathfinder.add_waypoint(310*coef_carte, 500*coef_carte);
    	//entities.pathfinder.add_waypoint(500*coef_carte, 200*coef_carte);
    	entities.pathfinder.add_waypoint(500*coef_carte, 350*coef_carte);
    	entities.pathfinder.add_waypoint(500*coef_carte, 500*coef_carte);
    	
    	entities.pathfinder.generate_edjes();
    	
    	
    }
}

