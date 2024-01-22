package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Currency;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable {
	
	
	
	
	final int originalTileSize=16;
	final int scale =3;
	
	public final int tileSize=originalTileSize * scale;
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	public final int screenWidth=tileSize*maxScreenCol;
	public final int screenHeight=tileSize*maxScreenRow;
	
	KeyHand keyH= new KeyHand();
	Thread gameThread;
	public CollisionChecker cChecker= new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	//player position
	
	int pX=100;
	int pY=100;
	int speed=4;
	
	TileManager tileM=new TileManager(this);
	
	//WORLD SETTINGS
		public final int maxworldcol=50;
		public final int maxworldrow=50;
		public final int worldwidth=tileSize*maxworldcol;
		public final int worldheight=tileSize*maxworldrow;
		
		
		
		
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setup() {
		
		aSetter.setObject();
	}
	
	public void startgame() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	int fps=60;
	@Override
	public void run() {
		
		while(gameThread!=null) {
//			System.out.println(System.nanoTime());
			double draw=1000000000/fps;
			double del=0;
			long lasttime=System.nanoTime();
			long currenttime;
			long timer=0;
			int drawCount=0;
			
			while (gameThread != null ) {
				currenttime=System.nanoTime();
				del+= (currenttime-lasttime)/draw;
				timer+= (currenttime- lasttime);
//				System.out.println(del+" "+currenttime+" "+lasttime+" "+draw);
				lasttime=currenttime; // xử lí khung hình kiểu delta
				
				if (del>= 1) {
//					System.out.println("The game loop is running");
					// 1 update: update infor về nhân vật, vị trí các kiểu
//					System.out.println(pX+"--"+pY);
					update();
					
					// 2 draw: vẽ lại khung hình
					
					repaint();
					del--;
					drawCount++;
				}
				if (timer>=1000000000) {
					System.out.println("FPS: "+drawCount);
					drawCount=0;
					timer=0;
				}
				

			}
		}
	}
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2= (Graphics2D)g;
		
		//tile
		tileM.draw(g2);
		//object
		for (int i=0; i< obj.length;  i++) {
			if (obj[i] != null) {
				obj[i].draw(g2,this);
			}
		}
		//player
		player.draw(g2);
		
		g2.dispose();
	}
	
}
