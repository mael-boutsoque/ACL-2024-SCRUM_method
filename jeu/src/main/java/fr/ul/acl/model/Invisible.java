package fr.ul.acl.model;

public class Invisible extends Entity {
    Invisible(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = true;
        image_path = null;
        load_image();
    }
}
