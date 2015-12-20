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
    /*public Weapon(String weapon){
        this.weapon=weapon;
        if(weapon.equals("p90")){
            speed =10;
            speedDelay =speed;
            soundEffect = new GreenfootSound("p90_shoot.wav");
        }else if(weapon.equals("barrett")){
            speed =0;//50
            speedDelay =speed;
            soundEffect = new GreenfootSound("p90_shoot.wav");
        }
    }*/
    
    

    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(speedDelay <speed){
            speedDelay++;
        }
    }    

    public void use(int xPos, int yPos){
        
    }
}
