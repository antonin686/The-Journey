package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import assets.Animation;
import assets.Assets;
import assets.AudioPlayer;
import mainGame.GameManager;
import states.States;
import worlds.WorldManager;

public class Player extends GameObject
{
	private int px = 0,py = 0;
	//private boolean chasing;
	
	private AudioPlayer crateM, gameM, fireM, dieM, itemM, dung1M, dung2M, dung3M, gateM, chaseM;
	public int axes = 100;
	private int kunai = 1;
	public  int wood = 0;
	public int rock = 0;
	public int cash = 1000;
	public int exp = 0;
	public int healthPotion = 0;
	public int maxHealth = 100;
	public int plevel = 0;
	public void setCountM(int countM) {
		this.countM = countM;
	}

	public int maxAxes = 100;
	private AudioPlayer warp;
	String statusM;
	
	private String[] statusA;
	int tickCount = 0;
	public int countM;
	
	
	private Animation animDown, animUp, animLeft, animRight;
	
	public Player(int x, int y,GameManager gM) 
	{
		super(x, y, ID.Player, gM);
		speed = 5;
		height = 48;
		health = 100;
		
		animDown = new Animation(200, Assets.player_down);
		animUp = new Animation(200, Assets.player_up);
	    animLeft = new Animation(200, Assets.player_left);
		animRight = new Animation(200, Assets.player_right);
		try {
			gameM = new AudioPlayer("town.wav");
			dung1M = new AudioPlayer("SpookyChains.wav");
			dung2M = new AudioPlayer("HorrorA.wav");
			dung3M = new AudioPlayer("ExtremeFx.wav");
			gateM = new AudioPlayer("Chanting.wav");
			chaseM = new AudioPlayer("gun.wav");
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		statusA = new String[]{"play", "stop"};
	}

	@Override
	public void tick() 
	{
		
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		checkStats();
		move();
		collision();
		getInput();
		
		
		//statusM;
		//System.out.println(countM);
	    //System.out.println(statusA[countM]);
		music();
	}
	
	

	private void music() {
		
		
		
		if(WorldManager.getCurrentWorld().equals(gM.getGame().mainWorld) && statusA[countM] == "play") {
			
			if(!(gameM.status == "play")) {
				try {
					gameM.restart();
					gameM.playLoop();
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			} 
			
	    } else
			try {
				gameM.stop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
       
		if((WorldManager.getCurrentWorld().equals(gM.getGame().dung1) || WorldManager.getCurrentWorld().equals(gM.getGame().dung2)) && statusA[countM] == "play") {
			
			if(!(dung3M.status == "play")) {
				try {
					//dung1M.restart();
					//dung1M.playLoop();
					dung2M.restart();
					dung2M.playLoop();
					dung3M.restart();
					dung3M.playLoop();
					gateM.restart();
					gateM.play();
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			} 
			
	    } else
			try {
				//dung1M.stop();
				dung2M.stop();
				dung3M.stop();
				gateM.stop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		
		
		
		/*
		if(isChased() && (WorldManager.getCurrentWorld().equals(gM.getGame().dung1) || WorldManager.getCurrentWorld().equals(gM.getGame().dung2))) {
			
			if(!(chaseM.status == "play")) {
				try {
					chaseM.restart();
					chaseM.play();
					
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			} 
			
	    } else
			try {
				chaseM.stop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		
		*/

	 }
	
	/*
	 private boolean isChased() {

		for(GameObject o : gM.getGoM().objects)
		{
			if(o.id == ID.Player)
			{
				px = o.getX();
				py = o.getY();
			}
			
		}
		double dx = x - px;
		double dy = y - py;
		double res = Math.sqrt((dx * dx) + (dy * dy));
		if( res < 200)
			return true;
		else return false;
		
		
	 }
	 */

	public String getStatusM() {
		return statusM;
	}

	public void setStatusM(String statusM) {
		this.statusM = statusM;
	}

	public void collision()
	{
		for(int i = 0; i < gM.getGoM().objects.size(); i++)
		{
			GameObject o = gM.getGoM().objects.get(i);
			if(o.getId() == ID.Block  || o.getId() == ID.Tree || o.getId() == ID.Rock)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					x += moveX * -1;
					y += moveY * -1;
				}
			}
			else if(o.getId() == ID.Enemy)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					health -= 2;
					
				}
			}
			else if(o.getId() == ID.Crate)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					axes += 20;
					o.die();
					try {
						crateM = new AudioPlayer("Crate.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					crateM.play();
				}
				
			}
			else if(o.getId() == ID.Gate)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					//System.out.println("true");
					if(o.getgID() == 1) {
						WorldManager.setCurrentWorld(gM.getGame().dung1, gM.getPlayer(), gM, 100, 200);
					}
					else if(o.getgID() == 2)
						WorldManager.setCurrentWorld(gM.getGame().dung2, gM.getPlayer(), gM, 100, 1000);
					else if(o.getgID() == 3)
						WorldManager.setCurrentWorld(gM.getGame().dung1, gM.getPlayer(), gM, 100, 200);
					
					else if(o.getgID() == 100)
						WorldManager.setCurrentWorld(gM.getGame().mainWorld, gM.getPlayer(), gM, 100, 200);
					
					 
				}
			}
			else if(o.getId() == ID.Shop)
			{
				if(getBounds().intersects(o.getBounds()) && gM.getKeyM().interect)
				{
					gM.getShopUI().open();
					gM.getKeyM().interect = false;
				}
			}
		}
		
		for(int i = 0; i < gM.getGoM().items.size(); i++)
		{
			GameObject o = gM.getGoM().items.get(i);
		
			if(o.getId() == ID.Wood || o.getId() == ID.Stone)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					o.die();
					try {
						itemM = new AudioPlayer("itemP.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					itemM.play();
				}
			}
		}
	}
	
	public void move()
	{
		x += moveX;
		y += moveY;
	}

	public void checkStats()
	{
	    //health
		if(health <= 0)
			die();
		if(health > maxHealth)
			health = maxHealth;

		//Skill
        plevel = exp / 1000;

        //Axes
        if(axes> maxAxes)
            axes = maxAxes;
	}

	public void getInput()
	{
		moveX = 0;
		moveY = 0;
		
		if(gM.getKeyM().down)
			moveY = speed;
		if(gM.getKeyM().up)
			moveY = -speed;
		if(gM.getKeyM().left)
			moveX = - speed;
		if(gM.getKeyM().right)
			moveX = speed;
		if(gM.getKeyM().tp)
		{
			if(kunai == 1)
				return;
			try {
				warp = new AudioPlayer("warping.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			warp.play();
			for(int i = 0; i < gM.getGoM().bullets.size(); i++)
			{
				GameObject b = gM.getGoM().bullets.get(i);
			
			    if(b.getId() == ID.Kunai)
				{
			    	
			    	for(int j = 0; j < gM.getGoM().objects.size(); j++)
					{
						GameObject o = gM.getGoM().objects.get(j);
						if(o.getId() == ID.Block  )
						{
							if(new Rectangle(b.getX(),b.getY(),32,64).intersects(o.getBoundsBig()))
							{
								y = b.getY() - 32;
								b.die();
								kunai++;
								return;
							}else
							{
								x = b.getX();
								y = b.getY();
								//System.out.println("false");
							}
						}
					}
			    	
					b.die();
					kunai++;
				}
			}
		}
		else if(gM.getKeyM().inventory)
		{
			gM.getInventory().open();
			gM.getKeyM().inventory = false;
		}
		else if(gM.getKeyM().interect)
		{
			gM.getKeyM().interect = false;
		}
		else if(gM.getKeyM().skillUI)
		{
			gM.getSkillUI().open();
			gM.getKeyM().skillUI = false;
		}
		else if(gM.getKeyM().healthPotion)
		{
			gM.getKeyM().healthPotion = false;
			healthPotion--;
			health = maxHealth;
		}
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getCurrentAnimationFrame(), x, y, 40, 48, null);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(moveX < 0){
			return animLeft.getCurrentFrame();
		}else if(moveX > 0){
			return animRight.getCurrentFrame();
		}else if(moveY < 0){
			return animUp.getCurrentFrame();
		}else if(moveY > 0){
			return animDown.getCurrentFrame();
		}else {
			return Assets.player_down[1];
		}
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void die() 
	{
		try {
			dung2M.stop();
			dung3M.stop();
			dieM = new AudioPlayer("game-over.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		dieM.play();
		
		JOptionPane.showMessageDialog(gM.getFrame(), "You Died!", "GAME OVER!!!", JOptionPane.PLAIN_MESSAGE);
		States.setState(gM.getGame().menuState);
		gM.getGame().fileM.newGame();
	}

	
	public int getAxes() {
		return axes;
	}

	public void setAxes(int axes) {
		this.axes = axes;
	}

	public int getHealth()
	{
		return health;
	}

	public int getKunai() {
		return kunai;
	}

	public void setKunai(int kunai) {
		this.kunai = kunai;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getRock() {
		return rock;
	}

	public void setRock(int rock) {
		this.rock = rock;
	}
}
