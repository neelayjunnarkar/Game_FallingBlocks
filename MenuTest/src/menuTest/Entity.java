package menuTest;

import java.awt.Graphics;

public abstract class Entity {
	
	protected int x, y, w, h;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g){}
	
}
