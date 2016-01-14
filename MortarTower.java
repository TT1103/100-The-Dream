import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * @author Tiger Zhao, Enoch Poon
 * @version January 13, 2016
 */
public class MortarTower extends Enemy
{
    int delay=100;
    public MortarTower(int level){
        super(level);
        healthBar = new HealthBar(600+(level*250), this);
        damage = 50 + (level*5);
    }

    public MortarTower(){
        super(1);
        healthBar = new HealthBar(500, this);
        damage = 50;
    }

    public void act() 
    {
        if (paused) {
            return;
        }
        delay--;

        List l = getWorld().getObjects(Player.class);
        Player p = (Player) l.get(0);
        turnTowards(p.getX(),p.getY());
        if(delay<0){
            getWorld().addObject(new Mortar(damage),getX(),getY());
            GreenfootSound effect = new GreenfootSound("mortar_shoot.wav");
            effect.setVolume(85);
            effect.play();
            delay=100;
        }
        super.act();
    }    
}
