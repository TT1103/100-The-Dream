import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class EnemyClusterBomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyClusterBomb extends EnemyBullet
{
    public EnemyClusterBomb(int speed, int damage, int x, int y){
        super(speed, damage, x, y);
    }
    /**
     * Act - do whatever the EnemyClusterBomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isTouching(Bullet.class)){
            Random r= new Random();
            for(int a = 0; a < 10; a++){
                int x = r.nextInt(getWorld().getWidth());
                int y = r.nextInt(getWorld().getHeight());
                getWorld().addObject(new EnemyBullet(10, 50, x, y), getX(), getY());
            }
            getWorld().removeObject(this);
            return;
        }
        super.act();
        
        
        
    }    
    
    public void detectCollision(){
        Player player =(Player) getOneIntersectingObject(Player.class);
        if (player != null){
            player.damage(damage);
            if(player.knockback==false){
                player.knockback=true;
                player.knockbackStrength=5;
                player.knockbackRotation=getRotation();
            }
            getWorld().removeObject(this);
            return;
        }
    }
}
