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
    
    
    int time =999;
    
    public Bullet(int speed, int damage){
        markerX=Greenfoot.getMouseInfo().getX();
        markerY=Greenfoot.getMouseInfo().getY();

        this.speed=speed;
        this.damage=damage;
    }

    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(start){

            getX=(double)getX();
            getY=(double)getY();
          
            turnTowards(markerX, markerY);
            double h=Math.sqrt(Math.pow(markerY-getY, 2)+Math.pow(markerX-getX, 2));
            x0=(markerX-getX)*speed/h;
            y0=(markerY-getY)*speed/h;
            start=false;
        }
        //getX=getX+x0;
        //getY=getY+y0;
        setLocation(getX()+((int)x0),getY()+((int)y0));
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
            getWorld().removeObject(this);
            return;
        }
        
        Impassable wall = (Impassable) getOneIntersectingObject(Impassable.class);
        if (wall != null){
            getWorld().removeObject(this);
            return;
        }
    }
}
