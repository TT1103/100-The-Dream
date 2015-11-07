import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class P90 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class P90 extends Weapon
{
    int bulletSpeed =20;
    int bulletDamage=30;
    public P90(){
        speed =10;
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
            PlayerBullet bullet = new PlayerBullet(bulletSpeed,bulletDamage);
            getWorld().addObject(bullet, xPos,yPos);
            
        }
    }
    
}
