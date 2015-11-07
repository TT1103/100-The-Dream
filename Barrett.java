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
        speed =50;
        speedDelay =speed;
        soundEffect = new GreenfootSound("p90_shoot.wav");
    }
    /**
     * Act - do whatever the P90 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        speedDelay++;
    }    
    
    public void use(int xPos, int yPos){ //x and y: current player pos

        if (speedDelay >= speed){
            GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay =0;
            PlayerSniperBullet bullet = new PlayerSniperBullet(bulletSpeed,bulletDamage);
            getWorld().addObject(bullet, xPos,yPos);
            
        }
    }
}
