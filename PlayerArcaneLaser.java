import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class PlayerArcaneLaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerArcaneLaser extends PlayerProjectile
{
    Enemy target;
    public PlayerArcaneLaser(int speed, int damage){
        super(speed,damage);
    }
    /**
     * Act - do whatever the PlayerArcaneLaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if(paused){
            return;
        }
        if(start){
            Player player = (Player)getWorld().getObjects(Player.class).get(0);
            markerX = Greenfoot.getMouseInfo().getX();
            markerY = Greenfoot.getMouseInfo().getY();
            if(Math.sqrt(Math.pow(player.getX() - markerX, 2) + Math.pow(player.getY() - markerY, 2)) > speed * 2){
                getWorld().addObject(marker, markerX, markerY);
            }else{
                setRotation(player.getRotation());
            }
           
            start=false;
        }
        

        if(getWorld().getObjects(Marker.class).contains(marker)){
            turnTowards(marker.getX(), marker.getY());
        }
        List<Enemy> enemies = getObjectsInRange(400,Enemy.class);
        if(enemies.size()>0 && target ==null){
            Enemy e = enemies.get(0);
            if(e!=null){
                if(e.pointsMeet(getX(),getY(),e.getX(),e.getY())){
                    turnTowards(e.getX(), e.getY());
                    target=e;
                }
            }
        }else if(target !=null){
            turnTowards(target.getX(), target.getY());
        }

        move(speed);
        getImage().setTransparency(255);
        if(isTouching(Player.class)){
            getImage().setTransparency(0);
        }
        this.detectCollision();
        time--;
        
        if(time <0){
            getWorld().removeObject(this);
            return;
        }
    }   
    
   
}
