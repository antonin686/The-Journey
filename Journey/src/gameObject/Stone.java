package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import assets.Assets;
import mainGame.GameManager;

public class Stone extends GameObject
{
	public Stone(int x, int y, GameManager gM) 
	{
		super(x, y,ID.Stone, gM);
		height = 64;
		width = 64;
		
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.rockSmall, x+10, y+10,width/2,height/2, null);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void die() 
	{
		gM.getPlayer().rock += 10;
		active = false;	
	}
}
