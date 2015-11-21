import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Actor
{
    int speed;
    int speedDelay;
    int damage;

    GreenfootSound soundEffect;
    String weapon;
    public Weapon(String weapon){
        this.weapon=weapon;
        if(weapon.equals("p90")){
            speed =10;
            speedDelay =speed;
            soundEffect = new GreenfootSound("p90_shoot.wav");
        }else if(weapon.equals("barrett")){
            speed =50;
            speedDelay =speed;
            soundEffect = new GreenfootSound("p90_shoot.wav");
        }
    }

    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        speedDelay++;
    }    

    public void use(int xPos, int yPos){
        if(weapon.equals("p90")){
            if (speedDelay >= speed){
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                speedDelay =0;
                PlayerBullet bullet = new PlayerBullet(20,30);
                getWorld().addObject(bullet, xPos,yPos);

            }
        }else if(weapon.equals("barrett")){
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
}
