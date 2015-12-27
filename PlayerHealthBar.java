import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class PlayerHealth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerHealthBar extends UnScrollable
{
    boolean start=true;
    int health;
    int maxHealth;

    Text text;
    int xSize =350;
    int ySize =20;
    
    
    public PlayerHealthBar(int curHealth, int maxHealth){
        
        GreenfootImage bar = new GreenfootImage(xSize, ySize);
        bar.setColor(Color.GREEN);
        bar.fillRect(0,0, (int)( xSize*((double)curHealth/maxHealth)), getImage().getHeight());
        setImage(bar);
        this.maxHealth=maxHealth;
        this.health = curHealth;
        text=new Text(String.valueOf(health) +"/"+ String.valueOf(maxHealth));
    }

    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(start){
            getWorld().addObject(text,getX(),getY());
            start=false;
        }
        //getImage().scale(350, 20);
 
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0,0,getImage().getWidth()-1, getImage().getHeight()-1);

        
    }   

    public void updateHealth(int newCurHealth, int newMaxHealth){
        maxHealth = newMaxHealth;
        health=newCurHealth;
        if(health <0){
            health =0;
        }
        getImage().setTransparency(0);

        GreenfootImage newbar = new GreenfootImage(getImage().getWidth(), getImage().getHeight());
        setImage(newbar);
        newbar.setColor(Color.GRAY);
        newbar.fillRect(0,0, xSize, ySize);
        
        if (health <= 0.25*maxHealth){
            newbar.setColor(Color.RED);
        }else if (health < 0.6*maxHealth){
            newbar.setColor(Color.YELLOW);
        }else{
            newbar.setColor(Color.GREEN);
        }
        newbar.fillRect(0,0, (int)(xSize * ((double)health/maxHealth)), getImage().getHeight());
        text.setText(String.valueOf(health) +"/"+ String.valueOf(maxHealth));
        //getWorld().showText(String.valueOf(health),getX(),getY());

    }

    public int getHealth () {
        return this.health;
    }
}
