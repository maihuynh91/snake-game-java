
package SnakeGame;
// import all required classes with format package.class
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class BoardGame extends JPanel implements ActionListener {
	
	//declare all array x, array y, and variables
	private final int x[] = new int[900];
	private final int y[] = new int[900];
	
	public  final int WIDTH = 300;
	public  final int HEIGHT = 300;
	private final int DELAY = 160;
	
	private final int DOT_SIZE = 10;
	
	private Image body; 
	private Image head;
	private int enemy_x;
	private int enemy_y;
	
    
    private boolean right = true;
	private boolean left = false;
	private boolean up = false;
    private boolean down = false;
   
	private int dots;
	private boolean gameisOver = false;
	private Timer timer;
	
	//constructor BoardGame calls the createBoard method
	public BoardGame() 
	{
		createBoard();
	}
	
	//create game board
	private void createBoard()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		setBackground(Color.pink);
		getSnakeImage();
		startGame();
		this.addKeyListener(new PressedKey());

	}
	
	//get snake's head and snake's body images
	private void getSnakeImage() 
	{
		ImageIcon bd= new ImageIcon(getClass().getResource("head.png"));
		body = bd.getImage();
		ImageIcon hd = new ImageIcon(getClass().getResource("apple.png"));
		head = hd.getImage();
	}
	
	//locate enemy position
	private void locateEnemy()
	{
		enemy_x = ((int)(Math.random() * 30)) * 10; //get random x position for enemy
		enemy_y = ((int)(Math.random() * 30)) * 10; //get random y position for enemy
	}
	
	//start the game
	private void startGame()
	{
		if(timer != null && timer.isRunning())timer.stop();
		
		if(!gameisOver) {
			dots = 2; //set initial length of snake is less than 2
			for(int z = 0; z < dots; z++)
			{
				x[z] = 30 - z * 10;
				y[z] = ((int)(Math.random() * 30)) * 10;
			}
			
			locateEnemy();
			timer = new Timer(DELAY, this);
		    timer.start();
		}
	}
	
	//draw enemy and snake
	private void drawing(Graphics g)
	{	
		if(gameisOver) {
			g.drawString("Game Over, Run Again!", (WIDTH/2), (HEIGHT/2));
		}
		else {
		g.setColor(Color.blue);		
	    g.fillRect(enemy_x, enemy_y,10,10); //draw enemy at enemy_x, enemy_y position with width and height:10
	    
	    for (int z = 0; z < dots; z++) {
	        if (z == 0) {
	            g.drawImage(head, x[z], y[z], this);
	        } else {
	            g.drawImage(body, x[z], y[z], this);
	        }
	      }
		}
	}

	//call drawing method
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    drawing(g);
	    }
	
	//move snake position to LEFT, RIGHT, UP, DOWN
	 private void moveSnake() 
	 {
		 for (int z = dots; z > 0; z--) {
	         x[z] = x[(z - 1)];
	         y[z] = y[(z - 1)];
	        }
		 
		 if (left) {
	         x[0] -= DOT_SIZE;
	        }
		 
		 if (right) {
            x[0] += DOT_SIZE;
		 	}

		 if (up) {
            y[0] -= DOT_SIZE;
		  }

		 if (down) {
            y[0] += DOT_SIZE;
          }     
	  }
	 
	//press key to move the snake
	 private class PressedKey extends KeyAdapter { 
	     public void keyPressed(KeyEvent e) {
	    	 int key = e.getKeyCode();

	            if ((key == KeyEvent.VK_LEFT) && (!right)) {
	                left = true;
	                up = false;
	                down = false;
	            }

	            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
	                right = true;
	                up = false;
	                down = false;
	            }

	            if ((key == KeyEvent.VK_UP) && (!down)) {
	                up = true;
	                right = false;
	                left = false;
	            }

	            if ((key == KeyEvent.VK_DOWN) && (!up)) {
	                down = true;
	                right = false;
	                left = false;
	            }
	        }  
	    }

	 //call action to be performed
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Timer run: " +e.getSource());
		{
			moveSnake();
			addSnakeBody();
			gameOver();
		}
		repaint(); //get component to repaint itself
	}
	
	//add dots to snake body if snake hits enemy
	private void addSnakeBody()
	{
		if((x[0] == enemy_x ) && ( y[0] == enemy_y))
			{
				dots++;
				locateEnemy();
			}
	}
	
	// if snake hits the boundaries, game is over
	private void gameOver()
	{
		if ((x[0] < 0) || (y[0] < 0) || (x[0] >= WIDTH) || (y[0] >= HEIGHT))
		{
			gameisOver = true;
			timer.stop();
		}    
	}
	 
	
	}
	 
	 
	 