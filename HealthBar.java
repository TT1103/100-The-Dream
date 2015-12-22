import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends UnScrollable
{
    boolean start;

    int health;
    int startHealth;
    Enemy parent;
    
    public HealthBar(int health, Enemy parent){
        getImage().setColor(Color.GREEN);
        getImage().fill();
        this.parent = parent;
        this.startHealth=health;
        this.health = health;
    }

    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0,0,getImage().getWidth()-1,getImage().getHeight()-1);
        if(!parent.dead){
            setLocation(parent.getX(), parent.getY()+parent.getImage().getHeight());
        }else{
            getWorld().removeObject(this);
        }
    }   
    
    
    public int width(){
        return getImage().getWidth();//currently 32
    }
    public void damage(int damage){
        this.health-=damage;
        getImage().setTransparency(0);
        GreenfootImage newbar = new GreenfootImage(32, getImage().getHeight());
        if (health <= 0.25*startHealth){
            newbar.setColor(Color.RED);
        }else if (health < 0.6*startHealth){
            newbar.setColor(Color.YELLOW);
        }else{
            newbar.setColor(Color.GREEN);
        }
        setImage(newbar);
        newbar.fillRect(0,0, (int)(32 * ((double)health/startHealth)), getImage().getHeight());
   

    }
}
