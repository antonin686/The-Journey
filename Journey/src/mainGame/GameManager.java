package mainGame;

import java.awt.Canvas;

import javax.swing.JFrame;


import gameObject.GameObjectManager;
import gameObject.Player;
import input.KeyManager;
import input.MouseManager;
import ui.Inventory;
import ui.ShopUI;
import ui.SkillUI;
import worlds.WorldManager;

public class GameManager 
{
	
	private  Game game;
	private  KeyManager keyM;
	private GameCamera cam;
	private GameObjectManager goM;
	private Player player;
	private int health = 100,ammo = 100; 
	private int camLimitX,camLimitY;
	public int worldTOLOAD;
	public boolean inGame;
	public GameManager(Game game) 
	{
		this.game = game;
		inGame = false;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public KeyManager getKeyM() {
		return keyM;
	}

	public void setKeyM(KeyManager keyM) {
		this.keyM = keyM;
	}
	public GameCamera getCam()
	{
		return game.getCam();
	}
	public int getWidth()
	{
		return game.getWidth();
	}
	public int getHeight()
	{
		return game.getHeight();
	}

	public GameObjectManager getGoM() {
		return goM;
	}

	public void setGoM(GameObjectManager goM) {
		this.goM = goM;
	}
	public Canvas getCanvas()
	{
		return game.display.getCanvas();
	}
	public JFrame getFrame()
	{
		return game.display.getFrame();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
	public Inventory getInventory()
	{
		return game.getInven();
	}
	public ShopUI getShopUI()
	{
		return game.getShopUI();
	}
	public SkillUI getSkillUI() { return game.getSkillUI();}
	
	public MouseManager getMouseM() { return game.getMouseM();}
	
	public WorldManager getWorld()
	{
		if(worldTOLOAD == 0)
			return game.mainWorld;
		else if(worldTOLOAD == 1)
			return game.dung1;
		else if(worldTOLOAD == 2)
			return game.dung2;
		
		return null;
	}
	
	
}
