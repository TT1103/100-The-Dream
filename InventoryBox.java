import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InventoryBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryBox extends GUI
{
    boolean pressed =false;
    Equipment item;
    int delay;
    GreenfootImage image = new GreenfootImage("inventory_box.png");
    /**
     * Act - do whatever the InventoryBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /* if(Greenfoot.mousePressed(this)){
        pressed = true;
        }else{
        pressed=false;
        }*/

        MouseInfo mi = Greenfoot.getMouseInfo();
        if(mi !=null){
            if(mi.getX() > getX()-24 && mi.getX() < getX()+24 && mi.getY() >getY()-24 && mi.getY()< getY()+24 && Greenfoot.mouseClicked(null)){
                pressed =true;
            }else{
                pressed =false;
            }
        }else{
            pressed =false;
        }
    }

    public void placeItem(Equipment item){
        if(item == null) return;
        this.item =item;
        item.setImage(item.itemImage);
        //item.setImage(new GreenfootImage("nothing.png"));
        //getImage().drawImage(item.itemImage,0,0);
        getWorld().addObject(item, getX(), getY());
        item.setLocation(getX(),getY());
    }

    public Equipment grabItem(){
        Equipment temp = item;
        item = null;
        return temp;
    }
}
