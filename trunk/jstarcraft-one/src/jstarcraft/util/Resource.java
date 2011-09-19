package jstarcraft.util;

/**
 * 资源类
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 上午10:00:08
 */
public class Resource {
	//矿
	private int mine;
	//兵
	private int man;

	public Resource(int mine, int man) {
		super();
		this.mine = mine;
		this.man = man;
	}

	public int getMan() {
		return man;
	}

	public void setMan(int man) {
		this.man = man;
	}

	public int getMine() {
		return mine;
	}

	public void setMine(int mine) {
		this.mine = mine;
	}

}
