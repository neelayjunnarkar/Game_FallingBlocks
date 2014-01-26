package Game_FallingBlocks.Game.src.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBlocks extends Entity{

	private Main instance;
	private int enemyX, enemyY, enemyW, enemyH;
	private Rectangle enemyHitbox;
	private int enemyYD;
	
	public EnemyBlocks(Main instance, int x, int y, int w, int h, int enemyYD){
		super(x, y);
		this.instance = instance;
		enemyX = x;
		enemyY = y;
		enemyW = w;
		enemyH = h;
		this.enemyYD = enemyYD;
		
		enemyHitbox = new Rectangle(enemyX, enemyY, enemyW, enemyH);
	}
	
	public void render(Graphics g){
		move();
		//gravity();
		Random random1 = new Random();
		int randomResult = random1.nextInt(5);
		switch (randomResult){
		case 1:
			g.setColor(Color.RED); break;
		case 2:
			g.setColor(Color.BLUE); break;
		case 3:
			g.setColor(Color.CYAN); break;
		case 4:
			g.setColor(Color.ORANGE); break;
		default:
			g.setColor(Color.YELLOW); break;
			
		}
		
		g.setColor(Color.RED);
		g.fillRect(enemyHitbox.x, enemyHitbox.y, enemyHitbox.width, enemyHitbox.height);
	}
	
	private void move(){
		if (!instance.getGround().isCollided(enemyHitbox)){
			enemyHitbox.y += enemyYD;
			
		}
		if (instance.getPlayer().EnemyisCollided(enemyHitbox)){
			instance.getPlayer().lives -= 1;
		}
		
	}
	
	private void gravity(){
		if (!instance.getGround().isCollided(enemyHitbox)){
			enemyYD += 1;
		}
		else if (instance.getGround().isCollided(enemyHitbox)){
			enemyYD = 0;
			if (enemyHitbox.y != 514){ enemyHitbox.y = 514; }
		}
	}
	public boolean EnemyisCollided(Rectangle entity){
		return enemyHitbox.intersects(entity);
	}
	
	public boolean isDead(){
		if (instance.getGround().isCollided(enemyHitbox)){
			return true;
		}
		else if (instance.getPlayer().EnemyisCollided(enemyHitbox)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setEnemyYD(int i){
		enemyYD = i;
	}
	public int getEnemyYD(){ return enemyYD; }
}
