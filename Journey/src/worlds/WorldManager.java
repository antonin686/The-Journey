package worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import assets.Assets;
import gameObject.Block;
import gameObject.Crate;
import gameObject.Enemy;
import gameObject.GameObjectManager;
import gameObject.Gate;
import gameObject.Player;
import mainGame.GameManager;

public abstract class WorldManager 
{
	protected GameManager gM;
	protected GameObjectManager goM;
	protected int width,height;
	public static  WorldManager CurrentWorld;
	protected Player player;
	protected int camLimitX,camLimitY;
	protected int worldID;
	public WorldManager(GameManager gM,Player player) 
	{
		this.gM = gM;
		this.player = player;
	}

	public static WorldManager getCurrentWorld() {
		return CurrentWorld;
	}

	public static void setCurrentWorld(WorldManager currentWorld,Player player,GameManager gM,int px, int py) {
		CurrentWorld = currentWorld;
		player.setX(px);
		player.setY(py);
		gM.setGoM(currentWorld.goM);
		gM.setCamLimitX(currentWorld.getCamLimitX());
		gM.setCamLimitY(currentWorld.getCamLimitY());
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void loadingWorld(BufferedImage image)
	{
		width = image.getWidth();
		height = image.getHeight();
		
		for(int x = 0; x < width ; x++)
		{
			for(int y = 0; y < height; y++)
			{
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255)
					goM.addObject(new Block(x * 32, y * 32,Assets.block, gM));
				if(green == 255 && blue == 0)
					goM.addObject(new Enemy(x * 32, y * 32, gM));
				if(green == 255 && blue == 255)
					goM.addObject(new Crate(x * 32, y * 32, gM));
				if(red == 255 && blue == 255) //Magenta
				{
					goM.addObject(new Gate(x * 32, y * 32,100, gM));
				}
				
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCamLimitX() {
		return camLimitX;
	}

	public void setCamLimitX(int camLimitX) {
		this.camLimitX = camLimitX;
	}

	public int getCamLimitY() {
		return camLimitY;
	}

	public void setCamLimitY(int camLimitY) {
		this.camLimitY = camLimitY;
	}

	public int getWorldID() {
		return worldID;
	}

	public void setWorldID(int worldID) {
		this.worldID = worldID;
	}
}
