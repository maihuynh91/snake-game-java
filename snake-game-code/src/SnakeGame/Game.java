package SnakeGame;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		 JFrame frame = new JFrame("Snake Games");
		 frame.setContentPane(new BoardGame());
		 frame.pack();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 frame.setLocationRelativeTo(null);
		}
	}
