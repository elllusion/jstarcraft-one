package jstarcraft.tile;

import java.awt.Graphics2D;
import java.awt.Point;

import jstarcraft.core.GridMap;


/**
 * 游戏地图界面中的一个组件
 * 
 * @author Xewee.Zhiwei.Wang
 * @time 2011-9-14 下午06:44:35
 */
public interface Tile extends java.io.Serializable{

	public void focus();
	
	public void blur();
	
	public Tile clone(int x,int y,GridMap gridMap);
	
	public void draw(Graphics2D g,int offsetX,int offsetY);
	
	public void update(long elapsedTime);
	
	public int getTileX();
	
	public int getTileY();
	
	public int getType();
	
	public int getId();
	
	public Point getSize();
	
	public void setHealth(float health);	
	
	public float getHealth();	
	
	public boolean operate(int x,int y,String uuid );
	 
	public float getDefence();
	
	public void setUUID(String i);
	
	public String getUUID();
	
}
