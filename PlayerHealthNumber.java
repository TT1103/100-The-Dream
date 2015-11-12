import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class PlayerHealthNumber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerHealthNumber extends Actor
{
    int health;
    public PlayerHealthNumber(int health){
        this.health=health;
    }
    /**
     * Act - do whatever the PlayerHealthNumber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(new GreenfootImage(health+"", 20, Color.BLACK, null));
    }    
    
    public void healthchange(int damage){
       health-=damage;
    }
}
