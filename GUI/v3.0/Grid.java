package battleShipGUI;
import javax.swing.*;
import java.awt.*;

public class Grid extends JFrame {
	private static final long serialVersionUID = 1L;
	Ship ships[];
	JButton cells[][];
	int table[][];
	
	public Grid() {
		this.setPreferredSize(new Dimension(250,250));;
		this.setLayout(new GridLayout(10,10));

		this.table = new int[10][10];
		for(int i=0;i<10;i++) 
			for(int j=0;j<10;j++) 
				this.table[i][j]=0;
		this.cells = new JButton[10][10];

		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				this.cells[i][j] = new JButton();
				this.add(this.cells[i][j]);
			}
		}
		//important, not to have nullPointerException
		this.ships=new Ship[5];
		for(int i=0;i<5;i++)
			this.ships[i] = new Ship();
	}
}