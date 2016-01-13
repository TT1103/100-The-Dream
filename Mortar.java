import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mortar extends Actor
{
    boolean start=true;
    Player player;
    int x;
    int y;
    int speed = 6;
    MortarTarget target;
    boolean paused =false;
    int rotation=0;
    public Mortar(){
        target=new MortarTarget(this);
        
    }

    public void act() 
    {
        if(paused){
            return;
        }
        if(start){
            player=(Player)getWorld().getObjects(Player.class).get(0);
            x=player.getX();
            y=player.getY();
            getWorld().addObject(target,x,y);
            start=false;
        }
        x=target.getX();
        y = target.getY();
        turnTowards(x,y);
        move(speed);
        turn(rotation);
        rotation+=10;
        if(getX()<=x+speed&&getY()<=y+speed&&getX()>=x-speed&&getY()>=y-speed){
            getWorld().addObject(new EnemyExplosion(player), x, y);
            getWorld().removeObject(target);
            getWorld().removeObject(this);
        }
    }    
}
