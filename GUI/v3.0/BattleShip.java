package battleShipGUI;
import java.awt.*;
import javax.swing.*;

public class BattleShip{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	public static void main(String[] args) {
		JFrame window = new JFrame("Battle Ship");
		window.setSize (WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TextField t = new TextField("Welcome, choose the mode of the game");
		window.setLayout(new GridLayout(5,1));
				
		Button b1 = new Button ("vs Computer");
		Button b2 = new Button ("2 players");
		Button b3 = new Button ("Online");
		Button b4 = new Button ("Exit");

		b1.addActionListener((c) -> {
			botButtonPressed(window);
	    });
		b2.addActionListener((c) -> {
			vsButtonPressed(window);
	    });
		b4.addActionListener((c) -> {
			exitButtonPressed();
	    });
		
		window.add(t);
		window.add(b1); window.add(b2);
		window.add(b3);	window.add(b4);
		window.setVisible(true);
		
		// JButton ship5 = new JButton("Carrier");
		// JButton ship4 = new JButton("Battleship");
		// JButton ship3 = new JButton("Cruiser/Submarine");
		// JButton ship2 = new JButton("Destroyer");
	}
	
	public static void arrangeShipsForUser() {
		User u = new User("Player");
		JButton confirm = new JButton("Confirm");
		u.add(confirm, BorderLayout.EAST);
		
		JButton ship5 = new JButton("Carrier/5");
		JButton ship4 = new JButton("Battleship/4");
		JButton ship3a = new JButton("Cruiser/3");
		JButton ship3b = new JButton("Submarine/3");
		JButton ship2 = new JButton("Destroyer/2");
		u.add(ship5);  u.add(ship4); u.add(ship3a);
		u.add(ship3b); u.add(ship2);

		ship5.addActionListener((e) -> {
			carrier(u);
	    });
		ship4.addActionListener((e) -> {
			battleship(u);
	    });
		ship3a.addActionListener((e) -> {
			cruiser(u);
	    });
		ship3b.addActionListener((e) -> {
			submarine(u);
	    });
		ship2.addActionListener((e) -> {
			destroyer(u);
	    });


		confirm.addActionListener((e) -> {
			confirmButtonPressed(u);
	    });
		
		u.setVisible(true);
	}

	public static void arrangeShipsForUsers() {
		User user1 = new User("Player1");
		User user2 = new User("Player2");
		
		JButton confirm1 = new JButton("Confirm");
		user1.add(confirm1, BorderLayout.EAST);
		JButton ship5 = new JButton("Carrier/5");
		JButton ship4 = new JButton("Battleship/4");
		JButton ship3a = new JButton("Cruiser/3");
		JButton ship3b = new JButton("Submarine/3");
		JButton ship2 = new JButton("Destroyer/2");
		user1.add(ship5);  user1.add(ship4); user1.add(ship3a);
		user1.add(ship3b); user1.add(ship2);
		
		ship5.addActionListener((e) -> {
			carrier(user1);
	    });
		ship4.addActionListener((e) -> {
			battleship(user1);
	    });
		ship3a.addActionListener((e) -> {
			cruiser(user1);
	    });
		ship3b.addActionListener((e) -> {
			submarine(user1);
	    });
		ship2.addActionListener((e) -> {
			destroyer(user1);
	    });
		confirm1.addActionListener((e) -> {
			confirmButton1Pressed(user1, user2);
	    });
		user1.setVisible(true);
		
		JButton confirm2 = new JButton("Confirm");
		user2.add(confirm2, BorderLayout.EAST);
		//user2.add(confirm1, BorderLayout.EAST);
		ship5 = new JButton("Carrier/5");
		ship4 = new JButton("Battleship/4");
		ship3a = new JButton("Cruiser/3");
		ship3b = new JButton("Submarine/3");
		ship2 = new JButton("Destroyer/2");
		user2.add(ship5);  user2.add(ship4); user2.add(ship3a);
		user2.add(ship3b); user2.add(ship2);
		
		ship5.addActionListener((e) -> {
			carrier(user2);
	    });
		ship4.addActionListener((e) -> {
			battleship(user2);
	    });
		ship3a.addActionListener((e) -> {
			cruiser(user2);
	    });
		ship3b.addActionListener((e) -> {
			submarine(user2);
	    });
		ship2.addActionListener((e) -> {
			destroyer(user2);
	    });
		confirm2.addActionListener((e) -> {
			confirmButton2Pressed(user1, user2);
	    });
		//user2.setVisible(true);
	}

	public static void botButtonPressed(JFrame f) {
		f.getContentPane().removeAll();
		f.setVisible(false);
		arrangeShipsForUser();
	}
	
	public static void vsButtonPressed(JFrame f) {
		f.getContentPane().removeAll();
		f.setVisible(false);
		arrangeShipsForUsers();
	}
	
	public static void exitButtonPressed() {
		System.exit(1);
	}

	public static boolean _isValid(Grid grid) {
		for(int i=0;i<5;i++){
			if(grid.ships[i].validity == false)
			//System.out.println(2);
			return false;
		}
		return true;
	}

	public static void confirmButtonPressed(User u){
		//if(u.myGrid.isValid()) {
		if( _isValid(u.myGrid) ) {
			Game.startGame(u);
			// System.out.print("Valid");
		}
	}
	
	public static void confirmButton1Pressed(User u1, User u2) {
		if( _isValid(u1.myGrid) ) {
			//System.out.println("Not done");
			u1.setVisible(false);
			u2.setVisible(true);
		}
	}
	
	public static void confirmButton2Pressed(User u1, User u2) {
		if( _isValid(u2.myGrid) ) {
			u2.setVisible(false);
			Game.startGame(u1,u2);
		}
	}

	public static void carrier(User u) {
		u.myGrid.ships[0].validity=false;
		if(u.ul!=null) { // this if condition is needed to remove all listeners from all buttons: caused many problems
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(u.ul[i][j] != null) {
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
					}
				}
			}
		}
		//System.out.println(u.myGrid.cells[0][0].getBackground());
		u.ul = new UserListener [10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				u.ul[i][j] = new UserListener(u,"Carrier");
				//System.out.println(u.ul[i][j].counter);
				u.myGrid.cells[i][j].addActionListener(u.ul[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(u.myGrid.cells[i][j].getBackground() == Color.BLUE) {
					u.myGrid.cells[i][j].setBackground(null);
					//u.ul[i][j].counter++; // to avoid button pressing problem !!!
				}
				else if(u.myGrid.cells[i][j].getBackground() == Color.YELLOW || 
						u.myGrid.cells[i][j].getBackground() == Color.PINK || 
						u.myGrid.cells[i][j].getBackground() == Color.MAGENTA ||
						u.myGrid.cells[i][j].getBackground() == Color.BLACK
						){ // TODO: verify
					u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]); //TODO: do smth with this doesnt work
					//u.ul[i][j].counter++;
				}
			}
		}
		// TODO: for the remaining colored cells (ships) we have to increase counter 
		//for(int i=0;i<10;i++) {
		//	for(int j=0;j<10;j++)
			//	u.myGrid.cells[i][j].addActionListener(new UserListener(u, "Carrier"));
		//}
	}

	public static void battleship(User u) {
		u.myGrid.ships[1].validity=false;
		if(u.ul!=null) {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(u.ul[i][j] != null) {
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
					}
				}
			}
		}
		u.ul = new UserListener [10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				u.ul[i][j] = new UserListener(u,"Battleship");
				u.myGrid.cells[i][j].addActionListener(u.ul[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(u.myGrid.cells[i][j].getBackground() == Color.YELLOW) {
					u.myGrid.cells[i][j].setBackground(null);
				}
				else if(u.myGrid.cells[i][j].getBackground() == Color.BLUE || 
						u.myGrid.cells[i][j].getBackground() == Color.PINK || 
						u.myGrid.cells[i][j].getBackground() == Color.MAGENTA ||
						u.myGrid.cells[i][j].getBackground() == Color.BLACK) { // TODO: verify
					u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
				}
			}
		}
	}

	public static void cruiser(User u) {
		u.myGrid.ships[2].validity=false;
		if(u.ul!=null) {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(u.ul[i][j] != null) {
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
					}
				}
			}
		}
		u.ul = new UserListener [10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				u.ul[i][j] = new UserListener(u,"Cruiser");
				u.myGrid.cells[i][j].addActionListener(u.ul[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(u.myGrid.cells[i][j].getBackground() == Color.MAGENTA) {
					u.myGrid.cells[i][j].setBackground(null);
				}
				else if(u.myGrid.cells[i][j].getBackground() == Color.YELLOW || 
						u.myGrid.cells[i][j].getBackground() == Color.PINK || 
						u.myGrid.cells[i][j].getBackground() == Color.BLUE ||
						u.myGrid.cells[i][j].getBackground() == Color.BLACK) { // TODO: verify
					u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
				}
			}
		}
	}
	
	public static void submarine(User u) {
		u.myGrid.ships[3].validity=false;
		if(u.ul!=null) {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(u.ul[i][j] != null) {
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
					}
				}
			}
		}
		u.ul = new UserListener [10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				u.ul[i][j] = new UserListener(u,"Submarine");
				u.myGrid.cells[i][j].addActionListener(u.ul[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(u.myGrid.cells[i][j].getBackground() == Color.PINK) {
					u.myGrid.cells[i][j].setBackground(null);
				}
				else if(u.myGrid.cells[i][j].getBackground() == Color.YELLOW || 
						u.myGrid.cells[i][j].getBackground() == Color.BLUE || 
						u.myGrid.cells[i][j].getBackground() == Color.MAGENTA ||
						u.myGrid.cells[i][j].getBackground() == Color.BLACK) { // TODO: verify
					u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
				}
			}
		}
	}

	public static void destroyer(User u) {
		u.myGrid.ships[4].validity=false;
		if(u.ul!=null) {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(u.ul[i][j] != null) {
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
					}
				}
			}
		}
		u.ul = new UserListener [10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				u.ul[i][j] = new UserListener(u,"Destroyer");
				u.myGrid.cells[i][j].addActionListener(u.ul[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(u.myGrid.cells[i][j].getBackground() == Color.BLACK) {
					u.myGrid.cells[i][j].setBackground(null);
				}
				else if(u.myGrid.cells[i][j].getBackground() == Color.YELLOW || 
						u.myGrid.cells[i][j].getBackground() == Color.PINK || 
						u.myGrid.cells[i][j].getBackground() == Color.MAGENTA ||
						u.myGrid.cells[i][j].getBackground() == Color.BLUE) { // TODO: verify
						u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
				}
			}
		}
	}
	
	public static void findButtonPresses(User u, String shipName) { // TODO: ?

	}
}