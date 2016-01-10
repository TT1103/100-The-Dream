import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * Write a description of class LevelSelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelSelector extends World
{
    //variables for game data
    String[] completedLevels;
    Player player; //Stores the player stuff

    // Buttons for level selection
    private LevelSelectorButtons one = new LevelSelectorButtons("Level1");
    private LevelSelectorButtons two = new LevelSelectorButtons("Level2");
    private LevelSelectorButtons three = new LevelSelectorButtons("Level3");
    private LevelSelectorButtons four = new LevelSelectorButtons("Level4");
    private LevelSelectorButtons five = new LevelSelectorButtons("Level5");
    
    
    boolean newGame = false; //if it is a new game or not

    int curGameLevel=1;
    
    Button resetButton; //button to start a new game and delete current game data
    
    /**
     * Constructor for objects of class LevelSelector.
     * 
     */
    public LevelSelector()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 

        // Add all the level selector buttons 
        addObject (one, 96, 481);
        addObject (two, 222, 481);
        addObject (three, 345, 481);
        addObject (four, 469, 481);
        addObject (five, 587, 481);
        
        // Creating the new game 
        newGame = !loadData();
        
   
        
    }

    public void act(){
        // Check for mouse click of each buttons to determine level selection
        int level=-1;
        
        if (Greenfoot.mouseClicked(one) && curGameLevel >=1) {
            level=1;
        } else if (Greenfoot.mouseClicked(two) && curGameLevel >=2) {
            level=2;
        } else if (Greenfoot.mouseClicked(three) && curGameLevel >=3) {
            level=3;
        } else if (Greenfoot.mouseClicked(four)) {
            level=4;
        } else if (Greenfoot.mouseClicked(five)) {
            level=5;
        }
        
        if(level > 0){
            if (newGame){
                Greenfoot.setWorld (new Map(level));
            }else{
                Greenfoot.setWorld (new Map(level,player));
            }
        }
        
        
    }

    public boolean loadData(){ //loads previous save data
        File file = new File("data/player_data.txt");
        if(!file.exists()){ //there has been no save data
            return false; //load data failed
        } 

        try{ //there is save data, now read it
            FileInputStream fileIn = new FileInputStream("data/player_data.txt");
            ObjectInputStream objIn = new ObjectInputStream (fileIn);

            PlayerData playerData = (PlayerData)objIn.readObject();
            player = new Player(playerData);
            curGameLevel=playerData.curGameLevel;
            parseInventory(player);
            fileIn.close();
            objIn.close();
        }catch (Exception ex){ //start new game if we can't read the file
            ex.printStackTrace();
            file.delete(); //remove the unreadable file
            return false;
        }
        return true;
    }

    public void parseInventory(Player player){
        int i =0;
        for (String s : player.playerData.inventory){
            if( s==null){
                i++;
                continue;
            }
            player.inventory[i] = stringToObject(s,player);
            i++;
        }
        
        String curWeapon = player.playerData.curWeapon;
        if(curWeapon !=null){ //load the weapon stuff if neccessary
            player.curWeapon = (Weapon)stringToObject(curWeapon,player);
        }
        String curHead = player.playerData.curHead;
        if(curHead !=null){ //load the weapon stuff if neccessary
            player.curHead = stringToObject(curHead,player);
        }
        String curChest = player.playerData.curChest;
        if(curChest !=null){ //load the weapon stuff if neccessary
            player.curChest = stringToObject(curChest,player);
        }
        String curLegs = player.playerData.curLegs;
        if(curLegs !=null){ //load the weapon stuff if neccessary
            player.curLegs = stringToObject(curLegs,player);
        }
    }
    
    public Equipment stringToObject(String s, Player player){
        if(s==null) return null;
        if (s.equals("knife")){
            return new Knife(player);
        }else if(s.equals("snipergun")){
            return new SniperGun(player);
        }else if(s.equals("machinegun")){
            return new MachineGun(player);
        }else if(s.equals("arcanemissiles")){
            return new ArcaneMissiles(player);
        }else if(s.equals("copperhelmet")){
            return new CopperHelmet();
        }else if(s.equals("copperchest")){
            return new CopperChest();
        }else if(s.equals("copperlegs")){
            return new CopperLegs();
        }else if(s.equals("xxxxx")){
        }
        
        return null;
    }
}
