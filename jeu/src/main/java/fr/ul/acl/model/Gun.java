package fr.ul.acl.model;
import java.util.Random;

public class Gun {
    private int shoot_delay;
    private int shoot_rate = 10;
    private int shoot_counter =0;
    private int damage = 1;
    private int bullet_size = 10 ;
    private int bullet_speed = 20;
    protected int xp_effet = 0;
    private int xp_to_next_lvl_effet = 6-1;
    protected String effet = "Normal";


    public Gun(){
        add_shoot_rate(shoot_rate);
    }

    public void update(){
        if(shoot_counter<shoot_delay){
            shoot_counter++;
        }
    }

    public void try_to_shoot(double angle , Entities entities){
        if(shoot_counter >= shoot_delay){
            shoot_counter = 0;
            shoot(angle , entities);
        }
    }

    private String choice_effet(){
        Random random = new Random();
        int choice = random.nextInt(2) + 1;
        if (choice == 1){
            return "Glace";
        }
        else {
            return "Feux";
        }
    }

    public void envolve_effet(Entities entities){
        if(this.xp_effet > this.xp_to_next_lvl_effet) {
			this.xp_effet = 0;
			//this.lvl++;

		}
    }

    private void shoot(double angle , Entities entities){
        Player player = entities.get_player();
        int x = (int) (player.get_x()+player.get_width()/2 + 100*Math.cos(angle));
        int y = (int) (player.get_y()+player.get_height()/2 + 100*Math.sin(angle));
        entities.add_projectile(new Bullet(x,y,bullet_size,bullet_size,angle,bullet_speed,damage,"Normal"));   
        
        System.err.println("compteur = "+entities.compteur);
        System.err.println("compteur_0 = "+entities.compteur_0);
        if (this.xp_effet>this.xp_to_next_lvl_effet){
            if (entities.compteur_0 == 0){
                entities.compteur_0 = entities.compteur;
                this.effet = choice_effet();
            }
            if (choice_effet().equals("Feux")){
                if (entities.compteur_projectile(6*entities.get_wave()) == 1){
                    this.xp_effet = 0;
                    entities.compteur = 0;
                    entities.compteur_0 = 0;
                    this.effet = "Normal";
                    /*for (int i=0; i<entities.enemies.size(); i++){
                        if (entities.get_enemi(i).speed == 0){
                            entities.get_enemi(i).bouge();
                        }
                    }*/
                }
            }
            else {
                if (entities.compteur_projectile(10*entities.get_wave()) == 1){
                    this.xp_effet = 0;
                    entities.compteur = 0;
                    entities.compteur_0 = 0;
                    this.effet = "Normal";
                    /*for (int i=0; i<entities.enemies.size(); i++){
                        if (entities.get_enemi(i).speed == 0){
                            entities.get_enemi(i).bouge();
                        }
                    }*/
                }
            }
            
            /*if (choice_effet().equals("Glace") || choice_effet().equals("Feux")){
                entities.add_projectile(new Bullet(x,y,bullet_size,bullet_size,angle,bullet_speed,damage,"Normal"));    
            }*/
            entities.add_projectile(new Bullet(x,y,bullet_size,bullet_size,angle,bullet_speed,damage,this.effet));
            
        }
        
    }

    public void add_shoot_rate(int value){
        shoot_rate += value;
        shoot_delay = 5 + 10000/(10+shoot_rate*shoot_rate);
        System.out.println("ATT_speed : [rate = "+String.valueOf(shoot_rate)+" , delay = "+String.valueOf(shoot_delay)+"]");
    }

    public void add_damages(int value) {
        this.damage += value;
        System.out.println("degats : [degats = "+String.valueOf(damage)+"]");
    }

    public void add_bullet_size(int value) {
        bullet_size = Math.min(bullet_size+value,100);
        System.out.println("bullet size : [size = "+String.valueOf(bullet_size)+"]");
    }

    public void add_bullet_speed(int value) {
        bullet_speed = Math.min(bullet_speed+value , 100);
        System.out.println("bullet speed : [speed = "+String.valueOf(bullet_speed)+"]");
    }
}
