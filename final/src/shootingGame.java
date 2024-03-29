import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class shootingGame extends JFrame {

	
	private Image bufferImage;
	private Graphics screenGraphic;
	
	private Image mainScreen = new ImageIcon("src/img/main_screen.jpg").getImage();
	
	private Image loadingScreen = new ImageIcon("src/img/loadingScreen.jpg").getImage();//�̹��� �ٲܰ�
	private Image gameScreen = new ImageIcon("src/img/game_screen.png").getImage();//�̹��� �ٲܰ�
	
	private boolean isMainScreen, isLoadingScreen, isGameScreen;
	
	private Game game = new Game();
	
	public shootingGame() {
		setTitle("Shooting Game");
		setUndecorated(true);
		setSize(Main.SCREEEN_WIDTH, Main.SCREEEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		
		init();
	}
	
	private void init() {
		isMainScreen = true;
		isLoadingScreen = false;
		isGameScreen = false;
		
		addKeyListener(new KeyListener());
	}
	
	private void gameStart() {
		isMainScreen = false;
		isLoadingScreen =true;
		
		Timer loadingTimer = new Timer();
		TimerTask loadingTask = new TimerTask() {
			
			@Override
			public void run() {
				isLoadingScreen = false;
				isGameScreen = true;
				game.start();
			}
		};
		loadingTimer.schedule(loadingTask, 3000);
		
	
	}
	
	@Override
	public void paint(Graphics g) {
		bufferImage = createImage(Main.SCREEEN_WIDTH, Main.SCREEEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0,null);
		
	}
	
	public void screenDraw(Graphics g) {
		if(isMainScreen) {
		g.drawImage(mainScreen,0,0,null);
		}
		if(isLoadingScreen) {
			g.drawImage(loadingScreen,0,0,null);
		}
		if(isGameScreen) {
			g.drawImage(gameScreen,0,0,null);
			game.gameDraw(g);
		}
		this.repaint();
	}
	
	class KeyListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
					game.setUp(true);
					break;
				case KeyEvent.VK_S:
					game.setDown(true);
					break;
				case KeyEvent.VK_A:
					game.setLeft(true);
					break;
				case KeyEvent.VK_D:
					game.setRight(true);
					break;
				case KeyEvent.VK_M:
					game.setShooting(true);
					break;
					
				case KeyEvent.VK_R:
					if(game.isOver())
					game.reset();
					break;
					
					
				case KeyEvent.VK_ENTER:
					if(isMainScreen) {
						gameStart();
					}
					break;		
					
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;					
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
					game.setUp(false);
					break;
				case KeyEvent.VK_S:
					game.setDown(false);
					break;
				case KeyEvent.VK_A:
					game.setLeft(false);
					break;
				case KeyEvent.VK_D:
					game.setRight(false);
					break;	
				case KeyEvent.VK_M:
					game.setShooting(false);
					break;
					
				
							
			}
		}
		
	}
}
