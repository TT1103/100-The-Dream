import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spawner extends Actor
{
    int spawn;
    int x = 50;
    int level = 0;
    boolean spawning = false;
    public Spawner(int spawn){
        this.spawn = spawn;//this determines what specific enemy will spawn. 0 = dogs, 1 = gunmen
        getImage().scale(100, 100);
    }

    /**
     * Act - do whatever the Spawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turn(-20);
        if(spawning){
            if(x-- == 0 || x == 25){
                GreenfootSound effect = new GreenfootSound("spawn_effect.wav");
                effect.setVolume(20);
                effect.play();
                switch(spawn){
                    case 0 : //spawn dogs
                    getWorld().addObject(new Dog(level), getX(), getY());
                    break;

                    case 1 : //spawn gunmen
                    getWorld().addObject(new Gunman(level), getX(), getY());
                    break;
                }
            }
        }
    }

    public void activate(int level){
        this.level = level;
        spawning = true;
        x = 50;
        GreenfootSound effect = new GreenfootSound("spawn_effect.wav");
        effect.setVolume(20);
        effect.play();
        switch(spawn){

            case 0 : //spawn dogs
            getWorld().addObject(new Dog(level), getX(), getY());
            break;

            case 1 : //spawn gunmen
            getWorld().addObject(new Gunman(level), getX(), getY());
            break;
        }
    }
}