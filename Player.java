import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
import java.awt.Color;

/**
 * The player class contains all the functionality of the object that the user controls in battle.
 * 
 * @author Tiger Zhao 
 * @version January 14, 2016
 */
public class Player extends Actor implements Serializable
{
    int screenX= 800;
    int screenY= 800;
    
    GreenfootImage[] walkingSprites = {
            new GreenfootImage("walking1.png"),
            new GreenfootImage("walking2.png"),
            new GreenfootImage("walking3.png"),
            new GreenfootImage("walking4.png"),
            new GreenfootImage("walking5.png"),
            new GreenfootImage("walking6.png"),
        };

    int delay = 25;
    int maxDelay = 25;
    GreenfootImage idleSprite = new GreenfootImage("idle.png");

    int maxMoveDelay =5;
    int moveDelay =maxMoveDelay;
    boolean moving =false;
    String direction ="";
    int speed =2;
    boolean knockback=false;

    MachineGun machinegun = new MachineGun(this);
    SniperGun sniper = new SniperGun(this);
    Knife knife = new Knife(this);
    boolean attacking =false;
    int knockbackDelay=5;
    int knockbackStrength;
    int knockbackRotation;
    
    int maxLevel =100;
    int curLevel =1;
    int curStatPoints =0;

    int curExp =0;
    int maxExp =100; //exp needed to level up
    double expRatio =1.2; //how many more times exp needed to level up the next time

    int precision =10; //affects damage of ranged weapons like guns
    int intelligence =10; //affects damage of mana weapons like spells and mana regen
    int dexterity =10; //affects damage melee weapons like swords and movement speed
    int defense =5; //affects damage reduction and health regen
    int endurance =10; //affects hp
    int spirituality =10; //affects mana

    int maxHealth = endurance*50;
    int curHealth =maxHealth;

    int maxMana =spirituality *20;
    int curMana =maxMana;

    ArrayList<Weapon> weapons=new ArrayList<Weapon>();

    PlayerData playerData;

    HUD hud;

    Weapon curWeapon;
    Equipment curHead,curChest,curLegs;

    boolean lvUp = false;
    int maxHpRecoverDelay=75-(defense/2);
    int hpRecoverDelay =maxHpRecoverDelay;
    
    int maxManaRegenDelay =15;
    int manaRegenDelay = maxManaRegenDelay;
    
    Equipment[] inventory = new Equipment[98];
    
    int curGameLevel =1; //the game progress

    boolean curse = false;

    
    boolean paused = false;
    
    /**
     * Default constructor for the player. 
     * Starts a new player with new data.
     */

    public Player(){
        playerData=new PlayerData();
    }
    
    /**
     * Loads the default items into the player's inventory.
     */
    public void setDefaults(){
        addToInventory(new Knife(this));
        addToInventory(new MachineGun(this));
        addToInventory(new ArcaneMissiles(this));
        addToInventory(new CopperHelmet());
        addToInventory(new CopperChest());
        addToInventory(new CopperLegs());
    }

    /**
     * Constructor used for creating a player with existing player save data.
     */
    public Player(PlayerData playerData){
        this.playerData = playerData;
        //load the data:
        curLevel = playerData.curLevel;
        curStatPoints =playerData.curStatPoints;

        curExp =playerData.curExp;
        maxExp =playerData.maxExp; 
        precision =playerData.precision; 
        intelligence =playerData.intelligence; 
        dexterity =playerData.dexterity; 
        defense =playerData.defense; 
        endurance =playerData.endurance; 
        spirituality =playerData.spirituality; 
        curGameLevel = playerData.curGameLevel;
        maxHpRecoverDelay = playerData.maxHpRecoverDelay;
        maxManaRegenDelay=playerData.maxManaRegenDelay;
        maxHealth = playerData.maxHealth;
        maxMana = playerData.maxMana;
        curHealth = maxHealth;
        curMana = maxMana;
        speed = playerData.speed;
    }

    /**
     * Used to add neccessary objects.
     */
    public void setup(){
        hud = new HUD(this);
        getWorld().addObject(hud, 0, 0);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        
        
        
        if(knockback){ //manage knockback
            int originalRotation = getRotation();
            setRotation(knockbackRotation);
            move(knockbackStrength);
            setRotation(originalRotation);

            knockbackStrength--;
            if (knockbackStrength ==0){
                knockback = false;
            }
        }
        
        //regenerate health over time
        if(hpRecoverDelay >0){
            hpRecoverDelay--;
            if (hpRecoverDelay==0){
                hpRecoverDelay = maxHpRecoverDelay;
                if(curse){
                    curHealth -= 4;
                }
                else if(curHealth < maxHealth){ 
                    curHealth++;
                }
                if(curHealth<0){
                    curHealth =0;
                }
            }
        }
        
        //regenerate mana over time
        if(manaRegenDelay >0){
            manaRegenDelay--;
            if(manaRegenDelay==0){
                manaRegenDelay = maxManaRegenDelay;
                if(curMana<maxMana){
                    if(curWeapon ==null || !curWeapon.damageType.equals("magic")){
                        curMana++;
                    }else if(!attacking){
                        curMana++;
                    }
                }
            }
        }
        
        //used to create a regeneration effect after leveling up:
        if(lvUp){
            if(curHealth < maxHealth){
                curHealth+=53;
            }
            if(curHealth>maxHealth){
                curHealth = maxHealth;
            }
            if(curMana < maxMana){
                curMana+=23;
            }
            if(curMana>maxMana){
                curMana = maxMana;
            }
            if(curMana >=maxMana && curHealth >=maxHealth){
                lvUp=false;
            }
        }
        controlMovement();
        controlWeapons();
        controlExp();
        
        //cheat to get a powerful weapon to beat the game for testing purposes
        if(Greenfoot.isKeyDown("i") && Greenfoot.isKeyDown("o") && Greenfoot.isKeyDown("p")){
            boolean added = false; //see if the dev weapon already exists
            for (Equipment e : inventory){
                if(e!=null &&e.name.equals("devweapon")){
                    added = true;
                }
            }
            if(curWeapon != null && curWeapon.name.equals("devweapon")){
                added=true;
            }
            if(!added) {
                addToInventory(new DevWeapon(this));
                getWorld().addObject(new Text("You have cheated!\nA powerful weapon has been added to your inventory.",25,Color.WHITE),400,600);
            }
            
        }
    }    

    /**
     * Used to deal damage to the player and spawn blood particles.
     * 
     * @param damage An integer indicating the amount of damage to deal.
     */
    public void damage(int damage){
        int totalDefense =defense;
        if (curHead!=null) totalDefense+=curHead.defense;
        
        if(curChest != null) totalDefense += curChest.defense;
        
        if(curLegs !=null)totalDefense+=curLegs.defense;
        
        damage-= (totalDefense)/2;
        if(damage<1) damage=1;
        
        curHealth-=damage;
        if(curHealth <0){
            curHealth =0;
        }
        
        //generate particles
        int particleSpeed=damage/2;
        int particleSize = damage/10;
        int particleNumber = damage/2;
        if(particleSpeed<15){
            particleSpeed=15;
        }
        if(particleSpeed > 30){
            particleSpeed =30;
        }
        if(particleSize <5){
            particleSize = 5;
        }
        if(particleNumber >10){
            particleSize = 10;
        }
        
        Particle par = new Particle(particleSpeed,particleSize,particleNumber); //5
        getWorld().addObject(par,getX(), getY());
    }

    /**
     * Used to cause knockback on the player.
     * 
     * @param str An integer indicating the strength of the knockback.
     * @param rotation An integer indicating the direction of the knockback as a rotation.
     */
    public void knockback(int str, int rotation){
        knockbackStrength =str;
        int knockbackRotation = rotation;
    }

    /**
     * Used to set the location of the player. Makes sure to prevent the player from moving through
     * impassable objects.
     * 
     * @param x An integer indicating the x position to move to.
     * @param y An integer indicating the y position to move to.
     */
    public void setLocation(int x, int y) {
        if (getWorld().getObjectsAt(x, y, Impassable.class).isEmpty()) {
            super.setLocation(x, y);
        }
    }
    
    public void move(int speed){
        double squareX =  (Math.cos(Math.toRadians(getRotation())));
        double squareY =   (Math.sin(Math.toRadians(getRotation())));
        int circleX = (int) (speed*squareX * Math.sqrt(1- 0.5* Math.pow(squareY,2)));
        int circleY = (int) (speed*squareY * Math.sqrt(1- 0.5* Math.pow(squareX,2)));
        
        setLocation(getX()+circleX, getY()+circleY);
    }

    /**
     * Used to manage the user's control of the player.
     */
    public void controlMovement(){
        moving =false;
        if(knockback==false){
            if (Greenfoot.isKeyDown("w")){
                moving = true;
                direction = "up";
                setLocation(getX(), getY()-speed);
            } if (Greenfoot.isKeyDown("a")){
                moving = true;
                direction = "left";
                setLocation(getX()-speed, getY());
            } if (Greenfoot.isKeyDown("s")){
                moving = true;
                direction = "down";
                setLocation(getX(), getY()+speed);
            } if (Greenfoot.isKeyDown("d")){
                moving = true;
                direction = "right";
                setLocation(getX()+speed, getY());
            }
        }

        if(moving){
            if (delay%5==0){
                setImage(walkingSprites[delay/5]);
            }
            delay--;
        }

        if (!moving){
            setImage(idleSprite);
            delay=maxDelay;
        }

        if (delay ==0){
            moving =false;
            delay =maxDelay;
        }

        MouseInfo mi = Greenfoot.getMouseInfo();
        if(mi != null){
            int mX= mi.getX();
            int mY = mi.getY();
            int pX= getX();
            int pY=getY();
            turnTowards(mX,mY);
        }
    }

    /**
     * Used to manage the user's control of the weapon and attacks.
     */
    public void controlWeapons(){
        if(curWeapon !=null){ 
            curWeapon.act();
        }
        if (Greenfoot.mousePressed(null)){
            attacking = true;
        }else if (Greenfoot.mouseClicked(null)){
            attacking = false;
        }

        if(attacking&&knockback==false && curWeapon !=null){
            curWeapon.use();
        }
    }

    /**
     * Used to manage the player's experience and determine if 
     * the player has leveled up.
     */
    public void controlExp(){
        while(curExp>maxExp){
            if(curLevel<maxLevel){
                curExp-=maxExp;
                maxExp*=expRatio;
                levelUp();
            }else{
                curExp = maxExp;
                break;
            }
        }
    }

    /**
     * Used to add experience to the player.
     * 
     * @param amount An integer indicating the amount of experience to give to the player.
     */
    public void gainExp(int amount){
        curExp +=amount;
    }

    /**
     * Levels up the player.
     */
    public void levelUp(){
        curStatPoints+=4;
        curLevel++;
        //display level up stuff
        Text t = new Text(50,"Leveled up!");
        getWorld().addObject(t, getX(),getY()-20);
        lvUp = true;
    }

    /**
     * Used to save the player object's data.
     */
    public void saveData(){
        playerData.saveData(this);
    }
    
    /**
     * Used to add an Equipment object to the player's inventory.
     * 
     * @param item The Equipment object to add.
     */
    public void addToInventory(Equipment item){
        for (int i=0; i < inventory.length; i++){
            if (inventory[i] == null){
                inventory[i] = item;
                return;
            }
        }
    }

    /**
     * Used to update the player's stats based on the allocated stat points.
     */
    public void updateStats(){
        maxHealth = endurance*50;
        maxMana =spirituality *20;
        maxManaRegenDelay = 15 - (intelligence/8);
        maxHpRecoverDelay=75-(defense/2);
        speed = 2+ (dexterity/49);
        
        if(speed>4){
            speed =4;
        }
        if(maxManaRegenDelay <1){
            maxManaRegenDelay=1;
        }
        if(maxHpRecoverDelay <1){
            maxHpRecoverDelay =1;
        }
    }
    
    /**
     * Used to subtract mana from the mana bar.
     * 
     * @param amount An integer indicating the amount of mana to reduce.
     */
    public void reduceMana(int amount){
        curMana -=amount;
        if(curMana<0){
            curMana =0;
        }
    }
}
