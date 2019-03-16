import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		User user1 = new User(1);
		User user2 = new User(2);
		user1.putBoats(); user2.putBoats();

		System.out.println("/nNow the game starts.. ");
		
		while(user1.nDestroyed != 4 && user2.nDestroyed != 4) {  			// later change 3 to 10
			int us1 = 1,us2 = 1;
			while( us1 == 1 || us1 == 2 ) {
				us1 = user1.shoot(user2);
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
			}
			while( us2 == 1 || us2 == 2 ) {
				us2 = user2.shoot(user1);
				if ( us2 == 2) user2.nDestroyed++;
				user2.myGrid.showGrid();
				System.out.println("USER2: your enemies board:");
				user2.enemyGrid.showGrid();
				if( us2 == 1 ) System.out.println("USER2: you shot a boat !");
				if ( us2 == 2) { user2.nDestroyed++; System.out.println("USER2: you sanked a boat!!!");}
				if(user2.nDestroyed == 4) {
					System.out.println("USER2 won!");
					System.exit(0);
				}
			}				
		}

	}	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 

}
