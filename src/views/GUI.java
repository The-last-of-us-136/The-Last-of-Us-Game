package views;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.*;
import java.io.IOException;

import javax.swing.*;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import engine.Game;
import model.characters.*;
import model.world.*;
import model.collectibles.*;

public class GUI extends JFrame implements ActionListener{
	private JButton start;
	private JLabel pick;
	private static ButtonGroup heroes;
	public static JRadioButton h1;
	public static JRadioButton h2;
	public static JRadioButton h3;
	public static JRadioButton h4;
	public static JRadioButton h5;
	public static JRadioButton h6;
	public static JRadioButton h7;
	public static JRadioButton h8;
	public static Clip clip=null;
	
	
	public GUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1925,1075);
		Color offwhite= new Color (226,220,205);
		Color green= new Color(115,195,108);
		Color brown=new Color(101,67,33);
		getContentPane().setBackground(offwhite);
		getContentPane().setLayout(null);
		start = new JButton("Start Game");
		start.setBounds(800,900,300,50);
		start.setFont(new Font("Times New Roman",Font.BOLD,40));
		start.setBackground (green);
		getContentPane().add(start);
		start.addActionListener(this);
		
		pick=new JLabel("PICK YOUR HERO");
		pick.setBounds(750,10,600,100);
		pick.setFont(new Font("Times New Roman",Font.BOLD,50));
		pick.setForeground(brown);
		getContentPane().add(pick);
		
		ImageIcon i1=new ImageIcon("src/Images/JOEL.jpeg");
		h1 = new JRadioButton();
		h1.setBounds(100,100,300,250);
		JLabel l1=new JLabel(i1);
		h1.setBackground(Color.BLACK);
		h1.setLayout(null);
		l1.setBounds(20,0,300,250);
		h1.add(l1);
		JTextArea t1=new JTextArea();
		t1.setBackground(offwhite);
		t1.setBounds(100,350,300,128);
		t1.setForeground(brown);
		t1.setEditable(false);
		t1.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr1= "Name: Joel Miller"+'\n'+"Type: Fighter"+'\n'+"HP: 140"+'\n'+"Actions: 5"+'\n'+"Attack Damage: 30";
		t1.setText(attr1);
		t1.setVisible(true);
		getContentPane().add(t1);
		getContentPane().add(h1);
		
		ImageIcon i2=new ImageIcon("src/Images/ELLIE.jpeg");
		h2 = new JRadioButton();
		h2.setBounds(550,100,300,250);
		JLabel l2=new JLabel(i2);
		h2.setBackground(Color.BLACK);
		h2.setLayout(null);
		l2.setBounds(20,0,300,250);
		h2.add(l2);
		JTextArea t2=new JTextArea();
		t2.setBounds(550,350,300,128);
		t2.setBackground(offwhite);
		t2.setForeground(brown);
		t2.setEditable(false);
		t2.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr2= "Name: Ellie Williams"+'\n'+"Type: Medic"+'\n'+"HP: 110"+'\n'+"Actions: 6"+'\n'+"Attack Damage: 15";
		t2.setText(attr2);
		t2.setVisible(true);
		getContentPane().add(t2);
		getContentPane().add(h2);
		
		ImageIcon i3=new ImageIcon("src/Images/TESS.jpeg");
		h3 = new JRadioButton();
		h3.setBounds(1000,100,300,250);
		JLabel l3=new JLabel(i3);
		h3.setBackground(Color.BLACK);
		h3.setLayout(null);
		l3.setBounds(20,0,300,250);
		h3.add(l3);
		JTextArea t3=new JTextArea();
		t3.setBounds(1000,350,300,128);
		t3.setBackground(offwhite);
		t3.setForeground(brown);
		t3.setEditable(false);
		t3.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr3= "Name: Tess"+'\n'+"Type: Explorer"+'\n'+"HP: 80"+'\n'+"Actions: 6"+'\n'+"Attack Damage: 20";
		t3.setText(attr3);
		t3.setVisible(true);
		getContentPane().add(t3);
		getContentPane().add(h3);
		
		ImageIcon i4=new ImageIcon("src/Images/RILEY.jpeg");
		h4 = new JRadioButton();
		h4.setBounds(1450,100,300,250);
		JLabel l4=new JLabel(i4);
		h4.setBackground(Color.BLACK);
		h4.setLayout(null);
		l4.setBounds(20,0,300,250);
		h4.add(l4);
		JTextArea t4=new JTextArea();
		t4.setBounds(1450,350,300,128);
		t4.setBackground(offwhite);
		t4.setForeground(brown);
		t4.setEditable(false);
		t4.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr4= "Name: Riley Abel"+'\n'+"Type: Explorer"+'\n'+"HP: 90"+'\n'+"Actions: 5"+'\n'+"Attack Damage: 25";
		t4.setText(attr4);
		t4.setVisible(true);
		getContentPane().add(t4);
		getContentPane().add(h4);
		
		ImageIcon i5=new ImageIcon("src/Images/TOMMY.jpeg");
		h5 = new JRadioButton();
		h5.setBounds(100,500,300,250);
		JLabel l5=new JLabel(i5);
		h5.setBackground(Color.BLACK);
		h5.setLayout(null);
		l5.setBounds(20,0,300,250);
		h5.add(l5);
		JTextArea t5=new JTextArea();
		t5.setBounds(100,750,300,128);
		t5.setBackground(offwhite);
		t5.setForeground(brown);
		t5.setEditable(false);
		t5.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr5= "Name: Tommy Miller"+'\n'+"Type: Explorer"+'\n'+"HP: 95"+'\n'+"Actions: 5"+'\n'+"Attack Damage: 25";
		t5.setText(attr5);
		t5.setVisible(true);
		getContentPane().add(t5);
		getContentPane().add(h5);
		
		ImageIcon i6=new ImageIcon("src/Images/BILL.jpeg");	
		h6 = new JRadioButton();
		h6.setBounds(550,500,300,250);
		JLabel l6=new JLabel(i6);
		h6.setBackground(Color.BLACK);
		h6.setLayout(null);
		l6.setBounds(20,0,300,250);
		h6.add(l6);
		JTextArea t6=new JTextArea();
		t6.setBounds(550,750,300,128);
		t6.setBackground(offwhite);
		t6.setForeground(brown);
		t6.setEditable(false);
		t6.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr6= "Name: Bill"+'\n'+"Type: Medic"+'\n'+"HP: 100"+'\n'+"Actions: 7"+'\n'+"Attack Damage: 10";
		t6.setText(attr6);
		t6.setVisible(true);
		getContentPane().add(t6);
		getContentPane().add(h6);
		
	
		ImageIcon i7=new ImageIcon("src/Images/DAVID.jpeg");
		JLabel l7=new JLabel(i7);
		h7 = new JRadioButton();
		h7.setBounds(1000,500,300,250);
		h7.setBackground(Color.BLACK);
		h7.setLayout(null);
		l7.setBounds(20,0,300,250);
		h7.add(l7);
		JTextArea t7=new JTextArea();
		t7.setBounds(1000,750,300,128);
		t7.setBackground(offwhite);
		t7.setForeground(brown);
		t7.setEditable(false);
		t7.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr7= "Name: David"+'\n'+"Type: Fighter"+'\n'+"HP: 150"+'\n'+"Actions: 4"+'\n'+"Attack Damage: 35";
		t7.setText(attr7);
		t7.setVisible(true);
		getContentPane().add(t7);
		getContentPane().add(h7);

		ImageIcon i8=new ImageIcon("src/Images/HENRY.jpeg");
		h8 = new JRadioButton();
		h8.setBounds(1450,500,300,250);
		JLabel l8=new JLabel(i8);
		h8.setBackground(Color.BLACK);
		h8.setLayout(null);
		l8.setBounds(20,0,300,250);
		h8.add(l8);
		JTextArea t8=new JTextArea();
		t8.setBounds(1450,750,300,128);
		t8.setBackground(offwhite);
		t8.setForeground(brown);
		t8.setEditable(false);
		t8.setFont(new Font(Font.MONOSPACED, Font.BOLD,19));
		String attr8= "Name: Henry Burell"+'\n'+"Type: Medic"+'\n'+"HP: 105"+'\n'+"Actions: 6"+'\n'+"Attack Damage: 15";
		t8.setText(attr8);
		t8.setVisible(true);
		getContentPane().add(t8);
		getContentPane().add(h8);

		heroes=new ButtonGroup();
		heroes.add(h1);
		heroes.add(h2);
		heroes.add(h3);
		heroes.add(h4);
		heroes.add(h5);
		heroes.add(h6);
		heroes.add(h7);
		heroes.add(h8);
		
		this.addMouseListener(new MouseListener() {
           
            public void mouseEntered(MouseEvent e) {
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Images/main cursor.png").getImage(),new Point(0,0),"custom cursor"));
            }
           
            public void mouseExited(MouseEvent e) {
                //setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Images/hand cursor.png").getImage(),new Point(0,0),"custom cursor"));
            }
			
			public void mouseClicked(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseReleased(MouseEvent arg0) {	
			}
        });
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			try {
				Game.loadHeroes("src/Heroes.csv");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(heroes.getSelection()==null){
				JOptionPane.showMessageDialog(this, "Please select a hero");
				return;
			}
			this.setVisible(false);
			GUI2 game=new GUI2();
		}
		
	}
	public static void main(String[]  args){
		GUI selectHero= new GUI();	
		try{
			File musicPath=new File("src/views/The Last of Us.wav");
			AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);
			clip =AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		}catch(Exception e){
			e.printStackTrace();
		}
  }

}
