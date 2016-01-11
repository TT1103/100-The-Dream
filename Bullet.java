import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    double getX;//bullet coordinates
    double getY;//bullet coordinates
    int markerX;//marker coordinates
    int markerY;//marker coordinates
    boolean start=true;
    int speed;
    int damage;
    double x0;//change in bullet coords
    double y0;//change in bullet coords
    boolean paused =false;
    
    int time =500;
    Marker marker = new Marker(this);
    public Bullet(int speed, int damage){

        getImage().setTransparency(0);
        
        this.speed=speed;
        this.damage=damage;
    }

    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
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
        move(speed);
        getImage().setTransparency(255);
        if(isTouching(Player.class)){
            getImage().setTransparency(0);
        }
        detectCollision();
        time--;
        if(time <0){
            getWorld().removeObject(this);
            return;
        }
        
    }    

    public void detectCollision(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if (enemy != null){
            enemy.damage(damage);
            if(getWorld().getObjects(Marker.class).contains(marker)){
                getWorld().removeObject(marker);
            }
            getWorld().removeObject(this);
            return;
        }

        Impassable wall = (Impassable) getOneIntersectingObject(Impassable.class);
        if (wall != null){
            if(getWorld().getObjects(Marker.class).contains(marker)){
                getWorld().removeObject(marker);
            }
            getWorld().removeObject(this);
            return;
        }
        
        Passage passage = (Passage) getOneIntersectingObject(Passage.class);
        if(passage != null){
            if(getWorld().getObjects(Marker.class).contains(marker)){
                getWorld().removeObject(marker);
            }
            getWorld().removeObject(this);
            return;
        }
    }
}
