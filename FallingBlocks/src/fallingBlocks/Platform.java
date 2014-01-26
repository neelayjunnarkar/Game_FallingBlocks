package fallingBlocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Platform{
	
	private int x, y, w, h;
	private Rectangle platform;
	
	public Platform(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		platform = new Rectangle(x, y, w, h);
	}
	
	public void render(Graphics g){
		Random random = new Random();
		int randomResult = random.nextInt(2);
		switch(randomResult+1){
		case 2:
			g.setColor(Color.CYAN); break;
		case 1:
			g.setColor(Color.GREEN); break;
		
		}
		g.setColor(Color.WHITE);
		g.fillRect(platform.x, platform.y, platform.width, platform.height);
	}
	
	public boolean isCollided(Rectangle entity){
		return platform.intersects(entity);
	}
}
