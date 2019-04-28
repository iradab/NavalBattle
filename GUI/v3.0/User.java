package battleShipGUI;

import javax.swing.*;
import java.awt.*;

public class User extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	String name;
	Grid myGrid;
	Grid enemyGrid;
	boolean turn;
	UserListener ul[][];
	
	public User(String name){
		this.name=name;
		TextField t = new TextField(this.name+", Choose your boats");
		this.setSize (WIDTH, HEIGHT);
		this.setLayout(new FlowLayout());
		this.setTitle("Battle Ship");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myGrid = new Grid();

		this.add(t, BorderLayout.NORTH);
		this.add(this.myGrid.getContentPane(), BorderLayout.CENTER);
	}

	public void removeUserListeners() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++)
				this.myGrid.cells[i][j].removeActionListener(this.ul[i][j]);
		}	
	}
	
	// static method that returns the state of a clicked cell in an array
	// of size 3: [0] for x, [1] for y, [2] to check if there's a ship or not
	public static int[] getStateOfCell(Bot bot, JButton button) {
		int ar[] = new int[3];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(button == bot.myGrid.cells[i][j]) {
					ar[0]=i; ar[1]=j; ar[2]=bot.myGrid.table[i][j];
					return ar;
				}
			}
		}
		return ar;// important in Java
	}
	
	public static int[] getStateOfCell(User u, JButton button) {
		int ar[] = new int[3];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(button == u.enemyGrid.cells[i][j]) {
					ar[0]=i; ar[1]=j; ar[2]=u.enemyGrid.table[i][j];
					return ar;
				}
			}
		}
		return ar;// important in Java
	}
	
	public boolean is_Won(Bot bot) {
		boolean WON=true;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(bot.myGrid.table[i][j] == 1) {
					WON=false;
					return WON;
				}
			}
		}
		return WON;
	}
	
	public boolean is_Won() {
		boolean WON=true;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(this.enemyGrid.table[i][j] == 1) {
					WON=false;
					return WON;
				}
			}
		}
		return WON;
	}

	public void shoot(User u, Bot bot, int index, JButton button) {		
		int info[]=getStateOfCell(bot, button);
		int x=info[0];		int y=info[1];
		index=info[2];
		
		// index can be 2 which means that cell was already checked
		// the handler does nothing in that case
		if(index == 1) { // The user damaged
			button.setBackground(Color.RED);
			bot.myGrid.table[x][y]=2;
		}
		else if(index == 0){ // The user missed
			button.setBackground(Color.GREEN);
			bot.myGrid.table[x][y]=2;
			u.turn=false;
			bot.turn=true;
			while(bot.turn)
				bot.turn = bot.shoot(u.myGrid);
		}
		u.revalidate();
		u.repaint();
		
		if(bot.is_Won(u)) {
			System.out.println("Bot won");	
			//System.exit(0); // TODO
			u.removeUserListeners();
		}

		if(u.is_Won(bot)) {
			System.out.println("User won");			
			//System.exit(0); // TODO
			u.removeUserListeners();
		}
	}

	public void shoot(User u1, User u2, int index, JButton button) {		
		int info[], x, y;
		if(u1.turn) {
			info=getStateOfCell(u1, button);
			x=info[0];		y=info[1];
			index=info[2];

			if(index == 1) { // The user1 damaged
				button.setBackground(Color.RED);
				u1.enemyGrid.table[x][y]=2;
			}
			else if(index == 0){ // The user1 missed
				button.setBackground(Color.GREEN);
				u1.enemyGrid.table[x][y]=2;
				u1.turn=false;
				u2.turn=true;
				u1.setVisible(false);
				u2.setVisible(true);
			}
		}
		else if(u2.turn){
			info=getStateOfCell(u2, button);
			x=info[0];		y=info[1];
			index=info[2];

			if(index == 1) { // The user2 damaged
				button.setBackground(Color.RED);
				u2.enemyGrid.table[x][y]=2;
			}
			else if(index == 0){ // The user2 missed
				button.setBackground(Color.GREEN);
				u2.enemyGrid.table[x][y]=2;
				u2.turn=false;
				u1.turn=true;
				u1.setVisible(true);
				u2.setVisible(false);
			}
		}
		
		// besides index can be 2 which means that cell was already checked
		// the handler does nothing in that case

		if(u1.is_Won()) {
			System.out.println(u1.name +" won");
			//System.exit(0); // TODO
		}

		if(u2.is_Won()) {
			System.out.println(u2.name +" won");			
			//System.exit(0); // TODO
		}
	}
}