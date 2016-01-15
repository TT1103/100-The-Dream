import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to model an enemy laser attack.
 * 
 * @author Tiger Zhao
 * @version January 10, 2016
 */
public class EnemyLaser extends EnemyBullet
{
    public EnemyLaser(int speed, int damage){
        super(speed, damage);
    }
    public EnemyLaser(int speed, int damage,int x, int y){
        super(speed, damage,x,y);
    }
    /**
     * Act - do whatever the EnemyLaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public  void detectCollision(){
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
