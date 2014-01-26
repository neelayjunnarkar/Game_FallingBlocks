package Game_FallingBlocks.Game.src.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends Entity {
	
	private Main instance;
	private Rectangle hitbox;
	private static int playerX, playerY, playerW, playerH;
	public int playerXD, playerYD = 1;
	private int frictionc = 0, frictioncdiv = 90;
	public int lives = 5;
	private int livesOriginal = lives;
	
	public Player(Main instance, int x, int y, int w, int h){
		super(x, y);
		this.instance = instance;
		playerX = x;
		playerY = y;
		playerW  = w;
		playerH = h;
		hitbox = new Rectangle(playerX, playerY, playerW, playerH);
	}
	
	@Override
	public void render(Graphics g){
		
		move();
		gravity();
		Random random = new Random();
		int randomResult = random.nextInt(2);
		switch(randomResult){
		case 1:
			g.setColor(Color.MAGENTA);
		case 2:
			g.setColor(Color.PINK);
		}
		g.setColor(Color.ORANGE);
		g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	
	private void move(){
		if (instance.getGround().isCollided(hitbox)){
			if (hitbox.y != 504 + (20 - hitbox.height)){
				hitbox.y = 504 + (20 - hitbox.height); 
			}
		}
		
		if (playerXD < 0){
			if (hitbox.x + hitbox.width > 0){
				hitbox.x += playerXD;
			}
			if (hitbox.x + hitbox.width < 0){
				hitbox.x = 810 + hitbox.x + hitbox.width;
			}
		}
		if (playerXD > 0){
			if (hitbox.x < 810){
				hitbox.x += playerXD;
			}
			if (hitbox.x > 810){
				hitbox.x = hitbox.x - hitbox.width - 810 ;
			}
		}
		if (playerYD < 0){
			if (hitbox.y > 5){hitbox.y += playerYD;}
		}
		if (playerYD > 0){
			hitbox.y += playerYD;
		}
	}
	
	private void gravity(){
		if (!instance.getGround().isCollided(hitbox)){
			playerYD += 1;
		}
		else if (instance.getGround().isCollided(hitbox)){
			playerYD = 0;

			if (hitbox.y != 504 + (20 - hitbox.height)){ 
				hitbox.y = 504 + (20 - hitbox.height) ; 
			}
			
			
		}
		
	}
	
	public void friction(){
		if (instance.getGround().isCollided(hitbox)){
			if (playerXD < 0){
				while (playerXD < 0){
					frictionc++;
					if (frictionc % frictioncdiv == 0){
						playerXD += 1;
					}
				}
			}
		
			if (playerXD > 0){
				while (playerXD > 0){
					frictionc++;
					if (frictionc % frictioncdiv == 0){
						playerXD -= 1;
					}
				}
			}
		} else{ // AIR RESISTANCE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
		}
	}
	
	public void reset(){
		lives = livesOriginal;
		
	}
	public boolean EnemyisCollided(Rectangle entity){
		return hitbox.intersects(entity);
	}
	public int getPlayerXD(){ return playerXD; }
	public void setPlayerXD(int value){ playerXD = value; }
	
	public int getPlayerYD(){ return playerYD; }
	public void setPlayerYD(int value){ playerYD = value; }
	
	public Rectangle getHitbox(){
		return hitbox;
	}

}
