import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Sentry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sentry extends Enemy
{
    int bulletSpeed=20;
    int bulletDamage=10;
    int speedDelay =5;
    public Sentry(int health){
        super(health);
        
    }
    
    public Sentry(){
        super(1000);
    }
    /**
     * Act - do whatever the Sentry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        controlWeapons();
        controlDeath();
    }    
    
    public void controlWeapons(){
        if(speedDelay <20){
            speedDelay++;
        }
        
        if (canSeePlayer()){
            List l = getWorld().getObjects(Player.class);
            Player p = (Player) l.get(0);
            turnTowards(p.getX(),p.getY());
            
            //shoot
            if(speedDelay ==20){
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                speedDelay =0;
                EnemyBullet bullet = new EnemyBullet(bulletSpeed,bulletDamage);
                getWorld().addObject(bullet, getX(),getY());
            }
        }
    }
}
