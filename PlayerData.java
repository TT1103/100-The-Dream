import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Used to store player data as an object
 * 
 * @author Tiger Zhao
 * @version December 26, 2015
 */
public class PlayerData implements Serializable
{
    
    int curLevel =1;
    int curStatPoints =0;
    
    int curExp =0;
    int maxExp =100; //exp needed to level up
    
    
    int precision =10; //affects damage of ranged weapons like guns
    int intelligence =10; //affects damage of mana weapons like spells
    int dexterity =10; //affects damage melee weapons like swords
    int defense =5; //affects damage reduction
    int endurance =10; //affects hp
    int spirituality =10; //affects mana
    
    int maxHealth = 500;
    int maxMana =endurance *50;
    
    Weapon weapons;
    
    /**
     * Act - do whatever the PlayerData wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }   
    
    public void saveData(Player player){
        curLevel = player.curLevel;
        curStatPoints =player.curStatPoints;
        
        curExp =player.curExp;
        maxExp =player.maxExp; 
        precision =player.precision; 
        intelligence =player.intelligence; 
        dexterity =player.dexterity; 
        defense =player.defense; 
        endurance =player.endurance; 
        spirituality =player.spirituality; 
        
        maxHealth = player.maxHealth;
        maxMana = player.maxMana;
        
        try{
            FileOutputStream fOut = new FileOutputStream("data/player_data.txt");
            ObjectOutputStream objOut = new ObjectOutputStream (fOut);
            objOut.writeObject (this);
            
            objOut.close();
            fOut.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
