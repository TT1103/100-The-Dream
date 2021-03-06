import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * A sentry gun that shoots bullets at the player.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Sentry extends Enemy
{
    int bulletSpeed=20;

    
    int maxDelay =7;
    int speedDelay =maxDelay;
    
    
    int maxDelay2=80;
    int delay2 = maxDelay2;
    
    int shots =0;
   
      
    public Sentry(){
        super(1);
        healthBar = new HealthBar(500, this);
        damage =12;
    }
    
    public Sentry(int level){
        super(level);
        healthBar = new HealthBar(500 +(level*200), this);
        damage =10 + (int)(level*1.5);
    }
    /**
     * Act - do whatever the Sentry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        
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
        if (canSeePlayer() && distanceToPlayer()<=800){
            List l = getWorld().getObjects(Player.class);
            Player p = (Player) l.get(0);
            turnTowards(p.getX(),p.getY());
            
            //shoot
            if(speedDelay >=maxDelay && shots>0){
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                speedDelay =0;
                EnemyBullet bullet = new EnemyBullet(bulletSpeed,damage);
                getWorld().addObject(bullet, getX(),getY());
                shots--;
            }
        }else{
            shots=0;
        }
    }
}
