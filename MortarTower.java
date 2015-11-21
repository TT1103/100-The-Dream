import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MissileTurret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MortarTower extends Enemy
{
    int delay=100;
    public MortarTower(int health){
        super(health);
    }
    public void act() 
    {
        delay--;
        if(canSeePlayer()&&delay<0){
            getWorld().addObject(new Mortar(),getX(),getY());
            delay=100;
        }
        
        if(dead){
            getWorld().removeObject(this);
        }
    }    
}
