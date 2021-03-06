import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * A class to model a weapon explosion that damages enemies. 
 * It is used by the Player
 * 
 * @author Tiger Zhao
 * @version January 14, 2015
 */
public class PlayerExplosion extends Actor
{
    GreenfootImage[]images={
            new GreenfootImage("explosion1.png"), 
            new GreenfootImage("explosion2.png"), 
            new GreenfootImage("explosion3.png"), 
            new GreenfootImage("explosion4.png")};
           
    int delay=0;
    
    boolean paused = false;
    int damage;
    
    /**
     * Constructor for the explosion.
     * 
     * @param damage The amount of damage that this explosion deals.
     */
    public PlayerExplosion(int damage){
        this.damage =damage;

        GreenfootSound effect = new GreenfootSound("explosion_effect.wav");
        effect.setVolume(80);
        effect.play();

    }

    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        delay++;
        if(delay < 32){
            setImage(images[delay/8]);
            Map map=(Map)getWorld();
            
        }else{
            getWorld().removeObject(this);
        }
        if(delay <=1){
            List<Enemy> enemies = getIntersectingObjects(Enemy.class);
            for(Enemy e: enemies){
                if(e!=null){
                    e.damage(damage);
                }
            }         
        }
        
        getImage().setTransparency(getImage().getTransparency()-5);
        turn(1);
    }    
}
