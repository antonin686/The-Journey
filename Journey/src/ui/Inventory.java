package ui;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import assets.Assets;
import mainGame.GameManager;

public class Inventory 
{
	
	
	private GameManager gM;
	private JFrame f;
	private JDialog d;
	public Inventory(GameManager gM) 
	{
		this.gM = gM;
	
	}

	public  void open()
	{
		f = gM.getFrame();
		d = new JDialog(f,"Inventory");
		d.setLayout(null);
		d.setModal(true);
		d.setSize(300, 300);
		d.setLocationRelativeTo(f);
		d.setResizable(false);
		
		//JLabel j2 = new JLabel(new ImageIcon(Assets.rock));
		//JLabel j3 = new JLabel(new ImageIcon(Assets.wood));
		
		JLabel j1 = new JLabel("Cash "+ gM.getPlayer().cash);
		JLabel j2 = new JLabel("Axes: " + gM.getPlayer().axes);
		JLabel j3 = new JLabel("Wood: " + gM.getPlayer().wood);
		JLabel j4 = new JLabel("Rock: " + gM.getPlayer().rock);
		JLabel j5 = new JLabel("Potions: " + gM.getPlayer().healthPotion);
		JLabel j6 = new JLabel(new ImageIcon(Assets.backGround));
		
		d.add(j1);
		d.add(j2);
		d.add(j3);
		d.add(j4);
		d.add(j5);
		d.add(j6);
	
		
		j1.setLocation(20,20);
		j2.setLocation(150, 20);
		j3.setLocation(20, 80);
		j4.setLocation(20, 140);
		j5.setLocation(20, 200);
		j6.setLocation(0,0);
		j1.setSize(64, 64);
		j2.setSize(64, 64);
		j3.setSize(64, 64);
		j4.setSize(64, 64);
		j5.setSize(64, 64);
		j6.setSize(1000,563);
		
		
		d.setVisible(true);
		//d.pack();
	}
	
	
}
