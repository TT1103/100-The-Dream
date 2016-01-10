import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class BossHealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossHealthBar extends HealthBar
{
    public BossHealthBar(int health, Enemy parent){
        super(health, parent, 700,20);
        
    }
    /**
     * Act - do whatever the BossHealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0,0,getImage().getWidth()-1,getImage().getHeight()-1);
        if(!parent.dead){
            //setLocation(parent.getX(), parent.getY()+parent.getImage().getHeight());
        }else{
            getWorld().removeObject(this);
        }
    }   
}
