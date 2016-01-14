import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to model a weapon used by the player.
 * 
 * @author Tiger Zhao 
 * @version January 14, 2016
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
    
    /**
     * Contains the code for the weapon's use.
     * Varies from different weapons.
     */
    public void use(){
        
    }
}
