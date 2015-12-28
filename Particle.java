import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle extends Actor
{
    int speed;
    int size;
    boolean noImage;
    boolean generated = false;
    boolean multiple =false;
    int number;
    boolean paused =false;
    public Particle( int size ,int angle){ //make a simple particle
        turn(angle);
        
        getImage().scale(Greenfoot.getRandomNumber(5)+size,Greenfoot.getRandomNumber(5)+size);
    }
    
   
    
    public Particle(int speed, int size, int number){//default 10 particles
        multiple =true;
        this.size =size;
        this.number =number;
        this.speed=speed/2;
        getImage().scale(Greenfoot.getRandomNumber(10)+size,Greenfoot.getRandomNumber(10)+size);
    }
    
    
    public void act() 
    {
        if(paused){
            return;
        }
        if(!generated && multiple){
            generated =true;
            for(int i =0 ; i <number; i++){
                Particle p = new Particle( size, (int)((i/(double)number)*360) );
                p.speed = (Greenfoot.getRandomNumber(speed*2)) /2;
                getWorld().addObject(p, getX(), getY());
            }
        }else if (!multiple){
            if(noImage){
                //set particle image depending on object
                noImage=false;
            }
            if(speed>0){
                move(speed);
                speed--;//particle spreads and decelerates
                
                
            }
            if(getImage().getTransparency()!=0){
                getImage().setTransparency(getImage().getTransparency()-1);//fade out
            }else{
                getWorld().removeObject(this);
            }
        }else{
            getWorld().removeObject(this);
        }
        
        
        
    }    
}