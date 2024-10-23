package fr.ul.acl.model;

public class Player extends Entity {
    private int speed;
    private Gun gun;

    Player(int x , int y){
        super(x - 30, y - 30 , 60 , 60);

        //chargement image
        image_path = "images\\player.png";
        this.load_image();

        // stats
        this.speed = 1;
        this.gun = new Gun();
    }

    public void move(int x,int y,Entities entities){
        this.move_relative(x, y, entities);
    }

    public void move_relative(int i,int j,Entities entities){
    	entities.player_move(i, j, entities);
    }

    public int get_speed() {
        return speed;
    }

    public Gun getGun() {
        return gun;
    }
}
