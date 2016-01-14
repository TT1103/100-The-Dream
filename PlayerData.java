import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Used to store player data as an object for easy saving and writing.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class PlayerData implements Serializable
{
    
    int curLevel =1;
    int curStatPoints =0;
    
    int curExp =0;
    int maxExp =100; //exp needed to level up
    
    int precision =10; 
    int intelligence =10; 
    int dexterity =10; 
    int defense =5; 
    int endurance =10; 
    int spirituality =10; 
    
    int maxHealth = 500;
    int maxMana =endurance *50;
    
    int maxHpRecoverDelay;
    int maxManaRegenDelay;
    int curGameLevel =1; //which map the player is on. 

    int speed; 
    
    String[] inventory = new String[98];
    String curWeapon, curHead, curChest, curLegs;
    
    /**
     * Saves the player data into a file located on the user's desktop.
     * 
     * @param player The player object that contains the data that needs to be saved.
     */
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
        
        speed = player.speed;
        
        maxManaRegenDelay = player.maxManaRegenDelay;
        maxHpRecoverDelay = player.maxHpRecoverDelay;
        curGameLevel = player.curGameLevel;
        parseInventory(player);
        
        //save the data:
        try{
            FileOutputStream fOut = new FileOutputStream (System.getProperty("user.home") + "/Desktop/player_data.txt");//("data/player_data.txt");
            ObjectOutputStream objOut = new ObjectOutputStream (fOut);
            objOut.writeObject (this);
            
            objOut.close();
            fOut.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Used to convert the inventory strings into actual objects usable by
     * the player.
     * 
     * @param player The player object that contains the inventory.
     */
    public void parseInventory(Player player){
        int i =0;
        for (Equipment e : player.inventory){
            if(e!=null){
                inventory[i]=e.name;
            }else{
                inventory[i] = null;
            }
            i++;
        }
        if(player.curWeapon != null){
            curWeapon = player.curWeapon.name;
        }else{
            curWeapon = null;
        }
        if(player.curHead != null){
            curHead = player.curHead.name;
        }else{
            curHead = null;
        }
        if(player.curChest != null){
            curChest = player.curChest.name;
        }else{
            curChest = null;
        }
        if(player.curLegs != null){
            curLegs = player.curLegs.name;
        }else{
            curLegs = null;
        }
    }
  
}
