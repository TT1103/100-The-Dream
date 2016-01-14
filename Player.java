import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
/**
 * The player class contains all the functionality of the object that the user controls in battle.
 * 
 * @author Tiger Zhao 
 * @version January 13, 2016
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
    
    int maxLevel =99;
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
    Equipment curHead,curChest,curLegs, curMisc ;

    boolean lvUp = false;
    int maxHpRecoverDelay=75-(defense/2);
    int hpRecoverDelay =maxHpRecoverDelay;
    
    int maxManaRegenDelay =15;
    int manaRegenDelay = maxManaRegenDelay;
    
    Equipment[] inventory = new Equipment[98];
    
    int curGameLevel =1;
    
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
        /*
        addToInventory(new IronHelmet());
        addToInventory(new IronChest());
        addToInventory(new IronLegs());
        
        addToInventory(new CarbonHelmet());
        addToInventory(new CarbonChest());
        addToInventory(new CarbonLegs());
        
        addToInventory(new ArcaneExplosion(this));
        addToInventory(new ArcaneLaser(this));
        
        addToInventory(new RocketLauncher(this));
        addToInventory(new Sword(this));
        addToInventory(new DeathSword(this));
        */
        addToInventory(new SniperGun(this));
    }

    /**
     * Constructor used for creating a player with existing player save data.
     */
    public Player(PlayerData playerData){
        this.playerData = playerData;
        //load the data...
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
        if(curWeapon !=null){
            curWeapon.act();
        }
        
        if(knockback){
            int originalRotation = getRotation();
            setRotation(knockbackRotation);
            move(knockbackStrength);
            setRotation(originalRotation);

            knockbackStrength--;
            if (knockbackStrength ==0){
                knockback = false;
            }
        }
        if(hpRecoverDelay >0){
            hpRecoverDelay--;
            if (hpRecoverDelay==0){
                hpRecoverDelay = maxHpRecoverDelay;
                if(curHealth < maxHealth) curHealth++;
            }
        }
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
    }    

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

    public void knockback(int str, int rotation){
        knockbackStrength =str;
        int knockbackRotation = rotation;
    }

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

    public void controlWeapons(){
        if (Greenfoot.mousePressed(null)){
            attacking = true;
        }else if (Greenfoot.mouseClicked(null)){
            attacking = false;
        }

        if(attacking&&knockback==false && curWeapon !=null){
            curWeapon.use();
        }
    }

    public void controlExp(){
        while(curExp>maxExp){
            curExp-=maxExp;
            maxExp*=expRatio;
            levelUp();
        }
    }

    public void gainExp(int amount){
        curExp +=amount;
    }

    public void levelUp(){
        curStatPoints+=4;
        curLevel++;
        //display level up stuff
        Text t = new Text(150,"Leveled up!");
        getWorld().addObject(t, getX(),getY()-20);
        lvUp = true;
    }

    public void saveData(){
        playerData.saveData(this);
    }
    
    public void addToInventory(Equipment item){
        for (int i=0; i < inventory.length; i++){
            if (inventory[i] == null){
                inventory[i] = item;
                return;
            }
        }
    }

    public void updateStats(){
        maxHealth = endurance*50;
        maxMana =spirituality *20;
        maxManaRegenDelay = 15 - (intelligence/8);
        maxHpRecoverDelay=75-(defense/2);
        speed = 2+ (dexterity/49);
    }
    
    public void reduceMana(int amount){
        curMana -=amount;
        if(curMana<0){
            curMana =0;
        }
    }
}
