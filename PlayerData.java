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
    
    int maxHpRecoverDelay;
    int maxManaRegenDelay;
    int curGameLevel =1; //which map the player is on. 

    String[] inventory = new String[98];
    String curWeapon, curHead, curChest, curLegs;
    //Equipment[] inventory = new Equipment[98];
    
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
        
        
        maxManaRegenDelay = player.maxManaRegenDelay;
        maxHpRecoverDelay = player.maxHpRecoverDelay;
        curGameLevel = player.curGameLevel;
        parseInventory(player);
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
