import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author Tiger Zhao
 * @version January 11, 2016
 */
public class Enemy extends Actor
{
    int screenX= 800;
    int screenY= 800;
    GreenfootImage[] movingSprites;
    int delay;
    int maxDelay;
    GreenfootImage idleSprite;
    int power;//amount of damage for each hit
    boolean moving =false;
    String direction ="";
    int speed;

    boolean shooting =false;
    
    int damage;
    int damageDelay;
    int maxDamageDelay;
    
    int level=1; //highest level = better stats
    
    boolean dead =false;

 
    static HashMap<String, int[][]> graph = new HashMap<String,int[][]>();
    boolean hasPath =false;
    int[][] pathToFollow;
    boolean noPath = false;
    int curPath=0;
    boolean needPath =false;
    HealthBar healthBar;
    
    boolean paused =false;
    
    boolean start = false;
   
    public Enemy(){
    }
    public Enemy(int level){
        start = true;

    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */  
    public void act() 
    {
        if(paused){
            return;
        }
        if(start){
            start = false;
            getWorld().addObject(healthBar, getX(),getY()+ getImage().getHeight());
        }
        controlMovement();
        controlWeapons();
        controlDeath();
        
        
    }    
    
    public void controlDeath(){
        if(dead){
            Player p = (Player) getWorld().getObjects(Player.class).get(0);
            p.gainExp(level *60);
            getWorld().removeObject(this);
        }
    }


    public void controlMovement(){

    }

    public void controlWeapons(){

    }

    public void damage(int d){
        healthBar.damage(d);
        if (healthBar.health<=0){
            dead =true;
        }
    }

    public void move(int speed){
        double squareX =  (Math.cos(Math.toRadians(getRotation())));
        double squareY =   (Math.sin(Math.toRadians(getRotation())));
        int circleX = (int) (speed*squareX * Math.sqrt(1- 0.5* Math.pow(squareY,2)));
        int circleY = (int) (speed*squareY * Math.sqrt(1- 0.5* Math.pow(squareX,2)));

        int origX=getX();
        int origY=getY();
        /*double squareX =  speed*(Math.cos(Math.toRadians(getRotation())));
        double squareY =   speed*(Math.sin(Math.toRadians(getRotation())));
        int circleX = (int)squareX;
        int circleY = (int) squareY;*/
        
        if (getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Player.class).isEmpty() ) {
            super.setLocation(getX()+circleX, getY()+circleY);
            //super.move(speed);
        }else if(getWorld().getObjectsAt(getX() +circleX, getY(), Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() +circleX, getY(), Player.class).isEmpty()) { //what if we only move x?
            super.setLocation(getX()+circleX, getY());
        }else if(getWorld().getObjectsAt(getX() , getY()+circleY, Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() , getY()+circleY, Player.class).isEmpty()){//what if we only move y?
            super.setLocation(getX(), getY()+circleY);
        }
        
        
        
    }

   
    ArrayList<String> visited;
    int[][] finalPath;
    ArrayList<ArrayList<int[]>> queue;
    public int[][] pathFind(int startX, int startY, int destX, int destY){
        visited = new ArrayList<String>();
        //get the absolute values, where 0,0, is the center of the map
        int absStartX = startX - ((Map) getWorld()).curX +400;
        int absStartY = startY - ((Map) getWorld()).curY+400;
        
        int absDestX = destX - ((Map) getWorld()).curX +400;
        int absDestY = destY - ((Map) getWorld()).curY+400;
        
        finalPath = null;
        queue = new ArrayList<ArrayList<int[]>>();
        
        int[] tempArray = new int[] {absStartX,absStartY};
        ArrayList<int[]> tempArrayList = new ArrayList<int[]>();
        tempArrayList.add(tempArray);
        
        queue.add(tempArrayList);
        
        recursivePathFind(absDestX, absDestY,0);
        
        return finalPath;
    }
    
    int nodeSize =30; //the size of the chunks that the world will be split into
    public void recursivePathFind(int destX, int destY, int depth){
        if(finalPath !=null) return; //path is already found, stop searchi
        if (queue.size() ==0) return;
        if(depth >100) return;
        ArrayList<int[]> path = queue.get(0);
        int[] curPoint = path.get(path.size()-1);
        queue.remove(0);
    
        int curX=curPoint[0];
        int curY=curPoint[1];

        //getWorld().addObject(new InventoryBox(), curX+((Map) getWorld()).curX -400, curY+((Map) getWorld()).curX -400);
        String id = String.valueOf(curX) + " " + String.valueOf(curY);
        
        if(pointsMeet(curX+((Map) getWorld()).curX -400,curY+((Map) getWorld()).curX -400, destX+((Map) getWorld()).curX -400, destY+((Map) getWorld()).curX -400)){ //the dog can now see the player and follow him easily. No need for path finding, done.
            finalPath = new int[path.size()][2];
            path.toArray(finalPath);  
            return;
        }
        
        if(visited.contains(id)){
          
            recursivePathFind( destX, destY,++depth);
        }
        visited.add(id);
        
        if(!getWorld().getObjectsAt(curX, curY, Impassable.class).isEmpty()){ 
            recursivePathFind( destX, destY,++depth);
        }
       
        //go through up, down left and right
        ArrayList<int[]> temp1 = new ArrayList(path);
        temp1.add(new int[]{curX + nodeSize, curY});
        queue.add(temp1);
        
        ArrayList<int[]> temp2 = new ArrayList(path);
        temp2.add(new int[]{curX - nodeSize, curY});
        queue.add(temp2);
        
        ArrayList<int[]> temp3 = new ArrayList(path);
        temp3.add(new int[]{curX, curY + nodeSize});
        queue.add(temp3);
        
        ArrayList<int[]> temp4 = new ArrayList(path);
        temp4.add(new int[]{curX, curY - nodeSize});
        queue.add(temp4);
        
        recursivePathFind( destX, destY,++depth);
    }
    
    public boolean canSeePlayer(){
        Player p = (Player) getWorld().getObjects(Player.class).get(0);
        int originalRotation = getRotation();
        this.turnTowards(p.getX(), p.getY());
        int rotation = getRotation();
        this.setRotation(originalRotation);

        int s = 10;
        double cos = Math.cos(Math.toRadians(rotation));
        double sin = Math.sin(Math.toRadians(rotation));
        double curX = getX();
        double curY = getY();
        int distance =0;
        while (distance<1000){
            curX += cos*s;
            curY += sin*s;
            distance+=Math.abs(cos*s);
            distance+=Math.abs(sin*s);
            if (getWorld().getObjectsAt((int) curX, (int) curY, Impassable.class).size() >0){
                return false;
            }else if (getWorld().getObjectsAt((int) curX, (int) curY, Player.class).size()>0){
                return true;
            }
        }
        return false;
    }

    public boolean canSeePoint(int destX, int destY){

        int originalRotation = getRotation();
        this.turnTowards(destX, destY);
        int rotation = getRotation();
        this.setRotation(originalRotation);

        int s = 10;
        double cos = Math.cos(Math.toRadians(rotation));
        double sin = Math.sin(Math.toRadians(rotation));
        double curX = getX();
        double curY = getY();
        while (curX >0 && curX<800 && curY>0 && curY<800){
            curX += cos*s;
            curY += sin*s;
            // getWorld().addObject(new Knife(), (int)curX,(int)curY);
            if (getWorld().getObjectsAt((int) curX, (int) curY, Impassable.class).size() >0 || getWorld().getObjectsAt((int) (curX -10), (int) curY, Impassable.class).size() >0 || getWorld().getObjectsAt((int) (curX+10), (int) curY, Impassable.class).size() >0||getWorld().getObjectsAt((int) curX, (int) (curY-10), Impassable.class).size() >0 ||getWorld().getObjectsAt((int) curX, (int) (curY+10), Impassable.class).size() >0){
                return false;
            }else if (Math.abs(curX-destX) <10 && Math.abs(curY-destY)<10){
                return true;
            }
        }
        return false;
    }
    
    public boolean pointsMeet(int startX, int startY, int destX, int destY){
        int origX = getX();
        int origY=getY();
        setLocation(startX, startY);
        int originalRotation = getRotation();
        
        this.turnTowards(destX, destY);
        int rotation = getRotation();
        
        this.setRotation(originalRotation);
        setLocation(origX, origY);
        
        int s = 10;
        double cos = Math.cos(Math.toRadians(rotation));
        double sin = Math.sin(Math.toRadians(rotation));
        double curX = startX;
        double curY = startY;
        while (curX >0 && curX<800 && curY>0 && curY<800){
            curX += cos*s;
            curY += sin*s;
            // getWorld().addObject(new Knife(), (int)curX,(int)curY);
            if (getWorld().getObjectsAt((int) curX, (int) curY, Impassable.class).size() >0 || getWorld().getObjectsAt((int) (curX -10), (int) curY, Impassable.class).size() >0 || getWorld().getObjectsAt((int) (curX+10), (int) curY, Impassable.class).size() >0||getWorld().getObjectsAt((int) curX, (int) (curY-10), Impassable.class).size() >0 ||getWorld().getObjectsAt((int) curX, (int) (curY+10), Impassable.class).size() >0){
                return false;
            }else if (Math.abs(curX-destX) <10 && Math.abs(curY-destY)<10){
                return true;
            }
        }
        return false;
    }

    /*public void moveToDefault(){
        int destX=defaultPath[curDefault][0];
        int destY= defaultPath[curDefault][1];

        boolean needPath = !canSeePoint(destX, destY);
        System.out.println("Need path: "+needPath);
        if(needPath && !hasPath){
            pathToFollow = pathFind(getX(),getY(),destX,destY);
            //System.out.println("length of path: "+pathToFollow.length);

            hasPath =true;
            curPath =0;
        }else if(!needPath){
            hasPath = false;
        }
        if(!needPath){
            turnTowards(destX,destY );
            move(speed);
            moving = true;
            //System.out.println(getX()+" " + getY());
            if (Math.abs(getX() - defaultPath[curDefault][0]) <=3 && Math.abs(getY() - defaultPath[curDefault][1]) <=3){
                curDefault++;
                if (curDefault >= defaultPathLength){
                    curDefault =0;
                }
            }
        }else{ //need path is true

            //follow the path
            turnTowards(pathToFollow[curPath][0],pathToFollow[curPath][1] );
            //setLocation(pathToFollow[curPath][0],pathToFollow[curPath][1] );
            move(speed);
            moving = true;
            //System.out.println(getX()+" " + getY());
            if (Math.abs(getX() - pathToFollow[curPath][0]) <=3 && Math.abs(getY() - pathToFollow[curPath][1]) <=3){
                curPath++;
                if(curPath >= pathToFollow.length){
                    curPath =0;
                }
            }
        }

    }*/
    
    public double distanceToPlayer(){
        Player p = (Player) getWorld().getObjects(Player.class).get(0);
        return Math.sqrt(Math.pow(getX()-p.getX(),2) + Math.pow(getY()-p.getY(),2));
    }
    
}
