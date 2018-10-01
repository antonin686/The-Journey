package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mainGame.GameManager;

public abstract class GameObject 
{
	protected int x,y;
	protected float speed;
	protected float moveX = 0, moveY = 0;
	protected ID id;
	protected int width = 32 , height = 32;
	protected GameManager gM;
	public int health = 0;
	protected boolean active = true;
	protected int gID;
	protected BufferedImage image;
	public GameObject(int x, int y, ID id, GameManager gM) 
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.gM = gM;
	}
	
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract void die();
	public Rectangle getBoundsBig()
	{
		return new Rectangle(x - 16, y -16, width *2, height *2);
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getMoveX() {
		return moveX;
	}

	public void setMoveX(float moveX) {
		this.moveX = moveX;
	}

	public float getMoveY() {
		return moveY;
	}

	public void setMoveY(float moveY) {
		this.moveY = moveY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public int getgID() {
		return gID;
	}



	public void setgID(int gID) {
		this.gID = gID;
	}
}
