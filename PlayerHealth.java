import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class PlayerHealth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerHealth extends Actor
{
    boolean start=true;
    int health;
    int startHealth;
    Player parent;
    PlayerHealthNumber phn;
    public PlayerHealth(int health, Player parent){
        getImage().setColor(Color.GREEN);
        getImage().fill();
        this.parent=parent;
        this.startHealth=health;
        this.health = health;
        phn=new PlayerHealthNumber(health);
    }

    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(start){
            getWorld().addObject(phn,getX(),getY());
            start=false;
        }
        getImage().scale(350, 20);
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0,0,getImage().getWidth()-1, getImage().getHeight()-1);
        
    }   
    
    public void damage(int damage){
        health-=damage;
        getImage().setTransparency(0);
        
        GreenfootImage newbar = new GreenfootImage(getImage().getWidth(), getImage().getHeight());
        if (health <= 0.25*startHealth){
            newbar.setColor(Color.RED);
        }else if (health < 0.6*startHealth){
            newbar.setColor(Color.YELLOW);
        }else{
            newbar.setColor(Color.GREEN);
        }
        setImage(newbar);
        newbar.fillRect(0,0, (int)(350 * ((double)health/startHealth)), getImage().getHeight());
        phn.healthchange(damage);

    }
}
