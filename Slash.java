import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Slash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slash extends UnScrollable
{
    int damage;
    int time =0;
    int timeDivisor =4;
    int idx=0; //index of sprites
    GreenfootImage[] sprites = new GreenfootImage[] {
        new GreenfootImage("slash1.png"),
        new GreenfootImage("slash2.png"),
        new GreenfootImage("slash3.png")
    };
    public Slash(int damage){
        this.damage=damage;
        double ratio =2.0;
        sprites[0].scale((int)(sprites[0].getWidth()*ratio),(int)(sprites[0].getHeight()*ratio));
        sprites[1].scale((int)(sprites[1].getWidth()*ratio),(int)(sprites[1].getHeight()*ratio));
        sprites[2].scale((int)(sprites[2].getWidth()*ratio),(int)(sprites[2].getHeight()*ratio));
    }
    
    
    /**
     * Act - do whatever the Slash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        time++;
        MouseInfo mi = Greenfoot.getMouseInfo();
        if(mi !=null){
            turnTowards(mi.getX(), mi.getY());
        }
        if(idx>= sprites.length && time%timeDivisor==0){
            getWorld().removeObject(this);
            return;
        }
        if (time % timeDivisor ==0){
            setImage(sprites[idx]);
            detectCollision();
            idx+=1;
        }
        
       
            
        
    }    
    
    public void detectCollision(){
        List<Enemy> enemies =  getIntersectingObjects(Enemy.class);
        for (Enemy enemy: enemies){
            if(enemy != null){
                enemy.damage(damage);
            }
        }
        
        
    }
}
