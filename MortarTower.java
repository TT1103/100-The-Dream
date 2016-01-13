import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MissileTurret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MortarTower extends Enemy
{
    int delay=100;
    public MortarTower(int level){
        super(level);
        healthBar = new HealthBar(level*500, this);
    }

    public MortarTower(){
        super(1);
        healthBar = new HealthBar(500, this);
    }

    public void act() 
    {
        if (paused) {
            return;
        }
        delay--;
        //if(canSeePlayer()){
        List l = getWorld().getObjects(Player.class);
        Player p = (Player) l.get(0);
        turnTowards(p.getX(),p.getY());
        if(delay<0){
            getWorld().addObject(new Mortar(),getX(),getY());
            GreenfootSound effect = new GreenfootSound("mortar_shoot.wav");
            effect.setVolume(85);
            effect.play();
            delay=100;
        }
        // }
        super.act();
        

    }    
}
