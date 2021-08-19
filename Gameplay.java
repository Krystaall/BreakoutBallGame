package breakoutBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play =false; //when game starts it shouldnt play by itself
	private int score=0;
	private int totalBricks=21;
	private Timer timer;
	private int delay=8; 
	private int playerX=310;//starting position of slider
	private int ballposX=120;//starting position of ball
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private MapGenerator map;
	
	//constructor
	public Gameplay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {//function to draw ball,slider,bricks etc
		
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString(""+score,590, 30);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalBricks<=0) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("You won:" ,190, 300);
			
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("Press enter to restart:" ,230, 350);
		}
		
		if(ballposY > 570) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("Game over,score:" ,190, 300);
			
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("Press enter to restart:" ,230, 350);
			
		}
		
		g.dispose();
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			
			//here intersection can be used only for rectangles
			if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX, 550,100,8))) {
				ballYdir=-ballYdir; //so that ball rebounds after intersection along the same slope in -ve dir.
			}
			
			A: for(int i=0;i<map.map.length; i++) {
				for(int j=0; j< map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth +80;
						int brickY = i*map.brickHeight +50;
						int brickWidth= map.brickWidth;
						int brickHeight= map.brickHeight;
						
						Rectangle rect= new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect= new Rectangle(ballposX, ballposY,20,20);
						Rectangle brickRect=rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX +1 >=brickRect.x + brickRect.width) {
								ballXdir=-ballXdir;
							}
							else {
								ballYdir=-ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			//left border
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			
			//top border
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			
			//right border
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}
		
		repaint();//just calls paint method again so after every turn the window is repainted
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //here if the user presses right arrow key 
			if(playerX>=600) {//if already at the right border then remain at the border
				playerX=600;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<10) {//if already at the left border then remain at the border
				playerX=10;
			}
			else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				score=0;
				totalBricks=21;
				map=new MapGenerator(3,7);
				
				repaint();

				
			}
		}
	}

	public void moveRight() {
		play=true;
		playerX += 20;
	}

	public void moveLeft() {
		play=true;
		playerX -= 20;
	}
	


}
