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

    int time =999;
    Marker marker = new Marker(this);
    public Bullet(int speed, int damage){
        markerX=Greenfoot.getMouseInfo().getX();
        markerY=Greenfoot.getMouseInfo().getY();
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
            getWorld().addObject(marker, markerX, markerY);

            // 
            //             getX=(double)getX();
            //             getY=(double)getY();
            //           
            //             turnTowards(markerX, markerY);
            //             double h=Math.sqrt(Math.pow(markerY-getY, 2)+Math.pow(markerX-getX, 2));
            //             x0=(markerX-getX)*speed/h;
            //             y0=(markerY-getY)*speed/h;
            start=false;
        }

        //         //getX=getX+x0;
        //         //getY=getY+y0;
        //         setLocation(getX()+((int)x0),getY()+((int)y0));
        if(getWorld().getObjects(Marker.class).contains(marker)){
            turnTowards(marker.getX(), marker.getY());
        }
        move(speed);
        detectCollision();
        time--;
        if(time <0){
            getWorld().removeObject(this);
            return;
        }
        getImage().setTransparency(255);
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
    }
}
