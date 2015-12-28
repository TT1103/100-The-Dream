import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends GUI
{
    GreenfootImage image;
    boolean pressed =false;
    public Button(){
        
    }
    
    public Button(String name){
        this.image = new GreenfootImage(name);
        setImage(image);
    }
    
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
