package breakoutBall;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	

		Gameplay gameplay =new Gameplay();
		
		JFrame frame = new JFrame();
		frame.setBounds(10,10,700,600);
		frame.setTitle("BREAKOUT BALL");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameplay);
		
	}

}
