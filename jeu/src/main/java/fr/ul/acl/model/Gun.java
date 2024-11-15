package fr.ul.acl.model;

public class Gun {
    private int shoot_rate = 10;
    private int shoot_counter =0;
    private int damage = 1;

    public void update(){
        if(shoot_counter<shoot_rate){
            shoot_counter++;
        }
    }

    public void try_to_shoot(double angle , Entities entities){
        if(shoot_counter >= shoot_rate){
            shoot_counter = 0;
            shoot(angle , entities);
        }
    }

    private void shoot(double angle , Entities entities){
        Player player = entities.get_player();
        int x = (int) (player.get_x()+player.get_width()/2 + 100*Math.cos(angle));
        int y = (int) (player.get_y()+player.get_height()/2 + 100*Math.sin(angle));
        entities.add_projectile(new Bullet(x,y,10,10,angle,30,damage));
    }
}
