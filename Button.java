import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A class used to represent a button. Allows for easy button creation.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Button extends Actor
{
    GreenfootImage image;
    boolean pressed =false;
    
    /**
     * A constructor used for creating a button with an image file name.
     *    
     * @param name The file name for the image to use.
     */
    public Button(String name){
        this.image = new GreenfootImage(name);
        setImage(image);
    }
    
    /**
     * A constructor used for creating a button with an image file name 
     * with a specific size.
     * 
     * @param name The file name for the image to use.
     * @param sizeX The width of the button.
     * @param sizeY The height of the button.
     */
    public Button(String name, int sizeX, int sizeY){
        this.image = new GreenfootImage(name);
        this.image.scale(sizeX,sizeY);
        setImage(this.image);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            pressed = true;
        }else{
            pressed=false;
        }
    }    
}
