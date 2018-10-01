package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

import assets.Assets;
import assets.AudioPlayer;
import gameObject.Axes;
import gameObject.Kunai;
import mainGame.GameManager;
import states.States;

public class MouseManager implements MouseListener, MouseMotionListener
{
	private AudioPlayer fireM, fire2M;
	private GameManager gM;
	public boolean attack;
	private int mouseX, mouseY;
	public boolean leftPressed, rightPressed;
	
	public MouseManager(GameManager gM) 
	{
		this.gM = gM;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(States.getState() == gM.getGame().gameState)
		{
			int mx = (int) (e.getX() + gM.getCam().getX());
			int my = (int) (e.getY() + gM.getCam().getY());
			
			int px = gM.getPlayer().getX();
			int py = gM.getPlayer().getY();
			
			if(SwingUtilities.isLeftMouseButton(e))
			{	
				
				if(gM.getPlayer().getAxes() > 0)
				{
					try {
						fireM = new AudioPlayer("fireAxe.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fireM.play();
					
					gM.getGoM().addBullet(new Axes(px + 16, py + 24,gM, mx, my));
					gM.getPlayer().setAxes((gM.getPlayer().getAxes()- 1));		
				}
				
			}
				
			if(SwingUtilities.isRightMouseButton(e))
			{
				
				if(gM.getPlayer().getKunai() > 0)
				{
					try {
						fire2M = new AudioPlayer("spear.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fire2M.play();
					gM.getGoM().addBullet(new Kunai(px + 16, py +24,gM, mx, my));
					gM.getPlayer().setKunai((gM.getPlayer().getKunai()- 1));
				}
			}
		}else
		{
			if(e.getButton() == MouseEvent.BUTTON1)
				leftPressed = true;
			else if(e.getButton() == MouseEvent.BUTTON3)
				rightPressed = true;
		}
		
	
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
	}
	
	
}
