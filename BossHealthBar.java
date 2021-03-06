import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * A special health bar used by enemy bosses
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
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
        if(parent.dead){
            getWorld().removeObject(this);
        }
    }   
}
