import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knifeman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knifeman extends Enemy
{
    int attackdelay=0;
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

    EnemySlash slash = new EnemySlash(50);
    public Knifeman(int health){
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

            attack();

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
        move(2);
        if(getWorld().getObjects(EnemySlash.class).contains(slash)){
            slash.setLocation(getX(),getY());
        }
        controlDeath();
    }    

    public void attack(){
        
        attackdelay++;
        if(attackdelay >= 100 && getObjectsInRange(30, Player.class).size() > 0){
            slash = new EnemySlash(50);
            getWorld().addObject(slash,getX(),getY());
            attackdelay=0;
        }
    }
}
