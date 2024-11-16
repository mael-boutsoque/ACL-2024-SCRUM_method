package fr.ul.acl.model;

public class Gun {
    private int shoot_delay;
    private int shoot_rate = 10;
    private int shoot_counter =0;
    private int damage = 1;
    private int bullet_size = 10 ;

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

    private void shoot(double angle , Entities entities){
        Player player = entities.get_player();
        int x = (int) (player.get_x()+player.get_width()/2 + 100*Math.cos(angle));
        int y = (int) (player.get_y()+player.get_height()/2 + 100*Math.sin(angle));
        entities.add_projectile(new Bullet(x,y,bullet_size,bullet_size,angle,30,damage));
    }

    public void add_shoot_rate(int value){
        shoot_rate += value;
        shoot_delay = 1000/(1+value);
        System.out.println("ATT_speed : [rate = "+String.valueOf(shoot_rate)+" , delay = "+String.valueOf(shoot_delay)+"]");
    }

    public void add_damages(int value) {
        this.damage += value;
        System.out.println("degats : [degats = "+String.valueOf(damage)+"]");
    }

    public void add_bullet_size(int value) {
        this.bullet_size += value;
        System.out.println("bullet size : [size = "+String.valueOf(bullet_size)+"]");
    }
}
