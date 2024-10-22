package fr.ul.acl.model;

public class Map extends Entity {
    Map(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "images\\map_3.png";
        load_image();
    }
}
