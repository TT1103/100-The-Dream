import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * A class used to model enemies.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Enemy extends Actor
{
    int screenX= 800;
    int screenY= 800;
    GreenfootImage[] movingSprites;
    int delay;
    int maxDelay;
    GreenfootImage idleSprite;
    int power;//amount of damage for each hit
    boolean moving =false;
    String direction ="";
    int speed;

    boolean shooting =false;
    
    int damage;
    int damageDelay;
    int maxDamageDelay;
    
    int level=1; //highest level = better stats
    
    boolean dead =false;

 
    static HashMap<String, int[][]> graph = new HashMap<String,int[][]>();
    boolean hasPath =false;
    int[][] pathToFollow;
    boolean noPath = false;
    int curPath=0;
    boolean needPath =false;
    HealthBar healthBar;
    
    boolean paused =false;
    
    boolean start = false;
   
    /**
     * An empty constructor
     */
    public Enemy(){
    }
    
    /**
     * A constructor used to create an enemy based on its level.
     */
    public Enemy(int level){
        start = true;
        this.level=level;
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
        if(start){
            start = false;
            getWorld().addObject(healthBar, getX(),getY()+ getImage().getHeight());
        }
        controlMovement();
        controlWeapons();
        controlDeath();
        
    }    
    
    /**
     * Used to manage the enemy's death.
     */
    public void controlDeath(){
        if(dead){
            Player p = (Player) getWorld().getObjects(Player.class).get(0);
            p.gainExp(level *60);
            getWorld().removeObject(this);
        }
    }

    /**
     * Used to manage the movement.
     */
    public void controlMovement(){
    }

    /**
     * Used to manage attacks.
     */
    public void controlWeapons(){
    }

    /**
     * Used to deal damage to this enemy.
     * 
     * @param d An integer indicating the amount of damage to do.
     */
    public void damage(int d){
        healthBar.damage(d);
        if (healthBar.health<=0){
            dead =true;
        }
    }

    /**
     * Used to move the enemy more accurately and without passing through impassable objects.
     * 
     * @param speed The speed to move.
     */
    public void move(int speed){
        double squareX =  (Math.cos(Math.toRadians(getRotation())));
        double squareY =   (Math.sin(Math.toRadians(getRotation())));
        int circleX = (int) (speed*squareX * Math.sqrt(1- 0.5* Math.pow(squareY,2)));
        int circleY = (int) (speed*squareY * Math.sqrt(1- 0.5* Math.pow(squareX,2)));

        int origX=getX();
        int origY=getY();

        if (getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Player.class).isEmpty() ) {
            super.setLocation(getX()+circleX, getY()+circleY);
        }else if(getWorld().getObjectsAt(getX() +circleX, getY(), Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() +circleX, getY(), Player.class).isEmpty()) { //what if we only move x?
            super.setLocation(getX()+circleX, getY());
        }else if(getWorld().getObjectsAt(getX() , getY()+circleY, Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() , getY()+circleY, Player.class).isEmpty()){//what if we only move y?
            super.setLocation(getX(), getY()+circleY);
        }
    }
    
    /**
     * @return A boolean indicating if the enemy can see the player without any impassable objects blocking.
     */
    public boolean canSeePlayer(){
        Player p = (Player) getWorld().getObjects(Player.class).get(0);
        return pointsMeet(getX(),getY(),p.getX(),p.getY());
    }

    /**
     * @return A boolean indicating if the enemy can a point without any impassable objects blocking.
     * 
     * @param destX An integer indicating the x value of the point.
     * @param destY An integer indicating the y value of the point.
     */
    public boolean canSeePoint(int destX, int destY){
        return pointsMeet(getX(),getY(),destX,destY);
    }
    
    /**
     * Determines if a point can meet another point without colliding with something impassable.
     * 
     * @param startX An integer indicating the first x value.
     * @param startY An integer indicating the first y value.
     * @param destX An integer indicating the second x value.
     * @param destY An integer indicating the second y value.
     */
    public boolean pointsMeet(int startX, int startY, int destX, int destY){
        int origX = getX();
        int origY=getY();
        setLocation(startX, startY);
        int originalRotation = getRotation();
        
        this.turnTowards(destX, destY);
        int rotation = getRotation();
        
        this.setRotation(originalRotation);
        setLocation(origX, origY);
        
        int s = 10;
        double cos = Math.cos(Math.toRadians(rotation));
        double sin = Math.sin(Math.toRadians(rotation));
        double curX = startX;
        double curY = startY;
        int distance =0;
        while (distance<1000){
            curX += cos*s;
            curY += sin*s;
            distance+=Math.abs(cos*s);
            distance+=Math.abs(sin*s);
            if (Math.abs(curX-destX)<10 && Math.abs(curY-destY)<10){
                return true;
            }else if (getWorld().getObjectsAt((int) curX, (int) curY, Impassable.class).size() >0){ 
                return false;
            }
        }
        return false;
    }

    /**
     * Calculates the returns the distance to the player.
     * 
     * @return A double indicating the distance from the player.
     */
    public double distanceToPlayer(){
        Player p = (Player) getWorld().getObjects(Player.class).get(0);
        return Math.sqrt(Math.pow(getX()-p.getX(),2) + Math.pow(getY()-p.getY(),2));
    }
    
}
