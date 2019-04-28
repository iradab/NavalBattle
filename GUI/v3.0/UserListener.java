import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class UserListener implements ActionListener{	
	User u;
	int counter;
	String shipName;
	
	public UserListener(User u, String shipName) {
		counter=0; this.u=u; this.shipName=shipName;
	}
	
	public void setShipName(String shipName) {
		this.shipName=shipName;
	}
	
	public void actionPerformed ( ActionEvent e ) {
		Color color=Color.BLACK; // each ship has its own color
		int count=0; // size of ship (# of cells of color <color>)
		int xStart=-1, yStart=-1;
		int shipIndex=-1, size=0;
		boolean flag=true; char orientation='0';
		if(shipName.equals("Carrier")) {
			color=Color.BLUE;
			shipIndex=0;
			size=5;
		}
		else if(shipName.equals("Battleship")) {
			color=Color.YELLOW;
			shipIndex=1;
			size=4;
		}
		else if(shipName.equals("Cruiser")) {
			color=Color.MAGENTA;
			shipIndex=2;
			size=3;
		}
		else if(shipName.equals("Submarine")) {
			color=Color.PINK;
			shipIndex=3;
			size=3;
		}
		else if(shipName.equals("Destroyer")) {
			color=Color.BLACK;
			shipIndex=4;
			size=2;
		}
		u.myGrid.ships[shipIndex].validity=false; // important !
		
		if(counter%2 == 0) { // arrange a ship to the current button
			JButton button = (JButton) e.getSource();
			button.setBackground(color);
			
				for(int i=0;i<10;i++) {
					for(int j=0;j<10;j++) {
						if(u.myGrid.cells[i][j].getBackground() == color)
							count++;
					}
				}
				//System.out.println(count);
				if( (count==size && shipIndex==0) || (count==size && shipIndex==1) ||
					(count==size && shipIndex==2) || (count==size && shipIndex==3)||
					(count==size && shipIndex==4) // check 5 possible ship situations
				  ) {
					
					boolean tmp=false;
					for(int i=0;i<10;i++) {
						for(int j=0;j<10;j++) {
							if(u.myGrid.cells[i][j].getBackground() == color) {
								xStart=i; yStart=j;
								tmp=true;
								break;
							}
						}
						if(tmp) break;
						//if(xStart != -1)	break; // TODO: where to put?
					}
					//System.out.println("xStart="+xStart); // TODO problem solved?
					//System.out.println("yStart="+yStart); // TODO problem solved?
					
					flag=true; //u.myGrid.ships[shipIndex].validity=true;
					if(xStart != 9 && yStart !=9) { // TODO: otherwise index 10 will appear; FIX: xEnd smtms is set to 13
													// TODO: there's a problem with last line and last column
						// TODO: fixed 2 todos above?
						if(u.myGrid.cells[xStart+1][yStart].getBackground() == color) {
							if(xStart+size-1 <= 9) { // is it possible to put the ship in those cells?
								orientation='v';
								//u.myGrid.ships[shipIndex].orientation='v';
								for(int i=xStart+2;i<xStart+size;i++) {
									if(u.myGrid.cells[i][yStart].getBackground() != color) {
										flag=false; break;
									}
								}								
							}
							else {
								flag=false;
							}
						}
						
						else if(u.myGrid.cells[xStart][yStart+1].getBackground() == color) {
							if(yStart+size-1 <= 9) {
								orientation='h'; //u.myGrid.ships[shipIndex].orientation='h';
								for(int j=yStart+2;j<yStart+size;j++) {
									if(u.myGrid.cells[xStart][j].getBackground() != color) {
										flag=false; break;
									}
								}
							}
							else {
								flag=false;
							}
						}
						else {flag=false;} // the case when no valid ship is presented
					}
					else if(xStart == 9) {
						if(u.myGrid.cells[xStart][yStart+1].getBackground() == color) {
							if(yStart+size-1 <= 9) {
								orientation='h'; //u.myGrid.ships[shipIndex].orientation='h';
								for(int j=yStart+2;j<yStart+size;j++) {
									if(u.myGrid.cells[xStart][j].getBackground() != color) {
										flag=false; break;
									}	
								}
							}
							else { flag=false; }
						}
						else {flag=false;} // the case when no valid ship is presented				
					}
					else { // yStart=9
						if(u.myGrid.cells[xStart+1][yStart].getBackground() == color) {
							if(xStart+size-1 <= 9) {
								orientation='v'; //u.myGrid.ships[shipIndex].orientation='v';
								for(int i=xStart+2;i<xStart+size;i++) {
									if(u.myGrid.cells[i][yStart].getBackground() != color) {
										flag=false; break;
									}
								}
							}
							else { flag=false; }
						}
						else {flag=false;} // the case when no valid ship is presented
					}
					/*
					if(u.myGrid.cells[xStart+1][yStart].getBackground() == color) {
						if(u.myGrid.cells[xStart+2][yStart].getBackground() == color &&
						Grid.ships[shipIndex].   u.myGrid.cells[xStart+3][yStart].getBackground() == color &&
						   u.myGrid.cells[xStart+4][yStart].getBackground() == color
						) {						
							u.myGrid.ships[0].xEnd=xStart+4;
							u.myGrid.ships[0].yEnd=yStart;
							u.myGrid.ships[0].validity=true;
						}
						u.myGrid.ships[0].orientation='h'; //
					}*/
					/*
					else if(u.myGrid.cells[xStart][yStart+1].getBackground() == color) {
						u.myGrid.ships[0].orientation='v';
						if(u.myGrid.cells[xStart][yStart+2].getBackground() == color &&
							   u.myGrid.cells[xStart][yStart+3].getBackground() == color &&
							   u.myGrid.cells[xStart][yStart+4].getBackground() == color
						) {						
								u.myGrid.ships[0].xEnd=xStart;
								u.myGrid.ships[0].yEnd=yStart+4;
								u.myGrid.ships[0].validity=true;
						}
					}*/
					if(flag == false) {
						for(int i=0;i<10;i++) {
							for(int j=0;j<10;j++) {			
								if(u.myGrid.cells[i][j].getBackground() == color) {
									u.myGrid.cells[i][j].setBackground(null);
									u.ul[i][j].counter++; // TODO
								}
							}
						}
						//u.revalidate();
						//u.repaint();
					}
					else { // ship is valid
						switch(shipIndex) { // setting names of ships
							case 0:  u.myGrid.ships[shipIndex].setName("Carrier"); break;
							case 1:  u.myGrid.ships[shipIndex].setName("BattleShip"); break;
							case 2:  u.myGrid.ships[shipIndex].setName("Cruiser"); break;
							case 3:  u.myGrid.ships[shipIndex].setName("Submarine"); break;
							case 4:  u.myGrid.ships[shipIndex].setName("Destroyer"); break;
							default: u.myGrid.ships[shipIndex].setName(""); break;
						}
						System.out.println(u.myGrid.ships[shipIndex].name);
						if(orientation == 'h') { // horizontal
							u.myGrid.ships[shipIndex].orientation='h';						
							u.myGrid.ships[shipIndex].xEnd=xStart;
							u.myGrid.ships[shipIndex].yEnd=yStart+size-1;
							u.myGrid.ships[shipIndex].validity=true;
						}
						else { // vertical
							u.myGrid.ships[shipIndex].orientation='v';
							u.myGrid.ships[shipIndex].xEnd=xStart+size-1;
							u.myGrid.ships[shipIndex].yEnd=yStart;
							u.myGrid.ships[shipIndex].validity=true;
						}
						
						for(int i=0;i<10;i++) {
							for(int j=0;j<10;j++)
								u.myGrid.cells[i][j].removeActionListener(u.ul[i][j]);
						}
						//System.out.println("orientation="+u.myGrid.ships[shipIndex].orientation); // TODO problem?
						//System.out.println("xEnd="+u.myGrid.ships[shipIndex].xEnd); // TODO problem solved?
						//System.out.println("yEnd="+u.myGrid.ships[shipIndex].yEnd); // TODO problem solved?

						//if(u.myGrid.ships[0].orientation == 'v') {
						//	for(int j=yStart;j<yEnd;j++)
						//		u.myGrid.cells[xStart][j].removeActionListener(u.ul[xStart][j]);
						//}
						//else {
						//	for(int i=yStart;i<yEnd;i++)
						//		u.myGrid.cells[i][yStart].removeActionListener(u.ul[i][yStart]);
						//}
					}
				}
				else if(count>5){
					for(int i=0;i<10;i++) {
						for(int j=0;j<10;j++) {			
							if(u.myGrid.cells[i][j].getBackground() == color) {
								u.myGrid.cells[i][j].setBackground(null);
								//u.ul[i][j].counter++; // TODO: important ?!
							}
						}
					}
					//u.revalidate();
					//u.repaint();
				}
		}
		else { // remove the ship from the current button
			JButton button = (JButton) e.getSource();
			button.setBackground(null);
		}
		counter++;
	}
}

/*
package battleShipGUI;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class EnemyListener implements ActionListener{
	int index; // can be either 1 or 0, helps us to determine if there's a ship in a certain cell or no
	boolean turn;
	
	public EnemyListener(int index, boolean turn) {
		//this.button = button;
		//this.u=u;
		this.index = index;
		this.turn = turn;
	}
	
	public void actionPerformed ( ActionEvent e ) {
		if(index == 1) {
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.RED);
			this.turn=false; // The user damaged
		}
		else {
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.GREEN);
			this.turn=true; // the user missed
		}
	}
}
*/