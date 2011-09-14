package jstarcraft.net;


import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import jstarcraft.icon.BaseIcon;
import jstarcraft.icon.HouseIcon;
import jstarcraft.net.datagram.IconInfo;
import jstarcraft.net.datagram.MoveInfo;
import jstarcraft.net.datagram.PlayerInfo;
import jstarcraft.net.datagram.PlayerList;
import jstarcraft.net.datagram.SpriteInfo;
import jstarcraft.net.socket.Client;
import jstarcraft.net.socket.ClientListener;
import jstarcraft.net.socket.Server;
import jstarcraft.net.udp.Player;
import jstarcraft.net.udp.PlayerListener;
import jstarcraft.tile.Sprite;
import jstarcraft.tile.Tile;


/**
 * 每个JStarWar应用程序对应一个NewWorkManager,每当一个用户创建Server的时候广播一次.通知所有在局域网的用户有这个Server.
 * 每个Client后台需要开一个线程不停的监听获取的信息. 另外,对于已在的服务器列表需要开个dectector线程不停的简体
 * 
 * @see Broadcaster,Detector,Receiver
 * @author jiangyp
 * 
 */
public interface NetWorkManager {

	public void establishServer(String serverName);

	public void join(PlayerInfo clientInfo, PlayerInfo serverInfo);

	public void listen();

	public void closeListen();

	public void select(String name, int index);

	public PlayerList startGame();

	public void addPlayerListener(PlayerListener playerListener);

	public void addClientListener(ClientListener clientListener);

	public void move(List<Sprite> sprites, int tx, int ty);

	/**
	 * 
	 * @param tile
	 * @param tx
	 * @param ty
	 * @param newTileUUID 新tileUUID
	 */
	public void operate(Tile tile, int tx, int ty,String newTileUUID);

	public void build(IconInfo iconInfo);
	
	public void readyBuild(IconInfo iconInfo);
	
	/**
	 * 为了避免数据不同步，需要通知每个Player删除tile
	 * @param iconInfo
	 */
	public void remove(SpriteInfo spriteInfo);
	public void close();

}
