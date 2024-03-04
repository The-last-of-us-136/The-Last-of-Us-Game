package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.world.*;
import model.collectibles.*;

public class GUI2 extends JFrame implements ActionListener{
	private JButton attackButton;
	private JButton cureButton;
	private JButton usespecialButton;
	private JButton endturnButton;
	private JLabel attack;
	private JLabel cure;
	private JLabel usespecial;
	private JLabel endturn;
	private JButton right;
	private JButton left;
	private JButton up;
	private JButton down;
	private JLabel yaxis;
	private JLabel chooset;
	private JLabel playing;
	private JLabel unlocked;
	private JLabel sup;
	private JLabel vac;
	private JLabel hpb;
	
	private JPanel Map= new JPanel(new GridLayout(15,15));
	private JPanel heroesPanel;
	private JLabel activeImage= new JLabel();
	private JTextArea activeAtt= new JTextArea();
	private JProgressBar activeHP=new JProgressBar();
	private JPanel activeVaccine=new JPanel();
	private JPanel activeSupply=new JPanel();
	private ArrayList<ImageIcon> Images =new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> Images2 =new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> Images3 =new ArrayList<ImageIcon>();
	private ArrayList<String> Names=new ArrayList<String>();
	private JButton[][] MapButtons =new JButton[15][15];
	private JLabel[] VInventory=new JLabel[5];
	private JLabel[] SInventory=new JLabel[5];
	private Hero currentHero;
	private Character currentTarget;
	private JComboBox<String> target=new JComboBox<String>();
	
	private JButton AI;
	private ArrayList<Point> discovered=new ArrayList<Point>();
	
	Timer timer = new Timer(1000, new ActionListener() {
        int i = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(Game.checkGameOver()==false){
        		if(i<Game.heroes.size()){
            		Hero h=Game.heroes.get(i);
	            	if(h.getActionsAvailable()>0){
	            	   selectedHero(h);
	            	   AI(h);
	            	   updateMap();
                       selectedHero(h);
                       updateHeroPanel();
                       targetList();
                       if(Game.checkWin() || Game.checkGameOver()){
                    	   AI.doClick();
                       }
	            	}
	            	else{
	            		i++;
	            		discovered.clear();
	            	}
            	}
            	else{
            		i=0;
            		try {
    					Game.endTurn();
    					playMusic("src/views/Zombie Roar Sound Effect.wav");
    				} catch (Exception e1) {
    				}
            	}
        	}
            
        }
    });
	
	public GUI2(){
		
		Images.add(new ImageIcon("src/Images/JOEL.jpeg"));
		Images.add(new ImageIcon("src/Images/ELLIE.jpeg"));
		Images.add(new ImageIcon("src/Images/TESS.jpeg"));
		Images.add(new ImageIcon("src/Images/RILEY.jpeg"));
		Images.add(new ImageIcon("src/Images/TOMMY.jpeg"));
		Images.add(new ImageIcon("src/Images/BILL.jpeg"));
		Images.add(new ImageIcon("src/Images/DAVID.jpeg"));
		Images.add(new ImageIcon("src/Images/HENRY.jpeg"));
		Images2.add(new ImageIcon("src/Images/JOEL2.jpg"));
		Images2.add(new ImageIcon("src/Images/ELLIE2.jpg"));
		Images2.add(new ImageIcon("src/Images/TESS2.jpg"));
		Images2.add(new ImageIcon("src/Images/RILEY2.jpg"));
		Images2.add(new ImageIcon("src/Images/TOMMY2.jpg"));
		Images2.add(new ImageIcon("src/Images/BILL2.jpg"));
		Images2.add(new ImageIcon("src/Images/DAVID2.jpg"));
		Images2.add(new ImageIcon("src/Images/HENRY2.jpg"));
		Images3.add(new ImageIcon("src/Images/JOEL3.jpeg"));
		Images3.add(new ImageIcon("src/Images/ELLIE3.jpeg"));
		Images3.add(new ImageIcon("src/Images/TESS3.jpeg"));
		Images3.add(new ImageIcon("src/Images/RILEY3.jpeg"));
		Images3.add(new ImageIcon("src/Images/TOMMY3.jpeg"));
		Images3.add(new ImageIcon("src/Images/BILL3.jpeg"));
		Images3.add(new ImageIcon("src/Images/DAVID3.jpeg"));
		Images3.add(new ImageIcon("src/Images/HENRY3.jpeg"));
		Names.add("Joel Miller");
		Names.add("Ellie Williams");
		Names.add("Tess");
		Names.add("Riley Abel");
		Names.add("Tommy Miller");
		Names.add("Bill");
		Names.add("David");
		Names.add("Henry Burell");
		
		currentHero=null;
		int heroIndex=0;
		if(GUI.h1.isSelected()){
			heroIndex=0;
		}else if(GUI.h2.isSelected()){
			heroIndex=1;
		}else if(GUI.h3.isSelected()){
			heroIndex=2;
		}else if(GUI.h4.isSelected()){
			heroIndex=3;
		}else if(GUI.h5.isSelected()){
			heroIndex=4;
		}else if(GUI.h6.isSelected()){
			heroIndex=5;
		}else if(GUI.h7.isSelected()){
			heroIndex=6;
		}else if(GUI.h8.isSelected()){
			heroIndex=7;
		}
		currentHero=Game.availableHeroes.get(heroIndex);
		Game.startGame(currentHero);
		
		
		ImageIcon a=new ImageIcon("src/Images/attack.jpeg");
		attackButton=new JButton(a);
		attackButton.setBounds(70,100,90,90);
		getContentPane().add(attackButton);
		attackButton.addActionListener(this);
		
		attack=new JLabel("Attack");
		attack.setBounds(85,165,70,70);
		getContentPane().add(attack);
		attack.setFont(new Font("Serif",Font.BOLD,20));
		attack.setForeground(new Color(53,126,199));
		//attack.addMouseListener();
		
		
		ImageIcon c=new ImageIcon("src/Images/cure.jpeg");
		cureButton=new JButton(c);
		cureButton.setBounds(70,360,90,90);
		getContentPane().add(cureButton);
		cureButton.addActionListener(this);
		
		cure=new JLabel("Cure");
		cure.setBounds(95,425,70,70);
		getContentPane().add(cure);
		cure.setFont(new Font("Serif",Font.BOLD,20));
		cure.setForeground(new Color(53,126,199));
		
		ImageIcon v=new ImageIcon("src/Images/special.jpeg");
		usespecialButton=new JButton(v);
		usespecialButton.setBounds(70,230,90,90);
		//usespecialButton.setBorderPainted(false);
		getContentPane().add(usespecialButton);
		usespecialButton.addActionListener(this);
		
		usespecial=new JLabel("Use Special");
		usespecial.setBounds(68,285,100,100);
		getContentPane().add(usespecial);
		usespecial.setFont(new Font("Serif",Font.BOLD,20));
		usespecial.setForeground(new Color(53,126,199));
		 
		
		ImageIcon e=new ImageIcon("src/Images/end.jpg");
		endturnButton=new JButton(e);
		endturnButton.setBounds(70,490 ,90,90);
		getContentPane().add(endturnButton);
		endturnButton.addActionListener(this);
		
		endturn=new JLabel("End Turn");
		endturn.setBounds(75,540,100,100);
		getContentPane().add(endturn);
		endturn.setFont(new Font("Serif",Font.BOLD,20));
		endturn.setForeground(new Color(53,126,199));
		
		
		this.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Images/main cursor.png").getImage(),new Point(0,0),"custom cursor"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Images/hand cursor.png").getImage(),new Point(0,0),"custom cursor"));
            }
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
        });
		
		AI=new JButton("Start AI");
		AI.setBounds(70,620,90,90);
		AI.setBackground(Color.BLACK);
		AI.setForeground(Color.WHITE);
		getContentPane().add(AI);
		AI.addActionListener(this);

		
		
		chooset=new JLabel("Select target (x,y)");
		chooset.setBounds(50,800,300,50);
		chooset.setForeground(new Color(53,126,199));
		chooset.setFont(new Font("Cooper Black",Font.ITALIC,25));
		getContentPane().add(chooset);
		
		playing=new JLabel("Playing Hero");
		playing.setBounds(1550,20,300,50);
		playing.setForeground(new Color(53,126,199));
		playing.setFont(new Font("Cooper Black",Font.BOLD,40));
		getContentPane().add(playing);
		
		sup=new JLabel("Supply Inventory");
		sup.setBounds(1475,480,300,50);
		sup.setForeground(new Color(53,126,199));
		sup.setFont(new Font("Serif",Font.ITALIC,20));
		getContentPane().add(sup);
		
		vac=new JLabel("Vaccine Inventory");
		vac.setBounds(1475,380,300,50);
		vac.setForeground(new Color(53,126,199));
		vac.setFont(new Font("Serif",Font.ITALIC,20));
		getContentPane().add(vac);
		
		hpb=new JLabel("HP Bar");
		hpb.setBounds(1475,280,300,50);
		hpb.setForeground(new Color(53,126,199));
		hpb.setFont(new Font("Serif",Font.ITALIC,20));
		getContentPane().add(hpb);
		
		unlocked=new JLabel("Unlocked Heroes");
		unlocked.setBounds(170,50,300,20);
		unlocked.setForeground(new Color(53,126,199));
		unlocked.setFont(new Font("Cooper Black",Font.BOLD,27));
		getContentPane().add(unlocked);
		
		ImageIcon r=new ImageIcon("src/Images/right.jpg");
		right=new JButton(r);
		right.setBounds(1730,700,100,100);
		right.setBackground(new Color(101,67,33));
		right.setBorderPainted(false);
		getContentPane().add(right);
		right.addActionListener(this);
		
		ImageIcon l=new ImageIcon("src/Images/left.jpg");
		left=new JButton(l);
		left.setBounds(1530,700,100,100);
		left.setBackground(new Color(101,67,33));
		left.setBorderPainted(false);
		getContentPane().add(left);
		left.addActionListener(this);
		
		ImageIcon u=new ImageIcon("src/Images/up.jpg");
		up=new JButton(u);
		up.setBounds(1630,600,100,100);
		up.setBackground(new Color(101,67,33));
		up.setBorderPainted(false);
		getContentPane().add(up);
		up.addActionListener(this);
		
		ImageIcon d=new ImageIcon("src/Images/down.jpg");
		down=new JButton(d);
		down.setBounds(1630,800,100,100);
		down.setBackground(new Color(101,67,33));
		down.setBorderPainted(false);
		getContentPane().add(down);
		down.addActionListener(this);
		
		String axis="0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10    11    12    13    14";
		axis=axis.replaceAll("\t", "     ");
		yaxis=new JLabel(axis);
		yaxis.setBounds(490,970,1000,25);
		yaxis.setForeground(Color.WHITE);
		yaxis.setFont(new Font(Font.MONOSPACED, Font.PLAIN,19));
		getContentPane().add(yaxis);
		JLabel yaxis2=new JLabel("y-axis");
		yaxis2.setBounds(460,990,100,25);
		yaxis2.setForeground(Color.WHITE);
		yaxis2.setFont(new Font(Font.MONOSPACED, Font.PLAIN,19));
		getContentPane().add(yaxis2);
		
		

		int hs=35;
		for(int i=14;i>=0;i--){
			JLabel xl=new JLabel(""+i);
			xl.setBounds(435,hs,30,20);
			hs+=63;
			xl.setForeground(Color.WHITE);
			xl.setFont(new Font(Font.MONOSPACED, Font.PLAIN,19));
			getContentPane().add(xl);
		}
		int hs2=820;
		String s="x-axis";
		for(int i=0;i<s.length();i++){
			JLabel xs=new JLabel(""+s.charAt(i));
			xs.setBounds(410,hs2,20,20);
			hs2+=20;
			xs.setForeground(Color.WHITE);
			xs.setFont(new Font(Font.MONOSPACED, Font.PLAIN,19));
			getContentPane().add(xs);
		}
		
		
			
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1925,1075);

		getContentPane().setBackground(new Color(2,7,93));
		getContentPane().setLayout(null);
		Map.setBounds(460,10,1000,950);
		Map.setBackground(new Color(212,175,55));
		getContentPane().add(Map);
		
		activeAtt.setEditable(false);
		activeAtt.setBounds(1650,75,230, 210);
		activeAtt.setBackground(new Color(169,169,169));
		activeAtt.setFont(new Font("Serif",Font.BOLD,25));
		activeHP.setBounds(1475,320,400,60);
		activeVaccine.setBounds(1475,420,400,60);
		activeSupply.setBounds(1475,520,400,60);
		activeVaccine.setLayout(new GridLayout(0,5));
		activeSupply.setLayout(new GridLayout(0,5));
		
		for(int i=14;i>=0;i--){
			for(int j=0;j<15;j++){
				JButton b=new JButton();
				if(Game.map[i][j].isVisible()==false) {
					b.setIcon(new ImageIcon("src/Images/mapnotv2.jpg"));
				}
				else {
				if(Game.map[i][j] instanceof TrapCell)
					b.setIcon(new ImageIcon("src/Images/map.jpg"));
				else if(Game.map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine)
						b.setIcon(new ImageIcon("src/Images/vaccine1.jpg"));
					else
						b.setIcon(new ImageIcon("src/Images/supply3.jpg"));
				}
				else{
					if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero){				
						String name=((CharacterCell) Game.map[i][j]).getCharacter().getName();
						b.setIcon(Images2.get(Names.indexOf(name)));
					}
					else if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie)
						b.setIcon(new ImageIcon("src/Images/Zombie.jpg"));
					else 
						b.setIcon(new ImageIcon("src/Images/map.jpg"));
				}
				}
				Map.add(b);
				MapButtons[i][j]=b;
				b.addActionListener(this);
			}
		}
		
		heroesPanel =new JPanel(new GridLayout(6,1));
		heroesPanel.setBounds(195,75,200,700);
		heroesPanel.setBackground(new Color(2,7,93));

		
		for(int i=0;i<5;i++){
			JLabel vl=new JLabel();
			VInventory[i]=vl;
			activeVaccine.add(vl);
			JLabel sl=new JLabel();
			SInventory[i]=sl;
			activeSupply.add(sl);
		}
		
		
		getContentPane().add(heroesPanel);
		getContentPane().add(activeAtt);
		getContentPane().add(activeHP);
		getContentPane().add(activeImage);
		getContentPane().add(activeSupply);
		getContentPane().add(activeVaccine);
		updateHeroPanel();
		selectedHero(currentHero);
		targetList();
		
		this.setVisible(true);
		
	}
	
	
	public void updateMap(){
		//Map.removeAll();
		for(int i=14;i>=0;i--){
			for(int j=0;j<15;j++){
				if(Game.map[i][j].isVisible()==false) {
					MapButtons[i][j].setIcon(new ImageIcon("src/Images/mapnotv2.jpg"));
				}
				else {
				if(Game.map[i][j] instanceof TrapCell)
					MapButtons[i][j].setIcon(new ImageIcon("src/Images/map.jpg"));
				else if(Game.map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine)
						MapButtons[i][j].setIcon(new ImageIcon("src/Images/vaccine1.jpg"));
					else
						MapButtons[i][j].setIcon(new ImageIcon("src/Images/supply3.jpg"));
				}
				else{
					if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero){				
						String name=((CharacterCell) Game.map[i][j]).getCharacter().getName();
						MapButtons[i][j].setIcon(Images2.get(Names.indexOf(name)));
					}
					else if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie)
						MapButtons[i][j].setIcon(new ImageIcon("src/Images/Zombie.jpg"));
					else 
						MapButtons[i][j].setIcon(new ImageIcon("src/Images/map.jpg"));
				}
					
			}
				}
		}
	}

	public void updateHeroPanel(){
		heroesPanel.removeAll();
		heroesPanel.validate();
		heroesPanel.repaint();
		boolean f=false;
		for(int i=0;i<Game.heroes.size();i++){
			
			JLabel b= new JLabel();
			b.setText(Game.heroes.get(i).toString2());
			b.setFont(new Font("Serif",Font.BOLD,15));
			b.setOpaque(true);
			b.setLayout(new FlowLayout());
			if(f==true) {
				b.setForeground(Color.BLACK);
				b.setBackground(new Color(209,237,242));
			}
			else {
				b.setForeground(Color.WHITE);
				b.setBackground(new Color(64,224,207));
			}
			f= !f;
			heroesPanel.add(b);
		}
	}
	
	public void selectedHero(Hero h){
		
		for(int i=0;i<5;i++){
			if(i<h.getVaccineInventory().size())
				VInventory[i].setIcon(new ImageIcon("src/Images/vaccine1.jpg"));
			else
				VInventory[i].setIcon(null);
			if(i<h.getSupplyInventory().size())
				SInventory[i].setIcon(new ImageIcon("src/Images/supply3.jpg"));
			else
				SInventory[i].setIcon(null);
		}
		
		getContentPane().remove(activeHP);
		getContentPane().validate();
		getContentPane().repaint();
		activeHP=new JProgressBar(0,h.getMaxHp());
		activeHP.setValue(h.getCurrentHp());
		activeHP.setStringPainted(true);
		activeHP.setBounds(1475,320,400,60);
		if(((double)h.getCurrentHp()/h.getMaxHp())>=0.7)
		    activeHP.setForeground(Color.GREEN);
		else if (((double)h.getCurrentHp()/h.getMaxHp())>=0.3)
			activeHP.setForeground(Color.YELLOW);
		else
			activeHP.setForeground(Color.RED);
		getContentPane().add(activeHP);
		
		
		activeAtt.setText(h.toString());
		activeImage.setBounds(1475,110,150, 150);
		activeImage.setIcon(Images3.get(Names.indexOf(h.getName())));
		
	}
	
	public void targetList(){
		getContentPane().remove(target);
		getContentPane().validate();
		getContentPane().repaint();
		ArrayList<String> n=new ArrayList<String>();
		for(Zombie z:Game.zombies){
			int x=z.getLocation().x;
			int y=z.getLocation().y;
			if(Game.map[x][y].isVisible()){
				n.add((z.getName()+" ("+x+","+y+")"));
			}
		}
		for(int i=0;i<Game.heroes.size();i++){
			int x=Game.heroes.get(i).getLocation().x;
			int y=Game.heroes.get(i).getLocation().y;
			n.add((Game.heroes.get(i).getName())+" ("+x+","+y+")");
		}
		String[] list= new String [n.size()];
		for (int i = 0; i < n.size(); i++) {
			list[i]=n.get(i);
		}
		target=new JComboBox<String>(list);
		target.setBounds(50,850,300,50);
		target.setFont(new Font("Serif",Font.BOLD,20));
		target.setBackground(Color.WHITE);
		getContentPane().add(target);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < Game.heroes.size(); i++) {
			int x=Game.heroes.get(i).getLocation().x;
			int y=Game.heroes.get(i).getLocation().y;
			String s=Game.heroes.get(i).getName()+" ("+x+","+y+")";
			if (s.equals(target.getSelectedItem())){
				currentTarget=Game.heroes.get(i);
			}
		}
		for (Zombie z:Game.zombies) {
			int x=z.getLocation().x;
			int y=z.getLocation().y;
			String s=z.getName()+" ("+x+","+y+")";
			if (s.equals(target.getSelectedItem())){
				currentTarget=z;
			}
		}
		currentHero.setTarget(currentTarget);
		if(e.getSource()==attackButton){
			try{
				currentHero.attack();
				playMusic("src/views/Gunshot-Sound-Effect-Single-Shot.wav");
				int iDisplaceXBy = 5;
				int iDisplaceYBy = -10;
				Point currLocation = getLocationOnScreen();
			    Point position1 = new Point(currLocation.x + iDisplaceXBy, currLocation.y+ iDisplaceYBy);
			    Point position2 = new Point(currLocation.x - iDisplaceXBy, currLocation.y- iDisplaceYBy);
			    for (int i = 0; i < 20; i++) {
			      setLocation(position1);
			      setLocation(position2);
			    }
			    setLocation(currLocation);
				
			}
			catch(NotEnoughActionsException|InvalidTargetException e1){
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if(e.getSource()==cureButton){
			try {
				currentHero.cure();
				playMusic("src/views/Heal - Sound Effect Clash Royale.wav");
			} catch (NoAvailableResourcesException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}else if (e.getSource()==usespecialButton){
			try {
				currentHero.useSpecial();
				playMusic("src/views/Power-Up - Sound Effect (HD).wav");
			} catch (NoAvailableResourcesException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}else if (e.getSource()==endturnButton){
			try {
				Game.endTurn();
				playMusic("src/views/Zombie Roar Sound Effect.wav");
			} catch (NotEnoughActionsException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if (e.getSource()==up){
			try {
				Cell t=Game.map[(int) currentHero.getLocation().getX()+1][(int) currentHero.getLocation().getY()];
				currentHero.move(Direction.UP);
				if(t instanceof TrapCell)
					JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)t).getTrapDamage()+"damage.");
			} catch (MovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if (e.getSource()==down){
			try {
				Cell t=Game.map[(int) currentHero.getLocation().getX()-1][(int) currentHero.getLocation().getY()];
				currentHero.move(Direction.DOWN);
				if(t instanceof TrapCell)
					JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)t).getTrapDamage()+"damage.");
			} catch (MovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if (e.getSource()==right){
			try {
				Cell t=Game.map[(int) currentHero.getLocation().getX()][(int) currentHero.getLocation().getY()+1];
				currentHero.move(Direction.RIGHT);
				if(t instanceof TrapCell)
					JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)t).getTrapDamage()+"damage.");
			} catch (MovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if (e.getSource()==left){
			try {
				Cell t=Game.map[(int) currentHero.getLocation().getX()][(int) currentHero.getLocation().getY()-1];
				currentHero.move(Direction.LEFT);
				if(t instanceof TrapCell)
					JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)t).getTrapDamage()+"damage.");
			} catch (MovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			} catch (NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		else if (e.getSource()==AI){
			if(AI.getText().equals("Start AI")){
				timer.start();
				AI.setText("Stop AI");
			}
			else{
				timer.stop();
				AI.setText("Start AI");
			}
	       
		}
		else{
			for (int i = 14; i >=0; i--) {
				for (int j = 0; j < MapButtons.length; j++) {
					if(MapButtons[i][j] == e.getSource()){
						if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero){
							currentHero=(Hero)((CharacterCell)Game.map[i][j]).getCharacter();
						}
					}
				}
			}
		}
		
		updateMap();
		selectedHero(currentHero);
		updateHeroPanel();
		targetList();
		if(Game.checkWin()){
			JOptionPane.showMessageDialog(this,"CONGRATULATIONS, YOU WON");
			this.setVisible(false);
			new GameOver();
		}
		if(Game.checkGameOver()){
			this.setVisible(false);
			new GameOver();
		}
		return;
		
	}
	
	
	
	
	public static void playMusic(String filepath){
		try{
			File musicPath=new File(filepath);
			AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);
			Clip clip =AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void AI(Hero h){
		Direction[] d={Direction.DOWN,Direction.LEFT,Direction.RIGHT,Direction.UP};
		if(h.getSupplyInventory().size()>0){
			if(h instanceof Medic){
				double lowhp=0.7;
				Hero h2=null;
				for(Hero h3:Game.heroes){
					double perc = (double)h3.getCurrentHp()/(double)h3.getMaxHp();
					if(perc<lowhp){
						h2=h3;
						lowhp=perc;
					}
				}
				if(h2!=null){
					h.setTarget(h2);
					h.getTarget().setCurrentHp(h.getTarget().getMaxHp());
					playMusic("src/views/Power-Up - Sound Effect (HD).wav");
					h.getSupplyInventory().get(0).use(h);
					h.setSpecialAction(true);
					return;
				}
			}
			if(h instanceof Explorer){
				for(int i = 0; i < Game.map.length; i++) {
					for(int j = 0; j < Game.map[i].length; j++) {
						Game.map[i][j].setVisible(true);
					}
				}
				playMusic("src/views/Power-Up - Sound Effect (HD).wav");
				h.getSupplyInventory().get(0).use(h);
				h.setSpecialAction(true);
				return;
			}
			
		}
		System.out.println("hi");
		Point p = h.getLocation();
		for (int i = -1; i <= 1; i++) {
			int cx = p.x + i;
			if (cx >= 0 && cx <= 14) {
				for (int j = -1; j <= 1; j++) {
					int cy = p.y + j;
					if (cy >= 0 && cy <= 14) {
						if (!(i == 0 && j == 0) && Game.map[cx][cy] instanceof CharacterCell
								&& ((CharacterCell) Game.map[cx][cy]).getCharacter() instanceof Zombie
								&& Game.map[cx][cy].isVisible()) {
							h.setTarget(((CharacterCell) Game.map[cx][cy]).getCharacter());
							if(h.getVaccineInventory().size()>0){
								h.getVaccineInventory().get(0).use(h);
								playMusic("src/views/Heal - Sound Effect Clash Royale.wav");
								h.setActionsAvailable(h.getActionsAvailable()-1);
								return;
							}
							else if(((double)h.getCurrentHp()/(double)h.getMaxHp())>0.5){
								h.getTarget().setCurrentHp(h.getTarget().getCurrentHp() - h.getAttackDmg());
								h.getTarget().defend(h);
								playMusic("src/views/Gunshot-Sound-Effect-Single-Shot.wav");
								int iDisplaceXBy = 5;
								int iDisplaceYBy = -10;
								Point currLocation = getLocationOnScreen();
							    Point position1 = new Point(currLocation.x + iDisplaceXBy, currLocation.y+ iDisplaceYBy);
							    Point position2 = new Point(currLocation.x - iDisplaceXBy, currLocation.y- iDisplaceYBy);
							    for (int s = 0; s < 20; s++) {
							      setLocation(position1);
							      setLocation(position2);
							    }
							    setLocation(currLocation);
								if (h instanceof Fighter && (h.isSpecialAction()))
									return;
								h.setActionsAvailable(h.getActionsAvailable()-1);
								return;
							}
							
						}
						else if(Game.map[cx][cy] instanceof CollectibleCell){
							int tX=cx;
							int tY=cy;
							if(i!=0 && j!=0){
								tX=p.x;
								if(Game.map[tX][tY] instanceof CharacterCell && ((CharacterCell) Game.map[tX][tY]).getCharacter()!=null){
									tX=cx;
									tY=p.y;
									if(Game.map[tX][tY] instanceof CharacterCell && ((CharacterCell) Game.map[tX][tY]).getCharacter()!=null){
										continue;
									}
								}
							}
							
							if (Game.map[tX][tY] instanceof CollectibleCell) {
								((CollectibleCell) Game.map[tX][tY]).getCollectible().pickUp(h);
							} else if (Game.map[tX][tY] instanceof TrapCell) {
								h.setCurrentHp(h.getCurrentHp() - ((TrapCell) Game.map[tX][tY]).getTrapDamage());
								JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)Game.map[tX][tY]).getTrapDamage()+"damage.");
							}
							Game.map[p.x][p.y] = new CharacterCell(null);
							h.setActionsAvailable(h.getActionsAvailable()-1);

							if (h.getCurrentHp() ==  0) {
								return;
							}
							Game.map[tX][tY] = new CharacterCell(h);
							h.setLocation(new Point(tX, tY));
							Game.adjustVisibility(h);
							return;
					    }
				   }
			   }
		   }
		}
		
		int tX=p.x;
		int tY=p.y;
		int reset=0;
		boolean flag=false;
		do{
			flag=false;
			tX = p.x;
			tY = p.y;
			Direction d1=d[(int)(Math.random()*4)];
			switch (d1) {
			case DOWN:
				tX--;
				break;
			case LEFT:
				tY--;
				break;
			case RIGHT:
				tY++;
				break;
			case UP:
				tX++;
				break;
			}
			if(!((p.x==0 && p.y==0) || (p.x==0 && p.y==14) || (p.x==14 && p.y==0) || (p.x==14 && p.y==14))){
				if(tX>0 && tY>0 && tX<14 && tY<14 ){
					for(Point p2:discovered){
						if(p2.getX()==tX && p2.getY()==tY){
							flag=true;
							reset++;
							System.out.println("AAA");
						}
						
					}
				}
					
			}
			if(reset==6){
				reset=0;
				discovered.clear();
			}
			
		}while(flag==true || tX<0 || tY<0 || tX>14 || tY>14 || (Game.map[tX][tY] instanceof CharacterCell && ((CharacterCell) Game.map[tX][tY]).getCharacter()!=null));
		if (Game.map[tX][tY] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[tX][tY]).getCollectible().pickUp(h);
		} else if (Game.map[tX][tY] instanceof TrapCell) {
			h.setCurrentHp(h.getCurrentHp() - ((TrapCell) Game.map[tX][tY]).getTrapDamage());
			JOptionPane.showMessageDialog(this, "You entered a trap cell and took"+((TrapCell)Game.map[tX][tY]).getTrapDamage()+"damage.");
		}
		Game.map[p.x][p.y] = new CharacterCell(null);
		discovered.add(p);
		if(discovered.size()>3)
			discovered.remove(0);
		System.out.println(discovered.toString());
		h.setActionsAvailable(h.getActionsAvailable()-1);

		if (h.getCurrentHp() ==  0) {
			return;
		}
		Game.map[tX][tY] = new CharacterCell(h);
		h.setLocation(new Point(tX, tY));
		Game.adjustVisibility(h);
		
	}


}
	




