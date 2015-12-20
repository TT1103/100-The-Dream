import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrett here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrett extends Weapon
{
    int bulletSpeed =50;
    int bulletDamage=400;
    public Barrett(){
        //super("barrett");
        super();
        speedDelay =50;
        speed=50;
    }
    /**
     * Act - do whatever the P90 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(int xPos, int yPos){ //x and y: current player pos
       // super.use(xPos, yPos);
       if (speedDelay >= speed){
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                speedDelay =0;
                PlayerSniperBullet bullet = new PlayerSniperBullet(50,400);
                getWorld().addObject(bullet, xPos,yPos);
            }
    }
}
