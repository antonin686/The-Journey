package worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import assets.Assets;

import gameObject.Block;
import gameObject.GameObjectManager;
import gameObject.Gate;
import gameObject.ID;
import gameObject.Player;
import gameObject.Rock;
import gameObject.Shop;
import gameObject.Tree;
import mainGame.GameManager;

public class MainWorld extends WorldManager
{

	
	public MainWorld(GameManager gM, Player player) {
		super(gM,player);
		goM = new GameObjectManager();
		loadingWorld(Assets.mainWorld);
		goM.addObject(new Shop(100,400,gM));
		goM.addObject(player);
		camLimitX = 1045;
		camLimitY = 1484;
		worldID = 0;
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
		int xEnd = (int) Math.min(width, (gM.getCam().getX() + gM.getWidth()) / 32 +1);
		int yStart = (int) Math.max(0, gM.getCam().getY() / 32);
		int yEnd = (int) Math.min(height, (gM.getCam().getY() + gM.getHeight()) / 32 + 1);
		
		for(int xx = xStart; xx < xEnd; xx++)
		{
			for(int yy = yStart; yy < yEnd; yy++)
			{
				g.drawImage(Assets.grass, xx * 32, yy *32,32,32, null);	
			}
		}
		
		goM.render(g);
		
	}
	
	@Override
	public void loadingWorld(BufferedImage image)
	{
		width = image.getWidth();
		height = image.getHeight();
		int gID =1;
		for(int x = 0; x < width ; x++)
		{
			for(int y = 0; y < height; y++)
			{
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && blue == 0 && green == 0) //Red
					goM.addObject(new Block(x * 32,y * 32,Assets.blockMW, gM));
				if(green == 255 && blue == 0) //Green
					goM.addObject(new Rock(x * 32, y * 32, gM));
				if(green == 255 && blue == 255) //Cyan
					goM.addObject(new Tree(x * 32, y * 32, gM));
				if(red == 255 && blue == 255) //Magenta
				{
					goM.addObject(new Gate(x * 32, y * 32,gID, gM));
					gID++;
				}
			}
		}
	}

}
