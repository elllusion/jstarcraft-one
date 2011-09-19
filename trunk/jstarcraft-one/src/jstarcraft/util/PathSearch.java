package jstarcraft.util;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径寻找的算法类
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 上午09:30:02
 */
public class PathSearch {
	/**
	 * 在数组map中中寻找start到goal的路径
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-17 上午09:30:35
	 * @param map
	 * @param start
	 * @param goal
	 * @return 存储路径的List
	 */
	public static List<Point> findPath(int[][] map, Point start, Point goal) {
		// 转换成node
		StarNode startNode = new StarNode(start);
		StarNode goalNode = new StarNode(goal);
		// open list
		LinkedList<StarNode> open = new LinkedList<StarNode>();
		LinkedList<StarNode> close = new LinkedList<StarNode>();
		// init
		startNode.searchParent = null;
		open.add(startNode);
		close.add(startNode);

		while (!open.isEmpty()) {

			StarNode node = open.removeFirst();
			// 起始节点等于结束节点
			if (node.equals(goalNode)) {
				return construct(node);
			}
			else {
				// 对周围节点进行遍历
				for (StarNode n : node.getNeighbors()) {
					if (!open.contains(n) && !close.contains(n) && n.isHit(map)) {
						// 节点n可以作为下一个搜寻路径
						// n是node的下一步搜索结果，根据这个来生成路径
						n.searchParent = node;
						// 加入到open中，作为下一步搜索的对象
						open.add(n);
						close.add(n);
					}
				}
			}
		}
		return null;
	}
	/**
	 * 在方法findPath()中对startNode，goalNode进行处理之后，
	 * 通过顺序处理节点的searchParent属性，得到搜索出来的路径。 用List存储。
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-17 上午09:57:44
	 * @param node
	 * @return
	 */
	public static List<Point> construct(StarNode node) {

		LinkedList<Point> path = new LinkedList<Point>();
		while (node != null) {
			path.addFirst(node.location);
			node = node.searchParent;
		}
		return path;
	}

	public static void main(String[] args) {
		int[][] map = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

		List<Point> list = findPath(map, new Point(1, 1), new Point(10, 13));

		for (Point p : list) {
			System.out.println(p.x + "," + p.y);
		}
	}
}
