import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * The level selector is a screen that lets the player choose which levels to play.
 * This also loads player data.
 * 
 * @author Tiger Zhao, Gary Yu
 * @version (a version number or a date)
 */
public class LevelSelector extends World {
    String[] completedLevels;
    Player player; //Stores the player stuff

    // Buttons for level selection
    private LevelSelectorButtons one = new LevelSelectorButtons("Level1");
    private LevelSelectorButtons two = new LevelSelectorButtons("Level2");
    private LevelSelectorButtons three = new LevelSelectorButtons("Level3");

    boolean newGame = false; //if it is a new game or not
    int curGameLevel=1;
    Button resetButton; //button to start a new game and delete current game data
    GreenfootSound music = new GreenfootSound("titlescreen_music.mp3");
    
    Text markText =  new Text("Computer Science Mark: ",25, Color.WHITE);
    int mark =0;
    /**
     * Default constructor for objects of class LevelSelector.
     * 
     */
    public LevelSelector()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        GreenfootSound effect = new GreenfootSound("titlescreen_music.mp3");
        music.setVolume(75);
        music.playLoop();
        setup();
    }

    /**
     * Contructor used to allow for music to flow.
     * Used when transitioning from the title screen.
     */
    public LevelSelector(GreenfootSound music){ 
        super(800, 800, 1,false); 
        this.music = music;
        setup();
    }

    /**
     * Setsup the neccessary objects.
     */
    public void setup(){
        // Add all the level selector buttons 
        addObject (one, 345, 478);
        addObject (two, 525, 478);
        addObject (three, 702, 478);

        newGame = !loadData(); //load the player data and determine if it is a new game
        
        addObject(markText,550,750);
        if (curGameLevel ==1){
            mark =0;
        }else if(curGameLevel==2){
            mark = 30;
        }else if(curGameLevel==3){
            mark = 65;
        }else{
            mark =100;
        }
        markText.setText("Your Computer Science Mark: "+String.valueOf(mark)+"%");
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
        }

        if(level > 0){
            music.stop();
            if (newGame){
                Greenfoot.setWorld (new Map(level));
            }else{
                Greenfoot.setWorld (new Map(level,player));
            }
        }
    }
    
    /**
     * Loads the previously saved data if it exists.
     * 
     * @return A boolean indicating whether or not it is a new game.
     */
    public boolean loadData(){ 
        File file = new File(System.getProperty("user.home") + "/Desktop/player_data.txt");
        
        if(!file.exists()){ //there has been no save data
            return false; //load data failed
        } 
        
        try{ //there is save data, now read it
            FileInputStream fileIn = new FileInputStream(file);//("data/player_data.txt");
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
    
    /**
     * Used to parse the player's inventory into the corresponding objects.
     * 
     * @param The player object of which to parse the inventory of.
     */
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

    /**
     * A helper method for parseInventory()
     * 
     * @param s A string indicating the Equipment object.
     * @param player The player object of which to parse the inventory of.
     * 
     * @return An Equipment object corresponding to a string.
     */
    public Equipment stringToObject(String s, Player player){
        if(s==null) return null;
        if (s.equals("knife")){
            return new Knife(player);
        }else if(s.equals("sword")){
            return new Sword(player);
        }else if(s.equals("deathsword")){
            return new DeathSword(player);
        }else if(s.equals("snipergun")){
            return new SniperGun(player);
        }else if(s.equals("machinegun")){
            return new MachineGun(player);
        }else if(s.equals("rocketlauncher")){
            return new RocketLauncher(player);
        }else if(s.equals("arcanemissiles")){
            return new ArcaneMissiles(player);
        }else if(s.equals("arcaneexplosion")){
            return new ArcaneExplosion(player);
        }else if(s.equals("arcanelaser")){
            return new ArcaneLaser(player);
        }else if(s.equals("copperhelmet")){
            return new CopperHelmet();
        }else if(s.equals("copperchest")){
            return new CopperChest();
        }else if(s.equals("copperlegs")){
            return new CopperLegs();
        }else if(s.equals("ironhelmet")){
            return new IronHelmet();
        }else if(s.equals("ironchest")){
            return new IronChest();
        }else if(s.equals("ironlegs")){
            return new IronLegs();
        }else if(s.equals("carbonhelmet")){
            return new CarbonHelmet();
        }else if(s.equals("carbonchest")){
            return new CarbonChest();
        }else if(s.equals("carbonlegs")){
            return new CarbonLegs();
        }
        return null;
    }
}
