import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Boss2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss2 extends Boss
{
   
    boolean attacking = false;
    
    int maxAttackDelay =150;
    int attackDelay = maxAttackDelay;
    
    int curAttack;
    
    int clusterCnt=0;
    int maxClusterAttackDelay =20;
    int clusterAttackDelay = maxClusterAttackDelay;

    int maxMoveDelay =5;
    int moveDelay=maxMoveDelay;
   
    boolean start = true;

    BossHealthBar hpBar;
    
    public void act() 
    {
        if(paused) return;

        if(start){
            start = false;
            hpBar = new BossHealthBar(50000, this);
            getWorld().addObject(hpBar, 400,775);

        }
        controlMovement();
        controlWeapons();
        controlDeath();
        
    }   
    
    public void controlMovement(){
        Player p = (Player) getWorld().getObjects(Player.class).get(0);
        turnTowards(p.getX(),p.getY());
        
        
    }

    public void controlWeapons(){
        Random rand = new Random();

        if (attackDelay >0 && !attacking) attackDelay--;

        if(attackDelay==0){
            attacking = true;
            curAttack = rand.nextInt(3);//0,1,2
            attackDelay =maxAttackDelay;
        }

        if(attacking){
            if(curAttack ==0){
                GreenfootSound effect = new GreenfootSound("spawn_effect.wav");
                effect.setVolume(80);
                effect.play();
                int randX = rand.nextInt(700)-350;
                int randY = rand.nextInt(700)-350;
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                
                if(rand.nextInt(2) ==0){
                    MortarTower tower = new MortarTower(10);
                    getWorld().addObject(tower, p.getX()+randX, p.getY()+randY);
                    while(!tower.canSeePlayer()){
                        randX = rand.nextInt(700)-350;
                        randY = rand.nextInt(700)-350;
                        tower.setLocation(randX+p.getX(), randY+p.getY());
                    }
                }else{
                    Sentry tower = new Sentry(10);
                    getWorld().addObject(tower, p.getX()+randX, p.getY()+randY);
                    while(!tower.canSeePlayer()){
                        randX = rand.nextInt(700)-350;
                        randY = rand.nextInt(700)-350;
                        tower.setLocation(randX+p.getX(), randY+p.getY());
                    }
                }
                

                attacking =false;
            
            }else if(curAttack ==1){
                clusterCnt = 20;
                attacking =false;
                attackDelay +=clusterCnt * clusterAttackDelay;
            }else if(curAttack ==2){ //spawn dogs
                if(getWorld().getObjects(Dog.class).size()>12){ //too many dogs! shoot mortars instead
                    clusterCnt = 20;
                    attacking =false;
                    attackDelay +=clusterCnt * clusterAttackDelay;
                }else{
                    for(int i =0; i <6; i++){
                        Dog d = new Dog(10);
                        getWorld().addObject(d, getX() -225+(i*75), getY());
                        d.setRotation(getRotation());
                        d.move(100);
                    }
                    attacking = false;
                }
            }
        }

        if(clusterCnt>0){
            if(clusterAttackDelay >0) clusterAttackDelay--;
            if(clusterAttackDelay ==0){
                clusterAttackDelay = maxClusterAttackDelay;
                GreenfootSound effect = new GreenfootSound("laser_shoot.wav");
                effect.setVolume(75);
                effect.play();
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                clusterCnt--;
                int targetX = p.getX() + (rand.nextInt(200)-100); 
                int targetY= p.getY() + (rand.nextInt(200)-100);
                EnemyClusterBomb cluster = new EnemyClusterBomb(10,50,targetX, targetY); //10,20
                cluster.turnTowards(p.getX(),p.getY());
                getWorld().addObject(cluster, getX(), getY()); 
            }
            if(clusterCnt ==0){
                attacking =false;
            }
        }

        

    }
    public void damage(int d){
        hpBar.damage(d);
        if (hpBar.health<=0){
            dead =true;
        }
    }

    public void controlDeath(){
        if(dead){
            Player p = (Player) getWorld().getObjects(Player.class).get(0);
            p.gainExp(15000);

            if(p.curGameLevel<3){ //increase the player's game progress
                p.curGameLevel=3;
            }

            LevelExit exit = new LevelExit();
            getWorld().addObject(exit, getX(), getY());
            exit.removeBlocking();

            getWorld().removeObject(this);
        }
    }

}
