package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import assets.Assets;
import mainGame.GameManager;
import worlds.WorldManager;

public class Wood extends GameObject
{

	public Wood(int x, int y, GameManager gM) {
		super(x, y,ID.Wood, gM);
		width = 32;
		height = 32;
	}

	@Override
	public void tick() 
	{
	
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.wood, x+16, y+70,width,height, null);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x+16,y+70,width,height);
	}

	@Override
	public void die() 
	{
		gM.getPlayer().wood+= 10;
		active = false;	
	}

}
