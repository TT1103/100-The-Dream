import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Enemy
{
    public Dog(int level){

        super(level);
        idleSprite = new GreenfootImage("dog_idle.png");
        speed =4;
        delay=21;
        maxDelay=15;
        movingSprites = new GreenfootImage[4];
        movingSprites[0] = new GreenfootImage("dog_moving1.png");
        movingSprites[1] = new GreenfootImage("dog_moving2.png");
        movingSprites[2] = new GreenfootImage("dog_moving3.png");
        movingSprites[3] = new GreenfootImage("dog_moving4.png");
        idleSprite = new GreenfootImage("dog_idle.png");

        damageDelay = 8;
        maxDamageDelay = damageDelay;
        damage =10;
        healthBar = new HealthBar(level*100, this);

    }
    
    public Dog(){

        super(1);
        idleSprite = new GreenfootImage("dog_idle.png");
        speed =4;
        delay=21;
        maxDelay=15;
        movingSprites = new GreenfootImage[4];
        movingSprites[0] = new GreenfootImage("dog_moving1.png");
        movingSprites[1] = new GreenfootImage("dog_moving2.png");
        movingSprites[2] = new GreenfootImage("dog_moving3.png");
        movingSprites[3] = new GreenfootImage("dog_moving4.png");
        idleSprite = new GreenfootImage("dog_idle.png");

        damageDelay = 8;
        maxDamageDelay = damageDelay;
        damage =10;

    }

    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        

        if(damageDelay >0){
            damageDelay--;
        }

    }    

    public void controlMovement(){
        moving =true;
        boolean seePlayer = canSeePlayer();
        if(moving){
            if (delay%7==0){
                setImage(movingSprites[delay/7]);
            }
            delay--;
        }

        if(delay ==0){
            moving = false;
            delay = maxDelay;
            setImage(idleSprite);
        }

        if(seePlayer){
            List l = getWorld().getObjects(Player.class);
            Player p = (Player) l.get(0);
            turnTowards(p.getX(),p.getY());
            move(speed);
        }else{
           // moveToDefault();
        }

    }

    
    
    public void controlWeapons(){
        List<Player> p = getIntersectingObjects(Player.class);
        if (!p.isEmpty() && damageDelay ==0){

            Player player = (Player) p.get(0);
            player.damage(damage);
            
            if(player.knockback==false){
                player.knockback=true;
                player.knockbackStrength=10;
                player.knockbackRotation=getRotation();
            }
            damageDelay = maxDamageDelay;
        }
    }
}
