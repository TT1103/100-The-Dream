import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * A mortar projectile weapon used by the enemy Mortar Tower.
 * 
 * @author Enoch Poon 
 * @version January 13, 2016
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
    int damage;
    public Mortar(int damage){
        target=new MortarTarget(this);
        this.damage=damage;
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
            getWorld().addObject(new EnemyExplosion(player,damage), x, y);
            getWorld().removeObject(target);
            getWorld().removeObject(this);
        }
    }    
}
