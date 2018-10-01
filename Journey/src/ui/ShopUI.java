package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import assets.Assets;
import mainGame.GameManager;

public class ShopUI
{
	private GameManager gM;
	private JFrame f;
	private JDialog d;
	public ShopUI(GameManager gM) 
	{
		this.gM = gM;
	
	}

	public  void open()
	{
		f = gM.getFrame();
		d = new JDialog(f,"Shop");
		d.setLayout(null);
		d.setModal(true);
		d.setSize(300, 300);
		d.setLocationRelativeTo(f);
		d.setResizable(false);
		
		
		String itemsSell[] = {"Wood(50$)","Rock(70$)"};
		String itemsBuy[] = {"Axes(1000$)","Salve(2000$)"}; 
		
		JLabel j1 = new JLabel("Amount:");
		JLabel j2 = new JLabel("Cash: " + gM.getPlayer().cash);
		JLabel j3 = new JLabel("Wood: " + gM.getPlayer().wood);
		JLabel j4 = new JLabel("Rock: " + gM.getPlayer().rock);
		JLabel j6 = new JLabel(new ImageIcon(Assets.backGround));
		JTextField jtf = new  JTextField();
		
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		JButton jb3 = new JButton();
		JButton jb4 = new JButton();
		
		JComboBox cb1 = new JComboBox(itemsSell); 
		JComboBox cb2 = new JComboBox(itemsBuy);
		
		
		//JLable
		j1.setLocation(96,120);
		j2.setLocation(20, 20);
		j3.setLocation(110, 20);
		j4.setLocation(200,20);
		
		j1.setSize(64, 32);
		j2.setSize(90, 16);
		j3.setSize(90, 16);
		j4.setSize(90, 16);
		
		
		//JTextField
		jtf.setLocation(160,120);
		jtf.setSize(64,34);
		jtf.setText("0");
		jtf.setEditable(false);
		
		
		//JButton
		jb1.setText("+");
		jb1.setLocation(224,120);
		jb1.setSize(44,16);
		jb1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int amount = Integer.parseInt(jtf.getText());
				amount++;
				jtf.setText("" + amount);
			}
		});
		
		jb2.setText("-");
		jb2.setLocation(224,136);
		jb2.setSize(44,16);
		jb2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int amount = Integer.parseInt(jtf.getText());
				if(amount >0)
					amount--;
				jtf.setText("" + amount);
			}
		});
		
		jb3.setText("Sell");
		jb3.setLocation(180,60);
		jb3.setSize(60,20);
		jb3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int amount = Integer.parseInt(jtf.getText());
				String choice = (String) cb1.getItemAt(cb1.getSelectedIndex());
				if(amount > 0)
				{
					switch (choice) {
						case "Wood(50$)":
							if (gM.getPlayer().wood - amount >= 0) {
								gM.getPlayer().wood -= amount;
								gM.getPlayer().cash += amount * 50;
								j2.setText("Cash: " + gM.getPlayer().cash);
								j3.setText("Wood: " + gM.getPlayer().wood);
							} else
								System.out.println("Not Enought Wood");
							break;
						case "Rock(70$)":
							if (gM.getPlayer().rock - amount >= 0) {
								gM.getPlayer().rock -= amount;
								gM.getPlayer().cash += amount * 70;
								j2.setText("Cash: " + gM.getPlayer().cash);
								j4.setText("Rock: " + gM.getPlayer().rock);
							} else
								System.out.println("Not Enought Rock");
							break;
					}
				}
			}
		});
		
		jb4.setText("Buy");
		jb4.setLocation(180,190);
		jb4.setSize(60,20);
		jb4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int amount = Integer.parseInt(jtf.getText());
				String choice = (String) cb2.getItemAt(cb2.getSelectedIndex());
				if(amount == 0)
					return;
				
				switch(choice)
				{
					case "Axes(1000$)":
						if(gM.getPlayer().cash >= 1000 * amount)
						{
							gM.getPlayer().cash -= 1000 * amount;
							gM.getPlayer().axes += amount * 20;
							j2.setText("Cash: " + gM.getPlayer().cash);
						}else
							System.out.println("Not Enought Cash");
					break;
					case "Salve(2000$)":
						if(gM.getPlayer().cash >= 2000 * amount)
						{
							gM.getPlayer().cash -= 2000 * amount;
							gM.getPlayer().healthPotion += amount;
							j2.setText("Cash: " + gM.getPlayer().cash);
						}else
							System.out.println("Not Enought Cash");
					break;
				}
				
			}
		});
		
		//JComboBox
		cb1.setBounds(50,60,100,20);
		cb2.setBounds(50,190,100,20);
		j6.setLocation(0,0);
		j6.setSize(1000,563);
		
		
		d.add(j1);
		d.add(j2);
		d.add(j3);
		d.add(j4);
		
		d.add(jtf);
		d.add(jb1);
		d.add(jb2);
		d.add(jb3);
		d.add(jb4);
		d.add(cb1);
		d.add(cb2);
		d.add(j6);
		d.setVisible(true);
		d.pack();
	}
}
