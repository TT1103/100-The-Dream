import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gunman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gunman extends Enemy
{
    int shootdelay=0;
    public Gunman(int health){
        super(health);
    }
    /**
     * Act - do whatever the Gunman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        if(canSeePlayer()){
            shoot();
        }
        move(5);
    }    
    
    public void shoot(){
        shootdelay++;
        if(shootdelay==100){
            getWorld().addObject(new EnemyBullet(15,10),getX(),getY());
            shootdelay=0;
        }
    }
}
