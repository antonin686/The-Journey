package worlds;

import java.awt.Color;
import java.awt.Graphics;

import assets.Assets;
import gameObject.Crate;
import gameObject.Enemy;
import gameObject.GameObjectManager;
import gameObject.ID;
import gameObject.Player;
import input.MouseManager;
import mainGame.GameManager;

public class Dung1 extends WorldManager
{

	public Dung1(GameManager gM,Player player) 
	{
		super(gM,player);
		goM = new GameObjectManager();
		loadingWorld(Assets.dung1);
		goM.addObject(player);
		camLimitX = 1045;
		camLimitY = 716;
		worldID = 1;
	}

	@Override
	public void tick() 
	{
		goM.tick();
		gM.getCam().tick(gM.getPlayer());		
	}

	@Override
	public void render(Graphics g) 
	{
		int xStart = (int) Math.max(0, gM.getCam().getX() / 32);
		int xEnd = (int) Math.min(width, (gM.getCam().getX() + gM.getWidth()) / 32 + 1);
		int yStart = (int) Math.max(0, gM.getCam().getY() / 32);
		int yEnd = (int) Math.min(height, (gM.getCam().getY() + gM.getHeight()) / 32 + 1);
		
		for(int xx = xStart; xx < xEnd; xx++)
		{
			for(int yy = yStart; yy < yEnd; yy++)
			{
				g.drawImage(Assets.floor, xx * 32, yy *32,32,32, null);	
			}
		}
		goM.render(g);
		
	}

}
