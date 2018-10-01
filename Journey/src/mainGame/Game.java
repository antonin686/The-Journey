package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import assets.Assets;
import gameObject.Player;
import input.KeyManager;
import input.MouseManager;
import states.EscapeState;
import states.GameState;
import states.HelpState;
import states.MenuState;
import states.States;
import ui.Inventory;
import ui.ShopUI;
import ui.SkillUI;
import utils.FileManager;
import worlds.Dung1;
import worlds.Dung2;
import worlds.MainWorld;
import worlds.WorldManager;


public class Game implements Runnable
{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 563;
	public Display display;
	private boolean running = false;
	private int width,height;

	public String title;
	public Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	//World
	public WorldManager mainWorld,dung1,dung2,dung;
	private GameManager gM;
	private KeyManager keyM;
	private MouseManager mouseM;
	private GameCamera cam;
	private Inventory inven;
	private ShopUI shopUI;
	private SkillUI skillUI;
	public Player player;
	int ticks = 0;
	public FileManager fileM;
	
	//States
	
	public States gameState;
	public States menuState;
	public States escapeState;
	public States helpState;

	public Game(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		Assets.init();
		
		display = new Display(title,width,height);
		keyM = new KeyManager(gM);
		
		gM = new GameManager(this);
		cam = new GameCamera(0,0,gM);
		player = new Player(100,200,gM);
		inven = new Inventory(gM);
		shopUI = new ShopUI(gM);
		skillUI = new SkillUI(gM);

		gM.setPlayer(player);
		
		init();
		
		
	}

	private void init()
	{
		
		gM.setKeyM(keyM);
		//World
		mainWorld = new MainWorld(gM,player);
		dung1 = new Dung1(gM,player);
	    dung2 = new Dung2(gM,player);
	    
	    //State
	    gameState = new GameState(gM);
	    menuState = new MenuState(gM);
	    escapeState = new EscapeState(gM);
	    helpState = new HelpState(gM);
		
	    
	    mouseM = new MouseManager(gM);
		display.getFrame().addKeyListener(keyM);
		gM.getCanvas().addMouseListener(mouseM);
		gM.getCanvas().addMouseMotionListener(mouseM);
		
		fileM = new FileManager(gM);
		WorldManager.setCurrentWorld(mainWorld,player,gM,100,800);
		//WorldManager.setCurrentWorld(menuState,player,gM,100,800);
		States.setState(menuState);
		
	}


	
	private void tick()
	{
		keyM.tick();
		if(States.getState() != null)
			States.getState().tick();
	}
	
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.clearRect(0, 0, width, height);
		
		
		
		//Draw Start
		
		
		
		g2d.translate(-gM.getCam().getX(), -gM.getCam().getY());
		
		if(States.getState() == gameState)
			States.getState().render(g);
		
		g2d.translate(gM.getCam().getX(), gM.getCam().getY());
		
		if(States.getState() == gameState) {
			g.setColor(Color.red);
			g.fillRect(5, 5, player.maxHealth * 2, 32);
			g.setColor(Color.GREEN);
			g.fillRect(5, 5, player.getHealth() * 2, 32);
			g.setColor(Color.ORANGE);
			g.drawString("Axes: " + player.getAxes(), 5, 50);
		}
		else
		{
			States.getState().render(g);
		}
		
		//Draw End
		
		bs.show();
		g.dispose();
		
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public void run()
	{
		
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000)
			{
				System.out.println("Fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}

	public GameCamera getCam() {
		return cam;
	}

	public void setCam(GameCamera cam) {
		this.cam = cam;
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

	public Inventory getInven() {
		return inven;
	}

	public void setInven(Inventory inven) {
		this.inven = inven;
	}

	public ShopUI getShopUI() {
		return shopUI;
	}

	public void setShopUI(ShopUI shopUI) {
		this.shopUI = shopUI;
	}
	public SkillUI getSkillUI() { return skillUI; }

	public void setSkillUI(SkillUI skillUI) { this.skillUI = skillUI; }
	
	public MouseManager getMouseM() { return mouseM;}
}
