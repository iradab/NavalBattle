package battleShipGUI;
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
		u.myGrid.ships[shipIndex].validity=false; // important point 
		
		if(counter%2 == 0) { // arrange a ship to the current button
			JButton button = (JButton) e.getSource();
			button.setBackground(color);
			
				for(int i=0;i<10;i++) {
					for(int j=0;j<10;j++) {
						if(u.myGrid.cells[i][j].getBackground() == color)
							count++;
					}
				}
				//System.out.println(count); // for debugging
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
					}
					//System.out.println("xStart="+xStart); // for debugging
					//System.out.println("yStart="+yStart); // for debugging
					
					flag=true; //u.myGrid.ships[shipIndex].validity=true;
					if(xStart != 9 && yStart !=9) {
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
								orientation='v';
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
					if(flag == false) {
						for(int i=0;i<10;i++) {
							for(int j=0;j<10;j++) {			
								if(u.myGrid.cells[i][j].getBackground() == color) {
									u.myGrid.cells[i][j].setBackground(null);
									u.ul[i][j].counter++;
								}
							}
						}
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
						//System.out.println(u.myGrid.ships[shipIndex].name);
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
						//System.out.println("orientation="+u.myGrid.ships[shipIndex].orientation); // for debugging
						//System.out.println("xEnd="+u.myGrid.ships[shipIndex].xEnd); // for debugging
						//System.out.println("yEnd="+u.myGrid.ships[shipIndex].yEnd); // for debugging 
					}
				}
				else if(count>5){
					for(int i=0;i<10;i++) {
						for(int j=0;j<10;j++) {			
							if(u.myGrid.cells[i][j].getBackground() == color)
								u.myGrid.cells[i][j].setBackground(null);
						}
					}
				}
		}
		else { // remove the ship from the current button
			JButton button = (JButton) e.getSource();
			button.setBackground(null);
		}
		counter++;
	}
}
