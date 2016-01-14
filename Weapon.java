import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Equipment
{
    int speed;
    int speedDelay;
    int damage;

    GreenfootSound soundEffect;
    String weapon;
    boolean paused =false;

    Player player;
    String damageType;
    
    int damageRatio =4; //how much a stat point affects damage
    public Weapon(Player player){
        type = "weapon";
        this.player = player;
    }
    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        if(speedDelay <speed){
            speedDelay++;
        }
    }    

    public void use(){
        
    }
}
