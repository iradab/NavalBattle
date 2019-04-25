// -1 -- nothing there 
// -2 -- a boat
// 1  -- shoot a boat
// 2  -- sanked a boat
// 0  -- incorrect shoot

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// creating two new users
		User user1 = new User(1);
		User user2 = new User(2);
		
		// allowing users to put their boats
		user1.putBoats();		
		//clearScreen();
		//changeTurn(2);
		
		user2.putBoats();
		//changeTurn(1);

		System.out.println("/nNow the game starts.. ");
		
		// start of the game itself
		// while the are still boats to shoot continue the game
		while(user1.nDestroyed != 4 && user2.nDestroyed != 4) {  			// later change 3 to 10
			int us1 = 1,us2 = 1;
			while( us1 == 1 || us1 == 2 || us1 == 3) {
				us1 = user1.shoot(user2);
				if(us1 != 3) {									// if it is not an error 
					System.out.println("USER1: your board:");
					user1.myGrid.showGrid();
					System.out.println("USER1: your enemies board:");
					user1.enemyGrid.showGrid();
					if( us1 == 1 ) System.out.println("USER1: you shot a boat !");
					if ( us1 == 2) { user1.nDestroyed++; System.out.println("USER1: you sanked a boat!!!");}
					if(user1.nDestroyed == 4) {             					// change it 
						System.out.println("USER1 won!");
						System.exit(0);
					}
					//clearScreen();
				}
			}
			while( us2 == 1 || us2 == 2 || us2 == 3) {
				us2 = user2.shoot(user1);
				if(us2 != 3) {									// if it is not an error 
					if ( us2 == 2) user2.nDestroyed++;
					user2.myGrid.showGrid();
					System.out.println("USER2: your enemies board:");
					user2.enemyGrid.showGrid();
					if( us2 == 1 ) System.out.println("USER2: you shot a boat !");
					if ( us2 == 2) { user2.nDestroyed++; System.out.println("USER2: you sanked a boat!!!");}
					if(user2.nDestroyed == 4) {                                 // change it 
						System.out.println("USER2 won!");
						System.exit(0);
					}

					//clearScreen();
				}
			}				
		}

		
	}	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 
	public static void changeTurn(int userNo) {
		System.out.println("User "+userNo+"'s turn will start in 2 seconds..");
		
		try{
		    Thread.sleep(2000);
		}
		catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		}
		clearScreen();
	}

}
