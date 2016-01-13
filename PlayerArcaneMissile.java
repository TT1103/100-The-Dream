import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class PlayerArcaneMissile here.
 * 
 * @author Tiger Zhao
 * @version January 11, 2016
 */
public class PlayerArcaneMissile extends PlayerProjectile
{
    public PlayerArcaneMissile(int speed, int damage){
        super(speed,damage);
    }
    /**
     * Act - do whatever the PlayerArcaneMissile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
 
        if(paused){
            return;
        }
        
        if(start){
            start = false;
            //set a variation in direction
            Random rand = new Random();
            MouseInfo mi = Greenfoot.getMouseInfo();
            if(mi !=null){
                turnTowards(mi.getX()+(rand.nextInt(100)-50), mi.getY()+(rand.nextInt(100)-50));
            }
        }
        
        List<Enemy> enemies = getObjectsInRange(200,Enemy.class);
        if(enemies.size()>0){
            Enemy e = enemies.get(0);
            if(e!=null){
                turnTowards(e.getX(), e.getY());
            }
        }
        

        
        move(speed);
        getImage().setTransparency(255);
        if(isTouching(Player.class)){
            getImage().setTransparency(0);
        }
        detectCollision();
        time--;
        
        if(time <0){
            getWorld().removeObject(this);
            return;
        }
        
     
    }    
}
