import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class EnemySlash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemySlash extends Actor
{
    int damage;
    int time =0;
    int timeDivisor = 10;
    int idx=0; //index of sprites
    GreenfootImage[] sprites = new GreenfootImage[] {
            new GreenfootImage("slash1.png"),
            new GreenfootImage("slash2.png"),
            new GreenfootImage("slash3.png")
        };
    Player p;
    boolean playerHit = false;
    boolean paused =false;
    public EnemySlash(int damage){
        this.damage=damage;
        double ratio =2.0;
        sprites[0].scale((int)(sprites[0].getWidth()*ratio),(int)(sprites[0].getHeight()*ratio));
        sprites[1].scale((int)(sprites[1].getWidth()*ratio),(int)(sprites[1].getHeight()*ratio));
        sprites[2].scale((int)(sprites[2].getWidth()*ratio),(int)(sprites[2].getHeight()*ratio));
        getImage().setTransparency(0);
    }

    /**
     * Act - do whatever the Slash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        time++;
        if(getObjectsInRange(30, Player.class).size() > 0){
            p = (Player) getObjectsInRange(30, Player.class).get(0);
        }
        if(p !=null){
            turnTowards(p.getX(), p.getY());
        }
        if(idx>= sprites.length && time%timeDivisor==0){
            getWorld().removeObject(this);
            return;
        }
        if (time % timeDivisor ==0){
            setImage(sprites[idx++]);
            detectCollision();
            getImage().setTransparency(255);
        }
        
            
    }    
    public void detectCollision(){
        if(p != null && intersects(p) && playerHit == false && idx > 0){
            p.damage(damage);
            if(p.knockback==false){
                p.knockback=true;
                p.knockbackStrength=5;
                p.knockbackRotation=getRotation();
            }

            playerHit = true;
        }

    }
}
