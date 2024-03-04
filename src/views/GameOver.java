package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame implements ActionListener{
	JButton play;
	JButton quit;
	
	public GameOver(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(700,300,600,400);
		getContentPane().setBackground(new Color (226,220,205));
		getContentPane().setLayout(null);
		
		JLabel l=new JLabel("GAME OVER");
		l.setFont(new Font("Bauhaus 93",Font.PLAIN,50));
		l.setBounds(165,0,400,200);
		getContentPane().add(l);
		
		play=new JButton("Play Again");
		play.setFont(new Font("Serif",Font.BOLD,20));
		play.setBounds(125,200,150,75);
		play.setForeground(Color.WHITE);
		play.setBackground(new Color(78,92,56));
		getContentPane().add(play);
		play.addActionListener(this);
		
		quit=new JButton("Quit");
		quit.setFont(new Font("Serif",Font.BOLD,20));
		quit.setBounds(325,200,150,75);
		quit.setBackground(Color.RED);
		getContentPane().add(quit);
		quit.addActionListener(this);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==play){
			new GUI();
			setVisible(false);
		}
		else{
			dispose();
			GUI.clip.stop();
		}
		
		
		
		
	}
}
