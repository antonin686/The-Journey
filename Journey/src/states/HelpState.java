package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import assets.Assets;
import mainGame.GameManager;

public class HelpState extends States
{

	public HelpState(GameManager gM) 
	{
		super(gM);
	}

	@Override
	public void tick() 
	{
		if(gM.getKeyM().esc)
		{
			
			States.setState(gM.getGame().menuState);
			
		}
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.backGround, 0, 0,gM.getWidth(),gM.getHeight(), null);
		g.setColor(Color.BLACK);
		g.fillRect(190, 90, gM.getWidth()/2 + 120, gM.getHeight()/2 + 120);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(200, 100, gM.getWidth()/2 + 100, gM.getHeight()/2 + 100);
		drawStrings(g);
		
	}
	
	public void drawStrings(Graphics g)
	{
		int y = 160;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20)); 
		g.drawString("UP", 400, y); g.drawString("- W", 550, y);
		g.drawString("Down", 400, y+= 30); g.drawString("- S", 550, y);
		g.drawString("Left", 400, y+= 30); g.drawString("- A", 550, y);
		g.drawString("Right", 400, y+= 30); g.drawString("- D", 550, y);
		g.drawString("Fire Axe", 400, y+= 30); g.drawString("- LMB", 550, y);
		g.drawString("Fire Kunai", 400, y+= 30); g.drawString("- RMB", 550, y);
		g.drawString("TelePort", 400, y+= 30); g.drawString("- SPACE", 550, y);
		g.drawString("Skill", 400, y+= 30); g.drawString("- P", 550, y);
		g.drawString("Inventory", 400, y+= 30); g.drawString("- I", 550, y);
		g.drawString("Interect", 400, y+= 30); g.drawString("- E", 550, y);
		
	}

}
