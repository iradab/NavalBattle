import java.util.Scanner;

public class User {
	Grid myGrid = new Grid();
	Grid enemyGrid = new Grid();
	
	Scanner input = new Scanner(System.in);
	
	int userNo;
	int nDestroyed = 0; // how many boats this user destroyed
	
	public User(int num) {
		myGrid.initGrid();
		enemyGrid.initGrid();
		userNo = num;
	}
	public void putBoats() {
		System.out.println("USER"+userNo+" : ");
		int []nBoats = {4,3,2,1} ;
		Boat b ;
		 
		for(int i=1; i <= 2; i++) {                         // change it // it is length
			for(int j =0; j< 2; j++){ 								// nBoats[i]
																	// length should be checked
				System.out.println("Enter starting and ending coordinates(of boat of length "+i+")");
				int x1,x2,y1,y2;
				x1 = input.nextInt(); y1 = input.nextInt();
				if(i == 1) { 
					x2= x1;  y2 = y1;
				}
				else {
					 x2 = input.nextInt(); y2 = input.nextInt();
				}
				b = new Boat();
				b.createBoat(x1-1, y1-1, x2-1, y2-1);
				if( myGrid.canAdd(b) == true && b.length == i)
					myGrid.addBoats(b);
				else {
					System.out.println("Cant add this boat .. Enter again");
					j--;
				}
			}
		}
		System.out.println("User"+userNo+" , "+"Your board :");
		myGrid.showGrid();
	}
	public int shoot(User otherUser) {
		int x,y;
		System.out.println("USER"+userNo+" ,your turn to shoot.. Enter coordinates:");
		x = input.nextInt(); y =input.nextInt();
		int sank = otherUser.myGrid.checkShoot(x-1,y-1,this.enemyGrid); 
		return sank;
	}
}

