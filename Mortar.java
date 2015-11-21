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
    MortarTarget target;
    public Mortar(){
        target=new MortarTarget(this);
        
    }

    public void act() 
    {

        if(start){
            player=(Player)getWorld().getObjects(Player.class).get(0);
            x=player.getX();
            y=player.getY();
            getWorld().addObject(target,x,y);
            start=false;
        }
        turnTowards(x,y);
        move(20);
        if(getX()<=x+10&&getY()<=y+10&&getX()>=x-10&&getY()>=y-10){
            if(isTouching(Player.class)){
                player.damage(100);

                player.knockback=true;
                player.knockbackStrength=15;
                turnTowards(player.getX(),player.getY());
                player.knockbackRotation=getRotation();

            }
            getWorld().removeObject(target);
            getWorld().removeObject(this);
        }
    }    
}
