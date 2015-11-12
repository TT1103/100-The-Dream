import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
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
    int speed =3;
    boolean knockback=false;
    PlayerHealth healthBar;
    P90 p90 = new P90();
    Barrett sniper = new Barrett();
    boolean shooting =false;
    int knockbackDelay=5;
    int knockbackStrength;
    int knockbackRotation;
    
    public void setup(){
        getWorld().addObject(p90,-100,-100);
        getWorld().addObject(sniper,-100,-100);
        healthBar=new PlayerHealth(1000, this);
        getWorld().addObject(healthBar, 180, 30);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

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
    }    

    public void damage(int damage){
        healthBar.damage(damage);
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
            shooting = true;
        }else if (Greenfoot.mouseClicked(null)){
            shooting = false;
        }

        if(shooting&&knockback==false){
            p90.use(getX(),getY());
            //sniper.use(getX(),getY());
        }
    }
}
