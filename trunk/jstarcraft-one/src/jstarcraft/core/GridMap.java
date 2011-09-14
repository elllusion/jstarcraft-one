package jstarcraft.core;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jstarcraft.icon.BaseIcon;
import jstarcraft.tile.Sprite;
import jstarcraft.tile.Tile;
import jstarcraft.util.path.AStarSearch;


/**
 * 利用网格grid来分析一个游戏地图map
 * 
 * @author Xewee.Zhiwei.Wang
 * @time 2011-9-14 下午06:33:43
 */
public class GridMap {

	private Grid[][] map;

	private int width, height;
	// 地图中具有的tile
	private List<Tile> tiles = Collections
			.synchronizedList(new ArrayList<Tile>());

	private GridMapRender tileMapRender;

	private Map<String, BaseIcon> iconMap;

	private long count;

	public GridMap(int width, int height) {
		this.width = width;
		this.height = height;

		// 初始化地图
		map = new Grid[height][width];
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				map[y][x] = new Grid();
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * 从地图中删除一个tile
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @param tile
	 */
	public void remove(Tile tile) {
		if (tile == null) {
			return;
		}
		// 从游戏地图界面上删掉tile
		tiles.remove(tile);
		Point loca = new Point(tile.getTileX(), tile.getTileY());
		Point size = tile.getSize();
		// 去掉Grid[][]中相应的部分
		for (int y = loca.y; y < loca.y + size.y; ++y) {
			for (int x = loca.x; x < loca.x + size.x; ++x) {
				map[y][x].remove(tile);
			}
		}
	}

	/**
	 * 在游戏地图界面上添加一个tile
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @param tile
	 */
	public synchronized void add(Tile tile) {

		Point loca = new Point(tile.getTileX(), tile.getTileY());
		Point size = tile.getSize();
		tiles.add(tile);

		for (int y = loca.y; y < loca.y + size.y; ++y) {
			for (int x = loca.x; x < loca.x + size.x; ++x) {
				map[y][x].add(tile);
			}
		}

		if (tile.getUUID() == null) {
			tile.setUUID(String.valueOf(++count));
		}
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public Grid getGrid(int tileX, int tileY) {
		return map[tileY][tileX];
	}

	public Tile getTile(int tileX, int tileY) {
		return map[tileY][tileX].isEmpty() ? null : map[tileY][tileX]
				.getFirst();
	}

	public Tile getTile(String UUID) {
		for (Tile tile : tiles) {
			if (tile.getUUID().equals(UUID)) {
				return tile;
			}
		}
		return null;
	}

	/**
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @param s
	 * @param toTileX
	 * @param toTileY
	 */
	public void move(Sprite s, int toTileX, int toTileY) {
		// System.out.println("move:"+toTileY+","+toTileX);
		map[s.getTileY()][s.getTileX()].remove(s);
		map[toTileY][toTileX].add(s);
	}

	/**
	 * 判断此grid是否已经有tile占用
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y) {
		return (x < 0 || y < 0) || !map[y][x].isEmpty();
	}

	/**
	 * 判断节点路径是否被阻挡
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHit(int x, int y) {
		// 如果此grid不为空
		if (!map[y][x].isEmpty()) {
			Tile tile = map[y][x].getFirst();
			if (!(tile instanceof Sprite)) {
				return true;
			}
			// return true;
		}
		return false;
	}

	public GridMapRender getTileMapRender() {
		return tileMapRender;
	}

	public void setTileMapRender(GridMapRender tileMapRender) {
		this.tileMapRender = tileMapRender;
	}

	public String getIconKey(BaseIcon icon) {
		for (Entry<String, BaseIcon> iconEntry : iconMap.entrySet()) {
			if (iconEntry.getValue().equals(icon)) {
				return iconEntry.getKey();
			}
		}
		return null;
	};

	public BaseIcon getIconValue(String key) {
		return iconMap.get(key);
	}

	public void setIconMap(Map<String, BaseIcon> iconMap) {
		this.iconMap = iconMap;
	};

	/**
	 * 从House.update()里面抽取出来
	 * 
	 * @param tile
	 * @param icon
	 */
	public void buildSprite(Tile house, BaseIcon icon, String newTileUUId) {

		System.out.println("GridMap.buildSprite()" + newTileUUId + "," + house);
		Tile tile = icon.getTile(house.getType());
		GridMapRender gm = getTileMapRender();
		Point location = AStarSearch.findNeighborNode(this, GridMapRender
				.tileXToPx(house.getTileX()), GridMapRender.tileYToPx(house
				.getTileY()));
		tile = tile.clone(location.x, location.y, this);
		tile.setUUID(newTileUUId);
		gm.addBuildTile(tile);
		tile.setHealth(1.0f);
	}
}
