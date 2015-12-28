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
    
    int maxDelay =7;
    int speedDelay =maxDelay;
    
    
    int maxDelay2=80;
    int delay2 = maxDelay2;
    
    int shots =0;
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
        if(paused){
            return;
        }
        controlWeapons();
        controlDeath();
    }    
    
    public void controlWeapons(){
        if(delay2<maxDelay2){
            delay2++;
        }
        
        if(speedDelay <maxDelay){
            speedDelay++;
        }
        
        if(delay2>=maxDelay2){
            shots =3;
            delay2 =0;
        }
        if (canSeePlayer() && distanceToPlayer()<=1000){
            List l = getWorld().getObjects(Player.class);
            Player p = (Player) l.get(0);
            turnTowards(p.getX(),p.getY());
            
            //shoot
            if(speedDelay >=maxDelay && shots>0){
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                speedDelay =0;
                EnemyBullet bullet = new EnemyBullet(bulletSpeed,bulletDamage);
                getWorld().addObject(bullet, getX(),getY());
                shots--;
            }
        }else{
            shots=0;
        }
    }
}
