import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    int[][] defaultPath = new int[][]{ {200,500}, {600,500}, {700,100}};
    int curDefault =0; //index for which position
    int defaultPathLength =defaultPath.length;

    static HashMap<String, int[][]> graph = new HashMap<String,int[][]>();
    boolean hasPath =false;
    int[][] pathToFollow;
    int curPath=0;
    boolean needPath =false;
    HealthBar healthBar;
    public void setup(){
        getWorld().addObject(healthBar, getX(),getY()+ getImage().getHeight());
    }

    public Enemy(int health){
        healthBar = new HealthBar(health, this);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */  
    public void act() 
    {
        controlMovement();
        controlWeapons();
        
        
        
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

        /*double squareX =  speed*(Math.cos(Math.toRadians(getRotation())));
        double squareY =   speed*(Math.sin(Math.toRadians(getRotation())));
        int circleX = (int)squareX;
        int circleY = (int) squareY;*/

        if (getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Impassable.class).isEmpty() && getWorld().getObjectsAt(getX() +circleX, getY()+circleY, Player.class).isEmpty()) {
            super.setLocation(getX()+circleX, getY()+circleY);
            //super.move(speed);
        }
    }

    public int[][] pathFind(int startX, int startY, int destX, int destY){
        startX = (int) (10*Math.round(startX/10.0));
        startY = (int) (10*Math.round(startY/10.0));
        ArrayList<int[][]> q = new ArrayList<int[][]>();

        q.add(new int[][] {{startX, startY}});

        ArrayList<String> visited = new ArrayList<String>();

        while(q.size()>0){
            int[][] path = q.get(0);
            int[] cur = path[path.length-1];
            String id = String.valueOf(cur[0]) + " " + String.valueOf(cur[1]);
            q.remove(0);

            if(getWorld().getObjectsAt(cur[0],cur[1], Impassable.class).size() >0 || getWorld().getObjectsAt(cur[0]+20,cur[1], Impassable.class).size() >0 || getWorld().getObjectsAt(cur[0]-20,cur[1], Impassable.class).size() >0||getWorld().getObjectsAt(cur[0],cur[1]+20, Impassable.class).size() >0||getWorld().getObjectsAt(cur[0],cur[1]-20, Impassable.class).size() >0){
                if (getWorld().getObjectsAt(cur[0]+10,cur[1]+10, Impassable.class).size() >0 || getWorld().getObjectsAt(cur[0]+10,cur[1]-10, Impassable.class).size() >0 ||getWorld().getObjectsAt(cur[0]-10,cur[1]+10, Impassable.class).size() >0||getWorld().getObjectsAt(cur[0]-10,cur[1]-10, Impassable.class).size() >0){

                    continue;
                }
            }
            //getWorld().addObject(new Knife(), cur[0],cur[1]);
            if(cur[0] == destX && cur[1] == destY){
                return path;
            }

            if(visited.contains(id)){
                continue;
            }
            visited.add(id);

            for(int[] neighbor: Enemy.graph.get(id)){
                int[][] newPath = Arrays.copyOf(path, path.length+1);
                newPath[newPath.length-1] = neighbor;
                q.add(newPath);
            }
        }
        return null;
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
        while (curX >0 && curX<800 && curY>0 && curY<800){
            curX += cos*s;
            curY += sin*s;

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

    public void moveToDefault(){
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

    }

    
}
