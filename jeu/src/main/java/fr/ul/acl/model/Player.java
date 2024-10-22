package fr.ul.acl.model;

public class Player extends Entity {
    private int speed;

    Player(int x , int y){
        super(x - 30, y - 30 , 60 , 60);

        //chargement image
        image_path = "images\\player.png";
        this.load_image();

        // stats
        this.speed = 1;
    }

    public void move(int x,int y,Entities entities){
        this.move_relative(x, y, entities);
    }

    public void move_relative(int i,int j,Entities entities){
    	if(this.can_move(i,j, entities)) {
            if(this.x + i > 0 && this.x + i < 1920/2 && this.y + j > 0 && this.y + j < 1080/2){
                x -= i;
                y -= j;
                x_relative += i;
                y_relative += j;
            }
        }
    }

    public int get_speed() {
        return speed;
    }

}
