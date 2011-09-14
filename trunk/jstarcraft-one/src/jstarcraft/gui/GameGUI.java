package jstarcraft.gui;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jstarcraft.core.FocusManager;
import jstarcraft.core.GridMapRender;
import jstarcraft.core.ResourceManager;
import jstarcraft.core.ResourceManager.Constant;
import jstarcraft.icon.BaseIcon;
import jstarcraft.net.NetWorkManager;
import jstarcraft.net.datagram.IconInfo;
import jstarcraft.net.datagram.MoveInfo;
import jstarcraft.net.datagram.PlayerInfo;
import jstarcraft.net.datagram.SpriteInfo;
import jstarcraft.net.socket.ClientEvent;
import jstarcraft.net.socket.ClientListener;
import jstarcraft.net.udp.PlayerEvent;
import jstarcraft.net.udp.PlayerListener;
import jstarcraft.tile.Scv;
import jstarcraft.tile.Sprite;
import jstarcraft.tile.Tile;


@SuppressWarnings({ "unused", "serial" })
public class GameGUI extends JPanel {

	//卡片布局器
	private CardLayout cardLayout = new CardLayout();
	//进度条面板 
	private ProgressPanel progressPanel; 
	//菜单面板
	private MainMenuPanel menuPanel;
	//游戏面板
	private GamePanel gamePanel;
	//需要update
	private Abstractpanel currentPanel;
	//单人游戏面板
	private SingleGamePanel singleGamePanel;
	//多人能游戏面板
	private MutilPlayerPanel mutilPlayerPanel;
	
	NewGamePanel newGamePanel;
	
	NetWorkManager netWorkManager;
	
	GridMapRender mapRender;
	
	JFrame frame;
	public GameGUI(JFrame frame) {
		this.frame = frame;
		setLayout(cardLayout);
		getBorder();
		frame.getContentPane().add(this);
		setSize(frame.getWidth(), frame.getHeight());
		//这里有个次序,最先加入的显示
		currentPanel = menuPanel = new MainMenuPanel(this, "mainMenu");
		progressPanel = new ProgressPanel(this, "progress");
		gamePanel = new GamePanel(this, "game");
		singleGamePanel = new SingleGamePanel(this,"singleGame");
		mutilPlayerPanel = new MutilPlayerPanel(this,"mutilGame");
		newGamePanel = new NewGamePanel(this,"newGame");
		
	}
	public void switchProgress(final int type,final List<Integer> types) {
	    
		cardLayout.show(this, "progress");
		currentPanel = progressPanel;
		
		Thread t = new Thread(){
			public void run(){
				mapRender = ResourceManager.load(type,types);
				mapRender.setNetWorkManager(netWorkManager);
			}
		};
		t.start();
	
	}

	public void switchGame() {
	 
		gamePanel.init();
		cardLayout.show(this, "game");
		currentPanel = gamePanel;
	}
	
	public void switchSingleGame() {
		
		cardLayout.show(this, "singleGame");
		singleGamePanel.reset();
		currentPanel = singleGamePanel;
	}
	
	public void switchMutilGame() {
		
		cardLayout.show(this, "mutilGame");
		mutilPlayerPanel.reset();
		currentPanel = mutilPlayerPanel;
	}
	
	public void switchMainMenu() {
		
		cardLayout.show(this, "mainMenu");
		menuPanel.reset();
		currentPanel = menuPanel;
		
	}
	
	public void switchNewGame() {
		
		cardLayout.show(this, "newGame");
		newGamePanel.reset();
		currentPanel = newGamePanel;
		
	}
	 
	
	
	public void update(long elapsedTime) {
		 
		  if(currentPanel!=null){
			  currentPanel.update(elapsedTime);
		  }
		 
	}
}
