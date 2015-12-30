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
    int imageDelay = 0;
    
    GreenfootImage[]images = {
        new GreenfootImage("gunman0.png"),
        new GreenfootImage("gunman1.png"),
        new GreenfootImage("gunman2.png"),
        new GreenfootImage("gunman1.png"),
        new GreenfootImage("gunman0.png"),
        new GreenfootImage("gunman3.png"),
        new GreenfootImage("gunman4.png"),
        new GreenfootImage("gunman3.png")
    };
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
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            turnTowards(player.getX(), player.getY());
            shoot();
        }
        if(imageDelay++ == 49){
            imageDelay = 0;
        }
        if(imageDelay%7 == 0){
            setImage(images[imageDelay/7]);
        }
        if(isTouching(Impassable.class)){
            turn(10);
        }
        move(3);
        controlDeath();
    }    
    
    public void shoot(){
        shootdelay++;
        if(shootdelay==100){
            getWorld().addObject(new EnemyBullet(15,10),getX(),getY());
            shootdelay=0;
        }
    }
}
