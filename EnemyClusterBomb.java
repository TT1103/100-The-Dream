import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * A class representing an enemy projectile weapon.
 * Cluster bombs are different from bullets.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
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
    public void act(){
        if(isTouching(PlayerProjectile.class)){
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
