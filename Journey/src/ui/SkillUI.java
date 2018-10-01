package ui;

import mainGame.GameManager;

import javax.swing.*;

import assets.Assets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillUI
{
    private GameManager gM;
    private JFrame f;
    private JDialog d;
    private int skillPoints = 0;
    private int healthp = 0,axeCap = 0;
    public SkillUI(GameManager gM)
    {
        this.gM = gM;
    }

    public void open()
    {
        skillPoints = calculateSkillPoint();

        f = gM.getFrame();
        d = new JDialog(f, "Skill");
        d.setLayout(null);
        d.setModal(true);
        d.setSize(300,300);
        d.setLocationRelativeTo(f);
        d.setResizable(false);

        JLabel jl1 = new JLabel("Skill Points: " + skillPoints);
        JLabel jl2 = new JLabel("Health:");
        JLabel jl3 = new JLabel ("Axe Cap:");
        JLabel j6 = new JLabel(new ImageIcon(Assets.backGround));
        
        JButton jb1 = new JButton();
        JButton jb2 = new JButton();
        JButton jb3 = new JButton();
        JButton jb4 = new JButton();

        JTextField jtf1 = new  JTextField();
        JTextField jtf2 = new  JTextField();

        //JTextField
        jtf1.setLocation(84,80);
        jtf1.setSize(64,32);
        jtf1.setText("" + healthp);
        jtf1.setEditable(false);

        jtf2.setLocation(84,140);
        jtf2.setSize(64,32);
        jtf2.setText("" + axeCap);
        jtf2.setEditable(false);

        //JLable
        jl1.setLocation(20,20);
        jl1.setSize(90,16);
        jl2.setLocation(20, 86);
        jl2.setSize(60,16);
        jl3.setLocation(20,146);
        jl3.setSize(60,16);

        //JButton
        jb1.setText("+");
        jb1.setLocation(148,80);
        jb1.setSize(44,32);
        jb1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int amount = Integer.parseInt(jtf1.getText());
                if(skillPoints>0)
                {
                    healthp++;
                    gM.getPlayer().maxHealth += 20;
                    skillPoints--;
                    jl1.setText("Skill Points: " + skillPoints);
                }


                jtf1.setText("" + healthp);
            }
        });

        jb2.setText("+");
        jb2.setLocation(148,140);
        jb2.setSize(44,32);
        jb2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int amount = Integer.parseInt(jtf2.getText());
                if(skillPoints>0)
                {
                    axeCap++;
                    gM.getPlayer().maxAxes += 20;
                    skillPoints--;
                    jl1.setText("Skill Points: " + skillPoints);
                }

                jtf2.setText("" + axeCap);
            }
        });
        
        j6.setLocation(0,0);
		j6.setSize(1000,563);

        d.add(jtf1);
        d.add(jtf2);
        d.add(jl1);
        d.add(jl2);
        d.add(jl3);
        d.add(jb1);
        d.add(jb2);
        d.add(j6);
        d.setVisible(true);
        d.pack();
    }
    public int calculateSkillPoint()
    {
        return gM.getPlayer().plevel - (healthp + axeCap);
    }

}
