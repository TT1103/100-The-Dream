import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Used to display inventory and stats.
 * Allows player to equip weapons, change stats and etc.
 * 
 * @author Tiger Zhao
 * @version December 28, 2015
 */
public class PlayerMenu extends Actor
{
    Player player;
    Button closeButton, exitButton;
    List<Object> obj = new ArrayList<Object>();
    Button[] addStatButtons = new Button[6];
    int statFontSize=20;
    Color statColor = Color.WHITE;
    Text[] statTexts = new Text[]{ 
        new Text("Precision",statFontSize,statColor), new Text("Intelligence",statFontSize,statColor),
        new Text("Dexterity",statFontSize,statColor), new Text("Defense",statFontSize,statColor), 
        new Text("Endurance",statFontSize,statColor), new Text("Spirituality",statFontSize,statColor),
        new Text("Stat Points",statFontSize,statColor)
    };
    Text[] statValues = new Text[7];
    
    Text defenseText;
    
    Text tooltip;
    
    InventoryBox head, chest,legs,weapon;
    InventoryBox[] boxes = new InventoryBox[98];
    boolean hasItem = false; //if the mouse is holding something
    Equipment curItem;
    
    /**
     * A constructor to create the player menu.
     * 
     * Index for stats: 
     * 0 precision
     * 1 intelligence
     * 2 dexterity
     * 3 defense
     * 4 endurance
     * 5 spirituality
     * 6 stat points
     * 
     * @param player The player object that this menu represents.
     */
    public PlayerMenu(Player player){
        this.player = player;
        closeButton = new Button("close_button.png");
        player.getWorld().addObject(closeButton, 736,14);
        
        exitButton = new Button("levelexit_button.png");
        player.getWorld().addObject(exitButton, 736,786);
        
        ((Map)player.getWorld()).pauseAll();
        displayStats();
        displayInventory();
        obj.add(closeButton);
        obj.add(exitButton);
    }
    
    /**
     * Act - do whatever the PlayerMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {

        controlStats();
        controlInventory();
        
        String recentKey = Greenfoot.getKey();
        
        if((closeButton.pressed || (recentKey!=null && recentKey.equals("e")))  && !hasItem){
            ((Map)player.getWorld()).unpauseAll();
            
            //cleanup equipment
            player.curWeapon = (Weapon)weapon.item;
            player.curHead = head.item;
            player.curChest = chest.item;
            player.curLegs = legs.item;
            weapon.item = null;
            head.item = null;
            chest.item = null;
            legs.item = null;
            
            //cleanup inventory
            for (int i =0; i < boxes.length; i++){
                player.inventory[i] = boxes[i].item;
            }
            
            getWorld().removeObjects(obj);
            getWorld().removeObject(this);
            
            return;
        }
        
        if(exitButton.pressed){
            player.saveData();
            ((Map) getWorld()).fadeOut();
            ((Map)getWorld()).music.stop();
            Greenfoot.setWorld (new LevelSelector());
        }
    }    
    
    /**
     * A method used to manage the Player's stats.
     */
    public void controlStats(){
        int i = 0;
        for (Button b: addStatButtons){
            if(b.pressed && player.curStatPoints>0){
                player.curStatPoints--;
                String newText="";
                if(i ==0){
                    player.precision++;
                    newText+=player.precision;
                }else if(i ==1){
                    player.intelligence++;
                    newText+=player.intelligence;
                }else if(i ==2){
                    player.dexterity++;
                    newText+=player.dexterity;
                }else if(i ==3){
                    player.defense++;
                    newText+=player.defense;
                }else if(i ==4){
                    player.endurance++;
                    newText+=player.endurance;
                }else if(i ==5){
                    player.spirituality++;
                    newText+=player.spirituality;
                }
                statValues[i].setText(newText);
                statValues[6].setText(String.valueOf(player.curStatPoints));
                player.updateStats();
            }
            i++;
        }
        int armorDefense =0;
        if(head.item !=null) armorDefense+=head.item.defense;
        if(chest.item !=null) armorDefense+=chest.item.defense;
        if(legs.item !=null) armorDefense+=legs.item.defense;
        defenseText.setText("Armor Defense: " +String.valueOf(armorDefense));
    }
    
    /**
     * A method used to manage the Player's inventory.
     */
    public void controlInventory(){
        if(curItem != null){
            MouseInfo mi = Greenfoot.getMouseInfo();
            if(mi !=null){
                curItem.setLocation(mi.getX(),mi.getY());
            }
        }
        
        for (InventoryBox ib : boxes){
            if (ib.pressed){
                if(ib.item !=null && !hasItem){ //grabbing an item
                    setCurItem(ib.grabItem());
                    hasItem = true;
                }else if(ib.item ==null && hasItem){ //placing an item
                    ib.placeItem(curItem);
                    curItem =null;
                    hasItem =false;
                }else if(ib.item != null && hasItem){ //swapping item
                    Equipment temp = curItem;
                    setCurItem(ib.grabItem());
                    ib.placeItem(temp);
                }
            }
        }
        
        if(head.pressed){
            if(head.item != null && !hasItem){
                setCurItem(head.grabItem());
                hasItem = true;
            }else if(head.item ==null && hasItem && curItem.type.equals("head")){
                head.placeItem(curItem);
                curItem =null;
                hasItem =false;
            }else if (head.item !=null && hasItem && curItem.type.equals("head")){
                Equipment temp = curItem;
                setCurItem(head.grabItem());
                head.placeItem(temp);
            }
        }else if(chest.pressed){
            if(chest.item != null && !hasItem){
                setCurItem(chest.grabItem());
                hasItem = true;
            }else if(chest.item ==null && hasItem && curItem.type.equals("chest")) {
                chest.placeItem(curItem);
                curItem =null;
                hasItem =false;
            }else if (chest.item !=null && hasItem && curItem.type.equals("chest")){
                Equipment temp = curItem;
                setCurItem(chest.grabItem());
                chest.placeItem(temp);
            }
        }else if(legs.pressed){
            if(legs.item != null && !hasItem){
                setCurItem(legs.grabItem());
                hasItem = true;
            }else if(legs.item ==null && hasItem && curItem.type.equals("legs")){
                legs.placeItem(curItem);
                curItem =null;
                hasItem =false;
            }else if (legs.item !=null && hasItem && curItem.type.equals("legs")){
                Equipment temp = curItem;
                setCurItem(legs.grabItem());
                legs.placeItem(temp);
            }
        }else if(weapon.pressed){
            if(weapon.item != null && !hasItem){
                setCurItem(weapon.grabItem());
                hasItem = true;
            }else if(weapon.item ==null && hasItem && curItem.type.equals("weapon")){
                weapon.placeItem(curItem);
                curItem =null;
                hasItem =false;
            }else if (weapon.item !=null && hasItem && curItem.type.equals("weapon")){
                Equipment temp = curItem;
                setCurItem(weapon.grabItem());
                weapon.placeItem(temp);
            }
        }
        
        //display tooltip when hovering:
        MouseInfo mi = Greenfoot.getMouseInfo();
        tooltip.setText("");
        if(mi !=null){
            List<InventoryBox> list = getWorld().getObjectsAt(mi.getX(),mi.getY(),InventoryBox.class);
            if(list.size()>0){
                InventoryBox ib = list.get(0);
                if(ib.item!=null){
                    tooltip.setText(ib.item.tooltip);
                }
            }
        }
        
    }
    
    /**
     * Used to refresh an item so it appears on top of everything.
     */
    public void setCurItem(Equipment item){ 
        int x = item.getX();
        int y = item.getY();
        getWorld().removeObject(item);
        getWorld().addObject(item,x,y);
        curItem = item;
    }
    
    /**
     * Used to display all the objects needed for the stats portion.
     */
    public void displayStats(){
        for (int i =0; i < 6;i++){
            addStatButtons[i] = new Button("add_button.png");
            obj.add(addStatButtons[i]);
        }
        statValues[0] = new Text(String.valueOf(player.precision), statFontSize, statColor);
        statValues[1] = new Text(String.valueOf(player.intelligence), statFontSize, statColor);
        statValues[2] = new Text(String.valueOf(player.dexterity), statFontSize, statColor);
        statValues[3] = new Text(String.valueOf(player.defense), statFontSize, statColor);
        statValues[4] = new Text(String.valueOf(player.endurance), statFontSize, statColor);
        statValues[5] = new Text(String.valueOf(player.spirituality), statFontSize, statColor);
        statValues[6] = new Text(String.valueOf(player.curStatPoints), statFontSize, statColor);
        
        int y =75;
        int yChange=30;
        for (Text t: statTexts){
            player.getWorld().addObject(t,115,y);
            obj.add(t);
            y+=yChange;
        }

        y =75;
        for (Button a : addStatButtons){
            player.getWorld().addObject(a,240,y);
            obj.add(a);
            y+=yChange;
        }
        
        y =75;
        for (Text s : statValues){
            player.getWorld().addObject(s,200,y);
            obj.add(s);
            y+=yChange;
        } 
        Text t = new Text("Level: " + String.valueOf(player.curLevel),statFontSize,statColor);
        player.getWorld().addObject(t, 115,285);
        obj.add(t);
        
        
        defenseText = new Text("Armor Defense: " , statFontSize, statColor);
        player.getWorld().addObject(defenseText, 350,325);
        obj.add(defenseText);
    }
    
    /**
     * Used to display all the objects needed for the inventory portion.
     */
    public void displayInventory(){
        //draw out the inventory stuff
        int i=0;
        for (int y =400; y <=712; y+=48){
            for(int x =88; x <= 712; x+=48){
                InventoryBox ib = new InventoryBox();
                player.getWorld().addObject(ib,x,y);
                obj.add(ib);
                boxes[i] = ib;
                i++;
            }
        }
        
        head = new InventoryBox();
        chest = new InventoryBox();
        legs = new InventoryBox();
        weapon = new InventoryBox();
        
        //display armor, weapon, text
        player.getWorld().addObject(head,350,100);
        player.getWorld().addObject(chest,350,180);
        player.getWorld().addObject(legs,350,260);
        player.getWorld().addObject(weapon,450,100);
        obj.add(head);
        obj.add(chest);
        obj.add(legs);
        obj.add(weapon);
        head.placeItem(player.curHead);
        chest.placeItem(player.curChest);
        legs.placeItem(player.curLegs);
        weapon.placeItem(player.curWeapon);
        obj.add(head.item);
        obj.add(chest.item);
        obj.add(legs.item);
        obj.add(weapon.item);
        Text t1 = new Text("Head",statFontSize,statColor);
        Text t2 = new Text("Chest",statFontSize,statColor);
        Text t3 = new Text("Legs",statFontSize,statColor);
        Text t4=new Text("Weapon",statFontSize,statColor);
        player.getWorld().addObject(t1, 350,135);
        player.getWorld().addObject(t2, 350,215);
        player.getWorld().addObject(t3, 350,295);
        player.getWorld().addObject(t4, 450,135);
        obj.add(t1);
        obj.add(t2);
        obj.add(t3);
        obj.add(t4);
        
        tooltip = new Text("",statFontSize,statColor);
        player.getWorld().addObject(tooltip, 575,250);
        obj.add(tooltip);
        //add objects to inventory boxes
        for (int x =0; x < boxes.length; x++){
            boxes[x].placeItem(player.inventory[x]); 
            obj.add(boxes[x].item);
        }
    }
}
