public class Boat {
	int [][]positions = new int[2][2];
	int length;
	boolean verticle;
	
	public void createBoat(int x1,int y1, int x2,int y2) {
		if(x1 == x2 || y1 == y2) {

			if(x1 == x2) {
				this.length = Math.abs(y2-y1)+1;
				this.verticle = true;
				if(y1>y2) {
					int y = y1;
					int x = x1;
					x1 = x2; y1 = y2;
					x2 = x; y2 = y;
					
				}
			}
			else {
				this.length = Math.abs(x2-x1)+1;
				this.verticle = false;
				if(x1 > x2) {
					int y = y1;
					int x = x1;
					x1 = x2; y1 = y2;
					x2 = x; y2 = y;
				}
			}
			this.positions[0][0] = x1;
			this.positions[0][1] = y1;
			this.positions[1][0] = x2;
			this.positions[1][1] = y2;
			
		}
	}
}
