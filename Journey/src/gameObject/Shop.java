package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import assets.Assets;
import mainGame.GameManager;

public class Shop extends GameObject
{

	public Shop(int x, int y,GameManager gM) {
		super(x, y, ID.Shop, gM);
		
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		//g.setColor(Color.PINK);
		g.drawImage(Assets.shop, x, y, width *2, height *2, null);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width * 2,height *2);
	}

	@Override
	public void die() 
	{
		
	}

}
