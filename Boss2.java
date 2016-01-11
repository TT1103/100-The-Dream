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
    int maxTurnDelay =2;
    int turnDelay =maxTurnDelay;
    int turnSpeed=1;
    boolean attacking = false;
    
    int maxAttackDelay =150;
    int attackDelay = maxAttackDelay;
    
    int curAttack;
    
    int clusterCnt=0;
    int maxClusterAttackDelay =20;
    int clusterAttackDelay = maxClusterAttackDelay;

    int maxMoveDelay =5;
    int moveDelay=maxMoveDelay;
    
    int bulletCnt=0;
    int maxBulletAttackDelay =5;
    int bulletAttackDelay = maxBulletAttackDelay;
    boolean start = true;
    Player p;
    BossHealthBar hpBar;
    
    public void act() 
    {
        if(paused) return;

        if(start){
            start = false;
            hpBar = new BossHealthBar(50000, this);
            getWorld().addObject(hpBar, 400,775);
            p = (Player) getWorld().getObjects(Player.class).get(0);
        }
        turnTowards(p.getX(),p.getY());
        
        if(!attacking){ //slowly move towards player
            if (moveDelay>0)moveDelay--;
            if(moveDelay==0){
                moveDelay = maxMoveDelay;
                
                int originalRotation = getRotation();
                this.turnTowards(p.getX(), p.getY());
                int rotation = getRotation();
                this.setRotation(originalRotation);
        
                double s = 2;
                double cos = Math.cos(Math.toRadians(rotation));
                double sin = Math.sin(Math.toRadians(rotation));
                
                setLocation((int) (getX()+cos*s) ,(int) (getY()+sin*s));
            }
        }
        controlWeapons();
        controlDeath();
        
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
                if(getWorld().getObjectsAt(getX(), getY(), Impassable.class).size() >0 ||getWorld().getObjectsAt(getX(), getY(), Sentry.class).size() >0 ){ //something is blocking
                    //shoot lasers instead
                    clusterCnt =10;
                    attacking =false;
                    attackDelay +=clusterCnt * clusterAttackDelay;
                }else{
                    GreenfootSound effect = new GreenfootSound("spawn_effect.wav");
                    effect.setVolume(80);
                    effect.play();
                    MortarTower mtower = new MortarTower(4);
                    getWorld().addObject(mtower, getX(), getY());

                    attacking =false;
                }
            }else if(curAttack ==1){
                clusterCnt = 20;
                attacking =false;
                attackDelay +=clusterCnt * clusterAttackDelay;
            }else if(curAttack ==2){ //bullet barrage
                bulletCnt = 100;
                attacking = false;
                attackDelay +=bulletCnt * bulletAttackDelay;
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

        if(bulletCnt>0){
            if(bulletAttackDelay >0) bulletAttackDelay--;
            if(bulletAttackDelay ==0){
                bulletAttackDelay = maxBulletAttackDelay;
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                bulletCnt--;
                int targetX = p.getX() + (rand.nextInt(200)-100); 
                int targetY= p.getY() + (rand.nextInt(200)-100); ;
                EnemyBullet bullet = new EnemyBullet(10,20,targetX, targetY); //10,20
                bullet.turnTowards(p.getX(),p.getY());
                getWorld().addObject(bullet, getX(), getY()); 
            }

            if(bulletCnt ==0){
                attacking = false;
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
            p.gainExp(2000);

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
