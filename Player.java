import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends UnScrollable implements Serializable
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

    boolean moving =false;
    String direction ="";
    int speed =2;
    boolean knockback=false;
    
    
    P90 p90 = new P90();
    Barrett sniper = new Barrett();
    Knife knife = new Knife();
    boolean shooting =false;
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
    int intelligence =10; //affects damage of mana weapons like spells
    int dexterity =10; //affects damage melee weapons like swords
    int defense =5; //affects damage reduction
    int endurance =10; //affects hp
    int spirituality =10; //affects mana
    
    int maxHealth = endurance*50;
    int curHealth =maxHealth;
    
    int maxMana =spirituality *20;
    int curMana =maxMana;
    
    ArrayList<Weapon> weapons=new ArrayList<Weapon>();
    
    PlayerData playerData;
    
    //PlayerHealth healthBar;
    HUD hud;
    
    
    
    public Player(){
        playerData=new PlayerData();
    }
    
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
        
        maxHealth = playerData.maxHealth;
        maxMana = playerData.maxMana;
        curHealth = maxHealth;
        curMana = maxMana;
    }
    
    public void setup(){
        getWorld().addObject(p90,-100,-100);
        getWorld().addObject(sniper,-100,-100);
        getWorld().addObject(knife,-100,-100);
        //healthBar=new PlayerHealth(curHealth,maxHealth, this);
        //getWorld().addObject(healthBar, 180, 30);
        hud = new HUD(this);
        getWorld().addObject(hud, 0, 0);
        
        weapons.add(p90);
        weapons.add(sniper);
        weapons.add(knife);
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
        controlMovement();
        controlWeapons();
        controlExp();
    }    

    public void damage(int damage){
        //healthBar.damage(damage);
        curHealth -= (damage - defense/3);
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
       // if(particleNumber < 
        Particle par = new Particle(particleSpeed,particleSize,particleNumber); //5
        getWorld().addObject(par,getX(), getY());
    }

    public void knockback(int str, int rotation){
        knockbackStrength =str;
        int knockbackRotation = rotation;
    }

    public void setLocation(int x, int y) {
        if (getWorld().getObjectsAt(x, y, Impassable.class).isEmpty()) {
            //if(x <0 || x >800 || y <0 || y >800){
            //    return;
            //}
            super.setLocation(x, y);
        }
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
    
    boolean weaponswitch=false;
    int weaponindex=0;
    
    public void controlWeapons(){

        if (Greenfoot.mousePressed(null)){
            shooting = true;
        }else if (Greenfoot.mouseClicked(null)){
            shooting = false;
        }

        //for weapon switching!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Needs some editing
        if(weaponswitch==false){
            if(Greenfoot.isKeyDown("q")){
                weaponswitch=true;
                weaponindex++;
                if(weaponindex >= weapons.size()){
                    weaponindex = 0;
                }
            }else if(Greenfoot.isKeyDown("e")){
                weaponswitch=true;
                weaponindex--;
                if(weaponindex <0){
                    weaponindex = weapons.size()-1;
                }
            }
        }
        if(!Greenfoot.isKeyDown("q") && !Greenfoot.isKeyDown("e")){
            weaponswitch=false;
        }

        
        if(shooting&&knockback==false){
            weapons.get(weaponindex).use(this);
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
        //display level up stuff
        Text t = new Text(150,"Leveled up!");
        getWorld().addObject(t, getX(),getY()-20);
        curHealth = maxHealth;
        
    }
    
    public void saveData(){
        playerData.saveData(this);
    }
    
    public void updateStats(){
        maxHealth = endurance*50;
        maxMana =spirituality *20;
    }
}
