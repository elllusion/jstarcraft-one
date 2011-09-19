package jstarcraft.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏地图中的一个坐标节点，主要用一个Point表示节点的位置 用一个LinkList表示这个节点周边的节点
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 上午09:31:57
 */
public class StarNode {

	// 相邻节点
	private LinkedList<StarNode> neighbors = new LinkedList<StarNode>();
	// 节点的位置
	Point location;

	StarNode searchParent;

	StarNode(Point location) {
		this.location = location;
	}
	//添加一个周边节点
	public StarNode add(StarNode neighbor) {
		neighbor.add(this);
		return this;
	}
	//是否与节点相邻
	boolean contains(StarNode neighbor) {
		return neighbors.contains(neighbor);
	}
	//获得节点的周边节点
	public List<StarNode> getNeighbors() {
		int x = location.x;
		int y = location.y;
		
		List<StarNode> neighbors = new ArrayList<StarNode>();
		// 上
		neighbors.add(new StarNode(new Point(x, y - 1)));
		// 右上
		neighbors.add(new StarNode(new Point(x + 1, y - 1)));
		// 右
		neighbors.add(new StarNode(new Point(x + 1, y)));
		// 右下
		neighbors.add(new StarNode(new Point(x + 1, y + 1)));
		// 下
		neighbors.add(new StarNode(new Point(x, y + 1)));
		// 左下
		neighbors.add(new StarNode(new Point(x - 1, y + 1)));
		// 左
		neighbors.add(new StarNode(new Point(x - 1, y)));
		// 左上
		neighbors.add(new StarNode(new Point(x - 1, y - 1)));
		return neighbors;
	}

	/**
	 * 该节点在地图中是否是可通过区域
	 * 
	 * @param map
	 * @return 可通行为true，不可通行为false
	 */
	boolean isHit(int[][] map) {
		int x = location.x;
		int y = location.y;
		//节点在map大小外部
		if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
			return false;
		}
		else {
			return map[y][x] == 0;
		}
	}
	//复写方法equals方法
	public boolean equals(Object obj) {
		if (obj instanceof StarNode) {
			StarNode n = (StarNode) obj;
			if (this.location.equals(n.location)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
