package breakoutBall;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickHeight;
	public int brickWidth;
	
	public MapGenerator(int row, int col) {//constructor
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1;//here 1 indicates brick is present and still not broken by the ball 
				            //i.e. ball did not intersect brick yet
			}
		}
		
		brickWidth= 540/col;
		brickHeight= 150/row; //already defined here for better visual representation 
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.white);
					g.fillRect(j*brickWidth +80 , i * brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth +80 , i * brickHeight + 50, brickWidth, brickHeight);
					
					}
				}
			}
		
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col]= value;
	}
	

}
