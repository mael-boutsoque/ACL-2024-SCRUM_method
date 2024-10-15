package model;

public class Player extends Entity {

    private int speed;

    Player(int x , int y){
        super(x - 30, y - 30);
        width = 60;
        height = 60;

        //chargement image
        image_path = "model\\images\\player.png";
        this.load_image();
        this.load_hitbox();

        // stats
        this.speed = 3;
    }

    public void move(int x,int y,Entities entities){
        this.move_relative(x, y, entities);
    }

    public void move_relative(int i,int j,Entities entities){
    	if(this.can_move(get_x() + i, get_y() + j, entities)) {
        x -= i;
        y -= j;
        x_relative += i;
        y_relative += j;}
    	else {
    		entities.player_move(i, j, entities);
    	}
    }

    public int get_speed() {
        return speed;
    }

}