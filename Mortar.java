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
    int speed = 10;
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
        move(10);
        turn(rotation);
        rotation+=10;
        if(getX()<=x+10&&getY()<=y+10&&getX()>=x-10&&getY()>=y-10){
            getWorld().addObject(new Explosion(player), x, y);
            getWorld().removeObject(target);
            getWorld().removeObject(this);
        }
    }    
}
