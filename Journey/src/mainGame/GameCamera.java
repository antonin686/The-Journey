package mainGame;

import gameObject.GameObject;

public class GameCamera 
{
	private float x,y;
	private GameManager gM;
	public GameCamera(float x, float y,GameManager gM)
	{
		this.x = x;
		this.y = y;
		this.gM = gM;
	}
	
	public void tick(GameObject o)
	{
		x += (o.getX() - x - gM.getWidth()/2) ;
		y += (o.getY() - y - gM.getHeight()/2) ;
		//System.out.println(x + " " + y);
		if(x <= 0) x = 0;
		if(x >= gM.getCamLimitX()) x = gM.getCamLimitX();
		if(y <= 0) y = 0;
		if(y >= gM.getCamLimitY()) y = gM.getCamLimitY();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
